import javafx.util.Pair;

import java.util.*;

/**
 * Given an expression such as expression = "e + 8 - a + 5" and an evaluation map such as {"e": 1} (given in terms of evalvars = ["e"] and evalints = [1]), return a list of tokens representing the simplified expression, such as ["-1*a","14"]
 *
 * An expression alternates chunks and symbols, with a space separating each chunk and symbol.
 * A chunk is either an expression in parentheses, a variable, or a non-negative integer.
 * A variable is a string of lowercase letters (not including digits.) Note that variables can be multiple letters, and note that variables never have a leading coefficient or unary operator like "2x" or "-x".
 * Expressions are evaluated in the usual order: brackets first, then multiplication, then addition and subtraction. For example, expression = "1 + 2 * 3" has an answer of ["7"].
 *
 * The format of the output is as follows:
 *
 * For each term of free variables with non-zero coefficient, we write the free variables within a term in sorted order lexicographically. For example, we would never write a term like "b*a*c", only "a*b*c".
 * Terms have degree equal to the number of free variables being multiplied, counting multiplicity. (For example, "a*a*b*c" has degree 4.) We write the largest degree terms of our answer first, breaking ties by lexicographic order ignoring the leading coefficient of the term.
 * The leading coefficient of the term is placed directly to the left with an asterisk separating it from the variables (if they exist.)  A leading coefficient of 1 is still printed.
 * An example of a well formatted answer is ["-2*a*a*a", "3*a*a*b", "3*b*b", "4*a", "5*c", "-6"]
 * Terms (including constant terms) with coefficient 0 are not included.  For example, an expression of "0" has an output of [].
 * Examples:
 *
 * Input: expression = "e + 8 - a + 5", evalvars = ["e"], evalints = [1]
 * Output: ["-1*a","14"]
 *
 * Input: expression = "e - 8 + temperature - pressure",
 * evalvars = ["e", "temperature"], evalints = [1, 12]
 * Output: ["-1*pressure","5"]
 *
 * Input: expression = "(e + 8) * (e - 8)", evalvars = [], evalints = []
 * Output: ["1*e*e","-64"]
 *
 * Input: expression = "7 - 7", evalvars = [], evalints = []
 * Output: []
 *
 * Input: expression = "a * b * c + b * a * c * 4", evalvars = [], evalints = []
 * Output: ["5*a*b*c"]
 *
 * Input: expression = "((a - b) * (b - c) + (c - a)) * ((a - b) + (b - c) * (c - a))",
 * evalvars = [], evalints = []
 * Output: ["-1*a*a*b*b","2*a*a*b*c","-1*a*a*c*c","1*a*b*b*b","-1*a*b*b*c","-1*a*b*c*c","1*a*c*c*c","-1*b*b*b*c","2*b*b*c*c","-1*b*c*c*c","2*a*a*b","-2*a*a*c","-2*a*b*b","2*a*c*c","1*b*b*b","-1*b*b*c","1*b*c*c","-1*c*c*c","-1*a*a","1*a*b","1*a*c","-1*b*c"]
 * Note:
 *
 * expression will have length in range [1, 250].
 * evalvars, evalints will have equal lengths in range [0, 100].
 */

public class BasicCalculatorIV {
    class Result {
        int i;
        int numeric;
        List<Term> terms;
        public Result(int i, int numeric, List<Term> terms) {
            this.i = i;
            this.terms = terms;
            this.numeric = numeric;
        }
    }
    class Term implements Comparable<Term> {
        List<StringBuilder> vars = new ArrayList<>();
        String expression = "";
        int leading = 1;
        int evaluatedVars = 0;
        int numeric = 0;
        public Term() {}
        public Term(Term a, Term b) {
            this.leading = a.leading * b.leading;
            for (StringBuilder var : a.vars) {
                if (isInteger(var.toString())) {
                    leading *= Integer.parseInt(var.toString());
                } else {
                    vars.add(var);
                }
            }
            for (StringBuilder var : b.vars) {
                if (isInteger(var.toString())) {
                    leading *= Integer.parseInt(var.toString());
                } else {
                    vars.add(var);
                }
            }
        }
        void evaluate(Map<String, Integer> eval) {
            String var = vars.get(vars.size() - 1).toString();
            if (var.isEmpty()) return;
            if (eval.containsKey(var)) {
                int value = eval.get(var);
                leading *= value;
                vars.get(vars.size() - 1).setLength(0);
            } else {
                evaluatedVars++;
            }
        }
        void addChar(char c) {
            vars.get(vars.size() - 1).append(c);
        }
        void buildExpression() {
            StringBuilder builder = new StringBuilder();
            vars.sort(Comparator.comparing(StringBuilder::toString));
            int asterisks = 0;
            for (int i = 0; i < vars.size(); i++) {
                if (isInteger(vars.get(i).toString())) {
                    leading *= Integer.parseInt(vars.get(i).toString());
                    continue;
                }
                builder.append(vars.get(i));
                builder.append('*');
                asterisks++;
            }
            if (asterisks > 0) builder.setLength(builder.length() - 1);
            expression = builder.toString();
        }
        @Override
        public String toString() {
            return leading + "*" + vars.toString();
        }
        @Override
        public int compareTo(Term t2) {
            return t2.vars.size() - this.vars.size();
        }
    }

    public List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {
        List<String> finalList = new ArrayList<>();
        Map<String, Integer> eval = new HashMap<>();
        for (int i = 0; i < evalvars.length; i++) {
            eval.put(evalvars[i], evalints[i]);
        }
        Result result = parse(expression, eval, 0);
        List<Term> terms = result.terms;
        List<Term> filtered = new ArrayList<>();
        int numeric = 0;
        for (int i = 0; i < terms.size(); i++) {
            terms.get(i).buildExpression();
            if (terms.get(i).expression.isEmpty()) {
                numeric += terms.get(i).leading;
                continue;
            }
            if (isInteger(terms.get(i).expression)) {
                numeric += terms.get(i).leading * Integer.parseInt(terms.get(i).expression);
            } else {
                filtered.add(terms.get(i));
            }
        }
        Collections.sort(filtered);
        for (Term term : filtered) {
            finalList.add(term.toString());
        }
        if (numeric != 0) {
            finalList.add(numeric + "");
        }
        return finalList;
    }

    private Result parse(String expression, Map<String, Integer> eval, int i) {
        List<Term> terms = new ArrayList<>();
        char lastSign = '+';
        int numeric = 0;
        while (i < expression.length()) {
            if (expression.charAt(i) == '(') {
                Result result = parse(expression, eval, i + 1);
                List<Term> innerTerms = result.terms;
                if (lastSign == '*') {
                    Term lastTerm = terms.get(terms.size() - 1);
                    terms.remove(terms.size() - 1);
                    for (Term term : innerTerms) {
                        terms.add(new Term(lastTerm, term));
                    }
                } else {
                    for (Term term : innerTerms) {
                        if (lastSign == '-') {
                            term.leading = -term.leading;
                        }
                        terms.add(term);
                    }
                }
                i = result.i;
                continue;
            } else if (expression.charAt(i) == ')') {
                i++;
                break;
            } else if (expression.charAt(i) == ' ') {
                Term lastTerm = terms.get(terms.size() - 1);
                lastTerm.evaluate(eval);
            } else if (expression.charAt(i) == '+') {
                lastSign = '+';
                terms.add(new Term());
                i += 2;
                continue;
            } else if (expression.charAt(i) == '-') {
                lastSign = '-';
                Term term = new Term();
                term.leading = -1;
                terms.add(term);
                i += 2;
                continue;
            } else if (expression.charAt(i) == '*') {
                lastSign = '*';
                i += 2;
                continue;
            } else {
                if (terms.size() == 0) {
                    terms.add(new Term());
                }
                Term lastTerm = terms.get(terms.size() - 1);
                if (lastTerm.evaluatedVars >= lastTerm.vars.size()) {
                    lastTerm.vars.add(new StringBuilder());
                }
                lastTerm.addChar(expression.charAt(i));
            }
            i++;
        }
        return new Result(i, numeric, terms);
    }

    private static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public void test() {
        //System.out.println(basicCalculatorIV("e + 8 - a + 5", new String[]{ "e" }, new int[] { 1 }).toString());
        //System.out.println(basicCalculatorIV("e - 8 + temperature - pressure", new String[]{ "e", "temperature" }, new int[] { 1, 12 }).toString());
        System.out.println(basicCalculatorIV("(e + 8) * (e - 8)", new String[]{ }, new int[] { }).toString());
        /*System.out.println(basicCalculatorIV("7 - 7", new String[]{ }, new int[] { }).toString());
        System.out.println(basicCalculatorIV("a * b * c + b * a * c * 4", new String[]{ }, new int[] { }).toString());
        System.out.println(basicCalculatorIV("((a - b) * (b - c) + (c - a)) * ((a - b) + (b - c) * (c - a))", new String[]{ }, new int[] { }).toString());*/
    }
}

/**
 * A dummy description of MinWindowSeq
 *
 * @author Fraga (antoniopedrofraga@gmail.com)
 * @since 1.0
 */
public class MinWindowSeq {
    public String minWindow(String S, String T) {
        int [] pos = new int[T.length()];
        int j = 0, i = 0, left = 0, right = 0;
        for (; i < S.length() && j < T.length(); i++) {
            if (T.charAt(j) == S.charAt(i)) {
                pos[j++] = i;
            }
        }
        if (j < T.length()) return "";
        left = pos[0];
        right = pos[T.length() - 1];
        i = pos[0] + 1; j = 0;
        while (T.length() != 1 && pos[T.length() - 1] < S.length() && i < S.length()) {
            while (i < S.length() && T.charAt(j) != S.charAt(i)) {
                i++;
            }
            if (i < S.length()) {
                pos[j] = i;
                if (j + 1 >= T.length() || pos[j] < pos[j + 1]) {
                    int newLeft = pos[0],
                            newRight = pos[T.length() - 1];
                    if (newLeft < newRight && newRight - newLeft < right - left) {
                        left = newLeft;
                        right = newRight;
                    } else {
                        j = 0;
                        i = pos[j] + 1;
                    }
                    continue;
                }
            }
            if (j + 1 < T.length()) {
                j = j + 1;
                i++;
            } else if (pos[T.length() - 1] < pos[T.length() - 2]) {
                break;
            } else {
                j = 0;
                i = pos[j] + 1;
            }
        }
        return S.substring(left, right + 1);
    }

    public void test() {
        System.out.println(minWindow("abcdebdde", "bde"));
        System.out.println(minWindow("jmeqksfrsdcmsiwvaovztaqenprpvnbstl", "k"));
        System.out.println(minWindow("cnhczmccqouqadqtmjjzl", "cm"));
        System.out.println(minWindow("hpsrhgogezyfrwfrejytjkzvgpjnqil", "ggj"));
        System.out.println(minWindow("cnhczmccqouqadqtmjjzl", "mm"));
        System.out.println(minWindow(
        "ffynmlzesdshlvugsigobutgaetsnjlizvqjdpccdylclqcbghhixpjihximvhapymfkjxyyxfwvsfyctmhwmfjyjidnfryiyajmtakisaxwglwpqaxaicuprrvxybzdxunypzofhpclqiybgniqzsdeqwrdsfjyfkgmejxfqjkmukvgygafwokeoeglanevavyrpduigitmrimtaslzboauwbluvlfqquocxrzrbvvplsivujojscytmeyjolvvyzwizpuhejsdzkfwgqdbwinkxqypaphktonqwwanapouqyjdbptqfowhemsnsl"
        ,"ntimcimzah"
        ));
        System.out.println(minWindow(
        "pcaimomtmveadexvauerxryuisraxixqbgnbwrjqidfdcnrlljirsioxlholkytimqtzubqommizbdxaunnydfylyerxmzatcedymqxgaochxnfeplecowiuwrqsaxjhqjfxpotrnytfteghimxlalhjclvnszzudxigaiozwduuudufxiupehkbumnlcempydugrsnqwfutycpfctlshyjlelszhtkaowhiweisjejisslhwcbwjdabmfnievmufkuvalpdufglzzwtvgqthbgzqqqxxzklhxvwygmrpoqlbwgopjnfymtcgxvtfqhztffdch"
        ,"oyncpimlbu"
        ));
    }
}

import javafx.util.Pair;

import java.util.*;

/**
 * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 *
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 *
 * Note:
 *
 * - A word is defined as a character sequence consisting of non-space characters only.
 * - Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * - The input array words contains at least one word.
 */


/**
 * LaTeX solves this problem by defining how bad it is to pack a line from word i to word j (inclusively).
 * We can define badness as (page width - total width) pow 3
 *
 * total width - Cube root of badness - (j - i) = width
 */
public class TextJustification {
    String emptyString(int size) {
        StringBuilder space = new StringBuilder("");
        for (int i = 0; i < size; i++) {
            space.append(' ');
        }
        return space.toString();
    }
    public List<String> fullDPJustify(String[] words, int maxWidth) {

        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        List<String> lines = new ArrayList<>();
        int [][] badness = new int[words.length][words.length];
        int [] bestMatch = new int[words.length];
        int [] bestBadness = new int[words.length];

        for (int i = 0; i < words.length; i++) {
            int currentWidth = 0;
            bestBadness[i] = Integer.MAX_VALUE;
            for (int j = i; j < words.length; j++) {
                currentWidth += words[j].length();
                int widthWithSpaces = (j - i) + currentWidth;
                badness[i][j] = widthWithSpaces > maxWidth ? Integer.MAX_VALUE : (int) Math.pow(maxWidth - widthWithSpaces, 3);
            }
        }

        /* BFS trough a graph to find the min path */
        queue.add(new Pair<>(0, 0));
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> peek = queue.remove();
            int i = peek.getKey(); int accumulatedBadness = peek.getValue();
            for (int j = i; j < words.length; j++) {
                if (badness[i][j] == Integer.MAX_VALUE) {
                    break;
                }
                int newBadness = accumulatedBadness + badness[i][j];
                int oldBadness = bestBadness[j];
                if (newBadness < oldBadness) {
                    bestBadness[j] = newBadness;
                    bestMatch[j] = i;
                    if (j + 1 < words.length) {
                        queue.add(new Pair<>(j + 1, newBadness));
                    }
                }
            }
        }

        for (int j = words.length - 1; j >= 0;) {
            int i = bestMatch[j], wordsWidth = (int)(maxWidth - Math.cbrt(badness[i][j]) - (j - i)), totalSpaces = (maxWidth - wordsWidth);
            StringBuilder line = new StringBuilder("");
            int evenDist = j == words.length - 1 || j == i ? 1 : totalSpaces / (j - i);
            int rest = j == words.length - 1 || j == i ? 0 : totalSpaces % (j - i);
            int charsSoFar = 0;
            for (int k = i; k <= j; k++) {
                int spaces = evenDist;
                if (rest > 0) {
                    rest--;
                    spaces++;
                }
                line.append(words[k]);
                charsSoFar += words[k].length();
                if (k < j) {
                    line.append(emptyString(spaces));
                    charsSoFar += spaces;
                } else if (k == j) {
                    line.append(emptyString(maxWidth - charsSoFar));
                }
            }
            lines.add(line.toString());
            j = i - 1;
        }

        Collections.reverse(lines);
        return lines;
    }
    /* It appears that we want a greedy algorithm.  */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> lines = new ArrayList<>();

        int wordsWidth = 0, i = 0;
        for (int j = 0; j < words.length; j++) {
            wordsWidth += words[j].length();
            int widthWithSpaces = wordsWidth + (j - i);

            /* If the next word doesn't fit in the line then we should pack it */
            if (j == words.length - 1 || (j < words.length - 1 && (widthWithSpaces + 1 + words[j + 1].length()) > maxWidth)) {
                int totalSpaces = maxWidth - wordsWidth;
                StringBuilder line = new StringBuilder("");
                int evenDist = j == words.length - 1 || j == i ? 1 : totalSpaces / (j - i);
                int rest = j == words.length - 1 || j == i ? 0 : totalSpaces % (j - i);
                int charsSoFar = 0;
                for (int k = i; k <= j; k++) {
                    int spaces = evenDist;
                    if (rest > 0) {
                        rest--;
                        spaces++;
                    }
                    line.append(words[k]);
                    charsSoFar += words[k].length();
                    if (k < j) {
                        line.append(emptyString(spaces));
                        charsSoFar += spaces;
                    } else if (k == j) {
                        line.append(emptyString(maxWidth - charsSoFar));
                    }
                }
                lines.add(line.toString());
                i = j + 1;
                wordsWidth = 0;
            }
        }
        return lines;
    }
}

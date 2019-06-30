public class EditDIstance {
    int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
    public int minDistance(String word1, String word2) {
        if (word1.equals("")) {
            return word2.length();
        } else if (word2.equals("")) {
            return word1.length();
        }
        int [][] distances = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < word1.length(); i++) {
            distances[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            distances[0][j] = j;
        }
        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                int a = i + 1, b = j + 1;
                int min = min(distances[a - 1][b], distances[a][b - 1], distances[a - 1][b - 1]);
                distances[a][b] = word1.charAt(i) != word2.charAt(j) ? min + 1 : distances[a - 1][b - 1];
            }
        }
        return distances[word1.length()][word2.length()];
    }
}

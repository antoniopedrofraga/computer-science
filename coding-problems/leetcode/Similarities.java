import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A dummy description of Similarities
 *
 * @author Fraga (antoniopedrofraga@gmail.com)
 * @since 1.0
 */
public class Similarities {
    public boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) return false;
        Set<String> similarities = new HashSet<>();
        for (List<String> pair : pairs) {
            similarities.add(pair.get(0) + "=" + pair.get(1));
        }
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) continue;
            if (!similarities.contains(words1[i] + "=" + words2[i]) &&
            !similarities.contains(words2[i] + "=" + words1[i])) {
                return false;
            }
        }
        return true;
    }
}

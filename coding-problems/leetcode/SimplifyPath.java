import java.util.ArrayList;
import java.util.Collections;

/**
 * Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.
 *
 * In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level. For more information, see: Absolute path vs relative path in Linux/Unix
 *
 * Note that the returned canonical path must always begin with a slash / , and there must be only a single slash / between two directory names.
 * The last directory name (if it exists) must not end with a trailing.
 * Also, the canonical path must be the shortest string representing the absolute path.
 */

public class SimplifyPath {
    public String simplifyPath(String path) {
        StringBuilder builder = new StringBuilder("");
        String [] array = path.split("/");
        ArrayList<String> finalPath = new ArrayList<>();
        int doubleDot = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            String current = array[i];
            if (current.equals("") || current.equals(".")) {
                continue;
            } else if (current.equals("..")) {
                doubleDot++;
            } else if (doubleDot > 0) {
                doubleDot--;
            } else {
                finalPath.add(current);
            }
        }
        if (finalPath.size() == 0) return "/";
        Collections.reverse(finalPath);
        for (String file : finalPath) {
            builder.append('/');
            builder.append(file);
        }
        return builder.toString();
    }
}

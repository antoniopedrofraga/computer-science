import java.util.Stack;

/**
 *
 * Suppose we abstract our file system by a string in the following manner:
 * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
 * dir
 *     subdir1
 *     subdir2
 *         file.ext
 *
 * The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.
 * The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:
 * dir
 *     subdir1
 *         file1.ext
 *         subsubdir1
 *     subdir2
 *         subsubdir2
 *             file2.ext
 *
 * The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.
 * We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).
 * Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.
 * Note:
 * The name of a file contains at least a . and an extension.
 * The name of a directory or sub-directory will not contain a ..
 * Time complexity required: O(n) where n is the size of the input string.
 * Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
 *
 * Approach: Use a stack to update levels of directories, this seems a depth first search.
 * Time complexity: O(n) -> n being the size of the input
 * Memory complexity: O(k n) -> n being the size of the input, k being the deepest level.
 */
public class LongestAbsolutePath {
    class File {
        int tabs;
        String name;
        public File(String name, int tabs) {
            this.name = name;
            this.tabs = tabs;
        }
    }
    public int lengthLongestPath(String input) {
        int max = 0, length = 0;
        Stack<File> path = new Stack<>();
        String [] lines = input.split("\n");
        for (String line : lines) {
            int i = 0, tabs = 0;
            for (; i < line.length() && line.charAt(i) == '\t'; i++) { tabs++; }
            while (path.size() > 0 && tabs <= path.peek().tabs) {
                length -= path.peek().name.length();
                path.pop();
            }
            path.push(new File(line.substring(tabs), tabs));
            length += path.peek().name.length();
            max = Math.max(max, length + path.size() - 1);
            if (line.indexOf(".") > 0)
                max = Math.max(max, length + path.size() - 1);
        }
        return max;
    }

    public void test() {
        System.out.println(lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
    }
}

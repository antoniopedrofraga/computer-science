import java.util.HashSet;
import java.util.Set;

/**
 * A dummy description of PhoneDirectory
 *
 * @author Fraga (antoniopedrofraga@gmail.com)
 * @since 1.0
 */
public class PhoneDirectory {
    Set<Integer> database = new HashSet<>();
    int maxNumbers;
    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        this.maxNumbers = maxNumbers;
        for (int i = 0; i < maxNumbers; i++) {
            database.add(i);
        }
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (database.size() == 0) return -1;
        int number = database.stream().findFirst().get();
        database.remove(number);
        return number;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return database.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if (number < maxNumbers && number >= 0) {
            database.add(number);
        }
    }
}

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Note: This is a companion problem to the System Design problem: Design TinyURL.
 *
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.
 *
 * Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 */

public class TinyUrl {
    String alphabet = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
    Map<String, String> dict = new HashMap<>();
    Random random = new Random();

    private String generateKey() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(alphabet.length());
            builder.append(alphabet.charAt(index));
        }
        return builder.toString();
    }
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String key = generateKey();
        while (dict.containsKey(key)) {
            key = generateKey();
        }
        String shortUrl = "http://tinyurl.com/" + key;
        dict.put(shortUrl, longUrl);
        return shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return dict.get(shortUrl);
    }
}

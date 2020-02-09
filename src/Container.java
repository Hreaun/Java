import java.util.HashMap;


public class Container {
    private HashMap<String, Integer> mapOfWords;
    private int wordCount = 0;

    Container() {
        mapOfWords = new HashMap<>();
    }

    public HashMap<String, Integer> getMapOfWords() {
        return mapOfWords;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void contain(String str) {
        String[] words = str.split("\\s+");
        for (String word : words) {
            if (word.isBlank())
                continue;
            word = word.substring(0, 1).toUpperCase() + word.substring(1);
            int count = mapOfWords.getOrDefault(word, 0);
            mapOfWords.put(word, count + 1);
            ++wordCount;
        }
    }
}

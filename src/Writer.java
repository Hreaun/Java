import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Writer {
    static public void write(String outStr, Container mapOfWords) {
        try {
            HashMap<String, Integer> map = mapOfWords.getMapOfWords();
            map = MapSort.sortByValue(map);
            FileWriter myWriter = new FileWriter(outStr);
            for (String i : map.keySet()) {
                String freq = String.format("%.4f", ((double) map.get(i) / mapOfWords.getWordCount()) * 100);
                myWriter.write(i + ";" + map.get(i) + ";" + freq + "%" + System.lineSeparator());
            }
            myWriter.close();
        } catch (IOException ex) {
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }

    }
}

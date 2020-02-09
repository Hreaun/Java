import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class MapSort {
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> map) {
        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
        map.entrySet().stream().sorted(HashMap.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        return reverseSortedMap;
    }
}


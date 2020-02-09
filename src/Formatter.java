public class Formatter {
    static public String format(String str) {
        str = str.toLowerCase();
        str = str.replaceAll("[^a-z0-9\\s]", "");
        return str;
    }
}

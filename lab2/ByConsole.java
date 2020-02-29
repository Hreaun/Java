import java.util.Scanner;

public class ByConsole implements Input {
    @Override
    public String[] read() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            String[] words = str.split("\\s+");
            if ((words[0].isEmpty()) || (words[0].charAt(0) == '#'))
                continue;
            if (words[0].equals("EXIT"))
                return null;
            return words;
        }
        return null;
    }
}

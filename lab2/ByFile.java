import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ByFile implements Input {
    Scanner myReader;

    ByFile(String in) throws FileNotFoundException {
        try {
            myReader = new Scanner(new FileReader(in));
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
            throw ex;
        }
    }

    @Override
    public String[] read() {
        while (myReader.hasNextLine()) {
            String str = myReader.nextLine();
            str = str.trim();
            String[] words = str.split("\\s+");
            if ((!words[0].isEmpty()) && (words[0].charAt(0) == '#'))
                continue;
            return words;
        }
        return null;
    }
}


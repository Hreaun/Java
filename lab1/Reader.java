import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Reader {
    public static Container read(String inStr) {
        Container wordMap = new Container();
        try {
            Scanner myReader = new Scanner(new FileReader(inStr));
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                data = Formatter.format(data);
                wordMap.contain(data);
            }
            myReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }
        return wordMap;
    }


    public static void main(String[] args) {
        Container mapOfWords = read(args[0]);
        Writer.write(args[1], mapOfWords);
    }
}

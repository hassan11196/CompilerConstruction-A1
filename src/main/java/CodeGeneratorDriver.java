import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class CodeGeneratorDriver {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        FileReader fileReader = new FileReader("./");
        Object obj = parser.parse(fileReader);
        System.out.println(obj);
        System.out.println("Heelo world");
    }
}

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;

public class Parser {
    private final String SOURCE_FILE_PATH;

    public Parser (String SOURCE_FILE_PATH){
        this.SOURCE_FILE_PATH = SOURCE_FILE_PATH;
    }

    public Document parse (){
        try {
            URL url = new URL(SOURCE_FILE_PATH);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(url.openStream());
        }catch (Exception e){
            throw new RuntimeException("Unexpected error parsing document");
        }
    }
}
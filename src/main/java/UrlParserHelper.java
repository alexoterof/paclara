import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UrlParserHelper {
  public static String parseUrl(String url){
    List<String> urlElements = Arrays.asList(url.split("/"));
    List<String> filteredUrlElements = urlElements.stream()
        .filter(element -> !element.matches(".*\\d.*"))
        .collect(Collectors.toList());
    return String.join("/", filteredUrlElements);
  }

  public static String parseRestParam(String url){
    List<String> urlElements = Arrays.asList(url.split("/"));
    return urlElements.stream()
        .filter(element -> element.matches(".*\\d.*"))
        .findFirst()
        .orElse(null);
  }
}

import java.util.List;
import org.json.*;

public class Mapper {

  public static String convertirComoListaAJSON(Object como) {
    JSONArray jsonArray = new JSONArray();
    jsonArray.put(new JSONObject(convertirAJSON(como)));
    return jsonArray.toString(4);
  }

  public static String convertirAJSON(Object objeto) {
    if (objeto == null) {
      return "{}";
    }
    if (objeto instanceof Country) {
      return convertirPaisAJSON((Country) objeto);
    } else if (objeto instanceof Book) {
      return convertirLibroAJSON((Book) objeto);
    } else if (objeto instanceof Author) {
      return convertirAutorAJSON((Author) objeto);
    } else if (objeto instanceof List<?>) {
      return convertirListaAJSON((List<?>) objeto);
    } else {
      return "{}";
    }
  }

  private static String convertirPaisAJSON(Country country) {
    JSONObject jsonPais = new JSONObject();
    jsonPais.put("nombre", country.getNombre());
    jsonPais.put("identificador", country.getIdentificador());
    return jsonPais.toString(4);
  }

  private static String convertirLibroAJSON(Book book) {
    JSONObject jsonLibro = new JSONObject();
    jsonLibro.put("titulo", book.getNombre());
    jsonLibro.put("identificador", book.getIdentificador());
    jsonLibro.put("autor", book.getIdAutor());
    jsonLibro.put("ISBN", book.getISBN());
    jsonLibro.put("disponible", book.getDisponible() ? "yes" : "no");
    return jsonLibro.toString(4);
  }

  private static String convertirAutorAJSON(Author author) {
    JSONObject jsonAutor = new JSONObject();
    jsonAutor.put("nombre", author.getNombre());
    jsonAutor.put("identificador", author.getIdentificador());
    jsonAutor.put("nacimiento", author.getNacimiento());
    jsonAutor.put("pais", author.getPaisId());
    return jsonAutor.toString(4);
  }

  private static String convertirListaAJSON(List<?> lista) {
    JSONArray jsonArray = new JSONArray();
    lista.forEach(object ->
        jsonArray.put(new JSONObject(convertirAJSON(object)))
    );

    return jsonArray.toString(4);
  }
}

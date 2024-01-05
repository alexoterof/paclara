import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/v1")
public class RestServlet extends HttpServlet {

  private final String SOURCE_FILE = "http://manolo.webs.uvigo.gal/SINT/libreria.xml";

  private final DataModel dataModel = new DataModel(SOURCE_FILE);

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    String pathInfo = request.getPathInfo();
    String filteredUrl = UrlParserHelper.parseUrl(pathInfo);
    String urlParam = UrlParserHelper.parseRestParam(pathInfo);

    switch (filteredUrl) {
      case "/libros":
        handleAllBooks(response);
        break;

      case "/libros/autor":
        if(urlParam == null)
          handleNotFound(response);
        else
          handleBooksByAuthorId(response, urlParam);
        break;

      case "/libro":
        if(urlParam == null)
          handleNotFound(response);
        else
          handleBookById(response, urlParam);
        break;

      case "/paises":
        handleAllCountries(response);
        break;

      case "/pais":
        if(urlParam == null)
          handleNotFound(response);
        else
          handleCountryById(response, urlParam);
        break;

      case "/autores":
        handleAllAuthors(response);
        break;

      case "/autor":
        if(urlParam == null)
          handleNotFound(response);
        else
          handleAuthorById(response, urlParam);
        break;

      case "/autores/pais":
        if(urlParam == null)
          handleNotFound(response);
        else
          handleAuthorsByCountryId(response, urlParam);
        break;

      default:
        handleNotFound(response);
    }
  }

  private void handleAllCountries(HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    List<Country> paises = dataModel.getCountries();
    Collections.sort(paises);
    out.println(Mapper.convertirAJSON(paises));
  }

  private void handleAllAuthors(HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    List<Author> autores = new ArrayList<>();
    dataModel.getCountries().forEach(pais ->
        autores.addAll(dataModel.getAuthors(pais.getIdentificador()))
    );
    Collections.sort(autores);
    out.println(Mapper.convertirAJSON(autores));
  }

  private void handleAllBooks(HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    List<Book> books = new ArrayList<>();
    dataModel.getCountries().forEach(pais ->
        dataModel.getAuthors(pais.getIdentificador()).forEach(autor ->
            books.addAll(dataModel.getBooks(autor.getIdentificador()))
        )
    );
    Collections.sort(books);
    out.println(Mapper.convertirAJSON(books));
  }

  private void handleCountryById(HttpServletResponse response, String paisId) throws IOException {
    PrintWriter out = response.getWriter();
    Country country = dataModel.getCountry(paisId);
    if (country == null)
      handleNotFound(response);
    else
      out.println(Mapper.convertirComoListaAJSON(country));
  }

  private void handleAuthorById(HttpServletResponse response, String autorIdParam) throws IOException {
    PrintWriter out = response.getWriter();
    Author author = dataModel.getAuthor(autorIdParam);
    if (author == null)
      handleNotFound(response);
    else
      out.println(Mapper.convertirComoListaAJSON(author));
  }

  private void handleBookById(HttpServletResponse response, String libroId) throws IOException {
    PrintWriter out = response.getWriter();
    Book book = dataModel.getBook(libroId);
    if (book == null)
      handleNotFound(response);
    else
      out.println(Mapper.convertirComoListaAJSON(book));
  }

  private void handleBooksByAuthorId(HttpServletResponse response, String autorId) throws IOException {
    PrintWriter out = response.getWriter();
    List<Book> librosPorAutor = dataModel.getBooks(autorId);
    Collections.sort(librosPorAutor);
    if(librosPorAutor.isEmpty())
      handleNotFound(response);
    else
      out.println(Mapper.convertirAJSON(librosPorAutor));
  }


  private void handleAuthorsByCountryId(HttpServletResponse response, String paisIdParam) throws IOException {
    PrintWriter out = response.getWriter();
    List<Author> autoresPorPais = dataModel.getAuthors(paisIdParam);
    Collections.sort(autoresPorPais);
    if (autoresPorPais.isEmpty())
      handleNotFound(response);
    else
      out.println(Mapper.convertirAJSON(autoresPorPais));
  }

  private void handleNotFound(HttpServletResponse response) throws IOException {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    response.getWriter().println("[]");
  }
}

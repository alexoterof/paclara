import java.util.List;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import javax.servlet.http.*;

public class SoapServlet extends HttpServlet {
    private final String SOURCE_FILE = "http://manolo.webs.uvigo.gal/SINT/libreria.xml";
    private final Renderer renderer = new Renderer();
    private final DataModel dataModel = new DataModel(SOURCE_FILE);

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            int fase;
            try{
                fase = Integer.parseInt(req.getParameter("fase"));
            } catch(NumberFormatException e) {
                fase = 0;
            }

            switch (fase){
                case 0:
                    doGetFase0(req,res);
                    break;
                case 1:
                    doGetFase1(res);
                    break;
                case 2:
                    doGetFase2(req,res);
                    break;
                case 3:
                    doGetFase3(req,res);
                    break;
                default:
                    throw new RuntimeException("Unimplemented step");
            }
        } catch (Exception ex) {
            System.out.println("Algo fue mal: " + ex);
        }
    }

    private void doGetFase0(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fichero = SOURCE_FILE.substring(SOURCE_FILE.lastIndexOf('/') + 1);;
        String clientIp = request.getRemoteAddr();
        String browserName = request.getHeader("User-Agent");
        String serverIp = getServerIp();
        renderer.rendererFase0(fichero,clientIp,browserName,serverIp,response);
    }

    private void doGetFase1(HttpServletResponse response) throws IOException {
        renderer.renderFase1(dataModel.getCountries(),response);
    }

    private void doGetFase2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idPais = request.getParameter("pais");
        Country country = dataModel.getCountry(idPais);
        List<Author> authors = dataModel.getAuthors(idPais);
        renderer.renderFase2(country.getNombre(), authors,response);
    }

    private void doGetFase3(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idAutor = request.getParameter("autor");
        Author author = dataModel.getAuthor(idAutor);
        Country country = dataModel.getCountry(author.getPaisId());
        List<Book> books = dataModel.getBooks(idAutor);
        renderer.renderFase3(country, author.getNombre(), books,response);

    }

    private static String getServerIp (){
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            return localHost.getHostAddress();

        } catch (UnknownHostException e) {
            System.out.println((Arrays.toString(e.getStackTrace())));
            return null;
        }
    }
}


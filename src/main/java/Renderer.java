import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Renderer {

    public void rendererFase0 (String fichero, String ipCliente, String navegador, String ipServidor, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Practica de su puta madre</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servicio de consulta de información</h1>");
        out.println("<div class=\"datos\">");
        out.println("<div>Fichero procesado: "+fichero + "</div>");
        out.println("<div>IP del cliente: "+ipCliente + "</div>");
        out.println("<div>Navegador del cliente: "+navegador + "</div>");
        out.println("<div>IP del servidor: "+ipServidor + "</div>");
        out.println("</div>");
        out.println("<input type=\"button\" class=\"anterior\" value=\"Avanzar\" onclick=\"location.href='P2Lib?fase=1'\">" );
        out.println("<hr>");
        out.println("<footer id =\"firma\"> Clara Mingyue Lopez Piñeiro");
        out.println("</body>");
        out.println("</html>");
    }
    public void renderFase1(List<Country> paises, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Practica de su puta madre</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servicio de consulta de información</h1>");
        out.println("<h2>Fase 1</h2>");
        out.println("<h3>Seleccione el país</h3>");
        out.println("<ol class=\"paises\" id=\"ipaises\">");
        paises.forEach(pais ->
                out.println("<li> <a href=\"P2Lib?fase=2&pais=" + pais.getIdentificador() + "\">" + pais.getNombre() + "</a></li>")
        );
        out.println("</ol>");
        out.println("<input type=\"button\" class=\"anterior\" value=\"Anterior\" onclick=\"location.href='P2Lib'\">" );
        out.println("<hr>");
        out.println("<footer id =\"firma\"> Clara Mingyue Lopez Piñeiro");
        out.println("</body>");
        out.println("</html>");
    }

    public void renderFase2(String pais, List<Author> autores, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Practica de su puta madre</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servicio de consulta de información</h1>");
        out.println("<h2>Fase 2 </h2>");
        out.println("<h3>Consultado información del país: "+pais+"</h3>");
        out.println("<h4>Seleccione el autor</h4>");
        out.println("<ol class=\"autores\" id=\"iautores\">");
        autores.forEach(autor ->
                out.println("<li><a href=\"P2Lib?fase=3&autor="+autor.getIdentificador()+"\">"+autor.getNombre()+"</a> Nacido en "+autor.getNacimiento()+"</li>")
        );
        out.println("</ol>");
        out.println("<input type=\"button\" class=\"anterior\" value=\"Anterior\" onclick=\"location.href='P2Lib?fase=1'\">" );
        out.println("<hr>");
        out.println("<footer id =\"firma\"> Clara Mingyue Lopez Piñeiro");
        out.println("</body>");
        out.println("</html>");
    }
    public void renderFase3(Country country, String autor, List<Book> books, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Practica de su puta madre</title>");
        out.println("<link rel='stylesheet' type='text/css' href='servlet.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servicio de consulta de información</h1>");
        out.println("<h2>Fase 3 </h2>");
        out.println("<h3>Consultado información del país: "+ country.getNombre()+"</h3>");
        out.println("<h4>Consultado información de autor: "+autor+"</h4>");
        out.println("<h5>Lista de libros: </h5>");
        out.println("<ol class=\"autores\" id=\"iautores\">");
        books.forEach(libro ->
                out.println("<li> <text class=\"dispo"+ libro.getDisponible()+ "\">" +  libro.getNombre() + "</text> ISBN: "+libro.getISBN()+"</li>")
        );
        out.println("</ol>");
        out.println("<input type=\"button\" class=\"anterior\" value=\"Anterior\" onclick=\"location.href='P2Lib?fase=2&pais="+ country.getIdentificador() +"'\">" );
        out.println("<hr>");
        out.println("<footer id =\"firma\"> Clara Mingyue Lopez Piñeiro");
        out.println("</body>");
        out.println("</html>");
    }
}

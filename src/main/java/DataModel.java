import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class DataModel {
    Parser parser;

    Document dom;

    public DataModel(String SOURCE_FILE_PATH) {
        parser = new Parser(SOURCE_FILE_PATH);
        dom = parser.parse();
    }

    public ArrayList<Country> getCountries() {
        ArrayList<Country> paises = new ArrayList<>();
        NodeList countryNodes = dom.getElementsByTagName("pais");
        for (int i=0; i < countryNodes.getLength(); i++){
            Element countryElement = (Element) countryNodes.item(i);
            String identifier = countryElement.getAttribute("identificador");
            String countryName = countryElement.getTextContent().trim();
            paises.add(new Country(identifier,countryName));
        }
        return paises;
    }

    public Country getCountry(String countryId) {
        NodeList countryNodes = dom.getElementsByTagName("pais");
        for (int i=0; i < countryNodes.getLength(); i++){
            Element countryElement = (Element) countryNodes.item(i);
            String identifier = countryElement.getAttribute("identificador");
            if(!identifier.equals(countryId))
                continue;
            String countryName = countryElement.getTextContent().trim();
            return new Country(identifier,countryName);
        }
        return null;
    }

    public List <Author> getAuthors(String countryId) {
        ArrayList<Author> autores = new ArrayList<>();
        NodeList authorsNodes = dom.getElementsByTagName("autor");
        for (int i=0; i < authorsNodes.getLength(); i++){
            Element authorElement = (Element) authorsNodes.item(i);
            String countryIdentifier = authorElement.getAttribute("pais");
            if(!countryIdentifier.equals(countryId))
                continue;
            String identifier = authorElement.getAttribute("identificador");
            String birhtYear = authorElement.getAttribute("nacimiento");
            String authorName = authorElement.getTextContent().trim();
            autores.add(new Author(identifier,birhtYear,authorName, countryIdentifier));
        }
        return autores;
    }

    public Author getAuthor(String authorId) {
        NodeList authorsNodes = dom.getElementsByTagName("autor");
        for (int i=0; i < authorsNodes.getLength(); i++){
            Element authorElement = (Element) authorsNodes.item(i);
            String identifier = authorElement.getAttribute("identificador");
            if(!identifier.equals(authorId))
                continue;

            String countryIdentifier = authorElement.getAttribute("pais");
            String birthYear = authorElement.getAttribute("nacimiento");
            String authorName = authorElement.getTextContent().trim();
            return new Author(identifier,birthYear,authorName, countryIdentifier);
        }
        return null;
    }

    public List<Book> getBooks(String authorId) {
        ArrayList<Book> books = new ArrayList<>();
        NodeList booksNodes = dom.getElementsByTagName("libro");
        for (int i=0; i < booksNodes.getLength(); i++){
            Element bookElement = (Element) booksNodes.item(i);
            String authorIdentifier = bookElement.getAttribute("autor");
            if(!authorIdentifier.equals(authorId))
                continue;

            String identifier = bookElement.getAttribute("identificador");
            String ISBN = bookElement.getAttribute("ISBN");
            Boolean isDisponible = bookElement.getAttribute("disponible").equals("yes");
            String bookName = bookElement.getTextContent().trim();
            books.add(new Book(identifier,ISBN,isDisponible,bookName, authorIdentifier));
        }
        return books;
    }

    public Book getBook(String bookId) {
        NodeList booksNodes = dom.getElementsByTagName("libro");
        for (int i=0; i < booksNodes.getLength(); i++){
            Element bookElement = (Element) booksNodes.item(i);
            String identifier = bookElement.getAttribute("identificador");
            if(!identifier.equals(bookId))
                continue;

            String ISBN = bookElement.getAttribute("ISBN");
            String authorIdentifier = bookElement.getAttribute("autor");
            Boolean isDisponible = bookElement.getAttribute("disponible").equals("yes");
            String bookName = bookElement.getTextContent().trim();
            return new Book(identifier,ISBN,isDisponible,bookName, authorIdentifier);
        }
        return null;
    }
}


public class Book implements Comparable<Book>{

    private final String identificador;
    private final Boolean disponible;
    private final String nombre;
    private final String ISBN;
    private final String idAutor;

    public Book(String identificador, String ISBN, Boolean disponible, String nombre, String idAutor) {
        this.identificador = identificador;
        this.ISBN = ISBN;
        this.disponible = disponible;
        this.nombre = nombre;
        this.idAutor = idAutor;
    }

    public String getIdentificador() {
        return identificador;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public String getNombre() {
        return nombre;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getIdAutor(){
        return idAutor;
    }

    @Override
    public int compareTo(Book o) {
        return this.identificador.compareTo(o.getIdentificador());
    }
}

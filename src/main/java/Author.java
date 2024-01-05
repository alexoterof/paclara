public class Author implements Comparable<Author> {

    private final String identificador;
    private final String nacimiento;
    private final String nombre;
    private final String paisId;


    public Author(String identificador, String nacimiento, String nombre, String paisId) {
        this.identificador = identificador;
        this.nacimiento = nacimiento;
        this.nombre = nombre;
        this.paisId = paisId;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPaisId() {
        return paisId;
    }

    @Override
    public int compareTo(Author o) {
        return this.identificador.compareTo(o.getIdentificador());
    }
}

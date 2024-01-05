public class Country implements Comparable<Country> {

    private final String identificador;
    private final String nombre;

    public Country(String identificador, String nombre) {
        this.identificador = identificador;
        this.nombre = nombre;
    }

    @Override
    public int compareTo(Country otroCountry) {
        return this.identificador.compareTo(otroCountry.identificador);
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getNombre() {
        return nombre;
    }
}

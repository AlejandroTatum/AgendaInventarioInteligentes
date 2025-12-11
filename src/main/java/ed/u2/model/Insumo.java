package ed.u2.model;

public class Insumo implements Comparable<Insumo> {
    private String id;
    private String nombre;
    private int stock;

    public Insumo(String id, String nombre, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
    }

    public int getStock() { return stock; }

    @Override
    public int compareTo(Insumo other) {
        // Orden ascendente por stock (necesario para la búsqueda binaria posterior)
        return Integer.compare(this.stock, other.stock);
    }

    @Override
    public String toString() {
        return String.format("%s | %-30s | Stock: %d", id, nombre, stock);
    }
}
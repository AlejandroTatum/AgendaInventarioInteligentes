package ed.u2.model;

public class Cita implements Comparable<Cita> {
    private String id;
    private String apellido;
    private String fechaHora; // Formato ISO 8601 permite ordenación alfabética directa

    public Cita(String id, String apellido, String fechaHora) {
        this.id = id;
        this.apellido = apellido;
        this.fechaHora = fechaHora;
    }

    public String getFechaHora() { return fechaHora; }

    @Override
    public int compareTo(Cita other) {
        return this.fechaHora.compareTo(other.fechaHora);
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s", id, apellido, fechaHora);
    }
}
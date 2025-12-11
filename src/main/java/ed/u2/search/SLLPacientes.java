package ed.u2.search;

import ed.u2.model.Paciente;
import java.util.ArrayList;
import java.util.List;

public class SLLPacientes {

    private static class Nodo {
        Paciente data;
        Nodo next;
        Nodo(Paciente data) { this.data = data; this.next = null; }
    }

    private Nodo head;
    private int size;

    public void add(Paciente p) {
        Nodo newNode = new Nodo(p);
        if (head == null) {
            head = newNode;
        } else {
            Nodo current = head;
            while (current.next != null) current = current.next;
            current.next = newNode;
        }
        size++;
    }

    // RÚBRICA: Búsqueda secuencial primera por apellido
    public Paciente findFirstByApellido(String apellido) {
        Nodo current = head;
        while (current != null) {
            if (current.data.getApellido().equalsIgnoreCase(apellido)) return current.data;
            current = current.next;
        }
        return null;
    }

    // RÚBRICA: Búsqueda secuencial última por apellido
    public Paciente findLastByApellido(String apellido) {
        Paciente found = null;
        Nodo current = head;
        while (current != null) {
            if (current.data.getApellido().equalsIgnoreCase(apellido)) {
                found = current.data; // Actualizamos pero seguimos buscando
            }
            current = current.next;
        }
        return found;
    }

    // RÚBRICA: FindAll(prioridad == X)
    public List<Paciente> findAllByPrioridad(int prioridad) {
        List<Paciente> result = new ArrayList<>();
        Nodo current = head;
        while (current != null) {
            if (current.data.getPrioridad() == prioridad) {
                result.add(current.data);
            }
            current = current.next;
        }
        return result;
    }

    public int getSize() { return size; }
}
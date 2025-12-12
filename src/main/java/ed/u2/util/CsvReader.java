package ed.u2.util;

import ed.u2.model.Cita;
import ed.u2.model.Insumo;
import ed.u2.model.Paciente;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public static Cita[] loadCitas(String path) {
        List<Cita> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                if(line.trim().isEmpty()) continue;
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    list.add(new Cita(parts[0].trim(), parts[1].trim(), parts[2].trim()));
                }
            }
        } catch (IOException e) { System.err.println("Error leyendo Citas: " + e.getMessage()); }
        return list.toArray(new Cita[0]);
    }

    public static Insumo[] loadInventario(String path) {
        List<Insumo> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                if(line.trim().isEmpty()) continue;
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    try {
                        int stock = Integer.parseInt(parts[2].trim());
                        list.add(new Insumo(parts[0].trim(), parts[1].trim(), stock));
                    } catch (NumberFormatException e) {
                        System.err.println("Dato numérico inválido en inventario: " + parts[2]);
                    }
                }
            }
        } catch (Exception e) { System.err.println("Error leyendo Inventario: " + e.getMessage()); }
        return list.toArray(new Insumo[0]);
    }

    public static List<Paciente> loadPacientesList(String path) {
        List<Paciente> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                if(line.trim().isEmpty()) continue;
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    try {
                        int prio = Integer.parseInt(parts[2].trim());
                        list.add(new Paciente(parts[0].trim(), parts[1].trim(), prio));
                    } catch (NumberFormatException e) {
                        System.err.println("Prioridad inválida en paciente: " + parts[2]);
                    }
                }
            }
        } catch (Exception e) { System.err.println("Error leyendo Pacientes: " + e.getMessage()); }
        return list;
    }
}
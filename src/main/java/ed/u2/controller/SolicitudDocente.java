package ed.u2.controller;

public record SolicitudDocente(HospitalController controller) {


    public void ejecutar() {

        // --- PRUEBAS DE PACIENTES ---
        controller.procesarBusquedaPaciente("2");
        controller.procesarBusquedaPaciente("Vega");

        // --- PRUEBAS DE STOCK ---
        controller.procesarBusquedaStock(450);
        controller.procesarBusquedaStock(100);

        // Agregar más cuando el profesor nos pida
    }
}
package ed.u2.controller;

public record SolicitudDocente(HospitalController controller) {

    // Recibimos el controlador principal para darle órdenes

    public void ejecutar() {
        // Aquí solo "envías los datos" como querías:

        // --- PRUEBAS DE PACIENTES ---
        controller.procesarBusquedaPaciente("2");
        controller.procesarBusquedaPaciente("Vega");

        // --- PRUEBAS DE STOCK ---
        controller.procesarBusquedaStock(450);
        controller.procesarBusquedaStock(100);

        // Si el docente pide algo nuevo, solo agregas una línea aquí:
        // controller.procesarBusquedaPaciente("NuevoApellido");
    }
}
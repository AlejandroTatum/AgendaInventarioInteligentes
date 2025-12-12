package ed.u2;

import ed.u2.controller.HospitalController;
import ed.u2.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        HospitalController controller = new HospitalController(view);

        // Arrancar la aplicación
        controller.iniciar();
    }
}
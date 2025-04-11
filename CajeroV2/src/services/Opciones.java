package services;

import java.util.Scanner;

import controllers.CuentaController;
import models.Cuenta;
import models.Usuario;
import views.MenuView;

public class Opciones {
    private Scanner scanner = new Scanner(System.in);
    private Cuenta cuenta;
    private Usuario usuario;
    private CuentaController cajero;

    public void iniciar() {
        cuenta = new Cuenta("123456", 1000000.0, "1234", 0);
        usuario = new Usuario(cuenta, "Profesor Daniel");
        cajero = new CuentaController(cuenta);

        if (!autenticar()) return;

        int opcion;
        do {
            MenuView.mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpia buffer
            manejarOpcion(opcion);
        } while (opcion != 6);

        scanner.close();
    }

    private boolean autenticar() {
        while (!cajero.cuentaBloqueada()) {
            System.out.print("Ingrese su PIN: ");
            String pin = scanner.nextLine();

            if (cajero.validarPIN(pin)) {
                System.out.println("\n¡Bienvenido, " + usuario.getNombre() + "!");
                return true;
            } else {
                System.out.println("PIN incorrecto, intente nuevamente");
            }

            if (cajero.cuentaBloqueada()) {
                System.out.println("Demasiados intentos, cuenta bloqueada");
                return false;
            }
        }
        return false;
    }

    private void manejarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> consultarSaldo();
            case 2 -> retirarDinero();
            case 3 -> depositarDinero();
            case 4 -> menuBolsillo();
            case 5 -> System.out.println("¡Hasta la próxima, vuelve pronto!");
            default -> System.out.println("Error, opción incorrecta");
        }
    }

    private void consultarSaldo() {
        System.out.println("Tu saldo es: $" + cajero.consultarSaldo());
    }

    private void retirarDinero() {
        System.out.print("¿Cuánto quieres sacar?: ");
        double retiro = scanner.nextDouble();

        if (cajero.retirar(retiro)) {
            System.out.println("Retiro exitoso. Su saldo es: $" + cajero.consultarSaldo());
        } else {
            System.out.println("No se pudo realizar el retiro");
        }
    }

    private void depositarDinero() {
        System.out.print("¿Cuánto desea meter?: ");
        double deposito = scanner.nextDouble();

        if (cajero.depositar(deposito)) {
            System.out.println("Depósito exitoso. Su saldo es: $" + cajero.consultarSaldo());
        } else {
            System.out.println("No se pudo realizar el deposito");
        }
    }

    private void menuBolsillo() {
        int opcionBolsillo;
        do {
            System.out.println("\nBOLSILLO");
            System.out.println("1. Consultar estado del bolsillo");
            System.out.println("2. Guardar dinero");
            System.out.println("3. Sacar dinero");
            System.out.println("4. Volver al menú principal");
            System.out.print("Elige una opción");
            opcionBolsillo = scanner.nextInt();

            switch (opcionBolsillo) {
                case 1:
                    System.out.println("Actualmente tiene $ " + cuenta.getBolsillo() + " en su bolsillo");
                    break;

                case 2:
                    System.out.print("¿Cuánto quiere depositar en su bolsillo?: ");
                    double montoGuardar = scanner.nextDouble();

                    if (montoGuardar <= 0) {
                        System.out.println("Monto incorrecto, debe ser mayor a cero");
                    } else if (montoGuardar > cajero.consultarSaldo()) {
                        System.out.println("Monto incorrecto, no puede superar el saldo");
                    } else {
                        cuenta.guardarEnBolsillo(montoGuardar);
                        System.out.println("Dinero depositado con éxito");
                    }
                    break;
                    
                    case 3:
                    System.out.print("¿Cuánto desea sacar de su bolsillo?: ");
                    double montoSacar = scanner.nextDouble();
                    
                    if (montoSacar <= 0) {
                        System.out.println("Monto incorrecto, debe ser mayor a cero");
                    } else if (montoSacar > cuenta.getBolsillo()) {
                        System.out.println("Monto incorrecto, no puede superar el saldo guardado en el bolsillo");
                    } else {
                        cuenta.sacarDelBolsillo(montoSacar);
                        System.out.println("Dinero sacado con exito");
                    }
                    break;

                case 4:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Esa opción no existe");
                    break;
            }
        } while (opcionBolsillo != 4);
    }
}

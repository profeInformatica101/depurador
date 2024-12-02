
package com.endes.cuentacorriente;

/**
 *
 * @author endes
 */
public class TestCuentaCorriente {

    public static void main(String[] args) {
        // Llamar a cada prueba estática
        probarLimiteDescubierto();
        probarConstructorYNombreBanco();
        probarTransferenciaEntreCuentas();
    }

    // Prueba 1: Verificar límite de descubierto
    public static void probarLimiteDescubierto() {
        System.out.println("== Prueba: Límite de Descubierto ==");

        // Crear una cuenta con límite de descubierto predeterminado (-50)
        CuentaCorriente cuenta = new CuentaCorriente("Ana", "García", "98765432B");

        // Ingresar dinero
        cuenta.ingresarDinero(100);

        // Intentar retirar dentro del límite
        boolean resultado1 = cuenta.sacarDinero(120);
        System.out.println("Retiro de 120 exitoso: " + resultado1); // true

        // Intentar retirar fuera del límite
        boolean resultado2 = cuenta.sacarDinero(50);
        System.out.println("Retiro de 50 exitoso: " + resultado2); // false

        // Mostrar información actual
        cuenta.mostrarInformacion();

        System.out.println("== Fin de Prueba: Límite de Descubierto ==\n");
    }

    // Prueba 2: Constructor con saldo inicial y cambio del nombre del banco
    public static void probarConstructorYNombreBanco() {
        System.out.println("== Prueba: Constructor y Nombre del Banco ==");

        // Crear una cuenta con saldo inicial
        CuentaCorriente cuenta1 = new CuentaCorriente(200);
        CuentaCorriente cuenta2 = new CuentaCorriente("Carlos", "López", "65432109C");

        // Mostrar información de ambas cuentas
        System.out.println("Información inicial de cuentas:");
        cuenta1.mostrarInformacion();
        cuenta2.mostrarInformacion();

        // Cambiar el nombre del banco
        CuentaCorriente.setNombreBanco("Banco Internacional");

        // Mostrar información después del cambio
        System.out.println("\nInformación después de cambiar el nombre del banco:");
        cuenta1.mostrarInformacion();
        cuenta2.mostrarInformacion();

        System.out.println("== Fin de Prueba: Constructor y Nombre del Banco ==\n");
    }

    // Prueba 3: Transferencia entre cuentas
    public static void probarTransferenciaEntreCuentas() {
        System.out.println("== Prueba: Transferencia Entre Cuentas ==");

        // Crear dos cuentas
        CuentaCorriente cuenta1 = new CuentaCorriente("Luis", "Martínez", "11111111D");
        CuentaCorriente cuenta2 = new CuentaCorriente("Sofía", "Hernández", "22222222E");

        // Ingresar dinero en la primera cuenta
        cuenta1.ingresarDinero(500);

        // Mostrar información inicial
        System.out.println("Información inicial de las cuentas:");
        cuenta1.mostrarInformacion();
        cuenta2.mostrarInformacion();

        // Transferir dinero de cuenta1 a cuenta2
        cuenta1.transferirDinero(cuenta2, 200);

        // Mostrar información después de la transferencia
        System.out.println("\nInformación después de la transferencia:");
        cuenta1.mostrarInformacion();
        cuenta2.mostrarInformacion();

        System.out.println("== Fin de Prueba: Transferencia Entre Cuentas ==\n");
    }
}

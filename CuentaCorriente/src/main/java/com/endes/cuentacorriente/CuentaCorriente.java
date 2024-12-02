package com.endes.cuentacorriente;
/**
 * Clase que representa una cuenta corriente en un banco.
 * Cada cuenta tiene un titular, un saldo, un límite de descubierto
 * y pertenece a un banco. La clase incluye operaciones típicas como
 * ingresar dinero, retirar dinero y transferir fondos entre cuentas.
 * 
 * @author endes
 */
public class CuentaCorriente {

    /** Nombre del banco (único para todas las cuentas). */
    private static String nombreBanco = "Banco Global";

    /** Saldo actual de la cuenta. */
    private double saldo;

    /** Límite de descubierto permitido en la cuenta (negativo o cero). */
    private final double limiteDescubierto;

    /** Nombre del titular de la cuenta. */
    private String nombre;

    /** Apellido del titular de la cuenta. */
    private String apellido;

    /** DNI del titular de la cuenta (acceso protegido). */
    protected String dni;

    /**
     * Constructor principal. Crea una cuenta corriente con nombre, apellido y DNI,
     * con saldo inicial 0 y límite de descubierto predeterminado (-50).
     * 
     * @param nombre  Nombre del titular de la cuenta.
     * @param apellido Apellido del titular de la cuenta.
     * @param dni      DNI del titular de la cuenta.
     */
    public CuentaCorriente(String nombre, String apellido, String dni) {
        this(0, -50, dni); // Llama al constructor más completo
        this.nombre = nombre;
        this.apellido = apellido;
    }

    /**
     * Constructor que permite crear una cuenta con un saldo inicial y sin datos del titular.
     * 
     * @param saldo Saldo inicial de la cuenta.
     */
    public CuentaCorriente(double saldo) {
        this(saldo, 0, null); // Llama al constructor más completo
    }

    /**
     * Constructor completo. Permite definir el saldo inicial, el límite de descubierto y el DNI del titular.
     * 
     * @param saldo            Saldo inicial de la cuenta.
     * @param limiteDescubierto Límite de descubierto permitido (debe ser negativo o cero).
     * @param dni              DNI del titular de la cuenta.
     * @throws IllegalArgumentException Si el límite de descubierto no es negativo o si el saldo es menor al límite.
     */
    public CuentaCorriente(double saldo, double limiteDescubierto, String dni) {
        if (limiteDescubierto > 0) {
            throw new IllegalArgumentException("El límite de descubierto debe ser negativo o cero.");
        }
        if (saldo < limiteDescubierto) {
            throw new IllegalArgumentException("El saldo inicial no puede ser menor que el límite de descubierto.");
        }
        this.saldo = saldo;
        this.limiteDescubierto = limiteDescubierto;
        this.dni = dni;
    }

    /**
     * Retira una cantidad de dinero de la cuenta, si no excede el límite de descubierto.
     * 
     * @param cantidad Cantidad a retirar.
     * @return {@code true} si la operación fue exitosa; {@code false} si no hay suficiente saldo.
     */
    public boolean sacarDinero(double cantidad) {
        if (this.saldo - cantidad >= this.limiteDescubierto) {
            this.saldo -= cantidad;
            return true;
        } else {
            System.out.println("Operación denegada: No se puede superar el límite de descubierto.");
            return false;
        }
    }

    /**
     * Ingresa una cantidad de dinero en la cuenta.
     * 
     * @param cantidad Cantidad a ingresar.
     */
    public void ingresarDinero(double cantidad) {
        this.saldo += cantidad;
    }

    /**
     * Transfiere una cantidad de dinero de esta cuenta a otra cuenta destino.
     * 
     * @param cuentaDestino La cuenta que recibirá el dinero.
     * @param cantidad      Cantidad a transferir.
     */
    public void transferirDinero(CuentaCorriente cuentaDestino, double cantidad) {
        if (this.sacarDinero(cantidad)) {
            cuentaDestino.ingresarDinero(cantidad);
            System.out.println("Transferencia de " + cantidad + " realizada con éxito de " +
                    this.getNombreCompleto() + " a " + cuentaDestino.getNombreCompleto());
        } else {
            System.out.println("Transferencia fallida: Fondos insuficientes en " + this.getNombreCompleto());
        }
    }

    /**
     * Muestra la información detallada de la cuenta corriente.
     */
    public void mostrarInformacion() {
        System.out.println("Titular: " + getNombreCompleto());
        System.out.println("DNI: " + this.dni);
        System.out.println("Saldo: " + this.saldo);
        System.out.println("Límite de descubierto: " + this.limiteDescubierto);
        System.out.println("Banco: " + nombreBanco);
    }

    /**
     * Obtiene el nombre completo del titular de la cuenta (nombre + apellido).
     * 
     * @return Nombre completo del titular.
     */
    public String getNombreCompleto() {
        return this.nombre + " " + this.apellido;
    }

    /**
     * Cambia el nombre del banco asociado a todas las cuentas.
     * 
     * @param nuevoNombreBanco El nuevo nombre del banco.
     */
    public static void setNombreBanco(String nuevoNombreBanco) {
        nombreBanco = nuevoNombreBanco;
    }

    /**
     * Obtiene el nombre del banco asociado a todas las cuentas.
     * 
     * @return El nombre del banco.
     */
    public static String getNombreBanco() {
        return nombreBanco;
    }
}

package ejercicio7;

import java.time.LocalDate;

public class Cliente implements Comparable<Cliente> {
    private String dni;
    private String nombre;
    private LocalDate fechaNacimiento;
    private double saldo;

    public Cliente(String dni, String nombre, LocalDate fechaNacimiento, double saldo) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.saldo = saldo;
    }

    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    public double getSaldo() { return saldo; }

    // Calcula la edad basándose en la fecha actual
    public int getEdad() {
        LocalDate hoy = LocalDate.now();
        int edad = hoy.getYear() - fechaNacimiento.getYear();
        // Ajuste fino: si aún no ha llegado su mes/día, tiene un año menos
        if (hoy.getDayOfYear() < fechaNacimiento.getDayOfYear()) {
            edad--;
        }
        return edad;
    }

    // Método para generar la línea que se guardará en el fichero
    public String toFicheroFormato() {
        return dni + ";" + nombre + ";" + fechaNacimiento.toString() + ";" + saldo;
    }

    @Override
    public int compareTo(Cliente otro) {
        return this.dni.compareTo(otro.getDni());
    }
}
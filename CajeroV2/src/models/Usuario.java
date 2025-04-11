package models;

public class Usuario {
    private String nombre;
    private Cuenta cuenta;

    public Usuario(Cuenta cuenta, String nombre) {
        this.cuenta = cuenta;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    
}

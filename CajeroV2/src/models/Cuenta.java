package models;

public class Cuenta {
    private String numeroCuenta;
    private Double saldo;
    private String pin;
    private int intentosFallidos;
    private double bolsillo = 0.0;
    
    public Cuenta(String numeroCuenta, Double saldo, String pin, int intentosFallidos) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.pin = pin;
        this.intentosFallidos = intentosFallidos;
    }
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
    public Double getSaldo() {
        return saldo;
    }
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    public String getPin() {
        return pin;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }
    public int getIntentosFallidos() {
        return intentosFallidos;
    }
    public void setIntentosFallidos(int intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }
    public double getBolsillo() {
        return bolsillo;
    }
    public void guardarEnBolsillo(double monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
            bolsillo += monto;
        }
    }
    public boolean sacarDelBolsillo(double monto) {
        if (monto > 0 && monto <= bolsillo) {
            bolsillo -= monto;
            saldo += monto;
            return true;
        }
        return false;
    }    
}

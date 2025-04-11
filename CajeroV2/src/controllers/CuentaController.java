package controllers;

import models.Cuenta;

public class CuentaController {
    private Cuenta cuenta;

    public CuentaController(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public double consultarSaldo() {
        return cuenta.getSaldo();
    }

    public boolean retirar(double monto) {
        if (monto > 0 && monto <= cuenta.getSaldo()) {
            cuenta.setSaldo(cuenta.getSaldo() - monto);
            return true;
        }
        return false;
    }

    public boolean depositar(double monto) {
        if (monto > 0) {
            cuenta.setSaldo(cuenta.getSaldo() + monto);
            return true;
        }
        return false;
    }

    public boolean validarPIN(String pinIngresado) {
        if (cuenta.getPin().equals(pinIngresado)) {
            cuenta.setIntentosFallidos(0);
            return true;
        } else {
            cuenta.setIntentosFallidos(cuenta.getIntentosFallidos() + 1);
            return false;
        }
    }

    public boolean cuentaBloqueada() {
        return cuenta.getIntentosFallidos() >= 3;
    }
}

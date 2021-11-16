/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Leo
 */
public class Conta {
    
    private int codigoConta;
    private String numeroConta;
    private String agenciaConta;
    private double saldoConta;
    
        public Conta() {
        this.codigoConta = 0;
    }
        
        public Conta(String numeroConta, String agenciaConta, double saldoConta) {
        this.numeroConta = numeroConta;
        this.agenciaConta = agenciaConta;
        this.saldoConta = saldoConta;
    }

    public int getCodigoConta() {
        return codigoConta;
    }

    public void setCodigoConta(int codigoConta) {
        this.codigoConta = codigoConta;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getAgenciaConta() {
        return agenciaConta;
    }

    public void setAgenciaConta(String agenciaConta) {
        this.agenciaConta = agenciaConta;
    }

    public double getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(double saldoConta) {
        this.saldoConta = saldoConta;
    }
    
        public String[] toArray() {
        String[] array = new String[4];
        array[0] = String.valueOf(getCodigoConta());
        array[1] = getNumeroConta();
        array[2] = getAgenciaConta();
        array[3] = String.valueOf(getSaldoConta());
        
        return array;
    }
}

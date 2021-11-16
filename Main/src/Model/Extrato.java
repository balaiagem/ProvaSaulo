/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Utilitario.Funcoes;
import java.util.Calendar;

/**
 *
 * @author Leo
 */
public class Extrato {
    
    private int codigoExtrato;
    private String descricaoExtrato;
    private Calendar dataExtrato;
    private String tipoExtrato;
    private double valorExtrato;
    private int extratoCodigoConta;

        public Extrato() {
        this.codigoExtrato = 0;
        this.dataExtrato = Calendar.getInstance();
    }

    public Extrato(String descricaoExtrato, Calendar dataExtrato, String tipoExtrato, double valorExtrato, int extratoCodigoConta) {
        this.descricaoExtrato = descricaoExtrato;
        this.dataExtrato = dataExtrato;
        this.tipoExtrato = tipoExtrato;
        this.valorExtrato = valorExtrato;
        this.extratoCodigoConta = extratoCodigoConta;
    }
    
    public int getCodigoExtrato() {
        return codigoExtrato;
    }

    public void setCodigoExtrato(int codigoExtrato) {
        this.codigoExtrato = codigoExtrato;
    }

    public String getDescricaoExtrato() {
        return descricaoExtrato;
    }

    public void setDescricaoExtrato(String descricaoExtrato) {
        this.descricaoExtrato = descricaoExtrato;
    }

    public Calendar getDataExtrato() {
        return dataExtrato;
    }

    public void setDataExtrato(Calendar dataExtrato) {
        this.dataExtrato = dataExtrato;
    }

    public String getTipoExtrato() {
        return tipoExtrato;
    }

    public void setTipoExtrato(String tipoExtrato) {
        this.tipoExtrato = tipoExtrato;
    }

    public double getValorExtrato() {
        return valorExtrato;
    }

    public void setValorExtrato(double valorExtrato) {
        this.valorExtrato = valorExtrato;
    }

    public int getExtratoCodigoConta() {
        return extratoCodigoConta;
    }

    public void setExtratoCodigoConta(int extratoCodigoConta) {
        this.extratoCodigoConta = extratoCodigoConta;
    }
    
        public String[] toArray() {
        String[] array = new String[6];
        array[0] = String.valueOf(getCodigoExtrato());
        array[1] = getDescricaoExtrato();
        array[2] = Funcoes.dateToString(getDataExtrato());
        array[3] = getTipoExtrato();
        array[4] = String.valueOf(getValorExtrato());
        array[5] = String.valueOf(getExtratoCodigoConta());

        return array;
    }
}

package Controller;

import DAO.DAOextrato;
import Utilitario.Funcoes;
import java.util.ArrayList;
import Model.Extrato;

public class ControlerExtrato {

    public static void inserir(String[] dados) {
        
        DAOextrato dao = new DAOextrato();

        try {   
            Extrato obj = new Extrato();
            obj.setCodigoExtrato(Integer.parseInt(dados[0]));
            obj.setDescricaoExtrato(dados[1]);
            obj.setDataExtrato(Funcoes.stringToDate(dados[2]));
            obj.setTipoExtrato(dados[3]);
            obj.setValorExtrato(Double.parseDouble(dados[4]));
            obj.setExtratoCodigoConta(Integer.parseInt(dados[5]));   

            dao.insert(obj);

        } catch (Exception f) {
            System.out.println("Erro ao gravar objeto na classe controle: " + f.getMessage());
        }
    }
    
    public static boolean excluir(int codigoExtrato){
        DAOextrato dao = new DAOextrato();
        return dao.delete(codigoExtrato);
    }

    public static String[] recuperar(int codigoExtrato){
        DAOextrato dao = new DAOextrato();
        Extrato objExtrato = dao.recuperar(codigoExtrato);
        return objExtrato.toArray();
    }
    
    public static String[][] recuperarExtratoConta(int codigoConta){
        DAOextrato dao = new DAOextrato();
        ArrayList<Extrato> lista = dao.recuperarExtratoConta(codigoConta);

        String[][] matrizReturn = null;
        matrizReturn = new String[lista.size()][6];

        for (int i = 0; i < lista.size(); i++) {
            matrizReturn[i] = lista.get(i).toArray();
        }
        
        return matrizReturn;
    }
}

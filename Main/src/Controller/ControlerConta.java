package Controller;

import DAO.DAOconta;
import java.util.ArrayList;
import Model.Conta;

public class ControlerConta {

    public static void inserir(String[] dados) {
        DAOconta dao = new DAOconta();

        try {
            Conta obj = new Conta();
            obj.setCodigoConta(Integer.parseInt(dados[0]));
            obj.setNumeroConta(dados[1]);
            obj.setAgenciaConta(dados[2]);
            obj.setSaldoConta(Double.parseDouble(dados[3]));            

            if ("0".equals(dados[0])){
                dao.insert(obj);
            } else {
                dao.update(obj);
            }
             
        } catch (Exception f) {
            System.out.println("Erro ao gravar objeto na classe controle: " + f.getMessage());
        }
    }

    public static boolean excluir(int codigoConta) {
        DAOconta dao = new DAOconta();
        return dao.delete(codigoConta);
    }

    public static String[] recuperar(int codigoConta) {
        DAOconta dao = new DAOconta();
        Conta objContato = dao.recuperar(codigoConta);
        return objContato.toArray();
    }
    
    public static String[][] recuperarTodos(){
        DAOconta dao = new DAOconta();
        ArrayList<Conta> lista = dao.recuperarTodos();
        String[][] matrizReturn = null;
        matrizReturn = new String[lista.size()][4];

        for (int i = 0; i < lista.size(); i++) {
            matrizReturn[i] = lista.get(i).toArray();
        }
        return matrizReturn;
    }
}

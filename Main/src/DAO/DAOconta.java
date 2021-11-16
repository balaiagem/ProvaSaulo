package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Conta;

public class DAOconta {
    
    private Connection conexao;
    
    public DAOconta(){
        conexao = Fabrica.getConexaoSINGLETON();
    }
    
    public DAOconta(Connection pConexao){
        conexao = pConexao;
    }

    public void insert(Conta obj) {
     
        try {
            PreparedStatement sql = conexao.prepareStatement( 
                            "INSERT INTO "
                                + "conta (codigo_conta, numero_conta , agencia_conta, saldo_conta)"
                                + "VALUES (?,?,?,?)");
            sql.setInt(1, obj.getCodigoConta());
            sql.setString(2, obj.getNumeroConta());
            sql.setString(3, obj.getAgenciaConta());
            sql.setDouble(4, obj.getSaldoConta());

            sql.execute();
            System.out.println("Inserido \n");
        } catch (SQLException ex) {
            System.out.println("Não inserido no DAO - erro: \n" + ex.getMessage());
        }
    }
    
    public void update(Conta obj) {

       try {
            PreparedStatement sql = conexao.prepareStatement(
                    "UPDATE conta SET numero_conta = ?, agencia_conta = ?, saldo_conta = ? WHERE codigo_conta = ?");
            
            sql.setString(1, obj.getNumeroConta());
            sql.setString(2, obj.getAgenciaConta());
            sql.setDouble(3, obj.getSaldoConta());
            sql.setInt(4, obj.getCodigoConta());
            
            sql.executeUpdate();
            sql.close();

            System.out.println("Atualizado \n");
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar conta - DAO \n" + ex.getMessage());
        }
    }
    
    public boolean delete(int codigoConta) {
       
        try {
            PreparedStatement ST = (PreparedStatement) conexao.prepareStatement("DELETE FROM conta WHERE codigo_conta = ?");
            ST.setInt(1, codigoConta);
            ST.execute();

            System.out.println("Excluido");
            return true;
        } catch (SQLException err) {
            System.err.println("Erro ao excluir - DAO: " + err.getMessage());
        }
        return false;
    }
    
    public Conta recuperar(int codigoConta) {
        Conta obj = new Conta();
        
        try {
            PreparedStatement ST = conexao.prepareStatement("SELECT * FROM conta WHERE codigo_conta = ?");
            ST.setInt(1, codigoConta);
            ResultSet objResultSet = ST.executeQuery();
            objResultSet.next();
            
            obj.setCodigoConta(objResultSet.getInt("codigo_conta"));
            obj.setNumeroConta(objResultSet.getString("numero_conta"));
            obj.setAgenciaConta(objResultSet.getString("agencia_conta"));
            obj.setSaldoConta(objResultSet.getDouble("saldo_conta"));

        } catch (Exception e) {
            System.err.println("Erro ao recuperar - DAO" + e.getMessage());
        }
        return obj;
    }
    
    public  ArrayList<Conta> recuperarTodos(){
        Conta obj = null;
        ArrayList<Conta> lista = new ArrayList<>();

        try {
            PreparedStatement ST = (PreparedStatement) conexao.prepareStatement(
                    "SELECT codigo_conta, numero_conta, agencia_conta, saldo_conta FROM conta");
            ResultSet objResultSet = ST.executeQuery();

            while(objResultSet.next()) {
                obj = new Conta();
                obj.setCodigoConta(objResultSet.getInt("codigo_conta"));
                obj.setNumeroConta(objResultSet.getString("numero_conta"));
                obj.setAgenciaConta(objResultSet.getString("agencia_conta"));
                obj.setSaldoConta(objResultSet.getDouble("saldo_conta"));

                lista.add(obj);
            }
            return lista;

        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar todos - DAO \n" + ex.getMessage());
        }
        return null;
    }
}

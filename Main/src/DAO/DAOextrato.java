package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import Model.Extrato;

public class DAOextrato {
    
    private Connection conexao;

    public DAOextrato(){
        conexao = Fabrica.getConexaoSINGLETON();
    }
    
    public DAOextrato(Connection pConexao){
        conexao = pConexao;
    }
    
    public void insert(Extrato obj) {
        
        try {
            PreparedStatement sql = conexao.prepareStatement( 
                    "INSERT INTO extrato (codigo_extrato, descricao_extrato , data_extrato, tipo_extrato, "
                            + "valor_extrato, extrato_codigo_conta) VALUES (?,?,?,?,?,?)");
            
            sql.setInt(1, obj.getCodigoExtrato());
            sql.setString(2, obj.getDescricaoExtrato());
            sql.setTimestamp(3, new Timestamp(obj.getDataExtrato().getTimeInMillis()));
            sql.setString(4, obj.getTipoExtrato());
            sql.setDouble(5, obj.getValorExtrato());
            sql.setInt(6, obj.getExtratoCodigoConta());
            
            sql.execute();
            System.out.println("Inserido \n");
        } catch (SQLException ex) {
            System.out.println("Não inserido - erro - DAO: \\n" + ex.getMessage());
        }
    }

    public  void update(Extrato obj) {
        try {
            String sqlUpdate = "UPDATE "
                                + "extrato "
                             + "SET "
                                + "descricao_extrato = ?, "
                                + "data_extrato = ?, "
                                + "tipo_extrato = ?, "
                                + "valor_extrato = ?, "
                                + "extrato_codigo_conta = ? "
                             + "WHERE "
                                + "codigo_extrato = ?";

            PreparedStatement sql = conexao.prepareStatement(sqlUpdate);

            sql.setString(1, obj.getDescricaoExtrato());
            sql.setTimestamp(2, new Timestamp(obj.getDataExtrato().getTimeInMillis()));
            sql.setString(3, obj.getTipoExtrato());
            sql.setDouble(4, obj.getValorExtrato());
            sql.setInt(5, obj.getExtratoCodigoConta());
            sql.setInt(6, obj.getCodigoExtrato());
            
            sql.executeUpdate();
            sql.close();

            System.out.println("Atualizado \n");
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar extrato - DAO \n" + ex.getMessage());
        }
    }

    public  boolean delete(int codigoExtrato) {
        try {
            PreparedStatement ST = (PreparedStatement) conexao.prepareStatement("DELETE FROM extrato WHERE codigo_extrato = ?");
            ST.setInt(1, codigoExtrato);
            ST.execute();
            
            System.out.println("Excluido");
            return true;
        } catch (SQLException err) {
            System.out.println("Erro ao excluir - DAO: " + err.getMessage());
        }
        
        return false;
    }
    
    public  Extrato recuperar(int codigoConta) {
        Extrato obj = new Extrato();
        
        try {
            PreparedStatement ST = conexao.prepareStatement("SELECT * FROM extrato WHERE extrato_codigo_conta  = ?");
            ST.setInt(1, codigoConta);
            ResultSet objResultSet = ST.executeQuery();     
            objResultSet.next();
            
            obj.setCodigoExtrato(objResultSet.getInt("codigo_extrato"));
            obj.setDescricaoExtrato(objResultSet.getString("descricao_extrato"));
            obj.getDataExtrato().setTime(objResultSet.getDate("data_extrato"));
            obj.setTipoExtrato(objResultSet.getString("tipo_extrato"));
            obj.setValorExtrato(objResultSet.getDouble("valor_extrato"));
            obj.setExtratoCodigoConta(objResultSet.getInt("extrato_codigo_conta"));
       
        } catch (Exception e) {
            System.err.println("Erro ao recuperar" + e.getMessage());
        }
        
        return obj;
    }
    
    public ArrayList<Extrato> recuperarExtratoConta(int codConta) {
        Extrato obj = null;
        ArrayList<Extrato> lista = new ArrayList<>();

        try {
            PreparedStatement ST = (PreparedStatement) conexao.prepareStatement(
                    "SELECT codigo_extrato, descricao_extrato , data_extrato, tipo_extrato, valor_extrato, extrato_codigo_conta FROM extrato WHERE extrato_codigo_conta = ?");
            ST.setInt(1, codConta);
            ResultSet objResultSet = ST.executeQuery();
            
            for (int i = 0; i < lista.size(); i++) {
                System.out.println(lista.get(i));
            }

            while(objResultSet.next()) {

                obj = new Extrato();
                obj.setCodigoExtrato(objResultSet.getInt("codigo_extrato"));
                obj.setDescricaoExtrato(objResultSet.getString("descricao_extrato"));
                obj.getDataExtrato().setTime(objResultSet.getDate("data_extrato"));
                obj.setTipoExtrato(objResultSet.getString("tipo_extrato"));
                obj.setValorExtrato(objResultSet.getDouble("valor_extrato"));
                obj.setExtratoCodigoConta(objResultSet.getInt("extrato_codigo_conta"));
                lista.add(obj);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar extrato - DAO \n" + ex.getMessage());
        }
        
        return null;
    }   
}

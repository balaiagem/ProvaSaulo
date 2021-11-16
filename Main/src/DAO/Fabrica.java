/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Leo
 */
public class Fabrica {
    
    private static Connection conexaoSINGLETON;
    private static final String usuario = "root";
    private static final String senha = "unifagoc";
    private static final String servidor = "localhost";
    private static final String porta = "3306";
    private static final String bd = "banco?useTimezone=true&serverTimezone=UTC";
    private static final String urlconexao = "jdbc:mysql://" + servidor + ":" + porta + "/" + bd;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
        private static Connection Conectar(){
        Connection conexao = null;
        try {
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(urlconexao, usuario, senha);
            System.out.println("Conectado!");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro driver:\n" + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Erro:\n" + ex.getMessage());
        }
        return conexao;
    }
    
            public static Connection getConexaoSINGLETON() {
        if (conexaoSINGLETON == null) {
            conexaoSINGLETON = Conectar();
        }
        return conexaoSINGLETON;
    }

    public static Connection getConexaoNOVA() {
        Connection conexao = null;
        try {
            conexao = Conectar();
            conexao.setAutoCommit(false);
            conexao.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return conexao;
    }
}

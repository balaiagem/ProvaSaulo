/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import DAO.DAOconta;
import DAO.DAOextrato;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Leo
 */
public class OperacaoContas {

    public static boolean transferir(int chaveContaOrigem,int chaveContaDestino, double valor) throws SQLException {

        Connection conexao = DAO.Fabrica.getConexaoNOVA();
        {
             
            try {
                
                DAOconta daoConta = new DAOconta(conexao);
                DAOextrato daoExtrato = new DAOextrato(conexao);
                conexao.commit();
                return true;

            } catch(Exception e)  {
                
                conexao.rollback();
                return false;
            }
        }
    }

}

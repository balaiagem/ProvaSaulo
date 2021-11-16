package Utilitario;

import DAO.DAOconta;
import DAO.DAOextrato;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JOptionPane;
import Model.Conta;
import Model.Extrato;

public class TransferenciaContas {
    
        public static boolean transferir(int codContaOrigem, int codContaDestino, double valor) throws SQLException{
            Connection conexao = DAO.Fabrica.getConexaoNOVA();
            
            //verifica se há saldo na conta de origem
            try {
                DAOconta daoConta = new DAOconta(conexao);
                Conta contaOrigem = daoConta.recuperar(codContaOrigem);
                Conta contaDestino = daoConta.recuperar(codContaDestino);

                if(contaOrigem.getSaldoConta() < valor){
                    System.out.println("Saldo insuficiente");
                    return false;
                }
                
                
                atualizar(contaOrigem, contaDestino, valor, conexao);
                conexao.commit();
                
                System.out.println("Transferido com sucesso");
            } catch (Exception e) {
                conexao.rollback();
                System.out.println("Erro ao transferir" + e);
            }
            return true;
        }
        
        public static boolean atualizar(Conta contaOrigem, Conta contaDestino, double valor, Connection conexao){
            
            //debita o saldo da conta de origem no banco
            try {
                DAOconta daoConta = new DAOconta(conexao);           
                contaOrigem.setSaldoConta(contaOrigem.getSaldoConta() - valor);
                daoConta.update(contaOrigem);
                
            } catch (Exception e) {
                System.out.println("Erro ao atualizar saldo da origem: " + e);
                return false;
            }
            
            //cria o extrato de saída da conta de origem
            try {
                DAOextrato daoExtrato = new DAOextrato(conexao);
                
                Extrato extr = new Extrato();
                extr.setCodigoExtrato(0);
                extr.setDescricaoExtrato("Enviado p/ conta: " + contaDestino.getCodigoConta());
                extr.setDataExtrato(Calendar.getInstance());
                extr.setTipoExtrato("S");
                extr.setValorExtrato(valor);
                extr.setExtratoCodigoConta(contaOrigem.getCodigoConta());
                daoExtrato.insert(extr);
            } catch (Exception e) {
                System.out.println("Erro ao criar extrato da origem: " + e);
                return false;
            }
            
            //credita o saldo da conta de destino no banco
            try {
                DAOconta daoConta = new DAOconta(conexao);           
                contaDestino.setSaldoConta(contaDestino.getSaldoConta() + valor);
                daoConta.update(contaDestino);
                
            } catch (Exception e) {
                System.out.println("Erro no destino: " + e);
                return false;
            }
            
             //cria o extrato de entrada da conta de destino
            try {
                DAOextrato daoExtrato = new DAOextrato(conexao);
                
                Extrato extr = new Extrato();
                extr.setCodigoExtrato(0);
                extr.setDescricaoExtrato("Recebido da conta: " + contaOrigem.getCodigoConta());
                extr.setDataExtrato(Calendar.getInstance());
                extr.setTipoExtrato("E");
                extr.setValorExtrato(valor);
                extr.setExtratoCodigoConta(contaDestino.getCodigoConta());
                daoExtrato.insert(extr);
            } catch (Exception e) {
                System.out.println("Erro ao criar extrato do destino: " + e);
                return false;
            }
        return true;
        
        }
        
        public static boolean sacar(double valor, int codConta) throws SQLException{
            
            Connection conexao = DAO.Fabrica.getConexaoNOVA();
            DAOconta daoConta = new DAOconta(conexao);
            Conta conta = daoConta.recuperar(codConta);
            
              if(conta.getSaldoConta() < valor){
                    System.out.println("Saldo insuficiente");
                    return false;
                }
              try{
            atualizarcontasaque(conta, valor, conexao);
                conexao.commit();
                
                System.out.println("Saque com sucesso");
            } catch (Exception e) {
                conexao.rollback();
                System.out.println("Erro ao sacar" + e);
            }
            return true;
            

 }
        
        public static boolean atualizarcontasaque(Conta conta, double valor, Connection conexao){
            
            try {
                DAOconta daoConta = new DAOconta(conexao);           
                conta.setSaldoConta(conta.getSaldoConta() - valor);
                daoConta.update(conta);
                
            } catch (Exception e) {
                System.out.println("Erro no saque: " + e);
                return false;
            }
            
         try {         
                DAOextrato daoExtrato = new DAOextrato(conexao);
                
                Extrato extr = new Extrato();
                extr.setCodigoExtrato(0);
                extr.setDescricaoExtrato("Saque: " + conta.getCodigoConta());
                extr.setDataExtrato(Calendar.getInstance());
                extr.setTipoExtrato("S");
                extr.setValorExtrato(valor);
                extr.setExtratoCodigoConta(conta.getCodigoConta());
                daoExtrato.insert(extr);
            } catch (Exception e) {
                System.out.println("Erro ao criar extrato do saque: " + e);
                return false;
            }
        return true;
        
        }
        
                public static boolean depositar(double valor, int codConta) throws SQLException{
            
            Connection conexao = DAO.Fabrica.getConexaoNOVA();
            DAOconta daoConta = new DAOconta(conexao);
            Conta conta = daoConta.recuperar(codConta);
            
              try{
            atualizarcontadeposito(conta, valor, conexao);
                conexao.commit();
                
                System.out.println("deposito com sucesso");
            } catch (Exception e) {
                conexao.rollback();
                System.out.println("Erro ao depositar" + e);
            }
            return true;
            

 }
                
                 public static boolean atualizarcontadeposito(Conta conta, double valor, Connection conexao){
            
            try {
                DAOconta daoConta = new DAOconta(conexao);           
                conta.setSaldoConta(conta.getSaldoConta() + valor);
                daoConta.update(conta);
                
            } catch (Exception e) {
                System.out.println("Erro no deposito: " + e);
                return false;
            }
            
         try {         
                DAOextrato daoExtrato = new DAOextrato(conexao);
                
                Extrato extr = new Extrato();
                extr.setCodigoExtrato(0);
                extr.setDescricaoExtrato("Deposito: " + conta.getCodigoConta());
                extr.setDataExtrato(Calendar.getInstance());
                extr.setTipoExtrato("E");
                extr.setValorExtrato(valor);
                extr.setExtratoCodigoConta(conta.getCodigoConta());
                daoExtrato.insert(extr);
            } catch (Exception e) {
                System.out.println("Erro ao criar extrato do deposito: " + e);
                return false;
            }
        return true;
        
        }
}

        

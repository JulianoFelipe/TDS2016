/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.JDBC;

import java.sql.Connection;

/**
 * DAO abstrado usado em todos os DAOs
 * do mesmo pacote. Contém métodos de
 * conexão com o banco.
 * @author Juliano Felipe da Silva
 */
public class JDBCAbstractDAO {
    protected Connection connection;
    
    public JDBCAbstractDAO() {
        connection = ConnectionFactory.getConnection();
    }
}

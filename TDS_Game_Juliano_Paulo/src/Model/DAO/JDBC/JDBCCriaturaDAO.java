/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.JDBC;

import Model.Criaturas.CriaturaBase;
import Model.DAO.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import Model.DAO.DatabaseException;

/**
 *
 * @author Juliano Felipe da Silva
 */
public class JDBCCriaturaDAO extends JDBCAbstractDAO implements CriaturaDAO {
    
    @Override
    public int inserir(CriaturaBase t) throws DatabaseException {
        String query = "INSERT INTO cliente (nome,cpf,tel,end,obs,lname) "
                     + "VALUES (?,?,?,?,?,?)";
        PreparedStatement pst = null;
        
        int nextId =-1;
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, cl.getNome());
            pst.setString(6, cl.getSobrenome());
            pst.setString(2, cl.getCpf());
            pst.setString(3, cl.getTel());
            pst.setString(4, cl.getEnd());
            pst.setString(5, cl.getEnd());
            pst.execute();

            nextId = getNextId();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }  finally {
            if (pst != null) 
                pst.close();
        }
        return nextId;
    }

    @Override
    public boolean remover(CriaturaBase t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizar(CriaturaBase t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CriaturaBase> resgatarTodos() throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CriaturaBase buscar(int primaryKey) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CriaturaBase> buscar(String nome) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNextId() throws SQLException {
        String query = "SELECT criaturaId FROM CriaturaBase";
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        
        int lastId=-1;
        while (rs.next()){
            lastId = rs.getInt("criaturaId");
        }
        return lastId+1;
    }
}

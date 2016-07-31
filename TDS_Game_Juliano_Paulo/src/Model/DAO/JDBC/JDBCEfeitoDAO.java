/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.JDBC;

import Model.DAO.*;
import Model.Efeitos.Efeitos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Juliano Felipe da Silva
 */
public class JDBCEfeitoDAO extends JDBCAbstractDAO implements EfeitoDAO {
    private static StringBuilder QUERY = new StringBuilder();
    
    @Override
    public int inserir(Efeitos t) throws DatabaseException {
        QUERY.append("INSERT INTO Efeito (tipo,comportamento,instantaneo,")
             .append("duracao,poder_percentual,poder_constante,descricao) ")
             .append("VALUES (?,?,?,?,?,?,?)");
        
        PreparedStatement pst = null;
        int nextId =-1;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            pst.setInt(1, t.getTipo().getValor());
            pst.setInt(2, t.getComportamento_efeito().getValor());
            pst.setBoolean(3, t.isInstantaneo());
            pst.setInt(4, t.getDuration());
            pst.setDouble(5, t.getPoder_percentual());
            pst.setDouble(6, t.getPoder_constante());
            pst.setString(7, t.getDescricao());
            pst.execute();

            nextId = getNextId();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }  finally {
            if (pst != null){
                try{ pst.close();}
                catch (SQLException ex){
                throw new DatabaseException(ex.getMessage());}
            }
        }
        
        QUERY = new StringBuilder();
        return nextId-1;
    }

    @Override
    public boolean remover(Efeitos t) throws DatabaseException {
        QUERY.append("DELETE FROM Efeito")
             .append("WHERE efeitoID=").append(t.getEfeitoID());

        PreparedStatement pst = null;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            pst.executeQuery();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }  finally {
            if (pst != null){
                try{ pst.close();}
                catch (SQLException ex){
                throw new DatabaseException(ex.getMessage());}
            }
        }
        
        QUERY = new StringBuilder();
        return true;
    }

    @Override
    public boolean atualizar(Efeitos t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Efeitos> resgatarTodos() throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Efeitos buscar(int primaryKey) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Efeitos> buscar(String nome) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNextId() throws SQLException {
        String query = "SELECT efeitoId FROM Efeito";
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        
        int lastId=-1;
        while (rs.next()){
            lastId = rs.getInt("efeitoId");
        }
        return lastId+1;
    }
}

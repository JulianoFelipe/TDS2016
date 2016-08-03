/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.JDBC;

import Model.DAO.DatabaseException;
import Model.DAO.ItemDAO;
import Model.Itens.ArmaBase;
import Model.Itens.ItemBase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link Model.DAO.ItemDAO} aplicado ao 
 * banco de dados SQLITE, identificado pela
 * opção {@link Model.DAO.DAOFactory#SQLITE}.
 * @author Juliano Felipe da Silva
 */
public class JDBCItemDAO extends JDBCAbstractDAO implements ItemDAO {
    private static StringBuilder QUERY = new StringBuilder();
    
    @Override
    public int inserir(ItemBase t) throws DatabaseException {
        QUERY.append("INSERT INTO ItemBase (nome,valor)")
             .append("VALUES (?,?)");
        
        PreparedStatement pst = null;
        int nextId =-1;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            pst.setString(1, t.getNome());
            pst.setInt(2, t.getValor());
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
        t.setItemId(nextId-1);
        return nextId-1;
    }

    @Override
    public boolean remover(ItemBase t) throws DatabaseException {
        QUERY.append("DELETE FROM ItemBase ")
             .append("WHERE itemId=").append(t.getItemId());

        PreparedStatement pst = null;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            pst.execute();
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
    public boolean atualizar(ItemBase t) throws DatabaseException {
        QUERY.append("UPDATE ItemBase SET nome=?, SET valor=? ")
             .append("WHERE itemId=").append(t.getItemId());

        PreparedStatement pst = null;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            pst.setString(1, t.getNome());
            pst.setInt(2, t.getValor());
            pst.execute();

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
    public List<ItemBase> resgatarTodos() throws DatabaseException {
        QUERY.append("SELECT * FROM ItemBase");

        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ItemBase> lista = new ArrayList();
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            rs = pst.executeQuery();
            
            while (rs.next()){
                lista.add( getInstance(rs));
            }

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }  finally {
            if (pst != null){
                try{ pst.close();}
                catch (SQLException ex){
                throw new DatabaseException(ex.getMessage());}
            }
            if (rs != null){
                try{ rs.close();}
                catch (SQLException ex){
                throw new DatabaseException(ex.getMessage());}
            }
        }
        
        QUERY = new StringBuilder();
        return lista;
    }

    @Override
    public ItemBase buscar(int primaryKey) throws DatabaseException {
        QUERY.append("SELECT * FROM ItemBase")
             .append("WHERE itemId=").append(primaryKey);

        PreparedStatement pst = null;
        ResultSet rs = null;
        
        ItemBase item = null;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            rs = pst.executeQuery();
            
            if (rs.next()){
                item = getInstance(rs);
            }

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }  finally {
            if (pst != null){
                try{ pst.close();}
                catch (SQLException ex){
                throw new DatabaseException(ex.getMessage());}
            }
            if (rs != null){
                try{ rs.close();}
                catch (SQLException ex){
                throw new DatabaseException(ex.getMessage());}
            }
        }
        
        QUERY = new StringBuilder();
        return item;
    }

    @Override
    public List<ItemBase> buscar(String nome) throws DatabaseException {
        QUERY.append("SELECT * FROM ItemBase")
             .append("WHERE nome=").append(nome);

        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ItemBase> lista = new ArrayList();
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            rs = pst.executeQuery();
            
            while (rs.next()){
                lista.add( getInstance(rs));
            }

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }  finally {
            if (pst != null){
                try{ pst.close();}
                catch (SQLException ex){
                throw new DatabaseException(ex.getMessage());}
            }
            if (rs != null){
                try{ rs.close();}
                catch (SQLException ex){
                throw new DatabaseException(ex.getMessage());}
            }
        }
        
        QUERY = new StringBuilder();
        return lista;
    }

    @Override
    public int getNextId() throws SQLException {
        String query = "SELECT itemId FROM ItemBase";
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        
        int lastId=-1;
        while (rs.next()){
            lastId = rs.getInt("itemId");
        }
        return lastId+1;
    }

    public List<ArmaBase> jogadorHeroiFK(int jogadorFK, int heroiFK) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private ItemBase getInstance(ResultSet rs) throws SQLException{
        ItemBase item = new ArmaBase(0.0); //Arma para poder instanciar
        
        item.setNome(rs.getString("nome"));
        item.setValor(rs.getInt("level"));
        
        return item;
    }
}

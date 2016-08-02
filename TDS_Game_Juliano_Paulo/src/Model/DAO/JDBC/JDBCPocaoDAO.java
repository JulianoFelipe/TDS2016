/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.JDBC;

import Model.DAO.DAOFactory;
import Model.DAO.DatabaseException;
import Model.DAO.PocaoDAO;
import Model.Itens.Constantes.Pocoes;
import Model.Itens.PocaoAumentoStatus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juliano Felipe da Silva
 */
public class JDBCPocaoDAO extends JDBCAbstractDAO implements PocaoDAO {
    private static StringBuilder QUERY = new StringBuilder();
    private static final DAOFactory DAO = DAOFactory.getDAOFactory( DAOFactory.SQLITE );
    
    @Override
    public int inserir(PocaoAumentoStatus t) throws DatabaseException {
        int itemId = DAO.getItemDAO().inserir(t);
        
        QUERY.append("INSERT INTO PocaoAumentoStatus (itemId,tipo,aumento)")
             .append("VALUES (?,?,?)");
        
        PreparedStatement pst = null;
        int nextId =-1;
        
        try {
            pst = connection.prepareStatement(QUERY.toString()); // 1 a 14
            pst.setInt(1, itemId);
            pst.setInt(2, t.getTipo().getCodigo());
            pst.setDouble(3, t.getAumento());
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
    public boolean remover(PocaoAumentoStatus t) throws DatabaseException {
        boolean rmItem = DAO.getItemDAO().remover(t);
        if (!rmItem) throw new DatabaseException("Retorno falso ao deletar itemTable Pai");
        
        QUERY.append("DELETE FROM PocaoAumentoStatus")
             .append("WHERE pocaoId=").append(t.getPocaoId());

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
    public boolean atualizar(PocaoAumentoStatus t) throws DatabaseException {
        boolean updateItem = DAO.getItemDAO().atualizar(t);
        if (!updateItem) throw new DatabaseException("Retorno falso ao atualizar itemTable Pai");
        
        QUERY.append("UPDATE PocaoAumentoStatus SET tipo=?, SET aumento=? ")
             .append("WHERE itemId=").append(t.getItemId());

        PreparedStatement pst = null;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            pst.setInt(1, t.getTipo().getCodigo());
            pst.setDouble(2, t.getAumento());
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
    public List<PocaoAumentoStatus> resgatarTodos() throws DatabaseException {
        QUERY.append("SELECT * FROM ItemBase, PocaoAumentoStatus ")
             .append("WHERE PocaoAumentoStatus.itemId = ItemBase.itemId");

        PreparedStatement pst = null;
        ResultSet rs = null;
        List<PocaoAumentoStatus> lista = new ArrayList();
        
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
    public PocaoAumentoStatus buscar(int primaryKey) throws DatabaseException {
        QUERY.append("SELECT * FROM ItemBase, PocaoAumentoStatus ")
             .append("WHERE PocaoAumentoStatus.itemId=ItemBase.itemId ")
             .append("AND PocaoAumentoStatus.pocaoId=").append(primaryKey);

        PreparedStatement pst = null;
        ResultSet rs = null;
        
        PocaoAumentoStatus pocao = null;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            rs = pst.executeQuery();
            
            if (rs.next()){
                pocao = getInstance(rs);
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
        return pocao;
    }

    @Override
    public List<PocaoAumentoStatus> buscar(String nome) throws DatabaseException {
        QUERY.append("SELECT * FROM ItemBase, PocaoAumentoStatus ")
             .append("WHERE PocaoAumentoStatus.itemId=ItemBase.itemId ")
             .append("AND PocaoAumentoStatus.nome=\"").append(nome).append("\"");

        PreparedStatement pst = null;
        ResultSet rs = null;
        List<PocaoAumentoStatus> lista = new ArrayList();
        
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
        String query = "SELECT pocaoId FROM PocaoAumentoStatus";
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        
        int lastId=-1;
        while (rs.next()){
            lastId = rs.getInt("pocaoId");
        }
        return lastId+1;
    }
    
    private PocaoAumentoStatus getInstance(ResultSet rs) throws SQLException {
        PocaoAumentoStatus pocao = new PocaoAumentoStatus(
                                       Pocoes.porCodigo( rs.getInt("tipo")),
                                       rs.getDouble("aumento"));
        
        pocao.setNome(rs.getString("nome"));
        pocao.setValor(rs.getInt("valor"));
        return pocao;
    }

    @Override
    public List<PocaoAumentoStatus> itemFK(int foreignKey) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

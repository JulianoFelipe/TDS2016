/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.JDBC;

import Model.DAO.DAOFactory;
import Model.DAO.DatabaseException;
import Model.DAO.PergaminhoDAO;
import Model.Habilidades.HabilidadeBase;
import Model.Itens.PergaminhoHabilidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juliano Felipe da Silva
 */
public class JDBCPergaminhoDAO extends JDBCAbstractDAO implements PergaminhoDAO {
    private static StringBuilder QUERY = new StringBuilder();
    private static final DAOFactory DAO = DAOFactory.getDAOFactory( DAOFactory.SQLITE );
    
    @Override
    public int inserir(PergaminhoHabilidade t) throws DatabaseException {
        int itemId = DAO.getItemDAO().inserir(t);
        int habilidadeId = DAO.getHabilidadeDAO().checarSeNoBanco(t.getSkill_associada());
        
        if (habilidadeId == -1){ //Se a habilidade não está no banco, insere
            habilidadeId = DAO.getHabilidadeDAO().inserir(t.getSkill_associada());
        }
        
        QUERY.append("INSERT INTO PergaminhoHabilidade (itemId,habilidadeId) ")
             .append("VALUES (?,?)");
        
        PreparedStatement pst = null;
        int nextId =-1;
        
        try {
            pst = connection.prepareStatement(QUERY.toString()); // 1 a 14
            pst.setInt(1, itemId);
            pst.setInt(2, habilidadeId);
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
        t.setPergaminhoId(nextId-1);
        return nextId-1;
    }

    @Override
    public boolean remover(PergaminhoHabilidade t) throws DatabaseException {
        boolean rmItem = DAO.getItemDAO().remover(t);
        if (!rmItem) throw new DatabaseException("Retorno falso ao deletar itemTable Pai");
        
        QUERY.append("DELETE FROM PergaminhoHabilidade")
             .append("WHERE pergaminhoId=").append(t.getPergaminhoId());

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
    public boolean atualizar(PergaminhoHabilidade t) throws DatabaseException {
        boolean updateItem = DAO.getItemDAO().atualizar(t);
        if (!updateItem) throw new DatabaseException("Retorno falso ao atualizar itemTable Pai");
        
        boolean updateHabilidade = DAO.getHabilidadeDAO().atualizar(t.getSkill_associada());
        if (!updateHabilidade) throw new DatabaseException("Retorno falso ao atualizar habilidade Pai");
        
        QUERY = new StringBuilder();
        return true;
    }

    @Override
    public List<PergaminhoHabilidade> resgatarTodos() throws DatabaseException {
        QUERY.append("SELECT * FROM ItemBase, HabilidadeBase, PergaminhoHabilidade ")
             .append("WHERE PergaminhoHabilidade.itemId=ItemBase.itemId ")
             .append("AND PergaminhoHabilidade.habilidadeId=HabilidadeBase.habilidadeId");

        PreparedStatement pst = null;
        ResultSet rs = null;
        List<PergaminhoHabilidade> lista = new ArrayList();
        
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
    public PergaminhoHabilidade buscar(int primaryKey) throws DatabaseException {
        QUERY.append("SELECT * FROM ItemBase, HabilidadeBase, PergaminhoHabilidade ")
             .append("WHERE PergaminhoHabilidade.itemId=ItemBase.itemId ")
             .append("AND PergaminhoHabilidade.habilidadeId=HabilidadeBase.habilidadeId ")
             .append("AND PergaminhoHabilidade.pergaminhoId=").append(primaryKey);

        PreparedStatement pst = null;
        ResultSet rs = null;
        
        PergaminhoHabilidade pergaminho = null;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            rs = pst.executeQuery();
            
            if (rs.next()){
                pergaminho = getInstance(rs);
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
        return pergaminho;
    }

    @Override
    public List<PergaminhoHabilidade> buscar(String nome) throws DatabaseException {
        QUERY.append("SELECT * FROM ItemBase, HabilidadeBase, PergaminhoHabilidade ")
             .append("WHERE PergaminhoHabilidade.itemId=ItemBase.itemId ")
             .append("AND PergaminhoHabilidade.habilidadeId=HabilidadeBase.habilidadeId ")
             .append("AND ItemBase.nome=\"").append(nome).append("\"");

        PreparedStatement pst = null;
        ResultSet rs = null;
        List<PergaminhoHabilidade> lista = new ArrayList();
        
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
        String query = "SELECT pergaminhoId FROM PergaminhoHabilidade";
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        
        int lastId=-1;
        while (rs.next()){
            lastId = rs.getInt("pergaminhoId");
        }
        return lastId+1;
    }

    @Override
    public List<PergaminhoHabilidade> itemHabilidadeFK(int itemFK, int habilidadeFK) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private PergaminhoHabilidade getInstance (ResultSet rs) throws SQLException, DatabaseException{
        HabilidadeBase t = DAO.getHabilidadeDAO().buscar(rs.getInt("habilidadeId"));
        PergaminhoHabilidade pergaminho = new PergaminhoHabilidade(t);
        pergaminho.setNome(rs.getString("nome"));
        pergaminho.setValor(rs.getInt("valor"));
        return pergaminho;
    }
}

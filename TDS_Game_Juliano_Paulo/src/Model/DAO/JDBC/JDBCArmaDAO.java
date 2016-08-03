/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.JDBC;

import Model.DAO.ArmaDAO;
import Model.DAO.DAOFactory;
import Model.DAO.DatabaseException;
import Model.Itens.ArmaBase;
import Model.Itens.Constantes.Armas;
import Model.Itens.Constantes.Modificador;
import Model.Itens.Constantes.Raridade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link Model.DAO.ArmaDAO} aplicado ao 
 * banco de dados SQLITE, identificado pela
 * opção {@link Model.DAO.DAOFactory#SQLITE}.
 * @author Juliano Felipe da Silva
 */
public class JDBCArmaDAO extends JDBCAbstractDAO implements ArmaDAO {
    private static StringBuilder QUERY = new StringBuilder();
    private static final DAOFactory DAO = DAOFactory.getDAOFactory( DAOFactory.SQLITE );
    
    @Override
    public int inserir(ArmaBase t) throws DatabaseException {
        int itemId = DAO.getItemDAO().inserir(t);
        
        QUERY.append("INSERT INTO ArmaBase (itemId,tipo,level,incrementoDano")
             .append(",raridade,modificador) ")
             .append("VALUES (?,?,?,?,?,?)");
        
        PreparedStatement pst = null;
        int nextId =-1;
        
        try {
            pst = connection.prepareStatement(QUERY.toString()); // 1 a 14
            pst.setInt(1, itemId);
            pst.setInt(2,  t.getTipo().getValor());
            pst.setInt(3,  t.getLevel());
            pst.setDouble(4,  t.getIncrementoDano());
            pst.setInt(5,  t.getRaridade().getCodigo());
            pst.setInt(6,  t.getModificador().getCodigo());
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
        t.setArmaId(nextId-1);
        return nextId-1;
    }

    @Override
    public boolean remover(ArmaBase t) throws DatabaseException {
        boolean rmItem = DAO.getItemDAO().remover(t);
        if (!rmItem) throw new DatabaseException("Retorno falso ao deletar itemTable Pai");
        
        QUERY.append("DELETE FROM ArmaBase ")
             .append("WHERE armaId=").append(t.getArmaId());

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
    public boolean atualizar(ArmaBase t) throws DatabaseException {
        boolean rmItem = DAO.getItemDAO().atualizar(t);
        if (!rmItem) throw new DatabaseException("Retorno falso ao atualizar itemTable Pai");
        
        QUERY.append("UPDATE ArmaBase SET tipo=?, SET level=?, ")
             .append("SET incrementoDano=?, SET raridade=?, SET modificador=? ")
             .append("WHERE itemId=").append(t.getItemId());

        PreparedStatement pst = null;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            pst.setInt(1, t.getTipo().getValor());
            pst.setInt(2, t.getLevel());
            pst.setDouble(3, t.getIncrementoDano());
            pst.setInt(4, t.getRaridade().getCodigo());
            pst.setInt(5, t.getModificador().getCodigo());
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
    public List<ArmaBase> resgatarTodos() throws DatabaseException {
        QUERY.append("SELECT * FROM ItemBase, ArmaBase ")
             .append("WHERE ArmaBase.itemId = ItemBase.itemId");

        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ArmaBase> lista = new ArrayList();
        
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
    public ArmaBase buscar(int primaryKey) throws DatabaseException {
        QUERY.append("SELECT * FROM ItemBase, ArmaBase ")
             .append("WHERE ArmaBase.itemId=ItemBase.itemId ")
             .append("AND ArmaBase.armaId=").append(primaryKey);

        PreparedStatement pst = null;
        ResultSet rs = null;
        
        ArmaBase arma = null;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            rs = pst.executeQuery();
            
            if (rs.next()){
                arma = getInstance(rs);
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
        return arma;
    }

    @Override
    public List<ArmaBase> buscar(String nome) throws DatabaseException {
        QUERY.append("SELECT * FROM ItemBase, ArmaBase ")
             .append("WHERE ArmaBase.itemId=ItemBase.itemId ")
             .append("AND ItemBase.nome=\"").append(nome).append("\"");

        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ArmaBase> lista = new ArrayList();
        
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
        String query = "SELECT armaId FROM ArmaBase";
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        
        int lastId=-1;
        while (rs.next()){
            lastId = rs.getInt("armaId");
        }
        return lastId+1;
    }

    /**
     *  Dado um ResultSet, retorna uma instância
     * de uma Arma. O ResultSet deve conter as
     * colunas de ambas "ArmaBase" e ItemBase".
     * @param rs contendo as colunas de Item e Arma.
     * @return Instância de ArmaBase.
     * @throws SQLException Em erro.
     */
    private ArmaBase getInstance(ResultSet rs) throws SQLException {
        ArmaBase arma = new ArmaBase(rs.getDouble( "incrementoDano" ));
        arma.setNome( rs.getString("nome"));
        arma.setItemId(rs.getInt("itemId"));
        arma.setValor(rs.getInt("valor"));
        
        arma.setArmaId( rs.getInt( "armaId" ));
        arma.setTipo( Armas.porCodigo( rs.getInt( "tipo" ) ));
        arma.setLevel( rs.getInt( "level" ));
        arma.setRaridade( Raridade.porCodigo( rs.getInt( "raridade" ) ));
        arma.setModificador( Modificador.porCodigo( rs.getInt( "modificador" ) ));
        
        return arma;
    }

    @Override
    public ArmaBase buscaViaIdSuper(int FK) throws DatabaseException {
        QUERY.append("SELECT * FROM ItemBase, ArmaBase ")
             .append("WHERE ArmaBase.itemId=ItemBase.itemId ")
             .append("AND ArmaBase.itemId=").append(FK);

        PreparedStatement pst = null;
        ResultSet rs = null;
        
        ArmaBase arma = null;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            rs = pst.executeQuery();
            
            if (rs.next()){
                arma = getInstance(rs);
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
        return arma;
    }
}

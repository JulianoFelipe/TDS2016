/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.JDBC;

import Model.DAO.ArmaduraDAO;
import Model.DAO.DAOFactory;
import Model.DAO.DatabaseException;
import Model.Itens.ArmaduraBase;
import Model.Itens.Constantes.Armaduras;
import Model.Itens.Constantes.Modificador;
import Model.Itens.Constantes.Raridade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juliano Felipe da Silva
 */
public class JDBCArmaduraDAO extends JDBCAbstractDAO implements ArmaduraDAO{
    private static StringBuilder QUERY = new StringBuilder();
    private static final DAOFactory DAO = DAOFactory.getDAOFactory( DAOFactory.SQLITE );
    
    @Override
    public int inserir(ArmaduraBase t) throws DatabaseException {
        int itemId = DAO.getItemDAO().inserir(t);
        
        QUERY.append("INSERT INTO ArmaduraBase (itemId,tipo,level,incrementoDefesa")
             .append(",raridade,modificador) ")
             .append("VALUES (?,?,?,?,?,?)");
        
        PreparedStatement pst = null;
        int nextId =-1;
        
        try {
            pst = connection.prepareStatement(QUERY.toString()); // 1 a 14
            pst.setInt(1, itemId);
            pst.setInt(2,  t.getTipo().getValor());
            pst.setInt(3,  t.getLevel());
            pst.setDouble(4,  t.getIncrementoDefesa());
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
        t.setArmaduraId(nextId-1);
        return nextId-1;
    }

    @Override
    public boolean remover(ArmaduraBase t) throws DatabaseException {
        boolean rmItem = DAO.getItemDAO().remover(t);
        if (!rmItem) throw new DatabaseException("Retorno falso ao deletar itemTable Pai");
        
        QUERY.append("DELETE FROM ArmaduraBase")
             .append("WHERE armaduraId=").append(t.getArmaduraId());

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
    public boolean atualizar(ArmaduraBase t) throws DatabaseException {
        boolean rmItem = DAO.getItemDAO().atualizar(t);
        if (!rmItem) throw new DatabaseException("Retorno falso ao atualizar itemTable Pai");
        
        QUERY.append("UPDATE ArmaduraBase SET tipo=?, SET level=?, ")
             .append("SET incrementoDefesa=?, SET raridade=?, SET modificador=? ")
             .append("WHERE itemId=").append(t.getItemId());

        PreparedStatement pst = null;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            pst.setInt(1, t.getTipo().getValor());
            pst.setInt(2, t.getLevel());
            pst.setDouble(3, t.getIncrementoDefesa());
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
    public List<ArmaduraBase> resgatarTodos() throws DatabaseException {
        QUERY.append("SELECT * FROM ItemBase, ArmaduraBase ")
             .append("WHERE ArmaduraBase.itemId = ItemBase.itemId");

        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ArmaduraBase> lista = new ArrayList();
        
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
    public ArmaduraBase buscar(int primaryKey) throws DatabaseException {
        QUERY.append("SELECT * FROM ItemBase, ArmaduraBase ")
             .append("WHERE ArmaduraBase.itemId=ItemBase.itemId ")
             .append("AND ArmaduraBase.armaduraId=").append(primaryKey);

        PreparedStatement pst = null;
        ResultSet rs = null;
        
        ArmaduraBase armadura = null;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            rs = pst.executeQuery();
            
            if (rs.next()){
                armadura = getInstance(rs);
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
        return armadura;
    }

    @Override
    public List<ArmaduraBase> buscar(String nome) throws DatabaseException {
        QUERY.append("SELECT * FROM ItemBase, ArmaduraBase ")
             .append("WHERE ArmaduraBase.itemId=ItemBase.itemId ")
             .append("AND ItemBase.nome=\"").append(nome).append("\"");

        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ArmaduraBase> lista = new ArrayList();
        
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
        String query = "SELECT armaduraId FROM ArmaduraBase";
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        
        int lastId=-1;
        while (rs.next()){
            lastId = rs.getInt("armaduraId");
        }
        return lastId+1;
    }
    
    private ArmaduraBase getInstance(ResultSet rs) throws SQLException {
        ArmaduraBase armadura = new ArmaduraBase(rs.getDouble( "incrementoDefesa" ));
        armadura.setNome( rs.getString("nome"));
        armadura.setItemId(rs.getInt("itemId"));
        armadura.setValor(rs.getInt("valor"));
        
        armadura.setArmaduraId( rs.getInt( "armaduraId" ));
        armadura.setTipo( Armaduras.porCodigo( rs.getInt( "tipo" ) ));
        armadura.setLevel( rs.getInt( "level" ));
        armadura.setRaridade( Raridade.porCodigo( rs.getInt( "raridade" ) ));
        armadura.setModificador( Modificador.porCodigo( rs.getInt( "modificador" ) ));
        
        return armadura;
    }

    @Override
    public List<ArmaduraBase> itemFK(int foreignKey) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

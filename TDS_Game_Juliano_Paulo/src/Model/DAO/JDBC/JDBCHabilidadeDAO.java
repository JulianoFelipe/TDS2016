/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.JDBC;

import Model.DAO.*;
import Model.Habilidades.HabilidadeBase;
import Model.Habilidades.TipoHabilidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link Model.DAO.HabilidadeDAO} aplicado ao 
 * banco de dados SQLITE, identificado pela
 * opção {@link Model.DAO.DAOFactory#SQLITE}.
 * @author Juliano Felipe da Silva
 */
public class JDBCHabilidadeDAO extends JDBCAbstractDAO implements HabilidadeDAO {
    private static StringBuilder QUERY = new StringBuilder();
    
    @Override
    public int inserir(HabilidadeBase t) throws DatabaseException {
        QUERY.append("INSERT INTO HabilidadeBase (tipoHabilidade) ")
             .append("VALUES (?)");
        
        PreparedStatement pst = null;
        int nextId =-1;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            pst.setInt(1, TipoHabilidade.porInstancia(t).getValor());
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
        t.setHabilidadeId(nextId-1);
        return nextId-1;
    }

    @Override
    public int insereDistinto(HabilidadeBase t) throws DatabaseException {
        int checar = checarSeNoBanco(t);
        if (checar == -1) checar = inserir(t);
        return checar;
    }
    
    @Override
    public boolean remover(HabilidadeBase t) throws DatabaseException {
        QUERY.append("DELETE FROM HabilidadeBase ")
             .append("WHERE habilidadeId=").append(t.getHabilidadeId());

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
    public boolean atualizar(HabilidadeBase t) throws DatabaseException {
        QUERY.append("UPDATE HabilidadeBase SET tipoHabilidade=? ")
             .append("WHERE habilidadeId=").append(t.getHabilidadeId());

        PreparedStatement pst = null;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            pst.setInt(1, TipoHabilidade.porInstancia(t).getValor());
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
    public List<HabilidadeBase> resgatarTodos() throws DatabaseException {
        QUERY.append("SELECT * FROM HabilidadeBase");

        PreparedStatement pst = null;
        ResultSet rs = null;
        List<HabilidadeBase> lista = new ArrayList();
        
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
    public HabilidadeBase buscar(int primaryKey) throws DatabaseException {
        QUERY.append("SELECT * FROM HabilidadeBase")
             .append(" WHERE habilidadeId=?");

        PreparedStatement pst = null;
        ResultSet rs = null;
        HabilidadeBase habilidade = null;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            pst.setInt(1, primaryKey);
            rs = pst.executeQuery();
            
            if (rs.next()){
                habilidade = getInstance(rs);
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
        return habilidade;
    }

    @Override
    public List<HabilidadeBase> buscar(String nome) throws DatabaseException {
        throw new UnsupportedOperationException("Habilidade não tem nome... :/");
    }

    @Override
    public int getNextId() throws SQLException {
        String query = "SELECT habilidadeId FROM HabilidadeBase";
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        
        int lastId=-1;
        while (rs.next()){
            lastId = rs.getInt("habilidadeId");
        }
        return lastId+1;
    }
    
    private HabilidadeBase getInstance(ResultSet rs) throws SQLException, DatabaseException{
        HabilidadeBase habilidade = null;
        TipoHabilidade tipo = TipoHabilidade.porCodigo(rs.getInt("tipoHabilidade"));
        habilidade = TipoHabilidade.habilidadePorTipo(tipo);
        return habilidade;
    }

    @Override
    public int checarSeNoBanco(HabilidadeBase t)  throws DatabaseException{
        QUERY.append("SELECT * FROM HabilidadeBase ")
             .append("WHERE tipoHabilidade=?");

        PreparedStatement pst = null;
        ResultSet rs = null;
        
        int id = -1;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            pst.setInt(1, TipoHabilidade.porInstancia(t).getValor());
            rs = pst.executeQuery();
            
            if (rs.next()){
                id = rs.getInt("habilidadeId");
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
        return id;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.JDBC;

import Model.Criaturas.CriaturaBase;
import Model.Criaturas.Mago;
import Model.DAO.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import Model.DAO.DatabaseException;
import java.util.ArrayList;

/**
 *
 * @author Juliano Felipe da Silva
 */
public class JDBCCriaturaDAO extends JDBCAbstractDAO implements CriaturaDAO {
    private static StringBuilder QUERY = new StringBuilder();
    
    @Override
    public int inserir(CriaturaBase t) throws DatabaseException {
        QUERY.append("INSERT INTO CriaturaBase (nome,level,pontosVida,ataque,defesa,")
             .append("maxPontosVida,barraAtaque,esquiva,velocidade) ")
             .append("VALUES (?,?,?,?,?,?,?,?,?)");
        
        PreparedStatement pst = null;
        int nextId =-1;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            pst.setString(1, t.getNome());
            pst.setInt(2, t.getLevel());
            pst.setDouble(3, t.getPontosVida());
            pst.setDouble(4, t.getAtaque());
            pst.setDouble(5, t.getDefesa());
            pst.setDouble(6, t.getMaxPontosVida());
            pst.setDouble(7, t.getBarraAtaque());
            pst.setInt(8, t.getEsquiva());
            pst.setDouble(9, t.getVelocidade());
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
    public boolean remover(CriaturaBase t) throws DatabaseException {
        QUERY.append("DELETE FROM CriaturaBase")
             .append("WHERE criaturaID=").append(t.getCriaturaId());

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
    public boolean atualizar(CriaturaBase t) throws DatabaseException {
        QUERY.append("UPDATE CriaturaBase SET nome=?, SET level=?, SET")
             .append("SET pontosVida=?, SET ataque=?, SET defesa=?,")
             .append("SET maxPontosVida=?, SET barraAtaque=?, SET esquiva=?,")
             .append("SET velocidade=? ")
             .append("WHERE criaturaID=").append(t.getCriaturaId());

        PreparedStatement pst = null;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            pst.setString(1, t.getNome());
            pst.setInt(2, t.getLevel());
            pst.setDouble(3, t.getPontosVida());
            pst.setDouble(4, t.getAtaque());
            pst.setDouble(5, t.getDefesa());
            pst.setDouble(6, t.getMaxPontosVida());
            pst.setDouble(7, t.getBarraAtaque());
            pst.setInt(8, t.getEsquiva());
            pst.setDouble(9, t.getVelocidade());
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
    public List<CriaturaBase> resgatarTodos() throws DatabaseException {
        QUERY.append("SELECT * FROM CriaturaBase");

        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CriaturaBase> lista = new ArrayList();
        
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
    public CriaturaBase buscar(int primaryKey) throws DatabaseException {
        QUERY.append("SELECT * FROM CriaturaBase")
             .append("WHERE criaturaID=").append(primaryKey);

        PreparedStatement pst = null;
        ResultSet rs = null;
        
        CriaturaBase criatura = null;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            rs = pst.executeQuery();
            
            if (rs.next()){
                criatura = getInstance(rs);
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
        return criatura;
    }

    @Override
    public List<CriaturaBase> buscar(String nome) throws DatabaseException {
        QUERY.append("SELECT * FROM CriaturaBase")
             .append("WHERE nome=").append(nome);

        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CriaturaBase> lista = new ArrayList();
        
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
        String query = "SELECT criaturaId FROM CriaturaBase";
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        
        int lastId=-1;
        while (rs.next()){
            lastId = rs.getInt("criaturaId");
        }
        return lastId+1;
    }
    
    private CriaturaBase getInstance(ResultSet rs) throws SQLException{
        CriaturaBase criatura = new Mago(); //Fazendo mago por nÃ£o
        //poder instanciar criaturaBase  -- Gambi meio... loca.
        
        criatura.setCriaturaId(rs.getInt("criaturaID"));
        criatura.setNome(rs.getString("nome"));
        criatura.setLevel(rs.getInt("level"));
        criatura.setPontosVida(rs.getDouble("pontosVida"));
        criatura.setAtaque(rs.getDouble("ataque"));
        criatura.setDefesa(rs.getDouble("defesa"));
        criatura.setMaxPontosVida(rs.getDouble("maxPontosVida"));
        criatura.setBarraAtaque(rs.getDouble("barraAtaque"));
        criatura.setEsquiva(rs.getInt("esquiva"));
        criatura.setVelocidade(rs.getDouble("velocidade"));
        
        return criatura;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.JDBC;

import Model.Acao;
import Model.DAO.*;
import Model.Habilidades.Dummy;
import Model.Habilidades.HabilidadeBase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juliano Felipe da Silva
 */
public class JDBCHabilidadeDAO extends JDBCAbstractDAO implements HabilidadeDAO {
    private static StringBuilder QUERY = new StringBuilder();
    
    @Override
    public int inserir(HabilidadeBase t) throws DatabaseException {
        QUERY.append("INSERT INTO HabilidadeBase (tipo,nome,")
             .append("tempoRecarregamento,descricao) ")
             .append("VALUES (?,?,?,?)");
        
        PreparedStatement pst = null;
        int nextId =-1;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            pst.setInt(1, t.getTipo().getValor());
            pst.setString(2, t.getNome());
            pst.setInt(3, t.getTempoRecarregamento());
            pst.setString(4, t.getDescricao());
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
    public boolean remover(HabilidadeBase t) throws DatabaseException {
        QUERY.append("DELETE FROM HabilidadeBase")
             .append("WHERE habilidadeId=").append(t.getHabilidadeId());

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
    public boolean atualizar(HabilidadeBase t) throws DatabaseException {
        QUERY.append("UPDATE HabilidadeBase SET tipo=?, SET nome=?, ")
             .append("SET tempoRecarregamento=?, SET descricao=? ")
             .append("WHERE itemId=").append(t.getHabilidadeId());

        PreparedStatement pst = null;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            pst.setInt(1, t.getTipo().getValor());
            pst.setString(2, t.getNome());
            pst.setInt(3, t.getProgressoRecarregamento());
            pst.setInt(4, t.getTempoRecarregamento());
            pst.setString(5, t.getDescricao());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HabilidadeBase> buscar(String nome) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
    private HabilidadeBase getInstance(ResultSet rs) throws SQLException{
        HabilidadeBase habilidade = new Dummy(null); //Arma para poder instanciar
        
        habilidade.setTipo(Acao.porCodigo( rs.getInt("tipo") ));
        habilidade.setNome(rs.getString("nome"));
        habilidade.setTempoRecarregamento(rs.getInt("tempoRecarregamento"));
        habilidade.setDescricao(rs.getString("descricao"));
        
        return habilidade;
    }

    @Override
    public int checarSeNoBanco(HabilidadeBase t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.JDBC;

import Model.Criaturas.CriaturaBase;
import Model.Criaturas.HeroisPersonalizados.Elesis;
import Model.DAO.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import Model.DAO.DatabaseException;
import Model.Habilidades.HabilidadeBase;
import java.util.ArrayList;

/**
 * {@link Model.DAO.CriaturaDAO} aplicado ao 
 * banco de dados SQLITE, identificado pela
 * opção {@link Model.DAO.DAOFactory#SQLITE}.
 * @author Juliano Felipe da Silva
 */
public class JDBCCriaturaDAO extends JDBCAbstractDAO implements CriaturaDAO {
    private static StringBuilder QUERY = new StringBuilder();
    private static final DAOFactory DAO = DAOFactory.getDAOFactory( DAOFactory.SQLITE );
    
    @Override
    public int inserir(CriaturaBase t) throws DatabaseException {
        List<HabilidadeBase> listaDeHabilidades = t.getListaDeHabilidades();

        QUERY.append("INSERT INTO CriaturaBase (nome,level,pontosVida,ataque,defesa,")
             .append("maxPontosVida,barraAtaque,esquiva,velocidade) ")
             .append("VALUES (?,?,?,?,?,?,?,?,?)");
        
        PreparedStatement pst = null;
        int criaturaId =-1;
        
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

            criaturaId = getNextId()-1;
            
            int habilidadeId;
            for (HabilidadeBase habilidade : listaDeHabilidades){
                habilidadeId = DAO.getHabilidadeDAO().insereDistinto(habilidade);
                relacionarHabilidadeCriatura(criaturaId, habilidadeId);
            }
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
        t.setCriaturaId(criaturaId);
        return criaturaId;
    }

    @Override
    public boolean remover(CriaturaBase t) throws DatabaseException {
        List<HabilidadeBase> listaDeHabilidades = t.getListaDeHabilidades();
        
        QUERY.append("DELETE FROM CriaturaBase ")
             .append("WHERE criaturaID=").append(t.getCriaturaId());

        PreparedStatement pst = null;
        
        try {
            for (HabilidadeBase habilidade : listaDeHabilidades){
                desrelacionarHabilidadeCriatura(t.getCriaturaId());
            }
            
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
    public boolean atualizar(CriaturaBase t) throws DatabaseException {
        List<HabilidadeBase> listaDeHabilidades = t.getListaDeHabilidades();
        
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

            for (HabilidadeBase habilidade : listaDeHabilidades){
                DAO.getHabilidadeDAO().remover(habilidade);
            }
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
    
    private CriaturaBase getInstance(ResultSet rs) throws SQLException, DatabaseException{
        CriaturaBase criatura = new Elesis(null); //
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
        
        criatura.setListaDeHabilidades(getListaHabilidades(criatura.getCriaturaId()));
        
        return criatura;
    }
    
    /**
     * Dado o ID de uma criatura e um id de uma
     * habilidade, estabelece que a habilidade "é" da criatura.
     * @param criaturaFK ID da criatura.
     * @param habilidadeFK ID da habilidade.
     * @return true em sucesso.
     * @throws Model.DAO.DatabaseException Em erro.
     */
    public boolean relacionarHabilidadeCriatura (int criaturaFK, int habilidadeFK) throws DatabaseException{
        //Não usa o mesmo QUERY para não bugar
        StringBuilder lQuery = new StringBuilder();
        
        lQuery.append("INSERT INTO HabilidadeCriatura (criaturaId,habilidadeId)")
             .append("VALUES (?,?)");
        
        PreparedStatement pst = null;
        
        try {
            pst = connection.prepareStatement(lQuery.toString()); // 1 a 14
            pst.setInt(1, criaturaFK);
            pst.setInt(2, habilidadeFK);
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
        return true;
    }
    
    private boolean desrelacionarHabilidadeCriatura(int criaturaFK)throws DatabaseException{
        //Não usa o mesmo QUERY para não bugar
        StringBuilder lQuery = new StringBuilder();
        
        lQuery.append("DELETE FROM HabilidadeCriatura ")
             .append("WHERE criaturaId=").append(criaturaFK);
        
        PreparedStatement pst = null;
        
        try {
            pst = connection.prepareStatement(lQuery.toString()); // 1 a 14
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
        return true;
    }

    private ArrayList<HabilidadeBase> getListaHabilidades(int criaturaPK) throws DatabaseException {
        ArrayList<HabilidadeBase> lista = new ArrayList();
        
        StringBuilder lQuery = new StringBuilder();
        lQuery.append("SELECT habilidadeId FROM HabilidadeCriatura ")
             .append("WHERE criaturaId=").append(criaturaPK);
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            rs = pst.executeQuery();
            
            while (rs.next()){
                lista.add( DAO.getHabilidadeDAO().buscar(rs.getInt("habilidadeId")));
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
        
        return lista;
    }
}

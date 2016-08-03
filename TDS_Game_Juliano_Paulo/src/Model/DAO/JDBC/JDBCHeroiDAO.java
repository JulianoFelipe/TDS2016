/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.JDBC;

import Model.DAO.*;
import Model.Criaturas.Heroi;
import Model.Criaturas.Mago;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link Model.DAO.HeroiDAO} aplicado ao 
 * banco de dados SQLITE, identificado pela
 * opção {@link Model.DAO.DAOFactory#SQLITE}.
 * @author Juliano Felipe da Silva
 */
public class JDBCHeroiDAO extends JDBCAbstractDAO implements HeroiDAO {
    private static StringBuilder QUERY = new StringBuilder();
    private static final DAOFactory DAO = DAOFactory.getDAOFactory( DAOFactory.SQLITE );
    private static final CriaturaDAO CDAO = DAO.getCriaturaDAO();
    
    @Override
    public int inserir(Heroi t) throws DatabaseException {
        int criaturaId = CDAO.inserir(t);
        int armaId=-1;
        if (t.getArma() != null)
            armaId = DAO.getArmaDAO().inserir(t.getArma());
        int armaduraId=-1;
        if (t.getArmadura() != null)
            armaduraId = DAO.getArmaduraDAO().inserir(t.getArmadura());  
        
        QUERY.append("INSERT INTO Heroi (criaturaId,xxp_nivel,multiplicadorPontosVida,multiplicadorVelocidade")
             .append(",multiplicadorAtaque,multiplicadorDefesa,incrementoPV,incrementoVelocidade,")
             .append("incrementoAtaque,incrementoDefesa,xpAtual,requerimentoXp,")
             .append("armadura,arma) ")
             .append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        PreparedStatement pst = null;
        int nextId =-1;
        
        try {
            pst = connection.prepareStatement(QUERY.toString()); // 1 a 14
            pst.setInt(1, criaturaId);
            pst.setDouble(2,  Heroi.getXP_LV_MULTIPLIER());
            pst.setDouble(3,  t.getMaxPontosVida());
            pst.setDouble(4,  t.getMultiplicadorVelocidade());
            pst.setDouble(5,  t.getMultiplicadorAtaque());
            pst.setDouble(6,  t.getMultiplicadorDefesa());
            pst.setDouble(7,  t.getIncrementoPV());
            pst.setDouble(8,  t.getIncrementoVelocidade());
            pst.setDouble(9,  t.getIncrementoAtaque());
            pst.setDouble(10,  t.getIncrementoDefesa());
            pst.setDouble(11, t.getXpAtual());
            pst.setDouble(12, t.getRequerimentoXp());
            pst.setInt(13, armaduraId);
            pst.setInt(14, armaId);
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
        t.setHeroiId(nextId-1);
        return nextId-1;
    }

    @Override
    public boolean remover(Heroi t) throws DatabaseException {
        boolean rmCriatura = CDAO.remover(t);
        if (!rmCriatura) throw new DatabaseException("Retorno falso ao deletar criaturaTable Pai");
        
        QUERY.append("DELETE FROM Heroi ")
             .append("WHERE heroiId=").append(t.getHeroiId());

        PreparedStatement pst = null;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            pst.executeUpdate();
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
    public boolean atualizar(Heroi t) throws DatabaseException {
        boolean atualizaCriatura = CDAO.atualizar(t);
        if (!atualizaCriatura) throw new DatabaseException("Retorno falso ao atualizar criaturaTable Pai");
        boolean atualizaArma = DAO.getArmaDAO().atualizar(t.getArma());
        if (!atualizaArma) throw new DatabaseException("Retorno falso ao atualizar arma do Herói");
        boolean atualizaArmadura = DAO.getArmaduraDAO().atualizar(t.getArmadura());
        if (!atualizaArmadura) throw new DatabaseException("Retorno falso ao atualizar armadura do Herói");
        
        QUERY.append("UPDATE Heroi SET xxp_nivel=?, SET multiplicadorPontosVida=?, ")
             .append("SET multiplicadorVelocidade=?, SET multiplicadorAtaque=?, ")
             .append("SET multiplicadorDefesa=?, SET incrementoPV=?, SET incrementoVelocidade=?, ")
             .append("SET incrementoAtaque=?, SET incrementoDefesa=?, SET xpAtual=?, SET requerimentoXp=? ")
             .append("WHERE heroiId=").append(t.getHeroiId());

        PreparedStatement pst = null;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            pst.setDouble(1, Heroi.getXP_LV_MULTIPLIER());
            pst.setDouble(2, t.getMultiplicadorPontosVida());
            pst.setDouble(3, t.getMultiplicadorVelocidade());
            pst.setDouble(4, t.getMultiplicadorAtaque());
            pst.setDouble(5, t.getMultiplicadorDefesa());
            pst.setDouble(6, t.getIncrementoPV());
            pst.setDouble(7, t.getIncrementoVelocidade());
            pst.setDouble(8, t.getIncrementoAtaque());
            pst.setDouble(9, t.getIncrementoDefesa());
            pst.setDouble(10, t.getXpAtual());
            pst.setDouble(11, t.getRequerimentoXp());
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
    public List<Heroi> resgatarTodos() throws DatabaseException {
        QUERY.append("SELECT * FROM CriaturaBase, Heroi ")
             .append("WHERE Heroi.criaturaId = CriaturaBase.criaturaID");

        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Heroi> lista = new ArrayList();
        
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
    public Heroi buscar(int primaryKey) throws DatabaseException {
        QUERY.append("SELECT * FROM CriaturaBase, Heroi ")
             .append("WHERE Heroi.criaturaId=CriaturaBase.criaturaID ")
             .append("AND Heroi.heroiId=").append(primaryKey);

        PreparedStatement pst = null;
        ResultSet rs = null;
        
        Heroi heroi = null;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            rs = pst.executeQuery();
            
            if (rs.next()){
                heroi = getInstance(rs);
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
        return heroi;
    }

    @Override
    public List<Heroi> buscar(String nome) throws DatabaseException {
        QUERY.append("SELECT * FROM CriaturaBase, Heroi ")
             .append("WHERE Heroi.criaturaId = CriaturaBase.criaturaID")
             .append("AND CriaturaBase.nome=\"").append(nome).append("\"");

        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Heroi> lista = new ArrayList();
        
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
    public boolean salvar(Heroi salvar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNextId() throws SQLException {
        String query = "SELECT heroiId FROM Heroi";
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        
        int lastId=-1;
        while (rs.next()){
            lastId = rs.getInt("heroiId");
        }
        return lastId+1;
    }
    
    private Heroi getInstance(ResultSet rs) throws SQLException{
        Heroi heroi = new Mago(null); //Fazendo mago por nÃ£o
        //poder instanciar criaturaBase  -- Gambi meio... loca.
        
        heroi.setCriaturaId( rs.getInt("criaturaID"));
        heroi.setNome(rs.getString("nome"));
        heroi.setLevel(rs.getInt("level"));
        heroi.setPontosVida(rs.getDouble("pontosVida"));
        heroi.setAtaque(rs.getDouble("ataque"));
        heroi.setDefesa(rs.getDouble("defesa"));
        heroi.setMaxPontosVida(rs.getDouble("maxPontosVida"));
        heroi.setBarraAtaque(rs.getDouble("barraAtaque"));
        heroi.setEsquiva(rs.getInt("esquiva"));
        heroi.setVelocidade(rs.getDouble("velocidade"));
        
        heroi.setMultiplicadorPontosVida(rs.getDouble("multiplicadorPontosVida"));
        heroi.setMultiplicadorVelocidade(rs.getDouble("multiplicadorVelocidade"));
        heroi.setMultiplicadorAtaque(rs.getDouble("multiplicadorAtaque"));
        heroi.setMultiplicadorDefesa(rs.getDouble("multiplicadorDefesa"));
        heroi.setIncrementoPV(rs.getDouble("incrementoPV"));
        heroi.setIncrementoVelocidade(rs.getDouble("incrementoVelocidade"));
        heroi.setIncrementoAtaque(rs.getDouble("incrementoAtaque"));
        heroi.setIncrementoDefesa(rs.getDouble("incrementoDefesa"));

        heroi.setXpAtual(rs.getDouble("xpAtual"));
        heroi.setRequerimentoXp(rs.getDouble("requerimentoXp"));
        return heroi;
    }
}

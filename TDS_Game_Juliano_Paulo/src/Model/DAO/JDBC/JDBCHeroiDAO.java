/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.JDBC;

import Model.Criaturas.CriaturaBase;
import Model.DAO.*;
import Model.Criaturas.Heroi;
import Model.Criaturas.Mago;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Juliano Felipe da Silva
 */
public class JDBCHeroiDAO extends JDBCAbstractDAO implements HeroiDAO {
    private static StringBuilder QUERY = new StringBuilder();
    private static final DAOFactory DAO = DAOFactory.getDAOFactory( DAOFactory.SQLITE );
    private static final CriaturaDAO CDAO = DAO.getCriaturaDAO();
    
    @Override
    public int inserir(Heroi t) throws DatabaseException {
        int criaturaId = CDAO.inserir(t);
        int armaId = DAO.getArmaDAO().inserir(t.getArma());
        int armaduraId = DAO.getArmaduraDAO().inserir(t.getArmadura());  
        
        QUERY.append("INSERT INTO Heroi (criaturaId,xxp,multiplicadorPontosVida,multiplicadorVelocidade")
             .append(",multiplicadorAtaque,multiplicadorDefesa,incrementoPV,incrementoVelocidade")
             .append("incrementoAtaque,incrementoDefesa,xpAtual,requerimentoXp")
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
        return nextId-1;
    }

    @Override
    public boolean remover(Heroi t) throws DatabaseException {
        boolean rmCriatura = CDAO.remover(t);
        if (!rmCriatura) throw new DatabaseException("Retorno falso ao deletar criaturaTable Pai");
        
        QUERY.append("DELETE FROM Heroi")
             .append("WHERE heroiId=").append(t.getHeroiId());

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
    public boolean atualizar(Heroi t) throws DatabaseException {
        boolean rmCriatura = CDAO.atualizar(t);
        if (!rmCriatura) throw new DatabaseException("Retorno falso ao atualizar criaturaTable Pai");
        
        return false;
    }

    @Override
    public List<Heroi> resgatarTodos() throws DatabaseException {
        /*List<Heroi> rmCriatura = cdao.resgatarTodos();
        if (!rmCriatura) throw new DatabaseException("Retorno falso ao deletar criaturaTable Pai");
        */
        return null;
    }

    @Override
    public Heroi buscar(int primaryKey) throws DatabaseException {
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
        
        int FKContidanoHeroi = 1;
        criatura = CDAO.buscar(FKContidanoHeroi);
        
        return null;
    }

    @Override
    public List<Heroi> buscar(String nome) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public List<Heroi> jogadorFK(int foreignKey) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Heroi getInstance(ResultSet rs) throws SQLException{
        //Heroi heroi = new Mago(); //Fazendo mago por nÃ£o
        //poder instanciar criaturaBase  -- Gambi meio... loca.
        
        //heroi.setCriatur
        
        return null;
    }
}

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
import Model.Itens.Constantes.Raridade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Juliano Felipe da Silva
 */
public class JDBCArmaduraDAO extends JDBCAbstractDAO implements ArmaduraDAO{
    private static StringBuilder QUERY = new StringBuilder();
    private static final DAOFactory dao = DAOFactory.getDAOFactory( DAOFactory.SQLITE );
    
    @Override
    public int inserir(ArmaduraBase t) throws DatabaseException {
        int itemId = dao.getItemDAO().inserir(t);
        
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
        return nextId-1;
    }

    @Override
    public boolean remover(ArmaduraBase t) throws DatabaseException {
        boolean rmItem = dao.getItemDAO().remover(t);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ArmaduraBase> resgatarTodos() throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArmaduraBase buscar(int primaryKey) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ArmaduraBase> buscar(String nome) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        ArmaduraBase armadura = new ArmaduraBase();
        armadura.setArmaduraId( rs.getInt( "armaduraId" ));
        armadura.setTipo( Armaduras.porCodigo( rs.getInt( "tipo" ) ));
        armadura.setLevel( rs.getInt( "level" ));
        armadura.setIncrementoDefesa(rs.getDouble( "incrementoDefesa" ));
        armadura.setRaridade( Raridade.porCodigo( rs.getInt( "raridade" ) ));
        //arma.setModificador( Modificador.porCodigo( rs.getInt( "modificador" ) ));
        
        return armadura;
    }

    @Override
    public List<ArmaduraBase> itemFK(int foreignKey) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

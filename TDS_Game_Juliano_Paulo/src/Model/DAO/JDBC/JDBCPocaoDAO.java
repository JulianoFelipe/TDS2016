/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.JDBC;

import Model.DAO.DAOFactory;
import Model.DAO.DatabaseException;
import Model.DAO.PocaoDAO;
import Model.Itens.PocaoAumentoStatus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Juliano Felipe da Silva
 */
public class JDBCPocaoDAO extends JDBCAbstractDAO implements PocaoDAO {
    private static StringBuilder QUERY = new StringBuilder();
    private static final DAOFactory dao = DAOFactory.getDAOFactory( DAOFactory.SQLITE );
    
    @Override
    public int inserir(PocaoAumentoStatus t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remover(PocaoAumentoStatus t) throws DatabaseException {
        boolean rmItem = dao.getItemDAO().remover(t);
        if (!rmItem) throw new DatabaseException("Retorno falso ao deletar itemTable Pai");
        
        QUERY.append("DELETE FROM ArmaduraBase")
             .append("WHERE armaduraId=").append(t.getPocaoId());

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
    public boolean atualizar(PocaoAumentoStatus t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PocaoAumentoStatus> resgatarTodos() throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PocaoAumentoStatus buscar(int primaryKey) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PocaoAumentoStatus> buscar(String nome) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNextId() throws SQLException {
        String query = "SELECT pocaoId FROM PocaoAumentoStatus";
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        
        int lastId=-1;
        while (rs.next()){
            lastId = rs.getInt("pocaoId");
        }
        return lastId+1;
    }
    
    private PocaoAumentoStatus getInstance(ResultSet rs) throws SQLException {
        
        /*        Double
        pocao.setArmaduraId( rs.getInt( "armaduraId" ));
        pocao.setTipo( Armaduras.porCodigo( rs.getInt( "tipo" ) ));
        pocao.setLevel( rs.getInt( "level" ));
        pocao.setIncrementoDefesa(rs.getDouble( "incrementoDefesa" ));
        pocao.setRaridade( Raridade.porCodigo( rs.getInt( "raridade" ) ));
        //arma.setModificador( Modificador.porCodigo( rs.getInt( "modificador" ) ));
        
        PocaoAumentoStatus pocao = new PocaoAumentoStatus();*/
        return null;
    }

    @Override
    public List<PocaoAumentoStatus> itemFK(int foreignKey) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

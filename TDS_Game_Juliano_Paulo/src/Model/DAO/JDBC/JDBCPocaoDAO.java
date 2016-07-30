/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.JDBC;

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

    @Override
    public int inserir(PocaoAumentoStatus t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remover(PocaoAumentoStatus t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

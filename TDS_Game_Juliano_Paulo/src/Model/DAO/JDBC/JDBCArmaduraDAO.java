/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.JDBC;

import Model.DAO.ArmaduraDAO;
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

    @Override
    public int inserir(ArmaduraBase t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remover(ArmaduraBase t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
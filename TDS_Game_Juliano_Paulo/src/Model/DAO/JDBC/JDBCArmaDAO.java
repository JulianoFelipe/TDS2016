/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.JDBC;

import Model.DAO.ArmaDAO;
import Model.DAO.DatabaseException;
import Model.Itens.ArmaBase;
import Model.Itens.Constantes.Armas;
import Model.Itens.Constantes.Raridade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Juliano Felipe da Silva
 */
public class JDBCArmaDAO extends JDBCAbstractDAO implements ArmaDAO {

    @Override
    public int inserir(ArmaBase t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remover(ArmaBase t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizar(ArmaBase t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ArmaBase> resgatarTodos() throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArmaBase buscar(int primaryKey) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ArmaBase> buscar(String nome) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNextId() throws SQLException {
        String query = "SELECT armaId FROM ArmaBase";
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        
        int lastId=-1;
        while (rs.next()){
            lastId = rs.getInt("armaId");
        }
        return lastId+1;
    }

    /**
     *  TEM QUE DAR JOIN PARA PODER CONSULTAR TUDO.
     *  TIPO DADOS DE ITEMBASE E ETC.
     * @param rs
     * @return
     * @throws SQLException 
     */
    private ArmaBase getInstance(ResultSet rs) throws SQLException {
        ArmaBase arma = new ArmaBase();
        //arma.setItemId(itemId);
        //arma.setJogador(null);
        //arma.setOwner(null);
        
        arma.setArmaId( rs.getInt( "armaId" ));
        arma.setItemId( rs.getInt( "itemId" ));
        arma.setTipo( Armas.porCodigo( rs.getInt( "tipo" ) ));
        arma.setLevel( rs.getInt( "level" ));
        arma.setIncrementoDano( rs.getDouble( "incrementoDano" ));
        arma.setRaridade( Raridade.porCodigo( rs.getInt( "raridade" ) ));
        //arma.setModificador( Modificador.porCodigo( rs.getInt( "modificador" ) ));
        
        return arma;
    }

    @Override
    public List<ArmaBase> itemFK(int foreignKey) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

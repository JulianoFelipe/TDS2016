/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.JDBC;

import Model.DAO.ArmaDAO;
import Model.DAO.DAOFactory;
import Model.DAO.DatabaseException;
import Model.Itens.ArmaBase;
import Model.Itens.Constantes.Armas;
import Model.Itens.Constantes.Modificador;
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
    private static StringBuilder QUERY = new StringBuilder();
    private static final DAOFactory dao = DAOFactory.getDAOFactory( DAOFactory.SQLITE );
    
    @Override
    public int inserir(ArmaBase t) throws DatabaseException {
        int itemId = dao.getItemDAO().inserir(t);
        
        QUERY.append("INSERT INTO ArmaBase (itemId,tipo,level,incrementoDano")
             .append(",raridade,modificador) ")
             .append("VALUES (?,?,?,?,?,?)");
        
        PreparedStatement pst = null;
        int nextId =-1;
        
        try {
            pst = connection.prepareStatement(QUERY.toString()); // 1 a 14
            pst.setInt(1, itemId);
            pst.setInt(2,  t.getTipo().getValor());
            pst.setInt(3,  t.getLevel());
            pst.setDouble(4,  t.getIncrementoDano());
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
    public boolean remover(ArmaBase t) throws DatabaseException {
        boolean rmItem = dao.getItemDAO().remover(t);
        if (!rmItem) throw new DatabaseException("Retorno falso ao deletar itemTable Pai");
        
        QUERY.append("DELETE FROM ArmaBase")
             .append("WHERE armaId=").append(t.getArmaId());

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
        ArmaBase arma = new ArmaBase(rs.getDouble( "incrementoDano" ));
        //arma.setItemId(itemId);
        //arma.setJogador(null);
        //arma.setOwner(null);
        
        arma.setArmaId( rs.getInt( "armaId" ));
        arma.setItemId( rs.getInt( "itemId" ));
        arma.setTipo( Armas.porCodigo( rs.getInt( "tipo" ) ));
        arma.setLevel( rs.getInt( "level" ));
        arma.setRaridade( Raridade.porCodigo( rs.getInt( "raridade" ) ));
        //arma.setModificador( Modificador.porCodigo( rs.getInt( "modificador" ) ));
        
        return arma;
    }

    @Override
    public List<ArmaBase> itemFK(int foreignKey) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args) throws SQLException, DatabaseException {
        ArmaBase armateste = new ArmaBase(1.0);
        armateste.setLevel(0);
        armateste.setModificador(Modificador.Nenhum);
        armateste.setNome("Blah");
        armateste.setRaridade(Raridade.Branca);
        armateste.setTipo(Armas.Espada);
        armateste.setValor(0);
        
        int lastId = dao.getArmaDAO().inserir(armateste);
        System.out.println(lastId);
    }

}

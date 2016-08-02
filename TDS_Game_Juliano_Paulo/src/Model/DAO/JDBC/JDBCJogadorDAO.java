/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.JDBC;

import Model.Criaturas.Heroi;
import Model.Criaturas.Jogador;
import Model.Criaturas.Mago;
import Model.DAO.DAOFactory;
import Model.DAO.DatabaseException;
import Model.DAO.HeroiDAO;
import Model.DAO.ItemDAO;
import Model.DAO.JogadorDAO;
import Model.Itens.ArmaBase;
import Model.Itens.ItemBase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Juliano Felipe da Silva
 */
public class JDBCJogadorDAO extends JDBCAbstractDAO implements JogadorDAO {
    private static StringBuilder QUERY = new StringBuilder();
    private static final DAOFactory DAO = DAOFactory.getDAOFactory( DAOFactory.SQLITE );
    private static final HeroiDAO HDAO = DAO.getHeroiDAO();
    private static final ItemDAO IDAO = DAO.getItemDAO();
    
    @Override
    public int inserir(Jogador t) throws DatabaseException {
        List<Heroi> listaDeHerois = t.getLista_de_herois();
        List<ItemBase> listaDeItens = t.getInventario();
        
        QUERY.append("INSERT INTO Jogador (nome,ouro)")
             .append("VALUES (?,?)");
        
        PreparedStatement pst = null;
        int jogadorId =-1;
        
        try {
            pst = connection.prepareStatement(QUERY.toString()); // 1 a 14
            pst.setString(1, t.getNome());
            pst.setDouble(2, t.getGold());
            pst.execute();
            
            jogadorId = getNextId() - 1;
            
            int heroiId;
            for (Heroi heroi : listaDeHerois){
                heroiId = HDAO.inserir(heroi);
                relacionarHeroiJogador(jogadorId, heroiId);
            }
            
            int itemId;
            for (ItemBase item : listaDeItens){
                itemId = IDAO.inserir(item);
                relacionarItemJogador(jogadorId, itemId);
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
        t.setJogadorId(jogadorId);
        return jogadorId;
    }

    @Override
    public boolean remover(Jogador t) throws DatabaseException {
        //Remover todos os itens e herois associados
        
        QUERY.append("DELETE FROM Jogador ")
             .append("WHERE jogadorId=").append(t.getJogadorId());
        
        PreparedStatement pst = null;
        
        try {
            List<Heroi> listaDeHerois = t.getLista_de_herois();
            List<ItemBase> listaDeItens = t.getInventario();
            for (Heroi heroi : listaDeHerois){
                HDAO.remover(heroi);
                desrelacionarHeroiJogador(t.getJogadorId());
            }
            for (ItemBase item : listaDeItens){
                IDAO.remover(item);
                desrelacionarItemJogador(t.getJogadorId());
            }
            
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
    public boolean atualizar(Jogador t) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Jogador> resgatarTodos() throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jogador buscar(int primaryKey) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Jogador> buscar(String nome) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNextId() throws SQLException {
        String query = "SELECT jogadorId FROM Jogador";
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        
        int lastId=-1;
        while (rs.next()){
            lastId = rs.getInt("jogadorId");
        }
        return lastId+1;
    }

    @Override
    public List<Heroi> getListaDeHerois(int primaryKey) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Dado o ID de um jogador e um id de um
     * herói, estabelece que o herói "é" do jogador.
     * @param jogadorPK ID do jogador.
     * @param heroiPK ID do heroi.
     * @return true em sucesso.
     */
    private boolean relacionarHeroiJogador(int jogadorPK, int heroiPK) throws DatabaseException{
        //Não usa o mesmo QUERY para não bugar
        StringBuilder lQuery = new StringBuilder();
        
        lQuery.append("INSERT INTO JogadorHeroi (jogadorId,heroiId)")
             .append("VALUES (?,?)");
        
        PreparedStatement pst = null;
        
        try {
            pst = connection.prepareStatement(lQuery.toString()); // 1 a 14
            pst.setInt(1, jogadorPK);
            pst.setInt(2, heroiPK);
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
    
    /**
     * Dado o ID de um jogador e um id de um
     * item, estabelece que o item "é" do jogador.
     * @param jogadorPK ID do jogador.
     * @param itemPK ID do item.
     * @return true em sucesso.
     */
    private boolean relacionarItemJogador(int jogadorPK, int itemPK)throws DatabaseException{
        //Não usa o mesmo QUERY para não bugar
        StringBuilder lQuery = new StringBuilder();
        
        lQuery.append("INSERT INTO ItemJogador (jogadorId,itemId)")
             .append("VALUES (?,?)");
        
        PreparedStatement pst = null;
        
        try {
            pst = connection.prepareStatement(lQuery.toString()); // 1 a 14
            pst.setInt(1, jogadorPK);
            pst.setInt(2, itemPK);
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
    
    private boolean desrelacionarItemJogador(int jogadorPK)throws DatabaseException{
        //Não usa o mesmo QUERY para não bugar
        StringBuilder lQuery = new StringBuilder();
        
        lQuery.append("DELETE FROM ItemJogador ")
             .append("WHERE jogadorId=").append(jogadorPK);
        
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
    
    private boolean desrelacionarHeroiJogador(int jogadorPK)throws DatabaseException{
        //Não usa o mesmo QUERY para não bugar
        StringBuilder lQuery = new StringBuilder();
        
        lQuery.append("DELETE FROM JogadorHeroi ")
             .append("WHERE jogadorId=").append(jogadorPK);
        
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
    
    public static void main(String[] args) throws DatabaseException {
        Jogador j = new Jogador();
        Heroi h = new Mago(j);
        ItemBase i = new ArmaBase(10.0);
        i.setNome("Item i");
        i.setValor(10);
        j.addItem(i);
        j.getLista_de_herois().add(h);
        
        int inserir = DAO.getJogadorDAO().inserir(j);
        j.setJogadorId(inserir);
        DAO.getJogadorDAO().remover(j);
    }
}

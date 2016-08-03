/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.JDBC;

import Model.Criaturas.Heroi;
import Model.Criaturas.Jogador;
import Model.DAO.DAOFactory;
import Model.DAO.DatabaseException;
import Model.DAO.HeroiDAO;
import Model.DAO.ItemDAO;
import Model.DAO.JogadorDAO;
import Model.Itens.ArmaBase;
import Model.Itens.ArmaduraBase;
import Model.Itens.ItemBase;
import Model.Itens.PergaminhoHabilidade;
import Model.Itens.PocaoAumentoStatus;
import static Model.DAO.JDBC.TipoItem.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
                TipoItem tipo = TipoItem.porInstancia(item);
                if (tipo == TipoItem.ArmaBase) itemId = DAO.getArmaDAO().inserir((ArmaBase)item);
                else if (tipo == TipoItem.ArmaduraBase) itemId = DAO.getArmaduraDAO().inserir((ArmaduraBase)item);
                else if (tipo == TipoItem.Pergaminho) itemId = DAO.getPergaminhoDAO().inserir((PergaminhoHabilidade)item);
                else if (tipo == TipoItem.Pocao) itemId = DAO.getPocaoDAO().inserir((PocaoAumentoStatus)item);
                else itemId = IDAO.inserir(item);
                relacionarItemJogador(jogadorId, itemId, tipo);
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
                    TipoItem tipo = TipoItem.porInstancia(item);
                         if (tipo == ArmaBase) DAO.getArmaDAO().remover((ArmaBase)item);
                    else if (tipo == ArmaduraBase) DAO.getArmaduraDAO().remover((ArmaduraBase)item);
                    else if (tipo == Pergaminho) DAO.getPergaminhoDAO().remover((PergaminhoHabilidade)item);
                    else if (tipo == Pocao) DAO.getPocaoDAO().remover((PocaoAumentoStatus)item);
                    else IDAO.remover(item);
                
                
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
        QUERY.append("SELECT * FROM Jogador");

        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Jogador> lista = new ArrayList();
        
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
    public Jogador buscar(int primaryKey) throws DatabaseException {
        QUERY.append("SELECT * FROM Jogador ")
             .append("WHERE jogadorId=").append(primaryKey);

        PreparedStatement pst = null;
        ResultSet rs = null;
        Jogador jogador = null;
        
        try {
            pst = connection.prepareStatement(QUERY.toString());
            rs = pst.executeQuery();
            
            if (rs.next()){
                jogador = getInstance(rs);
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
        return jogador;
    }

    @Override
    public List<Jogador> buscar(String nome) throws DatabaseException {
        QUERY.append("SELECT * FROM Jogador WHERE nome=\"")
             .append(nome).append("\"");

        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Jogador> lista = new ArrayList();
        
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
    public List<Heroi> getListaDeHerois(int primaryKey) throws DatabaseException{
        List<Heroi> herois = new ArrayList();
        
        StringBuilder lQuery = new StringBuilder();
        lQuery.append("SELECT heroiId FROM JogadorHeroi ")
              .append("WHERE jogadorId=").append(primaryKey);
        
        PreparedStatement pst = null;
        ResultSet inRS = null;
        try {
            pst = connection.prepareStatement(lQuery.toString()); // 1 a 14
            inRS = pst.executeQuery();
            while (inRS.next()){
                Heroi heroi = DAO.getHeroiDAO().buscar(inRS.getInt("heroiId"));
                herois.add(heroi);
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
        return herois;
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
     * @param TipoItem do item a ser relacionado.
     * @return true em sucesso.
     */
    private boolean relacionarItemJogador(int jogadorPK, int itemPK, TipoItem tipo)throws DatabaseException{
        //Não usa o mesmo QUERY para não bugar
        StringBuilder lQuery = new StringBuilder();
        
        lQuery.append("INSERT INTO ItemJogador (jogadorId,itemId,tipoItem)")
             .append("VALUES (?,?,?)");
        
        PreparedStatement pst = null;
        
        try {
            pst = connection.prepareStatement(lQuery.toString()); // 1 a 14
            pst.setInt(1, jogadorPK);
            pst.setInt(2, itemPK);
            pst.setInt(3, tipo.getValor());
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
    
    private Jogador getInstance(ResultSet rs) throws DatabaseException, SQLException{
        Jogador j = new Jogador();
        j.setNome( rs.getString("nome"));
        j.setGold( rs.getInt("ouro"));
        j.setJogadorId( rs.getInt ("jogadorId"));
        
        StringBuilder lQuery = new StringBuilder();
        lQuery.append("SELECT heroiId FROM JogadorHeroi ")
              .append("WHERE jogadorId=").append(j.getJogadorId());
        StringBuilder sQuery = new StringBuilder();
        sQuery.append("SELECT * FROM ItemJogador ")
              .append("WHERE jogadorId=").append(j.getJogadorId());
        
        PreparedStatement pst = null;
        ResultSet inRS = null;
        try {
            pst = connection.prepareStatement(lQuery.toString()); // 1 a 14
            inRS = pst.executeQuery();
            while (inRS.next()){
                Heroi heroi = DAO.getHeroiDAO().buscar(inRS.getInt("heroiId"));
                heroi.setJogador(j);
                j.getLista_de_herois().add(heroi);
            }
            
            pst = connection.prepareStatement(sQuery.toString());
            inRS = pst.executeQuery();
            while (inRS.next()){
                TipoItem tipo = TipoItem.porCodigo(inRS.getInt("tipoItem"));
                int itemId = inRS.getInt("itemId");
                if (tipo == TipoItem.ArmaBase) j.addItem( DAO.getArmaDAO().buscar(itemId));
                else if (tipo == TipoItem.ArmaduraBase) j.addItem( DAO.getArmaduraDAO().buscar(itemId));
                else if (tipo == TipoItem.Pergaminho) j.addItem( DAO.getPergaminhoDAO().buscar(itemId));
                else if (tipo == TipoItem.Pocao) j.addItem( DAO.getPocaoDAO().buscar(itemId));
                else j.addItem( IDAO.buscar(itemId));
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
        
        return j;
    }
    

}

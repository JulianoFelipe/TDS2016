/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.DAO.JDBC.JDBCDAOFactory;

/**
 *
 * @author Juliano Felipe da Silva
 */
public abstract class DAOFactory {
    /**
     * Código referente ao DAOFactory
     * no banco de dados SQLITE.
     */
    public static final int SQLITE = 1;
    
    /**
     * Retorna um DAOFactory de um dado
     * tipo identificado por um código.
     * <p>
     * As opções disponíveis são visíveis
     * e estáticas na classe. Como:
     * {@link Model.DAO.DAOFactory#SQLITE}.
     * @param opcao identificando o tipo.
     * @return DAOFactory correspondente ao
     *         código ou null em inválido.
     */
    public static DAOFactory getDAOFactory(int opcao) {
        switch (opcao ) {
            case SQLITE : return new JDBCDAOFactory();
            default: return null;
        }
    }
    
    /**
     * DAO referente à operações sob
     * a classe:
     * {@link Model.Itens.ArmaBase}.
     * @return DAO correspondente dependendo
     *         do código do DAOFactory.
     */
    public abstract ArmaDAO getArmaDAO();
    
    /**
     * DAO referente à operações sob
     * a classe:
     * {@link Model.Itens.ArmaduraBase}.
     * @return DAO correspondente dependendo
     *         do código do DAOFactory.
     */
    public abstract ArmaduraDAO getArmaduraDAO();
    
    /**
     * DAO referente à operações sob
     * a classe:
     * {@link Model.Criaturas.CriaturaBase}.
     * @return DAO correspondente dependendo
     *         do código do DAOFactory.
     */
    public abstract CriaturaDAO getCriaturaDAO();
    
    /**
     * DAO referente à operações sob
     * a classe:
     * {@link Model.Habilidades.HabilidadeBase}.
     * @return DAO correspondente dependendo
     *         do código do DAOFactory.
     */
    public abstract HabilidadeDAO getHabilidadeDAO();
    
    /**
     * DAO referente à operações sob
     * a classe:
     * {@link Model.Criaturas.Heroi}.
     * @return DAO correspondente dependendo
     *         do código do DAOFactory.
     */
    public abstract HeroiDAO getHeroiDAO();
    
    /**
     * DAO referente à operações sob
     * a classe:
     * {@link Model.Itens.ItemBase}.
     * @return DAO correspondente dependendo
     *         do código do DAOFactory.
     */
    public abstract ItemDAO getItemDAO();
    
    /**
     * DAO referente à operações sob
     * a classe:
     * {@link Model.Criaturas.Jogador}.
     * @return DAO correspondente dependendo
     *         do código do DAOFactory.
     */
    public abstract JogadorDAO getJogadorDAO();
    
    /**
     * DAO referente à operações sob
     * a classe:
     * {@link Model.Itens.PergaminhoHabilidade}.
     * @return DAO correspondente dependendo
     *         do código do DAOFactory.
     */
    public abstract PergaminhoDAO getPergaminhoDAO();
    
    /**
     * DAO referente à operações sob
     * a classe:
     * {@link Model.Itens.PocaoAumentoStatus}.
     * @return DAO correspondente dependendo
     *         do código do DAOFactory.
     */
    public abstract PocaoDAO getPocaoDAO();
}

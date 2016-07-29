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
    public static final int SQLITE = 1;
    
    public static DAOFactory getDAOFactory(int opcao) {
        switch (opcao ) {
            case SQLITE : return new JDBCDAOFactory();
            default: return null;
        }
    }
    
    public abstract ArmaDAO getArmaDAO();
    
    public abstract ArmaduraDAO getArmaduraDAO();
    
    public abstract CriaturaDAO getCriaturaDAO();
    
    public abstract EfeitoDAO getEfeitoDAO();
    
    public abstract HabilidadeDAO getHabilidadeDAO();
    
    public abstract HeroiDAO getHeroiDAO();
    
    public abstract ItemDAO getItemDAO();
    
    public abstract JogadorDAO getJogadorDAO();
    
    public abstract PergaminhoDAO getPergaminhoDAO();
    
    public abstract PocaoDAO getPocaoDAO();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.JDBC;

import Model.DAO.DAOFactory;
import Model.DAO.ArmaDAO;
import Model.DAO.ArmaduraDAO;
import Model.DAO.CriaturaDAO;
import Model.DAO.HabilidadeDAO;
import Model.DAO.HeroiDAO;
import Model.DAO.ItemDAO;
import Model.DAO.JogadorDAO;
import Model.DAO.PergaminhoDAO;
import Model.DAO.PocaoDAO;


/**
 *
 * @author Juliano Felipe da Silva
 */
public class JDBCDAOFactory extends DAOFactory {
    private static ArmaDAO armaDAO;
    private static ArmaduraDAO armaduraDAO;
    private static CriaturaDAO criaturaDAO;
    private static HabilidadeDAO habilidadeDAO;
    private static HeroiDAO heroiDAO;
    private static ItemDAO itemDAO;
    private static JogadorDAO jogadorDAO;
    private static PergaminhoDAO pergaminhoDAO;
    private static PocaoDAO pocaoDAO;

    @Override
    public ArmaDAO getArmaDAO() {
        if (armaDAO == null) {
            armaDAO = new JDBCArmaDAO();
        }
        return armaDAO;
    }

    @Override
    public ArmaduraDAO getArmaduraDAO() {
        if (armaduraDAO == null) {
            armaduraDAO = new JDBCArmaduraDAO();
        }
        return armaduraDAO;
    }

    @Override
    public CriaturaDAO getCriaturaDAO() {
        if (criaturaDAO == null) {
            criaturaDAO = new JDBCCriaturaDAO();
        }
        return criaturaDAO;
    }

    @Override
    public HabilidadeDAO getHabilidadeDAO() {
        if (habilidadeDAO == null) {
            habilidadeDAO = new JDBCHabilidadeDAO();
        }
        return habilidadeDAO;
    }

    @Override
    public HeroiDAO getHeroiDAO() {
        if (heroiDAO == null) {
            heroiDAO = new JDBCHeroiDAO();
        }
        return heroiDAO;
    }

    @Override
    public ItemDAO getItemDAO() {
        if (itemDAO == null) {
            itemDAO = new JDBCItemDAO();
        }
        return itemDAO;
    }

    @Override
    public JogadorDAO getJogadorDAO() {
        if (jogadorDAO == null) {
            jogadorDAO = new JDBCJogadorDAO();
        }
        return jogadorDAO;
    }

    @Override
    public PergaminhoDAO getPergaminhoDAO() {
        if (pergaminhoDAO == null) {
            pergaminhoDAO = new JDBCPergaminhoDAO();
        }
        return pergaminhoDAO;
    }

    @Override
    public PocaoDAO getPocaoDAO() {
        if (pocaoDAO == null) {
            pocaoDAO = new JDBCPocaoDAO();
        }
        return pocaoDAO;
    }

    
}

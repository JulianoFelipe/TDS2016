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
import Model.DAO.EfeitoDAO;
import Model.DAO.HabilidadeDAO;
import Model.DAO.HeroiDAO;


/**
 *
 * @author Juliano Felipe da Silva
 */
public class JDBCDAOFactory extends DAOFactory {
    private static ArmaDAO armaDAO;
    private static ArmaduraDAO armaduraDAO;
    private static CriaturaDAO criaturaDAO;
    private static EfeitoDAO efeitoDAO;
    private static HabilidadeDAO habilidadeDAO;
    private static HeroiDAO heroiDAO;

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
    public EfeitoDAO getEfeitoDAO() {
        if (efeitoDAO == null) {
            efeitoDAO = new JDBCEfeitoDAO();
        }
        return efeitoDAO;
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

    
}

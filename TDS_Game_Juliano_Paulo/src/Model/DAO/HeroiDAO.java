/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.Criaturas.Heroi;

/**
 * DAO sob {@link Model.Criaturas.Heroi} que 
 * estende o {@link Model.DAO.GenericDAO}.
 * @author Juliano Felipe da Silva
 */
public interface HeroiDAO extends GenericDAO<Heroi> {
    /**
     * Irá salvar o estado de um herói (apenas 
     * os dados mais voláteis), e.g. O tanto de
     * xp e etc.
     * @param salvar
     * @return 
     */
    boolean salvar (Heroi salvar);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.Criaturas.CriaturaBase;
import Model.Habilidades.HabilidadeBase;
import java.util.List;

/**
 * DAO sob {@link Model.Criaturas.CriaturaBase} que 
 * estende o {@link Model.DAO.GenericDAO}.
 * @author Juliano Felipe da Silva
 */
public interface CriaturaDAO extends GenericDAO<CriaturaBase> {
    /**
     * Dado um identificador de uma criatura, retorna
     * uma lista de habilidades pertencentes à mesma.
     * @param criaturaPK ID da criatura.
     * @return Lista de habilidades.
     * @throws DatabaseException Em erro.
     */
    List<HabilidadeBase> getListaHabilidades(int criaturaPK) throws DatabaseException;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.Itens.ArmaduraBase;

/**
 *
 * @author Juliano Felipe da Silva
 */
public interface ArmaduraDAO extends GenericDAO<ArmaduraBase> {
     /**
     * Busca a ArmaduraBase que possui como "super" o
     * item de ID correspondente ao ID passado.
     * @param FK ID do item "Super"
     * @return ArmaduraBase relacionada
     * @throws DatabaseException Em erro
     */
    ArmaduraBase buscaViaIdSuper (int FK)throws DatabaseException;
}

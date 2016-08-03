/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.Itens.ArmaBase;

/**
 *
 * @author Juliano Felipe da Silva
 */
public interface ArmaDAO extends GenericDAO<ArmaBase> {
    /**
     * Busca a ArmaBase que possui como "super" o
     * item de ID correspondente ao ID passado.
     * @param FK ID do item "Super"
     * @return ArmaBase relacionada
     * @throws DatabaseException Em erro
     */
    ArmaBase buscaViaIdSuper (int FK)throws DatabaseException;
}

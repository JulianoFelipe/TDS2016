/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.Itens.PergaminhoHabilidade;

/**
 *
 * @author Juliano Felipe da Silva
 */
public interface PergaminhoDAO extends GenericDAO<PergaminhoHabilidade> {
     /**
     * Busca o PergaminhoHabilidade que possui como "super" o
     * item de ID correspondente ao ID passado.
     * @param FK ID do item "Super"
     * @return PergaminhoHabilidade relacionada
     * @throws DatabaseException Em erro
     */
    PergaminhoHabilidade buscaViaIdSuper (int FK)throws DatabaseException;
}

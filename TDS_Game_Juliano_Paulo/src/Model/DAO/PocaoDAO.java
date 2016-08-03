/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.Itens.PocaoAumentoStatus;

/**
 *
 * @author Juliano Felipe da Silva
 */
public interface PocaoDAO extends GenericDAO<PocaoAumentoStatus> {
     /**
     * Busca a PocaoAumentoStatus que possui como "super" o
     * item de ID correspondente ao ID passado.
     * @param FK ID do item "Super"
     * @return PocaoAumentoStatus relacionada
     * @throws DatabaseException Em erro
     */
    PocaoAumentoStatus buscaViaIdSuper (int FK)throws DatabaseException;
}

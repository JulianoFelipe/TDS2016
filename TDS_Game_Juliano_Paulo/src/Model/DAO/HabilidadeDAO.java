/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.Habilidades.HabilidadeBase;

/**
 *
 * @author Juliano Felipe da Silva
 */
public interface HabilidadeDAO extends GenericDAO<HabilidadeBase> {
    /**
     * Dada uma habilidade,
     * checa se existe uma habilidade com
     * todos os atributos iguais ao passado.
     * <p>
     * Caso exista, retorna o id. Caso não,
     * retorna -1.
     * @param t Habilidade para comparar com
     *          as no banco.
     * @return O Id da habilidade.
     */
    int checarSeNoBanco(HabilidadeBase t);
}

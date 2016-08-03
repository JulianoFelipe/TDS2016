/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.Habilidades.HabilidadeBase;

/**
 * DAO sob {@link Model.Habilidades.HabilidadeBase} que 
 * estende o {@link Model.DAO.GenericDAO}.
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
     * @throws Model.DAO.DatabaseException Em erro.
     */
    int checarSeNoBanco(HabilidadeBase t) throws DatabaseException;
    
    /**
     * Usa o método {@link Model.DAO.HabilidadeDAO#checarSeNoBanco(Model.Habilidades.HabilidadeBase)}.
     * Se o retorno do mesmo for -1, insere usando:
     * {@link Model.DAO.GenericDAO#inserir(java.lang.Object)}.
     * @param t Habilidade a guardar
     * @return Posição guardada.
     * @throws DatabaseException Em erro.
     */
    int insereDistinto(HabilidadeBase t) throws DatabaseException;
}

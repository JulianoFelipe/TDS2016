/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.Criaturas.Heroi;
import Model.Criaturas.Jogador;
import java.util.List;

/**
 * DAO sob {@link Model.Criaturas.Jogador} que 
 * estende o {@link Model.DAO.GenericDAO}.
 * @author Juliano Felipe da Silva
 */
public interface JogadorDAO extends GenericDAO<Jogador> {
    
    /**
     * Retorna a lista de herois associados a um jogador.
     * Obs.: Os herois na lista retornada não são setados
     * para o jogador, isso terá de ser feito manualmente
     * em um loop.
     * @param primaryKey identificando o jogador.
     * @return Lista com os heróis do jogador
     * @throws Model.DAO.DatabaseException Em erro.
     */
    List<Heroi> getListaDeHerois(int primaryKey) throws DatabaseException;
}

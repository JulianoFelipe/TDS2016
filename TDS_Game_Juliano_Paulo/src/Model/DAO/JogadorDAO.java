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
 *
 * @author Juliano Felipe da Silva
 */
public interface JogadorDAO extends GenericDAO<Jogador> {
    
    /**
     * Retorna a lista de herois associados ao jogador dado
     * pela chave primária.
     * @param primaryKey identificando o jogador.
     * @return Lista com os heróis do jogador
     */
    List<Heroi> getListaDeHerois(int primaryKey);
}

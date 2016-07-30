/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.Itens.PergaminhoHabilidade;
import java.util.List;

/**
 *
 * @author Juliano Felipe da Silva
 */
public interface PergaminhoDAO extends GenericDAO<PergaminhoHabilidade> {
    
    List<PergaminhoHabilidade> itemHabilidadeFK(int itemFK, int habilidadeFK) throws DatabaseException; //Buscar
}

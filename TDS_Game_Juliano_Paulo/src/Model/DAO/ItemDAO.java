/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.Itens.ArmaBase;
import Model.Itens.ItemBase;
import java.util.List;

/**
 *
 * @author Juliano Felipe da Silva
 */
public interface ItemDAO extends GenericDAO<ItemBase> {
    
    List<ArmaBase> jogadorHeroiFK(int jogadorFK, int heroiFK) throws DatabaseException; //Buscar
}

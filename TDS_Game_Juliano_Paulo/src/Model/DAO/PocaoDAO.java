/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.Itens.PocaoAumentoStatus;
import java.util.List;

/**
 *
 * @author Juliano Felipe da Silva
 */
public interface PocaoDAO extends GenericDAO<PocaoAumentoStatus> {
    
    List<PocaoAumentoStatus> itemFK(int foreignKey) throws DatabaseException; //Buscar
}

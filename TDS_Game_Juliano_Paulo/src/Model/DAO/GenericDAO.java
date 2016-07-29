/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Juliano Felipe da Silva
 * @param <T> Dado genérico para ser
 *            atribuído à um DAO.
 */
public interface GenericDAO<T> {
    
    int getNextId(T t) throws SQLException;
    
    boolean inserir(T t) throws DatabaseException;
    
    boolean remover(T t) throws DatabaseException;
    
    boolean atualizar(T t) throws DatabaseException;
    
    List<T> resgatarTodos() throws DatabaseException;
    
    T buscar(int primaryKey) throws DatabaseException;
    
    List<T> buscar(String nome) throws DatabaseException; //Buscar
}

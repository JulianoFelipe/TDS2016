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
    
    /**
     * Retorna o próximo id disponível.
     * <p>
     * Se por exemplo o último id inserido
     * foi "3", retorna-se 4.
     * @return Próximo ID.
     * @throws SQLException em erro. 
     */
    int getNextId() throws SQLException;
    
    /**
     * Inserção de uma entidade T no módulo
     * de persistência.
     * @param t Entidade para ser inserida.
     * @return O Id ou -1 em erro.
     * @throws DatabaseException Em erro.
     */
    int inserir(T t) throws DatabaseException;
    
    /**
     * Inserção de uma entidade T no módulo
     * de persistência.
     * @param t Entidade para ser inserida.
     * @return true em sucesso ou false em erro.
     * @throws DatabaseException Em erro.
     */
    boolean remover(T t) throws DatabaseException;
    
    /**
     * Atualização de uma entidade T no módulo
     * de persistência.
     * @param t Entidade para ser inserida.
     * @return true em sucesso ou false em erro.
     * @throws DatabaseException Em erro.
     */
    boolean atualizar(T t) throws DatabaseException;
    
    /**
     * Retorna uma lista com todas as entidades
     * do tipo T no guardadas.
     * @return Lista com as entidades resgatadas.
     * @throws DatabaseException Em erro.
     */
    List<T> resgatarTodos() throws DatabaseException;
    
    /**
     * Retorna uma entidade T do módulo
     * de persistência, indicada por um ID.
     * @param primaryKey Identificador.
     * @return Entidade retornada, null caso ID não exista.
     * @throws DatabaseException Em erro.
     */
    T buscar(int primaryKey) throws DatabaseException;
    
    /**
     * Retorna uma lista com todas as entidades
     * do tipo T e com o nome indicado guardadas.
     * @param nome para buscar no módulo.
     * @return Lista com as entidades resgatadas.
     *         (Null se não exsitir nenhuma com esse
     *          nome).
     * @throws DatabaseException Em erro.
     */
    List<T> buscar(String nome) throws DatabaseException; //Buscar
    
}

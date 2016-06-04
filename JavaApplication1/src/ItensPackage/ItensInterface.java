/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ItensPackage;

/**
 * Interface basica para os itens do mundo
 *
 * @author Paulo.Tenorio
 */
public interface ItensInterface {
    /**
     * Chamado quando o item for excluido
     */
    public void onDrop();
    /**
     * Chamado quando o item for colocado
     */
    public void onCollect();
    /**
     * Chamado quando o item for vendido
     */
    public void onSell();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Criaturas;

import Model.Geradores.ArenaBatalha;
import Model.Itens.ArmaBase;
import Model.Itens.ArmaduraBase;
import Model.Itens.ItemBase;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe do jogador que conterá o inventario,dinheiro e personagens
 * @author FREE
 */
public class Jogador {
    
//<editor-fold defaultstate="collapsed" desc="Banco de dados">
    private int jogadorId;
    
    public int getJogadorId() {
        return jogadorId;
    }
    
    public void setJogadorId(int jogadorId) {
        this.jogadorId = jogadorId;
    }
//</editor-fold>     
    
    List< Heroi > lista_de_herois = new ArrayList<>();
    
    /**
     * Inventario do jogador
     */
    List< ItemBase > inventario = new ArrayList<>();
    
    /**
     * Dinheiro do jogador
     */
    private Integer gold  = 0;
    
    /**
     * Adiciona gold para o jogador
     *
     * @param sum o quanto adicionara
     */
    public void addGold(int sum) {
        gold = gold + sum;
    }
    
    /**
     * Retira gold do heroi
     *
     * @param sum o quanto retirará
     */
    public void subGold(int sum) {
        gold = gold - sum;
    }
    
    /**
     * Adiciona item ao inventario
     *
     * @param item item adicionado
     */
    public void addItem(ItemBase item) {
        this.inventario.add(item);
    }
    
    /**
     * Adiciona uma array de itens ao inventario
     *
     * @param item lista de item adicionado
     */
    public void addItem(List<ItemBase> item) {
        this.inventario.addAll(item);
    }
    
    
    
    /**
     * Remove item do inventario
     *
     * @param item item removido
     */
    public void removeItem(ItemBase item) {
        if (!inventario.remove(item)) {
            System.out.println("elemento nao encontrado :(");
        } else {
            System.out.println("Item:" + item + ",removido!");
        }
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public List<Heroi> getLista_de_herois() {
        return lista_de_herois;
    }
    
    /**
     * @return Retorna lista de itens com todos os itens
     */
    public List<ItemBase> getInventario() {
        return inventario;
    }
    
    /**
     * @return Retorna lista de itens que sao armas
     */
    public List< ItemBase > getArmas()
    {
        List< ItemBase > retorno = new ArrayList<>();
        for (ItemBase item : inventario)
        {
            if (item instanceof ArmaBase)
            {
                retorno.add(item);
            }
        }
        return(retorno);
    }
    
    /**
     * @return Retorna lista de itens que sao armaduras
     */
    public List< ItemBase > getArmaduras()
    {
        List< ItemBase > retorno = new ArrayList<>();
        for (ItemBase item : inventario)
        {
            if (item instanceof ArmaduraBase)
            {
                retorno.add(item);
            }
        }
        return(retorno);
    }
    
}

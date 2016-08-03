/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.JDBC;

import Model.Itens.ArmaBase;
import Model.Itens.ArmaduraBase;
import Model.Itens.ItemBase;
import Model.Itens.PergaminhoHabilidade;
import Model.Itens.PocaoAumentoStatus;

/**
 * Classe que identifica
 * a subclasse (se apilcável)
 * de um
 * {@link Model.Itens.ItemBase}
 * @author Juliano
 */
public enum TipoItem {
    ItemBase(0),
    ArmaBase(1),
    ArmaduraBase(2),
    Pergaminho(3),
    Pocao(4);
    
    private final int valor;
    private TipoItem(int valor) {
        this.valor = valor;
    }

    /**
     * Retorna o código de
     * um dado TipoItem.
     * @return código inteiro.
     */
    public int getValor() {
        return valor;
    }
    
    /**
     * Dado um inteiro, retorna um TipoItem,
     * esteja o inteiro dentro do alcance definido
     * na enum. Caso não esteja, joga uma 
     * IllegalArgumentException.
     * @param codigo para tentar obter um TipoItem.
     * @return TipoItem identificado pelo inteiro.
     */
    public static TipoItem porCodigo (int codigo){
        for (TipoItem item : TipoItem.values())
            if (codigo == item.valor) return item;
        throw new IllegalArgumentException ("Código inválido. Limite excedido.");
    }
    
    /**
     * Retorna o TipoItem correspondente
     * ao subtipo de ItemBase.
     * @param item ItemBase para descobrir o código.
     * @return TipoItem representando o subtipo de ItemBase.
     */
    public static TipoItem porInstancia (ItemBase item){
        if (item instanceof ArmaBase) return TipoItem.ArmaBase;
        if (item instanceof ArmaduraBase) return TipoItem.ArmaduraBase;
        if (item instanceof PergaminhoHabilidade) return TipoItem.Pergaminho;
        if (item instanceof PocaoAumentoStatus) return TipoItem.Pocao;
        return TipoItem.ItemBase;
    }
}

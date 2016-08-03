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
 *
 * @author User
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

    public int getValor() {
        return valor;
    }
    
    public static TipoItem porCodigo (int codigo){
        for (TipoItem item : TipoItem.values())
            if (codigo == item.valor) return item;
        throw new IllegalArgumentException ("Código inválido. Limite excedido.");
    }
    
    public static TipoItem porInstancia (ItemBase item){
        if (item instanceof ArmaBase) return TipoItem.ArmaBase;
        if (item instanceof ArmaduraBase) return TipoItem.ArmaduraBase;
        if (item instanceof PergaminhoHabilidade) return TipoItem.Pergaminho;
        if (item instanceof PocaoAumentoStatus) return TipoItem.Pocao;
        return TipoItem.ItemBase;
    }
}

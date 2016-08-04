/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Criaturas.HeroisPersonalizados;

import Model.Criaturas.Heroi;
import Model.Criaturas.HeroisPersonalizados.*;

/**
 *
 * @author FREE
 */
public enum HeroiTipo {
    ARTHAS(0),
    CLOE(1),
    ELESIS(2),
    DRUIDA(3);
    
    private final int valor;
    private HeroiTipo(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
    
    public static HeroiTipo porCodigo (int codigo){
        for (HeroiTipo item : HeroiTipo.values())
            if (codigo == item.valor) return item;
        throw new IllegalArgumentException ("Código inválido. Limite excedido.");
    }
    
    public static HeroiTipo porInstancia (Heroi heroi){
        if (heroi instanceof Arthas) return ARTHAS;
        else if (heroi instanceof Cloe) return CLOE;
        else if (heroi instanceof Elesis) return ELESIS;
        else if (heroi instanceof Druida) return DRUIDA;
        
        throw new IllegalArgumentException("HABILIDADE NAO CATALOGADA");
    }
}

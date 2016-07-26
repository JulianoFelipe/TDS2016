/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import utilidades.Descritivel;

/**
 *
 * @author Juliano_Felipe
 */
public enum Armas implements Descritivel {
    Espada(0),
    Cajado(1),
    Arco(2);
    
    private final int valor;
    private Armas(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
    
    public static Armas porCodigo (int codigo){
        for (Armas arma : Armas.values())
            if (codigo == arma.valor) return arma;
        throw new IllegalArgumentException ("Código inválido. Limite excedido.");
    }
    
    public static String descricao (Armas tipo){
        return tipo.getDescricao();
    }

    @Override
    public String getDescricao() {
        switch (valor){
            case 0:
                return "Espada de Cavaleiro";
            case 1:
                return "Cajado de Mago";
            case 2:
                return "Arco de Arqueiro";
            default:
                throw new IllegalArgumentException("Código inválido. Limite excedido. "); //Deve ser impossível chegar aqui...
                                                                                          //Mas só para garantir...
        }
    }
}

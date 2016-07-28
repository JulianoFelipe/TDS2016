/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Itens.Constantes;

import utilidades.Descritivel;

/**
 *
 * @author Juliano_Felipe
 */
public enum Armaduras implements Descritivel{
    Armadura(0),
    Tunica(1),
    Balistica(2);
    
    private final int valor;
    private Armaduras(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
    
    public static Armaduras porCodigo (int codigo){
        for (Armaduras armadura : Armaduras.values())
            if (codigo == armadura.valor) return armadura;
        throw new IllegalArgumentException ("Código inválido. Limite excedido.");
    }
    
    public static String descricao (Armaduras tipo){
        return tipo.getDescricao();
    }

    @Override
    public String getDescricao() {
        switch (valor){
            case 0:
                return "Armadura de Cavaleiro";
            case 1:
                return "Túnica de Mago";
            case 2:
                return "Armadura Balística de Arqueiro";
            default:
                throw new IllegalArgumentException("Código inválido. Limite excedido. "); //Deve ser impossível chegar aqui...
                                                                                          //Mas só para garantir...
        }
    }
}

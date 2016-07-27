package Model;

import utilidades.Descritivel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Identifica o tipo de ação,
 * como, por exemplo:
 * Ofensivo, Defensivo...
 * @author Juliano_Felipe
 */
public enum Acao implements Descritivel{
    Defensiva(0),
    Ofensiva(1);
    
    private final int valor;
    private Acao(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
    
    public static Acao porCodigo (int codigo){
        for (Acao acao : Acao.values())
            if (codigo == acao.valor) return acao;
        throw new IllegalArgumentException ("Código inválido. Limite excedido.");
    }
    
    public static String descricao (Acao tipo){
        return tipo.getDescricao();
    }

    @Override
    public String getDescricao() {
        switch (valor){
            case 0:
                return "Ação defensiva";
            case 1:
                return "Ação ofensiva";
            default:
                throw new IllegalArgumentException("Código inválido. Limite excedido. "); //Deve ser impossível chegar aqui...
                                                                                          //Mas só para garantir...
        }
    }
}

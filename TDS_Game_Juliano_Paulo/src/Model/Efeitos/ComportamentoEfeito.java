/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Efeitos;

/**
 *
 * @author Paulo.Tenorio
 */
public enum ComportamentoEfeito {
    PADRAO(0),//padrao sao efeitos que sao aplicados imediatamente e sao reduzidos cada vez que a criatura ganha turno
    INSTANTANEO(1),//efeitos que sao aplicados e imediatamente e sao reduzidos logo em seguida
    TURNO(2);//efeitos que sao aplicados e reduzidos quando ganha o turno
    
    private final int valor;
    private ComportamentoEfeito(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
    
    public static ComportamentoEfeito porCodigo (int codigo){
        for (ComportamentoEfeito comportamento : ComportamentoEfeito.values())
            if (codigo == comportamento.valor) return comportamento;
        throw new IllegalArgumentException ("Código inválido. Limite excedido.");
    }
}

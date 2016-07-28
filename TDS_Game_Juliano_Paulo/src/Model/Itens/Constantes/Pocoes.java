/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Itens.Constantes;

import utilidades.Descritivel;

/**
 *
 * @author User
 */
public enum Pocoes implements Descritivel{
    Vida(0, "Vida"),
    Velocidade(1, "Velocidade"),
    Ataque(2, "Ataque"),
    Defesa(3, "Defesa");
    
    
    private final int codigo;
    private final String tipo;
    private Pocoes(int codigo, String sufixo) {
        this.codigo = codigo;
        this.tipo = sufixo;
    }

    public static Pocoes porCodigo (int umCodigo){
        for (Pocoes codigo : Pocoes.values())
            if (umCodigo == codigo.codigo) return codigo;
        throw new IllegalArgumentException ("Código inválido. Limite excedido.");
    }
    
    public int getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String getDescricao() {
        return getTipo();
    }   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import utilidades.Descritivel;

/**
 * Define raridade de itens.
 * Raridade esta relacionada com
 * o dano base, somente.
 * @author Juliano.Silva10
 */
public enum Raridade implements Descritivel {
    Branca(0, "Comum"),     //Sistema de raridade de RPGs famosos
    Verde(1, "Incomum"),
    Azul(2, "Formidavel"),
    Roxo(3, "Super"),
    Magenta(4, "Transcendente"),
    Ciano(5, "Singular"),
    Laranja(6, "Sui Generis");
    
    
    private final int codigo;
    private final String sufixo;
    private Raridade(int codigo, String sufixo) {
        this.codigo = codigo;
        this.sufixo = sufixo;
    }

    public static Raridade porCodigo (int umCodigo){
        for (Raridade codigo : Raridade.values())
            if (umCodigo == codigo.codigo) return codigo;
        throw new IllegalArgumentException ("Código inválido. Limite excedido.");
    }
    
    public int getCodigo() {
        return codigo;
    }

    public String getSufixo() {
        return sufixo;
    }

    @Override
    public String getDescricao() {
        return getSufixo();
    }   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import utilidades.Descritivel;

/**
 * Define os modificadores de itens.
 * Modificadores atribuem "encantamentos"
 * para os itens, isto é, efeitos que afetam
 * os status que não a vida de uma Criatura.
 * @author Juliano.Silva10
 */
public enum Modificador implements Descritivel{
    Exemplo1(0, "Shocante"),     //Sistema de raridade de RPGs famosos
    Exemplo2(1, "Paranauê"),
    Exemplo3(2, "Bagulho");
    
    
    private final int codigo;
    private final String sufixo;
    private Modificador(int codigo, String sufixo) {
        this.codigo = codigo;
        this.sufixo = sufixo;
    }

    public static Modificador porCodigo (int umCodigo){
        for (Modificador codigo : Modificador.values())
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

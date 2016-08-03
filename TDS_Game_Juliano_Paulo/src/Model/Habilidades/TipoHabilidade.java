/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Habilidades;
import Model.Habilidades.HabilidadesPersonalizadas.*;

/**
 *
 * @author Paulo.Tenorio
 */
public enum TipoHabilidade {
    Conflagracao(0),
    Encorajemento(1),
    EscudoDivino(2),
    ExplorandoFraqueza(3),
    ForcaNatural(4),
    Fortalecimento(5),
    MordidaVenenosa(6),
    Nevasca(7),
    NuvemVenenosa(8),
    TeiaAranha(9);
    
    private final int valor;
    private TipoHabilidade(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
    
    public static TipoHabilidade porCodigo (int codigo){
        for (TipoHabilidade item : TipoHabilidade.values())
            if (codigo == item.valor) return item;
        throw new IllegalArgumentException ("Código inválido. Limite excedido.");
    }
    
    public static TipoHabilidade porInstancia (HabilidadeBase habilidade){
        if (habilidade instanceof Conflagracao) return TipoHabilidade.Conflagracao;
        if (habilidade instanceof Encorajamento) return TipoHabilidade.Encorajemento;
        if (habilidade instanceof EscudoDivino) return TipoHabilidade.EscudoDivino;
        if (habilidade instanceof ExplorandoFraqueza) return TipoHabilidade.ExplorandoFraqueza;
        if (habilidade instanceof ForcaNatural) return TipoHabilidade.ForcaNatural;
        if (habilidade instanceof Fortalecimento) return TipoHabilidade.Fortalecimento;
        if (habilidade instanceof MordidaVenenosa) return TipoHabilidade.MordidaVenenosa;
        if (habilidade instanceof Nevasca) return TipoHabilidade.Nevasca;
        if (habilidade instanceof NuvemVenenosa) return TipoHabilidade.NuvemVenenosa;
        if (habilidade instanceof TeiaAranha) return TipoHabilidade.TeiaAranha;
        throw new IllegalArgumentException("HABILIDADE NAO CATALOGADA");
    }
}


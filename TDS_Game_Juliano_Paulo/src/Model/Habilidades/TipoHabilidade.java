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
    CONFLAGRACAO(0),
    ENCORAJAMENTO(1),
    ESCUDODIVINO(2),
    EXPLORANDOFRAQUEZA(3),
    FORCANATURAL(4),
    FORTALECIMENTO(5),
    MORDIDAVENENOSA(6),
    NEVASCA(7),
    NUVEMVENENOSA(8),
    TEIAARANHA(9),
    INSIGNIATERRA(10);
    
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
        if (habilidade instanceof Conflagracao) return TipoHabilidade.CONFLAGRACAO;
        if (habilidade instanceof Encorajamento) return TipoHabilidade.ENCORAJAMENTO;
        if (habilidade instanceof EscudoDivino) return TipoHabilidade.ESCUDODIVINO;
        if (habilidade instanceof ExplorandoFraqueza) return TipoHabilidade.EXPLORANDOFRAQUEZA;
        if (habilidade instanceof ForcaNatural) return TipoHabilidade.FORCANATURAL;
        if (habilidade instanceof Fortalecimento) return TipoHabilidade.FORTALECIMENTO;
        if (habilidade instanceof MordidaVenenosa) return TipoHabilidade.MORDIDAVENENOSA;
        if (habilidade instanceof Nevasca) return TipoHabilidade.NEVASCA;
        if (habilidade instanceof NuvemVenenosa) return TipoHabilidade.NUVEMVENENOSA;
        if (habilidade instanceof TeiaAranha) return TipoHabilidade.TEIAARANHA;
        if (habilidade instanceof InsigniaTerra) return TipoHabilidade.INSIGNIATERRA;
        throw new IllegalArgumentException("HABILIDADE NAO CATALOGADA");
    }
}


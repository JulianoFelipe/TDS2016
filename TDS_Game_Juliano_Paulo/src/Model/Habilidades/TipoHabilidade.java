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
    INSIGNIATERRA(10),
    INSIGNIAFOGO(11),
    ANIQUILACAO(12),
    ATAQUEEMGRUPO(13),
    GOLPEATORDOADOR(14),
    REJUVENACAO(15),
    RUGIDO(16),
    SEDUCAO(17),
    SOBRECARGA(18),
    ZEROABSOLUTO(19);
    
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
        if (habilidade instanceof Conflagracao)         return CONFLAGRACAO;
        if (habilidade instanceof Encorajamento)        return ENCORAJAMENTO;
        if (habilidade instanceof EscudoDivino)         return ESCUDODIVINO;
        if (habilidade instanceof ExplorandoFraqueza)   return EXPLORANDOFRAQUEZA;
        if (habilidade instanceof ForcaNatural)         return FORCANATURAL;
        if (habilidade instanceof Fortalecimento)       return FORTALECIMENTO;
        if (habilidade instanceof MordidaVenenosa)      return MORDIDAVENENOSA;
        if (habilidade instanceof Nevasca)              return NEVASCA;
        if (habilidade instanceof NuvemVenenosa)        return NUVEMVENENOSA;
        if (habilidade instanceof TeiaAranha)           return TEIAARANHA;
        if (habilidade instanceof InsigniaTerra)        return INSIGNIATERRA;
        if (habilidade instanceof InsigniaFogo)         return INSIGNIAFOGO;
        if (habilidade instanceof Aniquilacao)          return ANIQUILACAO;
        if (habilidade instanceof AtaqueEmGrupo)        return ATAQUEEMGRUPO;
        if (habilidade instanceof GolpeAtordoador)      return GOLPEATORDOADOR;
        if (habilidade instanceof Rejuvenacao)          return REJUVENACAO;
        if (habilidade instanceof Rugido)               return RUGIDO;
        if (habilidade instanceof Seducao)              return SEDUCAO;
        if (habilidade instanceof Sobrecarga)           return SOBRECARGA;
        if (habilidade instanceof ZeroAbsoluto)         return ZEROABSOLUTO;
        
        throw new IllegalArgumentException("HABILIDADE NAO CATALOGADA");
    }
    
    /**
     * Método usado para retornar uma instância de uma habilidade
     * personalizada, dado o TipoHabilidade.
     * @param tipo da Habilidade. Identificador.
     * @return Instância da habilidade identificada pelo tipo.
     */
    public static HabilidadeBase habilidadePorTipo (TipoHabilidade tipo){
             if (tipo == CONFLAGRACAO)       return new Conflagracao();
        else if (tipo == ENCORAJAMENTO)      return new Encorajamento();
        else if (tipo == ESCUDODIVINO)       return new EscudoDivino();
        else if (tipo == EXPLORANDOFRAQUEZA) return new ExplorandoFraqueza();
        else if (tipo == FORCANATURAL)       return new ForcaNatural();
        else if (tipo == FORTALECIMENTO)     return new Fortalecimento();
        else if (tipo == MORDIDAVENENOSA)    return new MordidaVenenosa();
        else if (tipo == NEVASCA)            return new Nevasca();
        else if (tipo == NUVEMVENENOSA)      return new NuvemVenenosa();
        else if (tipo == TEIAARANHA)         return new TeiaAranha();
        else if (tipo == INSIGNIATERRA)      return new InsigniaTerra();
        else if (tipo == INSIGNIAFOGO)       return new InsigniaFogo();
        else if (tipo == ANIQUILACAO)        return new Aniquilacao();
        else if (tipo == ATAQUEEMGRUPO)      return new AtaqueEmGrupo();
        else if (tipo == GOLPEATORDOADOR)    return new GolpeAtordoador();
        else if (tipo == REJUVENACAO)        return new Rejuvenacao();
        else if (tipo == RUGIDO)             return new Rugido();
        else if (tipo == SEDUCAO)            return new Seducao();
        else if (tipo == SOBRECARGA)         return new Sobrecarga();
        else if (tipo == ZEROABSOLUTO)       return new ZeroAbsoluto();
        else{
            String methodName = Thread.currentThread().getStackTrace()[1]
                                .getMethodName();
            throw new IllegalArgumentException("Alguém esqueceu de adicionar"
                         + " a habilidade nessa função :D -> " + methodName);
        } //Check de sanidade para não esquecer de adicionar habilidades novas
          //aqui -- Caso contrário, pode bugar o banco
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Habilidades.HabilidadesPersonalizadas;

import Model.Acao;
import Model.Criaturas.CriaturaBase;
import Model.Efeitos.EfeitoAtributos;
import Model.Efeitos.Efeitos;
import Model.Efeitos.EfeitosBasicos;
import Model.Geradores.ArenaBatalha;
import Model.Habilidades.HabilidadeBase;
import Model.Habilidades.HabilidadesComportamentoPadrao;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FREE
 */
public class Aniquilacao extends HabilidadeBase{
    public Aniquilacao(CriaturaBase criatura_dono) {
        super(criatura_dono);
    }
    
    public Aniquilacao()
    {
        super();
    }
    
    @Override
    public void noUso(ArenaBatalha arena) {
        List< CriaturaBase > alvos = this.pegarInimigosVivos(arena);
        CriaturaBase dono = this.getDono();
        List< Efeitos > efeitos = new ArrayList<>();
        Efeitos efeito_de_reducao_de_ataque = new EfeitoAtributos(20.00,0.00,EfeitosBasicos.DANO_POR_TURNO,3);
        Efeitos efeitoDeReducaoDefesa = new EfeitoAtributos(50.00,0.00,EfeitosBasicos.DEFESA_DIMINUIR,3); 
        efeitos.add(efeitoDeReducaoDefesa);
        efeitos.add(efeito_de_reducao_de_ataque);
        Double multiplicador = 4.00;
        HabilidadesComportamentoPadrao.afeteTodosOsAlvos(this,arena, dono, alvos, efeitos, multiplicador, 0, 50, 0, 0 , 0);
    }

    @Override
    public void noUso(ArenaBatalha arena, CriaturaBase criatura) {
        noUso(arena);
    }

    @Override
    protected void setDescricao() {
        descricao = "Da 400% de dano em todos os inimigos\nAplica efeito de dano por segundo(20% da vida) por 3 turnos\nReduz defesa em 50% por 3 turnos\nTempo de recarga igual a 8 turnos";
    }

    @Override
    protected void setNome() {
        nome = "Aniquilacao";
    }

    @Override
    protected void setCoolDown() {
        tempoRecarregamento = progressoRecarregamento = 8;
    }

    @Override
    public File pegarArquivoImagem() {
        return( new File(getClass().getResource("/View/Imagens/Habilidades/aniquilacao.jpg").getFile() ) );
    }

    @Override
    public void setTipo() {
        tipo = Acao.Ofensiva;
    }
}

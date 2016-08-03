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
public class ExplorandoFraqueza extends HabilidadeBase{

    public ExplorandoFraqueza(CriaturaBase criatura_dono) {
        super(criatura_dono);
    }
    
    public ExplorandoFraqueza()
    {
        super();
    }

    @Override
    public void noUso(ArenaBatalha arena) {
        List< CriaturaBase > inimigos_vivos = this.pegarInimigosVivos(arena);
        solicitarIndice(this, inimigos_vivos);
    }

    @Override
    public void noUso(ArenaBatalha arena, CriaturaBase criatura) {
        final CriaturaBase dono = this.getDono();

        Efeitos efeito_envenenamento = new EfeitoAtributos(50.00,0.00,EfeitosBasicos.DEFESA_DIMINUIR,2);
        List< Efeitos > efeitos = new ArrayList<>();
        efeitos.add(efeito_envenenamento);
        
        HabilidadesComportamentoPadrao.afeteUmInimigo(this, arena, dono, criatura, efeitos , 2.00 , 0 , 0 , 0 , 0 , 0);
    }

    @Override
    protected void setDescricao() {
        descricao = "Da 200% do dano de ataque e diminue a defesa do alvo em 50% por 2 turnos\n4 turnos de recarga";
    }

    @Override
    protected void setNome() {
        nome = "ExplorandoFraqueza";
    }

    @Override
    protected void setCoolDown() {
        this.progressoRecarregamento = this.tempoRecarregamento = 4;
    }

    @Override
    public File pegarArquivoImagem() {
        return( new File(getClass().getResource("/View/Imagens/Habilidades/explorandofraqueza.png").getFile() ) );
    }
    
    @Override
    public void setTipo()
    {
        tipo = Acao.Ofensiva;
    }
    
}

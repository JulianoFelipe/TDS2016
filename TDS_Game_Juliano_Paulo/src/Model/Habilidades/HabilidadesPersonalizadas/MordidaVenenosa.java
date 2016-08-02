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
public class MordidaVenenosa extends HabilidadeBase{

    public MordidaVenenosa(CriaturaBase criatura_dono) {
        super(criatura_dono);
    }
    
    public MordidaVenenosa()
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

        Efeitos efeito_envenenamento = new EfeitoAtributos(5.00,0.00,EfeitosBasicos.DANO_POR_TURNO,2);
        List< Efeitos > efeitos = new ArrayList<>();
        efeitos.add(efeito_envenenamento);
        
        HabilidadesComportamentoPadrao.afeteUmInimigo(this, arena, dono, criatura, efeitos , 3.00 , 0 , 0 , 0 , 0 , 0);
    }

    @Override
    protected void setDescricao() {
        descricao = "Da 300% do dano de ataque e deixa um efeito de dano continuo igual a 5% da vida por dois turnos";
    }

    @Override
    protected void setNome() {
        nome = "MordidaVenenosa";
    }

    @Override
    protected void setCoolDown() {
        this.progressoRecarregamento = this.tempoRecarregamento = 2;
    }

    @Override
    public File pegarArquivoImagem() {
        return( new File(getClass().getResource("/View/Imagens/Habilidades/mordidavenenosa.png").getFile() ) );
    }
    
    @Override
    public void setTipo()
    {
        tipo = Acao.Ofensiva;
    }
    
    
    
}

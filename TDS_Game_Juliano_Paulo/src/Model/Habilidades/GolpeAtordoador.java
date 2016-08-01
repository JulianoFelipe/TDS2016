/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Habilidades;

import Model.Acao;
import Model.Criaturas.CriaturaBase;
import Model.Efeitos.Efeitos;
import Model.Efeitos.EfeitoAtributos;
import Model.Efeitos.Efeitos;
import Model.Efeitos.EfeitosBasicos;
import Model.Geradores.ArenaBatalha;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import utilidades.Math.battle_math;

/**
 *
 * @author Paulo.Tenorio
 */
public class GolpeAtordoador extends HabilidadeBase{

    public GolpeAtordoador(CriaturaBase criatura_dono) {
        super(criatura_dono);
    }

    @Override
    public void noUso(ArenaBatalha arena) {
        List< CriaturaBase > inimigos_vivos = this.pegarInimigosVivos(arena);
        solicitarIndice(this, inimigos_vivos);
    }

    @Override
    protected void setDescricao() {
        descricao = "Dano igual a 500% do ataque e aplica atordoamento por 2 turno";
    }

    @Override
    protected void setNome() {
        nome = "GolpeAtordoador";
    }

    @Override
    protected void setCoolDown() {
        this.tempoRecarregamento = this.progressoRecarregamento = 3;
    }

    @Override
    public void noUso(ArenaBatalha arena, CriaturaBase criatura) {
        final CriaturaBase dono = this.getDono();

        Efeitos efeito_de_atordoamento = new EfeitoAtributos(50.00,0.00,EfeitosBasicos.ATORDOAMENTO,2);
        List< Efeitos > efeitos = new ArrayList<>();
        efeitos.add(efeito_de_atordoamento);
        
        HabilidadesComportamentoPadrao.afeteUmInimigo(this, arena, dono, criatura, efeitos , 5.00 , 0 , 0 , 0 , 0,0);
        progressoRecarregamento = 0;
    }

    @Override
    public File pegarArquivoImagem() {
        return( new File(getClass().getResource("/View/Imagens/punch_icon.jpg").getFile() ) );
    }
    
    @Override
    public void setTipo()
    {
        tipo = Acao.Ofensiva;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Habilidades.HabilidadesPersonalizadas;

import Model.Acao;
import Model.Criaturas.CriaturaBase;
import Model.Efeitos.Efeitos;
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
public class Sobrecarga extends HabilidadeBase{
    public Sobrecarga(CriaturaBase criatura_dono) {
        super(criatura_dono);
    }
    
    public Sobrecarga() {
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

        List< Efeitos > efeitos = new ArrayList<>();
        
        Double porcentagemDeVidaPerdida = (100.00 - (dono.getPontosVida()*100/dono.getMaxPontosVida()));
        
        if (porcentagemDeVidaPerdida >= 90)
        {
            porcentagemDeVidaPerdida = 90.00;
        }
        
        double danoMultiplicador = 1 + porcentagemDeVidaPerdida/10;
        
        HabilidadesComportamentoPadrao.afeteUmInimigo(this, arena, dono, criatura, efeitos , danoMultiplicador , 0 , 0 , 0 , 0 , 0);
    }

    @Override
    protected void setDescricao() {
       descricao = "Da de 100%-1000% do ataque em um alvo dependendo da vida percentual do usuário da habilidade, aumentando conforme a vida do usuário diminue até onde a vida do usuário da habilidade é  10% ou menos dará 1000% do dano de ataque\nTempo de recarga 3 turnos";
    }

    @Override
    protected void setNome() {
        nome = "Sobrecarga";
    }

    @Override
    protected void setCoolDown() {
        this.progressoRecarregamento = this.tempoRecarregamento = 3;
    }

    @Override
    public File pegarArquivoImagem() {
        return( new File(getClass().getResource("/View/Imagens/Habilidades/sobrecarga.jpg").getFile() ) );
    }
    
    @Override
    public void setTipo()
    {
        tipo = Acao.Ofensiva;
    }
}

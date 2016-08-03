/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Habilidades.HabilidadesPersonalizadas;

import Model.Acao;
import Model.Criaturas.CriaturaBase;
import Model.Criaturas.Monstro;
import Model.Criaturas.MonstrosPersonalizados.Aranha;
import Model.Efeitos.Efeitos;
import Model.Geradores.ArenaBatalha;
import Model.Habilidades.HabilidadeBase;
import Model.Habilidades.HabilidadesComportamentoPadrao;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author FREE
 */
public class Proliferacao extends HabilidadeBase{
    public Proliferacao(CriaturaBase criatura)
    {
        super(criatura);
    }
    
    public Proliferacao()
    {
        super();
    }
    
    @Override
    public void noUso(ArenaBatalha arena) {
        List< CriaturaBase > alvos = this.pegarInimigosVivos(arena);
        List< Efeitos > efeitos = new ArrayList<>();
        Random gerador = new Random();
        for (int i=0;i<2;i++)
        {
            Integer numeroRandom = gerador.nextInt(2) + 1;
            Monstro monstroNovo = new Aranha(numeroRandom);
            arena.getListaDeVivos().add(monstroNovo);
        }
        HabilidadesComportamentoPadrao.afeteUmInimigo(this, arena, this.getDono(), this.getDono(), efeitos, 0.00, 0, 0, 0, 0, 1);
    }

    @Override
    public void noUso(ArenaBatalha arena, CriaturaBase criatura) {
        noUso(arena);
    }

    @Override
    protected void setDescricao() {
        descricao = "Cria de duas aranhas\nTempo de recarregamento de 5 turnos\n";
    }

    @Override
    protected void setNome() {
        nome = "Proliferacao";
    }

    @Override
    protected void setCoolDown() {
        this.tempoRecarregamento = this.progressoRecarregamento = 5;
    }

    @Override
    public File pegarArquivoImagem() {
        return( new File(getClass().getResource("/View/Imagens/Habilidades/proliferacao.jpg").getFile() ) );
    }
    
    @Override
    public void setTipo()
    {
        tipo = Acao.Ofensiva;
    }
}

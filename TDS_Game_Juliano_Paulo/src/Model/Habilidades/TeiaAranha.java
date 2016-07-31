/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Habilidades;

import Model.Criaturas.CriaturaBase;
import Model.Efeitos.EfeitoAtributos;
import Model.Efeitos.Efeitos;
import Model.Efeitos.EfeitosBasicos;
import Model.Geradores.ArenaBatalha;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FREE
 */
public class TeiaAranha extends HabilidadeBase{

    public TeiaAranha(CriaturaBase criatura_dono) {
        super(criatura_dono);
    }
    
    public TeiaAranha() {
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

        Efeitos efeito_de_atordoamento = new EfeitoAtributos(50.00,0.00,EfeitosBasicos.ATORDOAMENTO,1);
        List< Efeitos > efeitos = new ArrayList<>();
        efeitos.add(efeito_de_atordoamento);
        
        HabilidadesComportamentoPadrao.afeteUmInimigo(this, arena, dono, criatura, efeitos , 2.00 , 0 , 0 , 0 , 0);
    }

    @Override
    protected void setDescricao() {
       descricao = "Ataque um alvo dando dano igual a 200% do ataque e causa atordoamento por 1 turno!\nTempo de recarga 3 turnos";
    }

    @Override
    protected void setNome() {
        nome = "TeiaAranha";
    }

    @Override
    protected void setCoolDown() {
        this.progressoRecarregamento = this.tempoRecarregamento = 3;
    }

    @Override
    public File pegarArquivoImagem() {
        return( new File(getClass().getResource("/View/Imagens/teiaAranha.png").getFile() ) );
    }
    
}

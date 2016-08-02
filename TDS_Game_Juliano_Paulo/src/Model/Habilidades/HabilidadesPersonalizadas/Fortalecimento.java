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
public class Fortalecimento extends HabilidadeBase {

    @Override
    public void noUso(ArenaBatalha arena) {
        List< CriaturaBase > alvos = this.pegarAliadosVivos(arena);
        System.out.println("aliados = ");
        for (CriaturaBase soma : alvos)
        {
            System.out.println(soma.getNome() + "\n");
        }
        CriaturaBase dono = this.getDono();
        List< Efeitos > efeitos = new ArrayList<>();
        Efeitos efeitoDeAumentoDeAtaque = new EfeitoAtributos(30.00,0.00,EfeitosBasicos.ATAQUE_AUMENTAR,2);
        efeitos.add(efeitoDeAumentoDeAtaque);
        Double multiplicador = 0.00;
        Integer aumentoDeAtaque = 50;
        HabilidadesComportamentoPadrao.afeteTodosOsAlvos(this,arena, dono, alvos, efeitos, multiplicador, aumentoDeAtaque, 0, 0, 0 , 1);
    }

    @Override
    public void noUso(ArenaBatalha arena, CriaturaBase criatura) {
        noUso(arena);
    }

    @Override
    protected void setDescricao() {
        descricao = "Aumento poder de ataque de todos os aliado em 30% por 2 turnos\nTempo de recarga de 4 turnos";
    }

    @Override
    protected void setNome() {
        nome = "Fortalecimento";
    }

    @Override
    protected void setCoolDown() {
        tempoRecarregamento = progressoRecarregamento = 5;
    }

    @Override
    public File pegarArquivoImagem() {
        return( new File(getClass().getResource("/View/Imagens/Habilidades/fortalecimento.png").getFile() ) );
    }

    @Override
    public void setTipo() {
        tipo = Acao.Defensiva;
    }
    
}

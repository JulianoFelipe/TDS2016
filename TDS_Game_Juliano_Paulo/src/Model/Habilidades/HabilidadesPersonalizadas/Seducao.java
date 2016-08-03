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
 * @author Paulo
 */
public class Seducao extends HabilidadeBase{
    public Seducao(CriaturaBase criatura_dono) {
        super(criatura_dono);
    }
    
    public Seducao()
    {
        super();
    }

    @Override
    public void noUso(ArenaBatalha arena) {
        List< CriaturaBase > alvos = this.pegarInimigosVivos(arena);
        List< Efeitos > efeitos = new ArrayList<>();
        Efeitos efeitoReducaoAtaque = new EfeitoAtributos(50.00,0.00,EfeitosBasicos.ATAQUE_DIMINUIR,3);
        efeitos.add(efeitoReducaoAtaque);
        HabilidadesComportamentoPadrao.afeteTodosOsAlvos(this, arena, this.getDono() , alvos, efeitos, 0.0 , 50 , 0, 0, 0,0);
    }

    @Override
    public void noUso(ArenaBatalha arena, CriaturaBase criatura) {
        noUso(arena);
    }

    @Override
    protected void setDescricao() {
        descricao = "Reduz ataque de todos os inimigosem 50% por 3 turnos%\nTempo de recarga 5 turnos\n";
    }

    @Override
    protected void setNome() {
        nome = "Seducao";
    }

    @Override
    protected void setCoolDown() {
        this.tempoRecarregamento = this.progressoRecarregamento = 5;
    }

    @Override
    public File pegarArquivoImagem() {
        return( new File(getClass().getResource("/View/Imagens/Habilidades/seducao.jpg").getFile() ) );
    }
    
    @Override
    public void setTipo()
    {
        tipo = Acao.Ofensiva;
    }
}

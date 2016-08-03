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
public class Encorajamento extends HabilidadeBase{
    
    public Encorajamento(CriaturaBase criatura_dono) {
        super(criatura_dono);
    }
    
    public Encorajamento()
    {
        super();
    }
    
    @Override
    public void noUso(ArenaBatalha arena) {
        List< CriaturaBase > alvos = this.pegarAliadosVivos(arena);
        CriaturaBase dono = this.getDono();
        List< Efeitos > efeitos = new ArrayList<>();
        Efeitos efeitoDeAumentoDeAtaque = new EfeitoAtributos(30.00,0.00,EfeitosBasicos.BARRA_DE_ATAQUE_AUMENTAR,0);
        efeitos.add(efeitoDeAumentoDeAtaque);
        Double multiplicador = 0.00;
        HabilidadesComportamentoPadrao.afeteTodosOsAlvos(this,arena, dono, alvos, efeitos, multiplicador, 0, 0, 0, 30 , 1);
    }

    @Override
    public void noUso(ArenaBatalha arena, CriaturaBase criatura) {
        noUso(arena);
    }

    @Override
    protected void setDescricao() {
        descricao = "Aumento barra de ataque de todos os aliados em 30%\nTempo de recarga 3 turnos";
    }

    @Override
    protected void setNome() {
        nome = "Encorajamento";
    }

    @Override
    protected void setCoolDown() {
        tempoRecarregamento = progressoRecarregamento = 3;
    }

    @Override
    public File pegarArquivoImagem() {
        return( new File(getClass().getResource("/View/Imagens/Habilidades/encorajamento.jpg").getFile() ) );
    }

    @Override
    public void setTipo() {
        tipo = Acao.Defensiva;
    }
    
}

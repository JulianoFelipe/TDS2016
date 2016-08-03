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
public class AtaqueEmGrupo extends HabilidadeBase{
    
    public AtaqueEmGrupo(CriaturaBase criatura_dono) {
        super(criatura_dono);
    }
    
    public AtaqueEmGrupo()
    {
        super();
    }
    
    @Override
    public void noUso(ArenaBatalha arena) {
        List< CriaturaBase > alvos = this.pegarAliadosVivos(arena);
        CriaturaBase dono = this.getDono();
        List< Efeitos > efeitos = new ArrayList<>();
        Efeitos efeitoDeAumentoDeAtaque = new EfeitoAtributos(50.00,0.00,EfeitosBasicos.ATAQUE_AUMENTAR,0);
        Efeitos efeitoDeAumentoDeVelocidade = new EfeitoAtributos(50.00,0.00,EfeitosBasicos.VELOCIDADE_AUMENTAR,3);
        Efeitos efeitoDeAumentoDeBarraDeAtaque = new EfeitoAtributos(50.00,0.00,EfeitosBasicos.BARRA_DE_ATAQUE_AUMENTAR,0);
        efeitos.add(efeitoDeAumentoDeVelocidade);
        efeitos.add(efeitoDeAumentoDeAtaque);
        efeitos.add(efeitoDeAumentoDeBarraDeAtaque);
        Double multiplicador = 0.00;
        HabilidadesComportamentoPadrao.afeteTodosOsAlvos(this,arena, dono, alvos, efeitos, multiplicador, 50, 0, 50, 50 , 1);
    }

    @Override
    public void noUso(ArenaBatalha arena, CriaturaBase criatura) {
        noUso(arena);
    }

    @Override
    protected void setDescricao() {
        descricao = "Aumento barra de ataque de todos os aliados em 50% e a velocidade e o ataque de todos os aliados em 50% por 3 turnos\nTempo de recarga 8 turnos";
    }

    @Override
    protected void setNome() {
        nome = "AtaqueEmGrupo";
    }

    @Override
    protected void setCoolDown() {
        tempoRecarregamento = progressoRecarregamento = 8;
    }

    @Override
    public File pegarArquivoImagem() {
        return( new File(getClass().getResource("/View/Imagens/Habilidades/ataqueemgrupo.jpg").getFile() ) );
    }

    @Override
    public void setTipo() {
        tipo = Acao.Defensiva;
    }
    
}

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
public class ZeroAbsoluto extends HabilidadeBase{
    public ZeroAbsoluto(CriaturaBase criatura_dono) {
        super(criatura_dono);
    }
    
    public ZeroAbsoluto()
    {
        super();
    }
    
    @Override
    public void noUso(ArenaBatalha arena) {
        List< CriaturaBase > alvos = this.pegarInimigosVivos(arena);
        CriaturaBase dono = this.getDono();
        List< Efeitos > efeitos = new ArrayList<>();
        Efeitos efeitoDeReducaVelocidade = new EfeitoAtributos(40.00,0.00,EfeitosBasicos.VELOCIDADE_DIMINUIR,3);
        Efeitos efeitoDeReducaoBarraDeAtaque = new EfeitoAtributos(50.00,0.00,EfeitosBasicos.BARRA_DE_ATAQUE_DIMINUIR,0); 
        efeitos.add(efeitoDeReducaVelocidade);
        efeitos.add(efeitoDeReducaoBarraDeAtaque);
        Double multiplicador = 2.00;
        HabilidadesComportamentoPadrao.afeteTodosOsAlvos(this,arena, dono, alvos, efeitos, multiplicador, 0, 0, 40, 50 , 0);
    }

    @Override
    public void noUso(ArenaBatalha arena, CriaturaBase criatura) {
        noUso(arena);
    }

    @Override
    protected void setDescricao() {
        descricao = "Da 200% de dano em todos os inimigos\nAplica efeito de reducao de velocidade(40%) por 3 turnos\nReduz barra de ataque em 50%\nTempo de recarga igual a 8 turnos";
    }

    @Override
    protected void setNome() {
        nome = "ZeroAbsoluto";
    }

    @Override
    protected void setCoolDown() {
        tempoRecarregamento = progressoRecarregamento = 8;
    }

    @Override
    public File pegarArquivoImagem() {
        return( new File(getClass().getResource("/View/Imagens/Habilidades/zeroabsoluto.jpg").getFile() ) );
    }

    @Override
    public void setTipo() {
        tipo = Acao.Ofensiva;
    }
}

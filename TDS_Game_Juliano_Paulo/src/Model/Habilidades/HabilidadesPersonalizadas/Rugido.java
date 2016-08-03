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
public class Rugido extends HabilidadeBase{
    public Rugido(CriaturaBase criatura_dono) {
        super(criatura_dono);
    }
    
    public Rugido()
    {
        super();
    }

    @Override
    public void noUso(ArenaBatalha arena) {
        List< CriaturaBase > alvos = this.pegarInimigosVivos(arena);
        List< Efeitos > efeitos = new ArrayList<>();
        Efeitos efeitoReducaoAtaque = new EfeitoAtributos(30.00,0.00,EfeitosBasicos.ATAQUE_DIMINUIR,2);
        Efeitos efeitoReducaoBarraDeAtaque = new EfeitoAtributos(40.00,0.00,EfeitosBasicos.BARRA_DE_ATAQUE_DIMINUIR,0);
        efeitos.add(efeitoReducaoAtaque);
        efeitos.add(efeitoReducaoBarraDeAtaque);
        HabilidadesComportamentoPadrao.afeteTodosOsAlvos(this, arena, this.getDono() , alvos, efeitos, 0.0 , 30 , 0, 0, 40,0);
    }

    @Override
    public void noUso(ArenaBatalha arena, CriaturaBase criatura) {
        noUso(arena);
    }

    @Override
    protected void setDescricao() {
        descricao = "Reduz ataque em 30% por 2 turnos e barra de ataque em 40%\nTempo de recarga 5 turnos\n";
    }

    @Override
    protected void setNome() {
        nome = "Rugido";
    }

    @Override
    protected void setCoolDown() {
        this.tempoRecarregamento = this.progressoRecarregamento = 5;
    }

    @Override
    public File pegarArquivoImagem() {
        return( new File(getClass().getResource("/View/Imagens/Habilidades/rugido.jpg").getFile() ) );
    }
    
    @Override
    public void setTipo()
    {
        tipo = Acao.Ofensiva;
    }
}

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
public class Rejuvenacao extends HabilidadeBase{
    public Rejuvenacao(CriaturaBase criatura)
    {
        super(criatura);
    }
    
    public Rejuvenacao()
    {
        super();
    }
    
    @Override
    public void noUso(ArenaBatalha arena) {
        List< CriaturaBase > alvos = this.pegarAliadosVivos(arena);
        CriaturaBase dono = this.getDono();
        List< Efeitos > efeitos = new ArrayList<>();
        Efeitos efeitoDeCura = new EfeitoAtributos(10.00,0.00,EfeitosBasicos.CURA_POR_TURNO,2);
        efeitos.add(efeitoDeCura);
        HabilidadesComportamentoPadrao.afeteTodosOsAlvos(this,arena, dono, alvos, efeitos, 0.00, 0, 0, 0, 0 , 1);
    }

    @Override
    public void noUso(ArenaBatalha arena, CriaturaBase criatura) {
        noUso(arena);
    }

    @Override
    protected void setDescricao() {
        descricao = "Em todos aplica efeito de cura(10% da vida por turno) por 5 turnos\nTempo de recarga de 7 turnos";
    }

    @Override
    protected void setNome() {
        nome = "Rejuvenacao";
    }

    @Override
    protected void setCoolDown() {
        progressoRecarregamento = tempoRecarregamento = 7;
    }

    @Override
    public File pegarArquivoImagem() {
        return( new File(getClass().getResource("/View/Imagens/Habilidades/rejuvenacao.jpg").getFile() ) );
    }

    @Override
    public void setTipo() {
        tipo = Acao.Defensiva;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Habilidades;

import Model.Acao;
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
public class EscudoDivino extends HabilidadeBase{

    public EscudoDivino(CriaturaBase criatura)
    {
        super(criatura);
    }
    
    public EscudoDivino()
    {
        super();
    }
    
    @Override
    public void noUso(ArenaBatalha arena) {
        List< CriaturaBase > alvos = this.pegarAliadosVivos(arena);
        CriaturaBase dono = this.getDono();
        List< Efeitos > efeitos = new ArrayList<>();
        ///POR ALGUMA RAZAO A DURACAO DO EFEITO PARA SER 2 TEM QUE COLOCAR 3
        //TALVEZ BUG?
        Efeitos efeitoDeCura = new EfeitoAtributos(10.00,0.00,EfeitosBasicos.CURA_POR_TURNO,3);
        Efeitos efeitoImunidade = new EfeitoAtributos(10.00,0.00,EfeitosBasicos.IMUNIDADE,3);
        efeitos.add(efeitoDeCura);
        efeitos.add(efeitoImunidade);
        Double multiplicador = 0.1;
        HabilidadesComportamentoPadrao.afeteTodosOsAlvos(this,arena, dono, alvos, efeitos, multiplicador, 0, 0, 0, 0 , 1);
    }

    @Override
    public void noUso(ArenaBatalha arena, CriaturaBase criatura) {
        noUso(arena);
    }

    @Override
    protected void setDescricao() {
        descricao = "Em todos os aliados cura 20% da vida\nAplica efeito de imunidade(imune a efeitos negativos)\nAplica efeito de cura(10% da vida por turno) por 2 turnos\nTempo de recarga de 5 turnos";
    }

    @Override
    protected void setNome() {
        nome = "EscudoDivino";
    }

    @Override
    protected void setCoolDown() {
        progressoRecarregamento = tempoRecarregamento = 5;
    }

    @Override
    public File pegarArquivoImagem() {
        return( new File(getClass().getResource("/View/Imagens/protecaoimunidade.jpg").getFile() ) );
    }

    @Override
    public void setTipo() {
        tipo = Acao.Defensiva;
    }
    
}

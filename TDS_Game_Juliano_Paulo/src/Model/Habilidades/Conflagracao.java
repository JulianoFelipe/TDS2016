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
public class Conflagracao extends HabilidadeBase{

    public Conflagracao(CriaturaBase criatura_dono) {
        super(criatura_dono);
    }
    
    public Conflagracao()
    {
        super();
    }
    
    @Override
    public void noUso(ArenaBatalha arena) {
        List< CriaturaBase > alvos = this.pegarInimigosVivos(arena);
        CriaturaBase dono = this.getDono();
        List< Efeitos > efeitos = new ArrayList<>();
        int duracao_efeito;
        Efeitos efeito_de_reducao_de_ataque = new EfeitoAtributos(10.00,0.00,EfeitosBasicos.DANO_POR_TURNO,2);
        efeitos.add(efeito_de_reducao_de_ataque);
        Double multiplicador = 2.50;
        HabilidadesComportamentoPadrao.afeteTodosOsAlvos(this,arena, dono, alvos, efeitos, multiplicador, 0, 0, 0, 0 , 0);
    }

    @Override
    public void noUso(ArenaBatalha arena, CriaturaBase criatura) {
        noUso(arena);
    }

    @Override
    protected void setDescricao() {
        descricao = "Da 250% de dano em todos os inimigos\nAplica efeito de dano por segundo(10% da vida) por dois turnos\nTempo de recarga igual a 5 turnos";
    }

    @Override
    protected void setNome() {
        nome = "Conflagracao";
    }

    @Override
    protected void setCoolDown() {
        tempoRecarregamento = progressoRecarregamento = 5;
    }

    @Override
    public File pegarArquivoImagem() {
        return( new File(getClass().getResource("/View/Imagens/conflagracao.jpg").getFile() ) );
    }

    @Override
    public void setTipo() {
        tipo = Acao.Ofensiva;
    }
    
}

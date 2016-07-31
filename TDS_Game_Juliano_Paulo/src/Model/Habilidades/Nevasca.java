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
 * Habilidade que deixa inimigos devagar
 * @author Paulo
 */
public class Nevasca extends HabilidadeBase{

    public Nevasca(CriaturaBase criatura)
    {
        super(criatura);
    }
    
    @Override
    public void noUso(ArenaBatalha arena) {
        List< CriaturaBase > alvos = this.pegarInimigosVivos(arena);
        List< Efeitos > efeitos = new ArrayList<>();
        Efeitos efeito_lentidao = new EfeitoAtributos(25.00,0.00,EfeitosBasicos.VELOCIDADE_DIMINUIR,2);
        Efeitos efeitoReducaoBarraDeAtaque = new EfeitoAtributos(30.00,0.00,EfeitosBasicos.BARRA_DE_ATAQUE_DIMINUIR,0);
        efeitos.add(efeito_lentidao);
        efeitos.add(efeitoReducaoBarraDeAtaque);
        HabilidadesComportamentoPadrao.afeteTodosOsAlvos(this, arena, this.getDono() , alvos, efeitos, 1.5 , 0 , 0, 25, 90);
    }

    @Override
    public void noUso(ArenaBatalha arena, CriaturaBase criatura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void setDescricao() {
        descricao = "Da 150% do dano de ataque\nDiminui barra de ataque em 30%\nAplica efeito lentidão(25%) por 2 turno\nRecarga 5 turnos";
    }

    @Override
    protected void setNome() {
        nome = "Nevasca";
    }

    @Override
    protected void setCooldDown() {
        this.tempoRecarregamento = this.progressoRecarregamento = 5;
    }

    @Override
    public File pegarArquivoImagem() {
        return( new File(getClass().getResource("/View/Imagens/tempestade_de_neve.jpg").getFile() ) );
    }
    
}

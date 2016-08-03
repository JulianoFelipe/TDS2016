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
public class InsigniaTerra extends HabilidadeBase{
    public InsigniaTerra(CriaturaBase criatura_dono) {
        super(criatura_dono);
    }
    
    public InsigniaTerra()
    {
        super();
    }

    @Override
    public void noUso(ArenaBatalha arena) {
        final CriaturaBase dono = this.getDono();

        Efeitos efeitoAumentoDefesa = new EfeitoAtributos(50.00,0.00,EfeitosBasicos.DEFESA_AUMENTAR,2);
        List< Efeitos > efeitos = new ArrayList<>();
        efeitos.add(efeitoAumentoDefesa);
        
        HabilidadesComportamentoPadrao.afeteUmInimigo(this, arena, dono, dono, efeitos , 0.00 , 0 , 50 , 0 , 0 , 1);
    }

    @Override
    public void noUso(ArenaBatalha arena, CriaturaBase criatura) {
        noUso(arena);
    }

    @Override
    protected void setDescricao() {
        descricao = "Aumenta defesa em 50% por 2 turnos\nTempo de recarregamento 4 turnos";
    }

    @Override
    protected void setNome() {
        nome = "InsigniaTerra";
    }

    @Override
    protected void setCoolDown() {
        this.progressoRecarregamento = this.tempoRecarregamento = 4;
    }

    @Override
    public File pegarArquivoImagem() {
        return( new File(getClass().getResource("/View/Imagens/Habilidades/insigniaterra.jpg").getFile() ) );
    }
    
    @Override
    public void setTipo()
    {
        tipo = Acao.Ofensiva;
    }
}

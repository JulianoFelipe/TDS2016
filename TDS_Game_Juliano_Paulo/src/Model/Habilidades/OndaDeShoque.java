/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Habilidades;

import Controller.ConfiguracoesDeTempo;
import Model.Criaturas.CriaturaBase;
import Model.Criaturas.Monstro;
import Model.Efeitos.Efeitos;
import Model.Efeitos.EfeitoAtributos;
import Model.Efeitos.Efeitos;
import Model.Efeitos.EfeitosBasicos;
import Model.Geradores.ArenaBatalha;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import utilidades.Math.battle_math;

/**
 *
 * @author FREE
 */
public class OndaDeShoque extends HabilidadeBase{

    @Override
    public void noUso(ArenaBatalha arena) {
        System.out.println("inicio skill noUso");
        List< CriaturaBase > inimigos_vivos = this.pegarInimigosVivos(arena);
        final CriaturaBase dono = this.getDono();
        final int delay = ConfiguracoesDeTempo.getInstance().getTempo_aproximado();
        final Timer timer = new Timer(delay,null);
        timer.addActionListener(new ActionListener()
                {
                    int i = 0;
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        System.out.println("timer start!");
                        Double[] vetor_parametros = new Double[5];
                        CriaturaBase criatura = inimigos_vivos.get(i);
                        Double ataque = -criatura.getAtaque()*0.5;
                        
                        
                        Efeitos efeito_de_reducao_de_ataque = new EfeitoAtributos(50.00,0.00,EfeitosBasicos.ATAQUE_DIMINUIR);
                        efeito_de_reducao_de_ataque.setDuration(2);
                        Double heroi_dano = dono.getAtaque();
                        dono.incAttack(heroi_dano);
                        Double dmg = battle_math.calculate_damage(dono , criatura);
                        dono.decAttack(heroi_dano);
                        criatura.getListaDeEfeitos().add(efeito_de_reducao_de_ataque);
                        vetor_parametros[0] = dmg;
                        vetor_parametros[1] = ataque;
                        vetor_parametros[2] = new Double(0.00);
                        vetor_parametros[3] = new Double(0.00);
                        vetor_parametros[4] = new Double(0.00);
                        i++;
                        if (i >= inimigos_vivos.size())
                        {
                            arena.modificarCriatura(vetor_parametros, dono , criatura, true);
                            System.out.println("timer stop!");
                            timer.stop();
                        }
                        else
                        {
                            arena.modificarCriatura(vetor_parametros, dono , criatura, false);
                        }
                    }
                }
        );
        timer.setInitialDelay(0);
        timer.start();
    }
    
    @Override
    public void setDescricao() {
        descricao = "Da dano em todos os inimigos igual a 200% ataque e reduz ataques inimigos em 50%";
    }

    @Override
    protected void setCooldDown() {
        this.tempoRecarregamento = this.progressoRecarregamento = 3;
    }

    @Override
    protected void setNome() {
        this.nome = "OndaDeShoque";
    }

    @Override
    public void noUso(ArenaBatalha arena, CriaturaBase criatura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public File pegarArquivoImagem() {
        return( new File(getClass().getResource("/View/Imagens/skilldefault_icon.jpg").getFile() ) );
    }
    
}

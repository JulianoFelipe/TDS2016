/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Criaturas.CriaturaBase;
import Model.Criaturas.Heroi;
import Model.Criaturas.Escolha;
import Model.Criaturas.Jogador;
import Model.Geradores.ArenaBatalha;
import Model.Habilidades.HabilidadeBase;
import View.*;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import View.FrameExibido; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.Timer;
import math_package.battle_math;

/**
 *
 * @author FREE
 */
public class ControleArena implements Observer{
    AtaqueDefenderFrame ataquedefesa;
    BatalhaFrame arena_frame = null;
    private ArenaBatalha arena = null;
    public FrameExibido frame_a_exibir;
    public Escolha escolha;
    public int indice = 0;
    public double dmg = -100.00;
    
    public ControleArena(Jogador jogador)
    {
        ArenaBatalha battle_arena = new ArenaBatalha(jogador.getLista_de_herois());
        arena = battle_arena;
        battle_arena.addObserver(this);
        battle_arena.nextTurn();
    }
    
    public void getIndice() throws IOException
    {
        if (arena != null)
        {
            if (escolha == Escolha.ATACAR)
            {
                SeletorCriaturas seletor = new SeletorCriaturas( arena.getMonstroVivosArray() , this );
            }
            else if (escolha == Escolha.SKILL)
            {
                CriaturaBase atacante = arena.getBaseCreatureAt(0);
                SeletorHabilidades seletor = new SeletorHabilidades( atacante.getLista_de_habilidades() , this );
            }
        }
    }
    
    private void attack()
    {
        if (arena != null)
        {
            CriaturaBase atacante = arena.getBattleArenaSituation().get(0);
            CriaturaBase defensor = arena.getMonstroVivosArray().get(indice);
            dmg = battle_math.calculate_damage(atacante, defensor);
            arena.attackBaseCreature(dmg, atacante, defensor);
        }
    }
    
    public void criarProximoFrame() throws IOException, InterruptedException
    {
        if (frame_a_exibir == FrameExibido.BATALHA_FRAME)
        {
            if (arena_frame!=null)
            {
                arena_frame.dispose();
            }
            arena.nextTurn();


        }
        else if (frame_a_exibir == FrameExibido.ATACAR_DEFENDER_FRAME)
        {
            attack();
        }
        else if (frame_a_exibir == FrameExibido.ESCOLHER_ACAO)
        {
            if (arena_frame!=null)
            {
                arena_frame.dispose();
            }
            CriaturaBase criatura_escolhendo = arena.getBaseCreatureAt(0);
            EscolhaFrame escolha = new EscolhaFrame(this,criatura_escolhendo);
        }
        else if (frame_a_exibir == FrameExibido.SKILL_USADA)
        {
            CriaturaBase criatura_usando_skill = arena.getBaseCreatureAt(0);
            HabilidadeBase habilidade_usada = criatura_usando_skill.getLista_de_habilidades().get(indice);
        }
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ArenaBatalha && arg instanceof FrameExibido)
        {
            ArenaBatalha arena = (ArenaBatalha)o;
            FrameExibido frame = (FrameExibido)arg;
            
            if (frame == FrameExibido.BATALHA_FRAME)
            {
                if (arena_frame!=null)
                {
                    arena_frame.dispose();
                }
                try {
                    arena_frame = new BatalhaFrame(arena.getBattleArenaSituation(),this);
                    arena_frame.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(ControleArena.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if (frame == FrameExibido.ESCOLHER_ACAO)
            {
                if (arena_frame!=null)
                {
                    arena_frame.dispose();
                }
                CriaturaBase criatura_escolhendo = arena.getBaseCreatureAt(0);
                EscolhaFrame escolha = new EscolhaFrame(this,criatura_escolhendo);
            }
            
        }
        else
        {
            if (arg instanceof Object[])
            {
                Object[] vetor = (Object[])arg;
                if (vetor.length > 3 || vetor[0] instanceof FrameExibido || vetor[1] instanceof Double || vetor[2] instanceof CriaturaBase)
                {
                    if (arena_frame != null)
                    {
                        arena_frame.dispose();
                    }
                    dmg = (Double)vetor[1];
                    CriaturaBase atacante = (CriaturaBase)vetor[2];
                    CriaturaBase defensor = (CriaturaBase)vetor[3];
                    final int delay = 200;
                    final Timer timer = new Timer(delay, null);
                    final long start = System.currentTimeMillis();
                    final long animationTime = 2000;
                    double vida_antes = defensor.getPontos_vida() + dmg;
                    double vida_depois = defensor.getPontos_vida();
                    
                    try {
                        ataquedefesa = new AtaqueDefenderFrame(atacante,defensor);
                    } catch (IOException ex) {
                        Logger.getLogger(ControleArena.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    ataquedefesa.setText(String.format("Damage = %.4f", dmg));
                    
                    double dmg_parcial = dmg/10.00;
                    defensor.setPontos_vida(vida_antes);

                    timer.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            final long now = System.currentTimeMillis();
                            final long elapsed = now - start;
                            if (ataquedefesa != null)
                            {
                                defensor.takeDamage(dmg_parcial);
                                try {
                                    ataquedefesa.updateDefensor(defensor);
                                } catch (IOException ex) {
                                    Logger.getLogger(ControleArena.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            if (elapsed >= animationTime || dmg == 0) {
                                if (ataquedefesa!=null)
                                {
                                    ataquedefesa.dispose();
                                }
                                defensor.setPontos_vida(vida_depois);
                                frame_a_exibir = FrameExibido.BATALHA_FRAME;
                                try {
                                    criarProximoFrame();
                                } catch (IOException ex) {
                                    Logger.getLogger(ControleArena.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(ControleArena.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                timer.stop();
                            }
                        }
                    });

                    timer.start();



                    System.out.println(String.format("Damage = %.4f", dmg));
                }
            }
        }
    }
    
}

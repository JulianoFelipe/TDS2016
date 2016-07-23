/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import CriaturasPackage.BaseCreature;
import CriaturasPackage.HeroClass;
import Enum.EscolhaEnum;
import Geradores.BattleArena;
import View.*;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import Enum.FrameExibido; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author FREE
 */
public class ArenaControl implements Observer{
    AtaqueDefenderFrame ataquedefesa;
    BatalhaFrame arena_frame = null;
    BattleArena arena = null;
    public FrameExibido frame_a_exibir;
    public EscolhaEnum escolha;
    public int indice = 0;
    public double dmg = -100.00;
    
    public ArenaControl(List< HeroClass > lista_de_herois)
    {
        BattleArena battle_arena = new BattleArena(lista_de_herois);
        arena = battle_arena;
        battle_arena.addObserver(this);
        battle_arena.nextTurn();
    }
    
    public void getIndice() throws IOException
    {
        if (arena!=null)
        {
            SeletorCriaturas seletor = new SeletorCriaturas( arena.getMonstroVivosArray() , this );
        }
    }
    
    private void attack()
    {
        if (arena != null)
        {
            dmg = arena.attackBaseCreature( arena.getMonstroVivosArray().get(indice) );
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
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof BattleArena && arg instanceof FrameExibido)
        {
            BattleArena arena = (BattleArena)o;
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
                    Logger.getLogger(ArenaControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if (frame == FrameExibido.ESCOLHER_ACAO)
            {
                if (arena_frame!=null)
                {
                    arena_frame.dispose();
                }
                EscolhaFrame escolha = new EscolhaFrame(this);
            }
            
        }
        else
        {
            if (arg instanceof Object[])
            {
                Object[] vetor = (Object[])arg;
                if (vetor.length > 2 || vetor[0] instanceof FrameExibido || vetor[1] instanceof Double || vetor[2] instanceof BaseCreature)
                {
                    if (arena_frame != null)
                    {
                        arena_frame.dispose();
                    }
                    dmg = (Double)vetor[1];
                    BaseCreature defensor = (BaseCreature)vetor[2];
                    BaseCreature atacante = arena.getBaseCreatureAt( 0 );
                    final int delay = 200;
                    final Timer timer = new Timer(delay, null);
                    final long start = System.currentTimeMillis();
                    final long animationTime = 2000;
                    double vida_antes = defensor.getHit_points() + dmg;
                    double vida_depois = defensor.getHit_points();
                    
                    try {
                        ataquedefesa = new AtaqueDefenderFrame(atacante,defensor);
                    } catch (IOException ex) {
                        Logger.getLogger(ArenaControl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    ataquedefesa.setText(String.format("Damage = %.4f", dmg));
                    
                    double dmg_parcial = dmg/10.00;
                    defensor.setHit_points(vida_antes);

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
                                    Logger.getLogger(ArenaControl.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            if (elapsed >= animationTime || dmg == 0) {
                                if (ataquedefesa!=null)
                                {
                                    ataquedefesa.dispose();
                                }
                                defensor.setHit_points(vida_depois);
                                frame_a_exibir = FrameExibido.BATALHA_FRAME;
                                try {
                                    criarProximoFrame();
                                } catch (IOException ex) {
                                    Logger.getLogger(ArenaControl.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(ArenaControl.class.getName()).log(Level.SEVERE, null, ex);
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

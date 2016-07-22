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

/**
 *
 * @author FREE
 */
public class ArenaControl implements Observer{
    BatalhaFrame arena_frame = null;
    BattleArena arena = null;
    public FrameExibido frame_a_exibir;
    public EscolhaEnum escolha;
    public int indice = 0;
    
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
    
    public void criarProximoFrame() throws IOException
    {
        if (frame_a_exibir == FrameExibido.BATALHA_FRAME)
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
        else if (frame_a_exibir == FrameExibido.ATACAR_DEFENDER_FRAME)
        {
            BaseCreature atacante = arena.getBaseCreatureAt( 0 );
            BaseCreature defensor = arena.getMonstroVivosArray().get( indice );
            System.out.println("indice = " + indice);
            AtaqueDefenderFrame atacante_defensor = new AtaqueDefenderFrame(atacante,defensor);
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
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

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
public class ArenaControl extends Observable implements Observer{
    BatalhaFrame arena_frame = null;
    public EscolhaEnum escolha;
    
    public ArenaControl(List< HeroClass > lista_de_herois)
    {
        BattleArena battle_arena = new BattleArena(lista_de_herois);
        battle_arena.addObserver(this);
        battle_arena.nextTurn();
        
        this.addObserver(battle_arena);
        
    }
    
    public void notificar()
    {
        setChanged();
        notifyObservers(escolha);
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
                setChanged();
                EscolhaFrame escolha = new EscolhaFrame(this);
            }
            
            System.out.println("1");
        }
        System.out.println("2");
    }
    
}

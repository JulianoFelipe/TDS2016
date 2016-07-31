/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Criaturas.CriaturaBase;
import Model.Criaturas.Escolha;
import Model.Criaturas.Heroi;
import Model.Criaturas.Jogador;
import Model.Geradores.ArenaBatalha;
import Model.Habilidades.HabilidadeBase;
import Model.Itens.ItemBase;
import View.*;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import View.FrameExibido; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.Timer;
import utilidades.Math.battle_math;

/**
 *
 * @author FREE
 */
public class ControleArena implements Observer{
    AtaqueDefenderFrame ataquedefesa;
    BatalhaFrame arena_frame = null;
    private ArenaBatalha arena = null;
    public FrameExibido frame_a_exibir = null;
    public Escolha escolha = null;
    public CriaturaBase criatura_alvo = null;
    public List< CriaturaBase > opcoes_criaturas_alvos = null;
    public int indice = 0;
    public double dmg = -100.00;
    public HabilidadeBase habilidade;
    private Jogador jogador;
    
    public ControleArena(Jogador jogador)
    {
        HabilidadeBase.controle = this;
        this.jogador = jogador;
    }
    
    public ControleArena(Jogador jogador,FrameExibido frame)
    {
        HabilidadeBase.controle = this;
        this.jogador = jogador;
        criarProximoFrame();
    }
    
    public void getIndice() throws IOException
    {
        if (arena != null)
        {
            if (escolha == Escolha.ATACAR)
            {
                SeletorCriaturas seletor = new SeletorCriaturas( arena.getMonstroVivosArray() , this , Escolha.ATACAR);
            }
            else if (escolha == Escolha.SKILL)
            {
                CriaturaBase atacante = arena.getBaseCreatureAt(0);
                SeletorHabilidades seletor = new SeletorHabilidades( atacante.getListaDeHabilidades() , this );
            }
        }
    }
    
    private void attack()
    {
        if (arena != null)
        {
            CriaturaBase atacante = arena.getListaDeVivos().get(0);
            CriaturaBase defensor = arena.getMonstroVivosArray().get(indice);
            dmg = battle_math.calculate_damage(atacante, defensor);
            Double[] vetor_parametros = new Double[5];
            vetor_parametros[0] = dmg;
            vetor_parametros[1] = new Double(0.00);
            vetor_parametros[2] = new Double(0.00);
            vetor_parametros[3] = new Double(0.00);
            vetor_parametros[4] = new Double(0.00);
            arena.modificarCriatura(vetor_parametros, atacante, defensor,true);
        }
    }
    
    public void criarProximoFrame()
    {
        try{
        System.out.println("criando proximo frame");
            if (frame_a_exibir == FrameExibido.ARENA_INICIO)
            {
                ArenaBatalha battle_arena = new ArenaBatalha(jogador);
                arena = battle_arena;
                battle_arena.addObserver(this);
                battle_arena.nextTurn();
            }
            else if (frame_a_exibir == FrameExibido.BATALHA_FRAME)
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
            else if (frame_a_exibir == FrameExibido.SKILL_SELECIONADA)
            {
                CriaturaBase criatura_usando_skill = arena.getBaseCreatureAt(0);
                HabilidadeBase habilidade_usada = criatura_usando_skill.getListaDeHabilidades().get(indice);
                JFrame habilidade_utilizada = new HabilidadeUtilizada(this,criatura_usando_skill,habilidade_usada,false);
            }
            else if (frame_a_exibir == FrameExibido.SKILL_USADA)
            {
                CriaturaBase criatura_usando_skill = arena.getBaseCreatureAt(0);
                HabilidadeBase habilidade_usada = criatura_usando_skill.getListaDeHabilidades().get(indice);
                habilidade_usada.noUso(arena);
            }
            else if (frame_a_exibir == FrameExibido.ESCOLHER_CRIATURA_ATAQUE)
            {
                SeletorCriaturas seletor = new SeletorCriaturas( arena.getMonstroVivosArray() , this , Escolha.ATACAR);
            }
            else if (frame_a_exibir == FrameExibido.ESCOLHER_CRIATURA_SKILL)
            {
                SeletorCriaturas seletor = new SeletorCriaturas( opcoes_criaturas_alvos , this , Escolha.SKILL);
            }
            else if (frame_a_exibir == FrameExibido.INDICE_CRIATURA_ALVO_SKILL_ESCOLHIDA)
            {
                habilidade.noUso(arena, criatura_alvo);
            }
            else if (frame_a_exibir == FrameExibido.TELA_INICIAL)
            {
                TelaInicial tela = new TelaInicial(jogador);
                tela.setVisible(true);
            }
            else if (frame_a_exibir == FrameExibido.ESCOLHER_UM_HEROI && escolha == null)
            {
                List< Heroi > lista = jogador.getLista_de_herois();
                List< CriaturaBase > lista_2 = new ArrayList<>();
                for (Heroi heroi : lista)
                {
                    CriaturaBase criatura = (CriaturaBase)heroi;
                    lista_2.add(criatura);
                }
                SeletorCriaturas seletor = new SeletorCriaturas(lista_2,this,null);
            }
            else if (frame_a_exibir == FrameExibido.ESCOLHER_UM_HEROI && escolha != null)
            {
                Heroi heroi_selecionado = jogador.getLista_de_herois().get(indice);
                HeroiSelecionado frame = new HeroiSelecionado(heroi_selecionado,this);
            }
            else if (frame_a_exibir == FrameExibido.INVENTARIO)
            {
                Inventario frame = new Inventario(jogador);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
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
                System.out.println("BATALHA_FRAME");
                if (arena_frame!=null)
                {
                    arena_frame.dispose();
                }
                try {
                    arena_frame = new BatalhaFrame(arena.getListaDeVivos(),this);
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
                //System.out.println("ativado 1!");
                Object[] vetor = (Object[])arg;
                if (vetor.length > 8 && vetor[0] instanceof FrameExibido && vetor[1] instanceof Double && vetor[2] instanceof CriaturaBase && vetor[3] instanceof CriaturaBase && vetor[4] instanceof Boolean && vetor[5] instanceof Double && vetor[6] instanceof Double && vetor[7] instanceof Double)
                {
                    //System.out.println("ativado 2");
                    if (arena_frame != null)
                    {
                        arena_frame.dispose();
                    }
                    dmg = (Double)vetor[1];
                    Double ataque = (Double)vetor[5];
                    Double defesa = (Double)vetor[6];
                    Double velocidade = (Double)vetor[7];
                    Double barraAtaque = (Double)vetor[8];
                    CriaturaBase atacante = (CriaturaBase)vetor[2];
                    CriaturaBase defensor = (CriaturaBase)vetor[3];
                    boolean deve_criar_janela = (Boolean)vetor[4];
                    final long start = System.currentTimeMillis();
                    final long animationTime = ConfiguracoesDeTempo.getInstance().getTempo_total();
                    final int delay = new Double( Math.ceil((animationTime+0.00)/10.00) ).intValue();
                    final Timer timer = new Timer(delay, null);
                    double vida_antes = defensor.getPontosVida() + dmg;
                    double vida_depois = defensor.getPontosVida();
                    double ataque_antes = defensor.getEffectiveAttack() - ataque;
                    double defesaAntes = defensor.getEffectiveDefense() - defesa;
                    double velocidadeAntes = defensor.getEffectiveSpeed() - velocidade;
                    double barraAtaqueAntes = defensor.getBarraAtaque() - barraAtaque;
                    double ataqueBarDepois = defensor.getBarraAtaque();
                    try {
                        ataquedefesa = new AtaqueDefenderFrame(atacante,defensor);
                    } catch (IOException ex) {
                        Logger.getLogger(ControleArena.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    ataquedefesa.setText(String.format("Damage = %.4f", dmg));
                    
                    double dmg_parcial = dmg/10.00;
                    double ataqueParcial = ataque/10.00;
                    double defesaParcial = defesa/10.00;
                    double velocidadeParcial = velocidade/10.00;
                    Double atkBarParcial = (barraAtaque/10.00);
                    System.out.println("dmg_parcial = " + dmg_parcial);
                    defensor.setPontosVida(vida_antes);
                    timer.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            final long now = System.currentTimeMillis();
                            final long elapsed = now - start;
                            if (ataquedefesa != null)
                            {
                                defensor.takeDamage(dmg_parcial);
                                defensor.decAttack(ataqueParcial);
                                defensor.decDefense(defesaParcial);
                                defensor.decSpeed(velocidadeParcial);
                                defensor.decAttackBar(atkBarParcial.intValue());
                                try {
                                    ataquedefesa.updateDefensor(defensor);
                                    ataquedefesa.pack();
                                } catch (IOException ex) {
                                    Logger.getLogger(ControleArena.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            if (elapsed >= animationTime) {
                                if (ataquedefesa!=null)
                                {
                                    ataquedefesa.dispose();
                                }
                                defensor.setPontosVida(vida_depois);
                                defensor.setBarraAtaque(ataqueBarDepois);
                                if (deve_criar_janela)
                                {
                                    System.out.println("criando janela!");
                                    frame_a_exibir = FrameExibido.BATALHA_FRAME;
                                    criarProximoFrame();
                                }
                                timer.stop();
                            }
                        }
                    });
                    timer.setInitialDelay(0);
                    
                    timer.start();



                    System.out.println(String.format("Damage = %.4f", dmg));
                }
                else if (vetor.length>2 && vetor[0] instanceof FrameExibido && vetor[1] instanceof Double && vetor[2] instanceof Integer && vetor[3] instanceof List)
                {
                    FrameExibido frame = (FrameExibido)vetor[0];
                    Double pontos_experiencia = (Double)vetor[1];
                    Integer dinheiro = (Integer)vetor[2];
                    List< ItemBase > lista_itens = (List)vetor[3];
                    TelaRecompenca tela = new TelaRecompenca(this,pontos_experiencia,dinheiro,lista_itens);
                }
            }
        }
    }
    
}

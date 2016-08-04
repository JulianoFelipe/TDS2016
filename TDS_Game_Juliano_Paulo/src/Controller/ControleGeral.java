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
import Model.Itens.ConsumivelBase;
import Model.Itens.EquipavelBase;
import Model.Itens.ItemBase;
import Model.Itens.PergaminhoHabilidade;
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
import javax.swing.JOptionPane;
import javax.swing.Timer;
import utilidades.Math.battle_math;

/**
 * Comunicador que recebe eventos(entradas da view) e gera telas de acordo com essa entrada, alem de receber modificações do modelo
 * @author Paulo
 */
public class ControleGeral implements Observer{
    AtaqueDefenderFrame ataquedefesa;
    BatalhaFrame arena_frame = null;
    private ArenaBatalha arena = null;
    private FrameExibido frameParaExibir = null;
    private Escolha escolha = null;
    private CriaturaBase criatura_alvo = null;
    private Heroi ultimo_heroi_selecionado = null;
    private List< CriaturaBase > opcoes_criaturas_alvos = null;
    private int indice = 0;
    private double dmg = -100.00;
    private HabilidadeBase habilidade;
    private ItemBase item = null;
    private String mensagem = null;
    private Jogador jogador;
    public static ControleGeral ultimo_controle = null;
    
    /**
     * Referencia a tela inicial que inicia controlador
     */
    private TelaInicial tela;
    
    /**
     * usado para segurar temporariamente uma referencia
     */
    private ConsumivelBase bufferDeItem;
    
    /**
     * usado para segurar temporariamente uma referencia a uma janela
     */
    private JFrame janelaBuffer;
   
    
    public ControleGeral(Jogador jogador,TelaInicial tela)
    {
        HabilidadeBase.controle = this;
        ultimo_controle = this;
        this.jogador = jogador;
        this.tela = tela;
    }
    
    public ControleGeral(Jogador jogador,FrameExibido frame,TelaInicial tela)
    {
        HabilidadeBase.controle = this;
        this.jogador = jogador;
        ultimo_controle = this;
        this.tela = tela;
        criarProximoFrame();
    }
    
    /**
     * Metodo para ajudar a selecionar uma opcao dentre uma lista
     */
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
                SeletorHabilidades seletor = new SeletorHabilidades( atacante.getListaDeHabilidades() , this , true);
            }
        }
    }
    
    /**
     * Se o usuario usa um ataque normal esse metodo eh chamado
     */
    private void attack()
    {
        if (arena != null)
        {
            CriaturaBase atacante = arena.getListaDeVivos().get(0);
            CriaturaBase defensor = arena.getMonstroVivosArray().get(indice);
            dmg = battle_math.calculate_damage(atacante, defensor, 1);
            Double[] vetor_parametros = new Double[5];
            vetor_parametros[0] = dmg;
            vetor_parametros[1] = new Double(0.00);
            vetor_parametros[2] = new Double(0.00);
            vetor_parametros[3] = new Double(0.00);
            vetor_parametros[4] = new Double(0.00);
            arena.modificarCriatura(vetor_parametros, atacante, defensor,true,0);
        }
    }
    
    public void criarProximoFrame()
    {
        if (arena == null)
        {
            //System.out.println("----------RESET DO CONTROLE GERAL-------------");
            for (Heroi herois_possiveis : jogador.getLista_de_herois())
            {
                herois_possiveis.resetTotallySkillsCD();
                herois_possiveis.heal(herois_possiveis.getMaxPontosVida());
                herois_possiveis.resetarAtributosTemporarios();
                herois_possiveis.getListaDeEfeitos().clear();
                herois_possiveis.setBarraAtaque(0.00);
            }
        }
        try{
        //System.out.println("criando proximo frame");
            if (frameParaExibir == FrameExibido.ARENA_INICIO)
            {
                    ArenaBatalha battle_arena = new ArenaBatalha(jogador,indice,0);
                    arena = battle_arena;
                    arena.addObserver(this);
                    arena.delayInicial();
            }
            else if (frameParaExibir == FrameExibido.REFRESH_BATALHA_FRAME || frameParaExibir == FrameExibido.BATALHA_FRAME)
            {
                if (arena!=null)
                {
                    arena.delayInicial();
                }
            }
            else if (frameParaExibir == FrameExibido.ATACAR_DEFENDER_FRAME)
            {
                attack();
            }
            else if (frameParaExibir == FrameExibido.ESCOLHER_ACAO)
            {
                if (arena_frame!=null)
                {
                    arena_frame.dispose();
                }
                CriaturaBase criatura_escolhendo = arena.getBaseCreatureAt(0);
                EscolhaFrame escolha = new EscolhaFrame(this,criatura_escolhendo);
            }
            else if (frameParaExibir == FrameExibido.SKILL_SELECIONADA)
            {
                CriaturaBase criatura_usando_skill = arena.getBaseCreatureAt(0);
                HabilidadeBase habilidade_usada = criatura_usando_skill.getListaDeHabilidades().get(indice);
                JFrame habilidade_utilizada = new HabilidadeUtilizada(this,criatura_usando_skill,habilidade_usada,false,false);
            }
            else if (frameParaExibir == FrameExibido.SKILL_SELECIONADA_MONSTRO)
            {
                if (arena_frame!=null)
                {
                    arena_frame.dispose();
                }
                CriaturaBase criaturaUsandoHabilidade = arena.getBaseCreatureAt(0);
                HabilidadeBase habilidadeUtilizada = habilidade;
                //System.out.println("----------APAGAR HABILIDADE = " + habilidade.getNome() + "----------------");
                JFrame habilidade_utilizada = new HabilidadeUtilizada(this,criaturaUsandoHabilidade,habilidadeUtilizada,false,true);
            }
            else if (frameParaExibir == FrameExibido.SKILL_USADA)
            {
                CriaturaBase criatura_usando_skill = arena.getBaseCreatureAt(0);
                HabilidadeBase habilidade_usada = habilidade;
                if (criatura_alvo == null)
                {
                    habilidade_usada.noUso(arena);
                }
                else
                {
                    habilidade_usada.noUso(arena, criatura_alvo);
                }
                criatura_alvo = null;
            }
            else if (frameParaExibir == FrameExibido.ESCOLHER_CRIATURA_ATAQUE)
            {
                SeletorCriaturas seletor = new SeletorCriaturas( arena.getMonstroVivosArray() , this , Escolha.ATACAR);
            }
            else if (frameParaExibir == FrameExibido.ESCOLHER_CRIATURA_SKILL)
            {
                SeletorCriaturas seletor = new SeletorCriaturas( opcoes_criaturas_alvos , this , Escolha.SKILL);
            }
            else if (frameParaExibir == FrameExibido.INDICE_CRIATURA_ALVO_SKILL_ESCOLHIDA)
            {
                habilidade.noUso(arena, criatura_alvo);
                criatura_alvo = null;
            }
            else if (frameParaExibir == FrameExibido.TELA_INICIAL)
            {
                tela.setVisible(true);
            }
            else if (frameParaExibir == FrameExibido.ESCOLHER_UM_HEROI && escolha == null)
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
            else if (frameParaExibir == FrameExibido.ESCOLHER_UM_HEROI && escolha != null)
            {
                Heroi heroi_selecionado = jogador.getLista_de_herois().get(indice);
                ultimo_heroi_selecionado = heroi_selecionado;
                HeroiSelecionado frame = new HeroiSelecionado(jogador,heroi_selecionado,this);
                escolha = null;
            }
            else if (frameParaExibir == FrameExibido.INVENTARIO && escolha == null)
            {
                Inventario frame = new Inventario(jogador,this);
                janelaBuffer = frame;
            }
            else if (frameParaExibir == FrameExibido.INVENTARIO && escolha == Escolha.ITEM_ESCOLHIDO)
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
            else if (frameParaExibir == FrameExibido.INVENTARIO && escolha == Escolha.INDICE_ESCOLHIDO)
            {
                boolean caminhoAlternativo = false;
                Heroi heroi_selecionado = jogador.getLista_de_herois().get(indice);
                item.setHeroi(heroi_selecionado);
                if (item instanceof ConsumivelBase)
                {
                    if (item instanceof PergaminhoHabilidade)
                    {
                        ConsumivelBase item_especifico = (ConsumivelBase)item;
                        //tem que ver se nao tem 4 habilidades
                        //se tiver tem que remover uma antes de adicionar outra
                        ultimo_heroi_selecionado = heroi_selecionado;
                        if (heroi_selecionado.getListaDeHabilidades().size() <= 3)
                        {
                            //pode adicionar sem problema
                            item_especifico.onConsume();
                            mensagem = "Usou item " + item_especifico.getNome() + " em " + heroi_selecionado.getNome();
                            JOptionPane.showMessageDialog(null, mensagem);
                        }
                        else
                        {
                            //se for adicionar tem que remover alguma
                            item.setHeroi(null);
                            JOptionPane.showMessageDialog(null, "Heroi ja possue 4 habilidades, se quiser adicionar mais uma por favor remova uma habilidade");
                            caminhoAlternativo = true;
                            bufferDeItem = item_especifico;
                            if (janelaBuffer != null)
                            {
                                janelaBuffer.dispose();
                                janelaBuffer = null;
                            }
                            SeletorHabilidades seletor = new SeletorHabilidades( heroi_selecionado.getListaDeHabilidades() , this , false);
                        }
                    }
                    else
                    {
                        ConsumivelBase item_especifico = (ConsumivelBase)item;
                        item_especifico.onConsume();
                        mensagem = "Usou item " + item_especifico.getNome() + " em " + heroi_selecionado.getNome();
                        JOptionPane.showMessageDialog(null, mensagem);
                    }
                }
                else if (item instanceof EquipavelBase)
                {
                    EquipavelBase item_especifico = (EquipavelBase)item;
                    item_especifico.onEquip();
                    mensagem = "Equipou " + item_especifico.getNome() + " em " + heroi_selecionado.getNome();
                    JOptionPane.showMessageDialog(null, mensagem);
                }
                if (!caminhoAlternativo)
                {
                    frameParaExibir = FrameExibido.INVENTARIO;
                escolha = null;
                criarProximoFrame();
                }
            }
            else if (frameParaExibir == FrameExibido.PROCURANDO_ARMA_PARA_CRIATURA && escolha == null)
            {
                List< ItemBase > armas = jogador.getArmas();
                SeletorDeItem seletor = new SeletorDeItem(armas,this);
            }
            else if (frameParaExibir == FrameExibido.PROCURANDO_ARMA_PARA_CRIATURA && escolha != null)
            {
                item.setHeroi(ultimo_heroi_selecionado);
                if (item instanceof EquipavelBase)
                {
                    EquipavelBase item_equipavel  = (EquipavelBase)item;
                    item_equipavel.onEquip();
                    mensagem = "Equipou " + item_equipavel.getNome() + " em " + ultimo_heroi_selecionado.getNome();
                    JOptionPane.showMessageDialog(null, mensagem);
                }
                frameParaExibir = FrameExibido.ESCOLHER_UM_HEROI;
                escolha = Escolha.INDICE_ESCOLHIDO;
                criarProximoFrame();
            }
            else if (frameParaExibir == FrameExibido.PROCURANDO_ARMADURA_PARA_CRIATURA && escolha == null)
            {
                List< ItemBase > armaduras = jogador.getArmaduras();
                SeletorDeItem seletor = new SeletorDeItem(armaduras,this);
            }
            else if (frameParaExibir == FrameExibido.PROCURANDO_ARMADURA_PARA_CRIATURA && escolha != null)
            {
                item.setHeroi(ultimo_heroi_selecionado);
                if (item instanceof EquipavelBase)
                {
                    EquipavelBase item_equipavel  = (EquipavelBase)item;
                    item_equipavel.onEquip();
                    mensagem = "Equipou " + item_equipavel.getNome() + " em " + ultimo_heroi_selecionado.getNome();
                    JOptionPane.showMessageDialog(null, mensagem);
                }
                frameParaExibir = FrameExibido.ESCOLHER_UM_HEROI;
                escolha = Escolha.INDICE_ESCOLHIDO;
                criarProximoFrame();
            }
            else if (frameParaExibir == FrameExibido.TELA_INICIAL_E_MENSAGEM)
            {
                tela.setVisible(true);
                JOptionPane.showMessageDialog(tela, mensagem);
            }
            else if (frameParaExibir == FrameExibido.LOJA)
            {
                Loja frame = new Loja(jogador,this);
            }
            else if (frameParaExibir == FrameExibido.SKILL_SUBSTITUIDA)
            {
                ultimo_heroi_selecionado.getListaDeHabilidades().remove(indice);
                bufferDeItem.setHeroi(ultimo_heroi_selecionado);
                bufferDeItem.onConsume();
                frameParaExibir = FrameExibido.INVENTARIO;
                escolha = null;
                criarProximoFrame();
            }
            else if (frameParaExibir == FrameExibido.TELA_BATALHA_CONFIGURACOES)
            {
                JFrame batalhaConfig = new SelecionarNivelBatalha(this,jogador.getMaiorandar());
                //JFrame batalhaConfig = new SelecionarNivelBatalha(this,5);
            }
            else if (frameParaExibir == FrameExibido.ARENA_BATALHAR_CONTRA_CHEFE)
            {
                    ArenaBatalha battle_arena = new ArenaBatalha(jogador,0,indice);
                    arena = battle_arena;
                    arena.addObserver(this);
                    arena.delayInicial();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("------------UPDATE CONTROLEARENA---------------");
        if (o instanceof ArenaBatalha && arg instanceof FrameExibido)
        {
            ArenaBatalha arena = (ArenaBatalha)o;
            FrameExibido frame = (FrameExibido)arg;
            
            if (frame == FrameExibido.BATALHA_FRAME)
            {
                //System.out.println("BATALHA_FRAMEEEEEEEEEEEEEEEE");
                if (arena_frame!=null)
                {
                    arena_frame.dispose();
                }
                try {
                    arena_frame = new BatalhaFrame(arena.getListaDeVivos(),this,false);
                    arena_frame.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(ControleGeral.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if (frame == FrameExibido.REFRESH_BATALHA_FRAME)
            {
                if (arena_frame!=null)
                {
                    arena_frame.dispose();
                }
                try {
                    arena_frame = new BatalhaFrame(arena.getListaDeVivos(),this,true);
                    arena_frame.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(ControleGeral.class.getName()).log(Level.SEVERE, null, ex);
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
            else if (frame == FrameExibido.GAME_OVER)
            {
                if (arena_frame!=null)
                {
                    arena_frame.dispose();
                }
                TelaDeFimDeJogo telaDeFim = new TelaDeFimDeJogo();
                telaDeFim.pack();
                ViewGlobal.centralizarJanela(telaDeFim);
                telaDeFim.setVisible(true);
            }
        }
        else
        {
            if (arg instanceof Object[])
            {
                //System.out.println("ativado 1!");
                Object[] vetor = (Object[])arg;
                if (vetor.length > 10 && vetor[0] instanceof FrameExibido && vetor[1] instanceof Double && vetor[2] instanceof CriaturaBase && vetor[3] instanceof CriaturaBase && vetor[4] instanceof Boolean && vetor[5] instanceof Double && vetor[6] instanceof Double && vetor[7] instanceof Double && vetor[8] instanceof Double && vetor[9] instanceof Integer && vetor[10] instanceof Boolean)
                {
                    System.out.println("------------CRIANDO ATACARDEFENDER FRAME-------------------");
                    //System.out.println("ativado 2");
                    if (arena_frame != null)
                    {
                        arena_frame.dispose();
                    }
                    dmg = (Double)vetor[1];
                    //System.out.println("APAGAR  CONTROLE UPDATE dmg = " + (new Double(dmg).intValue()) );
                    Boolean deveZerar = (Boolean)vetor[10];
                    Double ataque = (Double)vetor[5];
                    Double defesa = (Double)vetor[6];
                    Double velocidade = (Double)vetor[7];
                    Double barraAtaque = (Double)vetor[8];
                    Integer tipoDeFrame = (Integer)vetor[9];
                    CriaturaBase atacante = (CriaturaBase)vetor[2];
                    CriaturaBase defensor = (CriaturaBase)vetor[3];
                    //System.out.println("------------DEFENSOR = " + defensor.getNome() + "------------------");
                    boolean deve_criar_janela = (Boolean)vetor[4];
                    final long start = System.currentTimeMillis();
                    final long animationTime = ConfiguracoesDeTempo.getInstance().getTempo_total();
                    final int delay = new Double( Math.ceil((animationTime+0.00)/10.00) ).intValue();
                    final Timer timer = new Timer(delay, null);
                    double vida_antes = 0.00;
                    double vida_depois = defensor.getPontosVida();
                    if (tipoDeFrame == 0)
                    {
                        vida_antes = defensor.getPontosVida() + dmg;
                    }
                    else if (tipoDeFrame == 1)
                    {
                        //System.out.println("Vida max alvo = " + defensor.getMaxPontosVida());
                        //System.out.println("Vida curada = " + dmg);
                        //System.out.println("Vida depois = " + vida_depois);
                        //System.out.println("barra aumento = " + barraAtaque);
                        vida_antes = defensor.getPontosVida() - dmg;
                    }
                    else
                    {
                        System.err.println("-----------ERRO TIPO INESPERADO!!!!--------------");
                    }
                    /*
                    System.out.println("defensor.ataqueBarInicio = " + (defensor.getBarraAtaque()*100/CriaturaBase.ATTACK_BAR_TO_MOVE));
                    defensor.incAttackBar(barraAtaque.intValue());
                    System.out.println("defensor.ataqueBarDepois1 = " + (defensor.getBarraAtaque()*100/CriaturaBase.ATTACK_BAR_TO_MOVE));
                    defensor.decAttackBar(barraAtaque.intValue());
                    System.out.println("defensor.ataqueBarDepois = " + (defensor.getBarraAtaque()*100/CriaturaBase.ATTACK_BAR_TO_MOVE));
                    */
                    try {
                        if (ataque >= 0)
                        {
                            ataquedefesa = new AtaqueDefenderFrame(atacante,defensor,tipoDeFrame);
                        }
                        else
                        {
                            ataquedefesa = new AtaqueDefenderFrame(atacante,defensor,tipoDeFrame);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(ControleGeral.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    if (tipoDeFrame == 0)
                    {
                        ataquedefesa.setText(String.format("Damage = %.4f", dmg));
                    }
                    else if (tipoDeFrame == 1)
                    {
                        ataquedefesa.setText(String.format("Cura = %.4f", dmg));
                    }
                    double dmg_parcial = dmg/10.00;
                    double ataqueParcial = ataque/10.00;
                    double defesaParcial = defesa/10.00;
                    double velocidadeParcial = velocidade/10.00;
                    Double atkBarParcial = (barraAtaque/10.00);
                    //System.out.println("dmg_parcial = " + dmg_parcial);
                    defensor.setPontosVida(vida_antes);
                    timer.addActionListener(new ActionListener() {
                        int contador = 0;
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            final long now = System.currentTimeMillis();
                            final long elapsed = now - start;
                            //System.out.println("contador = " + contador);
                            //System.out.println("ataque = " + defensor.getEffectiveAttack());
                            //System.out.println("Defensor BarraAtaque = " + (defensor.getBarraAtaque()*100/CriaturaBase.ATTACK_BAR_TO_MOVE));
                            //System.out.println("Parcial = " + atkBarParcial);
                            contador++;
                            if (ataquedefesa != null)
                            {
                                if (tipoDeFrame == 0)
                                {
                                    defensor.takeDamage(dmg_parcial);
                                    defensor.decAttack(ataqueParcial);
                                    defensor.decDefense(defesaParcial);
                                    defensor.decSpeed(velocidadeParcial);
                                    defensor.decAttackBar(atkBarParcial.intValue());
                                }
                                else
                                {
                                    defensor.heal(dmg_parcial);
                                    defensor.incAttack(ataqueParcial);
                                    defensor.incDefense(defesaParcial);
                                    defensor.incSpeed(velocidadeParcial);
                                    defensor.incAttackBar(atkBarParcial.intValue());
                                }
                                try {
                                    ataquedefesa.updateDefensor(defensor);
                                    ataquedefesa.pack();
                                } catch (IOException ex) {
                                    Logger.getLogger(ControleGeral.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            if (elapsed >= animationTime) {
                                if (ataquedefesa!=null)
                                {
                                    ataquedefesa.dispose();
                                }
                                defensor.setPontosVida(vida_depois);
                                //defensor.setBarraAtaque(ataqueBarDepois);
                                if (deve_criar_janela)
                                {
                                    System.out.println("criando janela!");
                                    frameParaExibir = FrameExibido.BATALHA_FRAME;
                                    criarProximoFrame();
                                }
                                timer.stop();
                            }
                        }
                    });
                    //System.out.println("ataque = " + defensor.getEffectiveAttack());
                    System.out.println("tipoDeFrame = " + tipoDeFrame);
                    if (true)
                    {
                        if (tipoDeFrame == 0)
                        {
                            defensor.incAttackBar(barraAtaque.intValue());
                        }
                        else if (tipoDeFrame.equals(1))
                        {
                            defensor.decAttackBar(barraAtaque.intValue());
                        }
                    }
                    if (tipoDeFrame == 0)
                    {
                        defensor.incDefense(defesa);
                        defensor.incSpeed(velocidade);
                        defensor.incAttack(ataque);
                    }
                    else if (tipoDeFrame == 1)
                    {
                        defensor.decDefense(defesa);
                        defensor.decSpeed(velocidade);
                        //System.out.println("ataque antes = " + defensor.getEffectiveAttack());
                        defensor.decAttack(ataque);
                        //System.out.println("ataque depois = " + defensor.getEffectiveAttack());
                    }
                    else
                    {
                        defensor.setBarraAtaque(0.00);
                    }
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
                    arena = null;
                    if (arena_frame!=null)
                    {
                        arena_frame.dispose();
                    }
                    TelaRecompenca tela = new TelaRecompenca(this,pontos_experiencia,dinheiro,lista_itens);
                }
                else
                {
                    System.err.println("------------PARAMETROS INCORRETOS--------------");
                }
            }
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    
    
    
    

    public void setAtaquedefesa(AtaqueDefenderFrame ataquedefesa) {
        this.ataquedefesa = ataquedefesa;
    }

    public void setFrameParaExibir(FrameExibido frame_a_exibir) {
        this.frameParaExibir = frame_a_exibir;
    }

    public void setEscolha(Escolha escolha) {
        this.escolha = escolha;
    }

    public void setCriatura_alvo(CriaturaBase criatura_alvo) {
        this.criatura_alvo = criatura_alvo;
    }

    public void setOpcoes_criaturas_alvos(List<CriaturaBase> opcoes_criaturas_alvos) {
        this.opcoes_criaturas_alvos = opcoes_criaturas_alvos;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public void setDmg(double dmg) {
        this.dmg = dmg;
    }

    public void setHabilidade(HabilidadeBase habilidade) {
        this.habilidade = habilidade;
    }

    public void setItem(ItemBase item) {
        this.item = item;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public static void setUltimo_controle(ControleGeral ultimo_controle) {
        ControleGeral.ultimo_controle = ultimo_controle;
    }

    public void setBufferDeItem(ConsumivelBase bufferDeItem) {
        this.bufferDeItem = bufferDeItem;
    }

    public void setJanelaBuffer(JFrame janelaBuffer) {
        this.janelaBuffer = janelaBuffer;
    }
    
    //</editor-fold>
}

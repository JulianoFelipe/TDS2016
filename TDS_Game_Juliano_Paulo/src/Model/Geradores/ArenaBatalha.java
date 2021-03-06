 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Geradores;

import Controller.ConfiguracoesDeTempo;
import View.FrameExibido;
import Model.Criaturas.Escolha;
import Model.Criaturas.CriaturaBase;
import Model.Criaturas.Heroi;
import Model.Criaturas.Jogador;
import Model.Criaturas.Monstro;
import Model.Criaturas.MonstroIA;
import Model.Criaturas.MonstrosPersonalizados.AranhaRainha;
import Model.Criaturas.MonstrosPersonalizados.DragaoFogo;
import Model.Criaturas.MonstrosPersonalizados.DragaoGelo;
import Model.Criaturas.MonstrosPersonalizados.DragaoVento;
import Model.Itens.ItemBase;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import javax.swing.Timer;
import utilidades.Math.turn_order_math;

/**
 * Arena que controlará os estados das criaturas em batalha(Herois e Monstro)
 * @author Paulo
 */
public class ArenaBatalha extends Observable{
    private final List< CriaturaBase > lista_criaturas;
    private final List< CriaturaBase > lista_mortos;
    private final Jogador jogador;

    /**
     * Constante que define o numero maximo de inimigos
     */
    public static int maxNumeroDeInimigos = 3;

    /**
     * Constante que define o numero minimo de inimigos
     */
    public static final int MIN_NUMERO_DE_INIMIGOS = 1;

    /**
     * Por hora constante nao faz nada alem de definir qualidade dos drops
     */
    public static final int AVERAGE_MONSTER_LEVEL = 1;
    
    /**
     * Para cada monstro chance de derrubar uma pot
     */
    public static final int CHANCE_DE_DROPAR_POT = 25;
    
    /**
     * Para cada monstro chance de derrubar ou armadura ou arma quando morrer
     */
    public static final int CHANCE_DE_DROPAR_ARMA_ARMADURA = 40;
    
    /**
     * Boolean necessario para controlar certos loops
     */
    private boolean ativado = true;
    
    /**
     * Boolean necessario para controlar certos loops
     */
    private boolean ativado2 = true;
    
    /**
     * Andar corrente da arena
     */
    private Integer andar;

    public ArenaBatalha(Jogador jogador, int andar, int batalhaContraChefe)
    {
        System.out.println("maximo monstros = " + maxNumeroDeInimigos);
        this.jogador = jogador;
        this.andar = andar;
        lista_criaturas= new ArrayList<>();
        for (Heroi h : jogador.getLista_de_herois())
        {
            lista_criaturas.add(h);
        }
        lista_mortos = new ArrayList<>();
        if (batalhaContraChefe == 0)
        {
            initArena();
        }
        else
        {
            iniciarBatalhaContraChefe(batalhaContraChefe);
        }
    }
    
    public List< CriaturaBase > getListaDeVivos()
    {
        return(lista_criaturas);
    }
    
    public List< CriaturaBase > getListaDeMortos()
    {
        return(lista_mortos);
    }
    
    public List< CriaturaBase > getMonstroVivosArray()
    {
        List< CriaturaBase> retorno = new ArrayList<>();
        for (CriaturaBase creature : lista_criaturas)
        {
            if (creature instanceof Monstro)
            {
                if (creature.isAlive())
                {
                    retorno.add(creature);
                }
            }
        }
        return(retorno);
    }
    
    public CriaturaBase getBaseCreatureAt(int indice)
    {
        return( lista_criaturas.get(indice) );
    }
    
    /**
     * Inclue monstros na lista de criaturas
     */
    private void initArena()
    {
        Random generator = new Random();
        int numero_de_inimigos;
        if (maxNumeroDeInimigos == MIN_NUMERO_DE_INIMIGOS)
        {
            numero_de_inimigos = MIN_NUMERO_DE_INIMIGOS;
        }
        else
        {
            numero_de_inimigos = MIN_NUMERO_DE_INIMIGOS + generator.nextInt(maxNumeroDeInimigos - MIN_NUMERO_DE_INIMIGOS);
        }
        int monstro_level = 1;
        
        if (andar == 2)
        {
            monstro_level = 1;
        }

        for (int i = 0; i < numero_de_inimigos; i++) {
            Monstro new_monstro = GeradorMonstro.gerarMonstro(monstro_level,andar);
            new_monstro.setPontosVida(new_monstro.getMaxPontosVida());
            lista_criaturas.add(new_monstro);
        }
        onStart(lista_criaturas);
        Collections.sort(lista_criaturas);
    }
    
    public void iniciarBatalhaContraChefe(int andar)
    {
        switch (andar)
        {
            case 1 :
                Monstro chefe = new AranhaRainha();
                lista_criaturas.add(chefe);
                break;
            case 2 :
                Monstro chefe1 = new DragaoFogo();
                Monstro chefe2 = new DragaoVento();
                Monstro chefe3 = new DragaoGelo();
                lista_criaturas.add(chefe1);
                lista_criaturas.add(chefe2);
                lista_criaturas.add(chefe3);
                break;
            default :
                System.err.println("---------------INDICE INCORRETO EM INICIARBATALHACONTRACHEFE----------------");
                break;
        }
        onStart(lista_criaturas);
        Collections.sort(lista_criaturas);
    }
    
    /**
     * Metodo chamado quando vai alterar o estado de uma criatura, o controlador recebe esses parametros pelo notify e criar uma tela baseado neles
     * @param parametros_de_criatura array de tamanho 5 com parametros sobre vida,ataque,defesa,velocidade e barra de ataque
     * @param atacante criatura atacante ou que esta usando a habilidade
     * @param defensor criatura que sofre o ataque ou habilidade
     * @param deve_criar_janela se é a ultima acao da rodada
     * @param tipoDeEfeito se eh ofensivo ou defensivo
     */
    public void modificarCriatura(Double[] parametros_de_criatura,CriaturaBase atacante,CriaturaBase defensor,Boolean deve_criar_janela,Integer tipoDeEfeito)
    {
        Double vidaModificada = parametros_de_criatura[0];
        Double ataqueModificada = parametros_de_criatura[1];
        Double defesaModificada = parametros_de_criatura[2];
        Double velocidadeModificada = parametros_de_criatura[3];
        Double barraDeAtaqueModificada = parametros_de_criatura[4];
        if (tipoDeEfeito == 0)
        {
            System.out.println("--------------------ATAQUE---------------");
        }
        else if (tipoDeEfeito == 1)
        {
            System.out.println("--------------------SUPORTE---------------");
        }
        else
        {
            System.out.println("--------------------??????????---------------");
        }
        if (ativado2)
        {
            whenGetTurnPrimario(atacante);
            ativado2 = false;
        }
        //System.out.println("Heroi " + atacante.getNome() + " atacando " + defensor.getNome() + "!");

        //System.out.println("pontos de vida defensor antes do damage = " + defensor.getPontosVida());
        //guardar status antes
        Double variacaoDeVida = 0.00;
        if (tipoDeEfeito == 0)
        {
            variacaoDeVida = defensor.getPontosVida();
            defensor.takeDamage(vidaModificada);
            if (defensor.getPontosVida() > 0.00)
            {
                variacaoDeVida = variacaoDeVida - defensor.getPontosVida();
            }
        }
        else if (tipoDeEfeito == 1)
        {
            //System.out.println("barraDeAtaque Modificada = " + barraDeAtaqueModificada);
            variacaoDeVida = defensor.getPontosVida();
            defensor.heal(vidaModificada);
            variacaoDeVida = defensor.getPontosVida() - variacaoDeVida;
        }
        
        //System.out.println("pontos de vida defensor depois do damage = " + defensor.getPontosVida());  
        Boolean deveZerar = false;
        if (atacante == defensor)
        {
            System.out.println("-------DEVEZERAR = TRUE-----------");
            deveZerar = true;
        }

        Object array_object[] = new Object[11];
        array_object[0] = FrameExibido.ATACAR_DEFENDER_FRAME;
        array_object[1] = variacaoDeVida;
        array_object[2] = atacante;
        array_object[3] = defensor;
        array_object[4] = deve_criar_janela;
        array_object[5] = ataqueModificada;
        array_object[6] = defesaModificada;
        array_object[7] = velocidadeModificada;
        array_object[8] = barraDeAtaqueModificada;
        array_object[9] = tipoDeEfeito;
        array_object[10] = deveZerar;
        
        if (ativado)
        {
            whenGetTurn(atacante);
            ativado = false;
        }
        
        setChanged();
        notifyObservers(array_object);
        
    }
    
    /**
     * Metodo que informa a batalha por um tempo antes de o controlador exibir a proxima tela
     */
    public void delayInicial()
    {
        System.out.println("----------CHAMANDO DELAY INICIAL---------------");
        turn_order_math.nextTurn(lista_criaturas);
        Collections.sort(lista_criaturas);
        setChanged();
        notifyObservers( FrameExibido.BATALHA_FRAME );
        int delay = ConfiguracoesDeTempo.getInstance().getTempoBatalhaFrame();
        int animationTime = delay-10;
        final long start = System.currentTimeMillis();
        Timer timer = new Timer(delay, null);
        timer.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                final long now = System.currentTimeMillis();
                final long elapsed = now - start;
                if (elapsed >= animationTime) {
                    System.out.println("proxima!");
                    proximaEtapa();
                    timer.stop();
                }
            }
        });
        timer.setInitialDelay(0);

        timer.start();
    }
    
    /**
     * Atualiza vetor criaturas e informa controler sobre estado
     */
    private void proximaEtapa()
    {
        System.out.println("---------------CHAMANDO PROXIMA ETAPA---------------");
        if (!condicaoDeParada(lista_criaturas))
        {
            ativado = true;
            ativado2 = true;
            CriaturaBase local_creature = lista_criaturas.get(0);
            setChanged();
            notifyObservers( FrameExibido.REFRESH_BATALHA_FRAME );
            System.out.println(local_creature.getNome() + " esta agindo!");
            ArrayList< CriaturaBase> array_inimigo_vivo = new ArrayList<>();
            ArrayList< CriaturaBase> array_aliado_vivo = new ArrayList<>();
            if (local_creature instanceof Heroi) {
                //pegar acao
                if (local_creature.getEstaAtordoado())
                {
                    whenGetTurnPrimario(local_creature);
                    whenGetTurn(local_creature);
                    delayInicial();
                }
            }
            else if (local_creature instanceof Monstro) {
                System.out.println("monstro!");
                if (local_creature.getEstaAtordoado())
                {
                    System.out.println("esta atordoado!");
                    whenGetTurn(local_creature);
                    delayInicial();
                }
                else
                {
                for (CriaturaBase creature : lista_criaturas) {
                    if (creature instanceof Heroi) {
                        array_inimigo_vivo.add(creature);
                    } else if (creature instanceof Monstro) {
                        array_aliado_vivo.add(creature);
                    } else {
                        
                    }

                }
                Monstro local_monstro = (Monstro) local_creature;
                Escolha action = get_monstro_choice(local_monstro);
                Random generator = new Random();
                    if (action == Escolha.ATACAR) {
                        System.out.println("monstro atacando!");
                        ArrayList< Heroi> possible_targets_list = new ArrayList<>();
                        for (int i = 0; i < lista_criaturas.size(); i++) {
                            if (!lista_criaturas.get(i).isAlive()) {
                                break;
                            }
                            if (lista_criaturas.get(i) instanceof Heroi) {
                                Heroi local_hero = (Heroi) lista_criaturas.get(i);
                                possible_targets_list.add(local_hero);
                            }
                        }
                        if (possible_targets_list.size() == 0) {
                            System.out.println("?????");
                        } else {
                            MonstroIA.decidirAcao(this, local_creature, array_aliado_vivo, array_inimigo_vivo);
                        }
                    } else if (action == Escolha.SKILL) {
                        /*
                        //por enquanto escolhera skill aleatoriamente dentre as possibilidades
                        ArrayList< HabilidadeBase> possible_skills = local_monstro.getUsableSkillsArray();
                        int skill_indice = generator.nextInt(possible_skills.size());
                        HabilidadeBase skill_usada = possible_skills.get(skill_indice);

                        System.out.println("Monstro esta usando skill -> " + skill_usada.getDescricao());
                        if (skill_usada.getTipo().equals("Ofensivo")) {
                            for (CriaturaBase creature : array_inimigo_vivo) {
                                skill_usada.transferEffect(creature);
                                //System.out.println("creature afetada->"+creature);
                            }
                        } else if (skill_usada.getTipo().equals("Defensivo")) {
                            for (CriaturaBase creature : array_aliado_vivo) {
                                skill_usada.transferEffect(creature);
                                //System.out.println("creature afetada->"+creature);
                            }
                        } else {
                            System.out.println("tipo = " + skill_usada.getTipo());

                        }
                        skill_usada.onUse();
                        */
                    }
                }
            } else {
                System.out.println("Erro grave 3");
            }
        }
        else
        {
            System.out.println("fim da batalha!");
            onEnd(lista_criaturas,lista_mortos);
        }
    }

    /**
     * Chamada todo turno
     *
     * @param entry Collections de base creature que sera avaliada
     */
    public void checarTodoTurno(Collection<CriaturaBase> entry) {
        //checar quem esta vivo e remover quem esta morto
        System.out.println("fazendo checagen de mortos!");
        if (entry instanceof ArrayList) {
            ArrayList< CriaturaBase> creature_array = (ArrayList) entry;

            for (int i = 0; i < creature_array.size(); i++) {
                //System.out.println("valor de i = " + i);
                CriaturaBase local_creature = creature_array.get(i);
                if (!local_creature.isAlive()) {
                    //System.out.println("break");
                    break;
                }
                if (local_creature.getPontosVida() <= 0) {
                    //System.out.println("creature " + local_creature.getNome() + " esta morta com " + local_creature.getPontosVida());
                    local_creature.onDeath();
                    //muda de fila de vivos para mortos
                    lista_criaturas.remove( local_creature );
                    lista_mortos.add( local_creature );
                    i = i - 1;
                }
                else
                {
                    //System.out.println("creature " + local_creature.getNome() + " esta viva com " + local_creature.getPontosVida());
                }
                local_creature.everyTime();
            }
        } else {
            throw new UnsupportedOperationException("Logica implementada somente sobre ArrayList no momento!");
        }
    }

    /**
     * Metodo chamada para criatura que ganhou o turno
     *
     * @param creature_que_ganhou_turno creature que ganhou o turno
     */
    public void whenGetTurn(CriaturaBase creature_que_ganhou_turno) {
        //faz algo com ela :)
        if (ativado)
        {
            System.out.println("---------------WHEN GET TURN ATIVADO---------------");
            creature_que_ganhou_turno.everyTurn();
        }
    }
    
    /**
     * Metodo chamada para criatura que ganhou o turno
     * @param creature_que_ganhou_turno creature que ganhou o turno
     */
    public void whenGetTurnPrimario(CriaturaBase creature_que_ganhou_turno) {
        //faz algo com ela :)
        if (ativado2)
        {
            creature_que_ganhou_turno.setBarraAtaque(0.00);
            creature_que_ganhou_turno.aplicarTodosOsEfeitos(1);
        }
    }

    /**
     * Metodo chamada no comeco da batalha
     *
     * @param coll collection de CriaturaBase
     */
    public void onStart(Collection< CriaturaBase> coll) {
        for (CriaturaBase creature : coll) {
            creature.onStart();
        }
        //display_battle_info_simplified(coll);

    }

    /**
     * Metoda chamando sempre que a batalha termina
     *
     * @param lista_vivos  Lista com criaturas vivas.
     * @param lista_mortos Lista com criaturas mortas.
     */
    public void onEnd(List< CriaturaBase> lista_vivos,List< CriaturaBase > lista_mortos) {
        ArrayList< CriaturaBase > lista_global = new ArrayList<>();
        for (CriaturaBase c : lista_vivos)
        {
            lista_global.add(c);
        }
        for (CriaturaBase c : lista_mortos)
        {
            lista_global.add(c);
        }
        ArrayList< Heroi> heroes = new ArrayList<>();
        ArrayList< Monstro> monstros = new ArrayList<>();
        for (CriaturaBase c : lista_global) {
            if (c instanceof Heroi) {
                Heroi hero = (Heroi) c;
                heroes.add(hero);
            } else if (c instanceof Monstro) {
                Monstro monstro = (Monstro) c;
                monstros.add(monstro);
            } else {
                
            }
        }

        //indica se há um monstro vivo pelo menos, ou seja herois perderam e deve-se executar gameover
        boolean someoneAlive = false;
        for (Monstro c : monstros) {
            if (c instanceof AranhaRainha)
            {
                if (jogador.getMaiorandar().equals(1))
                {
                    jogador.setMaiorandar(2);
                }
            }
            if (c.isAlive()) {
                someoneAlive = true;
                break;
            }
        }
        if (someoneAlive) {
            onGameOver();
        } else {
            Random generator = new Random();
            Double xp_pool = 0.00;
            Integer gold_pool = 0;
            int quantia_de_itens = 0;
            List< ItemBase > lista_de_drops = new ArrayList<>();
            for (Monstro c : monstros) {
                xp_pool = xp_pool + c.getLevel() * 100;
                gold_pool = gold_pool + c.getLevel() * 500;
                int rolador = generator.nextInt(100);
                if (rolador>CHANCE_DE_DROPAR_POT)
                {
                    ItemBase item = GeradorItem.generateStatusIncreasePotion(c.getLevel());
                    lista_de_drops.add(item);
                    quantia_de_itens++;
                }
                rolador = generator.nextInt(100);
                if (rolador>CHANCE_DE_DROPAR_ARMA_ARMADURA)
                {
                    ItemBase item = GeradorItem.gerarArmaArmadura(c.getLevel());
                    lista_de_drops.add(item);
                    quantia_de_itens++;
                }
            }

            System.out.println("Os herois ganharam " + xp_pool + " Experience Points, " + gold_pool + " Pecas de ouro e " + quantia_de_itens + " itens!\n");
            jogador.addGold(gold_pool);
            jogador.addItem(lista_de_drops);
            for (Heroi c : heroes) {
                if (c instanceof Heroi) {
                    Heroi local_hero = (Heroi) c;
                    local_hero.addXP(xp_pool);

                } else {
                    
                }
            }
            Object array_object[] = new Object[4];
            array_object[0] = FrameExibido.TELA_RECOMPENCA;
            array_object[1] = xp_pool;
            array_object[2] = gold_pool;
            array_object[3] = lista_de_drops;
            
            setChanged();
            notifyObservers(array_object);
            System.out.println("");
        }

    }

    /**
     * Se prescissar algum tratamento adicional quando o jogo terminar em game
     * over ele é feito aqui
     *
     */
    public void onGameOver() {
        FrameExibido frame = FrameExibido.GAME_OVER;
        setChanged();
        notifyObservers(frame);
    }

    /**
     * Checa se a batalha já chegou ao fim, ou seja todos os herois estao mortos
     * ou todos os monstros estao mortos.
     *
     * @param criaturaLista Collection com criaturas parcipando da batalha.
     * @return               True se batalha terminou, False caso contrario.
     */
    public boolean condicaoDeParada(Collection<CriaturaBase> criaturaLista) {
        checarTodoTurno(criaturaLista);
        int numero_de_herois = 0;
        int numero_de_monstros = 0;
        for (CriaturaBase local_creature : criaturaLista) {
            if (local_creature.isAlive()) {
                //System.out.println("BaseCreature nome = " + local_creature.getNome() + ", isalive = " + local_creature.isAlive() + ", vida = " + local_creature.getPontosVida() );
                if (local_creature instanceof Heroi) {
                    numero_de_herois++;
                } else {
                    if (local_creature instanceof Monstro) {
                        numero_de_monstros++;
                    } else {
                        System.out.println("Erro Grave 2");
                    }
                }
            }
        }
        //System.out.println("heroi = "+numero_de_herois + ",monstro = "+numero_de_monstros);
        if (numero_de_herois == 0 || numero_de_monstros == 0) {
            return (true);
        }
        return (false);
    }

    /**
     * A ação do monstro
     *
     * @param local_monstro Que executará a ação.
     * @return              Ação que o monstro executará.
     */
    public Escolha get_monstro_choice(Monstro local_monstro) {
        /*
        //se ele tem pelo menos uma skill para usar ele vai usar
        if (local_monstro.getUsableSkillsArray().size() > 0 || false) {
            return (CriaturaBase.SKILL_PROTOCOL);
        } else {
            //System.out.println("Monstro nao pode usar skill pois "+local_monstro.getUnusableSkills());
        }
        */
        return (Escolha.ATACAR);
    }
}

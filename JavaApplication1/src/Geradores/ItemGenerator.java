/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geradores;

import ItensPackage.*;
import SkillPackage.BaseSkill;
import java.util.Random;

/**
 * Gerador de itens aleatorio, por hora todos os itens sao gerados aqui
 *
 */
public class ItemGenerator {

    /**
     * constante que indica chance de um drop ocorrer por monstro morto ao final
     * de uma batalha
     */
    public static final int CHANCE_OF_DROP = 100;

    /**
     * Gera um item aleatorio dentre 3 possibilidades
     *
     * @param level Do item a ser retornado.
     * @return      Item retornado.
     */
    public static BaseItem generateItem(int level) {
        BaseItem retorno = null;
        Random generator = new Random();
        int valor = generator.nextInt(4);
        switch (valor) {
            case 0:
                retorno = generateStatusIncreasePotion(level);
                break;
            case 1:
                retorno = generateBaseArmor(level);
                break;
            case 2:
                retorno = generateBaseWeapon(level);
                break;
            case 3:
                retorno = generateSkillScroll(level);
                break;
            default:
                System.out.println("Essa msg nao deve aparecer em ItemGenerator");
                break;
        }
        return (retorno);
    }

    /**
     * Gera uma StatusIncreasePotion aleatoria
     *
     * @param level Do item a ser retornado.
     * @return      Potion gerada.
     */
    public static StatusIncreasePotion generateStatusIncreasePotion(int level) {
        Random generator = new Random();
        int tipo_potion = generator.nextInt(StatusIncreasePotion.MAX_RANDOM_VALOR + 1);
        //por enquanto potion strenght eh fixo em 10
        double potion_strenght = 10.00 * level;
        StatusIncreasePotion retorno = new StatusIncreasePotion(tipo_potion, potion_strenght);
        retorno.setAutomaticNome();
        return (retorno);
    }

    /**
     * Gera uma arma aleatoria
     *
     * @param level Da arma, afeta status dela.
     * @return      Uma arma.
     */
    public static BaseWeapon generateBaseWeapon(Integer level) {
        Random generator = new Random();
        BaseWeapon retorno = new BaseWeapon();
        Double base_damage_multiplier = 1.00;
        Integer rarity = generator.nextInt(4);
        base_damage_multiplier = base_damage_multiplier + level / 10.00 * (rarity + 1) * (rarity + 1);
        retorno.setDamage_increase(base_damage_multiplier);
        retorno.setLevel(level);
        switch (rarity) {
            case 0:
                retorno.setRaridade("Normal");
                break;
            case 1:
                retorno.setRaridade("Rare");
                break;
            case 2:
                retorno.setRaridade("Epica");
                break;
            case 3:
                retorno.setRaridade("Lendaria");
                break;
            default:
                retorno.setRaridade("Unnendified");
                break;
        }

        Integer tipo = generator.nextInt(2);
        switch (tipo) {
            case 0:
                retorno.setTipo("Sword");
                break;
            case 1:
                retorno.setTipo("Staff");
                break;
            default:
                retorno.setTipo("NotAWeapon");
                break;
        }

        retorno.setValor((rarity + 1) * (rarity + 1) * level * 500);

        retorno.setNome(retorno.getRaridade() + retorno.getTipo().toString() + "lv" + level.toString());

        return (retorno);
    }

    /**
     * Gerador de armaduras aleatorio
     *
     * @param level Da armadura, afeta status da armadura
     * @return uma armadura
     */
    public static BaseArmor generateBaseArmor(Integer level) {
        Random generator = new Random();
        BaseArmor retorno = new BaseArmor();
        Double base_damage_multiplier = 1.00;
        Integer rarity = generator.nextInt(4);
        base_damage_multiplier = base_damage_multiplier + level / 10.00 * (rarity + 1) * (rarity + 1);
        retorno.setDefense_increase(base_damage_multiplier);
        retorno.setLevel(level);
        switch (rarity) {
            case 0:
                retorno.setRaridade("Normal");
                break;
            case 1:
                retorno.setRaridade("Rare");
                break;
            case 2:
                retorno.setRaridade("Epica");
                break;
            case 3:
                retorno.setRaridade("Lendaria");
                break;
            default:
                retorno.setRaridade("Unnendified");
                break;
        }

        Integer tipo = generator.nextInt(2);
        switch (tipo) {
            case 0:
                retorno.setTipo("Armor");
                break;
            case 1:
                retorno.setTipo("Robe");
                break;
            default:
                retorno.setTipo("NotAWeapon");
                break;
        }

        retorno.setValor((rarity + 1) * (rarity + 1) * level * 500);

        retorno.setNome(retorno.getRaridade() + retorno.getTipo().toString() + "lv" + level.toString());

        return (retorno);
    }
    
    public static SkillScroll generateSkillScroll(Integer level)
    {
        SkillScroll retorno = new SkillScroll();
        BaseSkill skill = SkillGenerator.generate_skill();
        retorno.setSkill_associada(skill);
        retorno.setNome("Skill scroll de "+skill.getDescricao());
        retorno.setValor(10000);
        
        return(retorno);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geradores;
import ItensPackage.BaseItem;
import ItensPackage.StatusIncreasePotion;
import java.util.Random;
/**
 *
 * @author FREE
 */
public class ItemGenerator {
    /**
     * constante que indica chance de um drop ocorrer por monstro morto ao final de uma batalha
     */
    public static final int CHANCE_OF_DROP = 100;
    
    
    public static BaseItem generateItem()
    {
        BaseItem retorno = null;
        Random generator = new Random();
        int valor = generator.nextInt(1);
        switch (valor)
        {
            case 0:
                retorno = generateStatusIncreasePotion();
                break;
            default:
                System.out.println("Essa msg nao deve aparecer em ItemGenerator");
        }
        return(retorno);
    }
    
    public static StatusIncreasePotion generateStatusIncreasePotion()
    {
        Random generator = new Random();
        int tipo_potion = generator.nextInt(StatusIncreasePotion.MAX_RANDOM_VALOR+1);
        //por enquanto potion strenght eh fixo em 10
        double potion_strenght = 10.00;
        StatusIncreasePotion retorno = new StatusIncreasePotion(tipo_potion,potion_strenght);
        retorno.setAutomaticNome();
        return(retorno);
    }
}

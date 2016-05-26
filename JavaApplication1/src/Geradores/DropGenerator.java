/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geradores;
import ItensPackage.ItensInterface;
import java.util.Random;
import ItensPackage.HealingPotion;
/**
 * Classe responsavel por gerar drops(itens derrubados por monstros ou dinheiro).
 *
 * @author Paulo.Tenorio
 */
public class DropGenerator {
    public static ItensInterface gerar_item(int level_drop)
    {
        HealingPotion item_retorno = new HealingPotion(1);
        return(item_retorno);
    }
    
    public static Integer gerar_gold(int level_drop)
    {
        int min_range = level_drop*1;
        int max_range = level_drop*10;
        Random generator = new Random();
        Integer valor_retorno = generator.nextInt(max_range)+min_range;
        
        
        return(valor_retorno);
    }
}

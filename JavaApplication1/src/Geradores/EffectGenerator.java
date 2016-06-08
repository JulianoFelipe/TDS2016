/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geradores;
import EffectsPackage.*;
import java.util.Random;
/**
 *
 * @author FREE
 */
public class EffectGenerator {
    public static final int MAX_DURATION = 10;
    public static AtributesEffect getNewEffect()
    {
        Random generator = new Random();
        int valor = AtributesEffect.MIN_RANGE+generator.nextInt(AtributesEffect.MAX_RANGE+1);
        double valor_percentual = generator.nextInt(100)+0.00;
        AtributesEffect retorno = new AtributesEffect(valor_percentual,0.00,valor);
        retorno.setDuration(generator.nextInt(MAX_DURATION+1));
        return(retorno);
    }
}

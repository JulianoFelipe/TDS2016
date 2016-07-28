/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Geradores;

import Model.Efeitos.EfeitoAtributos;
import java.util.Random;

/**
 * Gera um efeito aleatorio
 *
 */
public class EffectGenerator {

    /**
     * Duracao maxima de um efeito
     */
    public static final int MAX_DURATION = 10;

    /**
     * Gera um efeito
     *
     * @return Um efeito.
     */
    public static EfeitoAtributos getNewEffect() {
        Random generator = new Random();
        int valor = EfeitoAtributos.MIN_RANGE + generator.nextInt(EfeitoAtributos.MAX_RANGE + 1);
        double valor_percentual = generator.nextInt(100) + 0.00;
        EfeitoAtributos retorno = new EfeitoAtributos(valor_percentual, 0.00, valor);
        retorno.setDuration(generator.nextInt(MAX_DURATION + 1));
        return (retorno);
    }
}

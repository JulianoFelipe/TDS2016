/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Geradores;

import Model.Criaturas.Heroi;
import Model.Criaturas.HeroisPersonalizados.Arthas;
import Model.Criaturas.HeroisPersonalizados.Cloe;
import Model.Criaturas.HeroisPersonalizados.Druida;
import Model.Criaturas.HeroisPersonalizados.Elesis;
import java.util.Random;


/**
 * Gera um heroi aleatorio
 * @author Paulo.Tenorio
 */
public class GeradorDeHeroi {
    public static Heroi gerarHeroi()
    {
        int numeroMaximo = 4;
        Random gerador = new Random();
        int numeroRandom = gerador.nextInt(numeroMaximo);
        Heroi retorno = null;
        switch (numeroRandom)
        {
            case 0 : 
                retorno = new Cloe(null);
                break;
            case 1 :
                retorno = new Arthas(null);
                break;
            case 2 :
                retorno = new Elesis(null);
                break;
            case 3 :
                retorno = new Druida(null);
                break;
        }
        return(retorno);
    }
}

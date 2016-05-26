/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author FREE
 */
public class Monstro extends BaseCreature{
    private static int numero_monstros=0;
    
    public Monstro()
    {
        super();
        numero_monstros++;
    }
    
    public static int getNumero_monstros()
    {
        return(numero_monstros);
    }
    
    public void DropLot()
    {
        
    }
}

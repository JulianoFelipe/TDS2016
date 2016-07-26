/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Criaturas;

/**
 *
 * @author Paulo Tenório
 */
public class Monstro extends CriaturaBase {

    private static int numero_monstros = 0;

    public Monstro() {
        super();
        numero_monstros++;
    }

    /**
     * Id do monstro
     *
     * @return id
     */
    public static int getNumero_monstros() {
        return (numero_monstros);
    }
}

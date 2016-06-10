/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geradores;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Juliano.Silva10
 */
public class MonstroNameGenerator extends NameGenerator {

    private final File hNames = new File(getClass().getResource("/Data/MonsterNames.txt").getFile());
    private final File hImprove_names = new File(getClass().getResource("/Data/ImprovedMonsterNames.txt").getFile());
    private final File hSpecial_names = new File(getClass().getResource("/Data/SpecialMonsterNames.txt").getFile());

    /**
     * Constrói um gerador de nomes aleatórios para monstros.
     *
     * @param MAX_NAMES Número máximo de palavras no nome.
     * @param CAN_REPEAT Se pode repetir nomes.
     * @param SPECIAL_NAMES Permitir nomes especiais.
     */
    public MonstroNameGenerator(int MAX_NAMES, boolean CAN_REPEAT, boolean SPECIAL_NAMES) {
        super(MAX_NAMES, CAN_REPEAT, SPECIAL_NAMES);
        super.setNames(hNames);
        super.setImproveNames(hImprove_names);
        super.setSpecialNames(hSpecial_names);
    }

    //Testador
    public static void main(String[] args) throws IOException {
        MonstroNameGenerator t = new MonstroNameGenerator(3, false, true);
        String name = t.generateImprovedName();
        System.out.println(name);

        name = t.generateSpecialName();
        System.out.println(name);

        name = t.generateRandomName();
        System.out.println(name);
    }
}

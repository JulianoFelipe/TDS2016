/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geradores;

import java.io.File;
import java.io.IOException;

/**
 * Gerador para nomes de heróis.
 * @author Juliano Silva
 */
public class GeradorNomeHeroi extends GeradorNome {

    private final File hSpecial_names = new File(getClass().getResource("/Data/NomeHeroiEspecial").getFile());
    private final File hNames = null;//new File (getClass().getResource("/Data/MonsterNames.txt").getFile());
    private final File hImprove_names = new File(getClass().getResource("/Data/NomeHeroiMelhorado").getFile());

    /**
     * Constrói um gerador de nomes aleatórios para heróis.
     * <p>
     * No momento, não há {@link NameGenerator#names}.
     *
     * @param MAX_NAMES Número máximo de palavras no nome.
     * @param CAN_REPEAT Se pode repetir nomes.
     * @param SPECIAL_NAMES Permitir nomes especiais.
     */
    public GeradorNomeHeroi(int MAX_NAMES, boolean CAN_REPEAT, boolean SPECIAL_NAMES) {
        super(MAX_NAMES, CAN_REPEAT, SPECIAL_NAMES);
        super.setNames(hNames);
        super.setImproveNames(hImprove_names);
        super.setSpecialNames(hSpecial_names);
    }

    @Override
    public String generateRandomName() {
        throw new java.lang.UnsupportedOperationException("Não existe arquivo de nomes aleatórios para heróis ainda!");
    }

    //Testador
    public static void main(String[] args) throws IOException {
        GeradorNomeHeroi t = new GeradorNomeHeroi(3, true, true);
        String name = t.generateImprovedName();
        System.out.println(name);

        name = t.generateSpecialName();
        System.out.println(name);

        Criaturas.Mago mago = new Criaturas.Mago();
        System.out.println(mago.getClass().getSimpleName());
    }
}

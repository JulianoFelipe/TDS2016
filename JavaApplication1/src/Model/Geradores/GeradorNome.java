/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Geradores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gerador genérico para nomes de monstros,
 * heróis, skills, etc.
 * @author Juliano.Silva10
 */
public class GeradorNome {

    /**
     * Arquivo de nomes especiais. Os nomes, até então, podem possuir três
     * palavras. e.g.: Leviata - 1 Conde Dracula - 2 The White Witch - 3 Um
     * cabeçalho é necessário para que seja válido.
     */
    protected File special_names;
    /**
     * Arquivo de nomes aleatórios. Um cabeçalho é necessário para que seja
     * válido.
     */
    protected File names;
    /**
     * Arquivo com nomes com três níveis de hierarquia. Um cabeçalho e
     * necessário para que seja válido.
     */
    protected File improve_names;

    /**
     * Número máximo de nomes gerados. Pode gerar menos que o máximo.
     */
    protected final int MAX_NAMES;
    /**
     * No caso de usar {@link NameGenerator#generateRandomName()}, permite ou
     * não a repetição de nomes.
     */
    protected final boolean CAN_REPEAT;
    /**
     * Permite ou não o uso de nomes especiais. Os nomes especiais sempre serão
     * gerados com uma, duas ou três palavras, dependedo de
     * {@link NameGenerator#MAX_NAMES}. E.g.: MAX_NAMES = 2 Nome de duas
     * palavras. {@code MAX_NAMES > 2 } Nome de três palavras.
     */
    protected final boolean SPECIAL_NAMES;

    /**
     * Constroi um gerador de nomes.
     *
     * @param MAX_NAMES Número máximo de nomes gerados; separados por espaço.
     * Ex.: 2 - Fire Orc.
     * @param CAN_REPEAT Se os nomes podem repetir (Ex.: False - Orc Orc).
     * Maybe?
     * @param SPECIAL_NAMES Gerar (ou não) nomes especiais (Ex.: Count Dracula).
     * @param names Arquivo com nomes aleatórios.
     * @param improve_names Arquivo com nomes com três níveis de hierarquia.
     * @param special_names Arquivo com nomes especiais (De um a três nomes).
     * e.g.: Leviata - 1 Conde Dracula - 2 The White Witch - 3
     */
    public GeradorNome(int MAX_NAMES, boolean CAN_REPEAT, boolean SPECIAL_NAMES, File names, File improve_names, File special_names) {
        this.names = names;
        this.improve_names = improve_names;
        this.special_names = special_names;

        this.MAX_NAMES = MAX_NAMES;
        this.CAN_REPEAT = CAN_REPEAT;
        this.SPECIAL_NAMES = SPECIAL_NAMES;
    }

    /**
     * Cria um gerador aleatório. Usado por subclasses para poderem setar os
     * arquivos com nomes.
     *
     * @param MAX_NAMES Número máximo de nomes gerados; separados por espaço.
     * Ex.: 2 - Fire Orc.
     * @param CAN_REPEAT Se os nomes podem repetir (Ex.: False - Orc Orc).
     * Maybe?
     * @param SPECIAL_NAMES Gerar (ou não) nomes especiais (Ex.: Count Dracula).
     */
    protected GeradorNome(int MAX_NAMES, boolean CAN_REPEAT, boolean SPECIAL_NAMES) {
        this.MAX_NAMES = MAX_NAMES;
        this.CAN_REPEAT = CAN_REPEAT;
        this.SPECIAL_NAMES = SPECIAL_NAMES;
    }

    protected void setSpecialNames(File special) {
        this.special_names = special;
    }

    protected void setImproveNames(File improved) {
        this.improve_names = improved;
    }

    protected void setNames(File names) {
        this.names = names;
    }

    /**
     * DEIXE DUAS OU MAIS LINHAS NO FINAL DO ARQUIVO PARA NÃO DAR NULL POINTER
     * EXCEPTION (:D)
     *
     * Gera um nome especial aleatório
     *
     * @param only_max Restringe o número de nomes para somente o mais próximo
     * de {@link NameGenerator#MAX_NAMES} se verdadeiro.
     * @return Nome selecionado aleatóriamente da lista de nomes especiais.
     * @throws FileNotFoundException Quando arquivo não é encontrado.
     * @throws IOException Erros na leitura de arquivo (Formato inadequado).
     * @throws UnsupportedOperationException No caso de tentar gerar com o
     * parâmetro {@link NameGenerator#SPECIAL_NAMES} em falso.
     */
    public String generateSpecialName(boolean only_max) throws FileNotFoundException, IOException {
        //Safadinho tentou gerar nome especial quando não podia...
        if (SPECIAL_NAMES == false) {
            throw new UnsupportedOperationException("Nope. Can't help ya. Não pode gerar nomes especiais se foi colocado para falso...");
        }

        if (!special_names.exists()) {
            throw new FileNotFoundException("Arquivo de nomes especiais não econtrado.");
        } //Redundante. Arrumar

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(special_names));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeradorNome.class.getName()).log(Level.SEVERE, null, ex);
        } // Criação do reader // Criação do reader

        int single = -1;
        int duo = -1;
        int triple = -1;

        String line;
        while (!(line = reader.readLine()).equals("")) { //Ler até encontrar linha vazia
            String[] split;
            if (line.startsWith("Nomes_unicos: ")) {
                split = line.split("Nomes_unicos: ");
                single = Integer.parseInt(split[1]);
            }

            if (line.startsWith("Nomes_duplos: ")) {
                split = line.split("Nomes_duplos: ");
                duo = Integer.parseInt(split[1]);
            }

            if (line.startsWith("Nomes_triplos: ")) {
                split = line.split("Nomes_triplos: ");
                triple = Integer.parseInt(split[1]);
            }
        }

        ArrayList<String> singleN = new ArrayList();
        if (single > 0) {
            while (!(line = reader.readLine()).equals("")) {
                singleN.add(line);
            }
        }

        ArrayList<String> duoN = new ArrayList();
        if (duo > 0) {
            while (!(line = reader.readLine()).equals("")) {
                duoN.add(line);
            }
        }

        ArrayList<String> tripleN = new ArrayList();
        if (triple > 0) {
            while (!(line = reader.readLine()).equals("")) {
                tripleN.add(line);
            }
        }

        //MAX_NAMES_INTERNAL é o mesmo que MAX_NAMES, a não ser
        //que "only_max" seja falso, assim, há chance de gerar menos
        //nomes que o máximo.
        int MAX_NAMES_INTERNAL = MAX_NAMES;
        if (!only_max) {
            Random newMax = new Random();
            MAX_NAMES_INTERNAL = newMax.nextInt(MAX_NAMES);
            MAX_NAMES_INTERNAL++;
            //Assim gera entre 1 e MAX_NAMES;
        }

        Random rand = new Random();
        if (MAX_NAMES_INTERNAL == 1) {
            int min = 0;
            int max = singleN.size() - 1;
            int randomIndex = rand.nextInt((max - min) + 1) + min;
            return singleN.get(randomIndex);
        }

        if (MAX_NAMES_INTERNAL == 2) {
            int min = 0;
            int max = duoN.size() - 1;
            int randomIndex = rand.nextInt((max - min) + 1) + min;
            return duoN.get(randomIndex);
        }

        int min = 0;
        int max = tripleN.size() - 1;
        int randomIndex = rand.nextInt((max - min) + 1) + min;
        return tripleN.get(randomIndex);
    }

    /**
     * Override de {@link NameGenerator#generateSpecialName(boolean)} para
     * retornar o mais próximo de {@link NameGenerator#MAX_NAMES}.
     *
     * @return Nome especial escolhido aleatóriamente.
     * @throws FileNotFoundException Caso arquivo não econtrado.
     * @throws IOException           Erro na leitura.
     */
    public String generateSpecialName() throws FileNotFoundException, IOException {
        return generateSpecialName(true);
    }

    /**
     * Gera um nome aleatório baseado nos parâmetros:      {@link NameGenerator#names}
     * {@link NameGenerator#CAN_REPEAT}
     * {@link NameGenerator#MAX_NAMES}
     *
     * @param allow_randomExt Permite ou não chance de gerar menos que
     * {@link NameGenerator#MAX_NAMES}.
     * @return Nome gerado aleatóriamente.
     *
     * @throws FileNotFoundException Quando arquivo não é encontrado.
     * @throws IOException Erros na leitura de arquivo (Formato inadequado).
     */
    public String generateRandomName(boolean allow_randomExt) throws FileNotFoundException, IOException {
        if (!names.exists()) {
            throw new FileNotFoundException("Arquivo de nomes não econtrado.");
        } //Redundante. Arrumar

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(names));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeradorNome.class.getName()).log(Level.SEVERE, null, ex);
        } // Criação do reader // Criação do reader

        int names = -1;

        String line;
        while (!(line = reader.readLine()).equals("")) { //Ler até encontrar linha vazia
            String[] split;
            if (line.startsWith("Nomes_unicos: ")) {
                split = line.split("Nomes_unicos: ");
                names = Integer.parseInt(split[1]);
            }
        }

        ArrayList<String> name = new ArrayList();

        if (names > 0) {
            while (!(line = reader.readLine()).equals("")) {
                name.add(line);
            }
        } else {
            throw new IOException("Arquivo não contem nomes ou é inválido.");
        }

        String generatedName = "";

        int num_names = 0;
        int min = 0;
        int max = name.size() - 1;
        while (num_names < MAX_NAMES) {
            if (num_names > 0) {
                generatedName += " ";
            }

            Random rand = new Random();
            int randomIndex = rand.nextInt((max - min) + 1) + min;

            if (CAN_REPEAT == false) { //Para não repetir nomes
                while (generatedName.contains(name.get(randomIndex))) {
                    rand = new Random();
                    randomIndex = rand.nextInt((max - min) + 1) + min;
                }
            }

            generatedName += name.get(randomIndex);
            num_names++;

            Random randomExit;
            if (allow_randomExt) {
                randomExit = new Random(); //25% de chance de sair
                int exit_code = rand.nextInt(4); //De 0 a 3 (?)
                if (exit_code == 0 && num_names > 0) {
                    return generatedName;
                }
            }
        }

        return generatedName;
    }

    /**
     * Overload de {@link NameGenerator#generateRandomName(boolean)} para nunca
     * retornar antes de gerar todos os nomes.
     *
     * @return Nome gerado aleatóriamente.
     *
     * @throws FileNotFoundException Quando arquivo não é encontrado.
     * @throws IOException Erros na leitura de arquivo (Formato inadequado).
     */
    public String generateRandomName() throws FileNotFoundException, IOException {
        return generateRandomName(false);
    }

    /**
     * Gera um nome aleatório melhorado. Segue uma hierarquia baseada em
     * {@link NameGenerator#improve_names}.
     * <p>
     * Se {@link NameGenerator#MAX_NAMES} for 3, gera três nomes (Prefixo, meio,
     * "fim"). Se for 2, gera dois (Meio, "fim"). Se 1, apenas "fim".
     * <p>
     * Pode sair ou não (Chance de 25%) antes de adicionar todos os nomes.
     * Sempre adiciona o "fim".
     *
     * @param allow_randomExt True para sair aleatóriamente no meio da geração.
     * False para não.
     * @return Nome melhorado gerado aleatóriamente.
     * @throws IOException Ao ler arquivo.
     */
    public String generateImprovedName(boolean allow_randomExt) throws IOException {
        if (!improve_names.exists()) {
            throw new FileNotFoundException("Arquivo de nomes melhorado não econtrado.");
        } //Redundante. Arrumar

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(improve_names));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeradorNome.class.getName()).log(Level.SEVERE, null, ex);
        } // Criação do reader // Criação do reader

        int pre = -1;
        int mid = -1;
        int end = -1;

        String line;
        while (!(line = reader.readLine()).equals("")) { //Ler até encontrar linha vazia
            String[] split;
            if (line.startsWith("Nomes_pre: ")) {
                split = line.split("Nomes_pre: ");
                pre = Integer.parseInt(split[1]);
            }

            if (line.startsWith("Nomes_mid: ")) {
                split = line.split("Nomes_mid: ");
                mid = Integer.parseInt(split[1]);
            }

            if (line.startsWith("Nomes_end: ")) {
                split = line.split("Nomes_end: ");
                end = Integer.parseInt(split[1]);
            }
        }

        if (pre <= 0 || mid <= 0 || end <= 0) {
            throw new IOException("Arquivo não contem nomes ou é inválido.");
        }

        ArrayList<String> preN = new ArrayList();
        ArrayList<String> midN = new ArrayList();
        ArrayList<String> endN = new ArrayList();

        while (!(line = reader.readLine()).equals("")) {
            if (MAX_NAMES >= 3) {
                preN.add(line);
            }
        }

        while (!(line = reader.readLine()).equals("")) {
            if (MAX_NAMES >= 2) {
                midN.add(line);
            }
        }

        while (!(line = reader.readLine()).equals("")) {
            endN.add(line);
        }

        int min = 0;
        int max = endN.size() - 1;

        Random rand = new Random();
        int randomIndex = rand.nextInt((max - min) + 1) + min;
        String generatedName = endN.get(randomIndex);
        if (MAX_NAMES == 1) {
            return generatedName;
        }

        Random randomExit = new Random(); //25% de chance de sair
        int exit_code = rand.nextInt(4); //De 0 a 3 (?)
        if (exit_code == 0 && allow_randomExt == true) {
            return generatedName;
        }

        if (MAX_NAMES >= 2) {
            max = midN.size() - 1;
            rand = new Random();
            randomIndex = rand.nextInt((max - min) + 1) + min;
            generatedName = midN.get(randomIndex) + " " + generatedName;
        }

        if (MAX_NAMES == 2) {
            return generatedName;
        }

        if (allow_randomExt == true) {
            randomExit = new Random();
            exit_code = rand.nextInt(4); //De 0 a 3 (?)
            if (exit_code == 0) {
                return generatedName;
            }
        }

        if (MAX_NAMES >= 3) {
            max = preN.size() - 1;
            rand = new Random();
            randomIndex = rand.nextInt((max - min) + 1) + min;
            generatedName = preN.get(randomIndex) + " " + generatedName;
        }

        return generatedName;
    }

    /**
     * Overload do método {@link NameGenerator#generateImprovedName(boolean)},
     * para nunca sair aleatóriamente, ou seja: Se
     * {@link NameGenerator#MAX_NAMES} for maior ou igual à 3, são gerados
     * sempre três nomes. Se for igual à 2, sempre gera dois. Etc.
     * <p>
     * Bom se quiser basear nomes em level.
     *
     * @return Nome melhorado gerado aleatóriamente.
     * @throws IOException Ao ler arquivo.
     */
    public String generateImprovedName() throws IOException {
        return generateImprovedName(false);
    }

    @Override
    public String toString() {
        return "NameGenerator{\n"
                + "Arquivo de nomes especiais: " + special_names
                + "\nArquivo de nomes aleatórios:" + names
                + "\nArquivo de nomes melhorados:" + improve_names
                + "\nNúmero máximo de nomes permitido: " + MAX_NAMES
                + "\nPode repetir nomes aleatórios: " + CAN_REPEAT
                + "\nPode gerar nomes especiais: " + SPECIAL_NAMES + "   }";
    }

}

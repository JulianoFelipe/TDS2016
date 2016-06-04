/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geradores;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Random;

/**
 * Gera nomes aleatórios para monstros.
 * 
 * @author Juliano Felipe
 */
public class MonstroNameGenerator {
    private final File special_names = new File (getClass().getResource("/Data/SpecialMonsterNames.txt").getFile());
    private final File monster_names = new File (getClass().getResource("/Data/MonsterNames.txt").getFile());
    private final File improve_names = new File (getClass().getResource("/Data/ImprovedMonsterNames.txt").getFile());
    
    private final int MAX_NAMES;
    private final boolean CAN_REPEAT;
    private final boolean SPECIAL_NAMES;
    private final int MONSTER_TYPE;
    //Padão de constantes? Tudo maiúsculo?

    /**
     * Constroi um gerador de nomes de monstros.
     * @param MAX_NAMES       Número máximo de nomes gerados; separados por espaço.
     *                        Ex.: 2 - Fire Orc.
     * @param CAN_REPEAT      Se os nomes podem repetir (Ex.: False - Orc Orc). Maybe?
     * @param SPECIAL_NAMES   Gerar (ou não) nomes especiais (Ex.: Count Dracula).
     * @param MONSTER_TYPE    Tipo do monstro. (TODO: Alterar parâmetro. Retirar. Etc. É uma ideia).
     */
    public MonstroNameGenerator(int MAX_NAMES, boolean CAN_REPEAT, boolean SPECIAL_NAMES, int MONSTER_TYPE) {
        this.MAX_NAMES = MAX_NAMES;
        this.CAN_REPEAT = CAN_REPEAT;
        this.MONSTER_TYPE = MONSTER_TYPE;
        this.SPECIAL_NAMES = SPECIAL_NAMES;
    }
    
    //Para testes
    private MonstroNameGenerator (){
        MAX_NAMES = 3;
        CAN_REPEAT = true;
        SPECIAL_NAMES = true;
        MONSTER_TYPE = -1;
    }
    
    /**
     *  DEIXE DUAS OU MAIS LINHAS NO FINAL DO ARQUIVO 
     * PARA NÃO DAR NULL POINTER EXCEPTION (:D)
     * Gera um nome especial aleatório
     * @return Nome selecionado aleatóriamente
     *         da lista de nomes especiais.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public String generateSpecialName () throws FileNotFoundException, IOException{
        //Verificar se é do tipo String
        
        //Safadinho tentou gerar nome especial quando não podia...
        if (SPECIAL_NAMES == false) return null;

        if (!special_names.exists()){
            throw new FileNotFoundException ("Arquivo de nomes especiais de monstros não econtrado.");
        } //Redundante. Arrumar
        
        BufferedReader reader=null;
        try {
            reader = new BufferedReader(new FileReader(special_names));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MonstroNameGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } // Criação do reader
        
        int single=-1;
        int duo=-1;
        int triple=-1;
        
        String line;
        while (!(line = reader.readLine()).equals("")) { //Ler até encontrar linha vazia
            String[] split;
//            if (line.startsWith("Nomes_totais: ")){
//                split = line.split("Nomes_totais: ");
//                total = Integer.parseInt(split[1]);
//            }
            
            if (line.startsWith("Nomes_unicos: ")){
                split = line.split("Nomes_unicos: ");
                single = Integer.parseInt(split[1]);
            }
            
            if (line.startsWith("Nomes_duplos: ")){
                split = line.split("Nomes_duplos: ");
                duo = Integer.parseInt(split[1]);
            }
            
            if (line.startsWith("Nomes_triplos: ")){
                split = line.split("Nomes_triplos: ");
                triple = Integer.parseInt(split[1]);
            }
        }
        
        ArrayList<String> singleN = new ArrayList();
        if (single>0){
            while (!(line = reader.readLine()).equals("")) {
                singleN.add(line);
            }
        }
        
        ArrayList<String> duoN = new ArrayList();
        if (duo>0){
            while (!(line = reader.readLine()).equals("")) {
                duoN.add(line);
            }
        }
        
        ArrayList<String> tripleN = new ArrayList();
        if (triple>0){
            while (!(line = reader.readLine()).equals("")) {
                tripleN.add(line);
            }
        }

        Random rand = new Random();
        if (MAX_NAMES == 1){
            int min=0;
            int max = singleN.size() - 1;
            int randomIndex = rand.nextInt((max-min)+1) + min;
            return singleN.get(randomIndex);
        }
        
        if (MAX_NAMES == 2){
            int min=0;
            int max = duoN.size() - 1;
            int randomIndex = rand.nextInt((max-min)+1) + min;
            return duoN.get(randomIndex);
        }
        
        int min=0;
        int max = tripleN.size() - 1;
        int randomIndex = rand.nextInt((max-min)+1) + min;
        return tripleN.get(randomIndex);
    }
    
    /**
     * Gera um nome aleatório baseado nos parâmetros:
     * {@link MonstroNameGenerator#monster_names}
     * {@link MonstroNameGenerator#CAN_REPEAT}
     * {@link MonstroNameGenerator#MAX_NAMES}
     * 
     * @return
     * @throws IOException 
     */
    public String generateRandomName () throws IOException{
        if (!monster_names.exists()){
            throw new FileNotFoundException ("Arquivo de nomes de monstros não econtrado.");
        } //Redundante. Arrumar
        
        BufferedReader reader=null;
        try {
            reader = new BufferedReader(new FileReader(monster_names));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MonstroNameGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } // Criação do reader
        
        int names=-1;
        
        String line;
        while (!(line = reader.readLine()).equals("")) { //Ler até encontrar linha vazia
            String[] split;
            if (line.startsWith("Nomes_unicos: ")){
                split = line.split("Nomes_unicos: ");
                names = Integer.parseInt(split[1]);
            }
        }
        
        ArrayList<String> name = new ArrayList();

        if (names>0){
            while (!(line = reader.readLine()).equals("")) {
                name.add(line);
            }
        } else {
            throw new IOException ("Arquivo não contem nomes ou é inválido.");
        }
        
        String generatedName="";
        
        int num_names=0;
        int min=0;
        int max = name.size() - 1;
        while (num_names <= MAX_NAMES){
            if (num_names>0) generatedName += " ";
            
            Random rand = new Random();
            int randomIndex = rand.nextInt((max-min)+1) + min;
            
            if (CAN_REPEAT == false){ //Para não repetir nomes
                while (generatedName.contains( name.get(randomIndex) )){
                    rand = new Random();
                    randomIndex = rand.nextInt((max-min)+1) + min;
                }
            }
            
            generatedName += name.get(randomIndex);
            num_names++;
            
            Random randomExit = new Random(); //25% de chance de sair
            int exit_code = rand.nextInt(4); //De 0 a 3 (?)
            if (exit_code==0 && num_names>0){
                return generatedName;
            }
        }
        
        return generatedName;
    }
    
    /**
     * Gera um nome aleatório melhorado.
     * Segue uma hierarquia baseada em
     * {@link MonstroNameGenerator#improve_names}.
     * <p>
     * Se {@link MonstroNameGenerator#MAX_NAMES} for 3,
     * gera três nomes (Prefixo, meio, criatura).
     * Se for 2, gera dois (Meio, Criatura).
     * Se 1, apenas criatura.
     * <p>
     * Pode sair ou não (Chance de 25%) antes de adicionar
     * todos os nomes. Sempre adiciona o "criatura".
     * 
     * @param random_ext   True para sair aleatóriamente no
     *                     meio da geração. False para não.
     * @return             Nome melhorado gerado aleatóriamente.
     * @throws IOException 
     */
    public String generateImprovedName (boolean random_ext) throws IOException{
        if (!improve_names.exists()){
            throw new FileNotFoundException ("Arquivo de nomes de monstros melhorado não econtrado.");
        } //Redundante. Arrumar
        
        BufferedReader reader=null;
        try {
            reader = new BufferedReader(new FileReader(improve_names));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MonstroNameGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } // Criação do reader
        
        int pre=-1;
        int mid=-1;
        int end=-1;
        
        String line;
        while (!(line = reader.readLine()).equals("")) { //Ler até encontrar linha vazia
            String[] split;
            if (line.startsWith("Nomes_pre: ")){
                split = line.split("Nomes_pre: ");
                pre = Integer.parseInt(split[1]);
            }
            
            if (line.startsWith("Nomes_mid: ")){
                split = line.split("Nomes_mid: ");
                mid = Integer.parseInt(split[1]);
            }
            
            if (line.startsWith("Nomes_end: ")){
                split = line.split("Nomes_end: ");
                end = Integer.parseInt(split[1]);
            }
        }
        
        if (pre<=0 || mid<=0 || end<=0)
            throw new IOException ("Arquivo não contem nomes ou é inválido.");
        
        ArrayList<String> preN = new ArrayList();
        ArrayList<String> midN = new ArrayList();
        ArrayList<String> endN = new ArrayList();
        
        while (!(line = reader.readLine()).equals("")) {
            if (MAX_NAMES >= 3){
                preN.add(line);
            }            
        }
        
        while (!(line = reader.readLine()).equals("")) {
            if (MAX_NAMES >= 2){
                midN.add(line);
            }            
        }
        
        while (!(line = reader.readLine()).equals("")) 
            endN.add(line);
        
        int min=0;
        int max=endN.size()-1;
        
        Random rand = new Random();
        int randomIndex = rand.nextInt((max-min)+1) + min;
        String generatedName=endN.get(randomIndex);
        if (MAX_NAMES == 1) return generatedName;
        
        Random randomExit = new Random(); //25% de chance de sair
        int exit_code = rand.nextInt(4); //De 0 a 3 (?)
        if (exit_code==0 && random_ext==true){ return generatedName; }
        
        if (MAX_NAMES >=2 ){
            max = midN.size()-1;
            rand  = new Random();
            randomIndex = rand.nextInt((max-min)+1) + min;
            generatedName = midN.get(randomIndex) + " " + generatedName;
        } 
        
        if (MAX_NAMES == 2) return generatedName;
        
        if (random_ext == true){ 
            randomExit = new Random();
            exit_code = rand.nextInt(4); //De 0 a 3 (?)
            if (exit_code==0){ return generatedName; }
        }
        
        if (MAX_NAMES >=3 ){
            max = preN.size()-1;
            rand  = new Random();
            randomIndex = rand.nextInt((max-min)+1) + min;
            generatedName = preN.get(randomIndex) + " " + generatedName;
        }

        return generatedName;
    }
    
    /**
     * Overload do método {@link MonstroNameGenerator#generateImprovedName(boolean)},
     * para nunca sair aleatóriamente, ou seja:
     * Se {@link MonstroNameGenerator#MAX_NAMES} 
     * for maior ou igual à 3, são gerados sempre três nomes.
     * Se for igual à 2, sempre gera dois. Etc.
     * <p>
     * Bom se quiser basear nomes em level de monstros.
     * @return             Nome melhorado gerado aleatóriamente.
     * @throws IOException
     */
    public String generateImprovedName () throws IOException{
        return generateImprovedName(false);
    }
    
    public static void main(String[] args) {
        //TESTING THIS CLASS
        MonstroNameGenerator t = new MonstroNameGenerator();
        try {
            String generateSpecialName = t.generateImprovedName();
            System.out.println(generateSpecialName);
        } catch (IOException ex) {
            Logger.getLogger(MonstroNameGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

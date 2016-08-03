/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import Model.Criaturas.Jogador;
import Model.DAO.DAOFactory;
import Model.DAO.DatabaseException;

/**
 *
 * @author Juliano.Silva10
 */
public class TesteBancoDeDados {
    private static final DAOFactory DAO = DAOFactory.getDAOFactory( DAOFactory.SQLITE );
    
    public static void main(String[] args) throws DatabaseException {
        /*Jogador j = new Jogador();
        Heroi h = new Arthas(j);
        h.getListaDeHabilidades().add( new Encorajamento(h));
        
        ArmaBase arma = new ArmaBase(10.0);
        arma.setNome("Arma i");
        arma.setValor(10);
        arma.setLevel(1);
        arma.setModificador(Modificador.Nenhum);
        arma.setRaridade(Raridade.Verde);
        arma.setTipo(Armas.Arco);
        
        j.addItem(arma);
        j.getLista_de_herois().add(h);

        int inserir = DAO.getJogadorDAO().inserir(j);  */

        //DAO.getJogadorDAO().remover(j);
        
        //List<Jogador> lista = DAO.getJogadorDAO().resgatarTodos();
        Jogador j = DAO.getJogadorDAO().buscar(66);
        System.out.println(j.getGold());
        System.out.println(j.getLista_de_herois());
        System.out.println(j.getInventario());
    }
}

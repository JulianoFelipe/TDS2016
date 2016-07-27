/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Itens;

import Model.Criaturas.Heroi;
import Model.Criaturas.Jogador;
import java.util.Objects;
import utilidades.Descritivel;

/**
 * Classe abstrata basica de todos os itens
 *
 */
public abstract class ItemBase implements Comparable, Descritivel {

    /**
     * Identificador do item
     */
    private Integer serial_number;

    /**
     * numero de itens criados, usado para definir serial_number
     */
    private static int instances_created = 0;

    /**
     * Jogador que controla heroi
     */
    private Jogador jogador;
    
    /**
     * Heroi que possue o item
     */
    private Heroi heroi;

    /**
     * Nome do item
     */
    private String nome;

    /**
     * Por hora todos os consumaveis tem esse valor
     */
    private Integer valor = 1000;

    ItemBase() {
        this.serial_number = instances_created;
        instances_created++;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador owner) {
        this.jogador = owner;
    }
    
    public Heroi getOwner()
    {
        return heroi;
    }
    
    public void setOwner(Heroi heroi)
    {
        this.heroi = heroi;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "BaseItem{" + "serial_number=" + serial_number + ", nome=" + nome + '}';
    }

    @Override
    public String getDescricao() {
        return (this.nome + ",valor=" + valor);
    }

    /**
     * Comparacao feita hora pelos seriais apenas
     *
     * @param o Objeto a ser comparado.
     * @return  0 se igual. 1 se maior ou 1 se menor.
     */
    @Override
    public int compareTo(Object o) {
        if (o instanceof ItemBase) {
            ItemBase other_item = (ItemBase) o;
            return (other_item.serial_number.compareTo(this.serial_number));
        }
        return (0);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.serial_number);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemBase other = (ItemBase) obj;
        if (!Objects.equals(this.serial_number, other.serial_number)) {
            return false;
        }
        return true;
    }

    /**
     * Chamada quando esse item for vendido em uma loja
     */
    public void onSell() {
        System.out.println("Vendendo item de valor " + this.getValor());
        System.out.println("Gold antes da transacao " + this.getJogador().getGold());
        this.jogador.addGold(this.valor);
        System.out.println("Gold depois da transacao " + this.getJogador().getGold());
        onDrop();
    }

    /**
     * Chamada quando esse item for removido
     */
    public void onDrop() {
        if (this.jogador == null) {
            System.out.println("owner = null(onDrop)!!");
        } else {
            if (this.heroi.getArmor() != null) {
                if (this.heroi.getArmor().equals(this)) {
                    this.heroi.setArmor(null);
                }
            }
            if (this.heroi.getWeapon() != null) {
                if (this.heroi.getWeapon().equals(this)) {
                    this.heroi.setWeapon(null);
                }
            }
            this.jogador.removeItem(this);
        }
        this.jogador = null;
        this.heroi = null;
    }

}
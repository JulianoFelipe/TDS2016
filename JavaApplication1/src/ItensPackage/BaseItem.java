/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ItensPackage;
import java.util.Objects;
import javaapplication1.HeroClass;
import utillities.Describable;
/**
 *
 * @author FREE
 */
public abstract class BaseItem implements Comparable,Describable{
    private Integer serial_number;
    private static int instances_created=0;
    private HeroClass owner;
    private String nome;
    
    /**
     * Por hora todos os consumaveis tem esse valor
     */
    private Integer valor = 1000;
    
    BaseItem()
    {
        this.serial_number = instances_created;
        instances_created++;
    }

    
    public HeroClass getOwner() {
        return owner;
    }

    public void setOwner(HeroClass owner) {
        this.owner = owner;
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
    public String getDescription()
    {
        return(this.nome);
    }

    /**
     * Comparacao feita hora pelos seriais apenas
     * @param o
     * @return 
     */
    @Override
    public int compareTo(Object o) {
        if (o instanceof BaseItem)
        {
            BaseItem other_item = (BaseItem)o;
            return(other_item.serial_number.compareTo(this.serial_number));
        }
        return(0);
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
        final BaseItem other = (BaseItem) obj;
        if (!Objects.equals(this.serial_number, other.serial_number)) {
            return false;
        }
        return true;
    }
    
    
    
    public void onSell()
    {
        this.owner.addMoney(this.valor);
        this.owner.removeItem(this);
        this.owner = null;
    }
    
    public void onDrop()
    {
        this.owner.removeItem(this);
        this.owner = null;
    }
    
}

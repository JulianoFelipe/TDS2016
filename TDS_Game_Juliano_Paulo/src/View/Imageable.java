/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.File;

/**
 * Interface que fornece metodo que retorna um arquivo relacionada a uma imagem da entidade que implementa a interface
 * @author Paulo
 */
public interface Imageable {
    public File getArquivoDeImagem();
}

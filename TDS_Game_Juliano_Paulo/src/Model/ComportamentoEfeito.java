/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Paulo.Tenorio
 */
public enum ComportamentoEfeito {
    PADRAO,//padrao sao efeitos que sao aplicados imediatamente e sao reduzidos cada vez que a criatura ganha turno
    INSTANTANEO,//efeitos que sao aplicados e imediatamente e sao reduzidos logo em seguida
    TURNO;//efeitos que sao aplicados e reduzidos quando ganha o turno
}

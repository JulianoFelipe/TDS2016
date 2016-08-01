/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author FREE
 */
public class ConfiguracoesDeTempo {
    private int tempo_total_milisegundos = 1000;
    private int tempo_total_aproximado = 1200;
    private int tempoBatalhaFrame = 2000;
    
    private ConfiguracoesDeTempo() {
    }
    
    public static ConfiguracoesDeTempo getInstance() {
        return ConfiguracoesDeTempoHolder.INSTANCE;
    }
    
    private static class ConfiguracoesDeTempoHolder {

        private static final ConfiguracoesDeTempo INSTANCE = new ConfiguracoesDeTempo();
    }
    
    public int getTempo_total()
    {
        return(tempo_total_milisegundos);
    }
    
    public int getTempo_aproximado()
    {
        return(tempo_total_aproximado);
    }

    public int getTempoBatalhaFrame() {
        return tempoBatalhaFrame;
    }
    
}

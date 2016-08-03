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
    public static final int TEMPOTOTALDEFAULT = 1000;
    public static final int BATALHAFRAMEDEFAULT = 2000;
    
    private int tempo_total_milisegundos = 1000;
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
        return(tempo_total_milisegundos + 200);
    }

    public void setTempo_total_milisegundos(int tempo_total_milisegundos) {
        this.tempo_total_milisegundos = tempo_total_milisegundos;
    }

    public void setTempoBatalhaFrame(int tempoBatalhaFrame) {
        this.tempoBatalhaFrame = tempoBatalhaFrame;
    }

    public int getTempoBatalhaFrame() {
        return tempoBatalhaFrame;
    }
    
}

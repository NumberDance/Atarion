package com.atarion.game.entidad.jugador.humano.trench;

import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.atarion.game.interfaz.escena.online.ParteMensaje;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Benefactor extends Trench
{
    public Benefactor()
    { super(); }
    public Benefactor(Batch genesis)
    { super(genesis); }


    @Override
    public MensajeJSON enviarEstado()
    { return super.enviarEstado().escribirAtributo(null,null,ParteMensaje.FINAL); }
    @Override
    public void recibirEstado(String estado)
    { super.recibirEstado(estado); }
    
    
    @Override
    public void activarEspecial()
    {}
    @Override
    public void desactivarEspecial()
    {}
}

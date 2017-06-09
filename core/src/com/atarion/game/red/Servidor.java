package com.atarion.game.red;

import com.atarion.game.interfaz.escena.Escena;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.event.ChangeEvent;

public class Servidor implements PropertyChangeListener
{
    private ServerSocket socket;
    private Escena escena;
    
    public Servidor(Escena escena)
    {
        socket = Gdx.net.newServerSocket(Protocol.TCP,"127.0.0.1",20595,new ServerSocketHints());
        this.escena = escena;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {}
}

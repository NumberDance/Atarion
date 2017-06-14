package com.atarion.game.red;

import com.atarion.game.interfaz.escena.Escena;
import com.atarion.game.interfaz.escena.EscenaOnline;
import com.atarion.game.interfaz.escena.EscenaPVP;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import javax.swing.event.ChangeEvent;

public class Servidor
{
    private ServerSocket socket;
    private HashSet<Socket> clients = new HashSet<Socket>();
    
    private EscenaOnline escena;
    
    public Servidor(EscenaOnline escena)
    {
        socket = Gdx.net.newServerSocket(Protocol.TCP,"127.0.0.1",20595,new ServerSocketHints());
        this.escena = escena;
        
        clients.add(socket.accept(new SocketHints()));
    }
}

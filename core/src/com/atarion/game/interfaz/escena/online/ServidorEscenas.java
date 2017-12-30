package com.atarion.game.interfaz.escena.online;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

public final class ServidorEscenas 
{
    private ServerSocket servidor;
    private HashMap<Integer,Socket> clientes = new HashMap<Integer,Socket>();
    private Integer cuenta = 0, capacidad = 1;
    private Gson conversor = new Gson();
    
    
    public ServidorEscenas()
    {
        try
        {
            servidor = new ServerSocket(20595);
            
            while(cuenta < capacidad)
            {
                System.out.println("Esperando clientes...");
                clientes.put(cuenta++,servidor.accept());
                System.out.println("Cliente conectado: " + clientes.get(cuenta).getInetAddress());
            }
            
            this.iniciarPartida();
        } 
        catch (IOException ex)
        {}
    }

    private void iniciarPartida()
    {   
        for(;;)
        {
            clientes.entrySet().forEach
            (
                cliente ->
                {
                    try
                    {
                        InputStream entrada = cliente.getValue().getInputStream();
                        
                        if(entrada.available() > 0)
                        {
                            String serializado = IOUtils.toString(new BufferedReader(new InputStreamReader(entrada)));
                            
                            System.out.println("____________________");
                            System.out.println(serializado);
                        }
                    }   
                    catch (IOException ex)
                    {}
                }
            );
        }
    }
}

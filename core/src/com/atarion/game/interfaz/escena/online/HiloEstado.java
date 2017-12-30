package com.atarion.game.interfaz.escena.online;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

public class HiloEstado implements Runnable 
{
    private Socket cliente;
    
    
    public HiloEstado(Socket cliente)
    { this.cliente = cliente; }
    
    
    @Override
    public void run()
    { 
        for(;;)
        {
            
            try
            {
                InputStream entrada = cliente.getInputStream();
                String serializado = IOUtils.toString(new BufferedReader(new InputStreamReader(entrada)));
                
                System.out.println("____________________");
                System.out.println(serializado);
                
                entrada.close();
            } 
            catch (IOException ex)
            {} 
        }
    }
}

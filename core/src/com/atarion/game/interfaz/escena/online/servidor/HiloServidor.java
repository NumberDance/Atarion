package com.atarion.game.interfaz.escena.online.servidor;


import com.atarion.game.interfaz.escena.online.MensajeJSON;
import com.atarion.game.interfaz.escena.online.ParteMensaje;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.Map.Entry;


public class HiloServidor extends Thread
{
    private String identificador;
    private Socket socket = null;
    private BufferedReader lector = null;
    private EscenaServidor escena = null;
    
    
    public HiloServidor(Entry<String,SimpleEntry<Socket,String>> cliente, EscenaServidor escena)
    { 
        this.identificador = cliente.getKey();
        
        try
        {
            this.socket = cliente.getValue().getKey();
            this.lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch(IOException ex)
        {}
        
        this.escena = escena;
    }
    
    
    @Override
    public void run()
    {
        try
        {  
            MensajeJSON id = new MensajeJSON().escribirAtributo("identificador",this.identificador,ParteMensaje.SINGULAR);
            id.enviar(this.socket.getOutputStream());
            
            //Espera a que todos los clientes hayan enviado sus estados iniciales.
            this.escena.agregarEstadoInicial(new MensajeJSON().recibir(lector));
            while(this.escena.getIniciales().size() != this.escena.getClientes().size())
            {}
            
            HashSet<String> estados = new HashSet<>();
            this.escena.getIniciales().forEach(inicial -> { estados.add(inicial.getJson().toString()); });
            
            MensajeJSON iniciales = new MensajeJSON().escribirArray("iniciales",estados,ParteMensaje.SINGULAR);
            iniciales.enviar(this.socket.getOutputStream());
            
            this.correrHilo();
        } 
        catch (IOException ex)
        {} 
    }
    private void correrHilo() throws IOException
    {
        while(!socket.isClosed())
        {
            try
            {   
                escena.getSemaforo().acquire();
                
                escena.getClientes().replace(identificador,new SimpleEntry<>(socket,lector.readLine()));
                System.out.println("Recibido estado del cliente " + this.identificador + " -> " + escena.getClientes().get(identificador).getValue());
                
                StringBuilder respuesta = new StringBuilder("");
                escena.getClientes().forEach
                (
                    (clave,valor) -> 
                    {    
                        if(!clave.equals(identificador) && !valor.getKey().isClosed())
                        { respuesta.append(valor.getValue()); } 
                    }
                );
                respuesta.append("\n");
                socket.getOutputStream().write(respuesta.toString().getBytes());
                
                escena.getSemaforo().release();
            } 
            catch (InterruptedException ex)
            {}
        }
    }
}

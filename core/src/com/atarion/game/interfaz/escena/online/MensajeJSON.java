package com.atarion.game.interfaz.escena.online;


import com.atarion.game.entidad.Entidad;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONObject;
import org.json.JSONTokener;


public class MensajeJSON 
{
    private StringBuilder mensaje;
    private JSONObject json;
    
    
    public MensajeJSON escribirAtributo(String nombre, String valor, ParteMensaje parte)
    {
        switch(parte)
        {
            case PRINCIPIO:
                this.mensaje = new StringBuilder("");
                this.mensaje.append("{");
            case CUERPO:
                this.agregarCampo(nombre,valor);
                this.mensaje.append(",");
            break;
            case FINAL:
                this.agregarCampo(nombre,valor);
                this.mensaje.append("}");
            break;
        }
        
        return this;
    }
    public MensajeJSON escribirArray(String nombre, HashSet<Entidad> coleccion, ParteMensaje parte)
    {
        switch(parte)
        {
            case PRINCIPIO:
                this.mensaje = new StringBuilder("");
                this.mensaje.append("{");
            case CUERPO:
                this.agregarArray(nombre,coleccion);
                this.mensaje.append(",");
            break;
            case FINAL:
                this.agregarArray(nombre,coleccion);
                this.mensaje.append("}");
            break;
        }

        
        return this;
    }
    private void agregarCampo(String nombre, String valor)
    {
        if(nombre != null && valor != null)
        {
            this.mensaje.append("'").append(nombre).append("'");
            this.mensaje.append(":");
            this.mensaje.append("'").append(valor).append("'");
        }
    }
    private void agregarArray(String nombre, HashSet<Entidad> coleccion)
    {
        this.mensaje.append("'").append(nombre).append("'");
        this.mensaje.append(":");
        this.mensaje.append("[");
        
        boolean primero = true;
        for(Entidad entidad : coleccion)
        {
            if(primero)
            { primero = false; }
            else
            { this.mensaje.append(","); }
            
            this.mensaje.append(entidad.enviarEstado().getMensaje().toString());
        }
        
        this.mensaje.append("]");
    }
    
    
    public void enviar(OutputStream salida)
    {
        try
        {
            this.mensaje.append("\n");
            salida.write(this.mensaje.toString().getBytes());
        } 
        catch (IOException ex)
        {}
    }
    public void recibir(BufferedReader lector)
    {
        try 
        { this.json = new JSONObject(new JSONTokener(lector.readLine())); } 
        catch (IOException ex)
        {}
    }

    
    
    public JSONObject getJson()
    { return json; }
    public StringBuilder getMensaje()
    { return mensaje; }
}
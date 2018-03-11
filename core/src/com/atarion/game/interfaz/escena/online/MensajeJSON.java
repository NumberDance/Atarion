package com.atarion.game.interfaz.escena.online;


import com.atarion.game.entidad.Entidad;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
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
            case SINGULAR:
                this.mensaje = new StringBuilder("");
                this.mensaje.append("{");
                this.agregarCampo(nombre,valor);
                this.mensaje.append("}");
            break;
        }
        
        return this;
    }
    public MensajeJSON escribirArray(String nombre, HashSet<String> coleccion, ParteMensaje parte)
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
            case SINGULAR:
                this.mensaje = new StringBuilder("");
                this.mensaje.append("{");
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
    private void agregarArray(String nombre, HashSet<String> coleccion)
    {
        this.mensaje.append("'").append(nombre).append("'");
        this.mensaje.append(":");
        this.mensaje.append("[");
        
        boolean primero = true;
        for(String elemento : coleccion)
        {    
            if(primero)
            { primero = false; }
            else
            { this.mensaje.append(","); }
            
            this.mensaje.append(elemento);
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
    public MensajeJSON recibir(BufferedReader lector)
    {
        try 
        {
            String estado = lector.readLine();
            this.generarJSON(estado);
        }
        catch (IOException ex)
        {}
        
        return this;
    }
    public MensajeJSON recibir(String estado)
    {
        this.generarJSON(estado); 
        return this;
    }

    
    private void generarJSON(String estado)
    {
        if(estado.equals(""))
        { this.json = null; }
        else
        { this.json = new JSONObject(new JSONTokener(estado)); } 
    }
    public JSONObject getJson()
    {
        if(json == null)
        { this.generarJSON(this.mensaje.toString()); }
        
        return json; 
    }
    
    
    public StringBuilder getMensaje()
    { return mensaje; }
}

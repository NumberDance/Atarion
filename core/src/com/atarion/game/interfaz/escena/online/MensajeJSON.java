package com.atarion.game.interfaz.escena.online;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

@Getter
public class MensajeJSON 
{
    private String mensaje = "";
    private JSONObject json = new JSONObject();
        
    
    public MensajeJSON concatenar(MensajeJSON segundo,String coleccion)
    {
        if(this.json.has(coleccion))
        { this.json.getJSONArray(coleccion).put(segundo.getJson()); }
        else
        {
            JSONArray entradas = new JSONArray();
            if(!this.mensaje.equals(""))
            { entradas.put(json); }
            entradas.put(segundo.getJson());
            
            JSONObject concatenado = new JSONObject();
            concatenado.put(coleccion,entradas);
            
            this.json = concatenado;
        }
        this.generarMensaje();
        
        return this;
    }
    public MensajeJSON escribirAtributo(String nombre, String valor)
    {
        this.json.put(nombre,valor);
        this.generarMensaje();
        
        return this;
    }
    
    
    public void enviar(OutputStream salida)
    {
        try
        {
            this.mensaje += "\n";
            salida.write(this.mensaje.getBytes());
        } 
        catch (IOException ex)
        {}
    }
    public MensajeJSON recibir(BufferedReader lector)
    {
        try 
        {
            this.mensaje = lector.readLine();
            this.generarJSON();
        }
        catch (IOException ex)
        {}
        
        return this;
    }
    public MensajeJSON recibir(String mensaje)
    {
        this.mensaje = mensaje;
        this.generarJSON(); 
        
        return this;
    }

    
    private void generarMensaje()
    {
        if(this.json == null)
        { this.mensaje = ""; }
        else
        { this.mensaje = this.json.toString(); }
    }
    private void generarJSON()
    {
        if(mensaje.equals(""))
        { this.json = null; }
        else
        { this.json = new JSONObject(new JSONTokener(mensaje)); } 
    }
}

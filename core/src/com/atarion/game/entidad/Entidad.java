package com.atarion.game.entidad;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import org.json.JSONObject;
import org.json.JSONTokener;


public abstract class Entidad extends Rectangle
{
    protected Batch genesis;
    protected Texture textura;
    protected String identificador = "NONE";
    
    
    public void actualizarEstado()
    { genesis.draw(textura,x,y); }
    public void dispose()
    { textura.dispose(); }
    
    
    protected StringBuilder volcarEstado()
    {
        StringBuilder estado = new StringBuilder();
        estado.append("{");
        estado.append("'identificador':").append("'").append(this.identificador).append("'").append(",");
        estado.append("'x':").append("'").append(this.x).append("'").append(",");
        estado.append("'y':").append("'").append(this.y).append("'");
        return estado;
    }
    protected void recibirEstado(String estado)
    {
        JSONObject objeto = new JSONObject(new JSONTokener(estado));
        this.identificador = objeto.getString("identificador");
        this.x = objeto.getFloat("x");
        this.y = objeto.getFloat("y");
    }

    
    public String getIdentificador()
    { return identificador; }
    
    
    public void setGenesis(Batch genesis)
    { this.genesis = genesis; }
    public void setTextura(Texture textura)
    { this.textura = textura; }
    public void setIdentificador(String identificador)
    { this.identificador = identificador; }
}

package com.atarion.game.desktop;


import com.atarion.game.Atarion;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class ClientLauncher 
{
    /*TODO: Hacer que soporte todas las resoluciones, ahora si se cambia la
    resolución el sistema de coordenadas PETA, config.resizeable = false por ahora*/
    public static void main(String [] args) 
    {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Atarion"; 
        config.width = 1000;
        config.height = 800;
        config.resizable = false;
        LwjglApplication aplicacion = new LwjglApplication(Atarion.getInstance(),config);
    }
}
package com.atarion.game.desktop;

import com.atarion.game.Atarion;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher 
{   
    private static boolean server = false;
    
    public static void main (String[] args) 
    {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Atarion";
        config.width = 1000;
        config.height = 800;
        new LwjglApplication(Atarion.getInstance(),config);
        
        if(server)
        {}
    }

    public static void setServer(boolean server)
    { DesktopLauncher.server = server; }
}
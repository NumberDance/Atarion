package com.atarion.game.desktop;

import com.atarion.game.Atarion;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher 
{   
    public static void main (String[] arg) 
    {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Atarion";
        config.width = 1000;
        config.height = 800;
        new LwjglApplication(Atarion.getInstance(),config);
    }
}
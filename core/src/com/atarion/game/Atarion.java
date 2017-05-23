package com.atarion.game;

import com.atarion.game.interfaz.menu.MenuPrincipal;
import com.badlogic.gdx.Game;

public class Atarion extends Game
{   
    private static final Atarion atarion = new Atarion();
    
    
    public static Atarion getInstance()
    { return atarion;}
    
    
    @Override
    public void create() 
    { this.setScreen(new MenuPrincipal()); }
    @Override
    public void render()
    { super.render(); }
    @Override
    public void dispose()
    { super.dispose(); }
}

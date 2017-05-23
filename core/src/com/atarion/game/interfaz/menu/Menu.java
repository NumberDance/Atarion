package com.atarion.game.interfaz.menu;

import com.atarion.game.interfaz.Interfaz;

public abstract class Menu extends Interfaz
{
    @Override
    public void render(float delta)
    {
        super.render(delta);   
        this.controlarTeclado();
    }

    
    protected abstract void controlarTeclado();
}


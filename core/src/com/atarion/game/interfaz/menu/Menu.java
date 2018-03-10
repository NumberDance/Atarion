package com.atarion.game.interfaz.menu;

import com.atarion.game.Atarion;
import com.atarion.game.interfaz.Interfaz;

public abstract class Menu extends Interfaz
{
    public void mostrar()
    { Atarion.getInstance().setScreen(this); }
    @Override
    public void render(float delta)
    {
        super.render(delta);   
        this.controlarTeclado();
    }

    
    protected abstract void controlarTeclado();
}


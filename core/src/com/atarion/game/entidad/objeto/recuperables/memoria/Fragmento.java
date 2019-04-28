package com.atarion.game.entidad.objeto.recuperables.memoria;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Fragmento 
{
    private String identificador;
    private int espacio;
    private int tiempo;
    private String historia;
}

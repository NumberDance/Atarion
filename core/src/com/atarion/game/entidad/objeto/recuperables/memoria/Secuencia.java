package com.atarion.game.entidad.objeto.recuperables.memoria;


import java.util.HashSet;


public class Secuencia 
{
    private HashSet<Fragmento> fragmentos = new HashSet<>();
    
    
    public Secuencia()
    {
        
    }
    public void agregarFragmento(Fragmento fragmento)
    { fragmentos.add(fragmento); }
    public String ensamblar()
    {
        String veredicto = "IGNORANCIA";
        
        fragmentos.forEach
        (
            fragmento -> 
            {
           
            }
        );
        
        return veredicto;
    }
}

package com.atarion.game.interfaz.escena.online.cliente;


import java.util.HashSet;
import org.junit.Test;
import org.mockito.Mockito;


public class EscenaClienteTest
{
    private HashSet<EscenaCliente> clientes = new HashSet<>();
    
    
    public EscenaClienteTest() 
    {
        clientes.add(Mockito.spy(EscenaCliente.class));
        clientes.add(Mockito.spy(EscenaCliente.class));
    }
    @Test
    public void testShow() 
    {
        this.clientes.forEach
        (
            cliente -> 
            {
                new Thread()
                {
                   @Override
                   public void run()
                   { 
                       for(;;)
                       { cliente.render(0); } 
                   }
                };
            }
        );
    }
}

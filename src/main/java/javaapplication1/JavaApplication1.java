/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

/**
 *
 * @author T-
 */
public class JavaApplication1 implements CommandLineRunner {
    @Autowired RepositorioTarjeta repoTarje;
    @Autowired RepositorioCliente repoCli;


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
	}

    @Override
    public void run(String... strings) throws Exception {
        
        //Insertar tarjeta
        Tarjeta t=new Tarjeta("debito", 1500L,new Date(2017, 11, 10));
        
        //Buscar todas las tarjetas
        for( Tarjeta tarje:repoTarje.findAll()){
           System.out.println(tarje);
           
         //Actualizar tarjeta por su id
          repoTarje.save(new Tarjeta("credito",2500L, new Date(2017, 11, 10)));
         System.out.println(repoTarje.findOne(Long.MIN_VALUE));
    }
        
   }
}  


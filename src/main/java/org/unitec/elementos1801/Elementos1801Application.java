package org.unitec.elementos1801;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Elementos1801Application implements CommandLineRunner{
@Autowired RepositorioMensajito repoMensa;
	public static void main(String[] args) {
		SpringApplication.run(Elementos1801Application.class, args);
	}

    @Override
    public void run(String... strings) throws Exception {
        //repoMensa.save(new Mensajito("17 de octubre", "No tembló"));
       
        System.out.println("Vamos a buscar todos");
        for( Mensajito mensa:repoMensa.findAll()){
            System.out.println(mensa);
        }
        
        System.out.println("Vamos a buscar por id");
          System.out.println(repoMensa.findOne(1));
          
          
          //Actualizar
          repoMensa.save(new Mensajito(1,"nuevo titulo", "nuevo cuerpo"));
          System.out.println(repoMensa.findOne(1));
          
          //Busqueda personalizada
          for(Mensajito mensa:repoMensa.findByTitulo("nuevo titulo")){
              System.out.println(mensa);
          }
          
        
    }
}

package org.unitec.elementos1801;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Elementos1801Application implements CommandLineRunner{
@Autowired RepositorioMensajito repoMensa;
@Autowired RepositorioUsuario repoUsu;
@Autowired RepositorioDireccion repoDir;

	public static void main(String[] args) {
		SpringApplication.run(Elementos1801Application.class, args);
	}

    @Override
    public void run(String... strings) throws Exception {
        
        //vamos a generar un usuario
            Usuario u=new Usuario(12488361L, "Karen", "karenn1129@gmail.com");
        //la guardamos
          //  repoUsu.save(u);
       //Generamos la direcion
        Direccion d= new Direccion(new Usuario(12488361L), "Calle 13", 55380, "Ecatepec");
        //repoDir.save(d);
        
        //Aqui haremos el join
            Direccion d2=repoDir.findOne(1L);
            
            System.out.println("Correo: "+d2.getU().getEmail()+" Municipio "+d2.getMunicipio());
            
            
        
        
            
            
            
        //repoMensa.save(new Mensajito("17 de octubre", "No tembló"));
       
       // System.out.println("Vamos a buscar todos");
       // for( Mensajito mensa:repoMensa.findAll()){
      //      System.out.println(mensa);
      //  }
        
      //  System.out.println("Vamos a buscar por id");
      //    System.out.println(repoMensa.findOne(1));
          
          
          //Actualizar
        //  repoMensa.save(new Mensajito(1,"nuevo titulo", "nuevo cuerpo"));
       //   System.out.println(repoMensa.findOne(1));
          
          //Busqueda personalizada
        //  for(Mensajito mensa:repoMensa.findByTitulo("nuevo titulo")){
        //      System.out.println(mensa);
        //  }
          
        
    }
}

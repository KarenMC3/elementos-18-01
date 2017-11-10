/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.elementos1801;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author T-107
 */
@SpringUI
@Theme("valo")
public class MiUI extends UI  {
    
    @Autowired RepositorioMensajito repoMensa;
    @Override
    
    
    protected void init(VaadinRequest request) {
        VerticalLayout layout=new VerticalLayout();
        
        //Generamos una etiqueta
        Label etiqueta= new Label("Aplicacion con mensajes");
        etiqueta.addStyleName(ValoTheme.LABEL_H1);
        layout.addComponent(etiqueta);
        setContent(layout);
        Label etiquetaGuardar= new Label("Aplicacion con mensajes");
        etiquetaGuardar.addStyleName(ValoTheme.LABEL_H2);
        TextField textoTitulo=new TextField();
        //El siguiente es un placeholder
        textoTitulo.setPlaceholder("Escribe el titulo");
        TextArea textoCuerpo= new TextArea();
        textoCuerpo.setPlaceholder("Escribe el cuerpo del mensaje");
        Button boton=new Button("Guardar mensaje");
        boton.addStyleName(ValoTheme.BUTTON_DANGER);
        
        //Manejamos el evento del boton
        boton.addClickListener(evento->{
            
            if(textoCuerpo.getValue().equals("")||textoTitulo.getValue().equals("")){
                
                Notification.show("Los campos son requeridos", Notification.Type.ERROR_MESSAGE);
            }
            else{
                
            
            repoMensa.save(new Mensajito(textoTitulo.getValue() ,textoCuerpo.getValue() ));
            Notification.show("Se guardo!!", Notification.Type.ERROR_MESSAGE);
            }
        });
   

        // Create a grid bound to the list
        Grid<Mensajito> grid = new Grid<>();
        grid.setItems((List)repoMensa.findAll());
        grid.addColumn(Mensajito::getId).setCaption("Id del mensaje");
        grid.addColumn(Mensajito::getTitulo).setCaption("Titulo del mensaje");
        grid.addColumn(Mensajito::getCuerpo).setCaption("Cuerpo del mensaje");

       
        
        //Agregamos al layout todo
        layout.addComponent(etiqueta);
        layout.addComponent(etiquetaGuardar);
        layout.addComponent((Component) textoTitulo);
        layout.addComponent(textoCuerpo);
        layout.addComponent(boton);
         layout.addComponent(grid);
         
         
         
         //Primero creamos un horizontal layout
         HorizontalLayout layout1= new HorizontalLayout();
         TextField textoId= new TextField();
         textoId.setPlaceholder("Introduce el id");
         Button botonBuscarId = new Button("Buscar");
         botonBuscarId.addStyleName(ValoTheme.BUTTON_PRIMARY);
         layout1.addComponent(textoId);
         layout1.addComponent(botonBuscarId);
         
         layout.addComponent(layout1);
         
         //Creamos otro layout para los campos de texto
         HorizontalLayout layout2=new HorizontalLayout();
         TextField textoBuscarId= new TextField();
         TextField textoBuscarTitulo= new TextField();
         TextArea textoBuscarCuerpo= new TextArea();
         layout2.addComponent(textoBuscarId);
         layout2.addComponent(textoBuscarTitulo);
         layout2.addComponent(textoBuscarCuerpo);
         layout.addComponent(layout2);
         
         
         //agregamos el boton actualizar en el loyout1
         Button botonActualizar=new Button("Actualizar");
         botonActualizar.addStyleName(ValoTheme.BUTTON_FRIENDLY);
         layout.addComponent(botonActualizar);
         
         setContent(layout);
         
          //agregamos el boton eliminar
         Button botonEliminar=new Button("Eliminar");
         botonEliminar.addStyleName(ValoTheme.BUTTON_PRIMARY);
         layout1.addComponent(botonEliminar);
         
         
        
        
        
        //Vamos a buscar por id
        botonBuscarId.addClickListener(evento->{
            Mensajito mensa=repoMensa.findOne(Integer.parseInt(textoId.getValue()));
            //Ajustamos los tres campos con los datos 
            //Primero el id
            textoBuscarId.setValue(""+mensa.getId());
            textoBuscarTitulo.setValue(mensa.getTitulo());
            textoBuscarCuerpo.setValue(mensa.getCuerpo());
           
        });
        
        
         //vamos a actualizar
        
         botonActualizar.addClickListener(evento->{
         
            if(textoBuscarId.getValue().equals("")||textoBuscarTitulo.getValue().equals("") || textoBuscarCuerpo.getValue().equals("")){
                 Notification.show("Los campos son requeridos!!", Notification.TYPE_ERROR_MESSAGE); 
             }else{
             
         repoMensa.save(new Mensajito(Integer.parseInt(textoBuscarId.getValue()),
                 textoBuscarTitulo.getValue() ,textoBuscarCuerpo.getValue()));             
         Notification.show("se guardo el mensaje!!", Notification.TYPE_ERROR_MESSAGE);
        
             }
              grid.setItems((List)repoMensa.findAll());
              textoBuscarId.setValue("");
              textoBuscarTitulo.setValue("");
              textoBuscarCuerpo.setValue("");
         });
         
         botonEliminar.addClickListener(evento ->{
         
          if(textoBuscarId.getValue().equals("")||textoBuscarTitulo.getValue().equals("") || textoBuscarCuerpo.getValue().equals("")){
                 Notification.show("Los campos son requeridos!!", Notification.TYPE_ERROR_MESSAGE); 
             }else{
             
         repoMensa.delete(new Mensajito(Integer.parseInt(textoBuscarId.getValue()),
                 textoBuscarTitulo.getValue() ,textoBuscarCuerpo.getValue()));             
         Notification.show("se elimino el mensaje!!", Notification.TYPE_ERROR_MESSAGE);
        
             }
              grid.setItems((List)repoMensa.findAll());
              textoBuscarId.setValue("");
              textoBuscarTitulo.setValue("");
              textoBuscarCuerpo.setValue("");
         
         
         });
    }
    
    //capa model es diseño usando mvc, es la capa considerada el cerebro de nuestra aplicacion
    //agregando dependencias
}

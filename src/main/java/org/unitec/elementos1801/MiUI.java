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
            if(textoCuerpo.getValue().equals("")&&textoTitulo.getValue().equals("")){
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
        grid.addColumn(Mensajito::getTitulo).setCaption("Titulo del mensaje");
        grid.addColumn(Mensajito::getCuerpo).setCaption("Cuerpo del mensaje");

       
        
        //Agregamos al layout todo
        layout.addComponent(etiqueta);
        layout.addComponent(etiquetaGuardar);
        layout.addComponent((Component) textoTitulo);
        layout.addComponent(textoCuerpo);
        layout.addComponent(boton);
         layout.addComponent(grid);
        setContent(layout);
    }
    
    
}

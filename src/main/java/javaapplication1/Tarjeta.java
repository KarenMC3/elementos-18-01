package javaapplication1;

//import javax.persistence.Entity;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.Id;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author T-107
 */

@Entity

public class Tarjeta {
    
    @Id
    
    private String tipo;
    private Long saldo;
    private Date fecha;

    

    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Tarjeta(String tipo, Long saldo, Date fecha) {
        this.tipo = tipo;
        this.saldo = saldo;
        this.fecha = fecha;
    }

    public Tarjeta() {
    }

    public Long getSaldo() {
        return saldo;
    }

    public void setSaldo(Long saldo) {
        this.saldo = saldo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}

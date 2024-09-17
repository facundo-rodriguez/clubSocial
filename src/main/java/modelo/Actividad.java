/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author FACU
 */
public class Actividad {
    
   private int id_actividad;
   private String descripcion;
   private String hora;
   private Profesor profesor;

    public Actividad() {
    }

   
    public Actividad(String descripcion, String hora, Profesor fk_profesor) {
        this.descripcion = descripcion;
        this.hora = hora;
        this.profesor = fk_profesor;
    }

   
    public Actividad(int id_actividad, String descripcion, String hora, Profesor fk_profesor) {
        this.id_actividad = id_actividad;
        this.descripcion = descripcion;
        this.hora = hora;
        this.profesor = fk_profesor;
    }

    
    
    public int getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(int id_actividad) {
        this.id_actividad = id_actividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Profesor getFk_profesor() {
        return profesor;
    }

    public void setFk_profesor(Profesor fk_profesor) {
        this.profesor = fk_profesor;
    }
   
   
    
    
   
}

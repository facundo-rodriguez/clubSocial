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
public class Socio {
    
    private int id_socio;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String localidad;

    
    /*SIN ID*/
    public Socio(String nombre, String apellido, String email, String telefono, String localidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.localidad = localidad;
    }
    
    
    /*CON ID*/
    public Socio(int id_socio, String nombre, String apellido, String email, String telefono, String localidad) {
        this.id_socio = id_socio;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.localidad = localidad;
    }

    public Socio() {
    }

    public int getId_socio() {
        return id_socio;
    }

    public void setId_socio(int id_socio) {
        this.id_socio = id_socio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        return "Socio{" + "id_socio=" + id_socio + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", telefono=" + telefono + ", localidad=" + localidad + '}';
    }

            

}

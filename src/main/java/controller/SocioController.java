/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.SocioDAO;
import conexion.Conexion;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import modelo.Socio;

/**
 *
 * @author FACU
 */
public class SocioController {
    
    private SocioDAO socioDAO;
    
    public SocioController() throws SQLException{
        
        Conexion con= new Conexion();
        
        this.socioDAO= new SocioDAO(con.conexionBD());
    }
    
    
    public Socio agregar(Socio socio){
        
        return this.socioDAO.agregar(socio);
    }
    
    
    public int[] borrar(Socio socio){
        
        return this.socioDAO.borrar(socio);
        
    }
    
    public List<Socio> listar() throws SQLException{
        
       return this.socioDAO.listar();
    }
    
    
    public int modificar(Socio socio){
    
        return this.socioDAO.modificar(socio);
    }
    
    
    public Socio buscar(int id_socio){
    
    
        return this.socioDAO.buscar(id_socio);
    }
    
    
    
    public int socio_actividad(int id_socio, String actividad){
    
        
        return this.socioDAO.socio_actividad(id_socio, actividad);
    }
    
    
    public List< HashMap<String,String> > listar_socio_actividad(int id_socio){
    
        return this.socioDAO.listar_socio_actividad(id_socio);
    }
    
    
    public int borrar_socio_actividad(int id_socio, String actividad){
    
        return this.socioDAO.borrar_socio_actividad(id_socio, actividad);
    }
    
    
    
}

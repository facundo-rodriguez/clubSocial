
package controller;

import DAO.ActividadDAO;
import conexion.Conexion;
import java.util.List;
import modelo.Actividad;

public class ActividadController {

    private ActividadDAO actividadDAO;
    
    public ActividadController(){
    
        Conexion con= new Conexion();
        
        actividadDAO=new ActividadDAO( con.conexionBD() );
    }


    public List<Actividad> listar(){
    
        return this.actividadDAO.listar();
    }

    
    
    public Actividad buscar(int id_actividad){
    
        
        return this.actividadDAO.buscar(id_actividad);
    }
    
    
    public Actividad agregar(Actividad actividad){
    
        return this.actividadDAO.agregar(actividad);
    }

    
    public int[] borrar(Actividad actividad){
    
        return this.actividadDAO.borrar(actividad);
    }
    
    
    public int modificar(Actividad actividad){
        
        return this.actividadDAO.modificar(actividad);
    }
}

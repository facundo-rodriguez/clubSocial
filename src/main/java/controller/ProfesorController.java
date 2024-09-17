/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ProfesorDAO;
import java.sql.SQLException;
import java.util.List;
import modelo.Profesor;

/**
 *
 * @author FACU
 */
public class ProfesorController {
    
    ProfesorDAO profesorDAO;

    public ProfesorController()  {
        this.profesorDAO = new ProfesorDAO();
    }
    
    
    public List<Profesor> listar() {
        
       return this.profesorDAO.listar();
    }
    
    
    public Profesor agregar(Profesor profesor){
                   
        return  this.profesorDAO.agregar(profesor);
    
    }
    
    
    public int[] borrar(int id_profesor){
    
       return this.profesorDAO.borrar(id_profesor);
       
    }
    
    public Profesor buscar(int id_profesor){
    
        return this.profesorDAO.buscar(id_profesor);
    }
    
    
    public boolean modificar(Profesor profesor){
        
        return this.profesorDAO.modificar(profesor);
    }
    
    
    
}

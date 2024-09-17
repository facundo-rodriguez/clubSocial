/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Actividad;
import modelo.Profesor;

/**
 *
 * @author FACU
 */
public class ActividadDAO {
    
    Connection con;
    
    public ActividadDAO(Connection con){
        
        this.con=con;
    }

    
    public List<Actividad> listar(){
    
        String sql="select a.id as id, a.nombre as descripcion, a.horario as horario, a.fk_profesor as fk_profesor,"
                + "p.nombre as nombreProfesor, p.apellido as apellidoProfesor"
                + " from actividades a "
                + "inner join profesores p on p.id=a.fk_profesor";
        
        try(PreparedStatement statement= this.con.prepareStatement(sql);
            ResultSet resultSet= statement.executeQuery() ){
        
            List<Actividad> actividades= new ArrayList<>();
            
            while(resultSet.next() ){
                
                Profesor profesor=new Profesor();
                
                profesor.setId_profesor( resultSet.getInt("fk_profesor") );
                profesor.setNombre(resultSet.getString("nombreProfesor") );
                profesor.setApellido("apellidoProfesor");
                        
                Actividad actividad= new Actividad( resultSet.getInt("id"),
                                                    resultSet.getString("descripcion"),
                                                    resultSet.getString("horario"),
                                                    profesor
                                                );
                
                actividades.add(actividad);
            }
            
            return actividades;
            
        }catch(Exception e){

            throw new RuntimeException(e);
        }		
    
    }
    
    
    
    public Actividad buscar(int id_actividad){
    
        String sql =  "select a.nombre as actividad, a.horario as horario, a.fk_profesor as fk_profesor, "
                    + "p.nombre as profe from actividades a "
                    + "inner join profesores p on p.id=a.fk_profesor " 
                    + "where a.id=? ";

        
        try(PreparedStatement statement=this.con.prepareStatement(sql); ){
            
            statement.setInt(1, id_actividad);
            
            try(ResultSet resultSet=statement.executeQuery() ){
                
                Actividad actividad=new Actividad();
                Profesor profesor=new Profesor();
                
                while( resultSet.next() ){
                    
                    actividad.setId_actividad(id_actividad);//
                    actividad.setDescripcion(resultSet.getString("actividad"));
                    actividad.setHora(resultSet.getString("horario"));
                    
                    profesor.setId_profesor( Integer.valueOf(resultSet.getString("fk_profesor") ) );
                    profesor.setNombre(resultSet.getString("profe"));
                    
                    actividad.setFk_profesor(profesor);
                }
                
                return actividad;
            }
            
        }catch(Exception e){
        
            throw new RuntimeException(e);
        }
        		
    }
    
    
   public Actividad agregar(Actividad actividad){
   
        String sql="insert into actividades (nombre,horario,fk_profesor) values (?,?,?)";
        
        try(PreparedStatement statement=this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS) ){
        
            statement.setString(1, actividad.getDescripcion());
            statement.setString(2, actividad.getHora());
            statement.setInt(3, actividad.getFk_profesor().getId_profesor());
            
            statement.execute();
            
            try(ResultSet resultSet=statement.getGeneratedKeys()){
                
                while(resultSet.next()){
                    
                    actividad.setId_actividad(resultSet.getInt(1));
                }
                
                return actividad;
            }
        }catch(Exception e){
            
            throw new RuntimeException(e);
        }
    
   }
   
   
   public int[] borrar(Actividad actividad){
   
       String sql="DELETE FROM socio_actividad where fk_actividad=?";
       String sql2 = "DELETE FROM actividades WHERE id=?";
       
       try(PreparedStatement statement= this.con.prepareStatement(sql);
           PreparedStatement statement2= this.con.prepareStatement(sql2); ){
       
           statement.setInt(1, actividad.getId_actividad());
           statement2.setInt(1, actividad.getId_actividad());
           
           statement.execute();
           statement2.execute();
           
           int updateCount=statement.getUpdateCount();
           int updateCount2=statement2.getUpdateCount();
           
           int[] registros={updateCount, updateCount2};
           
           return registros;
           
       }catch(Exception e){
       
           throw new RuntimeException(e);
       }
   }
    
   public int modificar(Actividad actividad){
   
        String sql="update actividades set nombre=?, horario=?, fk_profesor=? where id=?"; 

        try(PreparedStatement statement=this.con.prepareCall(sql)){
            
            statement.setString(1, actividad.getDescripcion());
            statement.setString(2, actividad.getHora());
            statement.setInt(3, actividad.getFk_profesor().getId_profesor());
            statement.setInt(4, actividad.getId_actividad());
            
            statement.execute();
            
            int updateCount= statement.getUpdateCount();
            
            return updateCount;
        
        }catch(Exception e){
            
            throw new RuntimeException(e);
        }
   
   }
   
   


}

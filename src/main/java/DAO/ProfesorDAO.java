/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mysql.jdbc.Statement;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import modelo.Profesor;

/**
 *
 * @author FACU
 */
public class ProfesorDAO {
    
    private Connection con;

    public ProfesorDAO()  {
        
        Conexion conexion=new Conexion();
        this.con = conexion.conexionBD();
      
    }
    
    
    public List<Profesor> listar(){
    
        List<Profesor> listaProfesores=new ArrayList<>();
        
        String sql="select * from profesores";
        
        try( Statement miestado = (Statement) this.con.createStatement();
             ResultSet miresultado = miestado.executeQuery(sql) ){
        
            while(miresultado.next() ){
            
                Profesor Profesor=new Profesor( 
                    miresultado.getInt("id"),
                    miresultado.getString("nombre"),
                    miresultado.getString("apellido"),
                    miresultado.getString("correo"),
                    miresultado.getString("telefono"),
                    miresultado.getString("localidad")
                );
            
                listaProfesores.add(Profesor);
            
            }
            
            return listaProfesores;
            
        }catch(SQLException e){
            
            throw new RuntimeException(e);
        }
   
    }
    
    
    public Profesor agregar(Profesor profesor){
    
        String sql="insert into profesores (nombre,apellido,correo,telefono,localidad) "
                + "values(?,?,?,?,?)";
       
        try(PreparedStatement statement= this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
            
            statement.setString(1, profesor.getNombre());
            statement.setString(2, profesor.getApellido());
            statement.setString(3, profesor.getEmail());        
            statement.setString(4, profesor.getTelefono());    
            statement.setString(5, profesor.getLocalidad());
            
            statement.execute();
            
            try(ResultSet resultSet= statement.getGeneratedKeys()){
                
                while(resultSet.next()){

                     profesor.setId_profesor( resultSet.getInt(1) );
                } 
                
                return profesor;
            }
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    
    
    public int[] borrar(int id_profesor){
    
        String sql2 = "DELETE FROM actividades WHERE fk_profesor=?";
	String sql = "DELETE FROM profesores WHERE id=?";
        
        try(  PreparedStatement statement2= this.con.prepareStatement(sql2);
              PreparedStatement statement= this.con.prepareStatement(sql);
            ){
            
                statement2.setInt(1,id_profesor);
                statement.setInt(1,id_profesor);
                 
                
                statement2.execute();
                statement.execute();

                int updateCount2 = statement2.getUpdateCount();
                int updateCount = statement.getUpdateCount();

                int[] registros= {updateCount,updateCount2};
                
                return  registros;
        }
        catch(SQLException e){ 
            
            throw new RuntimeException(e);
        }
    
    }

    
    
    public Profesor buscar(int id_profesor){
    
        String sql = "select * from profesores where id=?"; 
        
        Profesor profesor= new Profesor();
        
        try(PreparedStatement statement= this.con.prepareStatement(sql) ){
            
            statement.setInt(1, id_profesor);

            try(ResultSet resultSet=  statement.executeQuery();){
                
                while(resultSet.next()){

                     profesor.setId_profesor(resultSet.getInt("id") );
                     profesor.setNombre(resultSet.getString("nombre"));
                     profesor.setApellido(resultSet.getString("apellido"));
                     profesor.setEmail(resultSet.getString("correo"));
                     profesor.setTelefono(resultSet.getString("telefono"));
                     profesor.setLocalidad(resultSet.getString("localidad"));

                }
            }

            return profesor;
            
        }catch(SQLException e){
            
            throw new RuntimeException(e);
        }
        
    }
    
    
    public boolean modificar(Profesor profesor){
    
        String sql="update profesores set nombre=? , apellido=? , correo=? , telefono=? , localidad=?"
                + " where id=?";
        
        try(PreparedStatement statement=this.con.prepareStatement(sql) ){
            
            statement.setString(1, profesor.getNombre() );
            statement.setString(2, profesor.getApellido() ) ;
            statement.setString(3, profesor.getEmail() );
            statement.setString(4, profesor.getTelefono() );
            statement.setString(5, profesor.getLocalidad() );
            statement.setInt(6, profesor.getId_profesor() );   
            
            statement.execute();
            
            boolean result=false;
            
            if(statement.getUpdateCount()!=0 ){
                
               result=true;
            }
            
            System.out.println("el resultado es "+ result);
            return  result;
        
        }catch(Exception e){
        
            throw new RuntimeException(e);
        }

    }
    
    
    
}    
    
    
    


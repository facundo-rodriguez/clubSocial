/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Socio;

/**
 *
 * @author FACU
 */
public class SocioDAO {

    private Connection con;
    
    public SocioDAO( Connection con) {
    
        this.con=con;
    }
    
    
    
    public Socio agregar(Socio socio){
    
	String sql="insert into socios (nombre,apellido,correo,telefono,localidad) values (?,?,?,?,?);";
        
        try(PreparedStatement statement= this.con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){
            
            statement.setString(1, socio.getNombre() );
            statement.setString(2, socio.getApellido() ) ;
            statement.setString(3, socio.getEmail() );
            statement.setString(4, socio.getTelefono() );
            statement.setString(5, socio.getLocalidad() );
            
            statement.execute();
            
            try(ResultSet resultSet= statement.getGeneratedKeys()){
                
                while(resultSet.next()){
                    
                   socio.setId_socio( resultSet.getInt(1) );
                }
                
                return socio;
            }
            
        }catch(Exception e){
        
            throw new RuntimeException(e);
        }

    }
    
    
    public int[] borrar(Socio socio){
    
        String sql2="DELETE FROM socio_actividad where fk_socio=?";
	String sql = "DELETE FROM socios WHERE id=?";
        
        try(PreparedStatement statement2=this.con.prepareStatement(sql2);
            PreparedStatement statement=this.con.prepareStatement(sql); ){
        
            statement2.setInt(1, socio.getId_socio());
            statement.setInt(1, socio.getId_socio());
            
            statement2.execute();
            statement.execute();

            int updateCount2 = statement2.getUpdateCount();
            int updateCount = statement.getUpdateCount();

            int[] registros= {updateCount,updateCount2};
                
            return  registros;
            
        }catch(Exception e){
        
            throw new RuntimeException(e);
        
        }
    
                
    }
    
    
    public List<Socio> listar() throws SQLException{
    
        List<Socio> listaSocios=new ArrayList<>();
        
        Statement miestado = (Statement) this.con.createStatement();
         
        ResultSet miresultado = miestado.executeQuery("select * from socios") ;
         
        while(miresultado.next() ){
            
            Socio socio=new Socio( 
                     miresultado.getInt("id") ,
                    miresultado.getString("nombre"),
                    miresultado.getString("apellido"),
                    miresultado.getString("correo"),
                    miresultado.getString("telefono"),
                    miresultado.getString("localidad")
            );
            
            listaSocios.add(socio);
            
        }
        
	miestado.close();
        
        return listaSocios;
    }
    
    
    public int modificar(Socio socio){
    
        String sql="update socios set nombre=? , apellido=? , correo=? , telefono=? , localidad=?"
                + " where id=?"; 
					
        try(PreparedStatement statement= this.con.prepareStatement(sql) ){
        
            statement.setString(1, socio.getNombre() );
            statement.setString(2, socio.getApellido() );
            statement.setString(3, socio.getEmail() );
            statement.setString(4, socio.getTelefono() );  
            statement.setString(5, socio.getLocalidad() );
            statement.setInt(6, socio.getId_socio() );

            statement.execute();
            
            
            return statement.getUpdateCount(); 
            
        }catch(Exception e){
        
            throw new RuntimeException(e);
        }
        
    }
    
    
    public Socio buscar(int id_socio){
    
        String sql = "select * from socios where id=?; "; 
        Socio socio=new Socio();
        
        try(PreparedStatement statement=this.con.prepareStatement(sql) ){
            
            statement.setInt(1, id_socio);
            
            try( ResultSet miresultado= statement.executeQuery();){
                
                
                while(miresultado.next()) {
                    
                    socio.setId_socio(miresultado.getInt("id"));
                    socio.setNombre(miresultado.getString("nombre"));
                    socio.setApellido(miresultado.getString("apellido"));
                    socio.setTelefono(miresultado.getString("telefono"));
                    socio.setLocalidad(miresultado.getString("localidad"));
                    socio.setEmail(miresultado.getString("correo"));

                }
                
                return socio;
            }
        
        }catch(Exception e){
            
            throw new RuntimeException(e);
        }
        
     }
    
    
    			
				 	
    public int socio_actividad(int id_socio, String actividad){
        
        String sql="insert into socio_actividad (fk_socio,fk_actividad) values (?,(select id from actividades where nombre=?) );";
        
        try(PreparedStatement statement=this.con.prepareStatement(sql) ){
        
            statement.setInt(1, id_socio);
            statement.setString(2, actividad);
            
            statement.execute();
            
            return statement.getUpdateCount(); 
        
        }catch(Exception e){
            
            throw new RuntimeException(e);
        }
        
    }
    
    
    public List< HashMap<String,String> > listar_socio_actividad(int id_socio){
    
        String sql="select s.id as id_socio, s.nombre as nombre, s.apellido as apellido , a.nombre as actividad from socios s  "
                + "inner join socio_actividad sa on sa.fk_socio=s.id "
                + "inner join actividades a on a.id=sa.fk_actividad "
                + "where sa.fk_socio=?";
        
        
        try(PreparedStatement statement= this.con.prepareStatement(sql) ){
        
            statement.setInt(1, id_socio);
            
            try(ResultSet resultSet= statement.executeQuery() ){
                
                List< HashMap<String,String> > lista=new ArrayList<>();
                
                while(resultSet.next() ){
                
                    HashMap<String,String> socio_actividad=new  HashMap<>();
                    
                    socio_actividad.put("id_socio", String.valueOf(resultSet.getString("id_socio") ) );
                    socio_actividad.put("nombre", resultSet.getString("nombre") );
                    socio_actividad.put("apellido", resultSet.getString("apellido")  );
                    socio_actividad.put("actividad", resultSet.getString("actividad") );
                    
                    lista.add(socio_actividad);

                }
            
                return lista;
            }
            
        }catch(Exception e){
        
            throw new RuntimeException(e);
        }

    }
        
    
    public int borrar_socio_actividad(int id_socio, String actividad){
    
        String sql="DELETE FROM socio_actividad where fk_socio=? and fk_actividad=(select id from actividades where nombre=? );";
		
        try(PreparedStatement statement= this.con.prepareStatement(sql) ){
            
            statement.setInt(1, id_socio);
            statement.setString(2, actividad);
            
            statement.execute();
        
            return statement.getUpdateCount();
        }catch(Exception e){
            
            throw new RuntimeException(e);
        }
    }
    
    
}

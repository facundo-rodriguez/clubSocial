
package com.club.vistasClub;


import javax.swing.JFrame;
import javax.swing.JPanel;



import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Actividad;
import modelo.Profesor;
import modelo.Socio;


public class VistaTabla extends JFrame{

	private JTable tabla_listar = new JTable();
	
	private JPanel panel=new JPanel();
	

        public	VistaTabla(){
		
		
                add(panel);
                
		panel.add(tabla_listar);
	}
	
	public void listar_socios( List<Socio> listaSocios /*ResultSet miresultado*/) {
		
		try {
			
			DefaultTableModel modelo_listar = new DefaultTableModel();
			
			modelo_listar.setColumnIdentifiers(new Object[] {"id","nombre","apellido","correo","telefono","localidad"});
	
			
			//ResultSet miresultado = miestado.executeQuery("select * from socios");
		
			/*while(miresultado.next() ) {
				
				modelo_listar.addRow(new Object[] {miresultado.getString("id"),miresultado.getString("nombre"),miresultado.getString("apellido"),miresultado.getString("correo"),miresultado.getString("telefono"),miresultado.getString("localidad")});
			}*/
			
                        for(Socio socio: listaSocios){
                            modelo_listar.addRow(new Object[] {socio.getId_socio(), socio.getNombre(), socio.getApellido(), socio.getEmail(), socio.getTelefono(), socio.getLocalidad()  } );
                        }
			tabla_listar.setModel(modelo_listar);
	
		}catch(Exception e1) {
			e1.printStackTrace(); 
			//System.out.println("error");
		}
	}

        
        public void listar_profesores( List<Profesor> listaProfesores /*ResultSet miresultado*/) {
		
		try {
			
			DefaultTableModel modelo_listar = new DefaultTableModel();
			
			modelo_listar.setColumnIdentifiers(new Object[] {"id","nombre","apellido","correo","telefono","localidad"});
	
                        for(Profesor profe: listaProfesores){
                            modelo_listar.addRow(new Object[] {profe.getId_profesor(), profe.getNombre(), profe.getApellido(), profe.getEmail(), profe.getTelefono(), profe.getLocalidad() } );
                        }
			tabla_listar.setModel(modelo_listar);
	
		}catch(Exception e1) {
			e1.printStackTrace(); 
			//System.out.println("error");
		}
	}
        
        
        
	public void listar_socio_actividad( List< HashMap<String,String> > lista) {
		
		
		try {
			
			DefaultTableModel modelo_listar = new DefaultTableModel();
			
			modelo_listar.setColumnIdentifiers(new Object[] {"id","nombre","apellido","actividad"});
	
		
                        for(HashMap<String,String> obj: lista){
                            modelo_listar.addRow(new Object[] {obj.get("id_socio"), obj.get("nombre"), obj.get("apellido"), obj.get("actividad") } );
			
                        
                        }
                        
		/*	while(lista.next()) {
				
			}
			
		*/
			tabla_listar.setModel(modelo_listar);
	
		}catch(Exception e1) {
			e1.printStackTrace(); 
			//System.out.println("error");
		}
		
	}
	
	
	public void listar_actividad(List<Actividad> actividades) {
		
		
		try {
			
			DefaultTableModel modelo_listar = new DefaultTableModel();
			
			modelo_listar.setColumnIdentifiers(new Object[] {"id","nombre","horario","cod_profesor","profesor"});
	
		
			for(Actividad actividad: actividades) {
				
				modelo_listar.addRow(new Object[] {actividad.getId_actividad(), actividad.getDescripcion(), actividad.getHora(), actividad.getFk_profesor().getId_profesor() , actividad.getFk_profesor().getNombre() + " " + actividad.getFk_profesor().getApellido() } ) ;
			}
			
		
			tabla_listar.setModel(modelo_listar);
	
		}catch(Exception e1) {
			e1.printStackTrace(); 
			//System.out.println("error");
		}
		
	}
	
	
	
}

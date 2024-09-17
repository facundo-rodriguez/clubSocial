
package com.club.vistasClub;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import controller.ProfesorController;
import modelo.Profesor;

public class VistaProfesor extends JFrame implements ActionListener{

	private JPanel contentPane;
	
	
	private JTextField id;
	private JTextField nombre;
	private JTextField apellido;
	private JTextField correo;
	private JTextField telefono;
	private JTextField localidad;


	public VistaProfesor() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		
		contentPane.setLayout(null);
		
		JLabel label_id_profesor = new JLabel("id");
		label_id_profesor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_id_profesor.setBounds(10, 11, 32, 20);
		contentPane.add(label_id_profesor);
		
		id = new JTextField();
		label_id_profesor.setLabelFor(id);
		id.setBounds(77, 13, 86, 20);
		contentPane.add(id);
		id.setColumns(10);
		
		JLabel label_nombre = new JLabel("nombre");
		label_nombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_nombre.setBounds(10, 56, 68, 14);
		contentPane.add(label_nombre);
		
		nombre = new JTextField();
		label_nombre.setLabelFor(nombre);
		nombre.setBounds(77, 55, 86, 20);
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		JLabel label_apellido = new JLabel("apellido");
		label_apellido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_apellido.setBounds(10, 94, 68, 14);
		contentPane.add(label_apellido);
		
		apellido = new JTextField();
		label_apellido.setLabelFor(apellido);
		apellido.setBounds(77, 93, 86, 20);
		contentPane.add(apellido);
		apellido.setColumns(10);
		
		correo = new JTextField();
		correo.setBounds(77, 132, 86, 20);
		contentPane.add(correo);
		correo.setColumns(10);
		
		JLabel label_correo = new JLabel("correo");
		label_correo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_correo.setLabelFor(correo);
		label_correo.setBounds(10, 135, 46, 14);
		contentPane.add(label_correo);
		
		telefono = new JTextField();
		telefono.setBounds(77, 171, 86, 20);
		contentPane.add(telefono);
		telefono.setColumns(10);
		
		JLabel label_telefono = new JLabel("tel");
		label_telefono.setLabelFor(telefono);
		label_telefono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_telefono.setBounds(10, 174, 46, 14);
		contentPane.add(label_telefono);
		
		localidad = new JTextField();
		localidad.setBounds(77, 211, 86, 20);
		contentPane.add(localidad);
		localidad.setColumns(10);
		
		JLabel label_localidad = new JLabel("localidad");
		label_localidad.setLabelFor(localidad);
		label_localidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_localidad.setBounds(10, 214, 68, 14);
		contentPane.add(label_localidad);
		
		JButton agregar = new JButton("agregar");
		agregar.setBounds(199, 12, 79, 23);
		contentPane.add(agregar);
		
		JButton borrar = new JButton("borrar");
		borrar.setBounds(313, 12, 89, 23);
		contentPane.add(borrar);
		
		JButton modificar = new JButton("modificar");
		modificar.setBounds(199, 64, 79, 23);
		contentPane.add(modificar);
		
		
		JButton listar = new JButton("listar");
		listar.setEnabled(true);
		listar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		listar.setBounds(313, 64, 89, 23);
		contentPane.add(listar);
		
		JButton buscar_uno = new JButton("buscar");
		buscar_uno.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buscar_uno.setBounds(199, 111, 89, 23);
		contentPane.add(buscar_uno);
		
		agregar.addActionListener(this);
		borrar.addActionListener(this);
		modificar.addActionListener(this);
		listar.addActionListener(this);
		buscar_uno.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) { 
		
		try {
			
                        ProfesorController profesorController= new ProfesorController();
                        
                        
			if(e.getActionCommand()=="agregar") {
				
				int n = JOptionPane.showConfirmDialog(null, "Esta seguro que desea agregar?");
				
				if(n==JOptionPane.YES_OPTION){
					
                                    //String sql="insert into profesores (nombre,apellido,correo,telefono,localidad) values("+"'"+ nombre.getText()+ "','"+  apellido.getText()+ "','"+ correo.getText()+"',"+ telefono.getText()+",'"+localidad.getText()+"')";
                                    
                                    Profesor profesor=new Profesor(
                                        nombre.getText(),
                                        apellido.getText(),
                                        correo.getText(), 
                                        telefono.getText(),
                                        localidad.getText()
                                    );       
                                    
                                   int id= profesorController.agregar(profesor).getId_profesor();
                                   
                                   JOptionPane.showMessageDialog(rootPane, "el profesor con id "+ id + " fue agregado");
				}
				 
			}
                        else if(e.getActionCommand()=="borrar"){
				
                            int n = JOptionPane.showConfirmDialog(null, "Esta seguro que desea borrar ?");
				
                            if(n==JOptionPane.YES_OPTION){
				
                                int[] registros=profesorController.borrar( Integer.valueOf( id.getText() ));
                                
                                JOptionPane.showMessageDialog(null, "Se han eliminado "+ registros[0] + " profesores, "
                                                                    + "y "+ registros[1] + " actividades");
                                
                                
                                id.setText("");
                                nombre.setText("");
                                apellido.setText("");
                                telefono.setText("");
                                localidad.setText("");
                                correo.setText("");


                            }
				
			}
			else if (e.getActionCommand()=="listar"){
				
                            VistaTabla obj_tabla=new VistaTabla();
				
                            obj_tabla.listar_profesores( profesorController.listar());
				
                            obj_tabla.setVisible(true);
                            obj_tabla.setBounds(400,400,600,600);
                             
			}
			else if(e.getActionCommand()=="buscar") {
                                 
                            Profesor profesor= profesorController.buscar( Integer.valueOf( id.getText() ) );
			
                            nombre.setText( profesor.getNombre() );
                            apellido.setText( profesor.getApellido() );
                            telefono.setText( profesor.getTelefono() );
                            localidad.setText( profesor.getLocalidad() );
                            correo.setText(  profesor.getEmail() );
				
			}
			
                        else if(e.getActionCommand()=="modificar"){
				
				
                            int n = JOptionPane.showConfirmDialog(null, "Esta seguro que desea modificar?");

                            if(n==JOptionPane.YES_OPTION){
                                    
                                    Profesor profesor=new Profesor(Integer.valueOf(id.getText()),
                                                                   nombre.getText(),
                                                                   apellido.getText(),
                                                                   correo.getText(),
                                                                   telefono.getText(),
                                                                   localidad.getText()
                                                                );
                                    
                                    boolean modificado= profesorController.modificar(profesor);
                                    
                                    if(modificado){
                                        
                                        JOptionPane.showMessageDialog(rootPane, "Profesor modificado");
                                    }


                            }
				
			}
			
	
		}catch(Exception e1) {
                        
                    JOptionPane.showMessageDialog(rootPane, "ocurrio un error :/");
			//e1.printStackTrace(); 

		}
		
		
	
	}

}

package com.club.vistasClub;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import controller.ActividadController;
import controller.ProfesorController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import modelo.Actividad;
import modelo.Profesor;

public class VistaActividades extends JFrame  implements ActionListener {

	private JPanel contentPane;
	private JTextField nombre_actividad;
	private JTextField horario;
	private JTextField codigo_actividad;
	private JTextField dni;
	private JTextField nombre_profesor;


	public VistaActividades() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblActividad = new JLabel("actividad");
		lblActividad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblActividad.setBounds(10, 65, 67, 24);
		contentPane.add(lblActividad);
		
		nombre_actividad = new JTextField();
		nombre_actividad.setBounds(94, 69, 98, 20);
		contentPane.add(nombre_actividad);
		nombre_actividad.setColumns(10);
		
		JLabel lblHorario = new JLabel("horario");
		lblHorario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHorario.setBounds(10, 126, 67, 20);
		contentPane.add(lblHorario);
		
		horario = new JTextField();
		horario.setBounds(93, 128, 99, 20);
		contentPane.add(horario);
		horario.setColumns(10);
		
		JLabel label_codigo = new JLabel("codigo");
		label_codigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_codigo.setBounds(10, 11, 46, 14);
		contentPane.add(label_codigo);
		
		codigo_actividad = new JTextField();
		codigo_actividad.setBounds(94, 8, 98, 20);
		contentPane.add(codigo_actividad);
		codigo_actividad.setColumns(10);
		
		JButton boton_listar = new JButton("listar");
		boton_listar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		boton_listar.setBounds(240, 9, 89, 23);
		contentPane.add(boton_listar);
		
		JButton boton_buscar = new JButton("buscar");
		boton_buscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		boton_buscar.setBounds(339, 9, 89, 23);
		contentPane.add(boton_buscar);
		
		JButton boton_modificar = new JButton("modificar");
		boton_modificar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		boton_modificar.setBounds(240, 65, 89, 23);
		contentPane.add(boton_modificar);
		
		JButton boton_agregar = new JButton("agregar");
		boton_agregar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		boton_agregar.setBounds(339, 66, 89, 23);
		contentPane.add(boton_agregar);
		
		JButton borrar = new JButton("borrar");
		borrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		borrar.setBounds(240, 127, 89, 23);
		contentPane.add(borrar);
		
		dni = new JTextField();
		dni.setBounds(94, 175, 98, 20);
		contentPane.add(dni);
		dni.setColumns(10);
		
		nombre_profesor = new JTextField();
		nombre_profesor.setEditable(false);
		nombre_profesor.setBounds(208, 175, 89, 20);
		contentPane.add(nombre_profesor);
		nombre_profesor.setColumns(10);
		
		JLabel label_dni = new JLabel("dni");
		label_dni.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_dni.setBounds(10, 178, 55, 14);
		contentPane.add(label_dni);
		
		
		boton_agregar.addActionListener(this);
		borrar.addActionListener(this);
		boton_modificar.addActionListener(this);
		boton_listar.addActionListener(this);
		boton_buscar.addActionListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e) { 
		
		try {
                        
                    ActividadController actividadController= new ActividadController();
                    ProfesorController profesorController= new ProfesorController();
			
                    if(e.getActionCommand()=="agregar") {
				
                        int n = JOptionPane.showConfirmDialog(null, "Esta seguro que desea agregar?");

                        if(n==JOptionPane.YES_OPTION){
                            
                            Profesor profesor= profesorController.buscar(Integer.valueOf(dni.getText()));
                            Actividad actividad=new Actividad(nombre_actividad.getText(),horario.getText(), profesor  );
                            
                            actividad=actividadController.agregar(actividad);
                            
                            JOptionPane.showMessageDialog(rootPane, "La actividad fue agregada con el id: " + actividad.getId_actividad() );
                        }
                    }
			
			else if(e.getActionCommand()=="borrar"){
				
				int n = JOptionPane.showConfirmDialog(null, "Esta seguro que desea borrar ?");
				
				if(n==JOptionPane.YES_OPTION){
                                    
                                    Profesor profesor=new Profesor(); //profesorController.buscar(Integer.valueOf(dni.getText()));
                                    Actividad actividad=new Actividad(Integer.valueOf(codigo_actividad.getText()), nombre_actividad.getText(), horario.getText(),profesor );
                                    
                                   int[] registros= actividadController.borrar(actividad);
                                    
                                   JOptionPane.showMessageDialog(rootPane, "se elimino la actividad con id "+ actividad.getId_actividad() +", y se elimino para " + registros[1] + " socios" );
                                   
                                    codigo_actividad.setText("");
                                    nombre_actividad.setText("");
                                    horario.setText("");
                                    dni.setText("");
					
					
				}
				
				
				
			}
			
			else if (e.getActionCommand()=="listar"){
				
				
				VistaTabla obj_tabla_actividad=new VistaTabla();
				
                                List<Actividad> actividades= actividadController.listar();
                                
				obj_tabla_actividad.listar_actividad(actividades);
				
				obj_tabla_actividad.setVisible(true);
				obj_tabla_actividad.setBounds(400,400,600,600);
				
			}
			else if(e.getActionCommand()=="buscar") {
				
                            Actividad actividad= actividadController.buscar(Integer.valueOf( codigo_actividad.getText() ));
                            
                            nombre_actividad.setText(actividad.getDescripcion());
                            horario.setText(actividad.getHora());
                            dni.setText( String.valueOf( actividad.getFk_profesor().getId_profesor() ) );
                            nombre_profesor.setText(actividad.getFk_profesor().getNombre());

                            
			}
			else if(e.getActionCommand()=="modificar") {
				
				
				int n = JOptionPane.showConfirmDialog(null, "Esta seguro que desea modificar?");
				
				if(n==JOptionPane.YES_OPTION){
                                    
                                    Profesor profesor=profesorController.buscar(Integer.valueOf(dni.getText()));
                                    Actividad actividad=new Actividad(Integer.valueOf(codigo_actividad.getText()),nombre_actividad.getText(), horario.getText(), profesor );
					
                                    int registros=actividadController.modificar(actividad);
                                    
                                    JOptionPane.showMessageDialog(rootPane, "se ha modificado "+ registros + " registros");
				}
				
			}
	
		}catch(Exception e1) {
			e1.printStackTrace(); 
		
		}
		
		
	}
	
}

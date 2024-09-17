package com.club.vistasClub;

import java.awt.EventQueue;

import conexion.Conexion;

import com.club.vistasClub.VistaTabla;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import controller.SocioController;

import javax.swing.JOptionPane;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JTextField;
import javax.swing.JButton;
import modelo.Socio;

public class VistaSocios extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField id;
	private JTextField nombre;
	private JTextField apellido;
	private JTextField correo;
	private JTextField telefono;
	private JTextField localidad;
	
	private JTextField actividad;
	
	JTable tabla_listar = new JTable();


	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					socios frame = new socios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	 * Create the frame.
	 */
	public VistaSocios() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labe_id_socio = new JLabel("id:");
		labe_id_socio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labe_id_socio.setBounds(10, 11, 32, 20);
		contentPane.add(labe_id_socio);
		
		id = new JTextField();
		labe_id_socio.setLabelFor(id);
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
		
		JButton buscar_uno = new JButton("buscar_uno");
		buscar_uno.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buscar_uno.setBounds(199, 111, 100, 23);
		contentPane.add(buscar_uno);
		
		JButton listar_actividad = new JButton("listar_actividad");
		listar_actividad.setFont(new Font("Tahoma", Font.PLAIN, 11));
		listar_actividad.setBounds(250, 180, 170, 23);
		contentPane.add(listar_actividad);
		
		JButton borrar_actividad = new JButton("borrar_actividad");
		borrar_actividad.setFont(new Font("Tahoma", Font.PLAIN, 11));
		borrar_actividad.setBounds(250, 210, 170, 23);
		contentPane.add(borrar_actividad);
		
		JButton agregar_actividad = new JButton("agregar_actividad");
		agregar_actividad.setFont(new Font("Tahoma", Font.PLAIN, 11));
		agregar_actividad.setBounds(250, 150, 170, 23);
		contentPane.add(agregar_actividad);
		
		
                actividad = new JTextField();
		actividad.setBounds(450, 153, 200, 20);
		contentPane.add(actividad);
		actividad.setColumns(10);
		
		JLabel label_actividad = new JLabel("actividad");
		label_actividad.setLabelFor(actividad);
		label_actividad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_actividad.setBounds(450, 130, 100, 14);
		contentPane.add(label_actividad);
		
		
		agregar.addActionListener(this);
		borrar.addActionListener(this);
		modificar.addActionListener(this);
		listar.addActionListener(this);
		buscar_uno.addActionListener(this);
		agregar_actividad.addActionListener(this);
		listar_actividad.addActionListener(this);
		borrar_actividad.addActionListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e) { 
		
		try {
			
                        Conexion miconeccion= new Conexion();
                        
                        Statement miestado = (Statement) miconeccion.conexionBD().createStatement();
			
			SocioController socioController=new SocioController();
                        
                        if(e.getActionCommand()=="agregar") {
				
				
				int n = JOptionPane.showConfirmDialog(null, "Esta seguro que desea agregar ?");
				
				if(n==JOptionPane.YES_OPTION){
					
                                    Socio socio= new Socio(nombre.getText(),
                                                           apellido.getText(),
                                                           correo.getText(),
                                                           telefono.getText(),
                                                           localidad.getText()
                                                        );
                                    
                                    Socio s= socioController.agregar(socio);
                                    
                                    JOptionPane.showMessageDialog(rootPane, "El socio con id " + s.getId_socio() + " fue agregado con exito");
				}
				
			}
			
			else if(e.getActionCommand()=="borrar"){
				
				int n = JOptionPane.showConfirmDialog(null, "Esta seguro que desea borrar ?");
				
				if(n==JOptionPane.YES_OPTION){
					
                                    Socio socio= new Socio( Integer.valueOf(id.getText() ),
                                                            nombre.getText(),
                                                            apellido.getText(),
                                                            correo.getText(),
                                                            telefono.getText(),
                                                            localidad.getText()
                                                            
                                                          );
                                    
                                    int[] registros=socioController.borrar(socio);
                                    
                                    JOptionPane.showMessageDialog(rootPane, "se elimino el socio"+ socio.getId_socio() + ", sus " + registros[1] + " actividades." );
                                    
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
				
				obj_tabla.listar_socios( socioController.listar());
				
				obj_tabla.setVisible(true);
				obj_tabla.setBounds(400,400,600,600);
				miestado.close();
			
				
			}
			else if(e.getActionCommand()=="buscar_uno") {
				
				Socio socio= socioController.buscar( Integer.valueOf( id.getText() ) );
				
                                id.setText(String.valueOf(socio.getId_socio() ) );
                                nombre.setText(socio.getNombre() );
                                apellido.setText(socio.getApellido() );
                                telefono.setText(socio.getTelefono() );
                                localidad.setText(socio.getLocalidad() );
                                correo.setText(socio.getEmail() );
			}
			
			
			else if(e.getActionCommand()=="modificar") {
				
				
				int n = JOptionPane.showConfirmDialog(null, "Esta seguro que desea modificar?");
				
				if(n==JOptionPane.YES_OPTION){
					
                                     Socio socio= new Socio( Integer.valueOf(id.getText() ),
                                                            nombre.getText(),
                                                            apellido.getText(),
                                                            correo.getText(),
                                                            telefono.getText(),
                                                            localidad.getText()
                                                            
                                                          );
                                     
                                    int result= socioController.modificar(socio);
                                    
                                    String msj="registros modificados: " + result;
                                    
                                    JOptionPane.showMessageDialog(rootPane, msj);
                                    
					
				}
				
			}
			else if(e.getActionCommand()=="agregar_actividad") {
				
			
					int n = JOptionPane.showConfirmDialog(null, "Esta seguro que desea agregar la actividad al socio con id "+ id.getText() + " ?");
					
					if(n==JOptionPane.YES_OPTION){
						
                                            int ingresado=socioController.socio_actividad(Integer.valueOf(id.getText() ), actividad.getText());
						
                                            if(ingresado==1){
                                                    
                                                JOptionPane.showMessageDialog(rootPane, "El socio " + id.getText() + " se registró a la actividad "+ actividad.getText());
                                            }
                                                
                                               
					}
					
			}
			else if(e.getActionCommand()=="listar_actividad") {
				
				
				VistaTabla obj_socio_actividad=new VistaTabla();
				
				List< HashMap<String, String> > lista=socioController.listar_socio_actividad( Integer.valueOf( id.getText() ) );
				
                                obj_socio_actividad.listar_socio_actividad(lista);
                                
				obj_socio_actividad.setVisible(true);
				obj_socio_actividad.setBounds(400,400,400,400);
				
			}
			
			else if(e.getActionCommand()=="borrar_actividad"){
				
				int n = JOptionPane.showConfirmDialog(null, "Esta seguro que desea borrar la actividad al socio con id "+id.getText()+" ?");
				
				if(n==JOptionPane.YES_OPTION){
                                    
                                    int eliminado=socioController.borrar_socio_actividad(Integer.valueOf(id.getText()), actividad.getText() );
                                    
                                    if(eliminado==1){
                                                    
                                        JOptionPane.showMessageDialog(rootPane, "Se elimino la actividad " + actividad.getText() + " del socio "+ id.getText() );
                                    }
                                    
                                    id.setText("");
                                    nombre.setText("");
                                    apellido.setText("");
                                    telefono.setText("");
                                    localidad.setText("");
                                    correo.setText("");
                                    actividad.setText("");

				}
				
			}
	
		}catch(Exception exc){
			exc.printStackTrace(); 
                        JOptionPane.showMessageDialog(rootPane, "Ocurrio un error: " + exc.getMessage());
                                            
		}
		
		
	}
    }

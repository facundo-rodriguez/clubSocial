package com.club.vistasClub;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;


import javax.swing.*;

import java.awt.event.*;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;




public class VistaLogin extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField usuario;
	private JPasswordField clave;
	
	public VistaLogin() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_usuario = new JLabel("usuario");
		label_usuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_usuario.setBounds(10, 11, 55, 35);
		contentPane.add(label_usuario);
		
		usuario = new JTextField();
		label_usuario.setLabelFor(usuario);
		usuario.setBounds(88, 20, 143, 20);
		contentPane.add(usuario);
		usuario.setColumns(10);
		
		JLabel label_clave = new JLabel("password");
		label_clave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_clave.setBounds(10, 73, 93, 19);
		contentPane.add(label_clave);
		
		
		clave = new JPasswordField();
		label_clave.setLabelFor(clave);
		clave.setBounds(88, 74, 143, 20);
		contentPane.add(clave);
		
		
		JButton boton_acceder = new JButton("acceder");
		boton_acceder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		boton_acceder.setBounds(88, 161, 89, 23);
		contentPane.add(boton_acceder);
		
		boton_acceder.addActionListener(this);
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		
		//if(e.getActionCommand()=="Aceptar")
				
				if(e.getActionCommand()=="acceder"){
					
					try {
						Connection miconeccion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/club","root","");
						Statement miestado = (Statement) miconeccion.createStatement();
					
						
						String sql="select * from usuario where nombre='"+usuario.getText()+"' and clave='"+clave.getText()+"' limit 1";
						
						//System.out.println(sql);
						
						ResultSet miresultado = miestado.executeQuery(sql);
						
						
						
						/*if(miresultado!=){
							menu menu = new menu();
							menu.setVisible(true);
							
							this.setVisible(false); //hago referencia al objeto de la clase login
							//System.out.println(miresultado.getString("nombre") + " bienvenido");
							
						}*/
						
						while(miresultado.next()) {
							
							VistaMenu menu = new VistaMenu();
							menu.setVisible(true);
							
							this.setVisible(false); //hago referencia al objeto de la clase login
							System.out.println(miresultado.getString("nombre") + " bienvenido");
							
							
						}
					
					
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					
				}
			
                            }

}

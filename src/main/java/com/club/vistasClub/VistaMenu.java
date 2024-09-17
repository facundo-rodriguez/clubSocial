
package com.club.vistasClub;

import com.club.vistasClub.VistaProfesor;
import com.club.vistasClub.VistaActividades;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class VistaMenu extends JFrame implements ActionListener{

	private JPanel contentPane;


	public VistaMenu() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton boton_socios = new JButton("socios");
		boton_socios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		boton_socios.setBounds(10, 58, 89, 23);
		contentPane.add(boton_socios);
		
		JButton boton_profesores = new JButton("profesores");
		boton_profesores.setFont(new Font("Tahoma", Font.PLAIN, 14));
		boton_profesores.setBounds(144, 60, 109, 23);
		contentPane.add(boton_profesores);
		
		JButton boton_actividad = new JButton("actividades");
		boton_actividad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		boton_actividad.setBounds(295, 60, 99, 23);
		contentPane.add(boton_actividad);
		
		JButton boton_usuario = new JButton("usuario");
		boton_usuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		boton_usuario.setBounds(152, 111, 89, 23);
		contentPane.add(boton_usuario);
		
		
		boton_socios.addActionListener(this);
		boton_profesores.addActionListener(this);
		boton_actividad.addActionListener(this);
		boton_usuario.addActionListener(this);
		
		
		
	}
	
	
        @Override
	public void actionPerformed(ActionEvent e) { 
		
		if(e.getActionCommand()=="socios") {
			
			VistaSocios socio=new VistaSocios();		
			socio.setVisible(true);
		}
		
		
		else if(e.getActionCommand()=="profesores") {
			
			VistaProfesor profe=new VistaProfesor();		
			profe.setVisible(true);
			
		}
		else if(e.getActionCommand()=="actividades") {
			VistaActividades actividad=new VistaActividades();		
			actividad.setVisible(true);
		}
		
		
	}
	
}

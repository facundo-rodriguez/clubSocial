
package com.club.vistasClub;

//import com.club.vistasClub.login;

import controller.ProfesorController;
import controller.SocioController;
import java.awt.EventQueue;
import java.util.List;
import modelo.Profesor;
import modelo.Socio;

public class VistaPrincipal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("arranco");
                
		
			EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaLogin login= new VistaLogin();
					login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
			
                     /*   
                       try{
                           
                              SocioController socioController=new SocioController();
                           
                           List<Socio> socios= socioController.listar();
                           
                           for( Socio socio: socios){
                               
                               System.out.println(socio);
                               
                           }
                           
                           
                           ProfesorController profesorController=new ProfesorController();
                           
                           List<Profesor> profesores= profesorController.listar();
                           
                           for( Profesor profesor: profesores){
                               
                               System.out.println(profesor);
                               
                           }
                           
                        
                           
                           
                       }catch(Exception e){
                       
                       }
		*/
                
                System.out.println("termino");
                

	}

}

package A_Star;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class InterfaceGrafica extends JFrame {

	private JPanel PainelDeFundo;
	private JPanel PainelLateralDireito;
	private DesenhaMapa mapa_desenhado;
	private int sleep = 200;
	public Mapa mapa;
	
	public InterfaceGrafica (Mapa mapa ){
		
		this.mapa = mapa;
		PainelDeFundo = new JPanel ();
		PainelDeFundo.setLayout(new BorderLayout ());
				
		PainelLateralDireito=new JPanel ();
		PainelLateralDireito.setLayout(new BoxLayout(PainelLateralDireito, BoxLayout.Y_AXIS));
		
		mapa_desenhado = new DesenhaMapa(mapa.getMatriz(), 25,1, 10, 10);	
		
		JButton JBExecutarComAnimacao = new JButton("Animação");

		JBExecutarComAnimacao.addActionListener( new ActionListener() {
			public 	void actionPerformed (ActionEvent e){
				InterfaceGrafica.this.executarComAnimacao();
			}
		});
		PainelLateralDireito.add(JBExecutarComAnimacao);
		
		JButton JBExecutarSemAnimacao = new JButton("Sem Animação");
		JBExecutarSemAnimacao.addActionListener( new ActionListener() {
			public 	void actionPerformed (ActionEvent e){
				InterfaceGrafica.this.executarSemAnimacao ();
			}
		});
		PainelLateralDireito.add(JBExecutarSemAnimacao);
		
		JButton JBReset = new JButton("Reset");
		JBReset.addActionListener( new ActionListener() {
			public 	void actionPerformed (ActionEvent e){
				InterfaceGrafica.this.reset ();
			}
		});
		PainelLateralDireito.add(JBReset);
		
		this.getContentPane().add(PainelDeFundo);
		PainelDeFundo.add(BorderLayout.EAST, PainelLateralDireito);
		PainelDeFundo.add(BorderLayout.CENTER, mapa_desenhado);
	
		this.setSize(1100, 590);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	private void tempo (){

		try{
			Thread.sleep(sleep);   //Serve para deixar tempo do deslocamento vertical maior que o deslocamento horizontal da AI.
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void executarComAnimacao (){

		Thread novaThread = new Thread() {  
			  
		public void run() {  		
	        	 
		 	ArrayList<No> trajeto = InterfaceGrafica.this.mapa.getTrajeto();
	 		No no;
	 		for(int i=(trajeto.size()-1); i >= 0;i--){
	 			no = trajeto.get(i);
	 			if(no.getTipo_no()!= 2 && no.getTipo_no() !=3){  //Para a origem e o destino não mudarem de cor.
	 				InterfaceGrafica.this.mapa.setElemento(no.getX(), no.getY(), 5);
		 			EventQueue.invokeLater(new Runnable() {    
		 				   public void run() {    
		 					  InterfaceGrafica.this.repaint();      
		 				   }
		 			}    
		 			);
	 			}	     			
	 		
	 			InterfaceGrafica.this.tempo();      		
	 		} 		  
		}  
		};novaThread.start(); 		
	}
	
	public void executarSemAnimacao (){
		ArrayList<No> trajeto = mapa.getTrajeto();
		No no;
 		for(int i=(trajeto.size()-1); i>=0;i--){
 			no = trajeto.get(i);
			if(no.getTipo_no()!= 2 && no.getTipo_no() !=3){  //Para a origem e o destino não mudarem de cor.
				mapa.setElemento(no.getX(), no.getY(), 5);
				this.repaint();
			}
		}
	}
	
	public void reset (){
		mapa.criarMatriz();
		this.repaint();

	}

}

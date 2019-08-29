package A_Star;


import java.awt.*;
import javax.swing.*;

public class InterfaceGrafica extends JFrame {

	private JPanel PainelDeFundo;
	private JPanel PainelLateralDireito;
	private DesenhaMapa mapa_desenhado;
	
	public InterfaceGrafica (Mapa mapa ){
		
		PainelDeFundo = new JPanel ();
		PainelDeFundo.setLayout(new BorderLayout ());
				
		PainelLateralDireito=new JPanel ();
		PainelLateralDireito.setLayout(new BoxLayout(PainelLateralDireito, BoxLayout.Y_AXIS));
		
		mapa_desenhado = new DesenhaMapa(mapa, 30,1, 0, 0)	;	
		
		this.getContentPane().add(PainelDeFundo);
		PainelDeFundo.add(BorderLayout.EAST, PainelLateralDireito);
		PainelDeFundo.add(BorderLayout.CENTER, mapa_desenhado);
	
		this.setSize(600, 700);
		//this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
	}



}

package A_Star;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;


public class DesenhaMapa extends JPanel {
		
	private int larguracontorno;
	private	int larguraQuadrado;
	private	int x,y;
	private Mapa mapa;
	int [][] m_mapa;
		
	public DesenhaMapa(int [][] m_mapa, int larguraQuadrado, int larguraContorno, int origemX, int origemY){
		
		this.m_mapa = m_mapa;
		this.larguraQuadrado=larguraQuadrado;
		this.larguracontorno=larguraContorno;
		this.x=origemX;
		this.y=origemY;
	}
		
	public void paintComponent(Graphics a){
		
		for (int i=0; i < m_mapa.length; i++  ){
			for (int j=0; j< m_mapa[0].length; j++){	
				int tipo_no = m_mapa[i][j];
				switch(tipo_no){
				
					case 0: a.setColor(Color.LIGHT_GRAY);
							a.fillRect( ( j*(larguraQuadrado+larguracontorno) +x), ( i*(larguraQuadrado+larguracontorno) +y), larguraQuadrado, larguraQuadrado);
								
							break;
				
					case 1: a.setColor(Color.red);
							a.fillRect( (j*(this.larguraQuadrado+larguracontorno) +x), ( i*(this.larguraQuadrado+larguracontorno) +y), this.larguraQuadrado, this.larguraQuadrado);
						    
							break;
							
					case 2: a.setColor(Color.green);
							a.fillRect( (j*(this.larguraQuadrado+larguracontorno) +x), ( i*(this.larguraQuadrado+larguracontorno) +y), this.larguraQuadrado, this.larguraQuadrado);
							break;
					
					case 3: a.setColor(Color.blue);
							a.fillRect( (j*(this.larguraQuadrado+larguracontorno) +x), ( i*(this.larguraQuadrado+larguracontorno) +y), this.larguraQuadrado, this.larguraQuadrado);
							break;
					
					case 4: a.setColor(Color.yellow);
							a.fillRect( (j*(this.larguraQuadrado+larguracontorno) +x), ( i*(this.larguraQuadrado+larguracontorno) +y), this.larguraQuadrado, this.larguraQuadrado);
							break;
					
					case 5: a.setColor(Color.WHITE);
							a.fillRect( (j*(this.larguraQuadrado+larguracontorno) +x), ( i*(this.larguraQuadrado+larguracontorno) +y), this.larguraQuadrado, this.larguraQuadrado);
							break;
					
					case 6: a.setColor(Color.cyan);
							a.fillRect( (j*(this.larguraQuadrado+larguracontorno) +x), ( i*(this.larguraQuadrado+larguracontorno) +y), this.larguraQuadrado, this.larguraQuadrado);
							break;
					
					case 7: a.setColor(Color.orange);
							a.fillRect( (j*(this.larguraQuadrado+larguracontorno) +x), ( i*(this.larguraQuadrado+larguracontorno) +y), this.larguraQuadrado, this.larguraQuadrado);
							break;
										
				}				
			
			}
		
		}
		
	}

}
		



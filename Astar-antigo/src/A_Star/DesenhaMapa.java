package A_Star;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;


public class DesenhaMapa extends JPanel {
		
	private int larguracontorno;
	private	int larguraQuadrado;
	private	int x,y;
	private Mapa mapa;
		
	public DesenhaMapa(Mapa mapa, int larguraQuadrado, int larguraContorno, int origemX, int origemY){
		
		this.mapa = mapa; 
		this.larguraQuadrado=larguraQuadrado;
		this.larguracontorno=larguraContorno;
		this.x=origemX;
		this.y=origemY;
	}
		
	public void paintComponent(Graphics a){
		
		int i = 0, j = 0;		
		for (ArrayList<No> b:mapa.getConjuntoNos() ){
			i++;
			for (No c:b){
				j++;
				int tipo_no = c.getTipo_no();
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
			j = 0;
		}
		
	}

}
		



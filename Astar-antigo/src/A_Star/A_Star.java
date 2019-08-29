package A_Star;

import java.util.ArrayList;
import java.util.Collections;

public class A_Star {
	
	private Heuristica heuristica;
	private Mapa mapa;
	private ArrayList<No> lista_aberta;
	private ArrayList<No> lista_fechada;
	private No no_origem, no_destino;
	private double distancia_nos_adjacentes = 10;
	private ArrayList<No> trajeto;
	
	
	public static void main (String [] args){
		
		int [][] m_obstaculos = { 
								{2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
								};
		Heuristica heuristica = new Manhattan();
		A_Star a_star = new A_Star(m_obstaculos, 0, 0, 6, 4, heuristica);
		/*int i = 0, j = 0;		
		for (ArrayList<No> b:mapa.getConjuntoNos() ){
			j++;
			for (No c:b){
				i++;
				System.out.print(c.getTipo_no()+" ");
				
			}
			System.out.println("");
		}
		*/
	
		
		
	}
	
	public A_Star (int [][] m_obstaculos, int x_origem, int y_origem, int x_destino, int y_destino, Heuristica heuristica){
		
		this.heuristica = heuristica;
		this.mapa = new Mapa(m_obstaculos, x_origem, y_origem, x_destino, y_destino);
		this.lista_aberta = new ArrayList<No> ();
		this.lista_fechada = new ArrayList<No> ();
		this.no_origem = mapa.getNoOrigem();
		this.no_destino = mapa.getNoDestino();
		
		ArrayList<No> trajetoteste = this.executar();
		System.out.println(trajetoteste.size());
		
		for(No c:trajetoteste){
			System.out.println(c);
		}
		
		
		
		InterfaceGrafica interfaceGrafica = new InterfaceGrafica(mapa);

		
	}
	
	public ArrayList<No> executar (){
		
		this.lista_aberta.add(no_origem);
		
		no_origem.setG(0);
		no_origem.setH(this.valorH(no_origem, no_destino));
		no_origem.setF(no_origem.getG()+no_origem.getH());
		this.trajeto = new ArrayList<No>();
		
		No no_atual;
		double g_tentativa = 0;
		while (!lista_aberta.isEmpty()){
			Collections.sort(lista_aberta);
			no_atual = lista_aberta.get(0);  //Pega o nó com menor valor de F da lista aberta
			
			if((no_atual.getX() == no_destino.getX())&&(no_atual.getY() == no_destino.getY())){                   //Então há caminho possível
				trajeto.add(no_destino);
				no_atual = no_destino.getPai();
				
				while(no_atual != no_origem){
					trajeto.add(no_atual);
					no_atual = no_atual.getPai();
				}
				trajeto.add(no_origem);				
				
				return trajeto;
			}
			
			lista_aberta.remove(no_atual);
			lista_fechada.add(no_atual);
			no_atual.setEstado(2);            //2 significa que o nó está na lista fechada
			
			for(No no_vizinho:no_atual.getVizinhos()){
				
				if((no_vizinho.getEstado() == 2)||no_vizinho.isObstaculo()){//Se está na lista fechada ou é obstáculos
					continue;
				}
				
				g_tentativa = no_atual.getG() + distancia_nos_adjacentes;
				if( (no_vizinho.getEstado() != 1)){              //Não está na lista aberta
					lista_aberta.add(no_vizinho);
					no_vizinho.setEstado(1);
					no_vizinho.setPai(no_atual);
					no_vizinho.setG(g_tentativa);
					no_vizinho.setH(this.valorH(no_vizinho, no_destino));
					no_vizinho.setF(no_vizinho.getG()+no_vizinho.getH());
				}else{
					if(g_tentativa < no_vizinho.getG()){
						no_vizinho.setPai(no_atual);
						no_vizinho.setG(g_tentativa);
						no_vizinho.setF(no_vizinho.getG()+no_vizinho.getH());
					}
				}
				
			}
			
		}//Fim while
		
		return null; //Não há caminho possível
		
	}	
	
	public double valorH (No atual, No destino){
		
		return heuristica.valorHeuristica(atual, destino);
		
	}

}

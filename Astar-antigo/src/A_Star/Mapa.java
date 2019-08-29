package A_Star;

import java.util.ArrayList;

public class Mapa {
	
	private int largura;
	private int altura;
	private ArrayList<ArrayList <No>> conjunto_nos;
	private int x_origem, y_origem,  x_destino,  y_destino;
	private int [][] m_obstaculos;
	
	//1 na matriz indica onde tem obstábulos
	public Mapa (int [][] m_obstaculos, int x_origem, int y_origem, int x_destino, int y_destino){
		this.largura = m_obstaculos[0].length;
		this.altura = m_obstaculos.length;
		this.m_obstaculos = m_obstaculos;
		this.x_origem = x_origem;
		this.y_origem = y_origem;	
		this.x_destino = x_destino;
		this.y_destino = y_destino;
		this.criarconjunto_nos ();
		
	}
	
	//Criar estrutura de nós nos Arrays de acordo com a matriz de obstáculos
	private void criarconjunto_nos (){
		
		conjunto_nos = new ArrayList<ArrayList<No>> ();
		for(int i =0; i < this.altura; i++){
			conjunto_nos.add(new ArrayList<No> ());
			for(int j = 0; j < this.largura; j++){
				No no = new No (j,i);
				if(m_obstaculos[j][i] == 1){
					no.setObstaculo();
					
				}
				no.setTipo_no(m_obstaculos[i][j]);
				conjunto_nos.get(i).add(no);
			}
		}
		
	//Criar conexões entre os nós e definir conjunto de vizinhos
	No no_temp;
	No no_atual;
	for(int i =0; i < this.altura; i++){
		for(int j = 0; j < this.largura; j++){	
			no_atual = conjunto_nos.get(i).get(j);
			if( (j-1) >= 0){
				no_temp = conjunto_nos.get(i).get(j-1);
				no_atual.setOeste(no_temp);
				no_atual.addVizinho(no_temp);
			}
			if( (j+1)<this.largura){
				no_temp = conjunto_nos.get(i).get(j+1);
				no_atual.setLeste(no_temp);
				no_atual.addVizinho(no_temp);
			}
			if( (i-1)>=0){
				no_temp = conjunto_nos.get(i-1).get(j);
				no_atual.setNorte(no_temp);
				no_atual.addVizinho(no_temp);
			}
			if( (i+1)<this.altura){
				no_temp = conjunto_nos.get(i+1).get(j);
				no_atual.setSul(no_temp);
				no_atual.addVizinho(no_temp);
			}

		}
	}		
		
	}//Fim método criaconjunto_nos	
	
	public No getNoOrigem (){
		
		return this.conjunto_nos.get(y_origem).get(x_origem);
	}
	
	public No getNoDestino (){
		
		return this.conjunto_nos.get(y_destino).get(x_destino);
	}

	public int getLargura() {
		return largura;
	}

	public int getAltura() {
		return altura;
	}
	
	public ArrayList<ArrayList <No>> getConjuntoNos (){
		
		return this.conjunto_nos;
	}
}

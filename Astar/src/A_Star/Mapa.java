package A_Star;

import java.awt.print.Printable;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Mapa {
	
	private int largura;
	private int altura;
	private ArrayList<ArrayList <No>> conjunto_nos;
	private int x_origem;
	private int y_origem; 
	private int x_destino;
	private int y_destino;
	private int [][] m_obstaculos;
	private int [][] m_mapa;
	private ArrayList<No> trajeto;
	
	//1 na matriz indica onde tem obstábulos
	public Mapa (int [][] m_obstaculos) throws Exception{
		this.largura = m_obstaculos[0].length;
		this.altura = m_obstaculos.length;
		this.m_obstaculos = m_obstaculos;
		this.x_origem = -1;
		this.x_destino = -1;
		this.criarconjunto_nos ();
		m_mapa = new int[this.conjunto_nos.size()][this.conjunto_nos.get(0).size()];
	
		this.criarMatriz ();
		
	}
	/////////
	//Criar estrutura de nós nos Arrays de acordo com a matriz de obstáculos
	private void criarconjunto_nos () throws Exception {
		
		conjunto_nos = new ArrayList<ArrayList<No>> ();
		for(int i =0; i < this.altura; i++){
			conjunto_nos.add(new ArrayList<No> ());
			for(int j = 0; j < this.largura; j++){
				No no = new No (j,i);
				if(m_obstaculos[i][j] == 1){
					no.setObstaculo();
				}
				else if (m_obstaculos[i][j] == 2) {
					if (this.x_origem == -1) {
						this.x_origem = j;
						this.y_origem = i;
					}
					else {
						throw new Exception("A matriz de entrada possui mais de uma origem!");
					}
				}
				else if (m_obstaculos[i][j] == 3) {
					if (this.x_destino == -1) {
						this.x_destino = j;
						this.y_destino = i;
					}
					else {
						throw new Exception("A matriz de entrada possui mais de um destino!");
					}
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
	
	public void criarMatriz (){
		
		int i = 0, j = 0;		
		for (ArrayList<No> b:this.getConjuntoNos() ){
			for (No c:b){
				m_mapa[i][j++] = c.getTipo_no();
			}
			i++;
			j = 0;
		}
		
	}
	
	public int[][] getMatriz (){
		
		return this.m_mapa;		
	}
	
	public void setElemento (int x, int y, int valor){
		
		this.m_mapa[y][x] = valor;
		
	}
	
	public static void imprimirMatriz (int m [][], int num_matriz){
	
	
		String texto = "";
		texto +="\t"+"Matriz: "+num_matriz+"\n";
		for(int y = 0, y_len = m[0].length; y<y_len;y++){
			texto+="\t";
			for(int x = 0,x_len = m.length; x<x_len;x++){
				texto+=m[y][x]+" ";			
			}
				texto+="\n";
				
		}
		texto+="\n";
		
		 try{
			 File arquivo = new File("Dados.txt");
			 if (!arquivo.exists()) {
			 	arquivo.createNewFile();
		 }
			 FileWriter fw = new FileWriter(arquivo.getAbsoluteFile(),true);
			 BufferedWriter bw = new BufferedWriter(fw);
			 bw.write(texto);
			 bw.close();
		 }
		 catch (IOException e) {
			 e.printStackTrace();
		 }

	}
	public ArrayList<No> getTrajeto() {
		return trajeto;
	}
	public void setTrajeto(ArrayList<No> trajeto) {
		this.trajeto = trajeto;
	}
	
}

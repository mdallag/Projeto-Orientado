package A_Star;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

public class A_Star {
	
	private IHeuristica heuristica;
	private Mapa mapa;
	private ArrayList<No> lista_aberta;
	private ArrayList<No> lista_fechada;
	private No no_origem;
	private No no_destino;
	private double distancia_nos_adjacentes = 10;
	private ArrayList<No> trajeto;
	private Integer qtdNosExplorados= new Integer (0);
	private int comprimentoCaminho;

	
	public static void main (String [] args){
		
		
		long tempoInicial = 0;  

		long tempoFinal = 0;  
	
		
		int [][] m_obstaculos1 = {
								{2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{1, 0, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
								};
	
		
		boolean modoAnalise = true;
		A_Star a_star = null;
		Integer qtdObstaculos = 0;
		int linhas = 20, colunas = 20;
		ArrayList<No> trajeto = null;
		
		if(modoAnalise == false){
			IHeuristica heuristica = new Manhattan();
			//IHeuristica heuristica = new Hipotenusa();
			//IHeuristica heuristica = new AtalhoDiagonal();
		//	m_obstaculos1 = new int [15][35];
			//m_obstaculos1 [0][0] = 2;
			//m_obstaculos1 [9][9] = 3;
		//	m_obstaculos5 [3][5] = 1;
			
			a_star = new A_Star(m_obstaculos1, heuristica);	
			
			trajeto = a_star.executar();
			InterfaceGrafica interfaceGrafica = new InterfaceGrafica(a_star.getMapa());
			if(trajeto == null){
				
				JOptionPane.showMessageDialog(interfaceGrafica, "Não existe caminho possível.");
				System.exit(1);
			}
		
		}else{
		
		//Modo análise
		//Gerar automaticamente matrizes; Rodar A* para cada matriz e com cada heuristica
		
		ArrayList<IHeuristica> array_heuristicas = new ArrayList<IHeuristica> ();
		//Adicionar heuristicas
		array_heuristicas.add(new AtalhoDiagonal());
		array_heuristicas.add(new DistanciaEuclidiana()); //Trocar por outra heuristica
		array_heuristicas.add(new Manhattan());
	
	
		
		Arquivo arquivo = new Arquivo("Arquivo.xls");
		
		//Escrever nome das Heristicas na planilha
		for(int i = 0; i<array_heuristicas.size(); i++){
			
			IHeuristica heuristica = array_heuristicas.get(i);
			//arquivo.setCelula(heuristica.getNomeHeuristica(), (i+2), 1);   //Linha 2 da planilha excel
		}
		
		int linha_planilha = 2, coluna_planilha = 2;
		boolean controle = true;

		int proibido[][] = {{0,0}, {1, 0}, {1, 1}, {1, 2}, {2, 2}, {2, 4}, {2, 5}, 
							{2, 6}, {3, 2}, {3, 4}, {3, 6}, {4, 2}, {4, 4}, {4, 6}, 
							{5, 2}, {5, 4}, {5, 6}, {6, 2}, {6, 3},	{6, 4}, {6, 6}, 
							{6, 10}, {6, 11}, {6, 12}, {7, 6}, {7, 7}, {7, 8}, 
							{7, 10}, {7, 12}, {8, 8}, {8, 10}, {8, 12}, {9, 8}, 
							{9, 10}, {9, 12}, {10, 8}, {10, 9}, {10, 10}, {10, 12}, 
							{11, 12}, {12, 12}, {12, 14}, {12, 15}, {12, 16}, 
							{13, 12}, {13, 14}, {13, 16}, {14, 12}, {14, 13}, 
							{14, 14}, {14, 16}, {15, 16}, {16, 16}, {17, 16}, {17,17}};
		int [][] m_obstaculos = new int [linhas][colunas];
		int incremento_obstaculos =3;
		int num_matriz = 0;
		while(controle){
			
			//m_obstaculos = A_Star.gerarMatrixAletoria(proibido, qtdObstaculos, linhas, colunas);
			
			Mapa.imprimirMatriz(m_obstaculos, num_matriz);
			num_matriz+=3;
			A_Star.inserirObstaculosAletorios(m_obstaculos, incremento_obstaculos);
			m_obstaculos[0][0] = 2;								//Seta origem
			m_obstaculos[linhas - 1][colunas - 1] = 3;			//Seta destino
			//m_obstaculos[linhas - 1][2] = 3;								//Seta origem
			//m_obstaculos[6][10] = 3;			//Seta destino
			arquivo.setCelula(qtdObstaculos, 1, coluna_planilha);
			qtdObstaculos+= incremento_obstaculos;
			
			for(IHeuristica heuristica: array_heuristicas){
				tempoInicial = System.currentTimeMillis();  
				a_star = new A_Star(m_obstaculos, heuristica);
				
				trajeto = a_star.executar();
				tempoFinal = System.currentTimeMillis();
				if(trajeto == null){
					controle = false;
					break;
				}
				arquivo.setCelula(a_star.getQtdNosExplorados(), linha_planilha, coluna_planilha);
				 
				Long tempo = tempoFinal-tempoInicial;
				arquivo.setCelula((tempo), (linha_planilha+3), coluna_planilha); 
				linha_planilha++;
			}
			coluna_planilha++;
			linha_planilha = 2;	
		
		}
		
		try {
			arquivo.Escrever();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao gravar o arquivo da planilha.");
			e.printStackTrace();
		}	
		
		}
		
	}
	
	private static int[][] gerarMatrixAletoria(int proibido[][], int qtdObstaculos, int linhas, int colunas) {
		int m[][] = new int [linhas][colunas];

		for (int[] linha : proibido) {
			m[linha[0]][linha[1]] = 4;
		}
		while (qtdObstaculos > 0) {
			int x = (int) (Math.random() * linhas);
			int y = (int) (Math.random() * colunas);
			if (m[x][y] != 4 && m[x][y] != 1) {
				m[x][y] = 1;
				qtdObstaculos--;
			}
		}
		for (int[] linha : proibido) {
			m[linha[0]][linha[1]] = 0;
		}
		return m;
	}
	
	private static void inserirObstaculosAletorios(int  m_obstaculos [][], int qtdObstaculos) {
		
		 int linhas = m_obstaculos.length;
		 int colunas = m_obstaculos[0].length;

		while (qtdObstaculos > 0) {
			int x = (int) (Math.random() * linhas);
			int y = (int) (Math.random() * colunas);
			if (m_obstaculos[x][y] != 1) {
				m_obstaculos[x][y] = 1;
				qtdObstaculos--;
			}
		}

	}

	public A_Star (int [][] m_obstaculos, IHeuristica heuristica){
		
		this.heuristica = heuristica;
		try {
			   this.mapa = new Mapa(m_obstaculos);
			}
			  catch(Exception $e) {
			   JOptionPane.showMessageDialog(null, $e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			   System.exit(1);
			}
		this.lista_aberta = new ArrayList<No> ();
		this.lista_fechada = new ArrayList<No> ();
		this.no_origem = mapa.getNoOrigem();
		this.no_destino = mapa.getNoDestino();
		
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
			//this.Ordenar();

			no_atual = lista_aberta.get(0);  //Pega o nó com menor valor de F da lista aberta
			
			if((no_atual.getX() == no_destino.getX())&&(no_atual.getY() == no_destino.getY())){                   //Então há caminho possível
				trajeto.add(no_destino);
				no_atual = no_destino.getPai();
				
				while(no_atual != no_origem){
					trajeto.add(no_atual);
					no_atual = no_atual.getPai();
				}
				trajeto.add(no_origem);	
				
				mapa.setTrajeto(trajeto);
				
				this.qtdNosExplorados = this.lista_fechada.size();
				this.comprimentoCaminho = trajeto.size();
				
				return trajeto;
			}
			
			lista_aberta.remove(no_atual);
			lista_fechada.add(no_atual);
			if((no_atual.getX() != no_origem.getX())||(no_atual.getY() != no_origem.getY()))   //Para não pintar a origem
			mapa.setElemento(no_atual.getX(), no_atual.getY(), 6);
			
			no_atual.setEstado(2);            //2 significa que o nó está na lista fechada

			
			for(No no_vizinho:no_atual.getVizinhos()){
				
				if(no_vizinho.getEstado() == 2){//Se está na lista fechada ou é obstáculos
					continue;
				}
				
				if(no_vizinho.isObstaculo()){//Se está na lista fechada ou é obstáculos
			
					continue;
				}				
				
				g_tentativa = no_atual.getG() + distancia_nos_adjacentes;
			
				if( (no_vizinho.getEstado() != 1)){              //Não está na lista aberta
					this.lista_aberta.add(no_vizinho);
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
				
				System.out.println("x "+(no_atual.getX()+1)+"y "+(no_atual.getY()+1)+"F "+no_atual.getF());
				
			}
			
		}//Fim while
		
		return null; //Não há caminho possível
		
	}	
	
	private void Ordenar( ) {

		int tamanho = this.lista_aberta.size();
		No no_a = null;
		No no_b = null;
        for (int fixo = 0; fixo < tamanho; fixo++){
 
            for(int i = fixo + 1; i< tamanho; i++){
            	
                if(lista_aberta.get(fixo).getF() >= lista_aberta.get(i).getF() ){
                	no_b = lista_aberta.remove(i);
                	no_a = lista_aberta.remove(fixo);
                    lista_aberta.add(fixo, no_b);
                    lista_aberta.add(i, no_a );
              
                }                            
            }
        }
		
		
	}

	public double valorH (No atual, No destino){
		
		return heuristica.valorHeuristica(atual, destino);
		
	}
	
	public Mapa getMapa (){
		
		return this.mapa;
	}
	
	public double getQtdNosExplorados (){
		
		return this.qtdNosExplorados;
	}

}

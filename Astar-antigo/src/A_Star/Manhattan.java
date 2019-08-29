package A_Star;

public class Manhattan implements Heuristica{

	public double valorHeuristica(No atual, No destino) {
	
		double valor = 0;
		
		valor = (Math.abs(atual.getX()-destino.getX())+Math.abs(atual.getY()-destino.getY()))*10;
		
		return valor;
	}

}

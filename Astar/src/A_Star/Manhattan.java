package A_Star;

public class Manhattan implements IHeuristica{

	String nomeHeuristica = "Manhattan";
	
	public double valorHeuristica(No atual, No destino) {
		double dx = Math.abs(atual.getX()- destino.getX() );
		double dy = Math.abs(atual.getY()- destino.getY() );
	
		return  (dx + dy)*10;
	
	}

	
	public String getNomeHeuristica() {
		
		return this.nomeHeuristica;
	}

}

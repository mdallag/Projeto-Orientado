package A_Star;

public class AtalhoDiagonal implements IHeuristica{

	String nomeHeuristica = "Atalho Diagonal";
	
	public double valorHeuristica(No atual, No destino) {

		double x;
		double y;
		double H;

		x = Math.abs(atual.getX() - destino.getX());
		y = Math.abs(atual.getY() - destino.getY());

		if (x > y) {
			H = 14*y + 10*(x - y);
		}

		else {
			H = 14*x + 10*(y - x);
		}

		return H;
	}

	public String getNomeHeuristica() {

		return this.nomeHeuristica;
	}
}


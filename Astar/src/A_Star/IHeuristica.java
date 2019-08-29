package A_Star;

public interface IHeuristica {
	
	public double valorHeuristica (No atual, No destino); 
	
	public String getNomeHeuristica ();

}

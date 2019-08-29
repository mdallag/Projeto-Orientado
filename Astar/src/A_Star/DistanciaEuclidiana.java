package A_Star;

public class DistanciaEuclidiana implements IHeuristica{

 String nomeHeuristica = "Distância Euclidiana";
  
 public double valorHeuristica(No atual, No destino) {

 	double dx;
 	double dy;

 	dx = Math.abs(atual.getX() - atual.getX());
  dy = Math.abs(atual.getY() - destino.getY());
   
 	return Math.sqrt(dx * dx + dy * dy)*10;
 }

 public String getNomeHeuristica() {

 	return this.nomeHeuristica;
 }
}
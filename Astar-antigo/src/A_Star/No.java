package A_Star;

import java.util.ArrayList;

public class No implements Comparable<No> {
	
	private int x, y;
	private boolean obstaculo;
	private No norte;
	private No sul;
	private No leste;
	private No oeste;
	private No pai;
	private double h, g, f;
	private ArrayList<No> vizinhos;
	private int estado;
	private int tipo_no;
	
	public No (int x, int y){
		this.x = x;
		this.y = y;
		this.vizinhos = new ArrayList<No> ();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isObstaculo() {
		return obstaculo;
	}

	public void setObstaculo() {
		this.obstaculo = true;
	}

	public No getNorte() {
		return norte;
	}

	public void setNorte(No norte) {
		this.norte = norte;
	}

	public No getSul() {
		return sul;
	}

	public void setSul(No sul) {
		this.sul = sul;
	}

	public No getLeste() {
		return leste;
	}

	public void setLeste(No leste) {
		this.leste = leste;
	}

	public No getOeste() {
		return oeste;
	}

	public void setOeste(No oeste) {
		this.oeste = oeste;
	}

	public No getPai() {
		return pai;
	}

	public void setPai(No pai) {
		this.pai = pai;
	}
	
	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	public double getG() {
		return g;
	}

	public void setG(double g) {
		this.g = g;
	}

	public double getF() {
		return f;
	}

	public void setF(double f) {
		this.f = f;
	}

	public void addVizinho (No vizinho){
		this.vizinhos.add(vizinho);
	}
	
	public ArrayList<No> getVizinhos (){
		
		return this.vizinhos;
	}

	public int compareTo(No o) {
		
		if(this.f > o.getF())
			return 1;
		if(this.f < o.getF())
			return -1;
		return 0;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getTipo_no() {
		return tipo_no;
	}

	public void setTipo_no(int tipo_no) {
		this.tipo_no = tipo_no;
	}
	
	 public String toString() {
		 
		 return "("+x+","+y+")";
	 }


}

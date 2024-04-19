package org.example.tp;


import java.util.Map;

public  abstract class Mesa   {
	
	protected int numero;
	protected Votante presidente;
	protected int cupo;
	protected static int contador  =0;
	protected Map<Integer,Votante> votantes;  // Key= DNI, Value=Votante
	protected int horario;

	Mesa(Votante presidente){
		this.presidente=presidente;
		contador++;
		this.numero= contador;	
	}
	
	public void agregarTurnoPresidente(Votante v) {
		Turno<Integer, Integer> turno = new Turno<Integer, Integer>(getNumero(), 8);
		//Le paso el turno correspondiente al presidente-.
		v.setTurno(turno);	//Le asigno el turno al presidente.
		
		agregarVotante(v.getDNI(), v);
	}
	
	
	
	public abstract Turno<Integer, Integer> agregarTurno(Integer dni);
	
	public abstract boolean cumpleCondicionDeLaMesa (Votante v);
		
	public int cantVotantes() {
		return votantes.size();
	}
	
	public void agregarVotante(int dni,Votante votante) {
		this.votantes.put(dni, votante);
	}
	
	
	public Map<Integer,  Votante> mostrarListaVotantes() {
		return this.votantes;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getPresidente() {
		return presidente.getDNI();
	}

	public void setPresidente(Votante presidente) {
		this.presidente = presidente;
	}

	public int getCupo() {
		return cupo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}


	public int getHorario() {
		return horario;
	}

	public void setHorario(int horario) {
		this.horario = horario;
	}
	
	public String toString() {
		return  "\n Numero de mesa:" + this.numero  + "\t presidente: " + this.presidente.getNombre()  ;
	}
	

	
}

package org.example.tp;

public class Turno<X, Y> {
	final Integer numMesa;
	final Integer horario;
	
	public Turno(Integer numMesa, Integer horario) {
		this.numMesa = numMesa;
		this.horario = horario;
	}
	
	

	public Integer getNumMesa() {
		return this.numMesa;
	}
	
	public Integer getHorario() {
		return this.horario;
	}

	
	public String toString() {
		String s= "\n Nro de mesa:" + this.numMesa + "\t Horario del turno:" + this.horario + "hs";
		StringBuilder sb = new StringBuilder (s);
		return sb.toString();
	}
	
	
}

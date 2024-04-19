package org.example.tp;

import java.util.HashMap;

public class MesaTrabajador extends Mesa {
	
	MesaTrabajador(Votante presidente){
		super(presidente);
		super.cupo=0;
		super.votantes= new HashMap<Integer, Votante>();
	}

	public   Turno<Integer, Integer> agregarTurno(Integer dni)  {
			
		int horario = (int)(Math. random()*(12-8+1)+8);
		
		Turno<Integer,Integer> turno= new Turno<Integer,Integer> (this.numero,horario);
		//this.votantes.put(dni, horario); //Agrego al votante a la lista de la mesa
		
		return turno;
	}
	public    boolean cumpleCondicionDeLaMesa (Votante v) {
		return v.getTrabaja();
	}
	
	public void agregarVotante(int dni,Votante votante) {
		super.votantes.put(dni, votante);
	}
}

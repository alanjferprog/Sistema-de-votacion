package org.example.tp;

import java.util.HashMap;

public class MesaEnfPreex  extends Mesa{
	
	MesaEnfPreex(Votante presidente){
		super(presidente);
		super.cupo=199;
		super.votantes= new HashMap<Integer, Votante>();
	}
	
	
	public Turno<Integer, Integer> agregarTurno(Integer dni)  {
		int horario = 0;
		
		
		if (cupo > 180)
			horario=8;
		else if (cupo > 160 && cupo <= 180)
			horario=9;
		else if (cupo > 140 && cupo <= 160)
			horario = 10;
		else if (cupo > 120 && cupo <= 140)
			horario = 11;
		else if (cupo > 100 && cupo <= 120)
			horario = 12;
		else if (cupo > 80  && cupo <= 100)
			horario = 13;
		else if (cupo > 60  && cupo <= 80)
			horario = 14;
		else if (cupo > 40  && cupo <= 60)
			horario = 15;
		else if (cupo > 20  && cupo <= 40)
			horario = 16;
		else if (cupo > 0   && cupo <= 20)
			horario = 17;
		else
			horario = -1;
		
		Turno<Integer,Integer> turno= new Turno<Integer, Integer> (this.numero,horario);
	
		if (turno.getHorario() <= 0)
			return null;
		
		//this.votantes.put(dni, ); //Agrego al votante a la lista de la mesa
		this.cupo--;
		return turno;
	}
	
	public void agregarVotante(int dni,Votante votante) {
		super.votantes.put(dni, votante);
	}

	public  boolean cumpleCondicionDeLaMesa (Votante v) {
		return !v.getTrabaja() && v.getEnfPrevia();
	}
}

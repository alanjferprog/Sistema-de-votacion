package org.example.tp;

import java.util.HashMap;

public class MesaGeneral extends Mesa{
	
	MesaGeneral(Votante presidente){
		super(presidente);
		super.cupo=299;
		super.votantes= new HashMap<Integer, Votante>();
	}


	public Turno<Integer, Integer> agregarTurno(Integer dni)  {
		
		int horario = 0;
				
		if (cupo > 270)
			horario=8;
		else if (cupo > 240 && cupo <= 270)
			horario=9;
		else if (cupo > 210 && cupo <= 240)
			horario = 10;
		else if (cupo > 180 && cupo <= 210)
			horario = 11;
		else if (cupo > 150 && cupo <= 180)
			horario = 12;
		else if (cupo > 120  && cupo <= 150)
			horario = 13;
		else if (cupo > 90  && cupo <= 120)
			horario = 14;
		else if (cupo > 60  && cupo <= 90)
			horario = 15;
		else if (cupo > 30  && cupo <= 60)
			horario = 16;
		else if (cupo > 0   && cupo <= 30)
			horario = 17;
		
		Turno<Integer,Integer> turno= new Turno<Integer, Integer> (this.numero,horario);
		//this.votantes.put(dni, horario); //Agrego al votante a la lista de la mesa
		
		this.cupo--;
		return turno;
	}

	public    boolean cumpleCondicionDeLaMesa (Votante v) {
		return v.getEnfPrevia()==false && v.getTrabaja()==false;
	}
	
	public void agregarVotante(int dni,Votante votante) {
		super.votantes.put(dni, votante);
	}
}

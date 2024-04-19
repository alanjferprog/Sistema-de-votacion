package org.example.tp;

import java.util.HashMap;

public class MesaMayor65 extends Mesa {
	
	MesaMayor65(Votante presidente){
		super(presidente);
		super.cupo=99;
		super.votantes= new HashMap<Integer, Votante>();
		
	}


	public  Turno<Integer, Integer> agregarTurno(Integer dni)  {
		
		int horario = 0;
		
		if (cupo > 90)
			horario=8;
		else if (cupo > 80 && cupo <= 90)
			horario=9;
		else if (cupo > 70 && cupo <= 80)
			horario = 10;
		else if (cupo > 60 && cupo <= 70)
			horario = 11;
		else if (cupo > 50 && cupo <= 60)
			horario = 12;
		else if (cupo > 40 && cupo <= 50)
			horario = 13;
		else if (cupo > 30 && cupo <= 40)
			horario = 14;
		else if (cupo > 20 && cupo <= 30)
			horario = 15;
		else if (cupo > 10 && cupo <= 20)
			horario = 16;
		else if (cupo > 0  && cupo <= 10)
			horario = 17;
		
		Turno <Integer,Integer> turno = new Turno<Integer, Integer> (this.numero, horario);
		//this.votantes.put(dni, horario); //Agrego al votante a la lista de la mesa
		
		this.cupo--;
		return turno;
}


	public    boolean cumpleCondicionDeLaMesa (Votante v) {
		return v.getEdad() > 65 && !v.getTrabaja() && !v.getEnfPrevia();
	}
	
	
	public void agregarVotante(int dni,Votante votante) {
		super.votantes.put(dni, votante);
	}
}

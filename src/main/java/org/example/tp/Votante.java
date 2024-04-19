package org.example.tp;

public class Votante {
	
	private String nombre;
	private int DNI;
	private int edad;
	private boolean enfPrevia;
	private boolean trabaja;
	private boolean voto;
	private Turno<Integer,Integer> turno; //Nro de mesa - Hora

	
	Votante(int dni,String nombre,int edad,boolean enfPrevia,boolean trabaja){
		this.DNI=dni;
		this.nombre=nombre;
		this.edad=edad;
		this.enfPrevia=enfPrevia;
		this.trabaja=trabaja;
		this.voto = false;
		this.turno = null;
		
	}
	
	public int obtenerHorarioTurno() {
		return this.turno.getHorario();
	}
	
	public int obtenerMesaTurno() {
		return this.turno.getHorario();
	}
	
	public Turno<Integer,Integer> getTurno() {
		return this.turno;
	}
	
	
	//Devuelve true si tiene un turno asignado, false si no
	public boolean tieneTurno() {
		return this.turno != null;
	}
	
	
	//Lo que estamos haciendo no es crear el turno sino guardandolo. Algo as� como un papelito con la hora y la mesa
	public void setTurno(Turno<Integer,Integer> turno) { 
		this.turno = turno;
	}
	
	
	
	public boolean votar() {
		
		if(this.getVoto() == true)
			return false; //Si no voto  setea el voto en true y retorna true , en caso de ya haber votado devuelve false.
		else {
			this.setVoto(true);
			return true;
		}
	}
	
	
	
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDNI() {
		return DNI;
	}

	public void setDNI(int dNI) {
		DNI = dNI;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public boolean getEnfPrevia() {
		return enfPrevia;
	}

	public void setEnfPrevia(boolean enfPrevia) {
		this.enfPrevia = enfPrevia;
	}

	public boolean getTrabaja() {
		return trabaja;
	}

	public void setTrabaja(boolean trabaja) {
		this.trabaja = trabaja;
	}



	public boolean getVoto() {
		return voto;
	}

	public void setVoto(boolean voto) {
		this.voto = voto;
	}

	public String toString() {
		  String s= "\n Nombre: "+ this.nombre + "\t DNI:"  + this.DNI;
		  StringBuilder sb = new StringBuilder (s);
		  return sb.toString();
		 }


	
	
}



package org.example.tp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaDeTurnos {

	private String nombre;
	private Map <Integer , Mesa > mesas= new HashMap <Integer,Mesa>();           //KEY nro de mesa
	private Map <Integer , Votante  > personas= new HashMap <Integer , Votante>();  //KEY dni
	
	//============================================================================
	
	public SistemaDeTurnos (String nombreSistema) throws RuntimeException   {
		if(nombreSistema == null)
			throw new RuntimeException ("Ingrese un nombre valido.");
	
		this.setNombre(nombreSistema);
	}
	
	//============================================================================
	
	
	public void registrarVotante(int dni, String nombre, int edad, boolean enfPrevia, boolean trabaja
			) throws RuntimeException {
		
		if(personas.containsKey(dni) == true) {
			throw new RuntimeException ("La persona ya est� registrada.");
		}
		
		if(edad<16)
			throw new RuntimeException("La persona es menor de edad");
		
		else {
			
		Votante votante= new Votante(dni,nombre,edad,enfPrevia,trabaja);  //Creo un votante con los datos recibidos
		
		personas.put(dni,votante);

		}
	}

	
	//============================================================================
	
	
	public int agregarMesa(String tipoMesa, int dni) throws RuntimeException {

		// --- Asigna el turno del presidente dentro del mismo metodo.
		
		if(this.personas.containsKey(dni) == false || personas.get(dni).tieneTurno())
			throw new RuntimeException("La persona no es v�lida para ser presidente.");
		
		if(tipoMesa != "Enf_Preex" && tipoMesa!="Mayor65" && tipoMesa!="General" && tipoMesa!="Trabajador")
			throw new RuntimeException("El tipo de mesa es erronea");
		
		
		else {
			
			//Inicializo variables que voy a usar adelante
			Mesa mesa = null;	
			Votante presi = personas.get(dni);
			int numMesa=0;
			
			
			if (tipoMesa.equals("Trabajador")) 
				mesa = new MesaTrabajador(presi);	//Le paso el presidente.
				
			else if(tipoMesa.equals("Enf_Preex")) 
				mesa = new MesaEnfPreex(presi);
			
			else if(tipoMesa.equals("General")) 
				mesa = new MesaGeneral(presi);
			
			else if(tipoMesa.equals("Mayor65")) 
				mesa = new MesaMayor65(presi);
				
			numMesa= mesa.getNumero();
			mesas.put(numMesa, mesa);	//Agrego la mesa en el diccionario del sistema de turnos.
					
			
			
			mesa.agregarTurnoPresidente(presi);
		
			return numMesa;
			}		
	}

	
	//============================================================================
	
	
	public Turno<Integer, Integer> asignarTurno(int dni) throws RuntimeException{
		Votante votante = null;
		
		if(!this.personas.containsKey(dni))
			throw new RuntimeException("La persona no esta registrada");
		else
			votante = personas.get(dni);
		
		Turno <Integer,Integer> turno = null;
		
		if(votante.tieneTurno())
			//Devuelve la tupla a la cual accede con el dni.
			return votante.getTurno();
		
		else {
			
			for( Mesa mesa : mesas.values()) {
				if(mesa.cumpleCondicionDeLaMesa(votante)) {
					//Creo el turno
			    	turno = mesa.agregarTurno(dni); // Mesa crea el turno de la persona 
			    	if(turno != null) {
			    		votante.setTurno(turno); //  (El votante guarda su turno)
			    		mesa.agregarVotante(dni, votante); // Mesa agrega a la persona a la su lista de votantes   	
			    	}
			    }
			}
			return turno;
			
		}		
	}
	
	
	//============================================================================
	
	
	public int asignarTurnos() throws RuntimeException {
		
		int contador = 0;
		
		for(int dni : personas.keySet()) {
			if(!personas.get(dni).tieneTurno()) { // si la persona no tiene turno, intento asignarlo
				asignarTurno(dni);
				if(personas.get(dni).tieneTurno()) //En caso de haber podido se lo asigno
					contador++;
			}

		}
		return contador;
	}

	//============================================================================
	
	public boolean votar(Integer dni) throws RuntimeException {
		
		//Puede pasar que la persona o bien no tenga turno o bien no esta registrada --> no puede votar
		if(!personas.get(dni).tieneTurno() || !personas.containsKey(dni) )
			throw new RuntimeException("La persona no esta habilitada a votar");
		else {
		
			return personas.get(dni).votar();  //Manda a la persona a votar
		}
	}
	//============================================================================
	
	public int votantesConTurno(String tipoDeMesa) throws RuntimeException {
		
		if(tipoDeMesa != "Enf_Preex" && tipoDeMesa!="Mayor65" && tipoDeMesa!="General" && tipoDeMesa!="Trabajador")
			throw new RuntimeException("El tipo de mesa es erronea");
		
		int cont=0;

		for (Mesa mesa : mesas.values()) {
		    if(mesa.getClass().toString().equals(tipoDeMesa)) {
		    	cont= mesa.cantVotantes();
		    	}
		    }
		return cont;
		
	}

	//============================================================================
	
	public Turno<Integer,Integer> consultaTurno(int dni) throws RuntimeException {
		
		if(!personas.containsKey(dni))
			throw new RuntimeException ("Persona no registrada");
		
		else {	
			return personas.get(dni).getTurno();
			
		}		
	}
	
	//============================================================================
	
	//Dado un numero de mesa devuelve un map cuya key es la franja horaria y el valor es una lista con los votantes asignados a esa hora.
	public Map <Integer,List <Integer>> asignadosAMesa (Integer numMesa) throws RuntimeException{
		
	Map <Integer,List <Integer>> asignados = new HashMap <Integer,List <Integer>>();
	List <Integer> listaVotantes;
	
		
	if(mesas.containsKey(numMesa)) {   // me fijo si el numMesa esta dentro de mesas
	
			for(int i=8; i<=17; i++) {      // For que va desde 8 hasta 17 (Las franjas horarias)
				
				listaVotantes = new ArrayList<Integer>();
				for(Integer dni : mesas.get(numMesa).mostrarListaVotantes().keySet()) {  //obtengo los dni de la lista votantes de la mesa 
					if(i == personas.get(dni).obtenerHorarioTurno()){		// comparo el horario del dni con 'i'                     
						listaVotantes.add(dni);  
					}
				}	
				if (listaVotantes.size() != 0 )
					asignados.put(i, listaVotantes);    //Una vez termino un ciclo guardo la lista con los dni en el map
			}
			
		return asignados;
	}
	else
		throw new RuntimeException("La mesa no esta registrada");	
	}	
	
	//============================================================================
	
	//Mapa con la cantidad de votantes sin turno para cada mesa
	//Devuelve lista de tuplas con tipo de mesa y votantes sin turno que esperan ser asignados.
	public List<Tupla<String,Integer>> sinTurnoSegunTipoMesa(){
		
		List<Tupla<String,Integer>> asignados = new  ArrayList <Tupla<String,Integer>>();
		
		int contador1=0;
		int contador2=0;
		int contador3=0;
		int contador4=0;
		
		
		for (Votante votante : personas.values()) {			
			if(!votante.tieneTurno()  && votante.getTrabaja()) 
				contador1++;		
			else if(!votante.tieneTurno()  && votante.getEnfPrevia()) 
				contador2++;
			else if(!votante.tieneTurno()  && votante.getEdad() > 65) 
				contador3++;
			else if(!votante.tieneTurno()) 
				contador4++;
		}
		
		Tupla<String, Integer> mesaTrabajador    =new Tupla<String,Integer> ("Trabajador",  contador1);
		Tupla<String, Integer> mesaEnfPreex   =new Tupla<String,Integer> ("EnfPreex",  contador2);
		Tupla<String, Integer> mesaMayor65   =new Tupla<String,Integer> ("Mayor65",  contador3);
		Tupla<String, Integer> mesaGeneral =new Tupla<String,Integer> ("General",  contador4);
		
		if (contador1 > 0 )
			asignados.add(mesaTrabajador);					
		if (contador2 > 0 )
			asignados.add(mesaEnfPreex);
		if (contador3 > 0 )	
			asignados.add(mesaMayor65);
		if (contador4 > 0 )
			asignados.add(mesaGeneral);
		
		return asignados;				
	}





@Override
public String toString() {
	 
	 StringBuilder pConTurno= new StringBuilder();
	 StringBuilder pSinTurno= new StringBuilder();
	 for(int dni : personas.keySet()) {
		  if(personas.get(dni).getTurno()!=null) 
			  pConTurno.append(personas.get(dni).toString()).append(personas.get(dni).getTurno().toString()).append(" Voto: " + personas.get(dni).getVoto()  + "\n"); 
		  else pSinTurno.append(personas.get(dni).toString() + "\n");
	 }
	 
	 String mesasHabilitadas= "\n- Mesas habilitadas:  " +  mesas.values().toString() +" \n";
	 String personasRegistradas="\n- Personas registradas:" + personas.values().toString() +" \n";
	 String personasSinturnos= "\n- Personas sin turno:"  +  pSinTurno.toString().toString() +" \n"; 
	 String personasConTurnos= "\n- Personas con turno: " + pConTurno.toString() +" \n";
	 
	 StringBuilder sb = new StringBuilder (mesasHabilitadas).append(personasRegistradas).append(personasSinturnos).append(personasConTurnos);
	 
	 return "======================================Sistema de Turnos para Votaci�n - UNGS======================================\n" + sb;
	}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}


	
}
	


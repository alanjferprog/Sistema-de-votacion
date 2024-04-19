package org.example.tp;

public class Tupla<S1, S2> {
	S1 x;
	S2 y;
	public Tupla(S1 x,S2 y) {
		this.x=x;
		this.y=y;
	}


	public S1 getX() {
		return x;
	}

	public void setX(S1 x) {
		this.x = x;
	}

	public S2 getY() {
		return y;
	}

	public void setY(S2 y) {
		this.y = y;
	}

	
}

package logica;

public class hormiga extends Thread{
	int id;
	double vida;
	double fuerza;
	String tipo;

	public void run(){
		// interaccion de lucha entre una hormiga con las demas mientras haya hormigas en la base de datos o hasta que la hormiga mue
	}


	public hormiga(String t,double v, double f){
		this.tipo = t;
		this.vida = v;
		this.fuerza = f;
	}


	public double getVida() {
		return vida;
	}

	public void setVida(double vida) {
		this.vida = vida;
	}

	public double getFuerza() {
		return fuerza;
	}

	public long getId(){
		return this.id;
	}

	public void setFuerza(double fuerza) {
		this.fuerza = fuerza;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setId(int id){
		this.id = id;
	}



}

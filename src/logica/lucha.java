package logica;
import logica.hormiga;

import java.sql.Connection;
import java.sql.Statement;
import bDatos.ConectarDB;
import bDatos.MysqlDataSpImpl;

public class lucha {
	
	String tipo="";
    double vida=0;
    double fuerza=0;
    String tipo2="";
    double vida2=0;
    double fuerza2=0;
    ConectarDB con = new ConectarDB();
    Connection c = con.conectar();
    MysqlDataSpImpl my = new MysqlDataSpImpl(c);
    int hor1 = 0;
    int hor2 = 0;
    
	public void lucha(int hormiga1, int hormiga2){
		hor1 = hormiga1;
		hor2 = hormiga2;
		try {
			tipo = my.getHormiga("hormigas","tipo",hormiga1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			tipo2 = my.getHormiga("hormigas", "tipo", hormiga2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			vida = my.getHormigaD("hormigas", "vida", hormiga1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			vida2 = my.getHormigaD("hormigas", "vida", hormiga2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			fuerza = my.getHormigaD("hormigas", "fuerza", hormiga1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			fuerza2 = my.getHormigaD("hormigas", "fuerza", hormiga2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        hormiga h_1 = new hormiga(tipo,vida,fuerza);
        hormiga h_2 = new hormiga(tipo2,vida2,fuerza2);
        
        System.out.println("Van a luchar las hormigas....");
        System.out.println("Con Id "+hormiga1+" a nuestra derecha con las siguientes características:");
        System.out.println(h_1.getTipo()+" "+h_1.getVida()+" "+h_1.getFuerza());
        System.out.println("Y a nuestra izquierda on Id "+hormiga2+" con las siguientes características:");
        System.out.println(h_2.getTipo()+" "+h_2.getVida()+" "+h_2.getFuerza());
        
        System.out.println("Comienza el combate");
        round(h_1,h_2);
	}
	
	public void round(hormiga h1, hormiga h2){
		double v1= h1.getVida();
		double v2 = h2.getVida();
		double f1 = h1.getFuerza();
		double f2 = h2.getFuerza();
		int ronda = 0;
		boolean resul = false;
		while(resul != true){
			ronda++;
			System.out.println("Round "+ronda);
			resul = combate(v1,v2,f1,f2);
			v1 = vida;
			v2 = vida2;
			f1 = fuerza;
			f2 = fuerza2;
				
			}
		if(v1>v2){
			try {
				my.updateOneD(hor1, "hormigas", "vida", v1);
				my.removeOne(hor2, "hormigas");
				System.out.println("Gana la hormiga con Id "+ hor1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
				try {
					my.updateOneD(hor2, "hormigas", "vida", v2);
					my.removeOne(hor1, "hormigas");
					System.out.println("Gana la hormiga con Id "+ hor2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
		
		
	
		
	
	public boolean  combate(double v1,double v2,double f1,double f2){
		
		
		for(int i = 0; i<3 ;i++){
			int t1=turno();
			int d1=turno();
			if(t1 == 1){
				System.out.println("Ataca la hormiga con id "+hor1);
				if(d1 == 2){
					System.out.println("Pero la hormiga con id "+hor2+" esquiva el ataque");
				}else{
					v2=v2-f1;
					System.out.println("La hormiga con id "+hor1+" golpea a la hormiga "+hor2+" dejandola con una vida de "+v2 );
					if(v2<1){
						System.out.println("La hormiga con id "+hor2+ " queda eliminada");
						vida = v1;
						fuerza = f1;
						vida2 = v2;
						fuerza2 = f2;
						return true;				
					}			
				}			
			}else{
				System.out.println("Ataca la hormiga con id "+hor2);
				if(d1 == 1){
					System.out.println("Pero la hormiga con id "+hor1+" esquiva el ataque");
				}else{
					v1=v1-f2;
					System.out.println("La hormiga con id "+hor2+" golpea a la hormiga "+hor1+" dejandola con una vida de "+v1 );
					if(v1<1){
						System.out.println("La hormiga con id "+hor1+ " queda eliminada");
						vida = v1;
						fuerza = f1;
						vida2 = v2;
						fuerza2 = f2;
						return true;
					}				
				}
			}
		}
		vida = v1;
		fuerza = f1;
		vida2 = v2;
		fuerza2 = f2;
		return false;
	
		
	}
	
	public int turno(){
		return (int)(Math.random()*2)+1;
	}

}

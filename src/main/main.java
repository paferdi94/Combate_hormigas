package main;
import java.util.Scanner; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.PreparedStatement;

import logica.hormiga;
import bDatos.ConectarDB;
import bDatos.MysqlDataSpImpl;


public class main {

	//Variables
	
	String tiposH []  = {"Roja","Negra","Verde","Morada"};
	
	
	public static void main (String [ ] args) throws SQLException {
		
		
		//Variables
        ConectarDB con = new ConectarDB();
        main principal = new main();
        String cadena = "";
        String tipo="";
        double vida=0;
        double fuerza=0;
        String tipo2="";
        double vida2=0;
        double fuerza2=0;
        hormiga ho;
        hormiga h_1 = new hormiga(tipo,vida,fuerza);
        hormiga h_2 = new hormiga(tipo2,vida2,fuerza2);
        int cont = 0;// variable para contar cuantas hormigas hay dentro de la base de datos;
 		

        //Comienza la ejecucion del programa

        System.out.println("INICIO DE EJECUCIÓN.");
        Connection c = con.conectar();
        Statement st = con.conexion();
        MysqlDataSpImpl my = new MysqlDataSpImpl(c);
       
        //Si la conexion es correta podemos usarla para hacer consultas sobre la BD
        //CONSULTAS AQUI
         try {
			my.removeAll("hormigas");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
         System.out.println ("Por favor introduzca cuantas hormigas van a participar:");
         String entradaTeclado = "";
         Scanner  entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
         entradaTeclado = entradaEscaner.nextLine (); 
         int num =Integer.parseInt(entradaTeclado);
         entradaEscaner.close();
         
 		for(int i = 0; i< num; i++){
 			
         	ho = principal.creador();
         	try {
				my.insertAll("hormigas", ho.getTipo(), ho.getVida(), ho.getFuerza());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

         }
       
        // Se sacan los datos de la tabla personal
        cadena = "SELECT * FROM hormigas;";
        ResultSet rs = con.consultaQuery(st, cadena);
        if (rs != null) {
            System.out.println("El listado de hormiga es el siguiente:");
 
            while (rs.next()) {
            	cont++;
                System.out.println("  ID: " + rs.getObject("id"));
                System.out.println("  Tipo de Hormiga: "
                		+ rs.getObject("tipo") + " "
                        + rs.getObject("vida") + " "
                        + rs.getObject("fuerza"));
                System.out.println("- ");
            }
            con.cerrar(rs);
        }
        // obtener un id aleatorio entre todas las hormigas-
        int h1 = principal.aleatorioHormiga(cont);
        int h2 = principal.aleatorioHormiga(cont);
         
        try {
			tipo = my.getHormiga("hormigas","tipo",h1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			tipo2 = my.getHormiga("hormigas", "tipo", h2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			vida = my.getHormigaD("hormigas", "vida", h1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			vida2 = my.getHormigaD("hormigas", "vida", h2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			fuerza = my.getHormigaD("hormigas", "fuerza", h1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			fuerza2 = my.getHormigaD("hormigas", "fuerza", h2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        con.cerrar(st);
        System.out.println("FIN DE EJECUCIÓN.");
    }// acaba el main
	
	// construcción de hormiga
	public hormiga creador(){
		hormiga h = new hormiga(tiposH[(int) aleatorio(1)],aleatorio(2),aleatorio(3));
		return h;
	}
	
	//asignacionde vida fuerza y tipo aleatorio
	public double aleatorio(int n){
		double vi;
		double fu;
		double ti;
		if(n == 1){ ti = (int) (Math.random()*3);return ti;}
		if(n == 2){ vi = 100; vi += (int) (Math.random()*500);return vi;
		}else fu = 10; fu +=  (int) (Math.random()*40);return fu;
	}
	public int aleatorioHormiga(int cont){
		return (int)(Math.random()*cont)+1;	
	}
	
	// sistema de combate
	public int combate(hormiga h1,hormiga h2){
		boolean vivo = true;
		double vidah1 = h1.getVida();
		double vidah2 = h2.getVida();
		
		while(vivo){
			
			int ale = (int) ((Math.random()*2)+1);
			
			System.out.println("Golpea hormiga "+ale);
			
			if(ale == 1){
				vidah2 = vidah2- h1.getFuerza();
				System.out.println("La hormiga 1 golpea a la hormiga 2 produciendo un daño de "+h1.getFuerza()+" dejandola con "+ vidah2+"  de vida");
			}else if(ale ==2){
				vidah1 = vidah1- h2.getFuerza();
				System.out.println("La hormiga 2 golpea a la hormiga 1 produciendo un daño de "+h2.getFuerza()+" dejandola con "+ vidah1 +"  de vida");
			}
			
			if( vidah1 == 0 || vidah2 == 0){
				vivo = false;
			}
		}
		if(vidah1>vidah2){
		System.out.println("Ganadora la hormiga 1");
		return 1;
		} System.out.println("Ganadora la hormiga 2");
		return 2;
	}
	
			
}



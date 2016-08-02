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
import logica.lucha;


public class main {

	//Variables
	
	String tiposH []  = {"Roja","Negra","Verde","Morada"};
	
	
	public static void main (String [ ] args) throws SQLException {
		
		
		//Variables
        ConectarDB con = new ConectarDB();
        main principal = new main();
        lucha lu = new lucha();
        String cadena = "";
        hormiga ho;
        
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
        
        int h1 = principal.aleatorioHormiga(cont,0);
        int h2 = principal.aleatorioHormiga(cont,h1);
        lu.lucha(h1, h2);
        rs = con.consultaQuery(st, cadena);
        if (rs != null) {
        	int id = 0;
        	while (rs.next()) {
            id++;
               int d = (int)rs.getObject("id");
               try {
				my.updateOneD(d, "hormigas", "id", id);
               } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
               }
        	}
        	con.cerrar(rs);
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
	public int aleatorioHormiga(int cont,int dif){
		int res =  (int)(Math.random()*cont)+1;	
		if(res == dif) res = aleatorioHormiga(cont,dif);
		return res;
	}
	
				
}



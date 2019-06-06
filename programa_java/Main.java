package programa_java;
import java.sql.*;
import java.util.Scanner;
import java.io.IOException;
public class Main{
	public static void main(String[] args){
		try{
	   		Scanner sn = new Scanner(System.in);
       		boolean salir = false;
       		String opcion; //Guardaremos la opcion del usuario
  			BaseJava base = new BaseJava("Base_De_Datos/");
  			Connection connection= base.setUpConnection();

       		while(!salir){

        	   System.out.println("1. Cargar un cliente");
        	   System.out.println("2. Eliminar un cliente");
        	   System.out.println("3. Listar Todas las mucamas");
        	   System.out.println("4. Salir");

        	   System.out.println("Escribe una de las opciones");
        	   opcion = sn.nextLine();

        	   switch(opcion){
        	       case "1":
        	           System.out.println("Has seleccionado Cargar Cliente ");
        	           System.out.println("Ingrese Nombre");
        	           String nom =sn.nextLine();
        	           System.out.println("Ingrese Apellido");
            	       String ape =sn.nextLine();
                	   System.out.println("Ingrese DNI");
                	   String dni1 =sn.nextLine();
	                   System.out.println("Ingrese Direccion");
    	               String dir =sn.nextLine();
        	           System.out.println("Ingrese Nro Cliete");
            	       String nro =sn.nextLine();
                	   System.out.println("Ingrese Estado Civil");
	                   String estado =sn.nextLine();
	                   estado = estado.toUpperCase();
    	               base.insertCliente(dni1,nom,ape,dir,nro,estado,connection);
										 System.out.println("");
										 System.out.println("Carga exitosa");
        	           break;
            	   case "2":
	                   System.out.println("Has seleccionado Eliminar cliente");
    	               System.out.println("Ingrese el DNI del cliente que desea Eliminar");
        	           String dni =sn.nextLine();
            	       base.deleteCliente(dni,connection);
										 System.out.println("");
										 System.out.println("Borrado Exitoso");
                	   break;
	                case "3":
    	               System.out.println("Has seleccionado la opcion de Listar Todas las mucamas");
        	           base.listMucamas(connection);
										 System.out.println("");
            	       break;
                	case "4":
	                   salir=true;
    	               break;
        	        default:
            	       System.out.println("Solo números entre 1 y 4");
      	     	}
							System.out.println("");
							System.out.println("Presione una tecla para continuar");
							sn.nextLine();
     	  	}
     	}
     	catch(SQLException e){
     		System.err.println(""+e);
     	}
     	catch(IOException ex){
     		System.err.println(""+ex);
     	}
    }
}

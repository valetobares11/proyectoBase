import java.sql.*;

public class BaseJava {

	public BaseJava(String dir,String driver1){
			String driver = driver1;
    		String url = dir;
    		String username = "root";
    		String password = "root";
    }

    public Connection setUpConnection(String url,String driver) throws SQLException{
    	try{
    		// Load database driver if not already loaded.
      		Class.forName(driver);

      		// Establish network connection to database.
      		Connection connection =	DriverManager.getConnection(url,"root","root");
      		connection.setAutoCommit(false); 
      		return connection;
      	}
      	catch(ClassNotFoundException cnfe) {
      		System.err.println("Error loading driver: " + cnfe);
      		System.exit(1);
      		return null;
      	}
      	catch(SQLException e){
      		throw new SQLException(e);
      	}
    }


    public void insertCliente(String dni,String nroCte, String estadoCivil,Connection connection){
    	try{
    		String query = "INSERT INTO Cliente(dni_cliente,nro_cliente,estadoCivil) VALUES(?,?,?);";
    		PreparedStatement statement = connection.prepareStatement(query);
      		// Send query to database and store results.
      		statement.setString(1,dni);
      		statement.setString(2,nroCte);
      		statement.setString(3,estadoCivil);
      		statement.executeUpdate();
    		connection.commit();
    	}
    	catch(Exception e){
    		System.out.println("ERROR" + e);
    	}
    }
    public void deleteCliente(String dni,Connection connection){
    	try{
    		String query = "DELETE FROM Cliente WHERE dni_cliente = ?;";
    		PreparedStatement statement = connection.prepareStatement(query);
    			statement.setString(1,dni);
    			statement.executeUpdate();
    			connection.commit();
    		}
    	catch(Exception e){
    		System.out.println("ERROR" + e);
    	}
    }
    public void listMucamas(Connection connection){
    	try{
    		String query = "SELECT * FROM Persona INNER JOIN (Mucama LEFT JOIN Habitacion ON dni_Mucama = dni_Muca) ON dni = dni_Mucama;";
    		ResultSet res = connection.createStatement().executeQuery(query);
    		while(res.next()){
    			int dni = res.getInt("dni");
    			String nombre = res.getString("nombre");
    			String apellido = res.getString("apellido");
    			int nro = res.getInt("nro_Habitacion");
    			System.out.println("*********************");
    			System.out.println("DNI Mucama: " + dni);
    			System.out.println("Nombre: "+ nombre);
    			System.out.println("Apellido: " + apellido);
    			if(nro > 0){
    				System.out.println("Nro Habitacion: " + nro);
    			}
    			else{
    				System.out.println("Nro Habitacion:  NULL");
    			}

    		}
    	}
    	catch(Exception e){
    		System.out.println("ERROR" + e);
    	}
    }
    public static void main(String[] args) {
    		String driver = "org.gjt.mm.mysql.Driver";
    		String url = "jdbc:mysql://localhost:3306/database_Proyect";
    		try{
    			BaseJava base = new BaseJava(url,driver);
    			Connection con = base.setUpConnection(url,driver);
    			System.out.println("OK");
    			//base.insertCliente("2","23","SOLTERO",con);
	    		//base.deleteCliente("2",con);
	    		base.listMucamas(con);
	    	}
	    	catch(SQLException e){
	    		System.out.println("Error " + e);
	    	}
    }



}
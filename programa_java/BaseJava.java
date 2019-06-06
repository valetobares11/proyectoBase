package programa_java;
import java.sql.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Properties;

public class BaseJava {
	private String driver;
	private String url;
	private String username;
	private String password;

	public BaseJava(String path) throws IOException{
		Properties dbProps = new Properties();
		try{
			InputStream input = new FileInputStream(path+"database.properties");
			dbProps.load(input);
		}
		catch(IOException e){
			throw new IOException(e);
		}
		driver = dbProps.getProperty("driver");
		url = dbProps.getProperty("url");
		username = dbProps.getProperty("username");
		password = dbProps.getProperty("password");
	}

  public Connection setUpConnection() throws SQLException{
  	try{
  			// Load database driver if not already loaded.
  			Class.forName(this.driver);
  			// Establish network connection to database.
  			Connection connection =	DriverManager.getConnection(this.url,this.username,this.password);
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



	public void insertCliente(String dni, String nombre, String apellido, String direccion,String nroCte, String estadoCivil,Connection connection){
		try{
			String query1 = "INSERT INTO Persona(dni,nombre,apellido,direccion) VALUES(?,?,?,?);";
			PreparedStatement s = connection.prepareStatement(query1);
			s.setString(1,dni);
			s.setString(2,nombre);
			s.setString(3,apellido);
			s.setString(4,direccion);
			s.executeUpdate();
			query1 = "INSERT INTO Cliente(dni_cliente,nro_cliente,estadoCivil) VALUES(?,?,?);";
			s = connection.prepareStatement(query1);
			s.setString(1,dni);
			s.setString(2,nroCte);
			s.setString(3,estadoCivil);
			s.executeUpdate();
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
}

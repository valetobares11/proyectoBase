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
/**
*Asigna los atributos de nuestra base de datos a los atributos de la clase,
*Estos datos los encuentra en un archivo llamado "database.properties"
*@throws IOException cuando el archivo no es encontrado
*@param path El camino donde esta el archivo database.properties, si esta en el mismo directorio, es vacio
*/
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

/**
*Se conecta a la base de datos
*@throws SQLException si nuestra base de datos no existe o nuestro usuario o clave son incorrectos
*@throws ClassNotFoundException si el driver no es encontrado
*@return La conexion con la base de datos
*/
  public Connection setUpConnection() throws SQLException,ClassNotFoundException{
  	try{
  			// Load database driver if not already loaded.
  			Class.forName(this.driver);
  			// Establish network connection to database.
  			Connection connection =	DriverManager.getConnection(this.url,this.username,this.password);
  			connection.setAutoCommit(false);
  			return connection;
      }
      catch(ClassNotFoundException cnfe) {
				throw new ClassNotFoundException();
      }
      catch(SQLException e){
      	throw new SQLException(e);
      }
  }


/**
*Carga un cliente en la base de datos
*@param dni El dni del cliente
*@param nombre El nombre del cliente
*@param apellido El apellido del cliente
*@param direccion La direccion del cliente
*@param nroCte El numero de cliente
*@param estadoCivil el estado civil del cliente
*@param connection la conexion a la base de datos
*@throws SQLException Cuando ocurre un error en la base de datos(Se cargan mal los datos)
*/
	public void insertCliente(String dni, String nombre, String apellido, String direccion,String nroCte, String estadoCivil,Connection connection)throws SQLException{
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
		catch(SQLException e){
    		throw new SQLException(e);
		}
	}

	/**
	*Borra a un cliente utilizando su dni
	*@param dni El dni del cliente a borrar
	*@param connection La conexion a la base de datos
	*@throws SQLException Cuando ocurre un error en la base de datos
	*/
  public void deleteCliente(String dni,Connection connection) throws SQLException{
  	try{
  		String query = "DELETE FROM Cliente WHERE dni_cliente = ?;";
  		PreparedStatement statement = connection.prepareStatement(query);
  			statement.setString(1,dni);
  			statement.executeUpdate();
				connection.commit();
  		}
  	catch(SQLException e){
  		throw new SQLException(e);
  	}
  }

	/**
	*Lista todas las mucamas con las habitaciones que limpian
	*@param connection La conexion a la base de datos
	*@throws SQLException Cuando ocurre un error en la base de datos
	*/
  public void listMucamas(Connection connection) throws SQLException{
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
    catch(SQLException e){
    	throw new SQLException(e);
    }
  }
}

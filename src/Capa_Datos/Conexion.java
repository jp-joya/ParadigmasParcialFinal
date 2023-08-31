/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Datos;

import java.sql.*;  // Importa las clases relacionadas con SQL

public class Conexion {
	
	private final String url = "jdbc:mysql://localhost:3306/adelita";  // URL de la base de datos MySQL
	private final String user = "root";  // Usuario de la base de datos
	private final String pwt = "";      // Contraseña de la base de datos (en este caso, está vacía)
        
	public Conexion() {
		// Constructor por defecto, no hace nada especial
	}
	
	// Método para realizar una consulta SELECT en la base de datos
	public ResultSet Listar(String Cad) {
		try {
			Connection cn = DriverManager.getConnection(url, user, pwt);  // Establece una conexión a la base de datos
			PreparedStatement da = cn.prepareStatement(Cad);  // Prepara una consulta SQL con la cadena proporcionada
			ResultSet tbl = da.executeQuery();  // Ejecuta la consulta y obtiene el resultado como un conjunto de resultados (ResultSet)
			return tbl;  // Devuelve el conjunto de resultados
		} catch (SQLException e) {
			javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());  // Muestra un mensaje de error en caso de excepción
			return null;  // Devuelve null en caso de error
		}
	}
	
	// Método para ejecutar una consulta SQL que modifica los datos en la base de datos
    public String Ejecutar(String Cad) {
        try {
            Connection cn = DriverManager.getConnection(url, user, pwt);  // Establece una conexión a la base de datos
            PreparedStatement da = cn.prepareStatement(Cad);  // Prepara una consulta SQL con la cadena proporcionada
            int r = da.executeUpdate();  // Ejecuta la consulta y obtiene el número de filas afectadas
            if (r != 0) {
                return "";  // Si alguna fila fue afectada, devuelve una cadena vacía
            }
            return "Articulo no encontrado";  // Si ninguna fila fue afectada, devuelve un mensaje indicando que el artículo no se encontró
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());  // Muestra un mensaje de error en caso de excepción
            return null;  // Devuelve null en caso de error
        }
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Datos;

import java.sql.*;


public class Conexion {
	
	private final String url="jdbc:mysql://localhost:3306/ventas";
	private final String user="root";
	private final String pwt="";
        
	
	public Conexion() 
	{}
	public ResultSet Listar(String Cad) {
		try {
			Connection cn = DriverManager.getConnection(url, user, pwt);
			PreparedStatement da = cn.prepareStatement(Cad);
			ResultSet tbl = da.executeQuery();
			return tbl;
		} catch (SQLException e) {
			javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}
	
    public String Ejecutar(String Cad) {
    try    
    {
        Connection cn = DriverManager.getConnection(url,user,pwt);
        PreparedStatement da = cn.prepareStatement(Cad);
        int r = da.executeUpdate();
        if (r!=0) {
            return "Se afectaron " + r + " filas";
        }
	return "Articulo no encontrado";
    } catch (SQLException e) {
	javax.swing.JOptionPane.showMessageDialog(null,e.getMessage());
	return null;
    }
    }

   
	
	
}

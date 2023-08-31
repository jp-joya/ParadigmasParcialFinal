/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Negocio;

import java.util.ArrayList;
import java.sql.*;
import Capa_Datos.Conexion;


public class DataArticulo {

	private String art_cod;
	private String art_nom;
	private double art_pre;
	
	public String EliminarArticulo() {
		Conexion objmod = new Conexion();
		String cad = "delete from articulo where art_cod='"+ this.getArt_cod() + "'";
		return objmod.Ejecutar(cad);
		
	}
	
	public String GrabarArticulo() {
            Conexion objmod = new Conexion();
            String cad = "Insert into articulo values ('" + this.getArt_cod() + "', '" + this.getArt_nom() + "', '" + this.getArt_pre() + "')";
            return objmod.Ejecutar(cad);
	}
	
	public String EditarArticulo() {
            Conexion objmod = new Conexion ();
            String cad = "update articulo set art_nom='" + this.getArt_nom() + "', art_pre='" + this.getArt_pre()
                + "' where art_cod='" + this.getArt_cod() + "'";
            return objmod.Ejecutar(cad);
	}
	
	public ArrayList<DataArticulo> ListaArticulos() {
            ArrayList lista2 = new ArrayList();
            try {
                Conexion objmod = new Conexion();
                ResultSet tabla = objmod.Listar("select * from articulo");
                DataArticulo objart;
                while (tabla.next()) {
                    objart = new DataArticulo();
                    objart.setArt_cod(tabla.getString("art_cod"));

                    objart.setArt_nom(tabla.getString("art_nom"));

                    objart.setArt_pre(tabla.getDouble("art_pre"));
                    lista2.add(objart);                
                }
            } catch (SQLException e) {
                javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
            }

            return lista2;
	}
	
	public String getArt_cod() {
		return art_cod;
	}

	public void setArt_cod(String art_cod) {
		this.art_cod = art_cod;
	}

	public String getArt_nom() {
		return art_nom;
	}

	public void setArt_nom(String art_nom) {
		this.art_nom = art_nom;
	}
	public double getArt_pre() {
		return art_pre;
	}

	public void setArt_pre(double art_pre) {
		this.art_pre = art_pre;
	}

    @Override
    public String toString() {
        return "DataArticulo{" + "art_cod=" + art_cod + ", art_nom=" + art_nom + ", art_pre=" + art_pre + '}';
    }
        
	
}

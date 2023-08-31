/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Negocio;
/**
 *
 * @author jpjoy
 */
public class DataOrden {
    DataArticulo producto;
    private int cantidad;

    public DataOrden(DataArticulo producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public DataArticulo getProducto() {
        return producto;
    }

    public void setProducto(DataArticulo producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public String toString() {
		return producto + "	/	" + cantidad + "\n";
	}
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Negocio;

import Capa_Datos.Conexion;
import java.sql.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class DataPedido {
    private ArrayList<DataOrden> ordenes;
    private LocalDateTime fecha;
    private int cantidadProductos;
    private int contadorPedidos = 1;
    private int total;

    public DataPedido(){
        this.ordenes = new ArrayList<DataOrden>();
    }
    
    // Método para crear un pedido en la base de datos
    public String CrearPedido(){
        Conexion objmod = new Conexion();
        String cad = "Insert into pedido values ('" + contadorPedidos + "', '" + formarFecha(fecha) + "', '" + calcularCantidadProductos() +"', '"+ calcularTotal() + "')";
        contadorPedidos++;
        return objmod.Ejecutar(cad);  // Llama al método Ejecutar de la clase Conexion para ejecutar la consulta
    }

    // Método para obtener una lista de pedidos desde la base de datos
    public ArrayList<DataPedido> ListaPedidos() {
        ArrayList<DataPedido> lista = new ArrayList<>();
        try {
            Conexion objmod = new Conexion();
            ResultSet tabla = objmod.Listar("select * from pedido");  // Llama al método Listar de la clase Conexion para obtener los datos
            DataPedido objPed;
            while (tabla.next()) {
                objPed = new DataPedido();
                
                String str = tabla.getString("fecha");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); 
                objPed.setFecha(LocalDateTime.parse(str, formatter));
                
                objPed.setCantidadProductos(tabla.getInt("nPedidos"));
                objPed.setTotal(tabla.getInt("total"));
                lista.add(objPed);                
            }
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());  // Muestra un mensaje de error en caso de excepción
        }
        return lista;  // Devuelve la lista de pedidos
    }

    // Método para agregar una DataOrden a la lista de ordenes del pedido
    public void agregarDataOrden(DataArticulo producto, int cantidad) {
        boolean existeDataOrden = false;

        for (DataOrden orden : ordenes) {
            if (orden.getProducto().getArt_cod().equals(producto.getArt_cod())) {
                orden.setCantidad(orden.getCantidad() + cantidad);
                existeDataOrden = true;
                break;
            }
        }

        if (!existeDataOrden) {
            DataOrden nuevaDataOrden = new DataOrden(producto, cantidad);
            ordenes.add(nuevaDataOrden);
        }
    }

    // Método para obtener un pedido de la lista de pedidos
    public DataPedido get(int n){
        return ListaPedidos().get(n);
    }

    @Override
    public String toString() {
        String list = " " + Arrays.toString(ordenes.toArray()).replace("[", "").replace("]", "").replace(",", "");
        return list;
    }

    public ArrayList<DataOrden> getDataOrdenes() {
        return ordenes;
    }

    public int calcularTotal () {
        int total=0;
        for(DataOrden orden: ordenes) {
            total += orden.getProducto().getArt_pre() * orden.getCantidad();
        }
        return total;
    }

    public int calcularCantidadProductos() {
        int cantidadTotalProductos = 0;
        for (DataOrden orden : ordenes) {
            cantidadTotalProductos += orden.getCantidad();
        }
        return cantidadTotalProductos;
    }

    public void setFecha(LocalDateTime localDateTime) {
        this.fecha = localDateTime;
    }

    public int getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(int cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public int getTotal() {
        return total;
    }
    
    public void setTotal(int total) {
        this.total = total;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String formarFecha(LocalDateTime fecha) {
        DateTimeFormatter fechaF = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String fechas = fecha.format(fechaF);
        return fechas;
    }
}

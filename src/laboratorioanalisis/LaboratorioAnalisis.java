/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorioanalisis;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * @author johan soler, danny ochoa y juan bernal
 */
public class LaboratorioAnalisis {

    JFrame ventana;

    public LaboratorioAnalisis(JFrame ventana) {
        this.ventana = ventana;
    }
    
    public String[][] getTableOfJtable (JTable tabla){
        String[][] datos = new String [tabla.getRowCount()][tabla.getColumnCount()] ;
        for (int i = 0; i < tabla.getRowCount(); i++) {
            for (int j = 0; j < tabla.getColumnCount(); j++) {
                datos[i][j] = "" + tabla.getValueAt(i, j);
            }
        }
        return datos;
    }
    
    public String comprar(String codigo, String saldo, JTextField vueltas, JTable tabla){
        String[][] datos = getTableOfJtable(tabla);
        for (int i = 0; i < datos.length; i++) {
            for (int j = 0; j < datos[0].length; j++) {
                if(datos[i][0].equals(codigo)){
                    if(Integer.parseInt(datos[i][1]) <= Integer.parseInt(saldo)){
                        if(Integer.parseInt(datos[i][2]) - Integer.parseInt(datos[i][3]) >0){
                            tabla.setValueAt((Integer.parseInt(datos[i][3])+1)+"", i, 3);
                            tabla.setValueAt((Integer.parseInt(datos[i][4])-1)+"", i, 4);
                            vueltas.setText(Integer.parseInt(saldo) - Integer.parseInt(datos[i][1])+"");
                            return datos[i][5] + ", Disponible";
                        }else {
                            return datos[i][5] + ", No disponible";
                        }
                    }else {
                        return "Saldo insuficiente";
                    }
                }
            }
        }
        return "el codigo no corresponde a ningun producto";
    }
    
    public String registrar (String codigo, String valor, String inventario, String nombre, JTable tabla){
        
        DefaultTableModel modelo = new DefaultTableModel();
        String[] registro = {codigo, valor, inventario,"0", inventario, nombre};
        String[][] datos = getTableOfJtable(tabla);
        String[][] nuevaTabla = new String [datos.length+1][datos[0].length];
        int pointAux = 0;
        
        if(codigo.length()>1 || valor.length()>1 || inventario.length()>1 || nombre.length()>1){
            for (int i = 0; i < datos.length; i++) {
                pointAux = i;
                for (int j = 0; j < datos[0].length; j++) {
                    nuevaTabla[i][j] = datos [i][j];
                }
            }
            pointAux+=1;
            for (int i = 0; i < registro.length; i++) {
                nuevaTabla[pointAux][i] = registro[i];
            }
            setHeader(modelo);
            for (int i = 0; i < nuevaTabla.length; i++) {
                modelo.addRow(nuevaTabla[i]);
            }
            tabla.setModel(modelo);

            return "Se registro correctamente el producto:" + nombre;
        }
        return "No se pudo registrar el producto";
    }
    
    public void setHeader(DefaultTableModel modelo){
        modelo.addColumn("Codigo");
        modelo.addColumn("Precio");
        modelo.addColumn("Inventario");
        modelo.addColumn("Ventas X Producto");
        modelo.addColumn("Productos Disponibles");
        modelo.addColumn("Nombre");
    }
    
}

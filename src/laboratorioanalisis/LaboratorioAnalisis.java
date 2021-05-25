/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorioanalisis;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

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
        System.out.println(codigo + " -- " + datos[0][0]);
        for (int i = 0; i < datos.length; i++) {
            for (int j = 0; j < datos[0].length; j++) {
                if(datos[i][0].equals(codigo)){
                    if(Integer.parseInt(datos[i][2]) - Integer.parseInt(datos[i][3]) >0){
                        tabla.setValueAt((Integer.parseInt(datos[i][3])+1)+"", i, 3);
                        tabla.setValueAt((Integer.parseInt(datos[i][2])-1)+"", i, 2);
                        return datos[i][5] + ", Disponible";
                    }else {
                        return datos[i][5] + ", No disponible";
                    }
                }
            }
        }
        return "el codigo no corresponde a ningun producto";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;



/**
 *
 * @author angel
 */
public class Principal{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Modelo modelo = new Modelo();
        modelo.aprenderModelo();
        System.out.println("Al aplicar Random Forest a los datos obtenidos, la solución es " + modelo.aplicarModelo());
    }

}

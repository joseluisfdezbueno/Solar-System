/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import GUI.ControlWindow;
import Model.Universo;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.Canvas3D;

/**
 *
 * @author Rogelio García Peña
 * @author José Luis Fernandez Bueno
 */
public class Practica1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Se obtiene la configuración gráfica del sistema y se crea el Canvas3D que va a mostrar la imagen
        Canvas3D canvasPlanta = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
        Canvas3D canvasVariable = new Canvas3D (SimpleUniverse.getPreferredConfiguration());
        // Se le da el tamaño deseado al lienzo
        canvasPlanta.setSize(600, 400);
        canvasVariable.setSize(600, 400);
        // Se crea el universo y la rama de la vista con ese canvas
        Universo universo = new Universo(canvasPlanta, canvasVariable);
        //Universo universo2 = new Universo(canvasVariable);
        // Se crea la GUI a partir del Canvas3D y del Universo
        ControlWindow controlWindow = new ControlWindow (canvasPlanta, canvasVariable, universo);
        // Se muestra la ventana principal de la aplicación
        controlWindow.showWindow();
    }
    
}

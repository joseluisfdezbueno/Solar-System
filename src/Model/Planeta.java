/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

/**
 *
 * @author Rogelio García Peña
 * @author José Luis Fernandez Bueno
 */
public class Planeta extends AstroEsferico {
    private ArrayList<Satelite> misSatelites;
    private ArrayList<AnilloPlanetario> misAnillos;    
    
    public Planeta(String nombre, float radio, int velT, int velR, String rutaImagen, Material material, Vector3d vector) {
        super(nombre, radio, velT, velR, rutaImagen, material, vector);   
    
        misSatelites = new ArrayList<>();
        misAnillos = new ArrayList<>();        
    }
    
    public void añadirSatelite (Satelite s){
        misSatelites.add(s);
        traslacion.addChild(s.getRaiz());
    }
    
    public void añadirAnillo (AnilloPlanetario anillo){
        misAnillos.add(anillo);
        traslacion.addChild(anillo.getRotacion());
    }    
   
}

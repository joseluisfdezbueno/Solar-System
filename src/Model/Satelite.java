/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.media.j3d.Material;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

/**
 *
 * @author Rogelio García Peña
 * @author José Luis Fernandez Bueno
 */
public class Satelite extends AstroEsferico{
    
    public Satelite(String nombre, float radio, int velT, int velR, String rutaImagen, Material material, Vector3d vector) {
        super(nombre, radio, velT, velR, rutaImagen, material, vector);        
    }

}

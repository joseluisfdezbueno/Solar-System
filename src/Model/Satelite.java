/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

/**
 *
 * @author Rogelio García Peña
 * @author José Luis Fernandez Bueno
 */
public class Satelite extends AstroEsferico{
    
    public Satelite(String nombre, float radio, int velT, int velR, String rutaImagen, Vector3d vector) {
        super(nombre, radio, velT, velR, rutaImagen, vector);        
        this.textura.modificarMaterial(new Color3f (0.2f, 0.2f, 0.2f), new Color3f (0.0f, 0.0f, 0.0f),
            new Color3f (0.5f, 0.5f, 0.5f), new Color3f (0.7f, 0.7f, 0.7f), 128.0f);
    }
    
    /*
    public TransformGroup getTr(){
        return rot;
    }
    */
}

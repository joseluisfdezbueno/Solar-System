/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

/**
 *
 * @author Rogelio García Peña
 * @author José Luis Fernandez Bueno
 */
public class Traslacion extends TransformGroup{
    private final Transform3D translation;
    
    public Traslacion(Vector3d vector){
        translation = new Transform3D();
        translation.setTranslation(vector);
        this.setTransform(translation);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;

/**
 *
 * @author Rogelio García Peña
 * @author José Luis Fernandez Bueno
 */
public class Rotacion extends TransformGroup{
    //private final TransformGroup transform;
    private final Alpha value;
    private boolean estado;
    private RotationInterpolator rotator;
    
    public Rotacion (String eje, int vel){
        // Se crea el grupo que contendrá la transformación de rotación
        // Todo lo que cuelgue de él rotará
        //transform = new TransformGroup ();
        // Se le permite que se cambie en tiempo de ejecución
        this.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // Se crea la matriz de rotación
        Transform3D yAxis = new Transform3D ();
        if (eje.equalsIgnoreCase("y")){
            yAxis.rotY(Math.PI/2);
        }else if (eje.equalsIgnoreCase("x")){
            yAxis.rotX(Math.PI/2);
        }else
             yAxis.rotZ(Math.PI/2);
        // Se crea un interpolador, un valor numérico que se ira modificando en tiempo de ejecución
        value = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, 
                vel, 0, 0, 0, 0, 0);
        // Se crea el interpolador de rotación, las figuras iran rotando
        rotator = new RotationInterpolator (value, this, yAxis, 0.0f, (float) Math.PI*(-2.0f));
        // Se le pone el entorno de activación y se activa
        rotator.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 10000.0));
        rotator.setEnable(true);
        estado = true;
        // Se cuelga del grupo de transformación y este se devuelve
        this.addChild(rotator);
    }

    public TransformGroup getTransform() {
        return this;
    }
    
    // paramos/reanudamos la rotacion
    public void pararReanudar(){
        if (estado){
            rotator.setEnable(false);
            estado = false;
        }else{
            rotator.setEnable(true);
            estado = true;
        }            
    }
    
}

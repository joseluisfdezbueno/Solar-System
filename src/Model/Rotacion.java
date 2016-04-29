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
    private Alpha valorAlpha;
    private boolean estado;
    private RotationInterpolator rotacion;
    
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
        valorAlpha = new Alpha (-1, Alpha.INCREASING_ENABLE, 0, 0, 
                vel, 0, 0, 0, 0, 0);
        // Se crea el interpolador de rotación, las figuras iran rotando
        rotacion = new RotationInterpolator (valorAlpha, this, yAxis, 0.0f, (float) Math.PI*2.0f);
        // Se le pone el entorno de activación y se activa
        rotacion.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0 ), 100.0));
        rotacion.setEnable(true);
        estado = true;
        // Se cuelga del grupo de transformación y este se devuelve
        this.addChild(rotacion);
    }

    // paramos/reanudamos la rotación
    public void pararReanudar(){
        if (estado){
            rotacion.setEnable(false);
            estado = false;
        }else{
            rotacion.setEnable(true);
            estado = true;
        }            
    }
    
}

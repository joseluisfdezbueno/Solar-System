/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.io.FileNotFoundException;
import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.RotPosPathInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3d;

/**
 *
 * @author Rogelio García Peña
 * @author José Luis Fernandez Bueno
 */
public class Nave extends BranchGroup{
    private ObjectFile archivo;
    //private BranchGroup bg;
    private Scene modelo;
    //private Traslacion tr;
    private Vista vista;
    
    public Nave(){      
        this.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        this.setCapability(Group.ALLOW_CHILDREN_WRITE);
        
        //tr = new Traslacion(new Vector3d (25.0f, 0.0f, 0.0f));
        
        archivo = new ObjectFile (ObjectFile.RESIZE | ObjectFile.STRIPIFY | ObjectFile.TRIANGULATE );
        try {   
            modelo = archivo.load ("nave\\naveEspacial\\naveEspacial.obj");
        }catch (FileNotFoundException | ParsingErrorException | IncorrectFormatException e){
            System.err.println (e);
            System.exit(1);
        }
        this.addChild (modelo.getSceneGroup());
    }
    
    // Añade una vista a la nave
    public void añadirVista(Vista vista){
        this.vista = vista;
        this.addChild(vista);
    }
    
    // Selección del camino a seguir por la nave, y su orientación    
    public TransformGroup inicializarRuta(){
                    
        Alpha alpha = new Alpha( -1, Alpha.INCREASING_ENABLE, 0,0,20000,0,0,0,0,0 );
        TransformGroup tg = new TransformGroup();                
        Transform3D transformacion = new Transform3D();
        float[] alphas = {0.0f, 0.20f, 0.30f, 0.40f, 0.55f, 0.70f, 0.80f, 0.90f, 1.0f};
        Quat4f[] quats = new Quat4f[9];
        Point3f[] positions = new Point3f[9];
        RotPosPathInterpolator rotPosPath;

        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        // Orientaciones
        quats[0] = new Quat4f(0.0f, 1.0f, 0.0f, (float) (Math.PI));
        quats[1] = new Quat4f(0.0f, 1.0f, 0.0f, (float) (Math.PI)*1.5f);
        quats[2] = new Quat4f(0.0f, 1.0f, 0.0f, (float) (Math.PI)*0);
        quats[3] = new Quat4f(0.0f, 1.0f, 0.0f, (float) (Math.PI)*0);
        quats[4] = new Quat4f(0.0f, 1.0f, 0.0f, (float) (Math.PI)*0);
        quats[5] = new Quat4f(0.0f, 1.0f, 0.0f, (float) (Math.PI)*0);
        quats[6] = new Quat4f(0.0f, 1.0f, 0.0f, (float) (Math.PI)/2);
        quats[7] = new Quat4f(0.0f, 1.0f, 0.0f, (float) (Math.PI)/2);
        //quats[8] = new Quat4f(0.0f, 1.0f, 0.0f, (float) (Math.PI)*0);
        quats[8] = quats[0];

        // Posiciones
        positions[0]= new Point3f(25.0f,  0.0f, 0.0f);
        positions[1]= new Point3f(25.0f, 0.0f, 25.0f);
        positions[2]= new Point3f(0.0f,  0.0f, 25.0f);
        positions[3]= new Point3f(-25.0f,  0.0f, 25.0f);
        positions[4]= new Point3f(-25.0f,  0.0f, 0.0f);
        positions[5]= new Point3f(-25.0f,  0.0f, -25.0f);
        positions[6]= new Point3f(0.0f, 0.0f, -25.0f);
        positions[7]= new Point3f(25.0f,  0.0f, -25.0f);
        //positions[8]= new Point3f(8.0f,  0.0f, 8.0f);
        positions[8]= positions[0];

        //RotPosPathInterpolator(alpha, ramaAAplicar, transformacion, alphas, rotaciones, posiciones);
        // instanciamos el interpolator de rotacion-posioión
        rotPosPath = new RotPosPathInterpolator(alpha, tg, transformacion, alphas, quats, positions);
        // Campo de influencia    
        rotPosPath.setSchedulingBounds(new BoundingSphere(new Point3d(), 10000.0));
        
        // Enlazamos el iterador y la nave al mismo TG
        tg.addChild(this);
        tg.addChild(rotPosPath);
        
        return tg;
    }
}

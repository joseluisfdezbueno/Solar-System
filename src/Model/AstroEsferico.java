/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.Node;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

/**
 *
 * @author Rogelio García Peña
 * @author José Luis Fernandez Bueno
 */
public class AstroEsferico{
    //private final BranchGroup bg;
    private final Primitive sphere;
    Textura textura;
    
    TransformGroup rot, rot2, raiz;
    Traslacion tr;
    Rotacion rotacionPropia, rotacionEstrella;
    Vista vista;

    public AstroEsferico(String nombre, float radio, int velT, int velR, String nombreImagen, Vector3d vector) {
        //this.bg = new BranchGroup();
    //    this.bg.setCapability(BranchGroup.ALLOW_DETACH);
        this.textura = new Textura(nombreImagen);
        this.sphere = new Sphere(radio, Primitive.GENERATE_NORMALS |  Primitive.GENERATE_TEXTURE_COORDS |
                                    Primitive.ENABLE_APPEARANCE_MODIFY, 64, textura.getAppearance());
        //this.bg.addChild(sphere);
        
        // rotación sobre si mismo
        rotacionPropia = new Rotacion("y",velR);
        rot = rotacionPropia.getTransform();
        
        // vector de distancia al centro de coordenadas
        tr = new Traslacion(vector);

        if(velT != 0){
            // rotación sobre el sol
            rotacionEstrella = new Rotacion("y",velT);
            rot2 = rotacionEstrella.getTransform();
        
            // rot.addChild(this.getBg());
            rot.addChild(sphere);
            tr.addChild(rot);
            rot2.addChild(tr);
            raiz = rot2;
        }else{
            rot.addChild(sphere);
            tr.addChild(rot);
            raiz = tr;            
        }

    }

   /* public BranchGroup getBg() {
        return this.bg;
    }*/
    
    public TransformGroup getTg(){
        return raiz;
    }
    
    public void añadirVista(Vista vista){
        this.vista = vista;
        tr.addChild(vista);
    }
                   
}
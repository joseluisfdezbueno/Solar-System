/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

/**
 *
 * @author Rogelio García Peña
 * @author José Luis Fernandez Bueno
 */
public class Planeta extends AstroEsferico {
    /*private final TransformGroup rot;
    private final Traslacion tr;
    private final Rotacion rotacion;*/
    private ArrayList<Satelite> misSatelites;
    
    public Planeta(String nombre, float radio, int velT, int velR, String rutaImagen, Vector3d vector) {
        super(nombre, radio, velT, velR, rutaImagen, vector);   
/*
        rotacion = new Rotacion("y",velR);
        tr = new Traslacion(vector);
        rot = rotacion.getTransform();
        rot.addChild(this.getBg());
        tr.addChild(rot);
*/
        // ambiental, emisiva, difusa, especular
        this.textura.modificarMaterial(new Color3f (0.2f, 0.2f, 0.2f), new Color3f (0.0f, 0.0f, 0.0f),
                new Color3f (0.5f, 0.5f, 0.5f), new Color3f (0.7f, 0.7f, 0.7f), 128.0f);
                
        misSatelites = new ArrayList<>();
    }
    
    public void addSatelite (Satelite s){
        misSatelites.add(s);
        tr.addChild(s.getTg());
    }
   
    /*public Traslacion getTr(){
        return tr;
    }*/
}

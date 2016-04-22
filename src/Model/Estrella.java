/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javax.media.j3d.BranchGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;


/**
 *
 * @author Rogelio García Peña
 * @author José Luis Fernandez Bueno
 */
public class Estrella extends AstroEsferico{
    BranchGroup planetas;
    ArrayList<Planeta> misPlanetas;
    Luz miLuz;
    
    public Estrella(String nombre, float radio, int velT, int velR, String rutaImagen, Vector3d vector){
        super(nombre, radio, velT, velR, rutaImagen, vector);
        
        // seleccionamos el material de la estrella
        // ambiental, emisiva, difusa, especular, brillo
        this.textura.modificarMaterial(new Color3f (0.2f, 0.2f, 0.2f), new Color3f (1.0f, 1.0f, 1.0f),
                new Color3f (0f, 0f, 0f), new Color3f (0f, 0f, 0f), 1.0f);
        
        // creamos la rama de los planetas que giran alrededor de la estrella
        planetas = new BranchGroup();
        raiz.addChild(planetas);
        
        misPlanetas = new ArrayList<>();
        miLuz = new Luz();
        // Creamos y agregamos la luz puntual de la estrella
        raiz.addChild(miLuz.crearLuzPuntual(new Color3f(0.7f, 0.7f, 0.7f), new Point3f(0, 0, 0), new Point3f(0.05f, 0f, 0f))); // color, posicion, atenuación
        
    }
    
    void añadirPlaneta (Planeta p){
        misPlanetas.add(p);
        planetas.addChild(p.getTg());
    }
}

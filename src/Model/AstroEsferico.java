/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import javax.media.j3d.Material;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

/**
 *
 * @author Rogelio García Peña
 * @author José Luis Fernandez Bueno
 */
public class AstroEsferico{
    private final String nombre;
    private final float radio;
    private final int velT;
    private final int velR;
    private final String rutaImagen;
    private final Material material;
    private final Vector3d distanciaReferencia;
    private final Textura textura;
    private Vista vista;
    private final Primitive esfera;
    
    TransformGroup raiz;
    Traslacion traslacion;
    Rotacion rotacionPropia, rotacionEstrella;
    

    public AstroEsferico(String nombre, float radio, int velT, int velR, String rutaImagen, Material material, Vector3d distancia) {
        this.nombre = nombre;
        this.radio = radio;
        this.velT = velT;
        this.velR = velR;
        this.rutaImagen = rutaImagen;
        this.material = material;
        this.distanciaReferencia = distancia;
                
        // Creamos la esfera (y su textura) que representará un astro
        this.textura = new Textura(rutaImagen, material);
        this.esfera = new Sphere(radio, Primitive.GENERATE_NORMALS |  Primitive.GENERATE_TEXTURE_COORDS |
                                    Primitive.ENABLE_APPEARANCE_MODIFY, 64, textura.getAppearance());
        
        // rotación sobre si mismo
        rotacionPropia = new Rotacion("y",velR);
        
        // traslacion
        traslacion = new Traslacion(distancia);

        if(velT != 0){ // Para los astros que giran alrecedor de otros astros
            // rotación sobre el sol
            rotacionEstrella = new Rotacion("y",velT);
            
            rotacionPropia.addChild(esfera);
            traslacion.addChild(rotacionPropia);
            rotacionEstrella.addChild(traslacion);
            raiz = rotacionEstrella;
        }else{        // Para las estrellas o astros que no giran alrededor de ningún otro astro
            rotacionPropia.addChild(esfera);
            traslacion.addChild(rotacionPropia);
            raiz = traslacion;
        }

    }
    
    // devolvemos el nodo raiz que contiene al astro y sus transformaciones
    public TransformGroup getRaiz(){
        return raiz;
    }
    
    // añadimos una vista al astro
    public void añadirVista(Vista vista){
        this.vista = vista;
        traslacion.addChild(vista);
    }
                   
}
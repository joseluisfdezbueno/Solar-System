/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import com.sun.j3d.utils.geometry.Primitive;
import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Group;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 *
 * @author Jose
 */

public class Escena extends BranchGroup{
    
    private Estrella sol;
    private Planeta planeta;
    private Satelite satelite;
    private Vista vistaLuna;
    
    Escena(Vista vista){        
        this.vistaLuna = vista;
        // Se crea la escena
        crearEscena();
    }

    private void crearEscena(){
                       
        //############## SOL ##############
        // Estrella (nombre, radio, velTraslacion, velRotacion, rutaImagen, distanciaCentro)
        sol = new Estrella("Sol", 4, 0, -8000, "imgs/sol.jpg", new Vector3d (0f, 0f, 0f));
        this.addChild(sol.getTg());
        //------------ SOL -----------------        
                              
        //############## MERCURIO //##############        
        //  Planeta (nombre, radio, velTraslacion, velRotacion, rutaImagen, distanciaEstrella)
        this.planeta = new Planeta("Mercurio", 0.6f, 9000, -7000, "imgs/mercurio.jpg", new Vector3d (6.0f, 0.0f, 0.0f));
        sol.añadirPlaneta(planeta);      
        //------------ Mercurio -----------------
        
        //############## VENUS //##############
        this.planeta = new Planeta("Venus", 0.6f, 8000, 7000, "imgs/venus.jpg", new Vector3d (10.0f, 0.0f, 0.0f));
        sol.añadirPlaneta(planeta);
        //------------ VENUS -----------------       

        //############## TIERRA //##############
        this.planeta = new Planeta("Tierra", 1f, 9000, -7000, "imgs/tierra.jpg", new Vector3d (14.0f, 0.0f, 0.0f));
        sol.añadirPlaneta(planeta);        
        //............ LUNA ............
        //  Satélite (nombre, radio, velTraslacion, velRotacion, rutaImagen, distanciaPlaneta)
        this.satelite = new Satelite("Luna", 0.3f, 2000, 1000, "imgs/luna.jpg",new Vector3d (2f, 0.0f, 0.0f));
        this.satelite.añadirVista(vistaLuna);
        this.planeta.addSatelite(satelite);
        //------------ TIERRA -----------------       
        
        //############## MARTE //##############
        this.planeta = new Planeta("Martes", 0.6f, 8000, -7000, "imgs/marte.jpg", new Vector3d (18.0f, 0.0f, 0.0f));
        sol.añadirPlaneta(planeta);        
        //............FOBOS ............
        this.satelite = new Satelite("Luna", 0.3f, 2000, 1000, "imgs/fobos.jpg",new Vector3d (1f, 0.0f, 0.0f));
        this.planeta.addSatelite(satelite);
        //............ DEIMOS ............
        this.satelite = new Satelite("Luna", 0.3f, 2000, 1000, "imgs/deimos.jpg",new Vector3d (2f, 0.0f, 0.0f));
        this.planeta.addSatelite(satelite);                
        //------------ MARTE -----------------
        
        //############## JÚPITER //##############
        this.planeta = new Planeta("Júpiter", 2f, 9000, -7000, "imgs/jupiter.jpg", new Vector3d (24.0f, 0.0f, 0.0f));
        sol.añadirPlaneta(planeta);        
        //............ IO ............
        this.satelite = new Satelite("Io", 0.3f, 2000, 1000, "imgs/io.jpg",new Vector3d (2f, 0.0f, 0.0f));
        this.planeta.addSatelite(satelite);
        //............ EUROPA ............
        this.satelite = new Satelite("Europa", 0.3f, 2000, 1000, "imgs/europa.jpg",new Vector3d (3f, 0.0f, 0.0f));
        this.planeta.addSatelite(satelite);     
        //............ CALISTO ............
        this.satelite = new Satelite("Calisto", 0.3f, 2000, 1000, "imgs/calisto.jpg",new Vector3d (4f, 0.0f, 0.0f));
        this.planeta.addSatelite(satelite);   
        //------------ JÚPITER -----------------       
        
        //############## SATURNO //##############
        this.planeta = new Planeta("Saturno", 1.5f, 8000, -7000, "imgs/saturno.jpg", new Vector3d (28.0f, 0.0f, 0.0f));
        sol.añadirPlaneta(planeta);
        //............ Anillo A ............
        
        //............ Anillo B ............
        
        //............ Anillo C ............        
        
        //------------ SATURNO -----------------       
        
        //############## URANO //##############
        this.planeta = new Planeta("Urano", 1.5f, 9000, -7000, "imgs/urano.jpg", new Vector3d (32.0f, 0.0f, 0.0f));
        sol.añadirPlaneta(planeta);        
        //............ TITANIA ............
        this.satelite = new Satelite("Titania", 0.3f, 500, 1000, "imgs/titania.jpg",new Vector3d (2f, 0.0f, 0.0f));
        this.planeta.addSatelite(satelite);
        //............ ARIEL ............
        this.satelite = new Satelite("Ariel", 0.3f, 3000, 1000, "imgs/ariel.jpg",new Vector3d (3f, 0.0f, 0.0f));
        this.planeta.addSatelite(satelite);     
        //............ MIRANDA ............
        this.satelite = new Satelite("Miranda", 0.3f, 1500, 1000, "imgs/miranda.jpg",new Vector3d (4f, 0.0f, 0.0f));
        this.planeta.addSatelite(satelite);   
        //------------ URANO -----------------       
        
        //############## NEPTUNO //##############
        this.planeta = new Planeta("Neptuno", 1.5f, 5000, -7000, "imgs/neptuno.jpg", new Vector3d (36.0f, 0.0f, 0.0f));
        sol.añadirPlaneta(planeta);        
        //............ TRITÓN ............
        this.satelite = new Satelite("Tritón", 0.3f, 2000, 1000, "imgs/triton.jpg",new Vector3d (2f, 0.0f, 0.0f));
        this.planeta.addSatelite(satelite);          
        //------------ NEPTUNO -----------------               
                
    }

}

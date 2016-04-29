/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

/**
 *
 * @author Jose
 */

public class Escena extends BranchGroup{
    
    private Estrella sol;
    private final Vista vistaSubjetiva;
    
    Escena(Vista vista){        
        this.vistaSubjetiva = vista;
        // Se crea la escena
        crearEscena();
    }

    private void crearEscena(){
        Planeta planeta;
        Satelite satelite;
        AnilloPlanetario anillo;
        Material materialSol, materialPlaneta, materialSatelite, materialAnillo;
        
        //------ Definimos los materiales ------//
        // ambiental, emisivo, difuso, especular, brillo
        materialSol = new Material(new Color3f (0.2f, 0.2f, 0.2f), new Color3f (1.0f, 1.0f, 1.0f),
                new Color3f (0f, 0f, 0f), new Color3f (0f, 0f, 0f), 1.0f);
        
        materialPlaneta = new Material(new Color3f (0.2f, 0.2f, 0.2f), new Color3f (0.0f, 0.0f, 0.0f),
                new Color3f (0.5f, 0.5f, 0.5f), new Color3f (0.7f, 0.7f, 0.7f), 128.0f);
        
        materialSatelite = new Material(new Color3f (0.2f, 0.2f, 0.2f), new Color3f (0.0f, 0.0f, 0.0f),
            new Color3f (0.5f, 0.5f, 0.5f), new Color3f (0.7f, 0.7f, 0.7f), 128.0f);       
        
        materialAnillo = new Material(new Color3f (0.2f, 0.2f, 0.2f), new Color3f (0.0f, 0.0f, 0.0f),
                new Color3f (0.5f, 0.5f, 0.5f), new Color3f (0.7f, 0.7f, 0.7f), 128.0f);
        
        // -------------------------------------//
                                
                       
        //############## SOL ##############
        // Estrella (nombre, radio, velTraslacion, velRotacion, rutaImagen, material, distanciaCentro)
        this.sol = new Estrella("Sol", 4, 0, -8000, "imgs/Sol.jpg", materialSol, new Vector3d (0f, 0f, 0f));
        this.addChild(sol.getRaiz());
        //------------ SOL -----------------        
                              
        //############## MERCURIO //##############        
        //  Planeta (nombre, radio, velTraslacion, velRotacion, rutaImagen, material, distanciaEstrella)
        planeta = new Planeta("Mercurio", 0.6f, 9000, -7000, "imgs/mercurio.jpg", materialPlaneta, new Vector3d (6.0f, 0.0f, 0.0f));
        sol.añadirPlaneta(planeta);      
        //------------ Mercurio -----------------
        
        //############## VENUS //##############
        planeta = new Planeta("Venus", 0.6f, 8000, 7000, "imgs/venus.jpg", materialPlaneta, new Vector3d (10.0f, 0.0f, 0.0f));
        sol.añadirPlaneta(planeta);
        //------------ VENUS -----------------       

        //############## TIERRA //##############
        planeta = new Planeta("Tierra", 1f, 9000, -7000, "imgs/tierra.jpg", materialPlaneta, new Vector3d (14.0f, 0.0f, 0.0f));
        sol.añadirPlaneta(planeta);        
        //............ LUNA ............
        //  Satélite (nombre, radio, velTraslacion, velRotacion, rutaImagen, material, distanciaPlaneta)
        satelite = new Satelite("Luna", 0.3f, 2000, 1000, "imgs/luna.jpg", materialSatelite, new Vector3d (2f, 0.0f, 0.0f));
        satelite.añadirVista(vistaSubjetiva);
        planeta.añadirSatelite(satelite);
        //------------ TIERRA -----------------       
        
        //############## MARTE //##############
        planeta = new Planeta("Martes", 0.6f, 8000, -7000, "imgs/marte.jpg", materialPlaneta, new Vector3d (18.0f, 0.0f, 0.0f));
        sol.añadirPlaneta(planeta);        
        //............FOBOS ............
        satelite = new Satelite("Luna", 0.3f, 2000, 1000, "imgs/fobos.jpg", materialSatelite, new Vector3d (1f, 0.0f, 0.0f));
        planeta.añadirSatelite(satelite);
        //............ DEIMOS ............
        satelite = new Satelite("Luna", 0.3f, 2000, 1000, "imgs/deimos.jpg", materialSatelite, new Vector3d (2f, 0.0f, 0.0f));
        planeta.añadirSatelite(satelite);                
        //------------ MARTE -----------------
        
        //############## JÚPITER //##############
        planeta = new Planeta("Júpiter", 2f, 9000, -7000, "imgs/jupiter.jpg", materialPlaneta, new Vector3d (24.0f, 0.0f, 0.0f));
        sol.añadirPlaneta(planeta);        
        //............ IO ............
        satelite = new Satelite("Io", 0.3f, 2000, 1000, "imgs/io.jpg", materialSatelite, new Vector3d (2f, 0.0f, 0.0f));
        planeta.añadirSatelite(satelite);
        //............ EUROPA ............
        satelite = new Satelite("Europa", 0.3f, 2000, 1000, "imgs/europa.jpg", materialSatelite, new Vector3d (3f, 0.0f, 0.0f));
        planeta.añadirSatelite(satelite);     
        //............ CALISTO ............
        satelite = new Satelite("Calisto", 0.3f, 2000, 1000, "imgs/calisto.jpg", materialSatelite, new Vector3d (4f, 0.0f, 0.0f));
        planeta.añadirSatelite(satelite);   
        //------------ JÚPITER -----------------       
        
        //############## SATURNO //##############
        planeta = new Planeta("Saturno", 1.5f, 0, -7000, "imgs/saturno.jpg", materialPlaneta, new Vector3d (28.0f, 0.0f, 0.0f));
        sol.añadirPlaneta(planeta);
        //............ Anillo C ............        
        // AnilloPlanetario(nombre, radioInterior, radioExterior, velR, nombreImagen, material) 
        anillo = new AnilloPlanetario("Anillo A", 1.8f, 2.3f, 6000, "imgs/anillo_c.jpg", materialAnillo);
        planeta.añadirAnillo(anillo);        
        //............ Anillo B ............
        anillo = new AnilloPlanetario("Anillo A", 2.4f, 2.9f, 2000, "imgs/anillo_b.jpg", materialAnillo);
        planeta.añadirAnillo(anillo);                
        //............ Anillo A ............      
        anillo = new AnilloPlanetario("Anillo A", 3.1f, 4f, 9000, "imgs/anillo_a.jpg", materialAnillo);
        planeta.añadirAnillo(anillo);                
        //------------ SATURNO -----------------       
        
        //############## URANO //##############
        planeta = new Planeta("Urano", 1.5f, 9000, -7000, "imgs/urano.jpg", materialPlaneta,new Vector3d (32.0f, 0.0f, 0.0f));
        sol.añadirPlaneta(planeta);        
        //............ TITANIA ............
        satelite = new Satelite("Titania", 0.3f, 500, 1000, "imgs/titania.jpg", materialSatelite, new Vector3d (2f, 0.0f, 0.0f));
        planeta.añadirSatelite(satelite);
        //............ ARIEL ............
        satelite = new Satelite("Ariel", 0.3f, 3000, 1000, "imgs/ariel.jpg", materialSatelite, new Vector3d (3f, 0.0f, 0.0f));
        planeta.añadirSatelite(satelite);     
        //............ MIRANDA ............
        satelite = new Satelite("Miranda", 0.3f, 1500, 1000, "imgs/miranda.jpg", materialSatelite, new Vector3d (4f, 0.0f, 0.0f));
        planeta.añadirSatelite(satelite);   
        //------------ URANO -----------------       
        
        //############## NEPTUNO //##############
        planeta = new Planeta("Neptuno", 1.5f, 5000, -7000, "imgs/neptuno.jpg", materialPlaneta, new Vector3d (36.0f, 0.0f, 0.0f));
        sol.añadirPlaneta(planeta);        
        //............ TRITÓN ............
        satelite = new Satelite("Tritón", 0.3f, 2000, 1000, "imgs/triton.jpg", materialSatelite, new Vector3d (2f, 0.0f, 0.0f));
        planeta.añadirSatelite(satelite);          
        //------------ NEPTUNO -----------------               
                
    }

}

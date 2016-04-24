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
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
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
        //bg = new BranchGroup();
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
        
        //tr.addChild(modelo.getSceneGroup());
        //bg.addChild (tr);
        this.addChild (modelo.getSceneGroup());
    }
    
    /*
    public BranchGroup getBg() {
        return this.bg;
    }
    */
    
    public void añadirVista(Vista vista){
        this.vista = vista;
        this.addChild(vista);
    }    
    
}

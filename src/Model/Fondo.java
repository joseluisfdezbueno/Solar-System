/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Texture;
import javax.vecmath.Point3d;

/**
 *
 * @author Rogelio García Peña
 * @author José Luis Fernandez Bueno
 */
public class Fondo extends BranchGroup{
    private final Background fondo;
    private final Appearance app;
    private final Texture texture;
    private final Primitive sphere;
    private final BranchGroup entorno;
    
    public Fondo(){                
        
        // Se carga la textura y se introduce en la aparencia
        this.app = new Appearance ();
        this.texture = new TextureLoader ("imgs/fondo1.jpg", null).getTexture();
        this.app.setTexture (this.texture);
        
        // se crea la geometría donde pegaremos el fondo ya con sus textura
        this.sphere = new Sphere (1.0f, Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS_INWARD, this.app);

        // introducimos la esfera en la rama entorno
        this.entorno = new BranchGroup();
        this.entorno.addChild (this.sphere);
        
        // creamos el fondo pasándole su entorno y generamos su campo de aplicación
        this.fondo = new Background(this.entorno);
        this.fondo.setApplicationBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 1000000.0));
        
        // introducimos el fondo en el grupo
        this.addChild(this.fondo);
    }
}

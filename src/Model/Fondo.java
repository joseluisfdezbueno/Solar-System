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
    private final Background bg;
    private  Appearance app;
    private final Texture texture;
    private final Primitive sphere;
    private final BranchGroup bgGeometry;
    
    public Fondo(){                
        
        // Se carga la textura y se introduce en la aparencia
        this.app = new Appearance ();
        this.texture = new TextureLoader ("imgs/fondo.jpg", null).getTexture();
        this.app.setTexture (this.texture);
        
        this.sphere = new Sphere (40.0f, Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS_INWARD, this.app);

        this.bg = new Background();
        this.bg.setApplicationBounds (new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 10000.0));
        
        this.bgGeometry = new BranchGroup ();
        this.bgGeometry.addChild (this.sphere);
        this.bg.setGeometry (this.bgGeometry);
        
        
        this.addChild (this.bg);
                
                
    }
}

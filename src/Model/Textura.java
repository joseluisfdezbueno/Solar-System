/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Color3f;

/**
 *
 * @author Rogelio García Peña
 * @author José Luis Fernandez Bueno
 */
public class Textura{
    private final Texture textura;
    private final Appearance ap;
    private final TextureAttributes ta;
    
    public Textura (String nombre, Material material){
        this.ap = new Appearance();
        this.textura = new TextureLoader (nombre, null).getTexture();
        this.ap.setTexture (textura);
        this.ap.setMaterial (material);
        this.ta = new TextureAttributes();
        this.ta.setTextureMode(TextureAttributes.MODULATE);
        this.ap.setTextureAttributes(ta);
    }
    
    public Appearance getAppearance(){
        return this.ap;
    }
    
    public void modificarMaterial(Color3f ambiental, Color3f emisivo, Color3f difuso, Color3f especular, float brillo){
        this.ap.getMaterial().setAmbientColor(ambiental);
        this.ap.getMaterial().setEmissiveColor(emisivo);
        this.ap.getMaterial().setDiffuseColor(difuso);
        this.ap.getMaterial().setSpecularColor(especular);  
        this.ap.getMaterial().setShininess(brillo);          
    }        
}

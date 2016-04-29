/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.geometry.Stripifier;
import java.util.ArrayList;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TexCoordGeneration;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector4f;

/**
 *
 * @author Rogelio García Peña
 * @author José Luis Fernandez Bueno
 */
public class AnilloPlanetario extends Shape3D{
    private String nombre;
    private float radioInterior;
    private float radioExterior;
    private int velR;
    private String rutaImagen;
    private Material material;
    private Vector3d distanciaReferencia;
    private Textura textura;    
    private Rotacion rotacionPropia;
                     
    public AnilloPlanetario(String nombre, float radioInterior, float radioExterior, int velR, String rutaImagen, Material material){
        this.nombre = nombre;
        this.radioInterior = radioInterior;
        this.radioExterior = radioExterior;
        this.velR = velR;
        this.rutaImagen = rutaImagen;
        this.material = material;              
        
        /*
// Set up the texture map
        TextureLoader loader = new TextureLoader(text, "RGB", new Container());
        Texture texture = loader.getTexture();
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.0f, 0.0f));
        // Set up the texture attributes

        // Set up colors
        Color3f black = new Color3f(0.2f, 0.2f, 0.2f);
        Color3f white = new Color3f(0.75f, 0.75f, 0.75f);

        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);
        Appearance ap = new Appearance();
        ap.setTexture(texture);
        ap.setTextureAttributes(texAttr);
        ap.setMaterial(new Material(black, black, white, black, 0.0f));
*/
        this.textura = new Textura(rutaImagen, material);
        //textura.getAppearance();
        
        // angulo con el que formaremos las circunferencias
        double angle = (Math.PI * 2.0f)/100;
        
        // Un polígono de 2 contornos: la circunferencia exterior y el hueco interior
        int[] contornosPorPoligono = {2};
        int[] verticesPorContorno = {100, 100};
        
        ArrayList<Point3f> verticesAnillo = new ArrayList<Point3f>();
        Transform3D t1 = new Transform3D();
        Transform3D t2 = new Transform3D();
        t1.rotY(angle);
        Point3f vertice = new Point3f(radioExterior, 0, 0);
        verticesAnillo.add(new Point3f(vertice));
        
        // Generamos el resto de vértices del circulo exterior mediante rotación
        for (int i = 1; i < 100; i++) {
            t1.transform(vertice);
            verticesAnillo.add(new Point3f(vertice));
        }
        
        
        t2.rotY(-angle);
        vertice.set(radioInterior, 0, 0);
        verticesAnillo.add(new Point3f(vertice));
        // Generamos el resto de vértices del circulo interior mediante rotación
        for (int j = 1; j < 100; j++) {
            t2.transform(vertice);
            verticesAnillo.add(new Point3f(vertice));
        }
        
        Point3f[] aa = new Point3f[100 + 100];
        
        // Se genera la geometría
        GeometryInfo gi = new GeometryInfo(GeometryInfo.POLYGON_ARRAY);
        gi.setCoordinates(verticesAnillo.toArray(aa));
        gi.setContourCounts(contornosPorPoligono);
        gi.setStripCounts(verticesPorContorno);
        
        //Se generan las normales. Deben generarse antes que las cadenas de triángulos
        NormalGenerator normGen = new NormalGenerator(Math.toRadians(30));;
        normGen.generateNormals(gi);
        //Se generan las cadenas de triángulos
        Stripifier cadenas = new Stripifier();
        cadenas.stripify(gi);
        // Se obtiene la geometría
        GeometryArray geometria = gi.getGeometryArray();
        // Le damos sus coordenadas de textura
        TexCoordGeneration textCoorder = new TexCoordGeneration(TexCoordGeneration.OBJECT_LINEAR, TexCoordGeneration.TEXTURE_COORDINATE_2, new Vector4f(1.0f, 0.0f, 0.0f, 0.0f), new Vector4f(0.0f, 0.0f, 1.0f, 0.0f));
        //TexCoordGeneration textCoorder = new TexCoordGeneration(TexCoordGeneration.OBJECT_LINEAR, TexCoordGeneration.TEXTURE_COORDINATE_2);
        
        textura.getAppearance().setTexCoordGeneration(textCoorder);
        this.setGeometry(geometria);
        this.setAppearance(textura.getAppearance());        
        
        rotacionPropia = new Rotacion("y",velR);
        rotacionPropia.addChild(this);
 
//--------------------------------------------------------------------------------------//        
        /*
        AnilloShape abajo = new AnilloShape(ExteriorRadio, InteriorRadio, 20, ap);
        AnilloShape arriba = new AnilloShape(ExteriorRadio, InteriorRadio, 80, ap);

        TransformGroup objTrans1 = new TransformGroup();
        Transform3D t1 = new Transform3D();
        t1.rotX(Math.PI);
        objTrans1.setTransform(t1);
        
        //creamos una separacion entre ambos para que no este uno sobre otro. 
        TransformGroup objTrans2 = new TransformGroup();
        Transform3D t2 = new Transform3D();
        t2.setTranslation(new Vector3f(0f, 1f, 0f));
        objTrans2.setTransform(t2);
 
        this.addChild(objTrans1);
        objTrans1.addChild(objTrans2);
        objTrans2.addChild(abajo);
        
        this.addChild(arriba);*/
    }        
    public Rotacion getRotacion(){
        return this.rotacionPropia;
    }
}

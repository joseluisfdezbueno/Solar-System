/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.media.j3d.Canvas3D;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 *
 * @author Jose
 */
public class Vista extends TransformGroup{
    private boolean activada;
    private View view;
    private Canvas3D canvas;
    
    Vista(Canvas3D canvas){
        this.canvas = canvas;
    }
    
    public void crearVPlanta(Point3d posicion, Point3d dondeMirar, Vector3d vup, float escala, 
            float planoDelantero, float planoTrasero){
        
        // TransformGroup para posicionar y orientar la vista
        Transform3D transformPlanta = new Transform3D();
        transformPlanta.lookAt(posicion, dondeMirar, vup);
        transformPlanta.invert();
                
        this.setTransform(transformPlanta);
        
        ViewPlatform vpPlanta = new ViewPlatform();
        this.addChild (vpPlanta);
        
        // Definición de la vista paralela
        view = new View();
        view.setPhysicalBody(new PhysicalBody());
        view.setPhysicalEnvironment (new PhysicalEnvironment());
        
        // ajustamos parámetros
        view.setProjectionPolicy(View.PARALLEL_PROJECTION);
        view.setScreenScalePolicy (View.SCALE_EXPLICIT);
        view.setScreenScale(0.01);
        view.setFrontClipDistance(planoDelantero); //0.1
        view.setBackClipDistance(planoTrasero); // 20
        
        // enlazamos view con el ViewPlatform
        view.attachViewPlatform(vpPlanta);          
            
    }

    /*
    public void crearVPerspectiva(){
        // Se crea manualmente un ViewingPlatform para poder personalizarlo y asignárselo al universo
        ViewingPlatform viewingPlatform = new ViewingPlatform();

        // La transformación de vista, dónde se está, a dónde se mira, Vup
        TransformGroup viewTransformGroup = viewingPlatform.getViewPlatformTransform();
        Transform3D viewTransform3D = new Transform3D();
        viewTransform3D.lookAt (new Point3d (60,60,60), new Point3d (0,0,0), new Vector3d (0,1,0));
        viewTransform3D.invert();
        viewTransformGroup.setTransform (viewTransform3D);

        // El comportamiento, para mover la camara con el raton
        OrbitBehavior orbit = new OrbitBehavior(canvas, OrbitBehavior.REVERSE_ALL);
        orbit.setSchedulingBounds(new BoundingSphere(new Point3d (0.0f, 0.0f, 0.0f), 100.0f));
        orbit.setZoomFactor (2.0f);
        viewingPlatform.setViewPlatformBehavior(orbit);

        // Se establece el angulo de vision a 45 grados y el plano de recorte trasero
        Viewer viewer = new Viewer (canvas);
        View view = viewer.getView();
        view.setFieldOfView(Math.toRadians(45));
        view.setBackClipDistance(50.0);
        
    }    
    */
    
    public void crearVPerspSujetiva(Point3d posicion, Point3d dondeMirar, Vector3d vup, float anguloApertura, 
            float planoDelantero, float planoTrasero){
                   //new Point3d(2,0,0), new Point3d(0,0,0), new Vector3d(0,1,0));    
        // TransformGroup para posicionar y orientar la vista
        Transform3D transformPersp = new Transform3D();
        transformPersp.lookAt(posicion, dondeMirar, vup);
        transformPersp.invert();
        
        this.setTransform(transformPersp);
        
        ViewPlatform vpPersp = new ViewPlatform();
        this.addChild(vpPersp);
        
        // Definición de la vista en perspectiva
        view = new View();
        view.setPhysicalBody(new PhysicalBody());
        view.setPhysicalEnvironment(new PhysicalEnvironment());
        // la ajustmos
        view.setProjectionPolicy(View.PERSPECTIVE_PROJECTION);
        view.setFieldOfView(Math.toRadians(anguloApertura)); // 45
        view.setFrontClipDistance(planoDelantero); // 0.1
        view.setBackClipDistance(planoTrasero); // 35

        // enlazamos view con el ViewPlatform        
        view.attachViewPlatform(vpPersp);             
    }        
    
    public void setCanvas(Canvas3D canvas){
        this.canvas = canvas;
    }
    
    public Canvas3D getCanvas(){
        return this.canvas;
    }    
        
    public void habilitar(){
        // enlazamos el canvas al view
        if(!activada){
            view.addCanvas3D(this.canvas);
            activada = true;
        }
    }
    
    public void deshabilitar(){
        // eliminamos el enlace entre el canvas y el view
        if(activada){ // Si está activa
            view.removeCanvas3D(this.canvas); // Se quita el Canvas al View
            activada = false;
        }
    }
    
    
    
}
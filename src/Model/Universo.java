/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.Viewer;
import com.sun.j3d.utils.universe.ViewingPlatform;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Node;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 *
 * @author Rogelio García Peña
 * @author José Luis Fernandez Bueno
 */
public class Universo extends BranchGroup{
    
    private Escena escena;
    private Fondo fondo;
    private Luz luz;
    private Nave nave;
    private Pick pick;
    private Vista vistaPlanta, vistaLuna, vistaNave;
    
    private View view;
    private final Canvas3D canvas;
    private final SimpleUniverse universe;    
    
    public Universo(Canvas3D canvasPlanta, Canvas3D canvasVariable){

        this.canvas = canvasVariable;
                
        // ---------- VISTAS -------------- //
        
        // Creamos la vista en planta y la enlazamos al BG universo
        vistaPlanta = new Vista(canvasPlanta);
        //crearVPlanta(posicion, dondeMirar, vup, escala, planoDelantero, planoTrasero)
        vistaPlanta.crearVPlanta(new Point3d(0,600,0), new Point3d(0,0,0), new Vector3d(0,0,-1), 0.0022f, 0.3f, 200f);
        this.addChild(vistaPlanta);
        vistaPlanta.habilitar();
        
        // creamos la lista subjetiva posicionada en la luna
        vistaLuna = new Vista(canvasVariable);
        //crearVPlanta(posicion, dondeMirar, vup, angulo, planoDelantero, planoTrasero)
        vistaLuna.crearVPerspSujetiva(new Point3d(0.1f,0.8f,0f), new Point3d(-2,0,0), new Vector3d(0,1,0), 100f, 0.1f, 35f);
        
        // creamos la lista subjetiva posicionada al frente de la nave
        vistaNave = new Vista(canvasVariable);
        vistaNave.crearVPerspSujetiva(new Point3d(0,1.5,-1.5), new Point3d(0,0,3), new Vector3d(0,1,0), 60f, 0.1f, 35f);
        // ----------- FIN VISTAS  ------------- //
        
                
        // --- CREAMOS EL RESTO DE RAMAS ----- //        
        
        // Creamos y enlazamos el fondo
        this.fondo = new Fondo();
        this.addChild(fondo);
        
        // Creamos y enlazamos la luz
        this.luz = new Luz();
        this.addChild(luz.crearLuzAmbiental(new Color3f (0.2f, 0.2f, 0.2f)));
        
        // Creamos y enlazamos la escena        
        escena = new Escena(vistaLuna);
        this.addChild(this.escena);

        
        // -------------- NAVE ------------------ //
        nave = new Nave();         
        nave.añadirVista(vistaNave);
        this.addChild(nave.inicializarRuta());
    
        // --------------- PICK ------------------ //
        
        // Activamos la capacidad de ser seleccionado los elementos de la rama escena
        this.escena.setCapability(Node.ENABLE_PICK_REPORTING);
        this.escena.setPickable(true);
        pick = new Pick(canvasVariable, escena);
        this.escena.addChild(pick);
                    
        // ------------- FIN PICK ----------------- //
        
        
        // Se crea un SimpleUniverse con la vista en perspectiva por defecto
        this.universe = createUniverse (canvasVariable);
                                       
        // Se optimiza nuestro "universo" y se cuelga del simple universe
        this.compile();
        this.universe.addBranchGraph(this);
    }
    
    // creación del universo, a través de un simple universo, con la vista modificada y con comportamiento para la interacción con el ratón
    private SimpleUniverse createUniverse (Canvas3D canvas) {
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

        // Configuramos el view
        Viewer viewer = new Viewer (canvas);
        this.view = viewer.getView();
        this.view.setProjectionPolicy(View.PERSPECTIVE_PROJECTION);        
        this.view.setFieldOfView(Math.toRadians(45));
        this.view.setBackClipDistance(50.0);
        this.view.setFrontClipDistance(0.1); 

        // Se construye y devuelve el Universo con los parametros definidos
        return new SimpleUniverse (viewingPlatform, viewer);
    }

    // método que activa la vista en perspectiva creada por defecto    
    public void activarVistaPerspectiva(){
        // solo si la vista no tiene ningun canvas, podemos agregarle uno
       if(view.numCanvas3Ds() == 0){
            vistaNave.deshabilitar();
            vistaLuna.deshabilitar();
            view.addCanvas3D(canvas);
        } 
    }

    // método que activa la vista de la luna
    public void activarVistaLuna(){
        view.removeAllCanvas3Ds();        
        vistaNave.deshabilitar();
        vistaLuna.habilitar();
    }
    
    // método que activa la vista de la nave
    public void activarVistaNave(){
        view.removeAllCanvas3Ds();
        vistaLuna.deshabilitar();
        vistaNave.habilitar();        
    }    
    
    // método para cerrar la aplicación
    public void closeApp(int code){
        System.exit(code);
    }
    
}

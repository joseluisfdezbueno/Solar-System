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
import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Node;
import javax.media.j3d.RotPosPathInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3d;

/**
 *
 * @author Rogelio García Peña
 * @author José Luis Fernandez Bueno
 */
public class Universo extends BranchGroup{
    
    private Escena escena;
    private Fondo fondo;
    private BranchGroup root;
    private SimpleUniverse universe;    
    private Luz luz;
    private Nave nave;
    private Pick pick;
    private Vista vistaPlanta, vistaLuna, vistaNave;
    
    private View view;
    private Canvas3D canvas;
    
    public Universo(Canvas3D canvasPlanta, Canvas3D canvasVariable){

        this.canvas = canvasVariable;
                
        // ---------- VISTAS --------------
        // bg donde enlazamos la vista en planta
        BranchGroup bgPlanta = new BranchGroup();
        
        // Creamos la vista en planta, la enlazamos a un BG y la habilitamos
        vistaPlanta = new Vista(canvasPlanta);
        //crearVPlanta(posicion, dondeMirar, vup, escala, planoDelantero, planoTrasero)
        vistaPlanta.crearVPlanta(new Point3d(0,40,0), new Point3d(0,0,0), new Vector3d(0,0,-1), 0.01f, 0.1f, 20f);
        bgPlanta.addChild(vistaPlanta);
        vistaPlanta.habilitar();
    //    this.universe.addBranchGraph(bgPlanta);
        
        // creamos la lista subjetiva posicionada en la luna
        vistaLuna = new Vista(canvasVariable);
        //crearVPlanta(posicion, dondeMirar, vup, angulo, planoDelantero, planoTrasero)
        vistaLuna.crearVPerspSujetiva(new Point3d(2,0,0), new Point3d(0,0,0), new Vector3d(0,1,0), 45f, 0.1f, 35f);
        
        // creamos la lista subjetiva posicionada al frente de la nave
        vistaNave = new Vista(canvasVariable);
        vistaNave.crearVPerspSujetiva(new Point3d(2,0,0), new Point3d(0,0,0), new Vector3d(0,1,0), 45f, 0.1f, 35f);
        // ----------- FIN VISTAS  -------------
        
        // creamos la escena
        escena = new Escena(vistaLuna);
        
        // bg de la clase universo
        this.root = new BranchGroup();
                
        // Creamos y enlazamos el fondo
        this.fondo = new Fondo();
        this.root.addChild(fondo);
        
        // Creamos y enlazamos la luz
        this.luz = new Luz();
        this.root.addChild(luz.crearLuzAmbiental(new Color3f (0.2f, 0.2f, 0.2f)));
        
        // Enlazamos la escena
        this.root.addChild(this.escena.getBg());

        
//############## NAVE //############################################
        nave = new Nave();
         
        // Selección del camino a seguir por la nave, y su orientación
        //RotPosPathInterpolator(alpha, ramaAAplicar, transformacion, rotaciones, posiciones);                
    Alpha alpha = new Alpha( -1, Alpha.INCREASING_ENABLE, 0,0,20000,0,0,0,0,0 );
    TransformGroup tg = new TransformGroup();                
    Transform3D transformacion = new Transform3D();
    float[] alphas = {0.0f, 0.20f, 0.30f, 0.40f, 0.55f, 0.70f, 0.80f, 0.90f, 1.0f};
    Quat4f[] quats = new Quat4f[9];
    Point3f[] positions = new Point3f[9];

    tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

    quats[0] = new Quat4f(0.0f, 1.0f, 0.0f, (float) (Math.PI));
    quats[1] = new Quat4f(0.0f, 1.0f, 0.0f, (float) (Math.PI)*1.5f);
    quats[2] = new Quat4f(0.0f, 1.0f, 0.0f, (float) (Math.PI)*0);
    quats[3] = new Quat4f(0.0f, 1.0f, 0.0f, (float) (Math.PI)*0);
    quats[4] = new Quat4f(0.0f, 1.0f, 0.0f, (float) (Math.PI)*0);
    quats[5] = new Quat4f(0.0f, 1.0f, 0.0f, (float) (Math.PI)*0);
    quats[6] = new Quat4f(0.0f, 1.0f, 0.0f, (float) (Math.PI)/2);
    quats[7] = new Quat4f(0.0f, 1.0f, 0.0f, (float) (Math.PI)/2);
  //  quats[8] = new Quat4f(0.0f, 1.0f, 0.0f, (float) (Math.PI)*0);
    quats[8] = quats[0];
    
    positions[0]= new Point3f(25.0f,  0.0f, 0.0f);
    positions[1]= new Point3f(25.0f, 0.0f, 25.0f);
    positions[2]= new Point3f(0.0f,  0.0f, 25.0f);
    positions[3]= new Point3f(-25.0f,  0.0f, 25.0f);
    positions[4]= new Point3f(-25.0f,  0.0f, 0.0f);
    positions[5]= new Point3f(-25.0f,  0.0f, -25.0f);
    positions[6]= new Point3f(0.0f, 0.0f, -25.0f);
    positions[7]= new Point3f(25.0f,  0.0f, -25.0f);
    //positions[8]= new Point3f(8.0f,  0.0f, 8.0f);
    positions[8]= positions[0];

    RotPosPathInterpolator rotPosPath = new RotPosPathInterpolator(alpha, tg, transformacion, alphas, quats, positions);
        
    rotPosPath.setSchedulingBounds(new BoundingSphere(new Point3d(), 10000.0));
        nave.añadirVista(vistaNave);
        tg.addChild(nave);
        tg.addChild(rotPosPath);
        this.root.addChild(tg);
      //  this.root.addChild(nave.getBg());
//------------ NAVE ------------------------------------------------------
    
        // pick
                // Activamos la capacibilidad de ser seleccionado
        escena.getBg().setCapability(Node.ENABLE_PICK_REPORTING);
        escena.getBg().setPickable(true);
        pick = new Pick(canvasVariable, escena.getBg());
        escena.getBg().addChild(pick);
        
        // ########### VISTAS ##############
        
        // Se crea el universo. La parte de la vista
        this.universe = createUniverse (canvasVariable);
        
        this.universe.addBranchGraph(bgPlanta);        
                        
        // Se optimiza la escena y se cuelga del universo
        this.root.compile();
        this.universe.addBranchGraph(this.root);
    }
    
    // creación del universo
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

        // Se establece el angulo de vision a 45 grados y el plano de recorte trasero
        Viewer viewer = new Viewer (canvas);
        view = viewer.getView();
        view.setFieldOfView(Math.toRadians(45));
        view.setBackClipDistance(50.0);

        // Se construye y devuelve el Universo con los parametros definidos
        return new SimpleUniverse (viewingPlatform, viewer);
    }
    
    public void activarVistaPerspectiva(){
       // if(view.getCanvas3D(0) == null){
            vistaNave.deshabilitar();
            vistaLuna.deshabilitar();
            view.addCanvas3D(canvas);
        //} 
    }
    
    public void activarVistaLuna(){
        //view.removeCanvas3D(canvas);
        view.removeAllCanvas3Ds();        
        vistaNave.deshabilitar();
        vistaLuna.habilitar();
    }
    
    public void activarVistaNave(){
        //view.removeCanvas3D(canvas);
        view.removeAllCanvas3Ds();
        vistaLuna.deshabilitar();
        vistaNave.habilitar();        
    }    
    
    // método para cerrar la aplicación
    public void closeApp(int code) {
        System.exit (code);
    }
    
}

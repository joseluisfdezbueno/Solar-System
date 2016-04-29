/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import com.sun.j3d.utils.pickfast.PickCanvas;
import java.awt.AWTEvent;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.media.j3d.Behavior;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PickInfo;
import javax.media.j3d.SceneGraphPath;
import javax.media.j3d.WakeupCondition;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.vecmath.Point3d;



/**
 *
 * @author Jose
 */


public class Pick extends Behavior{
    private final WakeupCondition condicion;
    private final PickCanvas pickCanvas ;
    private final Canvas3D canvas ;
    
    public Pick (Canvas3D canvas, BranchGroup rama){
        this.canvas = canvas;
        condicion = new WakeupOnAWTEvent(MouseEvent.MOUSE_CLICKED);
        pickCanvas = new PickCanvas(canvas, rama);
        this.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 10000.0));
    }
    
    @Override
    public void initialize(){
        // Activamos la funcionalidad
        setEnable(true);
        wakeupOn(condicion);
    }
    
    @Override
    public void processStimulus(Enumeration cond){
        SceneGraphPath camino = new SceneGraphPath();
        Rotacion rotacion;
        
        WakeupOnAWTEvent c = (WakeupOnAWTEvent) cond.nextElement();
        AWTEvent[] e = c.getAWTEvent();
        MouseEvent raton = (MouseEvent) e[0];
                
        switch (raton.getID()){
            case MouseEvent.MOUSE_CLICKED:
                pickCanvas.setShapeLocation(raton);
                PickInfo pi = pickCanvas.pickClosest();
                if(pi != null){
                   System.out.println(pi.getNode().getParent().getParent());
                   rotacion = (Rotacion) pi.getNode().getParent().getParent(); // shape3d -> sphere -> TransformGroup
                   rotacion.pararReanudar();
                }
                break;
            default:
                System.out.println("Fallo al hacer pick");                         
        }
        
        // Establecer de nuevo la condición de respuesta
        wakeupOn(condicion);
    }
    
}
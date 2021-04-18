package org.yourorghere;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;


/**
 * GLRenderer.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class GLRenderer implements GLEventListener {
public boolean arranque = false;
public double theta_hora=0.0;
public double theta_dia=0.0;


    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!
        
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        //glu.gluPerspective(45.0f, h, 1.0, 20.0);
        glu.gluOrtho2D(-800, 800, -800, 800);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
public void DibujarCirculo(GLAutoDrawable drawable, double radio , double RR , double GG , double BB){
    GL gl= drawable.getGL();
    double _radio=radio;
    float calx,caly;
    //gl.glBegin(GL.GL_POLYGON);
    gl.glBegin(GL.GL_TRIANGLE_FAN);
      // gl.glColor3d(RR, GG, BB);
      //gl.glVertex3f(0 , 0 , 0);
        for (float i = 0; i < 10; i+=0.01) {
           calx=(float) (_radio*cos(i));
           caly=(float) (_radio*sin(i));
           gl.glColor3d(RR-(i*70), GG-(i*70), BB-(i*70)*2);
            gl.glVertex3f(calx , caly , 0);
        }
 
    gl.glEnd();
    }
public void DibujarCirculoliso(GLAutoDrawable drawable, double radio , double RR , double GG , double BB){
    GL gl= drawable.getGL();
    double _radio=radio;
    float calx,caly;
    gl.glBegin(GL.GL_POLYGON);
      // gl.glColor3f(RR, GG, BB);
        for (float i = 0; i < 10; i+=0.01) {
           calx=(float) (_radio*cos(i));
           caly=(float) (_radio*sin(i));
           gl.glColor3d(RR, GG, BB);
            gl.glVertex3f(calx , caly , 0);
        }
 
    gl.glEnd();
    }

public void trazarEjes(GLAutoDrawable drawable){
    GL gl = drawable.getGL();
        //eje x = rojo
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(-6000.0f, 0.0f, 0.0f);
        gl.glVertex3f(6000.0f, 0.0f, 0.0f);
        gl.glEnd();
        //eje y = verde
        gl.glColor3f(0.0f,1.0f,0.0f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.0f, -6000.0f, 0.0f);
        gl.glVertex3f(0.0f, 6000.0f, 0.0f);
        gl.glEnd();
        // eje z = azul
        gl.glColor3f(0.0f,0.0f,1.0f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.0f, 0.0f, -5000.0f);
        gl.glVertex3f(0.0f, 0.0f, 5000.0f);
        gl.glEnd(); 
        
       
    }


    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        
        trazarEjes(drawable);
       gl.glColor3f(1.0f, 0.0f, 0.0f);
            gl.glLineWidth(2);
            
            
        gl.glPushMatrix();
                     gl.glRotated(theta_dia*360/25, 0, 0, 1);
                     DibujarCirculo(drawable , 50 , 255 , 255 , 0);//sol
        gl.glPopMatrix();
        gl.glPushMatrix();
                gl.glRotated(theta_dia*360/365, 0 , 0, 1);
                gl.glTranslated(190, 0, 0); 
            gl.glPushMatrix();
                    gl.glRotated(theta_hora*360/24, 0, 0, 1);
                    DibujarCirculo(drawable , 35 , 0 , 0 , 255);//tierra
            gl.glPopMatrix();
            gl.glRotated(12*theta_dia*360/365, 0, 0, 1);
            gl.glTranslated(45, 0, 0);
             gl.glRotated(0.5*theta_hora*360/24, 0, 0, 1);
            DibujarCirculo(drawable , 10 , 100 , 255 , 255);//luna
        gl.glPopMatrix();
        
        gl.glRotated(theta_dia*360/88, 0, 0, 1);
        gl.glTranslated(90, 0, 0);
        gl.glRotated(0.5*theta_hora*360/12, 0, 0, 1);
        DibujarCirculo(drawable , 20 , 28 , 223 , 244);//mercurio
        
        gl.glLoadIdentity();
        gl.glRotated(theta_dia*360/687, 0, 0, 1);
        gl.glTranslated(320, 0, 0);
        gl.glRotated(0.5*theta_hora*360/24, 0, 0, 1);
        DibujarCirculo(drawable , 40 , 255 , 0 , 0);//marte
        
        gl.glLoadIdentity();
        gl.glRotated(theta_dia*360/1753, 0, 0, 1);
        gl.glTranslated(500, 0, 0);
        DibujarCirculo(drawable , 70 , 255 , 250 , 0);//anillo
        gl.glRotated(0.5*theta_hora*360/24, 0, 0, 1); 
      //DibujarCirculoliso(drawable , 55 , 0 , 0 , 0);//espacio negro
        DibujarCirculoliso(drawable , 55 , 255 , 255 , 255);//espacio blanco
        DibujarCirculo(drawable , 40 , 206 , 184 , 184);//saturno
        
       
                
        
        gl.glFlush();
    }

    
    
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

   
}


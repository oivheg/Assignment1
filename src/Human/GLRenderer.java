package Human;

import com.jogamp.opengl.util.texture.Texture;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.glu.GLU;

/**
 * author: bs
 */
public class GLRenderer implements GLEventListener
{
    // the horse
   
    Man theMan=null;
    Ground ground = null;
    // mousing
    float m_rotZ=0.0f;
    float m_rotY=0.0f;
    boolean m_isWalking=false;
    
    GLAutoDrawable this_drawable=null;
    
        @Override
    public void init(GLAutoDrawable drawable) {
        this_drawable=drawable;
        GL2 gl = drawable.getGL().getGL2();
        // Enable VSync
        gl.setSwapInterval(1); 
        // set up lighting
        float ambient[] = {0.5f,0.5f,0.5f,1.0f };
        float diffuse[] = {1.0f,1.0f,1.0f,1.0f };
        float position[] = {1.0f,1.0f,1.0f,0.0f };
        float lmodel_ambient[] = {0.4f,0.4f,0.4f,1.0f };
        
        gl.glLightfv(GLLightingFunc.GL_LIGHT0, GLLightingFunc.GL_AMBIENT, ambient,0);
        gl.glLightfv(GLLightingFunc.GL_LIGHT0, GLLightingFunc.GL_DIFFUSE, diffuse,0);
        gl.glLightfv(GLLightingFunc.GL_LIGHT0, GLLightingFunc.GL_POSITION, position,0);
        
        gl.glEnable(GLLightingFunc.GL_LIGHT0);
        gl.glEnable(GLLightingFunc.GL_LIGHTING);
   
         
          
       
    gl.glEnable(GL.GL_DEPTH_TEST);
       
        
        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GLLightingFunc.GL_SMOOTH);
        // set up the horse
       
        theMan=new Man();
        ground=new Ground();
        //load textures
      
       theMan.skin = LoadTexture.addTexture("HumanSkin.jpg");
       theMan.race = LoadTexture.addTexture("Face.jpg");
       theMan.neck = LoadTexture.addTexture("neck.png");
       theMan.shoulder = LoadTexture.addTexture("shoulder1.png");
       theMan.body = LoadTexture.addTexture("body.png");
       theMan.waist = LoadTexture.addTexture("bodyl.png");
       theMan.hips = LoadTexture.addTexture("hips.png");
       theMan.kne = LoadTexture.addTexture("kne.png");
       theMan.legs = LoadTexture.addTexture("legs.png");
       theMan.feet = LoadTexture.addTexture("HumanSkin.jpg");
       ground.Grass = LoadTexture.addTexture("grass.jpg");
       ground.ground = LoadTexture.addTexture("grass.jpg");
      
         
    }
    public void reshape(GLAutoDrawable drawable, int x, int y, 
            int width, int height)
    {
  GL2 gl = drawable.getGL().getGL2();
  
  GLU glu = new GLU();
        if (height <= 0) {  height = 1;}
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(60.0f, h, 1.0, 50.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2(); 
        GLU glu=new GLU();
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT );
        gl.glClear(GL2.GL_ACCUM_BUFFER_BIT | GL.GL_STENCIL_BUFFER_BIT );
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        glu.gluLookAt(0.0f, 5.0f, 3.0,
                      0.0f, 0.0f, 0.0f,
                      0.0f, 0.0f, 1.0f);
        // do basic mousing
        gl.glRotatef(m_rotY, 0.0f, 1.0f,0.0f);
        gl.glRotatef(m_rotZ, 0.0f, 0.0f,1.0f);
        // draw the horse
        //sky.drawSkyBox(gl);
       
        ground.drawMe(gl);
        theMan.drawMe(gl);
        
        
        
       
        
        // Flush all drawing operations to the graphics card
        gl.glFlush();
       if(m_isWalking)
            theMan.updateAngles();
    }
    public void displayChanged(GLAutoDrawable drawable,
            boolean modeChanged, boolean deviceChanged) {
    }
    public void setBasicRotation(float z,float y)
    {
        m_rotZ=z;
        m_rotY=y;
    }
    public void setHeadAngles(float v1,float v2)
    {
   
    }
    public void  doWalk(boolean on)
    {
        m_isWalking=on;
    }

    @Override
    public void dispose(GLAutoDrawable glad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
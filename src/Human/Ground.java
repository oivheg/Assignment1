package Human;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;
import java.io.File;
import java.io.IOException;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GLException;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import java.lang.Object;



/**
 * @author bs
 */
public class Ground 
{
    
    
    // static dimensions
    
    // eof static dimensions
    // angles
    // the horse know how to walk ?
   
    // eof angles



    public Ground()
    {
        resetAngles(); 
    }
     private static final int SLICES=20;
    private static final int STACKS=10;
     public Texture Grass ;
    
     public Texture ground;
    GLU glu = null;
    GL2 gl = null;
    GLUquadric q=null;
    boolean test= true;
    public  void drawMe(GL2 gl2)
    {
        
         gl=gl2;
         glu=new GLU();
         q=glu.gluNewQuadric();
       
 
        // assume origo in bodys center.
        // x:forward, y:twds horses left, z:up
        GLU glu=new GLU();
        GLUquadric q=glu.gluNewQuadric();
        
             glu.gluQuadricTexture(q, true);
             glu.gluQuadricDrawStyle(q, GLU.GLU_FILL);
             //glu.gluQuadricDrawStyle(q, GLU.GLU_SILHOUETTE);
             glu.gluQuadricOrientation(q, GLU.GLU_OUTSIDE);
             glu.gluQuadricNormals(q, GLU.GLU_SMOOTH);
            
                 
                 
         
       
       
        // set the material
        setMaterial(gl);
       
     
      
       ground.enable(gl);
        ground.bind(gl);
        gl.glPushMatrix();
        //ground
        gl.glRotatef(90, 0, 1f, 0);
        gl.glTranslatef(1.8f,0,0);
        glu.gluSphere(q, 10, 2, 2);
         ground.disable(gl);
        
        
       
        
        
        //ikke i bruk
        Grass.enable(gl);
        Grass.bind(gl);
         gl.glRotatef(90, 0, 1, 0);
         float counter = 10;
         float x = -7;
         float y = 0;
         gl.glRotatef(45, 0, 0, 1f);
        /* for (int i= 0; i <= 110; i++  ) {
             gl.glPushMatrix();
              gl.glTranslatef(0,x,0);
              drawClosedCylinder(0.009f,0f,0.5f);
                      
              for (int k = 0; k <= 110; k ++) {
                 
                   gl.glTranslatef(y,0,0);
                  drawClosedCylinder(0.009f,0f,0.5f);
                  y+= 0.1;
                 
              }
         
         x += 0.1;
         y = 0;
          gl.glPopMatrix();
         }
      */
      
         Grass.disable(gl);
         gl.glPopMatrix();
      
          //end ikke i bruk
         
  
        
        glu.gluDeleteQuadric(q);
    }
 
    
   
    

    
   
    public void setMaterial(GL2 gl)
    {
        
        
        //whitish
        float amb[]={1.0f,0.86f,0.61f,1.0f};
        float diff[]={1.0f,0.76f,0.94f,1.0f};
        float spec[]={0.16f,0.28f,0.42f,1.0f};
        float shine=128f;
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,GL2.GL_AMBIENT,amb,0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,GL2.GL_DIFFUSE,diff,0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK,GL2.GL_SPECULAR,spec,0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK,GL2.GL_SHININESS,shine);
    }
   
   public void updateAngles()
    {
      
            
     }
      private void drawClosedCylinder(float r1, float r2, float l)
    {
        
        gl.glPushMatrix();
       
        glu.gluDisk(q, 0.0f, r1, SLICES, STACKS);
        glu.gluCylinder(q, r1, r2, l, SLICES, STACKS);
     
        gl.glTranslatef(0.0f,0.0f,l);
        glu.gluDisk(q, 0.0f, r2, SLICES, STACKS);
        gl.glPopMatrix();
        
    }  
            
      
           
     
    public void resetAngles()
    {
       
        updateAngles();
    }
}
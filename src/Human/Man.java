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



/**
 * @author bs
 */
public class Man
{
    // static dimensions
    public static final float BASE=1.0f;
    public static final float BODY_LENGTH=BASE*1.0f;
    public static final float BODY_RADIUS=BASE*0.3f;
    public static final float LEG_UPPER_LENGTH=BASE*0.65f;
    public static final float LEG_LOWER_LENGTH=BASE*0.4f;
    public static final float LEG_RADIUS=BASE*0.06f;
    public static final float NECK_LENGTH=BASE*0.65f;
    public static final float NECK_RADIUS=0.06f;
    public static final float HEAD_LENGTH=BASE*0.4f;
    public static final float HEAD_RADIUS=BASE*0.15f;
    private static final int SLICES=20;
    private static final int STACKS=10;
    // eof static dimensions
    // angles
    // the horse know how to walk ?
   
    private int movePos=0;

    private int leftUpper = 0;
    private int leftLower = 0;
    private int leftHand = 0;
    private int rightUpper = 0;
    private int rightLower = 0;
    private int rightHand;
            
    // eof angles



    public Man()
    {
        resetAngles(); 
    }
    
     public Texture skin;
     public Texture race;
     public Texture neck;
     public Texture shoulder;
     public Texture body;
     public Texture waist;
     public Texture hips;
     public Texture kne;
     public Texture legs;
     public Texture feet;
    
      GLU glu = null;
      GLUquadric q=null;
      GL2 gl = null;
    public void drawMe(GL2 gl2)
    {
               gl=gl2;
 
        // assume origo in bodys center.
        // x:forward, y:twds horses left, z:up
        glu=new GLU();
        q=glu.gluNewQuadric();
       
             glu.gluQuadricTexture(q, true);
             glu.gluQuadricDrawStyle(q, GLU.GLU_FILL);
             //glu.gluQuadricDrawStyle(q, GLU.GLU_SILHOUETTE);
             glu.gluQuadricOrientation(q, GLU.GLU_OUTSIDE);
             glu.gluQuadricNormals(q, GLU.GLU_SMOOTH);
            
                
          
        // set the material
        setMaterial(gl);
       
         drawhead();
         drawNeck();
         drawBody();
         drawShoulders();
         drawArms();         
         drawWaist();
         drawHips();
         drawKne();
         drawLegs();
         drawFeet();
      
        glu.gluDeleteQuadric(q);
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
    boolean test;
    private int armCount = 0;
   public void updateAngles()
    {
      if (leftUpper >= 120){
          test = true;
      }else if(leftUpper <= 0) {
          test = false; 
      }
      
      if (test){
          leftUpper -=10;
          leftLower -=10;
      }else{
          leftUpper +=20;
          leftLower +=20;
      }
     
            
     }
    public void resetAngles()
    {
        movePos=0;
        updateAngles();
    }
  

    private void texture(Texture tex, GL2 gl,boolean bool) {
        if(bool){
             tex.enable(gl);
        tex.bind(gl);
        }else {
            tex.disable(gl);
        }
       
        
        
    }

    private void drawShoulders() throws GLException {
        //Draw Shoulders
        gl.glPushMatrix();
          texture(skin,gl,true );
          Shoulders(-0.55f,0,0.75f);
          Shoulders(1.1f, 0, -0f);
         skin.disable(gl);
        gl.glPopMatrix();
    }
    
    private void Shoulders(float x,float y, float z) {
         gl.glTranslatef(x,y,z);
        glu.gluSphere(q, 0.08f, SLICES, 20);
    }

    private void drawBody() throws GLException {
        //draw body
        gl.glPushMatrix();
        
        gl.glTranslatef(0, 0, 0.7f);
        //gl.glRotatef(50f, 0f, 0f, 0.0f);
        gl.glScalef(1f, 0.5f, 1.0f);
     
         texture(shoulder,gl,true );
        glu.gluDisk(q, 0.0f,-0.5f, SLICES, STACKS);
        shoulder.disable(gl);
        texture(body,gl,true );
        glu.gluCylinder(q, -0.5f, -0.3f, -0.65f, SLICES, STACKS);
     
        gl.glTranslatef(0.0f,0.0f,-0.65f);
        body.disable(gl);
        gl.glPopMatrix();
    }

    private void drawNeck() throws GLException {
        // draw neck
        gl.glPushMatrix();
        texture(neck,gl,true );
        gl.glTranslatef(0, 0, 0.9f);
        drawClosedCylinder(-0.1f,-0.1f, -0.2f);
        neck.disable(gl);
        gl.glPopMatrix();
    }

    private void drawhead() throws GLException {
        gl.glPushMatrix();
       texture(race,gl,true );
      
       gl.glTranslatef(0, 0, 1.1f);
       gl.glRotatef(180f, 0f, 0f, 1f);
       gl.glScalef(0.8f, 1f, 1.2f);
       glu.gluSphere(q, 0.25f, SLICES, 20);
      race.disable(gl);
       gl.glPopMatrix();
    }

    private void drawArms() throws GLException {
        
        //lef 
         drawArm(-0.55f,leftUpper,leftLower);
         //right 
         drawArm(0.55f,rightUpper,rightLower);  
         
    }

   

    private void drawWaist() throws GLException {
        //waist
         gl.glPushMatrix();
           texture(waist,gl,true );
         gl.glTranslatef(0, 0, 0.1f);
          gl.glScalef(1f, 0.5f, 1.0f);
        drawClosedCylinder(-0.3f,-0.4f, -0.4f);
        waist.disable(gl);
        gl.glPopMatrix();
    }

    private void drawHips() throws GLException {
        //hips
         gl.glPushMatrix();
          texture(hips,gl,true );
         
         gl.glTranslatef(-0.2f, 0, -0.3f);
         //gl.glScalef(1f, 0.5f, 1.0f);
        drawClosedCylinder(-0.155f,-0.1f, -0.8f);
         
        gl.glTranslatef(0.4f, 0, 0);
        drawClosedCylinder(-0.155f,-0.1f, -0.8f);
          gl.glPopMatrix();
           hips.disable(gl);
         gl.glPushMatrix();
    }

    private void drawKne() throws GLException {
        //kne
        gl.glTranslatef(-0.2f,0,-1.15f);
         texture(kne,gl,true );
      
        glu.gluSphere(q, 0.1f, SLICES, 20);
        gl.glTranslatef(0.4f,0,0);
        glu.gluSphere(q, 0.1f, SLICES, 20);
       kne.disable(gl);
        gl.glPopMatrix();
    }

    private void drawLegs() throws GLException {
        //legs
         gl.glPushMatrix();
           texture(legs,gl,true );
           gl.glTranslatef(-0.2f,0,-1.2f);
     
       drawClosedCylinder(-0.1f,-0.06f, -0.6f);
      
       gl.glTranslatef(0.4f,0,0);
         drawClosedCylinder(-0.1f,-0.06f, -0.6f);
           legs.disable(gl);
       gl.glPopMatrix();
    }

    private void drawFeet() throws GLException {
        // feet
       gl.glPushMatrix();
      texture(feet,gl,true );
      
        gl.glTranslatef(-0.2f,0,-1.8f);
           gl.glRotatef(90f, 1.0f, 0f, 0f);
        createFeet(glu, q, gl);
        
         gl.glTranslatef(0.4f,0,0);
         createFeet(glu,q,gl);
   feet.disable(gl);
       
         gl.glPopMatrix();
    }
    private void createFeet(GLU glu, GLUquadric q, GL2 gl) {
        glu.gluSphere(q, 0.1f, SLICES, 20);
        drawClosedCylinder(-0.1f,-0.1f, -0.3f);
    }

   

    private void drawArm(float x, int upper, int lower ) throws GLException {
        
        texture(skin,gl,true );
        gl.glPushMatrix();
        gl.glTranslatef(x,0,0.75f-0.006f);
        gl.glRotatef(upper, 0, 1, 0);
        drawClosedCylinder(-0.06f,-0.06f, -0.6f);
        gl.glTranslatef(0,0,-0.63f);
        glu.gluSphere(q, 0.08f, SLICES, 20);
        gl.glRotatef(lower, 0, 1, 0);
        drawClosedCylinder(-0.06f,-0.06f, -0.6f);
        gl.glTranslatef(0,0,-0.63f);
        glu.gluSphere(q, 0.08f, SLICES, 20);
        skin.disable(gl);
         gl.glPopMatrix();
    }

}
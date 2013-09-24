package Human;
import java.io.File;
import java.io.IOException;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GLException;

public class LoadTexture {
   
    public static Texture addTexture( String humanSkinjpg) {
       Texture test=null;
        try {
            test = TextureIO.newTexture(new File("Bilder/"+humanSkinjpg),false);
        } catch (IOException ex) {
            Logger.getLogger(Man.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Man.class.getName()).log(Level.SEVERE, null, ex);
        }
       return test;
    } 
}
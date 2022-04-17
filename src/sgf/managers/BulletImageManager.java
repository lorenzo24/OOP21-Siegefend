package sgf.managers;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * 
 */
public class BulletImageManager extends AbstractImageLoaderManager<Integer> {

    // TODO: da fare
    
    @Override
    public Image getImage(Integer element) {
        try {
            return ImageIO.read(new File("res" + File.separator + "bullets" + File.separator + "bullet.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void fillMap(Map<Integer, String> map) {
        // TODO Auto-generated method stub

    }

}

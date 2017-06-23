package objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Daniil on 23.06.2017.
 */
public class QueenImage {
    private BufferedImage queenImage;

    public QueenImage (){
        try {
            queenImage = ImageIO.read(new File("resources\\images\\qween.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @param newW needed width
     * @param newH heeded height
     * @return scaled image
     */

    public BufferedImage resize (int newW, int newH) {
        BufferedImage image = new BufferedImage(newH, newH, queenImage.getType());
        int w = queenImage.getWidth();
        int h = queenImage.getHeight();
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(queenImage, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();

        return image;
    }
}

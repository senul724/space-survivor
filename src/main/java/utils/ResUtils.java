package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ResUtils {
  public static BufferedImage getImage(URL url) throws IOException {
    return ImageIO.read(url);
  }

  public static ImageIcon getImageIcon(URL url) throws IOException {
    return new ImageIcon(ImageIO.read(url));
  }
}

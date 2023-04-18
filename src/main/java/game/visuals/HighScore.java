package game.visuals;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import utils.ResUtils;

public class HighScore extends JLabel {
  public HighScore(int x, int y) {
    try {
      ImageIcon icon = ResUtils.getImageIcon(getClass().getResource("/img/hscore.png"));
      this.setIcon(icon);
      this.setBounds(x, y, 300, 150);
    } catch (IOException e) {
      System.out.println("Failed to load the image from resources!");
    }
  }
}

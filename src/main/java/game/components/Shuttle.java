package game.components;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import utils.ResUtils;

public class Shuttle extends JLabel {
  public static final int yPosition = 340;
  public static final int xPosition = 340;

  public Shuttle() throws IOException {
    ImageIcon icon = ResUtils.getImageIcon(getClass().getResource("/img/shuttle.png"));
    this.setIcon(icon);
    this.setBounds(xPosition, yPosition, 120, 120);
  }
}

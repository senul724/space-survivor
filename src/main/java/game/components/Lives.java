package game.components;

import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import utils.ResUtils;

public class Lives extends JLabel {
  public Lives(int x, int y) throws IOException {
    ImageIcon icon = ResUtils.getImageIcon(getClass().getResource("/img/heart.png"));
    this.setIcon(icon);
    this.setBounds(x, y, 60, 60);
  }
}

package game.visuals;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class Alert extends JLabel {
  public Alert(int x, int y, int len) {

    this.setHorizontalTextPosition(JLabel.CENTER);
    this.setVerticalTextPosition(JLabel.TOP);
    this.setVerticalAlignment(JLabel.CENTER);
    this.setHorizontalAlignment(JLabel.CENTER);
    this.setIconTextGap(20);
    this.setBounds(x, y, len, len);

    // foreground
    this.setForeground(Color.WHITE);
    this.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 30));
  }
}

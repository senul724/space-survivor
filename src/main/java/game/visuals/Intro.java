package game.visuals;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.ImageIcon;
// import javax.swing.ImageIcon;
import javax.swing.JLabel;

import utils.ResUtils;

// import utils.ResUtils;

public class Intro extends JLabel {
  public Intro(int x, int y, int len) throws IOException {
    ImageIcon icon = ResUtils.getImageIcon(getClass().getResource("/img/shuttlef.png"));

    this.setText("SPACE SURVIVOR ULTIMATE");
    this.setIcon(icon);
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

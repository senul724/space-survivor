package game.visuals;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import utils.ResUtils;

public class ScoreBoard extends JLabel {
  public ScoreBoard(int x, int y, int w, int h) {

    this.setText("score: 0");
    this.setVerticalAlignment(JLabel.CENTER);
    this.setHorizontalAlignment(JLabel.LEFT);
    this.setBounds(x, y, w, h);

    // foreground
    this.setForeground(Color.WHITE);
    this.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 25));
  }

  public ScoreBoard(int x, int y, int w, int h, String imagePath, int score) throws IOException {
    ImageIcon icon = ResUtils.getImageIcon(getClass().getResource(imagePath));
    this.setIcon(icon);
    this.setText(String.valueOf(score));
    this.setVerticalAlignment(JLabel.CENTER);
    this.setHorizontalAlignment(JLabel.LEFT);
    this.setVerticalTextPosition(JLabel.CENTER);
    this.setHorizontalTextPosition(JLabel.RIGHT);
    this.setIconTextGap(15);

    this.setBounds(x, y, w, h);

    // foreground
    this.setForeground(Color.WHITE);
    this.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 25));
  }
}

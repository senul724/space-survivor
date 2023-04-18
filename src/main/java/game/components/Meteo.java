package game.components;

import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import utils.ResUtils;

public class Meteo extends JLabel {
  Random random = new Random();

  public int xShift;
  public int yShift;

  public Meteo() throws IOException {
    ImageIcon icon = ResUtils.getImageIcon(getClass().getResource("/img/meteo.png"));
    this.setIcon(icon);
    this.setBounds(0, 0, 120, 120);
  }

  public int getXPos() {
    return this.getX() + xShift;
  }

  public int getYPos() {
    return this.getY() + yShift;
  }

  public void updateLocation() {
    this.setLocation(getXPos(), getYPos());
  }

  // returns true if the meteo is on screen
  public boolean isOnAction() {
    int currentX = this.getX();
    int currentY = this.getY();

    return currentX > 920 || currentX < -120 || currentY > 920 || currentY < -120 ? false : true;
  }

  public void updatePosition(int shift) {
    int x;
    int y;
    boolean isFixedX = random.nextBoolean();
    boolean isOrigin = random.nextBoolean();
    boolean isDiagonal = false;
    int position = random.nextInt(1001) - 100;
    int possibleUnfixedShift = position > 400 ? -shift : shift;

    if (position <= 100 || position >= 500) {
      isDiagonal = true;
    }

    if (isFixedX) {
      x = isOrigin ? -100 : 900;
      y = position;
      this.xShift = isOrigin ? shift : -shift;
      this.yShift = isDiagonal ? possibleUnfixedShift : 0;
    } else {
      y = isOrigin ? -100 : 900;
      x = position;
      this.yShift = isOrigin ? shift : -shift;
      this.xShift = isDiagonal ? possibleUnfixedShift : 0;
    }

    this.setLocation(x, y);
  }
}

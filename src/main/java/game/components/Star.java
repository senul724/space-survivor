package game.components;

import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import utils.ResUtils;

public class Star extends JLabel {
  Random random = new Random();
  int sectionCounter = 0;

  public Star() throws IOException {
    ImageIcon icon = ResUtils.getImageIcon(getClass().getResource("/img/star.png"));

    this.setIcon(icon);
    this.setBounds(-120, -120, 120, 120);
  }

  public void updatePosition() {
    int x;
    int y;
    int xIncrement;
    int yIncrement;

    if (sectionCounter > 3) {
      sectionCounter = 0;
    }

    if (sectionCounter < 2) {
      xIncrement = sectionCounter > 0 ? 0 : 1;
      yIncrement = sectionCounter > 0 ? 1 : 0;
    } else {
      xIncrement = sectionCounter % 2;
      yIncrement = xIncrement;
    }

    x = random.nextInt(340) + (340 * xIncrement);
    y = random.nextInt(340) + (340 * yIncrement);

    this.setLocation(x, y);
    sectionCounter++;
  }

  public boolean didCollect(int shuttleX, int shuttleY) {
    int starX = this.getX();
    int starY = this.getY();
    if (!((starX + 85 <= shuttleX) || (shuttleX + 85 <= starX) || (starY + 100 <= shuttleY)
        || (shuttleY + 100 <= starY))) {
      return true;
    } else {
      return false;
    }
  }
}

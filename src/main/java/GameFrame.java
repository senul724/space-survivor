import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

// custom packages
import game.visuals.*;
import game.components.*;
import game.data.Score;
import utils.CustomMathUtils;
import utils.ResUtils;
import utils.Player;

public class GameFrame extends JFrame implements KeyListener, ActionListener {
  static final int DELAY = 10;
  static final int MOVE_SHIFT = 50;
  static final int LIFE_AMOUNT = 3;
  static int M_MOVE_SHIFT = 2;

  int meteo_count = 3;
  int lives = LIFE_AMOUNT;
  int validationDelayCounter = 0;
  int score = 0;
  int highScore = Score.getHighScore();

  static boolean initiated = false;
  static boolean over = false;
  static boolean paused = false;
  static int shuttleX = Shuttle.xPosition;
  static int shuttleY = Shuttle.yPosition;

  Intro introScreen = new Intro(0, 0, 800);
  Alert alertScreen = new Alert(0, 0, 800);
  Button restartButton = new Button(350, 600, 100, 30, "RESTART");
  Button startButton = new Button(350, 600, 100, 30, "START");
  ScoreBoard scoreBoard = new ScoreBoard(50, 0, 300, 100);
  ScoreBoard highScoreBoard = new ScoreBoard(350, 0, 700, 100, "/img/trophy.png", highScore);
  HighScore highScoreImage = new HighScore(250, 200);
  Timer gameTimer;
  Shuttle shuttle;
  Star star;

  Player track = new Player(getClass().getResource("tracks/tetris.wav"));
  Player explodeTrack = new Player(getClass().getResource("tracks/explode.wav"));
  Player scoreTrack = new Player(getClass().getResource("tracks/pick.wav"));
  Player overTrack = new Player(getClass().getResource("tracks/end.wav"));

  // meteos
  Meteo[] meteos;
  Lives[] liveIcons;

  GameFrame() throws IOException {
    JLabel bg = new JLabel(ResUtils.getImageIcon(getClass().getResource("/img/bg.png")));
    ImageIcon gameIcon = ResUtils.getImageIcon(getClass().getResource("/img/shuttlef.png"));

    shuttle = new Shuttle();
    star = new Star();
    track.start();

    meteos = new Meteo[meteo_count];
    for (int i = 0; i < meteo_count; i++) {
      meteos[i] = new Meteo();
    }

    liveIcons = new Lives[LIFE_AMOUNT];
    for (int i = 0; i < LIFE_AMOUNT; i++) {
      liveIcons[i] = new Lives(700 - (i * 50), 10);
    }

    introScreen.add(startButton);
    bg.setBounds(0, 0, 800, 800);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.addKeyListener(this);
    this.setSize(800, 800);
    this.setLayout(null);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setIconImage(gameIcon.getImage());

    this.add(highScoreImage);
    highScoreImage.setVisible(false);

    this.add(highScoreBoard);
    this.add(scoreBoard);
    scoreBoard.setVisible(false);

    this.add(alertScreen);
    alertScreen.setVisible(false);
    alertScreen.add(restartButton);

    for (Lives el : liveIcons) {
      this.add(el);
    }

    this.add(star);

    for (Meteo el : meteos) {
      this.add(el);
    }

    this.add(shuttle);
    shuttle.setVisible(false);

    this.add(introScreen);

    this.add(bg);

    this.setVisible(true);

    gameTimer = new Timer(DELAY, this);
    gameTimer.start();
  }

  public void updateMeteoMovement(boolean forceUpdate) {
    for (Meteo el : meteos) {
      if (forceUpdate || !el.isOnAction()) {
        el.updatePosition(M_MOVE_SHIFT);
      }
    }
  }

  public void updateMeteoLocation() {
    for (Meteo el : meteos) {
      el.updateLocation();
    }
  }

  public void initiate() {
    introScreen.setVisible(false);
    shuttle.setVisible(true);
    scoreBoard.setVisible(true);

    // updating meteo position
    updateMeteoMovement(true);

    // adding star
    star.updatePosition();

    initiated = true;
  }

  public void reset() {
    initiated = false;
    score = 0;
    lives = LIFE_AMOUNT;
    over = false;
    paused = false;
    shuttle.setLocation(Shuttle.xPosition, Shuttle.yPosition);
    shuttleX = Shuttle.xPosition;
    shuttleY = Shuttle.yPosition;
    scoreBoard.setText("score: 0");

    if (!track.clip.isActive()) {
      track.start();
    }

    for (Lives el : liveIcons) {
      el.setVisible(true);
    }
  }

  public boolean checkCollision() {
    for (Meteo el : meteos) {
      int meteoCenterX = el.getX() + 60;
      int meteoCenterY = el.getY() + 60;

      // getting the distance between the 2 centers
      boolean didCollide = CustomMathUtils.checkCircleOverlap(shuttleX + 60, meteoCenterX, shuttleY + 60, meteoCenterY);

      // checking the overlap
      if (didCollide) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int pressedkey = e.getKeyCode();
    int xPos = shuttle.getX();
    int yPos = shuttle.getY();

    switch (pressedkey) {
      // move left
      case 37: // <-
        if (xPos <= 70 || over || paused) {
          return;
        }

        shuttleX = xPos - MOVE_SHIFT;
        shuttle.setLocation(shuttleX, shuttleY);

        break;

      case 65: // a
        if (xPos <= 70 || over || paused) {
          return;
        }

        shuttleX = xPos - MOVE_SHIFT;
        shuttle.setLocation(shuttleX, shuttleY);

        break;

      // move right
      case 39: // ->
        if (xPos >= 600 || over || paused) {
          return;
        }

        shuttleX = xPos + MOVE_SHIFT;
        shuttle.setLocation(shuttleX, shuttleY);

        break;

      case 68: // d
        if (xPos >= 600 || over || paused) {
          return;
        }

        shuttleX = xPos + MOVE_SHIFT;
        shuttle.setLocation(shuttleX, shuttleY);

        break;

      // move up
      case 38: // ^
        if (yPos <= 70 || over || paused) {
          return;
        }

        shuttleY = yPos - MOVE_SHIFT;
        shuttle.setLocation(shuttleX, shuttleY);

        break;

      case 87: // w
        if (yPos <= 70 || over || paused) {
          return;
        }

        shuttleY = yPos - MOVE_SHIFT;
        shuttle.setLocation(shuttleX, shuttleY);

        break;

      // move down
      case 40: // v
        if (yPos >= 600 || over || paused) {
          return;
        }

        shuttleY = yPos + MOVE_SHIFT;
        shuttle.setLocation(shuttleX, shuttleY);

        break;

      case 83: // s
        if (yPos >= 600 || over || paused) {
          return;
        }

        shuttleY = yPos + MOVE_SHIFT;
        shuttle.setLocation(shuttleX, shuttleY);

        break;

      case 32:
        if (!initiated || over) {
          return;
        }
        if (!paused) {
          alertScreen.setText("Game Paused");
          alertScreen.setVisible(true);
        } else {
          alertScreen.setVisible(false);
        }
        paused = !paused;

        break;

      case 81:
        System.exit(0);
        break;

      case 82:
        if (initiated && !over) {
          reset();
        }
        break;

      default:
        break;
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (startButton.pressed && !over && !paused) {
      if (!initiated) {
        initiate();
      }

      validationDelayCounter += 1;
      if (validationDelayCounter >= 50) {
        updateMeteoMovement(false);
        validationDelayCounter = 0;
      }

      updateMeteoLocation();

      boolean collided = checkCollision();

      if (collided) {
        lives -= 1;
        liveIcons[lives].setVisible(false);
        validationDelayCounter = 0;

        if (lives <= 0) {
          track.stop();
          overTrack.start();
          over = true;
          if (score > highScore) {
            highScore = score;
            Score.setHighScore(score);
            highScoreBoard.setText(String.valueOf(score));
            highScoreImage.setVisible(true);
          }
          alertScreen.setText("GAME OVER!");
          alertScreen.setVisible(true);
        } else {
          explodeTrack.start();
          try {
            TimeUnit.MILLISECONDS.sleep(500);
          } catch (InterruptedException error) {
            System.out.println(error);
          }
          shuttle.setLocation(Shuttle.xPosition, Shuttle.yPosition);
          shuttleX = Shuttle.xPosition;
          shuttleY = Shuttle.yPosition;
          updateMeteoMovement(true);
        }
      }

      if (star.didCollect(shuttleX, shuttleY)) {
        scoreTrack.stop();
        scoreTrack.start();
        score += 10;
        scoreBoard.setText("score: " + String.valueOf(score));
        star.updatePosition();
      }

      if (!over && !track.clip.isActive()) {
        track.start();
      }
    }
    if (restartButton.pressed) {
      reset();
      highScoreImage.setVisible(false);
      restartButton.pressed = false;
      alertScreen.setVisible(false);

    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
  }
}

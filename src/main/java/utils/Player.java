package utils;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Player {
  public Clip clip;
  AudioInputStream stream;

  public Player(URL url) {
    try {
      stream = AudioSystem.getAudioInputStream(url);
      clip = AudioSystem.getClip();
      clip.open(stream);
    } catch (UnsupportedAudioFileException e) {
      System.out.println("File not supported!");
    } catch (Exception e) {
      System.out.println("Something went wrong!");
    }

  }

  public void start() {
    clip.setMicrosecondPosition(0);
    clip.start();
  }

  public void stop() {
    clip.stop();
  }
}

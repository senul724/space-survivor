package game.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Score {
  public static int getHighScore()  {
    try {
      BufferedReader reader = new BufferedReader(new FileReader("src/data/high-score.txt"));
      String data = reader.readLine();
      reader.close();
      return Integer.parseInt(data.trim());
    } catch (Exception e) {
      setHighScore(0);
    }
    return 0;
  }

  public static void setHighScore(int newScore) {
    try {
      BufferedWriter myWriter = new BufferedWriter(new FileWriter("src/data/high-score.txt"));
      myWriter.write(String.valueOf(newScore));
      myWriter.close();
    } catch (Exception e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}

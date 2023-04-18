package game.components;
import javax.swing.JButton;

public class Button extends JButton {
    public boolean pressed = false;
    // for cordinate layout
    public Button(int x, int y, int w, int h, String title){
        this.addActionListener(e -> {
            pressed = true;
        });
        this.setText(title);
        this.setFocusable(false);
        this.setBounds(x, y, w, h);
    }

    // for border layout
    Button(String title){
        this.addActionListener(e -> {
            pressed = true;
        });
        this.setText(title);
        this.setFocusable(false);
    }
}

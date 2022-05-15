import javax.swing.*;
import java.awt.*;

public class BackButton extends JButton {

    // Returns a JButton that is used as a backbutton in several views.
    public BackButton() {

        ImageIcon backArrow = new ImageIcon("src/images/backArrow_small.png");
        this.setIcon(backArrow);
        this.setText("TAKAISIN");
        this.setFont(new Font("Arial", Font.BOLD, 25));
        this.setForeground(new Color(31, 29, 114));
        this.setMaximumSize(new Dimension(250, 80));
        this.setBackground(new Color(237, 243, 249));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BackButtonPanel extends JPanel {


    public BackButtonPanel() {

    ImageIcon backArrow = new ImageIcon("src/images/backArrow_small.png");
    //System.out.println("width: " + backArrow.getIconWidth());
    //System.out.println("height: " + backArrow.getIconHeight());
    //backArrow.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
    JLabel arrowImage = new JLabel(backArrow);
    arrowImage.setIcon(backArrow);
    JLabel backText = new JLabel("TAKAISIN");
    backText.setFont(new Font("Arial", Font.BOLD, 25));
    backText.setForeground(new Color(31, 29, 114));
    JPanel backButton = new JPanel();
    //backButton.setBackground(Color.GREEN);
    backButton.setMaximumSize(new Dimension(250, 80));
    //this.setBackground(Color.BLUE);
    //System.out.println(backText.getPreferredSize());
    backButton.setBackground(new Color(237, 243, 249));
    this.setBackground(new Color(237, 243, 249));
    //this.setBackground(new Color(237, 243, 249));
    backButton.setLayout(new BoxLayout(backButton, BoxLayout.X_AXIS));
    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    //this.setAlignmentX(FlowLayout.LEFT);
    backButton.add(arrowImage);
    backButton.add(backText);
    //backButton.setAlignmentX(FlowLayout.LEFT);
    //backButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
    backButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    //cConstraints.gridx = 0;
    //cConstraints.gridy = 6;
    //cConstraints.weightx = 0;
    //backButton.setPreferredSize(new Dimension(50, 50));
    //backButton.setMaximumSize(new Dimension(50,50));
    this.add(backButton);
    
}
}

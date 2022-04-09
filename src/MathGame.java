import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MathGame extends JFrame{

    

    public MathGame() {

        System.out.println("Täällä ollaan");
        JFrame frame = new JFrame();
        JPanel centerPanel = new JPanel();
        JPanel northPanel = new JPanel();
        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();
        centerPanel.setBorder(BorderFactory.createEmptyBorder(600, 600, 200, 600));
        centerPanel.setBackground(new Color (237,243,249));
        northPanel.setBorder(BorderFactory.createEmptyBorder(30,30, 10, 30));
        northPanel.setBackground(new Color (50,34,151));
        eastPanel.setBorder(BorderFactory.createEmptyBorder(60,60, 20, 60));
        eastPanel.setBackground(new Color (206,225,242));
        westPanel.setBorder(BorderFactory.createEmptyBorder(60,60, 20, 60));
        westPanel.setBackground(new Color (206,225,242));

        JButton startGameButton = new JButton("ALOITA");
        ImageIcon homeIcon = new ImageIcon("src/images/home2.png");
        JButton homeButton = new JButton(new ImageIcon(homeIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
        JButton starButton = new JButton(new ImageIcon("filename"));
        JButton helpButton = new JButton(new ImageIcon("filename"));
        
        centerPanel.add(startGameButton);
        northPanel.add(homeButton);
        northPanel.add(starButton);
        northPanel.add(helpButton);
        homeButton.setBackground(new Color(84, 138, 137));
        starButton.setBackground(new Color(84, 138, 137));
        helpButton.setBackground(new Color(84, 138, 137));

        startGameButton.setBackground(new Color(255, 164, 58));
       
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(westPanel, BorderLayout.WEST);
        frame.add(eastPanel, BorderLayout.EAST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Matikkapeli");
        frame.pack();
        frame.setVisible(true);


    }


{ 

   

   }
    
    
     public static void main(String[] args) {
        MathGame game = new MathGame();
        game.setVisible(true);  
    }
}
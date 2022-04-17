import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** The main application class that contains the main function. Mathgame creates the main frame 
 * and the control panel  */
public class MathGame extends JFrame{

    

    public MathGame() {

        JFrame frame = new JFrame();
        StartPanel centerPanel = new StartPanel();
        JPanel northPanel = new JPanel();
        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();
        //centerPanel.setBorder(BorderFactory.createEmptyBorder(400, 400, 400, 400));
        //centerPanel.setBackground(new Color (237,243,249));
        northPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));
        northPanel.setBackground(new Color (50,34,151));
        eastPanel.setBorder(BorderFactory.createEmptyBorder(60,60, 20, 60));
        eastPanel.setBackground(new Color (206,225,242));
        westPanel.setBorder(BorderFactory.createEmptyBorder(60,60, 20, 60));
        westPanel.setBackground(new Color (206,225,242));

        northPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

        ImageIcon homeIcon = new ImageIcon("src/images/home2.png");
        JButton homeButton = new JButton(new ImageIcon(homeIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
        homeButton.setPreferredSize(new Dimension(45, 45));

        ImageIcon starIcon = new ImageIcon("src/images/star-images-9472.png");
        JButton starButton = new JButton(new ImageIcon(starIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
        starButton.setPreferredSize(new Dimension(45, 45));

        ImageIcon questionIcon = new ImageIcon("src/images/question-mark-10920.png");
        JButton helpButton = new JButton(new ImageIcon(questionIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
        helpButton.setPreferredSize(new Dimension(45, 45));

        northPanel.add(homeButton);
        northPanel.add(starButton);
        northPanel.add(helpButton);
        homeButton.setBackground(new Color(84, 138, 137));
        starButton.setBackground(new Color(84, 138, 137));
        helpButton.setBackground(new Color(84, 138, 137));

       
        /*centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints cConstraints = new GridBagConstraints();
        cConstraints.insets = new Insets(30, 30, 30, 30);
        //constraints.fill = GridBagConstraints.VERTICAL;

        ImageIcon startPicture = new ImageIcon("src/images/startImage_small.png");
        //ImageIcon scaledStartPicture = new ImageIcon(startPicture.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH));
        JLabel startImageLabel = new JLabel();
        startImageLabel.setIcon(startPicture);
        //System.out.println("width: " + startPicture.getIconWidth());
        //System.out.println("height: " + startPicture.getIconHeight());
        cConstraints.gridx = 0;
        cConstraints.gridy = 0;
        //constraints.weightx = 1;
        //constraints.weighty = 1;
        //constraints.gridheight = 1;
        //constraints.fill = GridBagConstraints.BOTH;
        centerPanel.add(startImageLabel, cConstraints);
        
        JButton startGameButton = new JButton("ALOITA");
        startGameButton.setBackground(new Color(255, 164, 58));
        startGameButton.setFont((new Font("Arial", Font.BOLD, 20)));
        cConstraints.gridx = 0;
        cConstraints.gridy = 1;
        //constraints.weightx = 1;
        //constraints.weighty = 20;
        //constraints.gridheight = 6;
        //constraints.anchor = GridBagConstraints.SOUTH;
        //constraints.fill = GridBagConstraints.NONE;
        cConstraints.ipadx = 60; //internal padding x
        cConstraints.ipady = 25; //internal padding y
        centerPanel.add(startGameButton, cConstraints);*/
       
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
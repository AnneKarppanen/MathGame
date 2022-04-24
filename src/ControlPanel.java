import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;

/**
 * ControlPanel is the area at the top of the frame that contains buttons to
 * navigate back to
 * opening screen, to view the results and to read game istructions.
 */
public class ControlPanel extends JPanel {

    private JButton homeButton = null;
    private JButton starButton = null;
    private JButton helpButton = null;
    private GameController gameController;

    public ControlPanel(GameController gameController) {
        this.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));
        this.setBackground(new Color(50, 34, 151));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        this.gameController = gameController;

       

        ImageIcon homeIcon = new ImageIcon("src/images/home2.png");
        homeButton = new JButton(
                new ImageIcon(homeIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
        homeButton.setPreferredSize(new Dimension(45, 45));

        ImageIcon starIcon = new ImageIcon("src/images/star-images-9472.png");
        starButton = new JButton(
                new ImageIcon(starIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
        starButton.setPreferredSize(new Dimension(45, 45));

        ImageIcon questionIcon = new ImageIcon("src/images/question-mark-10920.png");
        helpButton = new JButton(
                new ImageIcon(questionIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
        helpButton.setPreferredSize(new Dimension(45, 45));

        this.add(homeButton);
        this.add(starButton);
        this.add(helpButton);
        homeButton.setBackground(new Color(84, 138, 137));
        starButton.setBackground(new Color(84, 138, 137));
        helpButton.setBackground(new Color(84, 138, 137));

        setUpButtonListeners();

    }

    public void setUpButtonListeners() {
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if(source == homeButton){
                    System.out.println("Homebutton clicked");
                    gameController.showNewGameWindow();           
                }else if(source == starButton){
                    System.out.println("Starbutton clicked");
                }else if(source == helpButton){
                    System.out.println("Helpbutton clicked");
                } 

            }
        };

        homeButton.addActionListener(buttonListener);
        starButton.addActionListener(buttonListener);
        helpButton.addActionListener(buttonListener);
    }

}

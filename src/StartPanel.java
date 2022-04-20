
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartPanel extends JPanel {

    private JButton startGameButton = null;
    private ImageIcon startPicture = null;
    private JLabel startImageLabel = null;
    private GameController gameController = null;

    public StartPanel(GameController gameController) {
        this.setBorder(BorderFactory.createEmptyBorder(400, 400, 400, 400));
        this.setBackground(new Color(237, 243, 249));
        this.setLayout(new GridBagLayout());
        this.gameController = gameController;
        GridBagConstraints cConstraints = new GridBagConstraints();
        cConstraints.insets = new Insets(30, 30, 30, 30);
        // constraints.fill = GridBagConstraints.VERTICAL;

        startPicture = new ImageIcon("src/images/startImage_small.png");
        // ImageIcon scaledStartPicture = new
        // ImageIcon(startPicture.getImage().getScaledInstance(200, 200,
        // java.awt.Image.SCALE_SMOOTH));
        startImageLabel = new JLabel();
        startImageLabel.setIcon(startPicture);
        // System.out.println("width: " + startPicture.getIconWidth());
        // System.out.println("height: " + startPicture.getIconHeight());
        cConstraints.gridx = 0;
        cConstraints.gridy = 0;
        // constraints.weightx = 1;
        // constraints.weighty = 1;
        // constraints.gridheight = 1;
        // constraints.fill = GridBagConstraints.BOTH;
        this.add(startImageLabel, cConstraints);

        startGameButton = new JButton("ALOITA");
        startGameButton.setBackground(new Color(255, 164, 58));
        startGameButton.setFont((new Font("Arial", Font.BOLD, 20)));
        startGameButton.addActionListener(new ButtonHandler());
        cConstraints.gridx = 0;
        cConstraints.gridy = 1;
        // constraints.weightx = 1;
        // constraints.weighty = 20;
        // constraints.gridheight = 6;
        // constraints.anchor = GridBagConstraints.SOUTH;
        // constraints.fill = GridBagConstraints.NONE;
        cConstraints.ipadx = 60; // internal padding x
        cConstraints.ipady = 25; // internal padding y
        this.add(startGameButton, cConstraints);
    }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gameController.chooseUser();
            
        }
        
    }

}


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartPanel extends JPanel {

    private JButton startGameButton = null;
    private ImageIcon startPicture = null;
    private JLabel startImageLabel = null;

    public StartPanel() {
        this.setBorder(BorderFactory.createEmptyBorder(200, 200, 200, 200));
        this.setBackground(new Color(237, 243, 249));
        this.setLayout(new GridBagLayout());
        GridBagConstraints cConstraints = new GridBagConstraints();
        cConstraints.insets = new Insets(30, 30, 30, 30);


        startPicture = new ImageIcon("src/images/startImage_small.png");
        startImageLabel = new JLabel();
        startImageLabel.setIcon(startPicture);
        cConstraints.gridx = 0;
        cConstraints.gridy = 0;
        this.add(startImageLabel, cConstraints);

        startGameButton = new JButton("ALOITA");
        startGameButton.setBackground(new Color(255, 164, 58));
        startGameButton.setFont((new Font("Arial", Font.BOLD, 20)));
        startGameButton.addActionListener(new ButtonHandler());
        cConstraints.gridx = 0;
        cConstraints.gridy = 1;
        cConstraints.ipadx = 60;
        cConstraints.ipady = 25;
        this.add(startGameButton, cConstraints);
    }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GameController.getInstance().chooseUser();
            
        }
        
    }

}

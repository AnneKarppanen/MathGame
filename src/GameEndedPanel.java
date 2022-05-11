import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameEndedPanel extends JPanel {

    private JButton newGameButton = null;
    private JButton quitButton = null;

    public GameEndedPanel(boolean newStarAchieved, boolean isNewHighScore, int points) {

        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(new Color(237, 243, 249));
        // this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(new BorderLayout());
        JPanel middlePane = new JPanel();
        middlePane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        middlePane.setLayout(new GridBagLayout());
        middlePane.setBackground(new Color(237, 243, 249));
        // middlePane.setMaximumSize(new Dimension(800, 600));
        GridBagConstraints cConstraints = new GridBagConstraints();
        cConstraints.insets = new Insets(20, 20, 20, 20);
        // cConstraints.fill = GridBagConstraints.VERTICAL;

        cConstraints.gridx = 0;
        cConstraints.gridy = 0;
        // constraints.weightx = 1;
        // constraints.weighty = 1;
        // constraints.gridheight = 1;
        // cConstraints.gridwidth = GridBagConstraints.REMAINDER;
        // constraints.fill = GridBagConstraints.BOTH;

        /*
         * JPanel filler = new JPanel();
         * filler.setBackground(Color.RED);
         * cConstraints.fill = GridBagConstraints.BOTH;
         * //cConstraints.weightx = 0.25;
         * //cConstraints.gridx = 0;
         * //cConstraints.gridy = 0;
         * //cConstraints.weighty = 1;
         * this.add(filler, cConstraints);
         */
        JLabel instruction = new JLabel("PELI PÄÄTTYI");
        instruction.setFont(new Font("Arial", Font.BOLD, 50));
        instruction.setForeground(new Color(50, 34, 151));
        cConstraints.fill = GridBagConstraints.NONE;
        cConstraints.weightx = 0.5;
        middlePane.add(instruction, cConstraints);

        JPanel pointsPanel = new JPanel();
        pointsPanel.setBackground(new Color(237, 243, 249));

        JLabel pointsText = new JLabel("PISTEESI  ");
        pointsText.setFont(new Font("Arial", Font.BOLD, 70));
        pointsText.setForeground(new Color(50, 34, 151));
        pointsText.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        int pointAmount = GameController.getInstance().getCurrentPoints();
        JLabel amountOfPoints = new JLabel(String.valueOf(pointAmount));
        amountOfPoints.setFont(new Font("Arial", Font.BOLD, 90));
        amountOfPoints.setForeground(new Color(158, 60, 167));
        amountOfPoints.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        pointsPanel.add(pointsText);
        pointsPanel.add(amountOfPoints);
        pointsPanel.setLayout(new BoxLayout(pointsPanel, BoxLayout.X_AXIS));
        // pointsPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        cConstraints.gridx = 0;
        cConstraints.gridy = 1;
        cConstraints.fill = GridBagConstraints.NONE;
        // cConstraints.weightx = 0;
        middlePane.add(pointsPanel, cConstraints);

        // JPanel resultPanel = new NormalGameEndPanel();
        JPanel resultPanel = new GameEndPanelStar();
        cConstraints.gridx = 0;
        cConstraints.gridy = 2;
        middlePane.add(resultPanel, cConstraints);

        JPanel filler = new JPanel();
        // filler.setBackground(Color.RED);
        // cConstraints.fill = GridBagConstraints.BOTH;
        cConstraints.gridx = 0;
        cConstraints.gridy = 3;
        // cConstraints.weightx = 0.25;
        // cConstraints.weighty = 1;
        middlePane.add(filler, cConstraints);

        this.newGameButton = new JButton("UUSI PELI");
        newGameButton.setBackground(new Color(255, 164, 58));
        newGameButton.setFont((new Font("Arial", Font.BOLD, 40)));
        cConstraints.gridx = 0;
        cConstraints.gridy = 4;
        // cConstraints.weightx = 0;
        // constraints.weighty = 20;
        // constraints.gridheight = 6;
        // constraints.anchor = GridBagConstraints.SOUTH;
        // constraints.fill = GridBagConstraints.NONE;
        cConstraints.ipadx = 40; // internal padding x
        cConstraints.ipady = 20; // internal padding y
        middlePane.add(newGameButton, cConstraints);
        this.add(middlePane, BorderLayout.CENTER);

        this.quitButton = new JButton("LOPETA");
        quitButton.setBackground(new Color(109, 177, 240));
        quitButton.setFont((new Font("Arial", Font.PLAIN, 30)));
        quitButton.setMargin(new Insets(10, 10, 10, 10));
        // quitButton.setPreferredSize(new Dimension(120, 80));
        JPanel southPanel = new JPanel();
        southPanel.setBackground(new Color(237, 243, 249));
        southPanel.add(quitButton);
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        this.add(southPanel, BorderLayout.SOUTH);

        // setUpButtonListeners();

    }

    /*
     * public void setUpButtonListeners() {
     * ActionListener buttonListener = new ActionListener() {
     * 
     * @Override
     * public void actionPerformed(ActionEvent e) {
     * Object source = e.getSource();
     * if(source == textField || source == okButton){
     * String text = textField.getText();
     * GameController gameController = GameController.getInstance();
     * gameController.addNewUser(text);
     * gameController.showChooseOperationPanel();
     * 
     * }else if(source == backButton){
     * GameController.getInstance().chooseUser();
     * }
     * 
     * }
     * };
     * 
     * textField.addActionListener(buttonListener);
     * okButton.addActionListener(buttonListener);
     * // backButton.addActionListener(buttonListener);
     */

    private class NormalGameEndPanel extends JPanel {

        private NormalGameEndPanel() {
            ImageIcon smiley = new ImageIcon("src/images/smiley_small.png");
            JLabel smileyLabel = new JLabel();
            smileyLabel.setIcon(smiley);
            this.setBackground(new Color(237, 243, 249));
            this.add(smileyLabel);

        }

    }

    private class GameEndPanelStar extends JPanel {

        private GameEndPanelStar() {

            JLabel youGotStar = new JLabel("Ansaitsit tähden!");
            youGotStar.setFont(new Font("Arial", Font.ITALIC, 50));
            youGotStar.setForeground(new Color(158, 60, 167));
            youGotStar.setAlignmentX(CENTER_ALIGNMENT);

            Box.Filler filler = new Box.Filler(new Dimension(5,5), new Dimension(10,10), new Dimension(15,15));

            ImageIcon star = new ImageIcon("src/images/starAchieved_small.png");
            JLabel starLabel = new JLabel();
            starLabel.setIcon(star);
            this.setBackground(new Color(237, 243, 249));
            starLabel.setAlignmentX(CENTER_ALIGNMENT);

            Box.Filler filler2 = new Box.Filler(new Dimension(2,2), new Dimension(5,5), new Dimension(10,10));

            int starAmount = GameController.getInstance().getGameData().getNumberOfStarsAtTheEnd();
            JLabel amountOfStars = new JLabel(String.valueOf(starAmount) + " / 3");
            amountOfStars.setFont(new Font("Arial", Font.BOLD, 25));
            amountOfStars.setForeground(new Color(50, 34, 151));
            amountOfStars.setAlignmentX(CENTER_ALIGNMENT);

            this.setBackground(new Color(237, 243, 249));
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.add(youGotStar);
            this.add(filler);
            this.add(starLabel);
            this.add(filler2);
            this.add(amountOfStars);
            

        }

    }

    private class GameEndPanelHighScore extends JPanel {

        private GameEndPanelHighScore() {
            JLabel youGotStar = new JLabel("Ansaitsit tähden!");
            youGotStar.setFont(new Font("Arial", Font.ITALIC, 50));
            youGotStar.setForeground(new Color(158, 60, 167));

            ImageIcon smiley = new ImageIcon("src/images/smiley_small.png");
            JLabel starLabel = new JLabel();
            starLabel.setIcon(smiley);

            int pointAmount = GameController.getInstance().getCurrentPoints();
            JLabel amountOfStars = new JLabel(String.valueOf(pointAmount));
            amountOfStars.setFont(new Font("Arial", Font.BOLD, 90));
            amountOfStars.setForeground(new Color(158, 60, 167));
            amountOfStars.setAlignmentY(Component.BOTTOM_ALIGNMENT);

            this.setBackground(new Color(237, 243, 249));
            this.add(youGotStar);
            this.add(starLabel);
            this.add(amountOfStars);

        }

    }

    private class GameEndPanelStarAndHighScore extends JPanel {

        private GameEndPanelStarAndHighScore() {
            ImageIcon smiley = new ImageIcon("src/images/smiley_small.png");
            JLabel smileyLabel = new JLabel();
            smileyLabel.setIcon(smiley);
            this.setBackground(new Color(237, 243, 249));
            this.add(smileyLabel);

        }

    }
}

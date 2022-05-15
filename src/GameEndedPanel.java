import javax.swing.*;
import javax.swing.Box.Filler;

import java.awt.*;
import java.awt.event.*;


/*Creates the panel that is shown at the end of the game. The panel shows the points and informs the 
user if they have received a new star or a new high score. The middle of panel shows a different image
and information depending on whether there were no special achievements or 
a new star and/or a new high score was achieved.*/
public class GameEndedPanel extends JPanel {

    private JButton newGameButton = null;
    private JButton quitButton = null;

    public GameEndedPanel(boolean newStarAchieved, boolean isNewHighScore, int points) {

        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(new Color(237, 243, 249));
        this.setLayout(new BorderLayout());

        JPanel middlePane = new JPanel();
        middlePane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        //middlePane.setLayout(new GridBagLayout());
        middlePane.setLayout(new BoxLayout(middlePane, BoxLayout.Y_AXIS));
        middlePane.setBackground(new Color(237, 243, 249));
       

        /*GridBagConstraints cConstraints = new GridBagConstraints();
        cConstraints.insets = new Insets(20, 20, 20, 20);
        cConstraints.gridx = 0;
        cConstraints.gridy = 0;*/

        JLabel instruction = new JLabel("PELI PÄÄTTYI");
        instruction.setFont(new Font("Arial", Font.BOLD, 50));
        instruction.setForeground(new Color(50, 34, 151));
        instruction.setAlignmentX(CENTER_ALIGNMENT);
        //cConstraints.fill = GridBagConstraints.NONE;
        //cConstraints.weightx = 0.5;
        //middlePane.add(instruction, cConstraints);
        middlePane.add(instruction);

        Dimension fillerMinDimension = new Dimension(5,5);
        Dimension fillerPreferredDimension = new Dimension(20,20);
        Dimension fillerMaxDimension = new Dimension(40, 40);

        //Box.Filler filler1 = new Filler(fillerMinDimension, fillerPreferredDimension, fillerMaxDimension);
        //middlePane.add(filler1);

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
        pointsPanel.setLayout(new BoxLayout(pointsPanel, BoxLayout.X_AXIS));
        pointsPanel.add(pointsText);
        pointsPanel.add(amountOfPoints);
        
        pointsPanel.setAlignmentX(CENTER_ALIGNMENT);
        //cConstraints.gridx = 0;
        //cConstraints.gridy = 1;
        //cConstraints.fill = GridBagConstraints.NONE;
        //middlePane.add(pointsPanel, cConstraints);
        
        middlePane.add(pointsPanel);

        Box.Filler filler2 = new Filler(fillerMinDimension, fillerPreferredDimension, fillerMaxDimension);
        middlePane.add(filler2);

        JPanel resultPanel = new JPanel();

        if (!newStarAchieved && !isNewHighScore) {
            resultPanel = new NormalGameEndPanel();
        } else {
            resultPanel = new AchievementPanel(newStarAchieved, isNewHighScore);
        }

        //cConstraints.gridx = 0;
        //cConstraints.gridy = 2;
        //middlePane.add(resultPanel, cConstraints);
        resultPanel.setAlignmentX(CENTER_ALIGNMENT);
        middlePane.add(resultPanel);

        //JPanel filler = new JPanel();
        //cConstraints.gridx = 0;
        //cConstraints.gridy = 3;
        //middlePane.add(filler, cConstraints);
       

        Box.Filler filler = new Filler(fillerMinDimension, fillerPreferredDimension, fillerMaxDimension);
        middlePane.add(filler);

        this.newGameButton = new JButton("UUSI PELI");
        newGameButton.setBackground(new Color(255, 164, 58));
        newGameButton.setFont((new Font("Arial", Font.BOLD, 40)));
        newGameButton.setAlignmentX(CENTER_ALIGNMENT);
        //cConstraints.gridx = 0;
        //cConstraints.gridy = 4;
        //cConstraints.ipadx = 40;
        //cConstraints.ipady = 20;
        //middlePane.add(newGameButton, cConstraints);
        //this.add(middlePane, BorderLayout.CENTER);
        middlePane.add(newGameButton);
        middlePane.setAlignmentX(CENTER_ALIGNMENT);
        middlePane.setAlignmentY(CENTER_ALIGNMENT);
        this.add(middlePane);

        this.quitButton = new JButton("LOPETA");
        quitButton.setBackground(new Color(109, 177, 240));
        quitButton.setFont((new Font("Arial", Font.PLAIN, 30)));
        quitButton.setMargin(new Insets(10, 10, 10, 10));

        JPanel southPanel = new JPanel();
        southPanel.setBackground(new Color(237, 243, 249));
        southPanel.add(quitButton);
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        this.add(southPanel, BorderLayout.SOUTH);

        setUpButtonListeners();

    }

    private class NormalGameEndPanel extends JPanel {

        private NormalGameEndPanel() {
            ImageIcon smiley = new ImageIcon("src/images/smiley_small.png");
            JLabel smileyLabel = new JLabel();
            smileyLabel.setIcon(smiley);
            this.setBackground(new Color(237, 243, 249));
            this.add(smileyLabel);

        }

    }

    private class AchievementPanel extends JPanel {

        private AchievementPanel(boolean newStarAchieved, boolean isNewHighScore) {

            String text = null;
            JLabel caption = null;
            JPanel imagePanel = null;

            if (newStarAchieved && !isNewHighScore) {
                text = "Ansaitsit tähden!";
                imagePanel = new StarPanel();

            } else if (isNewHighScore && !newStarAchieved) {
                text = "Uusi ennätys!";
                imagePanel = new TrophyPanel();

            } else if (isNewHighScore && newStarAchieved) {
                text = "Uusi ennätys ja tähti!";
                JPanel imagepanel1 = new TrophyPanel();
                Box.Filler filler = new Box.Filler(new Dimension(15, 15), new Dimension(30, 30), new Dimension(45, 45));
                JPanel imagePanel2 = new StarPanel();
                imagePanel = new JPanel();
                imagePanel.add(imagepanel1);
                imagePanel.add(filler);
                imagePanel.add(imagePanel2);
                imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.X_AXIS));

            }

            JLabel textLabel = new JLabel(text);
            textLabel.setFont(new Font("Arial", Font.ITALIC, 50));
            textLabel.setForeground(new Color(158, 60, 167));
            textLabel.setAlignmentX(CENTER_ALIGNMENT);

            Box.Filler filler = new Box.Filler(new Dimension(5, 5), new Dimension(10, 10), new Dimension(15, 15));
            Box.Filler filler2 = new Box.Filler(new Dimension(2, 2), new Dimension(5, 5), new Dimension(10, 10));

            this.setBackground(new Color(237, 243, 249));
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.add(textLabel);
            this.add(filler);
            this.add(imagePanel);
            this.add(filler2);

        }

    }

    private class StarPanel extends JPanel {

        public StarPanel() {

            ImageIcon star = new ImageIcon("src/images/starAchieved_small.png");
            JLabel starLabel = new JLabel();
            starLabel.setIcon(star);
            this.setBackground(new Color(237, 243, 249));
            starLabel.setAlignmentX(CENTER_ALIGNMENT);

            Box.Filler filler2 = new Box.Filler(new Dimension(2, 2), new Dimension(5, 5), new Dimension(10, 10));

            int starAmount = GameController.getInstance().getGameData().getNumberOfStarsAtTheEnd();
            JLabel amountOfStars = new JLabel(String.valueOf(starAmount) + " / 3");
            amountOfStars.setFont(new Font("Arial", Font.BOLD, 25));
            amountOfStars.setForeground(new Color(50, 34, 151));
            amountOfStars.setAlignmentX(CENTER_ALIGNMENT);

            this.add(starLabel);
            this.add(filler2);
            this.add(amountOfStars);
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        }

    }

    private class TrophyPanel extends JPanel {

        public TrophyPanel() {

            ImageIcon trophy = new ImageIcon("src/images/trophy_small.png");
            JLabel trophyLabel = new JLabel();
            trophyLabel.setIcon(trophy);
            trophyLabel.setBackground(new Color(158, 60, 167));
            trophyLabel.setAlignmentX(CENTER_ALIGNMENT);

            Box.Filler filler2 = new Box.Filler(new Dimension(2, 2), new Dimension(5, 5),
                    new Dimension(10, 10));

            int rank = GameController.getInstance().getGameData().getRankAtTheEnd();
            JLabel rankLabel = new JLabel(String.valueOf(rank) + ". SIJA");
            rankLabel.setFont(new Font("Arial", Font.BOLD, 25));
            rankLabel.setForeground(new Color(50, 34, 151));
            rankLabel.setAlignmentX(CENTER_ALIGNMENT);

            this.add(trophyLabel);
            this.add(filler2);
            this.add(rankLabel);
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.setBackground(new Color(237, 243, 249));

        }

    }

    public void setUpButtonListeners() {
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();

                if (source == newGameButton) {
                    GameController.getInstance().chooseUser();

                } else if (source == quitButton) {
                    GameController.getInstance().showNewGameWindow();
                }

            }
        };

        newGameButton.addActionListener(buttonListener);
        quitButton.addActionListener(buttonListener);

    }

}

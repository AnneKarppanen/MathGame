import javax.swing.*;
import javax.swing.Box.Filler;
import javax.swing.plaf.DimensionUIResource;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class ResultPanel extends JPanel {

    private JButton closeButton;
    private User user;
    private HighscoreChart highScores;

    private JRadioButton additionButton1;
    private JRadioButton substractionButton1;
    private JRadioButton multiplicationButton1;

    private JPanel userResultsByOperation;
    private JPanel userAdditionResults;
    private JPanel userSubstractionResults;
    private JPanel userMultiplicationResults;

    private CardLayout cardLayout1;

    private JRadioButton additionButton2;
    private JRadioButton substractionButton2;
    private JRadioButton multiplicationButton2;

    private JPanel topResultsByOperation;
    private JPanel topAdditionResults;
    private JPanel topSubstractionResults;
    private JPanel topMultiplicationResults;

    private CardLayout cardLayout2;

    ResultPanel(User user, HighscoreChart highScores) {

        this.user = user;
        this.highScores = highScores;
        this.setMinimumSize(new Dimension(1000, 600));
        
        additionButton1 = new JRadioButton("YHTEENLASKU");
        substractionButton1 = new JRadioButton("VÄHENNYSLASKU");
        multiplicationButton1 = new JRadioButton("KERTOLASKU");

        additionButton2 = new JRadioButton("YHTEENLASKU");
        substractionButton2 = new JRadioButton("VÄHENNYSLASKU");
        multiplicationButton2 = new JRadioButton("KERTOLASKU");

        JTabbedPane results = new JTabbedPane();
        results.setPreferredSize(new DimensionUIResource(900, 600));
        results.setFont(new Font("Arial", Font.BOLD, 40));

        JPanel operationSelectionPanel = createOperationPanel(additionButton1, substractionButton1,
                multiplicationButton1);
        /*
         * operationSelectionPanel.setMinimumSize(new Dimension(900, 60));
         * 
         * additionButton = new JRadioButton("YHTEENLASKU");
         * additionButton.setFont(new Font("Arial", Font.BOLD, 25));
         * additionButton.setSelected(true);
         * 
         * Dimension fillerDimension = new Dimension(40,25);
         * Box.Filler filler1 = new Filler(fillerDimension, fillerDimension,
         * fillerDimension);
         * 
         * substractionButton = new JRadioButton("VÄHENNYSLASKU");
         * substractionButton.setFont(new Font("Arial", Font.BOLD, 25));
         * 
         * Box.Filler filler2 = new Filler(fillerDimension, fillerDimension,
         * fillerDimension);
         * 
         * multiplicationButton = new JRadioButton("KERTOLASKU");
         * multiplicationButton.setFont(new Font("Arial", Font.BOLD, 25));
         * 
         * ButtonGroup bGroup = new ButtonGroup();
         * bGroup.add(additionButton);
         * bGroup.add(substractionButton);
         * bGroup.add(multiplicationButton);
         * 
         * operationSelectionPanel.add(additionButton);
         * operationSelectionPanel.add(filler1);
         * operationSelectionPanel.add(substractionButton);
         * operationSelectionPanel.add(filler2);
         * operationSelectionPanel.add(multiplicationButton);
         * operationSelectionPanel.setLayout(new BoxLayout(operationSelectionPanel,
         * BoxLayout.X_AXIS));
         * //operationSelectionPanel.setFont(new Font("Arial", Font.BOLD, 25));
         * 
         * //resultsByOperation = createResultPanel("+");
         * //resultsByOperation.setMinimumSize(new Dimension(1000, 600));
         */
        this.userAdditionResults = createResultPanel("+");
        this.userSubstractionResults = createResultPanel("-");
        this.userMultiplicationResults = createResultPanel("*");
        this.cardLayout1 = new CardLayout();
        this.userResultsByOperation = new JPanel();
        userResultsByOperation.setLayout(cardLayout1);
        userResultsByOperation.add(userAdditionResults, "addition");
        userResultsByOperation.add(userSubstractionResults, "substraction");
        userResultsByOperation.add(userMultiplicationResults, "multiplication");

        JPanel userResults = new JPanel();
        // userResults.setBackground(backgroundGrey);
        userResults.add(operationSelectionPanel);
        userResults.add(userResultsByOperation);

        results.add("OMAT TULOKSET", userResults);

        JPanel operationSelectionPanel2 = createOperationPanel(additionButton2, substractionButton2,
                multiplicationButton2);

        this.topAdditionResults = createTop5Panel("+");
        this.topSubstractionResults = createTop5Panel("-");
        this.topMultiplicationResults = createTop5Panel("*");
        this.cardLayout2 = new CardLayout();
        this.topResultsByOperation = new JPanel();
        topResultsByOperation.setLayout(cardLayout2);
        topResultsByOperation.add(topAdditionResults, "addition2");
        topResultsByOperation.add(topSubstractionResults, "substraction2");
        topResultsByOperation.add(topMultiplicationResults, "multiplication2");

        JPanel top5 = new JPanel();
        top5.add(operationSelectionPanel2);
        top5.add(topResultsByOperation);
        // top5.setBackground(Color.yellow);
        results.add("ENNÄTYKSET TOP 5", top5);

        results.setSelectedComponent(userResults);

        this.add(results);
        setUpButtonListeners();

    }

    public JPanel createTop5Panel(String operation) {

        this.setMinimumSize(new Dimension(1000, 600));
        Color backgroundGrey = new Color(221, 221, 221);
        JPanel recordPanel = new JPanel();
        //recordPanel.setMinimumSize(new Dimension(1000, 600));
        recordPanel.setLayout(new BoxLayout(recordPanel, BoxLayout.Y_AXIS));
        JPanel titlePanel = new JPanel();
        titlePanel.add(createJLabel(operation));
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        titlePanel.setBackground(backgroundGrey);
        // titlePanel.setMaximumSize(new Dimension(900, 120));

        JLabel rank = new JLabel(" SIJA     ");
        rank.setFont(new Font("Arial", Font.PLAIN, 25));
        JLabel playerName = new JLabel("PELAAJA   ");
        playerName.setFont(new Font("Arial", Font.PLAIN, 25));
        JLabel points = new JLabel("PISTEET  ");
        points.setFont(new Font("Arial", Font.PLAIN, 25));

        //JPanel titles = new JPanel();
        //rank.setPreferredSize(new Dimension(125, 30));
       // titles.add(rank);
        //titles.add(playerName);
        //titles.add(points);
        //titles.setLayout(new BoxLayout(titles, BoxLayout.X_AXIS));
        JPanel top5Panel = new JPanel();
        top5Panel.setLayout(new GridLayout(6, 3));
        
        top5Panel.add(rank);
        top5Panel.add(playerName);
        top5Panel.add(points);
        
        //top5Panel.setLayout(new BoxLayout(top5Panel, BoxLayout.Y_AXIS));
        
        LinkedList<HighscoreLine> records = highScores.getHighScoresByOperation(operation);
        int rankOnList = 1;
        Color blueBackgroud = new Color(206, 225, 242);
        Color lightBackground = new Color(229, 229, 229);
        for (HighscoreLine highScore : records) {

            JLabel rankLabel = new JLabel(" " + String.valueOf(rankOnList) + ".");
            rankLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            rankLabel.setOpaque(true);
            if(rankOnList == 2 || rankOnList == 4) {
                rankLabel.setBackground(blueBackgroud);
            } else {
                rankLabel.setBackground(lightBackground);
            }

            String name = highScore.getUsername();
            JLabel nameLabel = new JLabel(name);
            nameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            nameLabel.setOpaque(true);
            if(rankOnList == 2 || rankOnList == 4) {
                nameLabel.setBackground(blueBackgroud);
            } else {
                nameLabel.setBackground(lightBackground);
            }

            int points1 = highScore.getPoints();
            JLabel pointLabel = new JLabel(String.valueOf(points1));
            pointLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            pointLabel.setOpaque(true);
            if(rankOnList == 2 || rankOnList == 4) {
                pointLabel.setBackground(blueBackgroud);
            } else {
                pointLabel.setBackground(lightBackground);
            }

            top5Panel.add(rankLabel);
            top5Panel.add(nameLabel);
            top5Panel.add(pointLabel);

            /*JPanel recordLine = new JPanel();
            recordLine.add(rankLabel);
            recordLine.add(nameLabel);
            recordLine.add(pointLabel);

            top5Panel.add(recordLine);*/

            rankOnList++;
        }

        if (rankOnList < 6) {
            int i = (6 - rankOnList) * 3;
            while(i > 0) {
                JLabel fillerLabel = new JLabel();
                ImageIcon fillerIcon = new ImageIcon("src/images/fillerImage.png");
                fillerLabel.setIcon(fillerIcon);

                top5Panel.add(fillerLabel);
                i--;

            }
        }

        top5Panel.setBackground(backgroundGrey);
        // starsByRangeAndMaximum.setMinimumSize(new Dimension(900, 500));

        recordPanel.add(titlePanel);
        //recordPanel.add(titles);
        recordPanel.add(top5Panel);

        return recordPanel;

    }

    public JPanel createOperationPanel(JRadioButton additionButton, JRadioButton substractionButton,
            JRadioButton multiplicationButton) {

        JPanel operationSelectionPanel = new JPanel();

        operationSelectionPanel.setMinimumSize(new Dimension(900, 60));

        //additionButton = new JRadioButton("YHTEENLASKU");
        additionButton.setFont(new Font("Arial", Font.BOLD, 25));
        additionButton.setSelected(true);

        Dimension fillerDimension = new Dimension(40, 25);
        Box.Filler filler1 = new Filler(fillerDimension, fillerDimension, fillerDimension);

        //substractionButton = new JRadioButton("VÄHENNYSLASKU");
        substractionButton.setFont(new Font("Arial", Font.BOLD, 25));

        Box.Filler filler2 = new Filler(fillerDimension, fillerDimension, fillerDimension);

        //multiplicationButton = new JRadioButton("KERTOLASKU");
        multiplicationButton.setFont(new Font("Arial", Font.BOLD, 25));

        ButtonGroup bGroup = new ButtonGroup();
        bGroup.add(additionButton);
        bGroup.add(substractionButton);
        bGroup.add(multiplicationButton);

        operationSelectionPanel.add(additionButton);
        operationSelectionPanel.add(filler1);
        operationSelectionPanel.add(substractionButton);
        operationSelectionPanel.add(filler2);
        operationSelectionPanel.add(multiplicationButton);
        operationSelectionPanel.setLayout(new BoxLayout(operationSelectionPanel, BoxLayout.X_AXIS));
        // operationSelectionPanel.setFont(new Font("Arial", Font.BOLD, 25));

        return operationSelectionPanel;
    }

    public JLabel createJLabel(String operation) {

        String title = "";

        if (operation.equals("+")) {
            title = " YHTEENLASKU +";
        } else if (operation.equals("-")) {
            title = " VÄHENNYSLASKU -";
        } else if (operation.equals("*")) {
            title = " KERTOLASKU *";
        }

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));

        return titleLabel;
    }

    public JPanel createResultPanel(String operation) {

        this.setMinimumSize(new Dimension(1000, 600));
        Color backgroundGrey = new Color(221, 221, 221);
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        JPanel titlePanel = new JPanel();
        titlePanel.add(createJLabel(operation));
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        titlePanel.setBackground(backgroundGrey);
        // titlePanel.setMaximumSize(new Dimension(900, 120));

        JPanel starsByRangeAndMaximum = new JPanel();
        JLabel range = new JLabel(" LUKUALUE");
        range.setFont(new Font("Arial", Font.PLAIN, 25));
        JLabel level1 = new JLabel("HELPPO");
        level1.setFont(new Font("Arial", Font.PLAIN, 25));
        JLabel level2 = new JLabel("KESKIVAIKEA");
        level2.setFont(new Font("Arial", Font.PLAIN, 25));
        JLabel level3 = new JLabel("VAIKEA");
        level3.setFont(new Font("Arial", Font.PLAIN, 25));

        JLabel range1 = new JLabel(" 0-10");
        range1.setFont(new Font("Arial", Font.PLAIN, 25));
        JLabel range2 = new JLabel(" 0-20");
        range2.setFont(new Font("Arial", Font.PLAIN, 25));
        JLabel range3 = new JLabel(" 0-100");
        range3.setFont(new Font("Arial", Font.PLAIN, 25));

        starsByRangeAndMaximum.setLayout(new GridLayout(4, 4));
        range.setPreferredSize(new Dimension(225, 112));
        starsByRangeAndMaximum.add(range);
        starsByRangeAndMaximum.add(level1);
        starsByRangeAndMaximum.add(level2);
        starsByRangeAndMaximum.add(level3);

        starsByRangeAndMaximum.add(range1);
        starsByRangeAndMaximum.add(makeStarLabel(0));
        starsByRangeAndMaximum.add(makeStarLabel(0));
        int stars = user.getStars(operation, 10, 3);
        starsByRangeAndMaximum.add(makeStarLabel(stars));

        starsByRangeAndMaximum.add(range2);
        starsByRangeAndMaximum.add(makeStarLabel(0));
        starsByRangeAndMaximum.add(makeStarLabel(0));
        stars = user.getStars(operation, 20, 3);
        starsByRangeAndMaximum.add(makeStarLabel(stars));

        starsByRangeAndMaximum.add(range3);
        starsByRangeAndMaximum.add(makeStarLabel(0));
        starsByRangeAndMaximum.add(makeStarLabel(0));
        stars = user.getStars(operation, 100, 3);
        starsByRangeAndMaximum.add(makeStarLabel(stars));

        starsByRangeAndMaximum.setBackground(backgroundGrey);
        // starsByRangeAndMaximum.setMinimumSize(new Dimension(900, 500));

        resultPanel.add(titlePanel);
        resultPanel.add(starsByRangeAndMaximum);

        return resultPanel;

    }

    private JLabel makeStarLabel(int numberOfStars) {

        JLabel starLabel = new JLabel();
        ImageIcon starIcon = null;

        if (numberOfStars == 0) {
            starIcon = new ImageIcon("src/images/NoStars.png");
        } else if (numberOfStars == 1) {
            starIcon = new ImageIcon("src/images/OneStar.png");
        } else if (numberOfStars == 2) {
            starIcon = new ImageIcon("src/images/TwoStars.png");
        } else if (numberOfStars == 3) {
            starIcon = new ImageIcon("src/images/ThreeStars.png");
        }

        starLabel.setIcon(starIcon);

        return starLabel;
    }

    /*
     * public void changeResultPanel(String operation) {
     * remove(this.resultsByOperation);
     * this.resultsByOperation = createResultPanel(operation);
     * this.repaint();
     * this.revalidate();
     * }
     */

    public void setUpButtonListeners() {
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == closeButton) {
                    System.out.println("closeButton clicked");

                } else if (source == additionButton1) {
                    System.out.println("additionButton clicked");
                    cardLayout1.show(userResultsByOperation, "addition");

                    // changeResultPanel("+");
                } else if (source == substractionButton1) {
                    System.out.println("substractionButton clicked");
                    cardLayout1.show(userResultsByOperation, "substraction");
                    // changeResultPanel("-");
                } else if (source == multiplicationButton1) {
                    System.out.println("multiplicationButton clicked");
                    cardLayout1.show(userResultsByOperation, "multiplication");
                    // changeResultPanel("*");
                } else if (source == additionButton2) {
                    // System.out.println("radioButtonHelppo clicked");
                    cardLayout2.show(topResultsByOperation, "addition2");
                    ;
                } else if (source == substractionButton2) {
                    // System.out.println("radioButtonKeskivaikea clicked");
                    cardLayout2.show(topResultsByOperation, "substraction2");

                } else if (source == multiplicationButton2) {
                    // System.out.println("jRadioVaikea clicked");
                    cardLayout2.show(topResultsByOperation, "multiplication2");
                } /*
                   * else if (source == backButton) {
                   * GameController.getInstance().showChooseOperationPanel();
                   * System.out.println("Klikattiin backButtonia");
                   * }
                   */
            }
        };

        // closeButton.addActionListener(buttonListener);
        additionButton1.addActionListener(buttonListener);
        substractionButton1.addActionListener(buttonListener);
        multiplicationButton1.addActionListener(buttonListener);
        additionButton2.addActionListener(buttonListener);
        substractionButton2.addActionListener(buttonListener);
        multiplicationButton2.addActionListener(buttonListener);
        //backButton.addActionListener(buttonListener);

    }

}

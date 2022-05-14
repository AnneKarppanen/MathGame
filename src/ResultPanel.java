import javax.swing.*;
import javax.swing.Box.Filler;
import javax.swing.plaf.DimensionUIResource;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
        this.setBackground(new Color(237, 243, 249));

        additionButton1 = new JRadioButton("YHTEENLASKU");
        substractionButton1 = new JRadioButton("VÄHENNYSLASKU");
        multiplicationButton1 = new JRadioButton("KERTOLASKU");

        additionButton2 = new JRadioButton("YHTEENLASKU");
        substractionButton2 = new JRadioButton("VÄHENNYSLASKU");
        multiplicationButton2 = new JRadioButton("KERTOLASKU");

        closeButton = new JButton("SULJE");
        closeButton.setBackground(new Color(255, 164, 58));
        closeButton.setFont((new Font("Arial", Font.BOLD, 40)));
        JPanel closeButtonPanel1 = new JPanel();
        closeButtonPanel1.add(closeButton);
        closeButtonPanel1.setBackground(new Color(237, 243, 249));

        JTabbedPane results = new JTabbedPane();
        results.setFont(new Font("Arial", Font.BOLD, 40));

        JPanel operationSelectionPanel = createOperationPanel(additionButton1, substractionButton1,
                multiplicationButton1);

        if (this.user != null) {
            this.userAdditionResults = createResultPanel("+");
            userAdditionResults.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.userSubstractionResults = createResultPanel("-");
            userSubstractionResults.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.userMultiplicationResults = createResultPanel("*");
            userMultiplicationResults.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            Color backgroundGray = new Color(231, 231, 231);
            JLabel noUserLabel1 = new JLabel("PELAAJAA EI OLE VALITTU");
            noUserLabel1.setFont(new Font("Arial", Font.BOLD, 25));
            noUserLabel1.setOpaque(true);

            this.userAdditionResults = new JPanel();
            userAdditionResults.setBackground(backgroundGray);

            this.userSubstractionResults = new JPanel();
            userSubstractionResults.setBackground(backgroundGray);

            this.userMultiplicationResults = new JPanel();
            userMultiplicationResults.setBackground(backgroundGray);

        }

        this.cardLayout1 = new CardLayout();
        this.userResultsByOperation = new JPanel();
        userResultsByOperation.setLayout(cardLayout1);
        userResultsByOperation.add(userAdditionResults, "addition");
        userResultsByOperation.add(userSubstractionResults, "substraction");
        userResultsByOperation.add(userMultiplicationResults, "multiplication");

        JPanel userResults = new JPanel();
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
        top5.setLayout(new BoxLayout(top5, BoxLayout.Y_AXIS));

        JPanel top5WithFillers = new JPanel();
        top5WithFillers.setLayout(new BoxLayout(top5WithFillers, BoxLayout.X_AXIS));
        Dimension sideFillerMinDimension = new Dimension(5, 5);
        Dimension sideFillerPreferredDimension = new Dimension(50, 50);
        Dimension sideFillerMaxDimension = new Dimension(50, 50);

        Box.Filler sideFiller1 = new Filler(sideFillerMinDimension, sideFillerPreferredDimension,
                sideFillerMaxDimension);
        Box.Filler sideFiller2 = new Filler(sideFillerMinDimension, sideFillerPreferredDimension,
                sideFillerMaxDimension);

        top5WithFillers.add(sideFiller1);
        top5WithFillers.add(top5);
        top5WithFillers.add(sideFiller2);

        results.add("ENNÄTYKSET TOP 5", top5WithFillers);

        results.setSelectedComponent(userResults);

        Box.Filler filler = new Filler(new Dimension(10, 10), new Dimension(25, 25), new Dimension(30, 30));

        this.add(results);
        this.add(filler);
        this.add(closeButtonPanel1);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentY(CENTER_ALIGNMENT);
        setUpButtonListeners();

    }

    public JPanel createTop5Panel(String operation) {

        Color darkerBackgroundGrey = new Color(221, 221, 221);
        Color backgroundGrey = new Color(231, 231, 231);
        JPanel recordPanel = new JPanel();
        recordPanel.setLayout(new BoxLayout(recordPanel, BoxLayout.Y_AXIS));
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = createJLabel(operation);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(darkerBackgroundGrey);
        titlePanel.add(titleLabel);
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        titlePanel.setBackground(darkerBackgroundGrey);
        titlePanel.setMaximumSize(new Dimension(900, 240));

        Font titleLineFont = new Font("Arial", Font.PLAIN, 25);
        Map<TextAttribute, Object> attributes = new HashMap<>(titleLineFont.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

        JLabel rank = new JLabel("SIJA");
        rank.setFont(titleLineFont);
        rank.setFont(titleLineFont.deriveFont(attributes));
        JLabel playerName = new JLabel("PELAAJA");
        playerName.setFont(titleLineFont);
        playerName.setFont(titleLineFont.deriveFont(attributes));
        JLabel points = new JLabel("PISTEET");
        points.setFont(new Font("Arial", Font.PLAIN, 25));
        points.setFont(titleLineFont.deriveFont(attributes));

        JPanel top5Panel = new JPanel();
        top5Panel.setLayout(new GridLayout(6, 3));

        top5Panel.add(rank);
        top5Panel.add(playerName);
        top5Panel.add(points);

        LinkedList<HighscoreLine> records = highScores.getHighScoresByOperation(operation);
        int rankOnList = 1;
        Color blueBackgroud = new Color(206, 225, 242);
        Color lightBackground = Color.WHITE;
        Font top5Font = new Font("Arial", Font.PLAIN, 20);
        for (HighscoreLine highScore : records) {

            JLabel rankLabel = new JLabel(" " + String.valueOf(rankOnList) + ".");
            rankLabel.setFont(top5Font);
            rankLabel.setOpaque(true);
            if (rankOnList == 2 || rankOnList == 4) {
                rankLabel.setBackground(blueBackgroud);
            } else {
                rankLabel.setBackground(lightBackground);
            }

            String name = highScore.getUsername();
            JLabel nameLabel = new JLabel(name);
            nameLabel.setFont(top5Font);
            nameLabel.setOpaque(true);
            if (rankOnList == 2 || rankOnList == 4) {
                nameLabel.setBackground(blueBackgroud);
            } else {
                nameLabel.setBackground(lightBackground);
            }

            int points1 = highScore.getPoints();
            JLabel pointLabel = new JLabel(String.valueOf(points1));
            pointLabel.setFont(top5Font);
            pointLabel.setOpaque(true);
            if (rankOnList == 2 || rankOnList == 4) {
                pointLabel.setBackground(blueBackgroud);
            } else {
                pointLabel.setBackground(lightBackground);
            }

            top5Panel.add(rankLabel);
            top5Panel.add(nameLabel);
            top5Panel.add(pointLabel);

            rankOnList++;
        }

        if (rankOnList < 6) {
            int i = (6 - rankOnList) * 3;
            while (i > 0) {
                JLabel fillerLabel = new JLabel();
                ImageIcon fillerIcon = new ImageIcon("src/images/fillerImage.png");
                fillerLabel.setIcon(fillerIcon);

                top5Panel.add(fillerLabel);
                i--;

            }
        }

        top5Panel.setBackground(backgroundGrey);
        top5Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Box.Filler filler3 = new Filler(new Dimension(5, 5), new Dimension(10, 10), new Dimension(10, 10));
        filler3.setBackground(darkerBackgroundGrey);

        recordPanel.add(titlePanel);
        recordPanel.add(top5Panel);
        recordPanel.add(filler3);
        recordPanel.setBackground(darkerBackgroundGrey);

        JPanel panelToReturn = new JPanel();
        panelToReturn.setLayout(new BoxLayout(panelToReturn, BoxLayout.X_AXIS));

        Dimension minimumFiller = new Dimension(10, 10);
        Dimension preferredFiller = new Dimension(150, 150);
        Dimension maximumFiller = new Dimension(200, 200);
        Box.Filler filler1 = new Filler(minimumFiller, preferredFiller, maximumFiller);
        filler1.setBackground(darkerBackgroundGrey);
        Box.Filler filler2 = new Filler(minimumFiller, preferredFiller, maximumFiller);
        panelToReturn.add(filler1);
        panelToReturn.add(recordPanel);
        panelToReturn.add(filler2);
        filler2.setBackground(darkerBackgroundGrey);
        panelToReturn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelToReturn.setBackground(darkerBackgroundGrey);

        return panelToReturn;

    }

    public JPanel createOperationPanel(JRadioButton additionButton, JRadioButton substractionButton,
            JRadioButton multiplicationButton) {

        JPanel operationSelectionPanel = new JPanel();
        operationSelectionPanel.setMinimumSize(new Dimension(900, 60));

        additionButton.setFont(new Font("Arial", Font.BOLD, 25));
        additionButton.setSelected(true);

        Dimension fillerMinDimension = new Dimension(20, 40);
        Dimension fillerPreferredDimension = new Dimension(40, 40);
        Dimension fillerMaxDimension = new Dimension(150, 40);

        Box.Filler filler1 = new Filler(fillerMinDimension, fillerPreferredDimension, fillerMaxDimension);

        substractionButton.setFont(new Font("Arial", Font.BOLD, 25));

        Box.Filler filler2 = new Filler(fillerMinDimension, fillerPreferredDimension, fillerMaxDimension);

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
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(231, 231, 231));

        return titleLabel;
    }

    public JPanel createResultPanel(String operation) {

        this.setMinimumSize(new Dimension(1000, 600));
        Color backgroundGrey = new Color(231, 231, 231);
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        JPanel titlePanel = new JPanel();
        titlePanel.add(createJLabel(operation));
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        titlePanel.setBackground(backgroundGrey);

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

    public void setUpButtonListeners() {
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == closeButton) {
                    GameController.getInstance().showPreviousPanel();

                } else if (source == additionButton1) {
                    cardLayout1.show(userResultsByOperation, "addition");
                } else if (source == substractionButton1) {
                    cardLayout1.show(userResultsByOperation, "substraction");
                } else if (source == multiplicationButton1) {
                    System.out.println("multiplicationButton clicked");
                    cardLayout1.show(userResultsByOperation, "multiplication");
                } else if (source == additionButton2) {
                    cardLayout2.show(topResultsByOperation, "addition2");
                } else if (source == substractionButton2) {
                    cardLayout2.show(topResultsByOperation, "substraction2");
                } else if (source == multiplicationButton2) {
                    cardLayout2.show(topResultsByOperation, "multiplication2");
                }
            }
        };

        closeButton.addActionListener(buttonListener);
        additionButton1.addActionListener(buttonListener);
        substractionButton1.addActionListener(buttonListener);
        multiplicationButton1.addActionListener(buttonListener);
        additionButton2.addActionListener(buttonListener);
        substractionButton2.addActionListener(buttonListener);
        multiplicationButton2.addActionListener(buttonListener);

    }

}

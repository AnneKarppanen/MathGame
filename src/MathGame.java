import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

/** The main application class that contains the main function. Mathgame creates the main frame 
 * and the control panel  */
public class MathGame extends JFrame{

    private JPanel centerPanel;
    private JPanel northPanel;
    private JPanel eastPanel;
    private JPanel westPanel;
    private JPanel southPanel;
    //private GameController gameController;
    

    public MathGame() {

        System.out.println("Täällä ollaan, uusi testi");
        GameController gameController = GameController.getInstance();
        gameController.intializeGame(this);
        this.centerPanel = new StartPanel();
        this.northPanel = new ControlPanel();
        this.eastPanel = new JPanel();
        this.westPanel = new JPanel();
        //this.southPanel = new JPanel();
        this.setLayout(new BorderLayout());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(screenSize.width - 20, screenSize.height - 40));
        //this.setMinimumSize(new Dimension(800, 600));
        //int screenWidth = screenSize.width;
        //int sceenHeight = screenSize.height;
        //centerPanel.setBorder(BorderFactory.createEmptyBorder(400, 400, 400, 400));
        //centerPanel.setBackground(new Color (237,243,249));
        //northPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));
        //northPanel.setBackground(new Color (50,34,151));
        eastPanel.setBorder(BorderFactory.createEmptyBorder(60, 60, 20, 60));
        eastPanel.setBackground(new Color (206,225,242));
        westPanel.setBorder(BorderFactory.createEmptyBorder(60,60, 20, 60));
        westPanel.setBackground(new Color (206,225,242));
        //southPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        //southPanel.setBackground(new Color (237,243,249));

        /*northPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

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

       
        centerPanel.setLayout(new GridBagLayout());
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
       
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(northPanel, BorderLayout.NORTH);
        this.add(westPanel, BorderLayout.WEST);
        this.add(eastPanel, BorderLayout.EAST);
        
        //this.add(southPanel, BorderLayout.SOUTH);

        WindowListener windowListener = new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                /*int response = JOptionPane.showConfirmDialog(null, "HALUATKO SULKEA OHJELMAN?", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }*/
                JDialog closingDialog = new JDialog();
                
                /*double centerPanelWidth = centerPanel.getSize().getWidth();
                double centerPanelheighth = centerPanel.getSize().getHeight();
                closingDialog.setLocationRelativeTo(centerPanel);
                
                int width = (int) centerPanelWidth / 4 + 130;
                int height = (int) centerPanelheighth / 4;
                closingDialog.setLocation(width, height);*/
                

                closingDialog.setResizable(false);
               
                /*System.out.println("width: " + centerPanelWidth);
                System.out.println("height: " + centerPanelheighth);
                System.out.println("centerPanelLocation: " + centerPanel.getLocation());*/
                
                
                closingDialog.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK));
                JPanel contentPanel = new JPanel();
                
                Box.Filler filler1 = new Box.Filler(new Dimension(5,100), new Dimension(10,150), new Dimension(15,150));
                
                JLabel question = new JLabel("HALUATKO SULKEA OHJELMAN?");
                //question.setMinimumSize(new Dimension(200,30));
                //question.setPreferredSize(new Dimension(300, 30));
                //question.setMaximumSize(new Dimension(400, 50));
                question.setFont((new Font("Arial", Font.PLAIN, 25)));
                //question.setBackground(Color.red);
                question.setAlignmentX(CENTER_ALIGNMENT);
                contentPanel.add(filler1);
                contentPanel.add(question);

                JPanel buttonPanel = new JPanel();
                buttonPanel.setBackground(Color.WHITE);
                //buttonPanel.setPreferredSize(new Dimension(600, 100));
                buttonPanel.setMaximumSize(new Dimension(1000, 150));
                //buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 60));
                //buttonPanel.setAlignmentX(CENTER_ALIGNMENT);

                JButton yesButton = new JButton("KYLLÄ");
                yesButton.setBackground(new Color(156, 204, 249));
                yesButton.setFont((new Font("Arial", Font.BOLD, 25)));
                //Border border1 = BorderFactory.createLineBorder(new Color(57, 120, 178));
                //yesButton.setBorder(border1);
                yesButton.setMargin(new Insets(5,5,5,5));
            
                //yesButton.setAlignmentX(CENTER_ALIGNMENT);

                //Box.Filler filler2 = new Box.Filler(new Dimension(5,5), new Dimension(10,10), new Dimension(15,15));
                
                JButton noButton = new JButton("EI");
                noButton.setBackground(new Color(196, 196, 196));
                noButton.setFont((new Font("Arial", Font.BOLD, 25)));
                noButton.setMargin(new Insets(5,32,5,32));
                //noButton.setAlignmentX(CENTER_ALIGNMENT);

                contentPanel.setBackground(Color.WHITE);
                buttonPanel.add(yesButton);
                //buttonPanel.add(filler2);
                buttonPanel.add(noButton);
                contentPanel.add(buttonPanel);
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
                contentPanel.setAlignmentX(CENTER_ALIGNMENT);

                closingDialog.setContentPane(contentPanel);
                //closingDialog.setSize(600, 600);
                closingDialog.setMinimumSize(new Dimension(600, 600));
                getRootPane().setDefaultButton(yesButton);
                closingDialog.setLocationRelativeTo(centerPanel);
                closingDialog.setVisible(true);
                System.out.println("Dialog location: " + closingDialog.getLocation());

                setUpButtonListeners(closingDialog, yesButton, noButton);


            }

            public void setUpButtonListeners(JDialog closingDialog, JButton yesButton, JButton noButton) {
                ActionListener buttonListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Object source = e.getSource();
                            
                        if (source == yesButton) {
                            System.exit(0); 
                                        
                        } else if (source == noButton) {
                            closingDialog.dispose();
                        } 
                        
                    }
                };
        
                yesButton.addActionListener(buttonListener);
                noButton.addActionListener(buttonListener);
        
            }

            

        };

        this.addWindowListener(windowListener);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("Matikkapeli");
        this.pack();
        this.setVisible(true);


    }

    public void changePanel(JPanel panel) {
        this.remove(this.centerPanel);
        this.add(panel, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
        this.centerPanel = panel;

    }

    /*public void changeSouthPanel(JPanel panel) {
        this.remove(this.southPanel);
        this.add(panel, BorderLayout.SOUTH);
        //this.pack();
        this.setVisible(true);
        this.southPanel = panel;

    }*/


{ 

   

   }
    
    
     public static void main(String[] args) {
        //GameController gameController = GameController.getInstance();
        MathGame mathGame = new MathGame();
        //gameController.intializeGame();  
        
    }
}
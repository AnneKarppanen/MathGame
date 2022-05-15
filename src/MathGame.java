import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The main application class that contains the main function. Mathgame creates
 * the main frame and the control panel
 */
public class MathGame extends JFrame {

    private JPanel centerPanel;
    private JPanel northPanel;
    private JPanel eastPanel;
    private JPanel westPanel;

    public MathGame() {

        GameController gameController = GameController.getInstance();
        gameController.intializeGame(this);
        this.centerPanel = new StartPanel();
        GameController.getInstance().setCurrentPanel(centerPanel);
        this.northPanel = new ControlPanel();
        this.eastPanel = new JPanel();
        this.westPanel = new JPanel();
        this.setLayout(new BorderLayout());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(screenSize.width - 20, screenSize.height - 40));
        eastPanel.setBorder(BorderFactory.createEmptyBorder(60, 60, 20, 60));
        eastPanel.setBackground(new Color(206, 225, 242));
        westPanel.setBorder(BorderFactory.createEmptyBorder(60, 60, 20, 60));
        westPanel.setBackground(new Color(206, 225, 242));

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(northPanel, BorderLayout.NORTH);
        this.add(westPanel, BorderLayout.WEST);
        this.add(eastPanel, BorderLayout.EAST);

        /*
         * Window listener for listening the close window action from the user. Also
         * displays a dialog if the user is sure to close the window.
         */
        WindowListener windowListener = new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                JDialog closingDialog = new JDialog();
                closingDialog.setResizable(false);
                closingDialog.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK));

                JPanel contentPanel = new JPanel();
                Box.Filler filler1 = new Box.Filler(new Dimension(5, 100), new Dimension(10, 150),
                        new Dimension(15, 150));

                JLabel question = new JLabel("HALUATKO SULKEA OHJELMAN?");
                question.setFont((new Font("Arial", Font.PLAIN, 25)));
                question.setAlignmentX(CENTER_ALIGNMENT);
                contentPanel.add(filler1);
                contentPanel.add(question);

                JPanel buttonPanel = new JPanel();
                buttonPanel.setBackground(Color.WHITE);
                buttonPanel.setMaximumSize(new Dimension(1000, 150));
                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 60));

                JButton yesButton = new JButton("KYLLÄ");
                yesButton.setBackground(new Color(156, 204, 249));
                yesButton.setFont((new Font("Arial", Font.BOLD, 25)));
                yesButton.setMargin(new Insets(5, 5, 5, 5));

                JButton noButton = new JButton("EI");
                noButton.setBackground(new Color(196, 196, 196));
                noButton.setFont((new Font("Arial", Font.BOLD, 25)));
                noButton.setMargin(new Insets(5, 32, 5, 32));

                contentPanel.setBackground(Color.WHITE);
                buttonPanel.add(yesButton);
                buttonPanel.add(noButton);
                contentPanel.add(buttonPanel);
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
                contentPanel.setAlignmentX(CENTER_ALIGNMENT);

                closingDialog.setContentPane(contentPanel);
                closingDialog.setMinimumSize(new Dimension(600, 600));
                getRootPane().setDefaultButton(yesButton);
                closingDialog.setLocationRelativeTo(centerPanel);
                closingDialog.setVisible(true);

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

    // Method for changing the next Panel to be shown
    public void changePanel(JPanel panel) {
        this.remove(this.centerPanel);
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
        this.centerPanel = panel;
        this.pack();

    }

    // Main method to start the application
    public static void main(String[] args) {
        MathGame mathGame = new MathGame();

    }
}
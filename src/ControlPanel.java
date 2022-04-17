import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** ControlPanel is the area at the top of the frame that contains buttons to navigate back to
 * opening screen, to view the results and to read game istructions. 
 */
public class ControlPanel extends JPanel implements ActionListener {

    private JButton homeButton = null;
    private JButton starButton = null;
    private JButton helpButton = null;

    public ControlPanel() {
        

    }

    JPanel northPanel = new JPanel();
    northPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));
    northPanel.setBackground(new Color (50,34,151));

    northPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

        ImageIcon homeIcon = new ImageIcon("src/images/home2.png");
        homeButton = new JButton(new ImageIcon(homeIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
        homeButton.setPreferredSize(new Dimension(45, 45));

        ImageIcon starIcon = new ImageIcon("src/images/star-images-9472.png");
        starButton = new JButton(new ImageIcon(starIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
        starButton.setPreferredSize(new Dimension(45, 45));

        ImageIcon questionIcon = new ImageIcon("src/images/question-mark-10920.png");
        helpButton = new JButton(new ImageIcon(questionIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
        helpButton.setPreferredSize(new Dimension(45, 45));

        northPanel.add(homeButton);
        northPanel.add(starButton);
        northPanel.add(helpButton);
        homeButton.setBackground(new Color(84, 138, 137));
        starButton.setBackground(new Color(84, 138, 137));
        helpButton.setBackground(new Color(84, 138, 137));

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    
}

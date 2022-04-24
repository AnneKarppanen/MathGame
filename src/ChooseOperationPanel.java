import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChooseOperationPanel extends JPanel{
    private JButton additionButton = null;
    private JButton subtractionButton = null;
    private JButton multiplicationButton = null;
    private JLabel instruction = null;

    public ChooseOperationPanel(){

        

        this.setBorder(BorderFactory.createEmptyBorder(400, 400, 400, 400));
        this.setBackground(new Color(237, 243, 249));
        this.setLayout(new GridBagLayout());
        GridBagConstraints cConstraints = new GridBagConstraints();
        cConstraints.insets = new Insets(30, 30, 30, 30);
        cConstraints.gridx = 0;
        cConstraints.gridy = 0;
        this.instruction = new JLabel("TESTAILLAANPA");
        this.instruction.setFont(new Font("Arial", Font.PLAIN, 25));
        this.add(instruction, cConstraints);
        cConstraints.gridx = 0;
        cConstraints.gridy = 1;


    }
    
}

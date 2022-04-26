import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChooseOperationPanel extends JPanel{
    private JButton additionButton = null;
    private JButton subtractionButton = null;
    private JButton multiplicationButton = null;
    private GameController gameController;

    public ChooseOperationPanel(){
        this.gameController = gameController.getInstance();
        //this.gameController = gameController;
        this.setBorder(BorderFactory.createEmptyBorder(400, 400, 400, 400));
        this.setBackground(new Color(237, 243, 249));
        this.setLayout(new GridBagLayout());
        GridBagConstraints cConstraints = new GridBagConstraints();
        cConstraints.insets = new Insets(20, 20, 20, 20);
 
        
        additionButton = new JButton("YHTEENLASKU +");
        additionButton.setBackground(new Color(255, 164, 58));
        additionButton.setFont((new Font("Arial", Font.BOLD, 20)));
        cConstraints.gridx = 0;
        cConstraints.gridy = 1;
        cConstraints.ipadx = 100; // internal padding x
        cConstraints.ipady = 25; // internal padding y
        additionButton.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(additionButton, cConstraints);

        subtractionButton = new JButton("VÄHENNYSLASKU -");
        subtractionButton.setBackground(new Color(255, 164, 58));
        subtractionButton.setFont((new Font("Arial", Font.BOLD, 20)));
        cConstraints.gridx = 0;
        cConstraints.gridy = 2;
        cConstraints.ipadx = 70; // internal padding x
        cConstraints.ipady = 25; // internal padding y
        subtractionButton.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(subtractionButton, cConstraints);

        multiplicationButton = new JButton("KERTOLASKU *");
        multiplicationButton.setBackground(new Color(255, 164, 58));
        multiplicationButton.setFont((new Font("Arial", Font.BOLD, 20)));
        cConstraints.gridx = 0;
        cConstraints.gridy = 3;
        cConstraints.ipadx = 110; // internal padding x
        cConstraints.ipady = 25; // internal padding y
        multiplicationButton.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(multiplicationButton, cConstraints);

        setUpButtonListeners();
    }

    public void setUpButtonListeners() {
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if(source == additionButton){
                    System.out.println("AdditionButton clicked");
                    gameController.showChooseDifficultyPanel();           
                }else if(source == subtractionButton){
                    //This functionality has been agreed to be optional with teachers, so no implementation.
                    System.out.println("SubtractionButton clicked");
                }else if(source == multiplicationButton){
                    //This functionality has been agreed to be optional with teachers, so no implementation.
                    System.out.println("MultiplicationButton clicked");
                } 

            }
        };

        additionButton.addActionListener(buttonListener);
        subtractionButton.addActionListener(buttonListener);
        multiplicationButton.addActionListener(buttonListener);
    }
    
}

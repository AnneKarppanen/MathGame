import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChooseDifficultyPanel extends JPanel {
    private JButton playButton = null;
    //private GameController gameController;


    public ChooseDifficultyPanel(){
        //this.gameController = gameController;
        //this.gameController = gameController.getInstance();
        this.setBorder(BorderFactory.createEmptyBorder(400, 400, 400, 400));
        this.setBackground(new Color(237, 243, 249));
        this.setLayout(new GridBagLayout());
        GridBagConstraints cConstraints = new GridBagConstraints();
        cConstraints.insets = new Insets(20, 20, 20, 20);
 
        
        playButton = new JButton("PELAA");
        playButton.setBackground(new Color(255, 164, 58));
        playButton.setFont((new Font("Arial", Font.BOLD, 20)));
        cConstraints.gridx = 0;
        cConstraints.gridy = 1;
        cConstraints.ipadx = 60; // internal padding x
        cConstraints.ipady = 25; // internal padding y
        playButton.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(playButton, cConstraints);

        setUpButtonListeners();

    }

    public void setUpButtonListeners() {
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if(source == playButton){
                    System.out.println("playButton clicked");
                    //tästä lähtee sitten eka peli-ikkuna
                    GameController.getInstance().showGamePanel();          
              }
         /*   else if(source == subtractionButton){
                    //This functionality has been agreed to be optional with teachers, so no implementation.
                    System.out.println("SubtractionButton clicked");
                }else if(source == multiplicationButton){
                    //This functionality has been agreed to be optional with teachers, so no implementation.
                    System.out.println("MultiplicationButton clicked");
                }  */

            } 
        };

        playButton.addActionListener(buttonListener);
       // subtractionButton.addActionListener(buttonListener);
       // multiplicationButton.addActionListener(buttonListener);
    }

    
}

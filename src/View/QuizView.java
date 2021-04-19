package View;

import Controller.Controller;
import Model.Slang;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class QuizView {
    JFrame frame;
    JPanel panel;
    JButton[] buttons;
    JLabel label;
    public QuizView(String quizObject){
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(60, 60, 60 ,60 ));
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttons = new JButton[4];
        ArrayList<Slang> slangs = Controller.getInstance().randomSlang(4);
        Random rnd = new Random();

        if(quizObject.compareTo("slang") == 0)
        {
            label = new JLabel(slangs.get(rnd.nextInt(4)).getSlang());
            for(int i=0;i<4;i++) {
                buttons[i] = new JButton(slangs.get(i).meaningsToString());
                buttons[i].setActionCommand(slangs.get(i).meaningsToString());
                buttons[i].addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                Controller.getInstance().CheckSlang(label.getText(),
                                        actionEvent.getActionCommand()
                                );
                            }
                        }
                );
            }
        }
        if(quizObject.compareTo("definition") == 0)
        {
            label = new JLabel(slangs.get(rnd.nextInt(4)).meaningsToString());
            for(int i=0;i<4;i++) {
                buttons[i] = new JButton(slangs.get(i).getSlang());
                buttons[i].setActionCommand(slangs.get(i).getSlang());
                buttons[i].addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                Controller.getInstance().CheckSlang(actionEvent.getActionCommand(), label.getText());
                            }
                        }
                );
            }
        }

        panel.add(label,gbc);
        for(int i=0;i<4;i++){
            panel.add(buttons[i],gbc);
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new JFrame();
                frame.add(panel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}

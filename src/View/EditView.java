package View;

import Controller.Controller;
import Model.Slang;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditView {
    JFrame frame;
    JPanel panel;
    JButton button;
    JTextField[] meaningsTextField;
    JTextField slangTextField;
    int count;
    public EditView(Slang slang){
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(60, 60, 60 ,60 ));
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        if(slang == null) return;
        panel.add(new JLabel("Edit slang"), gbc);
        panel.add(new JLabel("Slang: "));
        slangTextField = new JTextField(slang.getSlang());
        panel.add(slangTextField,gbc);
        meaningsTextField = new JTextField[100];
        count = 0;
        for(String i : slang.getMeanings()){
            panel.add(new JLabel("Meaning: "));
            meaningsTextField[count] = new JTextField(i);
            panel.add(meaningsTextField[count], gbc);
            count++;
        }
        button = new JButton("Edit");
        button.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        Slang _new = new Slang(slangTextField.getText());
                        for(int i=0;i<count;i++)
                            _new.addMeaning(meaningsTextField[i].getText());
                        Controller.getInstance().overwriteSlang(slang, _new);
                    }
                }
        );
        panel.add(button,gbc);

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

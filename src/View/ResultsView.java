package View;

import Model.Slang;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ResultsView {
    JFrame frame;
    JPanel panel;
    JTextField textField;
    public ResultsView(ArrayList<Slang> slangs){

        panel = new JPanel();
        panel.setBorder(new EmptyBorder(60, 60, 60 ,60 ));
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        if(slangs.size() == 0) panel.add(new JLabel("Empty"));
        for(Slang i : slangs){
            for(String j : i.getMeanings()){
                panel.add(new JLabel("slang: "));
                panel.add(new TextField(i.getSlang()));
                panel.add(new JLabel("Definition:"));
                panel.add(new TextField(j), gbc);
            }
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

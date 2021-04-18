package View;
import Controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class AddView {

    private class AddPanel extends JPanel{
        JTextField slangTextField;
        JTextField meaningTextField;
        JButton button;
        public AddPanel(){
            setBorder(new EmptyBorder(60, 60, 60 ,60 ));
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.NORTH;

            gbc.anchor = GridBagConstraints.EAST;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            add(new JLabel("Slang: "));
            slangTextField = new JTextField("", 15);
            add(slangTextField,gbc);

            add(new JLabel("Definition: "));
            meaningTextField = new JTextField("", 15);
            add(meaningTextField, gbc);

            button = new JButton("Add Slang");
            button.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            Controller.getInstance().addSlang(
                                    slangTextField.getText(),
                                    meaningTextField.getText()
                                );
                            }
                    }
            );
            add(button);

        }

    }
    public AddView(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.add(new AddView.AddPanel());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}

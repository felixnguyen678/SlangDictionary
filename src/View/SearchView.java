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

public class SearchView {

    private class SearchPanel extends JPanel{
        JTextField textField;
        JButton button;
        public SearchPanel(String searchObject){
            setBorder(new EmptyBorder(60, 60, 60 ,60 ));
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.NORTH;
            add(new JLabel(searchObject+": "));
            textField = new JTextField("", 15);
            textField.setActionCommand(searchObject);
            textField.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            if(actionEvent.getActionCommand().compareTo("slang") == 0){
                                Controller.getInstance().searchBySlang(textField.getText());
                            }
                            if(actionEvent.getActionCommand().compareTo("keyword")==0){
                                Controller.getInstance().searchByKeyword(textField.getText());
                            }
                            if(actionEvent.getActionCommand().compareTo("delete slang")==0){
                                Controller.getInstance().deleteSlang(textField.getText());
                            }
                            if(actionEvent.getActionCommand().compareTo("edit slang")==0){
                                Controller.getInstance().editSlang(textField.getText());
                            }
                        }
                    }
            );
            add(textField);

            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            button = new JButton("Search");
            button.setActionCommand(searchObject);
            button.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            if(actionEvent.getActionCommand().compareTo("slang") == 0){
                                Controller.getInstance().searchBySlang(textField.getText());
                            }
                            if(actionEvent.getActionCommand().compareTo("keyword")==0){
                                Controller.getInstance().searchByKeyword(textField.getText());
                            }
                            if(actionEvent.getActionCommand().compareTo("delete slang")==0){
                                Controller.getInstance().deleteSlang(textField.getText());
                            }
                            if(actionEvent.getActionCommand().compareTo("edit slang")==0){
                                Controller.getInstance().editSlang(textField.getText());
                            }
                        }
                    }
            );
            add(button);

        }

    }
    public SearchView(String searchObject){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.add(new SearchView.SearchPanel(searchObject));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}

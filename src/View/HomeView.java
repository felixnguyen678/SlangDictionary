package View;

import Controller.Controller;
import Model.Repository;

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

public class HomeView{



    public class MenuPane extends JPanel{
        private JButton searchBySlangButton;
        private JButton searchByKeywordButton;
        private JButton historyButton;
        private JButton addSlangButton;
        private JButton editSlangButton;
        private JButton deleteSlangButton;
        private JButton resetButton;
        private JButton randomButton;
        private JButton slangQuizButton;
        private JButton definitionQuizButton;
        public MenuPane() {
            setBorder(new EmptyBorder(60, 60, 60 ,60 ));
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.NORTH;

            add(new JLabel("<html><h1><strong><i>Slang Dictionary</i></strong></h1><hr></html>"), gbc);

            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            searchBySlangButton = new JButton("Search by Slang");
            searchBySlangButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            SearchView searchView = new SearchView("slang");
                        }
                    }
            );
            this.searchByKeywordButton = new JButton("Search by keyword");
            searchByKeywordButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            SearchView searchView = new SearchView("keyword");
                        }
                    }
            );
            this.historyButton = new JButton(("History"));
            historyButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            ResultsView resultsView = new ResultsView(
                                    Controller.getInstance().getSlangHistory()
                            );
                        }
                    }
            );
            this.addSlangButton = new JButton("Add Slang");
            addSlangButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            AddView addView = new AddView();
                        }
                    }
            );
            this.editSlangButton = new JButton("Edit Slang");
            editSlangButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            SearchView searchView = new SearchView("edit slang");
                        }
                    }
            );
            this.deleteSlangButton = new JButton("Delete Slang");
            deleteSlangButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            SearchView searchView = new SearchView("delete slang");
                        }
                    }
            );
            this.resetButton = new JButton("Reset Dictionary");
            resetButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            Controller.getInstance().resetDictionary();
                        }
                    }
            );
            this.randomButton = new JButton("Random Slang");
            randomButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            ResultsView resultsView = new ResultsView(
                                    Controller.getInstance().randomSlang(1)
                            );
                        }
                    }
            );
            this.slangQuizButton = new JButton("Slang Quiz");
            slangQuizButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            QuizView quizView = new QuizView("slang");
                        }
                    }
            );
            this.definitionQuizButton = new JButton("Definition Quiz");
            definitionQuizButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            QuizView quizView = new QuizView("definition");

                        }
                    }
            );

            // group of buttons
            JPanel buttons = new JPanel(new GridBagLayout());
                buttons.add(searchBySlangButton, gbc);
                buttons.add(searchByKeywordButton, gbc);
                buttons.add(historyButton, gbc);
                buttons.add(addSlangButton, gbc);
                buttons.add(editSlangButton, gbc);
                buttons.add(deleteSlangButton, gbc);
                buttons.add(resetButton, gbc);
                buttons.add(randomButton, gbc);
                buttons.add(slangQuizButton, gbc);
                buttons.add(definitionQuizButton, gbc);

            gbc.weighty = 1;
            add(buttons, gbc);
        }

    }
    public HomeView(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.add(new MenuPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

}

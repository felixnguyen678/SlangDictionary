package Controller;

import Model.*;
import View.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    //singleton

    private static Controller instance = new Controller();
    private Controller(){}

    public static Controller getInstance() {
        return instance;
    }



    public void searchBySlang(String _slang){
            ArrayList<Slang> slangs = new ArrayList<Slang>();
            Slang req = new Slang(_slang);
            Slang res = Repository.getInstance().findBySlang(req);
            if(res == null){
                JOptionPane.showMessageDialog(null, "We do not have any slang like this","Search warning", JOptionPane.WARNING_MESSAGE);
                req.addMeaning("This slang doesn't exist");
                Repository.getInstance().addSlangHistory(req);
                slangs.add(req);
                return;
            }
            else{
                Repository.getInstance().addSlangHistory(res);
                slangs.add(res);
            }
        ResultsView resultsView = new ResultsView(slangs);

    }
    public void searchByKeyword(String _keyword){
        Keyword keyword = Repository.getInstance().findByKeyword(
                new Keyword(_keyword)
        );
        if(keyword == null){
            JOptionPane.showMessageDialog(null, "We do not have any slang like this","Search warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        ResultsView resultsView = new ResultsView(keyword.getSlangs());

    }
    public ArrayList<Slang> getSlangHistory(){
        return Repository.getInstance().getSlangHistory();
    }
    public void addSlang(String _slang, String _meaning){
        Slang slang = new Slang(_slang, _meaning);
        Slang check = Repository.getInstance()
                .findBySlang(slang);
        if(check == null){
            Repository.getInstance().addSlang(slang);
            JOptionPane.showMessageDialog(null,"Successfully Added !!!");
        }
        else{
            String[] options = {"Overwrite", "Dupplicate"};
            int i = JOptionPane.showOptionDialog(null, "Overwrite or duplicate", "Select an Option",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if(i == 0){
                Repository.getInstance().overwriteSlang(slang);
                JOptionPane.showMessageDialog(null,"Successfully Overwrited !!!");
            }
            if(i == 1){
                Repository.getInstance().duplicateSlang(slang);
                JOptionPane.showMessageDialog(null,"Successfully Duplicated !!!");
            }
        }
    }
    public void deleteSlang(String _slang){
        Slang slang = new Slang(_slang);
        if(Repository.getInstance().findBySlang(slang) == null){
            JOptionPane.showConfirmDialog(null, "We do not have any slang like this");

        }else{
            Repository.getInstance().deleteSlang(slang);
            JOptionPane.showMessageDialog(null, "Successfully Deleted !!!");
        }
    }
    public void resetDictionary(){
        Repository.getInstance().reset("slang.txt");
        JOptionPane.showMessageDialog(null, "Successfully reseted !!!");

    }
    public ArrayList<Slang> randomSlang(int number){
        ArrayList<Slang> slangs = new ArrayList<Slang>();
        for(int i=0;i<number;i++)
            slangs.add(Repository.getInstance().randomSlang());
        return slangs;
    }
    public void editSlang(String _slang){
        Slang req = new Slang(_slang);
        Slang res = Repository.getInstance().findBySlang(req);
        if(res == null){
            JOptionPane.showMessageDialog(null, "We do not have any slang like this","Search warning", JOptionPane.WARNING_MESSAGE);
            req.addMeaning("This slang doesn't exist");
            Repository.getInstance().addSlangHistory(req);
            return;
        }
        else{
            Repository.getInstance().addSlangHistory(res);
        }
        EditView editView = new EditView(res);
    }
    public void overwriteSlang(Slang _old, Slang _new){
        Repository.getInstance().deleteSlang(_old);
        Repository.getInstance().addSlang(_new);
        JOptionPane.showMessageDialog(null, "Successful !!!");
    }
    public void CheckSlang(String _slang, String _meaningsToString){
        Slang res = Repository.getInstance().findBySlang(new Slang(_slang));
        if(res.meaningsToString().compareTo(_meaningsToString) == 0)
            JOptionPane.showMessageDialog(null, "Congraculation, you have been right !!!");
        else
            JOptionPane.showMessageDialog(null, "Sorry, your choice has been wrong !!!");

    }
}
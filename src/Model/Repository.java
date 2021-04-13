package Model;

import java.security.Key;
import java.util.ArrayList;

public class Repository {
    AVLTree<Slang> slangAVLTree;
    AVLTree<Keyword> keywordAVLTree;
    ArrayList<Slang> slangHistory;
    ArrayList<Keyword> keywordHistory;

    // singleton
    private static final Repository instance = new Repository();
    private Repository(){ }
    public static Repository getInstance(){ return instance;}


    //import data
    public void import_data(String _filepath){

    }
    // export data
    public void export_data(String _filepath){
    }

    // get history
    public ArrayList<Slang> getSlangHistory(){
        return slangHistory;
    }
    public ArrayList<Keyword> getKeywordHistory() { return keywordHistory;}

    // add to history
    public void addSlangHistory(Slang _slang){
        slangHistory.add(_slang);
    }
    public void addKeywordHistory(Keyword _keyword) { keywordHistory.add(_keyword); };
    //    find by slang
    public Slang findBySlang(Slang req){
        Slang res = slangAVLTree.find(req);

        if(res != null){    // if there are not any slang, construct new Slang and put into history and return null
            req.addMeaning("This slang does not exist");
            slangHistory.add(req);
            return req;
        } else{         //else put into history and return slang
            slangHistory.add(res);
            return res;
        }
    }
    //  find by Keyword
    public ArrayList<Slang> findByKeyword(Keyword req){
        Keyword res = keywordAVLTree.find(req);

        if(res != null) {
            ArrayList<Slang> res_slang_set = new ArrayList<Slang>();
            Slang res_slang = new Slang(
                    "There are not any slang like this",
                    "");
            req.addSlang(res_slang);
            keywordHistory.add(req);
            return res_slang_set;
        } else{
            keywordHistory.add(res);
            return res.getSlangs();
        }
    }
    // split key words
    public void addSlang(Slang _slang){
        // add to slang tree and keyword tree both
        slangAVLTree.insert(_slang);
        for(String item : _slang.meanings){
            String[] kw = item.split("//W+");

        }

    }
    //  overwrite ( delete and insert)
    public void overwriteSlang(Slang _slang){
    }

}

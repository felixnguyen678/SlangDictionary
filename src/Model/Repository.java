package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;

public class Repository {
    public AVLTree<Slang> slangAVLTree;
    private AVLTree<Keyword> keywordAVLTree;
    private ArrayList<Slang> slangHistory;
    private ArrayList<Keyword> keywordHistory;
    // singleton
    private static Repository instance = new Repository();
    private Repository(){
        slangAVLTree = new AVLTree<Slang>();
        keywordAVLTree = new AVLTree<Keyword>();
        slangHistory = new ArrayList<Slang>();
        keywordHistory = new ArrayList<Keyword>();
    }
    public static Repository getInstance(){ return instance;}



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

        if(res == null){    // if there are not any slang, construct new Slang and put into history and return null
            //System.out.println("This slang does not exist");
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
        //slangAVLTree.insert( new Slang("sdf", "sdf"));
        /*
        for(String item : _slang.meanings){
            String[] kw = item.split("//W+");

        }
        */
    }
    //  overwrite ( delete and insert)
    public void overwriteSlang(Slang _slang){
    }


    //import data
    public void import_data(String _filepath) throws IOException {
        System.out.println("importing");
        String line = null;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(_filepath));

        while((line = bufferedReader.readLine())!= null){
            String[] _split = line.split("`");
            addSlang(new Slang(_split[0], _split[1]));
        }
        System.out.println("successfully imported");
    }
    // export data
    public void export_data(String _filepath){
    }
    // reset
    public void reset(String _filepath) throws IOException {
        instance = new Repository();
        import_data(_filepath);

    }
}

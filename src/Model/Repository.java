package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Repository {
    public AVLTree<Slang> slangAVLTree;
    private AVLTree<Keyword> keywordAVLTree;
    private ArrayList<Slang> slangHistory;
    private ArrayList<Keyword> keywordHistory;
    // singleton
    private static final Repository instance = new Repository();
    private Repository(){
        slangAVLTree = new AVLTree<Slang>();
        keywordAVLTree = new AVLTree<Keyword>();
        slangHistory = new ArrayList<Slang>();
        keywordHistory = new ArrayList<Keyword>();
        try {
            import_data("slang.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Repository getInstance(){ return instance;}

    // debug


    //---- CRUD modules
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
        return slangAVLTree.find(req);
    }
    //  find by Keyword
    public Keyword findByKeyword(Keyword req){
        return keywordAVLTree.find(req);
    }
    // add slang and keyword
    public void addSlang(Slang _slang){
        // add to slang tree and keyword tree both
        Slang search = slangAVLTree.find(_slang);
        if(search != null){
            search.addMeaning(_slang.getMeanings().get(0));
            slangAVLTree.delete(search);
            slangAVLTree.insert(search);
        }
        else
            slangAVLTree.insert(_slang);
        for(String i : _slang.getMeanings()){// add keyword to keywordAVL tree
            String[] split = i.split("\\W+");
            for(String j : split){
                Keyword keyword = null;
                if((keyword =  keywordAVLTree.find(new Keyword(j))) != null){
                    keyword.addSlang(_slang);
                } else {
                    keyword = new Keyword(j);
                    keyword.addSlang(_slang);
                    keywordAVLTree.insert(keyword);
                }

            }
        }
    }
    public void deleteSlang(Slang _slang){

        Slang search = slangAVLTree.find(_slang);
        if(search != null){
            ArrayList<String> split1 = search.getMeanings();
            for(String i: split1){
                String[] split2 = i.split("\\W+");
                for(String j : split2) {


                    Keyword keyword = keywordAVLTree.find(new Keyword(j));
                    if(keyword != null) {
                        int loc = 0;
                        ArrayList<Slang> slangArrayList = keyword.getSlangs();
                        for (int t = 0; t < slangArrayList.size(); t++) {
                            if (keyword.getSlangs().get(t).compareTo(_slang) == 0) {
                                loc = t;
                            }
                        }
                        slangArrayList.remove(loc);
                        Keyword renewedKeyword = new Keyword(j, slangArrayList);
                        keywordAVLTree.delete(renewedKeyword);
                        keywordAVLTree.insert(renewedKeyword);
                    }
                }
            }
        }

        slangAVLTree.delete(_slang);



    }
    //  overwrite ( delete and insert)
    public void overwriteSlang(Slang _slang){
        Slang search = slangAVLTree.find(_slang);
        if(search != null){
        deleteSlang(search);
        search = slangAVLTree.find(search);
        if(search == null) System.out.println("ys");
        addSlang(_slang);
        }
    }
    public void duplicateSlang(Slang _slang){
        Slang search = slangAVLTree.find(_slang);
        search.addMeaning(_slang.getMeanings().get(0));
        overwriteSlang(search);
    }


    //----- IO Modules -----
    //import data
    public void import_data(String _filepath) throws IOException {
        System.out.println("importing");
        String line = null;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(_filepath));

        while((line = bufferedReader.readLine())!= null){
            String[] _split = line.split("`");
            String[] _keyword = _split[1].split("[|]");
            for(String i:_keyword){
                Slang slang =  new Slang(_split[0], i);
                addSlang(slang);
            }
        }
        System.out.println("successfully imported");
    }
    // export data
    public void export_data(String _filepath){
    }
    // reset
    public void reset(String _filepath){
        slangAVLTree = new AVLTree<Slang>();
        keywordAVLTree = new AVLTree<Keyword>();
        slangHistory = new ArrayList<Slang>();
        keywordHistory = new ArrayList<Keyword>();
        try {
            import_data("slang.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Slang randomSlang(){
        return slangAVLTree.randomNode();
    }
}

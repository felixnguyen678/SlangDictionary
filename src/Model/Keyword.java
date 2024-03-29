package Model;

import java.security.Key;
import java.util.ArrayList;

public class Keyword implements Comparable<Keyword> {
    private String keyword;
    private ArrayList<Slang> slangs = new ArrayList<Slang>();

    public Keyword(String _keyword){
        this.keyword = _keyword;
    }
    public Keyword(String _keyword, ArrayList<Slang> _slangs){
        this.keyword = _keyword;
        this.slangs = _slangs;
    }
    public void addSlang(Slang _slang){
        slangs.add(_slang);
    }
    public String getKeyword() {
        return keyword;
    }

    public ArrayList<Slang> getSlangs() {
        return slangs;
    }



    @Override
    public int compareTo(Keyword _keyword) {
        return this.keyword.toLowerCase().compareTo(_keyword.keyword.toLowerCase());
    }


}
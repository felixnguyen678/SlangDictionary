package Model;

import java.util.ArrayList;

public class Slang implements Comparable<Slang> {
    private String slang;
    ArrayList<String> meanings = new ArrayList<String>();
    public String getSlang() {
        return slang;
    }

    public ArrayList<String> getMeanings() {
        return meanings;
    }

    public Slang(String _slang){
        this.slang = _slang;
    }
    public Slang(String _slang, String _meaning){
        this.slang = _slang;
        this.addMeaning(_meaning);
    }
    public void addMeaning(String _meaing){
        meanings.add(_meaing);
    }
    @Override
    public int compareTo(Slang _slang) {
        if(this.slang.compareTo(_slang.slang)>0)
            return 1;
        if(this.slang.compareTo(_slang.slang)<0)
            return -1;
        return 0;
    }

}

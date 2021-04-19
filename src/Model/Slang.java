package Model;

import java.util.ArrayList;

import static java.lang.Math.pow;

public class Slang implements Comparable<Slang> {
    public String slang;
    private ArrayList<String> meanings = new ArrayList<String>();
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
        return this.slang.compareTo(_slang.slang);
    }
    public String meaningsToString(){
        String str = "";
        for(String i: this.getMeanings()){
            str += i;
        }
        return str;
    }
}

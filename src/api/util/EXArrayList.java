package api.util;

import java.util.ArrayList;

public class EXArrayList {
    ArrayList<String> a = new ArrayList<>();
    String name;
    public EXArrayList(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String get(int index){
        return a.get(index);
    }
    public void add(String da){
        a.add(da);
    }
    public void remove(String da){
        a.remove(da);
    }
}

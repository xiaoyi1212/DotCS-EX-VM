package api.util;

public class Index {
    public String script_name;
    public int index;
    public Index(String script_name,int index){this.script_name = script_name;this.index = index;}

    public int getIndex() {
        return index;
    }

    public String getScript_name() {
        return script_name;
    }
}

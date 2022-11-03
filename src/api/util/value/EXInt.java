package api.util.value;

import api.util.Util;

public class EXInt extends ValueType{
    int i;
    public EXInt(String i){
        if( Util.getIntegerData(i)!=null){
            this.i = Util.getIntegerData(i);
            return;
        }
        this.i = Util.getIntegerDataH(i);
    }

    @Override
    public void setValue(Object o) {
        i = (int) o;
    }

    @Override
    public Object getValue() {
        return i;
    }
}

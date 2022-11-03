package api.util.value;

import api.util.Util;

public class EXBool extends ValueType{
    boolean data;
    public EXBool(String data){
        if(Util.getStringData(data)!=null){
            this.data = Util.getBooleanData(data);
            return;
        }
        this.data = Util.getBooleanDataH(data);
    }
    @Override
    public Object getValue() {
        return data;
    }

    @Override
    public void setValue(Object o) {
        data = (boolean) o;
    }
}

package api.util.value;

import api.util.Util;

public class EXFloat extends ValueType{
    double data;
    public EXFloat(String data){
        if(Util.getStringData(data)!=null){
            this.data = Util.getDoubleData(data);
            return;
        }
        this.data = Util.getDoubleDataH(data);
    }
    @Override
    public Object getValue() {
        return data;
    }

    @Override
    public void setValue(Object o) {
        data = (double) o;
    }
}

package api.util.value;

import api.manage.Data;
import api.util.Util;

public class EXString extends ValueType{

    String data;
    public EXString(String data){
        if(Util.getStringData(data)!=null){
            this.data = Util.getStringData(data);
            return;
        }
        this.data = Util.getStringDataH(data);
    }

    @Override
    public Object getValue() {
        return data;
    }

    @Override
    public void setValue(Object o) {
        data = (String) o;
    }
}

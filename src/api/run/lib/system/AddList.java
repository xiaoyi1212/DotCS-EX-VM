package api.run.lib.system;

import api.manage.Data;
import api.method.MethodFrame;
import api.method.NativeMethodFrame;
import api.run.ExceptionError;
import api.util.EXArrayList;
import api.util.Util;

public class AddList extends NativeMethodFrame {
    @Override
    public String getName() {
        return "addList";
    }

    @Override
    public void invokeMethod(String a, MethodFrame frame) {
        String value = a.split("/")[1];
        String data = Util.getConstOrValue(value);
        for(EXArrayList arrayList:Data.arrays){
            if(arrayList.getName().equals(a.split("/")[0])){
                arrayList.add(data);
                return;
            }
        }
        ExceptionError.throwError(frame,"NPException","Unknown list name");
    }
}

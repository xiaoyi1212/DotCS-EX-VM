package api.run.lib.system;

import api.manage.Data;
import api.method.MethodFrame;
import api.method.NativeMethodFrame;
import api.run.ExceptionError;
import api.util.EXArrayList;
import api.util.Util;
import api.util.value.EXInt;

public class GetList extends NativeMethodFrame {
    @Override
    public String getName() {
        return "getList";
    }

    @Override
    public void invokeMethod(String a, MethodFrame frame) {
        try {
            String value = a.split("/")[1];
            String data = Util.getConstOrValue(value);
            for (EXArrayList arrayList : Data.arrays) {
                if (arrayList.getName().equals(a.split("/")[0])) {
                    frame.stack.push(new EXInt(arrayList.get(Integer.parseInt(a))));
                    return;
                }
            }
            ExceptionError.throwError(frame, "NPException", "Unknown list name");
        }catch (ArrayIndexOutOfBoundsException e){
            ExceptionError.throwError(frame,"AIOOBException",e.getLocalizedMessage());
        }
    }
}

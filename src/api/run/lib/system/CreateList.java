package api.run.lib.system;

import api.manage.Data;
import api.method.MethodFrame;
import api.method.NativeMethodFrame;
import api.util.EXArrayList;
import api.util.Util;

public class CreateList extends NativeMethodFrame {
    @Override
    public String getName() {
        return "createList";
    }

    @Override
    public void invokeMethod(String value, MethodFrame frame) {
        String data = Util.getConstOrValue(value);
        Data.arrays.add(new EXArrayList(data));
    }
}

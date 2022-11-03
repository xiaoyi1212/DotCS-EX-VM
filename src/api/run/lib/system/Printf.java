package api.run.lib.system;

import api.method.MethodFrame;
import api.method.NativeMethodFrame;
import api.util.Util;

public class Printf extends NativeMethodFrame {

    @Override
    public String getName() {
        return "printf";
    }

    @Override
    public void invokeMethod(String value, MethodFrame frame) {
        String data = Util.getConstOrValue(value);
        System.out.println(data);
    }
}

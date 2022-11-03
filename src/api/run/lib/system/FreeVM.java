package api.run.lib.system;

import api.manage.ThreadManage;
import api.method.MethodFrame;
import api.method.NativeMethodFrame;

public class FreeVM extends NativeMethodFrame {

    @Override
    public String getName() {
        return "freeVM";
    }

    @Override
    public void invokeMethod(String value, MethodFrame frame) {
        ThreadManage.GCThread();
        System.gc();
    }
}

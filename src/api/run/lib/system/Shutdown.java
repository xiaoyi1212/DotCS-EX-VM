package api.run.lib.system;

import api.manage.ThreadManage;
import api.method.MethodFrame;
import api.method.NativeMethodFrame;
import api.util.ThreadEX;
import api.util.Util;

public class Shutdown extends NativeMethodFrame {
    @Override
    public String getName() {
        return "shutdown";
    }

    @Override
    public void invokeMethod(String value, MethodFrame frame) {
        for(ThreadEX threadEX: ThreadManage.threadEXES){
            threadEX.setStatus(ThreadEX.STATUS.DEATH);
        }
        System.exit(Integer.parseInt(Util.getConstOrValue(value)));
    }
}

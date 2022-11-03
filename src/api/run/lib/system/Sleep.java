package api.run.lib.system;

import api.manage.ThreadManage;
import api.method.MethodFrame;
import api.method.NativeMethodFrame;
import api.run.ExceptionError;
import api.util.ThreadEX;
import api.util.Util;

public class Sleep extends NativeMethodFrame {
    @Override
    public String getName() {
        return "sleep";
    }

    @Override
    public void invokeMethod(String value, MethodFrame frame) {
        try {
            String name = Util.getConstOrValue(value.split("/")[0]);
            long time = Long.parseLong(Util.getConstOrValue(value.split("/")[1]));
            for (ThreadEX ex : ThreadManage.threadEXES) {
                if (ex.getName().equals(name)) {
                    ex.sleep(time);
                    return;
                }
            }
            ExceptionError.throwError(frame,"RuntimeError","Unknown thread name.");
        }catch (Exception e){
            ExceptionError.throwError(frame,"ThreadException",e.getLocalizedMessage());
        }
    }
}

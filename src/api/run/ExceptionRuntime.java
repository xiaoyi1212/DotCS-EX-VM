package api.run;

import api.manage.ThreadManage;
import api.method.MethodFrame;
import api.util.NativeInvokeMethod;
import api.util.ThreadEX;

public class ExceptionRuntime {
    public static void exception(MethodFrame frame,String name){
        if(frame instanceof NativeInvokeMethod) return;
        if(frame.getName().equals("main")){
            for(ThreadEX threadEX:ThreadManage.threadEXES) threadEX.setStatus(ThreadEX.STATUS.WAIT);
            for(ThreadEX threadEX:ThreadManage.threadEXES){
                if(threadEX.getName().equals("main")){
                    threadEX.setStatus(ThreadEX.STATUS.ERROR);
                    break;
                }
            }
            if(name.equals("VMLoaderError")||name.equals("JVMError")||name.equals("OutOfMemoryError")){
                for (ThreadEX ex:ThreadManage.threadEXES) ex.setStatus(ThreadEX.STATUS.DEATH);
                ThreadManage.GCThread();
                System.gc();
                System.exit(-1);
                return;
            }
        }
    }
}

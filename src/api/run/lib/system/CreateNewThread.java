package api.run.lib.system;

import api.manage.ThreadManage;
import api.method.MethodFrame;
import api.method.NativeMethodFrame;
import api.run.VMExecutor;
import api.util.NativeIndex;
import api.util.NativeInvokeMethod;
import api.util.ThreadEX;
import api.util.Util;

public class CreateNewThread extends NativeMethodFrame {
    @Override
    public String getName() {
        return "createNewThread";
    }

    @Override
    public void invokeMethod(String value, MethodFrame frame) {
        for(MethodFrame frame1: ThreadManage.frames){
            if(frame1.getName().equals(value.split("/")[0])){
                ThreadC c = new ThreadC(frame,Util.getConstOrValue(value));
                c.start();
                return;
            }
        }
    }
    class ThreadC extends Thread{

        MethodFrame frame1;
        String value;

        public ThreadC(MethodFrame frame,String value){
            this.frame1 = frame;
            this.value = value;
        }

        @Override
        public void run() {
            ThreadEX threadEX = new ThreadEX(this,Util.getConstOrValue(value.split("/")[1]),new NativeIndex(), ThreadEX.STATUS.RUNNING);
            threadEX.push(frame1);
            ThreadManage.threadEXES.add(threadEX);
            VMExecutor.executorInvoke(new NativeInvokeMethod(),threadEX,frame1.getName(),"");
        }
    }
}

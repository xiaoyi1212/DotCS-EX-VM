package api.manage;

import api.method.MethodFrame;
import api.method.NativeMethodFrame;
import api.util.ThreadEX;

import java.util.ArrayList;

public class ThreadManage {
    public static ArrayList<ThreadEX> threadEXES = new ArrayList<>(); //线程池

    public static ArrayList<NativeMethodFrame> native_frames = new ArrayList<>(); // 本地函数池

    public static ArrayList<MethodFrame> frames = new ArrayList<>(); // 函数池

    public static void GCThread(){ //线程池FREE
        ArrayList<ThreadEX> ex = new ArrayList<>();
        for(ThreadEX thread:threadEXES){
            if(thread.getStatus().equals(ThreadEX.STATUS.DEATH)){
                ex.add(thread);
            }
        }
        threadEXES.removeAll(ex);
        System.gc();
    }
}

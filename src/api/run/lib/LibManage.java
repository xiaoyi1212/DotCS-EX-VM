package api.run.lib;

import api.manage.ThreadManage;
import api.method.NativeMethodFrame;
import api.run.ExceptionError;
import api.util.NativeInvokeMethod;

import java.util.ArrayList;

public class LibManage {
    static ArrayList<BaseLIB> libs = new ArrayList<>();
    public static void addLib(BaseLIB lib){
        if(!libs.contains(lib)) libs.add(lib);
    }
    public static void addLib(String name, NativeMethodFrame frame){
        for(BaseLIB lib:libs) {
            if (lib.getName().equals(name)) {
                lib.getLib().add(frame);
                return;
            }
        }
    }
    public static void addLib(BaseLIB lib,NativeMethodFrame frame){
        for(BaseLIB libt:libs) {
            if (libt.equals(lib)) {
                libt.getLib().add(frame);
                return;
            }
        }
    }

    public static void loadLib(String name){
        for (BaseLIB lib:libs) {
            if (lib.getName().equals(name)) {
                lib.init();
                for(NativeMethodFrame frame: lib.getLib()){
                    ThreadManage.native_frames.add(frame);
                }
                return;
            }
        }
        ExceptionError.throwError(new NativeInvokeMethod(),"VMLoaderError","Unknown lib name.");
    }

}

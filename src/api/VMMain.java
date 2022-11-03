package api;

import api.manage.ByteCodeLex;
import api.run.ExceptionError;
import api.run.lib.LibManage;
import api.run.lib.SystemLib;
import api.run.lib.plugin.PluginManage;
import api.util.NativeInvokeMethod;

public final class VMMain {
    static int time = 0;
    public static String namef = "script.exbc";private static final boolean DEBUG = false;
    public static void main(String[] args) {

        PluginManage.load();

        LibManage.addLib(new SystemLib());

        try {
            if (DEBUG) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (true) {
                                time += 1;
                                Thread.sleep(1);
                            }
                        } catch (Exception e) {
                        }
                    }
                });
                thread.start();

                ByteCodeLex.lex(ByteCodeLex.readFile(namef));

                thread.stop();
                System.out.println("Load time:" + time);
                return;
            }

            if (args.length < 1) {
                ExceptionError.throwError(new NativeInvokeMethod(), "VMLoaderError", "Not have script file name.");
                System.exit(0);
                return;
            }
            namef = args[0];

            ByteCodeLex.lex(ByteCodeLex.readFile(namef));
        }catch (Exception e){
            ExceptionError.throwError(new NativeInvokeMethod(),"VMLoaderError","Loading throw vm NPException");
        }

    }
}

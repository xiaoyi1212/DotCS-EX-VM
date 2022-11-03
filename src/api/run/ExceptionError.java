package api.run;

import api.manage.ThreadManage;
import api.method.MethodFrame;

public class ExceptionError {
    public static void throwError(MethodFrame frame_e,String name, String message){
        ExceptionRuntime.exception(frame_e,name);
        String a = "";
        for(MethodFrame frame: ThreadManage.frames){
            a = a + frame.getName()+"-[Method]\n";
        }
        System.err.println("Exception throw"+":["+name+"]:"+message+"\n" +
                "Method stack info:\n"+a);
    }
}

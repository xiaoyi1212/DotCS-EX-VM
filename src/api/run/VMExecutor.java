package api.run;

import api.manage.Data;
import api.manage.ThreadManage;
import api.method.NativeMethodFrame;
import api.method.MethodFrame;
import api.util.*;
import api.util.value.*;


public class VMExecutor {

    static ThreadEX main;

    public static void launch(ThreadEX threadEX){
        new Thread(() -> {
            main = new ThreadEX(new NativeThread(),"main",new NativeIndex(), ThreadEX.STATUS.RUNNING);
            ThreadManage.threadEXES.add(main);
            executorInvoke(new NativeInvokeMethod(),main,"main","");
        }).start();
    }

    private static void fetch(ThreadEX threadEX,MethodFrame frame){
        for(int i = 0;i<frame.ByteCodes.size();i++){
            String bytecode = frame.ByteCodes.get(i);
            switch (bytecode.split(" ")[0]){
                case "invoke":
                    executorInvoke(frame,threadEX,bytecode.split(" ")[1],bytecode.split(" ")[2]);
                   break;
                case "nol":
                    break;
                case "push":
                    executorPush(frame,bytecode.split(" ")[1]);
                    break;
                case "pop":
                    executorPop(frame,bytecode.split(" ")[1]);
                    break;
                case "jmp":
                    if(bytecode.split(" ")[1].split("_")[0].equals("this")){
                        i = Integer.parseInt(bytecode.split(" ")[1].split("_")[1]) - 2;
                    }
                    break;
                case "jub":
                    if(!executorJUB(frame)){
                        i = Integer.parseInt(bytecode.split(" ")[1].split("_")[1]) - 2;
                    }
                    break;
                case "add":
                    add(frame);
                    break;
                case "sub":
                    sub(frame);
                    break;
                case "mul":
                    mul(frame);
                    break;
                case "div":
                    div(frame);
                    break;
                case "pushvar":
                    executorPushvar(bytecode.split(" ")[1],frame);
                    break;
                case "mov":
                    executorMove(frame,bytecode.substring(bytecode.indexOf("(")+1,bytecode.indexOf(")")),bytecode.split(" ")[1].split("_")[0]);
                    break;
                case "retstack":
                    executorRetStack(threadEX,frame);
                    return;
            }
        }
    }

    private static void executorPop(MethodFrame frame,String var){
        for(ValueType valueType:Data.vs){
            if(valueType.getName().equals(var)){
                valueType.setValue(frame.stack.pop().getValue());
                return;
            }
        }
        ExceptionError.throwError(frame,"RuntimeError","[POP]Unknown value name.");
    }

    private static void executorRetStack(ThreadEX threadEX,MethodFrame frame){
        ValueType data = frame.stack.peek();
        threadEX.pop();
        threadEX.top().stack.push(data);
    }

    private static void executorMove(MethodFrame frame,String data,String name){
        try {
            for (ValueType valueType : Data.vs) {
                if (valueType.getName().equals(name)) {

                    data = data.substring(data.indexOf("(") + 1, data.indexOf(")"));

                    valueType.setValue(data);
                    return;
                }
            }
            ExceptionError.throwError(frame,"RuntimeError","[MOVE]Unknown value name.");
        }catch (ClassCastException e){
            ExceptionError.throwError(frame,"JVMError",e.getLocalizedMessage());
        }
    }

    private static void executorPushvar(String name,MethodFrame frame){
        for(ValueType valueType:Data.vs){
            if(valueType.getName().equals(name)){
                executorPush(frame,valueType);
                return;
            }
        }
        ExceptionError.throwError(frame,"RuntimeError","[PUSH]Unknown value name.");
    }

    private static boolean executorJUB(MethodFrame frame){
        ValueType data = frame.stack.pop();

        if(data instanceof EXInt&&frame.stack.peek() instanceof EXInt) return (int) data.getValue() == (int) frame.stack.peek().getValue();
        if(data instanceof EXBool) return (boolean) data.getValue();
        if(data instanceof EXString&&frame.stack.peek() instanceof EXString) return data.getValue().equals(frame.stack.peek().getValue());
        if(data instanceof EXFloat&&frame.stack.peek() instanceof EXFloat) return (double) data.getValue() == (double) frame.stack.peek().getValue();

        return false;
    }


    private static void add(MethodFrame frame){
        ValueType data = frame.stack.pop();
        if(data instanceof EXInt&&frame.stack.peek() instanceof EXInt){
            int a = (int) data.getValue() + (int) frame.stack.peek().getValue();
            frame.stack.push(new EXInt(String.valueOf(a)));
        }else if(data instanceof EXString&&frame.stack.peek() instanceof EXString){
            String a = (String) data.getValue() + (String) frame.stack.peek().getValue();
            frame.stack.push(new EXInt(String.valueOf(a)));
        }
    }

    private static void sub(MethodFrame frame){
        ValueType data = frame.stack.pop();
        if(data instanceof EXInt&&frame.stack.peek() instanceof EXInt){
            int a = (int) data.getValue() - (int) frame.stack.peek().getValue();
            frame.stack.push(new EXInt(String.valueOf(a)));
        }
    }

    private static void mul(MethodFrame frame){
        ValueType data = frame.stack.pop();
        if(data instanceof EXInt&&frame.stack.peek() instanceof EXInt){
            int a = (int) data.getValue() * (int) frame.stack.peek().getValue();
            frame.stack.push(new EXInt(String.valueOf(a)));
        }
    }

    private static void div(MethodFrame frame){
        ValueType data = frame.stack.pop();
        if(data instanceof EXInt&&frame.stack.peek() instanceof EXInt){
            int a = (int) data.getValue() / (int) frame.stack.peek().getValue();
            frame.stack.push(new EXInt(String.valueOf(a)));
        }
    }

    private static void executorPush(MethodFrame frame,String data){
        frame.stack.push(Util.getValueType(data));
    }
    private static void executorPush(MethodFrame frame,ValueType data){
        frame.stack.push(data);
    }
    public static void executorInvoke(MethodFrame frame_v,ThreadEX threadEX,String name,String var){
        try {
            for (NativeMethodFrame frame : ThreadManage.native_frames) {
                if (frame.getName().equals(name)) {
                    frame.invokeMethod(var,frame);
                    return;
                }
            }
            for (MethodFrame frame : ThreadManage.frames) {
                threadEX.push(frame);
                if(frame.getName().equals(name)){
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if(!var.equals("VOID")){
                                for(String v:var.split("/")){
                                    frame.values.add(v);
                                }
                            }
                            fetch(threadEX,frame);
                        }
                    });
                    thread.start();
                    thread.join();
                    threadEX.pop();
                    return;
                }
            }
            ExceptionError.throwError(frame_v,"RuntimeError","VM Not found byte code invoke method");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

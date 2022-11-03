package api.manage;

import api.run.ExceptionError;
import api.run.VMExecutor;
import api.run.lib.LibManage;
import api.method.MethodFrame;
import api.VMMain;
import api.util.Index;
import api.util.NativeInvokeMethod;
import api.util.NativeThread;
import api.util.ThreadEX;
import api.util.Util;
import api.util.value.ValueType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ByteCodeLex {
    public static ArrayList<String> readFile(String name){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(name));
            ArrayList<String> ret = new ArrayList<>();
            String line = "";
            while ((line = reader.readLine())!=null){
                ret.add(line);
            }
            return ret;
        } catch (IOException e) {
            ExceptionError.throwError(new NativeInvokeMethod(),"VMLoaderError","Not found file:"+name);
            return null;
        }
    }
    public static void lex(ArrayList<String> fileby){ //生产MethodFrame
        ThreadEX main = new ThreadEX(new NativeThread(),"main",new Index(VMMain.namef,0), ThreadEX.STATUS.RUNNING);
        ThreadManage.threadEXES.add(main);
        MethodFrame frame = null;boolean MEWS = false;
        for(String bytecode:fileby){
            switch (bytecode.split(" ")[0]){
                case "f": //加载常量
                    String data = bytecode.substring(bytecode.indexOf("(")+1,bytecode.indexOf(")"));
                    Data.fs.put(bytecode.split(" ")[1],data);
                    break;
                case "v":
                    String data1 = bytecode.substring(bytecode.indexOf("(")+1,bytecode.indexOf(")"));
                    String type = bytecode.split(" ")[2].split("\\(")[0];

                    ValueType valueType = Util.getValueTypeH(type+"("+data1+")V");
                    valueType.setName(bytecode.split(" ")[1]);

                    Data.vs.add(valueType);
                    break;
                case "imp":
                    String libname = bytecode.substring(bytecode.indexOf("(")+1,bytecode.indexOf(")"));
                    LibManage.loadLib(libname);
                    break;
                case "F": //触发索引
                    frame = new MethodFrame();
                    frame.setName(bytecode.split(" ")[1]);
                    MEWS = true;
                    break;
                case "ret": //终止记录
                    MEWS = false;
                    ThreadManage.frames.add(frame); //存入函数区
                    frame = null;
                default:
                    if(MEWS){ //字节码记录器
                        frame.ByteCodes.add(bytecode);
                    }
                    break;
            }
        }
        VMExecutor.launch(main);
    }
}

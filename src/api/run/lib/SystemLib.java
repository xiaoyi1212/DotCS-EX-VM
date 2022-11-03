package api.run.lib;

import api.method.NativeMethodFrame;
import api.run.lib.system.*;

import java.util.ArrayList;

public class SystemLib extends BaseLIB{
    ArrayList<NativeMethodFrame> lib = new ArrayList<>();
    @Override
    public ArrayList<NativeMethodFrame> getLib() {
        return lib;
    }

    @Override
    public String getName() {
        return "SYSTEM";
    }

    @Override
    public void init(){
        lib.add(new Printf());
        lib.add(new FreeVM());
        lib.add(new CreateList());
        lib.add(new AddList());
        lib.add(new RemoveList());
        lib.add(new GetList());
        lib.add(new CreateNewThread());
        lib.add(new Shutdown());
    }
}

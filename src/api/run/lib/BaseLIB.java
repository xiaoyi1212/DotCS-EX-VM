package api.run.lib;

import api.method.NativeMethodFrame;

import java.util.ArrayList;

public abstract class BaseLIB {
    public abstract ArrayList<NativeMethodFrame> getLib();
    public abstract String getName();
    public abstract void init();
}

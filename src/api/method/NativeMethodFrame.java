package api.method;


public abstract class NativeMethodFrame extends MethodFrame{
    public abstract String getName();
    public abstract void invokeMethod(String value,MethodFrame frame);
}

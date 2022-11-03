package api.util;

import api.method.MethodFrame;
import api.method.NativeMethodFrame;
import api.run.ExceptionError;

public class NativeInvokeMethod extends NativeMethodFrame {
    @Override
    public String getName() {
        return "VMLoaderFunction";
    }

    @Override
    public void invokeMethod(String value, MethodFrame frame) {
        ExceptionError.throwError(frame,"RuntimeError","You can't invoke native function.");
    }
}

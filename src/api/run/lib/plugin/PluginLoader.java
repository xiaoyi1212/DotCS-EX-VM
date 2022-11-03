package api.run.lib.plugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginLoader {
    public static void load(File file){
        try {
            URL url1 = new URL("file:"+file.getAbsolutePath());
            URLClassLoader myClassLoader1 = new URLClassLoader(new URL[]{url1}, Thread.currentThread()
                    .getContextClassLoader());
            Class<?> myClass1 = myClassLoader1.loadClass("method.MainRegister");
            NativeMethodMain action1 = (NativeMethodMain) myClass1.newInstance();
            action1.onEnable();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

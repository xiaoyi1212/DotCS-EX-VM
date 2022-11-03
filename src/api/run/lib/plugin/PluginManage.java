package api.run.lib.plugin;

import java.io.File;

public class PluginManage {
    public static void load(){
        File file = new File("natives");
        if(!file.exists()) file.mkdir();

        for(File file1:file.listFiles()){
            PluginLoader.load(file1);
        }
    }
}

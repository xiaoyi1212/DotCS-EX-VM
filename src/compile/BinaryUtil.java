package compile;

import java.io.*;
import java.util.ArrayList;

public class BinaryUtil {
    public static ArrayList<String> readFile(String path){
        try {
            int Size_Buffer = 16 * 1024;
            FileInputStream read = new FileInputStream(new File(path));
            BufferedInputStream buffered_reader = new BufferedInputStream(read, Size_Buffer);
            ArrayList<String> ret = new ArrayList<>();
            int byt;
            while ((byt = buffered_reader.read()) != -1) {
                ret.add(Integer.toBinaryString(byt));
            }
            buffered_reader.close();
            return ret;
        } catch (IOException e) {
            return null;
        }
    }

    public static void writeFile(String path, ArrayList<String> data){
        try(BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(path,true))){
            byte[] info =byte2String(data);
            byte[] o = new byte[info.length+1];
            o[info.length+1] = '\n';
            writer.write(o);
        }catch (IOException io){
        }
    }

    private static byte[] byte2String(ArrayList<String> binaryByteString){
        String[] binaryStr= binaryByteString.toArray(new String[binaryByteString.size()]);
        byte[] byteArray=new byte[binaryStr.length];
        for(int i=0;i< binaryStr.length;i++){
            byteArray[i]=(byte)parse(binaryStr[i]);
        }
        return byteArray;
    }

    private static int parse(String str){
        if(32==str.length()){
            str="-"+str.substring(1);
            return -(Integer.parseInt(str, 2)+Integer.MAX_VALUE+1);
        }
        return Integer.parseInt(str, 2);
    }
}

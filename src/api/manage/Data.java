package api.manage;

import api.util.EXArrayList;
import api.util.value.ValueType;

import java.util.ArrayList;
import java.util.HashMap;

public final class Data {
    public static HashMap<String,String> fs = new HashMap<>(); // 常量池
    public static ArrayList<ValueType> vs = new ArrayList<>(); //变量池
    public static ArrayList<EXArrayList> arrays = new ArrayList<>();
}

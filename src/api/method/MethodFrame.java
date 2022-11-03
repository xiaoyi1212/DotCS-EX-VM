package api.method;

import api.util.Index;
import api.util.value.ValueType;

import java.util.ArrayList;
import java.util.Stack;

public class MethodFrame {
    Index index;
    public Stack<ValueType> stack = new Stack<>(); //操作栈
    public ArrayList<String> ByteCodes = new ArrayList<>(); //函数内字节码
    String name; //函数名
    public ArrayList<String> values = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public Index getIndex() {
        return index;
    }

    public Stack<ValueType> getStack() {
        return stack;
    }

    public ArrayList<String> getByteCodes() {
        return ByteCodes;
    }

    public String getName() {
        return name;
    }

    public void setIndex(Index index) {
        this.index = index;
    }
}

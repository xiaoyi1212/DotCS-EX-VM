package api.util;

import api.method.MethodFrame;

import java.util.Stack;

public class ThreadEX {
    public enum STATUS{
        RUNNING,DEATH,WAIT,LOADING,ERROR
    }
    Index index;
    String name;
    STATUS status;
    Thread thread;

    Stack<MethodFrame> Stackmethod = new Stack<>(); //方法区堆栈
    public ThreadEX(Thread thread,String name, Index index, STATUS status){
        this.thread = thread;
        this.status = status;
        this.name = name;
        this.index = index;
    }

    public void stop(){
        this.status = STATUS.DEATH;
        thread.stop();
    }

    public void sleep(long time) throws InterruptedException{Thread.sleep(time);}

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public MethodFrame top(){
        return Stackmethod.peek();
    }

    public Stack<MethodFrame> getStack() {
        return Stackmethod;
    }

    public void push(MethodFrame frame) {
        Stackmethod.push(frame);
    }

    public MethodFrame pop(){
        return Stackmethod.pop();
    }

    public STATUS getStatus() {
        return status;
    }

    public void setIndex(Index index) {
        this.index = index;
    }
}

package compile;

public class ByteCode {
    //交换
    public static final String IMP =      "0110100101";
    public static final String F   =      "0110100001";
    public static final String RET =      "0110100111";
    public static final String f   =      "0110101001";
    public static final String v   =      "0110101010";

    //操作
    public static final String PUSH =     "1110100101";
    public static final String POP =      "1110100111";
    public static final String JMP =      "1110100100";
    public static final String JUB =      "1110101101";
    public static final String INVOKE =   "1110101100";
    public static final String MOV =      "1110101111";
    public static final String PUSHVAR =  "1110101110";
    public static final String NOL =      "1110100000";
    public static final String RETSTACK = "1110100001";

    //运算
    public static final String ADD =      "1011101001";
    public static final String SUB =      "1011101010";
    public static final String MUL =      "1011101011";
    public static final String DIV =      "1011101100";

    public enum ByteCodeType{
        IMP,F,RET,f,v,PUSH,POP,JMP,JUB,INVOKE,MOV,PUSHVAR,NOL,RETSTACK,ADD,SUB,MUL,DIV;
        public static ByteCodeType ByteCodeType(String i){
            switch (i){
                case ByteCode.IMP: return IMP;
                case ByteCode.F: return F;
                case ByteCode.RET: return RET;
                case ByteCode.f: return f;
                case ByteCode.v: return v;
                case ByteCode.PUSH: return PUSH;
                case ByteCode.POP: return POP;
                case ByteCode.JMP: return JMP;
                case ByteCode.JUB: return JUB;
                case ByteCode.INVOKE: return INVOKE;
                case ByteCode.MOV: return MOV;
                case ByteCode.PUSHVAR: return PUSHVAR;
                case ByteCode.NOL: return NOL;
                case ByteCode.RETSTACK: return RETSTACK;
                case ByteCode.ADD: return ADD;
                case ByteCode.SUB: return SUB;
                case ByteCode.MUL: return MUL;
                case ByteCode.DIV: return DIV;
                default: return null;
            }
        }
    }
}

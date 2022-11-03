package api.util;

import api.manage.Data;
import api.run.ExceptionError;
import api.util.value.*;

public final class Util {
    public static String getConstOrValue(String value) {
        String data = "";
        if (value.split("_")[0].equals("F")) {
            data = Data.fs.get(value.split("_")[1]);
            if (data == null) ExceptionError.throwError(new NativeInvokeMethod(),"NullPrintError", "Const data is null");
        } else if (value.split("_")[0].equals("V")) {


            for (ValueType valueType : Data.vs) {
                if (valueType.getName().equals(value.split("_")[1])) {
                    data = (String) valueType.getValue();
                    break;
                }
            }

            if (data == null) ExceptionError.throwError(new NativeInvokeMethod(),"NullPrintError", "Const data is null");
            try {
                data = data.substring(data.indexOf("(") + 1, data.indexOf(")"));
            } catch (StringIndexOutOfBoundsException e) {
                if (data.split("_")[0].equals("F")) {
                    data = Data.fs.get(data.split("_")[1]);
                    if (data == null) ExceptionError.throwError(new NativeInvokeMethod(),"NullPrintError", "Const data is null");
                } else if (data.split("_")[0].equals("V")) {
                    for (ValueType valueType : Data.vs) {
                        if (valueType.getName().equals(data.split("_")[1])) {
                            data = (String) valueType.getValue();
                            break;
                        }
                    }
                    if (data == null) ExceptionError.throwError(new NativeInvokeMethod(),"NullPrintError", "Const data is null");
                    try {
                        data = data.substring(data.indexOf("(") + 1, data.indexOf(")"));
                    } catch (StringIndexOutOfBoundsException var) {
                        ExceptionError.throwError(new NativeInvokeMethod(),"PointerValueError", "pointer recursively stores");
                        System.exit(0);
                        return null;
                    }
                }
            }
        }
        return data;
    }

    public static Integer getIntegerData(String data) {
        if (data.split("_")[0].equals("int")) {
            data = data.substring(data.indexOf("(") + 1, data.indexOf(")"));
            return Integer.parseInt(data);
        }
        return null;
    }

    public static String getStringData(String data) {
        if (data.split("_")[0].equals("int")) {
            data = data.substring(data.indexOf("(") + 1, data.indexOf(")"));
            return data;
        }
        return null;
    }

    public static Double getDoubleData(String data) {
        if (data.split("_")[0].equals("int")) {
            data = data.substring(data.indexOf("(") + 1, data.indexOf(")"));
            return Double.valueOf(data);
        }
        return null;
    }

    public static Boolean getBooleanData(String data) {
        if (data.split("_")[0].equals("bool")) {
            data = data.substring(data.indexOf("(") + 1, data.indexOf(")"));
            if (data.equals("true")) return true;
            return false;
        }
        return null;
    }



    public static Integer getIntegerDataH(String data) {
        if (data.split("\\(")[0].equals("int")) {
            data = data.substring(data.indexOf("(") + 1, data.indexOf(")"));
            return Integer.parseInt(data);
        }
        return null;
    }

    public static String getStringDataH(String data) {
        if (data.split("\\(")[0].equals("string")) {
            data = data.substring(data.indexOf("(") + 1, data.indexOf(")"));
            return data;
        }
        return null;
    }

    public static Double getDoubleDataH(String data) {
        if (data.split("\\(")[0].equals("double")) {
            data = data.substring(data.indexOf("(") + 1, data.indexOf(")"));
            return Double.valueOf(data);
        }
        return null;
    }

    public static Boolean getBooleanDataH(String data) {
        if (data.split("\\(")[0].equals("bool")) {
            data = data.substring(data.indexOf("(") + 1, data.indexOf(")"));
            if (data.equals("true")) return true;
            return false;
        }
        return null;
    }



    public static ValueType getValueType(String data) {
        switch (data.split("_")[0]) {
            case "int":
                return new EXInt(data);
            case "string":
                return new EXString(data);
            case "float":
                return new EXFloat(data);
            case "bool":
                return new EXBool(data);
            default:
                return null;
        }
    }

    public static ValueType getValueTypeH(String data) {
        switch (data.split("\\(")[0]) {
            case "int":
                return new EXInt(data);
            case "string":
                return new EXString(data);
            case "float":
                return new EXFloat(data);
            case "bool":
                return new EXBool(data);
            default:
                return null;
        }
    }
}


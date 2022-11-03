package api.util.value;


public abstract class ValueType {
    String name;
    public abstract Object getValue();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void setValue(Object o);
}

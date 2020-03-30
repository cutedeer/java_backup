package bean;

public class A {
    private  String name;

    private boolean b;

    private int o;


    public A(String name) {
        this.name = name;
    }

    public A() {
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public int getO() {
        return o;
    }

    public void setO(int o) {
        this.o = o;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                ", b=" + b +
                ", o=" + o +
                '}';
    }
}

class test1{
    public static void main(String[] args) {
        System.out.println(new A());
    }
}
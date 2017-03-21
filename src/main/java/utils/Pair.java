package utils;

public class Pair<T, U> {
    private final T t;
    private final U u;

    public Pair(T t, U u) {
        this.t= t;
        this.u= u;
    }

    public T getFirst() {
        return t;
    }

    public U getSecond() {
        return u;
    }

    public Object get(int index) {
        if (index==0) {
            return t;
        } else if (index==1) {
            return u;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public String toString(){
        return "(" + t + ", " + u + ")";
    }


}
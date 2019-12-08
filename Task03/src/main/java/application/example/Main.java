package application.example;

public abstract class Main {
    public int sum (int a, int b) {
        return a + b;
    }


}



class Sub extends Main {
    public int sum (int a, int b, int c) {
        return a + b;
    }
}
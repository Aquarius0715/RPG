package hello_world;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i <= 3; i++) {
            for (int ii = 0; ii < i; ii++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

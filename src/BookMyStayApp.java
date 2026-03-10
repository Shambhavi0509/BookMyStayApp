import java.util.Scanner;

public class BookMyStayApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Book My Stay App");

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.println("Hello " + name + "! Welcome to our hotel booking system.");
    }
}
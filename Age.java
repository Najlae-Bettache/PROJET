import java.util.Scanner;

public class Age {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("entrez votre age: ");
        int age = scanner.nextInt();

        if(age>18){
            System.out.println("vous etes majeur!");
        }
        else{
            System.out.println("vous etes mineur!");
        }


        scanner.close();
    }
    
}
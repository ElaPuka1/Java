import java.util.Scanner;

public class Main {
    public static boolean runNextGame(Scanner sc){
        System.out.println("Попробуешь еще раз? (да / нет)");
        String answer = sc.nextLine();

        if (answer == null || answer.isBlank()){
            answer = sc.nextLine();
        }
        if(answer.equalsIgnoreCase("да")) return true;
        if(answer.equalsIgnoreCase("нет")) return false;

        return runNextGame(sc);
    }
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите свое имя");
        String name = sc.nextLine();
        System.out.println(name + ", я умею складывать числа. Давай попробуем!");

        while (true) {
            System.out.println("Введи первое число");
            Integer num1 = sc.nextInt();

            System.out.println("Введи второе число");
            Integer num2 = sc.nextInt();

            int sum = num1 + num2;
            System.out.println(num1 + "+" + num2 + "=" + sum);
            boolean run = runNextGame(sc);
            if (run == false) break;
        }
        System.out.println(name + ", спасибо за проведенное время!");
    }

}

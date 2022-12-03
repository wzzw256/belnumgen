import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final char[] numberLetters = {
                'A', 'B', 'E', 'I',
                'K', 'M', 'H', 'O',
                'P', 'C', 'T', 'X'
        };
        LinkedList<String> usedNumbers = new LinkedList<>();

        char[] number = new char[7];

        int randomPos;
        int amount, region;

        System.out.println("Генератор белорусских автомобильных номеров.");

        System.out.print("Укажите регион: ");
        region = sc.nextInt();
        if (region > 7 || region < 1) {
            System.out.println("Ошибка. Неверный регион.");
            System.exit(0);
        }
        System.out.print("Сколько номеров нужно сгенерировать? ");
        amount = sc.nextInt();
        if (amount < 1) {
            System.out.println("Ошибка. Неверное количество.");
            System.exit(0);
        }

        for (int i = 0; i < amount; i++) {
            for (int j = 0; j < number.length; j++) {
                if (j < 4) {
                    number[j] = (char) (Math.random() * 10 + 48);
                }
                else if (j != 4){
                    randomPos = (int) (Math.random() * numberLetters.length);
                    number[j] = numberLetters[randomPos];
                }
                else {
                    number[j] = ' ';
                }
            }

            //избегание повторов
            String currentNumber = new String(number);
            if (!usedNumbers.contains(currentNumber)) {
                usedNumbers.add(currentNumber);
            }
        }

        try (FileWriter writer = new FileWriter("generatednumbers.txt", false))
        {
            int i = 1;
            writer.write("СГЕНЕРИРОВАННЫЕ НОМЕРА:\n");
            for (String el : usedNumbers) {
                writer.write(i + ".\t");
                i++;

                writer.write(el + "-" + region + "\n");
            }

            System.out.println("\nЗапись прошла успешно.");
        }
        catch (IOException ex){
            System.out.println("\nОшибка записи в файл.");
        }

        sc.close();
    }
}

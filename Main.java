import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int earnings = 0;    // доходы
        int spendings = 0;   // расходы
        while (true) {
            System.out.println("Выберите операцию и введите её номер:");
            System.out.println("1. Добавить новый доход");
            System.out.println("2. Добавить новый расход");
            System.out.println("3. Выбрать систему налогообложения");
            String input = scanner.nextLine();
            if (input.equals("end")) {
                System.out.println("\n" + "Программа завершена!");
                break;
            } else {
                int operation = Integer.parseInt(input);
                switch (operation) {
                    case 1:
                        System.out.println("Введите сумму дохода:");
                        earnings += Integer.parseInt(scanner.nextLine());
                        break;
                    case 2:
                        System.out.println("Введите сумму расхода:");
                        spendings += Integer.parseInt(scanner.nextLine());
                        break;
                    case 3:
                        if (earnings == 0) {
                            System.out.println("Вы не указали сумму дохода");
                            System.out.println();
                        } else {
                            // УСН доходы: 6%
                            int taxSixPercent = taxOnEarnings(earnings);
                            //УСН доходы минус расходы: 15%
                            int taxFifteenPercent = taxEarningsMinusSpendings(earnings, spendings);
                            displayTaxCalculationResult(taxSixPercent, taxFifteenPercent);
                            System.out.println();
                        }
                        break;
                    default:
                        System.out.println("Такой операции нет");
                }
            }
        }
    }

    public static int taxOnEarnings(int earnings) {
        return earnings * 6 / 100;
    }

    public static int taxEarningsMinusSpendings(int earnings, int spendings) {
        int tax = (earnings - spendings) * 15 / 100;
        if (tax >= 0) {
            return tax;
        } else {
            // если расходы оказались больше, то налог посчитается отрицательным
            return 0;
        }
    }

    public static void displayTaxCalculationResult(int taxSixPercent, int taxFifteenPercent) {
        if (taxSixPercent < taxFifteenPercent) {
            System.out.println("Мы советуем вам УСН доходы");
            System.out.println("Ваш налог составит: " + taxSixPercent + " рублей");
            System.out.println("Налог на другой системе: " + taxFifteenPercent + " рублей");
            System.out.println("Экономия: " + (taxFifteenPercent - taxSixPercent) + " рублей");
        } else if (taxSixPercent > taxFifteenPercent) {
            System.out.println("Мы советуем вам УСН доходы минус расходы");
            System.out.println("Ваш налог составит: " + taxFifteenPercent + " рублей");
            System.out.println("Налог на другой системе: " + taxSixPercent + " рублей");
            System.out.println("Экономия: " + (taxSixPercent - taxFifteenPercent) + " рублей");
        } else {
            System.out.println("Вы можете выбрать любую систему УСН");
            System.out.println("Налог УСН доходы составит: " + taxSixPercent + " рублей");
            System.out.println("Налог УСН доходы минус расходы составит: " + taxFifteenPercent + " рублей");
            System.out.println("Экономия: 0 рублей");
        }
    }
}
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws TestException {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

        //запрос на ввод, обработка с клавиатуры
        System.out.println("Введите выражение, содержащее два операнда и один оператор, разделенные пробелами.");
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        scanner.close();
        String[] figures = expression.split(" ");
        String[] romanNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] arabicNumbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        int operand1;
        int operand2;
        int result;
        String numberSystem;

        //проверяем, что в выражении три аргумета и, что оба операнда либо арабские, либо римские числа. Переводим операнды в арабские целые числа
        if (figures.length != 3) {
            throw new TestException("Выражение должно содержать три аргумента: два операнда и один оператор, разделенные пробелами.");
        } else if (contains(romanNumbers, (figures[0])) && contains(romanNumbers, (figures[2]))) {
            numberSystem = "rom";
            //System.out.println(Unit.valueOf(figures[0])); // возвращает имя
            //System.out.println((Unit.valueOf(figures[0])).getValue()); // возвращает значение
            operand1 = (RomToArab.valueOf(figures[0])).getValue();
            operand2 = (RomToArab.valueOf(figures[2])).getValue();
        } else if (contains(arabicNumbers, (figures[0])) && contains(arabicNumbers, (figures[2]))) {
            numberSystem = "arab";
            operand1 = Integer.parseInt(figures[0]);
            operand2 = Integer.parseInt(figures[2]);
        } else {
            throw new TestException("Оба операнда должны быть либо римскими, либо арабскими целыми числами от 1 до 10");
        }

        //проверяем, является ли оператор +, -, * или /. Проводим арифметическую операцию
        switch (figures[1]) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                result = operand1 / operand2;
                break;
            default:
                throw new TestException("Некорректно задан оператор. Возожны только операции сложения, вычитания, умножения, деления.");
        }

        //выводим результат в нужной системе счисления, для римских чисел проверяем, чтобы значение было не менее 1
        if (numberSystem.equals("arab")) {
            System.out.println(result);
        } else if (numberSystem.equals("rom")) {
            if (result < 0) {
                throw new TestException("Результат работы с римскими числами не может быть меньше единицы.");
            } else {
                int hundredsArab = result / 100 * 100;
                int decadesArab = result % 100 / 10 * 10;
                int unitsArab = result % 10;
                String hundredsRom = "";
                String decadesRom = "";
                String unitsRom = "";
                for (RomToArab allfigures : RomToArab.values()) {
                    int x = allfigures.getValue();
                    if (x == hundredsArab) {
                        hundredsRom = allfigures.toString();
                    } else if (x == decadesArab) {
                        decadesRom = allfigures.toString();
                    } else if (x == unitsArab) {
                        unitsRom = allfigures.toString();
                    }
                }
                System.out.println(hundredsRom + decadesRom + unitsRom);
            }
        }
    }

    private static boolean contains(String[] numbers, String figure) {
        int k = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i].equals(figure)) {
                k++;
            }
        }
        if (k == 1) {
            return true;
        } else {
            return false;
        }
    }
}

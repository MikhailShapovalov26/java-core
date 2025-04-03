public class Main {
    public static void main(String[] args) {

        Calculator calc = Calculator.instance.get();

        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(1,1);
        int c = calc.devide.apply(a, b); //ArithmeticException: / by zero деление на 0, выше мы сделали вычитание 1 из 1 и получили ноль

        calc.println.accept(c);
    }
}
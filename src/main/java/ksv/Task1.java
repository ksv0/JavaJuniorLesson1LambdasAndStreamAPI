package ksv;

import java.util.Arrays;
import java.util.List;

/**
 * Напишите программу, которая использует Stream API для обработки списка чисел.
 * Программа должна вывести на экран среднее значение всех четных чисел в списке.
 * 2. *Дополнительная задaча:
 * Переработать метод балансировки корзины товаров cardBalancing() с использованием Stream API
 */
public class Task1 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        double average = numbers.stream()
                .filter(n -> n % 2 == 0) // Оставляем только четные числа
                .mapToDouble(n -> n) // Преобразуем Integer в double
                .average() // Вычисляем среднее значение
                .orElse(0); // Если список пустой, возвращаем 0

        System.out.println("Среднее значение четных чисел: " + average);
    }
}
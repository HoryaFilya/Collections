package ru.naumen.collection.task3;

import java.nio.file.Path;
import java.util.*;

/**
 * <p>Написать консольное приложение, которое принимает на вход произвольный текстовый файл в формате txt.
 * Нужно собрать все встречающийся слова и посчитать для каждого из них количество раз, сколько слово встретилось.
 * Морфологию не учитываем.</p>
 * <p>Вывести на экран наиболее используемые (TOP) 10 слов и наименее используемые (LAST) 10 слов</p>
 * <p>Проверить работу на романе Льва Толстого “Война и мир”</p>
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class WarAndPeace {

    private static final Path WAR_AND_PEACE_FILE_PATH = Path.of("src/main/resources",
            "Лев_Толстой_Война_и_мир_Том_1,_2,_3,_4_(UTF-8).txt");

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Map<String, Integer> words = new LinkedHashMap<>();
        WordParser wordParser = new WordParser(WAR_AND_PEACE_FILE_PATH);
        wordParser.forEachWord(word -> words.put(word, words.getOrDefault(word, 0) + 1));       // O(n)

        Queue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>(11, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));
        Queue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(11, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        for (Map.Entry<String, Integer> entry : words.entrySet()) {                         // O(n)
            if (minHeap.size() != 10 || entry.getValue() <= minHeap.peek().getValue()) {    // O(1)
                minHeap.offer(entry);                                                       // O(log 10)

                if (minHeap.size() > 10) {
                    minHeap.poll();                                                         // O(log 10)
                }
            }

            if (maxHeap.size() != 10 || entry.getValue() >= maxHeap.peek().getValue()) {    // O(1)
                maxHeap.offer(entry);                                                       // O(log 10)

                if (maxHeap.size() > 10) {
                    maxHeap.poll();                                                         // O(log 10)
                }
            }
        }

        System.out.println("Наиболее используемые (TOP) 10 слов:");

        while (!maxHeap.isEmpty()) {
            Map.Entry<String, Integer> entry = maxHeap.poll();                              // O(log 10)
            System.out.println("%s = %s".formatted(entry.getKey(), entry.getValue()));
        }

        System.out.println();

        System.out.println("Наименее используемые (LAST) 10 слов:");

        while (!minHeap.isEmpty()) {
            Map.Entry<String, Integer> entry = minHeap.poll();                              // O(log 10)
            System.out.println("%s = %s".formatted(entry.getKey(), entry.getValue()));
        }

        System.out.println(System.currentTimeMillis() - start);

        // LinkedHashMap - т.к. необходимо иметь и ключ, и значение. И contains() для LinkedHashMap занимает O(1).
        // LinkedHashMap позволяет быстрее итерироваться по элементам коллекции, нежели HashMap.
        // PriorityQueue - позволяет добавлять/удалять элемент в очередь, дополнительно сортируя очередь за O(log k), где k - размер очереди.

        // Big O = O(n) + O(n) * ( O(log 10) + O(log 10) + O(log 10) + O(log 10) ) + 10 * O(log 10) + 10 * O(log 10) -> O(n * log 10)
    }
}
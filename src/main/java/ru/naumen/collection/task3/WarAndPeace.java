package ru.naumen.collection.task3;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

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
        Map<String, Integer> words = new HashMap<>();
        WordParser wordParser = new WordParser(WAR_AND_PEACE_FILE_PATH);
        wordParser.forEachWord(word -> {                                        // O(n)
            if (words.containsKey(word)) {                                      // O(1)
                words.put(word, words.get(word) + 1);                           // O(1)
            } else {
                words.put(word, 1);                                             // O(1)
            }
        });

        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>(Comparator.comparingInt(Map.Entry<String,Integer>::getValue));
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(Comparator.comparingInt(Map.Entry<String,Integer>::getValue).reversed());

        for (Map.Entry<String, Integer> entry : words.entrySet()) {             // O(n)
            minHeap.offer(entry);                                               // O(log 10)
            maxHeap.offer(entry);                                               // O(log 10)

            if (minHeap.size() > 10) {
                minHeap.poll();                                                 // O(log 10)
            }

            if (maxHeap.size() > 10) {
                maxHeap.poll();                                                 // O(log 10)
            }
        }

        System.out.println("Наиболее используемые (TOP) 10 слов:");

        while (!maxHeap.isEmpty()) {
            Map.Entry<String, Integer> entry = maxHeap.poll();                  // O(log 10)
            System.out.println("%s = %s".formatted(entry.getKey(), entry.getValue()));
        }

        System.out.println();

        System.out.println("Наименее используемые (LAST) 10 слов:");

        while (!minHeap.isEmpty()) {
            Map.Entry<String, Integer> entry = minHeap.poll();                  // O(log 10)
            System.out.println("%s = %s".formatted(entry.getKey(), entry.getValue()));
        }

        // HashMap - т.к. необходимо иметь и ключ, и значение. И contains() для HashMap занимает O(1).
        // Выбирать LinkedHashMap | TreeMap нет смысла, т.к. порядок и сортировка нам не важны, а они ухудшают перфоманс.
        // PriorityQueue - позволяет добавлять/удалять элемент в очередь, дополнительно сортируя очередь за O(log k), где k - размер очереди.

        // Big O = O(n) * ( O(1) + O(1) ) + O(n) * ( O(log 10) + O(log 10) + O(log 10) + O(log 10) ) + 10 * O(log 10) + 10 * O(log 10) -> O(n * log 10)
    }
}
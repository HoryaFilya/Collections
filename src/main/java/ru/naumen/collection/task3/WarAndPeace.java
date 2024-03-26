package ru.naumen.collection.task3;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

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

    private static Map<String, Integer> words = new HashMap();

    public static void main(String[] args) {
        WordParser wordParser = new WordParser(WAR_AND_PEACE_FILE_PATH);
        wordParser.forEachWord(word -> {                                        // O(n)
            if (words.containsKey(word)) {                                      // O(1)
                words.put(word, words.get(word) + 1);                           // O(1)
            } else {
                words.put(word, 1);                                             // O(1)
            }
        });

        System.out.println("Наиболее используемые (TOP) 10 слов:");
        words.entrySet().stream()
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))     // O(n * log(n))
                .limit(10)
                .forEach(System.out::println);

        System.out.println();

        System.out.println("Наименее используемые (LAST) 10 слов:");
        words.entrySet().stream()
                .sorted((o1, o2) -> o1.getValue().compareTo(o2.getValue()))     // O(n * log(n))
                .limit(10)
                .forEach(System.out::println);

        // HashMap - т.к. необходимо иметь и ключ, и значение. И contains() для HashMap занимает O(1),
        // если hashCode() реализован верно и корзин достаточно. При коллизиях - можно попадать всегда в одну корзину -> вырождаемся в связанный список -> O(n)
        // Выбирать LinkedHashMap | TreeMap нет смысла, т.к. порядок и сортировка нам не важны, а они ухудшают перфоманс.

        // Result = O(n) + O(1) + O(1) + O(n * log(n))
    }
}
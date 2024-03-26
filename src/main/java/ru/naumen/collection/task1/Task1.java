package ru.naumen.collection.task1;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Дано:
 * <pre>
 * public class User {
 *     private String username;
 *     private String email;
 *     private byte[] passwordHash;
 *     …
 * }
 * </pre>
 * Нужно написать утилитный метод
 * <pre>
 * public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB);
 * </pre>
 * <p>который возвращает дубликаты пользователей, которые есть в обеих коллекциях.</p>
 * <p>Одинаковыми считаем пользователей, у которых совпадают все 3 поля: username,
 * email, passwordHash. Дубликаты внутри коллекций collA, collB можно не учитывать.</p>
 * <p>Метод должен быть оптимален по производительности.</p>
 * <p>Пользоваться можно только стандартными классами Java SE.
 * Коллекции collA, collB изменять запрещено.</p>
 *
 * См. {@link User}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task1 {

    /**
     * Возвращает дубликаты пользователей, которые есть в обеих коллекциях
     */
    public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB) {
        Set<User> users1 = new HashSet<>(collA);
        Set<User> users2 = new HashSet<>(collB);

        users1.retainAll(users2);                   // O(n)

        return users1.stream()
                .toList();                          // O(n)

        // HashSet - т.к. при пересечении необходимо использовать contains(), а для HashSet он занимает O(1),
        // если hashCode() реализован верно и корзин достаточно. При коллизиях - можно попадать всегда в одну корзину -> вырождаемся в связанный список -> O(n)
        // Также коллекция убирает повторяющиеся элементы.
        // Выбирать LinkedHashSet | TreeSet нет смысла, т.к. порядок и сортировка нам не важны, а они ухудшают перфоманс.

        // Result = O(n) + O(n)
    }

    public static void main(String[] args) {
        User user1 = new User("misha", "misha@", null);
        User user2 = new User("mishaa", "misha@", null);
        User user3 = new User("mishaaa", "misha@", null);

        List<User> one = List.of(user1, user2, user3);
        List<User> two = List.of(user1, user3);

        System.out.println(Task1.findDuplicates(one, two));
    }
}

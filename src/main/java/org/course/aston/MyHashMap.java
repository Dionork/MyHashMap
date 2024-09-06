package org.course.aston;

/**
 * Класс HashMap реализует хеш-таблицу.
 *
 * @param <K> тип ключа.
 * @param <V> тип значения.
 */
public class MyHashMap<K, V> {
    /**
     * емкость хеш-таблицы по умолчанию (16)
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    /**
     * коэффициент загрузки, используемый по умолчанию;
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    /**
     * Хеш-таблица, реализованная на основе массива,
     * для хранения пар «ключ-значение» в виде узлов. Здесь хранятся наши Node;
     */
    private Node<K, V>[] table;
    private float loadFactor; //коэффициент загрузки.
    private int capacity; //размер хэш-таблицы.
    private int size; //количество пар «ключ-значение».
    private int threshold; //предельное количество элементов, при достижении которого размер хэш-таблицы увеличивается вдвое.

    /**
     * Конструктор по умолчанию.
     */
    public MyHashMap() {
        this.capacity = DEFAULT_INITIAL_CAPACITY;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    /**
     * Конструктор параметрезированный.
     *
     * @param initialCapacity размер хэш-таблицы.
     * @param loadFactor      коэффициент загрузки. По умолчанию 0.75.
     * @param threshold       предельное количество элементов,
     *                        при достижении которого размер хэш-таблицы увеличивается вдвое.
     */
    public MyHashMap(int initialCapacity, float loadFactor, int threshold) {
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.threshold = threshold;
    }

    /**
     * Конструктор параметрезированный.
     *
     * @param initialCapacity размер хэш-таблицы.
     */
    public MyHashMap(int initialCapacity) {
        this.capacity = initialCapacity;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    /**
     * Конструктор параметрезированный.
     *
     * @param initialCapacity размер хэш-таблицы.
     * @param loadFactor      коэффициент загрузки. По умолчанию 0.75.
     */
    public MyHashMap(int initialCapacity, float loadFactor) {
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
    }

    /**
     * Конструктор параметрезированный. Для копирования ХешМап.
     *
     * @param map копируемая хэш-таблица.
     */
    public MyHashMap(MyHashMap<K, V> map) {
        this.capacity = map.capacity;
        this.loadFactor = map.loadFactor;
        this.threshold = map.threshold;

    }

    /**
     * Метод добавления элемента в хэш-таблицу.
     *
     * @param key   ключ.
     * @param value значение.
     * @see MyHashMap#resize() - испльзуется метод resize(), для увелечения хэш-таблицы.
     * Если хэш-таблица уже больше threshold, то размер хэш-таблицы увеличивается вдвое.
     */
    public void put(K key, V value) {
        //Если нет таблицы, то создается новая.
        if (table == null) {
            capacity = DEFAULT_INITIAL_CAPACITY;
            table = new Node[capacity];
            threshold = (int) (capacity * loadFactor);//максимальный размер хэш-таблицы
        }
        //Проверка количество элементов в хэш-таблице.
        // Если количество элементов больше threshold, то размер хэш-таблицы увеличивается вдвое.
        if (size >= threshold) {
            resize();
        }
        int i = (table.length) - 1 & hash(key); //индекс ячейки хэш-таблицы;
        //Если ячейка пуста, то добавляется новый элемент.
        if (table[i] == null) {
            table[i] = new Node<>(hash(key), key, value, null);
            size++;
        }
        //Если элемент уже есть в хэш-таблице, то изменяется значение. Иначе добавляется новый элемент.
        if (table[i].hash == hash(key) && table[i].key.equals(key)) {
            table[i].value = value; //значение узла в пределах одной корзины.

        } else {
            Node<K, V> e = table[i];
            while (e != null) {
                if (e.hash == hash(key) && e.key.equals(key)) {
                    e.value = value;
                    return;
                }
                e = e.next;
            }
            table[i] = new Node<>(hash(key), key, value, table[i]);
            size++;
        }
    }

    /**
     * Изменение массива хэш-таблицы.
     */
    private void resize() {
        capacity = capacity * 2;
        threshold = (int) (capacity * loadFactor);
        Node<K, V>[] oldTable = table;
        table = new Node[capacity];
        for (int i = 0; i < oldTable.length; i++) {
            Node<K, V> e = oldTable[i];
            while (e != null) {
                int j = (table.length) - 1 & hash(e.key); //индекс ячейки хэш-таблицы;
                Node<K, V> next = e.next;
                e.next = table[j];
                table[j] = e;
                e = next;
            }
        }
    }

    /**
     * Метод, который возвращает значение по ключу.
     *
     * @param key ключ,
     */

    public V get(K key) {
        int i = (table.length) - 1 & hash(key); //индекс ячейки хэш-таблицы;
        if (table[i] == null) {
            System.out.println("Объет не найден!");
        }
        Node<K, V> e = table[i];
        while (e != null) {
            if (e.hash == hash(key) && e.key.equals(key)) {
                return e.value;
            }
            e = e.next; //ссылка на следующий узел в пределах одной корзины
        }
        return null;
    }

    /**
     * Метод,который выводит все значения из таблицы в консоль
     */
    public void values() {
        if (table == null) {
            System.out.println("Таблица пуста!");
        }
        for (int i = 0; i < table.length; i++) {
            Node<K, V> e = table[i];
            while (e != null) {
                System.out.println(e.value); // Выводим значение
                e = e.next; //ссылка на следующий узел в пределах одной корзины
            }
        }
    }

    /**
     * Метод,который выводит все ключи из таблицы в консоль
     */
    public void keySet() {
        if (table == null) {
            System.out.println("Таблица пуста!");
        }
        for (int i = 0; i < table.length; i++) {
            Node<K, V> e = table[i];
            while (e != null) {
                System.out.println(e.key); // Выводим ключ
                e = e.next; //ссылка на следующий узел в пределах одной корзины
            }
        }
    }

    /**
     * Метод,который выводит ключи и значения из таблицы в консоль
     */
    public void entrySet() {
        if (table == null) {
            System.out.println("Таблица пуста!");
        }
        for (int i = 0; i < table.length; i++) {
            Node<K, V> e = table[i];
            while (e != null) {
                System.out.println(e.key + " " + e.value); // Выводим ключ и значение в консоль
                e = e.next; //ссылка на следующий узел в пределах одной корзины
            }
        }
    }

    /**
     * Метод,который удаляет значение по ключу.
     *
     * @param key ключ, который нужно удалить
     */
    public void delete(K key) {
        int i = (table.length) - 1 & hash(key); //индекс ячейки хэш-таблицы;
        if (table[i] == null) {
            System.out.println("Объект не найден!");
        }
        Node<K, V> e = table[i];
        while (e != null) {
            if (e.hash == hash(key) && e.key.equals(key)) {
                table[i] = e.next; //удаляем узел из хэш-таблицы
                size--;
                return;
            }
            e = e.next; //ссылка на следующий узел в пределах одной корзины
        }
    }


    /**
     * Метод,который возвращает количество элементов в хэш-таблице.
     */
    public int size() {
        return size;
    }

    /**
     * Метод переопределяет хеш ключа.
     *
     * @param key ключ, который нужно переопределить.
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16); //хеш-функция
    }

    /**
     * Внутренний класс для хранения данных
     *
     * @param <K> тип ключа
     * @param <V> тип значения
     */
    private class Node<K, V> {
        final int hash; // Для хранения хэш-кода ключа
        final K key;// Для хранения ключа
        V value;// Для хранения значения
        Node<K, V> next;// Для хранения следующего узла с одинаковым хешкодом ключа
/**
 * Конструктор для создания нового узла.
 * @param hash хэш-код ключа
 * @param key ключа
 * @param value значение
 * @param next следующий узел*/
        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;

        }
    }
}

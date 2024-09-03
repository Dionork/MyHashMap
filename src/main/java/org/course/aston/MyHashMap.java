package org.course.aston;

public class MyHashMap<K, V> {
    /**
     * емкость хеш-таблицы по умолчанию (16)
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    /**
     * максимально возможная емкость хеш-таблицы (приблизительно 1 млрд.)
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * коэффициент загрузки, используемый по умолчанию;
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * это «порог» количества элементов в одной корзине,
     * при достижении которого внутренний связный список будет преобразован
     * в древовидную структуру (красно-черное дерево).
     */
    static final int TREEIFY_THRESHOLD = 8;

    /**
     * если количество элементов в одной корзине уменьшится до 6,
     * то произойдет обратный переход от дерева к связному списку;
     */
    static final int UNTREEIFY_THRESHOLD = 6;
    /**
     * минимальная емкость (количество корзин) хеш-таблицы,
     * при которой возможен переход к древовидной структуре.
     * Т.е. если в хеш-таблице по крайней мере 64 бакета и в одном бакете 8 или более элементов,
     * то произойдет переход к древовидной структуре.
     */
    static final int MIN_TREEIFY_CAPACITY = 64;
    private Node<K, V>[] table; // сама хеш-таблица, реализованная на основе массива,
    // для хранения пар «ключ-значение» в виде узлов. Здесь хранятся наши Node;
    private float loadFactor;
    private int capacity;
    private int size; //количество пар «ключ-значение».
    private int threshold; //предельное количество элементов, при достижении которого размер хэш-таблицы увеличивается вдвое.

    public MyHashMap(int initialCapacity, float loadFactor, int threshold) {
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.threshold = threshold;
    }

    public MyHashMap() {
        this.capacity = DEFAULT_INITIAL_CAPACITY;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public MyHashMap(int initialCapacity) {
        this.capacity = initialCapacity;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public MyHashMap(int initialCapacity, float loadFactor) {
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
    }

    public void put(K key, V value) {
        if (table == null) {
            table = new Node[capacity];
        }
        int i = (table.length) - 1 & hash(key); //индекс ячейки хэш-таблицы;
        if (table[i] == null) {
            table[i] = new Node<>(hash(key), key, value, null);
        } else {
            for (Node<K, V> e = table[i]; e != null; e = e.next) {
                if (e.hash == hash(key) && e.key.equals(key)) {
                    e.value = value;
                    return;
                }
            }
            table[i] = new Node<>(hash(key), key, value, table[i]);
        }
        size++;
    }

    public void get(K key) {
        int i = (table.length) - 1 & hash(key); //индекс ячейки хэш-таблицы;
        if (table[i] == null) {
            System.out.println("Объет не найден!");
        }
        Node<K, V> e = table[i]; //ссылка на следующий узел в пределах одной корзины
        while (e != null) {
            if (e.hash == hash(key) && e.key.equals(key)) {
                System.out.println(e.value); // Найдено значение!
                return;
            }
            e = e.next; //ссылка на следующий узел в пределах одной корзины
        }
    }

    public void values() {
        if (table == null) {
            System.out.println("Таблица пуста!");
        }
        for (int i = 0; i < table.length; i++) {
            Node<K, V> e = table[i]; //ссылка на следующий узел в пределах одной корзины
            while (e != null) {
                System.out.println(e.value); // Выводим значение
                e = e.next; //ссылка на следующий узел в пределах одной корзины
            }
        }
    }

    public void keySet() {
        if (table == null) {
            System.out.println("Таблица пуста!");
        }
        for (int i = 0; i < table.length; i++) {
            Node<K, V> e = table[i]; //ссылка на следующий узел в пределах одной корзины
            while (e != null) {
                System.out.println(e.key); // Выводим ключ
                e = e.next; //ссылка на следующий узел в пределах одной корзины
            }
        }
    }

    public void entrySet() {
        if (table == null) {
            System.out.println("Таблица пуста!");
        }
        for (int i = 0; i < table.length; i++) {
            Node<K, V> e = table[i]; //ссылка на следующий узел в пределах одной корзины
            while (e != null) {
                System.out.println(e.key + " " + e.value); // Выводим ключ и значение
                e = e.next; //ссылка на следующий узел в пределах одной корзины
            }
        }
    }

    public void delete(K key) {
        int i = (table.length) - 1 & hash(key); //индекс ячейки хэш-таблицы;
        if (table[i] == null) {
            System.out.println("Объект не найден!");
        }
        Node<K, V> e = table[i]; //ссылка на следующий узел в пределах одной корзины
        while (e != null) {
            if (e.hash == hash(key) && e.key.equals(key)) {
                table[i] = e.next; //удаляем узел из хэш-таблицы
                size--;
                return;
            }
            e = e.next; //ссылка на следующий узел в пределах одной корзины
        }
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16); //хеш-функция
    }

    public int size() {
        return size; //количество элементов в хэш-таблице.
    }

    private class Node<K, V> {
        final int hash; // хеш текущего элемента, который мы получаем в результате хеширования ключа;
        final K key; //ключ текущего элемента.
        V value;//значение текущего элемента.
        Node<K, V> next; //ссылка на следующий узел в пределах одной корзины
        /**
         * Внутрений класс Node содержит информацию о текущем элементе. Ключ и значение.
         * Ссылка на следующий узел в пределах одной корзины.*/
        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

}
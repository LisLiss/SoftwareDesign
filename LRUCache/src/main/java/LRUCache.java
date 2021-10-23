import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private final Map<K, Element> elements = new HashMap<>();
    private Element head, tail;
    private final int capacity;
    private int size;

    protected class Element {
        private Element prev, next;
        private V value;
        private K key;

        Element(K key, V value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }

        Element(K key, V value, Element prev, Element next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    public LRUCache(int capacity) {
        assert capacity > 0 : "Capacity can't be less than zero";
        this.capacity = capacity;
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    private void deleteElement(Element element) {
        Element prev = element.prev;
        Element next = element.next;
        if (prev == null && next == null) {
            this.head = null;
            this.tail = null;
        } else if (prev == null) {
            assert next.prev == element : "Next element must have previous current element";
            assert this.tail == element : "Element without prev is a tail";
            next.prev = null;
            this.tail = next;
        } else if (next == null) {
            assert prev.next == element : "Prev element must have next current element";
            assert this.head == element : "Element without prev is a tail";
            prev.next = null;
            this.head = prev;
        } else {
            assert next.prev == element : "Next element must have previous current element";
            assert prev.next == element : "Prev element must have next current element";
            prev.next = next;
            next.prev = prev;
        }
    }

    private void addElement(Element element) {
        element.next = null;
        if (size == 0) {
            element.prev = null;
            this.head = element;
            this.tail = element;
        } else {
            element.prev = this.head;
            this.head.next = element;
            this.head = element;
        }
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public V get(K key) {
        Element element = elements.get(key);
        if (element == null) return null;
        deleteElement(element);
        addElement(element);
        assert head == element : "Getting element must be in the head of elements";
        return element.value;
    }

    public ArrayList<K> getAll() {
        ArrayList<K> ans = new ArrayList<>();
        Element element = this.tail;
        while (element != null) {
            ans.add(element.key);
            element = element.next;
        }
        return ans;
    }

    public void put(K key, V value) {
        if (elements.containsKey(key)) {
            Element element = elements.get(key);
            deleteElement(element);
            element.value = value;
            addElement(element);
            assert head == element : "Putting updated element must be in the head of elements";
        } else {
            if (capacity == size) {
                remove(this.tail.key);
            }
            Element element = new Element(key, value);
            addElement(element);
            assert head == element : "Putting element must be in the head of elements";
            elements.put(key, element);
            size++;
            assert size == elements.size() : "Size of map must be equal size after putting";
        }
    }

    public void remove(K key) {
        if (elements.containsKey(key)) {
            Element element = elements.get(key);
            deleteElement(element);
            elements.remove(key);
            size--;
            assert size == elements.size() : "Size of map must be equal size after removing";
        }
    }
}

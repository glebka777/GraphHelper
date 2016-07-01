package logic.util;

import java.lang.reflect.Array;

public class Queue<T> {
    private int size;
    private int head;
    private int tail;
    private T[] data;

    public Queue(T element, int size) {
        Class TClass = element.getClass();
        this.data = (T[]) Array.newInstance(TClass, this.size = size);
    }

    public void push(T element) {
        if (++tail == size) {
            tail = 0;
        }
        data[tail] = element;
    }

    public T pop() {
        if (++head == size) {
            head = 0;
        }
        return data[head];
    }

    public boolean isEmpty() {
        return head == tail;
    }

}

package Lab6;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ListNode {
    private Object value;
    private ListNode next;
    private Lock lock;

    public ListNode(Object value, ListNode next) {
        this.value = value;
        this.next = next;
        this.lock = new ReentrantLock();
    }

    public Object getValue() {
        return value;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }

    @Override
    public String toString() {
        return
                "{" + value +
                ", next=" + next +
                '}';
    }
}

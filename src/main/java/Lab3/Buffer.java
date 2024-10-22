package Lab3;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    Queue<Meal> queue;
    int size;

    public Buffer(int size) {
        this.queue = new LinkedList<>();
        this.size = size;
    }

    public synchronized void put(Meal meal) {
        while(queue.size() >= size) {
            System.out.println("No space. Producer is waiting with queue size: " + queue.size());
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        queue.add(meal);
        System.out.println("QUEUE SIZE: " + queue.size());
    }

    public synchronized Meal get() {
        while(queue.isEmpty()) {
            System.out.println("No meal. Consumer is waiting");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        Meal meal = queue.remove();
        System.out.println("QUEUE SIZE: " + queue.size());
        return meal;
    }
}

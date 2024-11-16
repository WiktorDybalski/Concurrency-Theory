package Lab6;


public class ThreadFactory {
    private List list;

    public ThreadFactory(List list) {
        this.list = list;
    }

    public Thread createAddThread(Integer number) {
        return new Thread(() -> {
            boolean result = list.add(number);
            if (result) {
                System.out.println("Added " + number + " to the list.");
            } else {
                System.out.println("Failed to add " + number + " to the list.");
            }
            list.printList();
        });
    }

    public Thread createRemoveThread(Integer number) {
        return new Thread(() -> {
            boolean result = list.remove(number);
            if (result) {
                System.out.println("Removed " + number + " from the list.");
            } else {
                System.out.println("Failed to remove " + number + " from the list. Element not found.");
            }
            list.printList();
        });
    }

    public Thread createContainsThread(Integer number) {
        return new Thread(() -> {
            boolean result = list.contains(number);
            if (result) {
                System.out.println("The list contains the element " + number + ".");
            } else {
                System.out.println("The list does not contain the element " + number + ".");
            }
            list.printList();
        });
    }
}

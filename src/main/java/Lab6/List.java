package Lab6;

public class List {

    private ListNode head;

    public List() {
        this.head = new ListNode(0, null);
    }

    public ListNode getHead() {
        return head;
    }

    public boolean add(Object o) {
        ListNode currNode = head;
        ListNode nextNode;
        currNode.lock();
        try {
            while (currNode.getNext() != null) {
                nextNode = currNode.getNext();
                nextNode.lock();
                currNode.unlock();
                currNode = nextNode;
            }
            ListNode newNode = new ListNode(o, null);
            currNode.setNext(newNode);
        } catch (Exception e) {
            return false;
        } finally {
            currNode.unlock();
        }
        return true;
    }

    public boolean remove(Object o) {
        ListNode currNode = head;
        ListNode nextNode;
        currNode.lock();
        try {
            while (currNode.getNext() != null) {
                nextNode = currNode.getNext();
                nextNode.lock();
                if (nextNode.getValue().equals(o)) {
                    currNode.setNext(nextNode.getNext());
                    currNode.unlock();
                    nextNode.unlock();
                    return true;
                }
                currNode.unlock();
                currNode = nextNode;
            }
        } catch (Exception e) {
            currNode.unlock();
            e.printStackTrace();
            return false;
        }
        currNode.unlock();
        return false;
    }

    public boolean contains(Object o) {
        ListNode currNode = head;
        ListNode nextNode;
        currNode.lock();
        try {
            while (currNode.getNext() != null) {
                nextNode = currNode.getNext();
                nextNode.lock();
                if (nextNode.getValue().equals(o)) {
                    currNode.unlock();
                    nextNode.unlock();
                    return true;
                }
                currNode.unlock();
                currNode = nextNode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public void printList() {
        ListNode currNode = head.getNext();
        synchronized (this) {
            System.out.println("================");
            System.out.print("List: [");
            boolean first = true;
            while (currNode != null) {
                if (!first) {
                    System.out.print(", ");
                }
                System.out.print(currNode.getValue());
                first = false;
                currNode = currNode.getNext();
            }
            System.out.println("]");
            System.out.println("================");
        }
    }
}

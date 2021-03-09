import java.util.NoSuchElementException;

public class MyQueue<E> {
    private static class QueueNode<E> {
        private E element;
        private QueueNode<E> next;

        public QueueNode(E element, QueueNode<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    private QueueNode<E> front;
    private QueueNode<E> end;

    public MyQueue() {
        front = new QueueNode<E>(null, null);
        end = new QueueNode<E>(null, null);
    }

    public int getSize() {
        int size = 0;
        QueueNode<E> p = front;
        while (p.next != null) {
            p = p.next;
            size++;
        }
        return size;
    }

    public boolean isEmpty() {
        return (front == end);

    }

    public void enqueue(E e) {
        QueueNode<E> node = new QueueNode<E>(e, null);
        if (end != null){
            end.next = node;
        }
        end = node;
        if (front.element == null){
            front = end;
        }
    }

    public E dequeue() throws NoSuchElementException {
        if (front == null)
            throw new NoSuchElementException();
        E element = front.element;
        front = front.next;
        if (front == null){
            end = null;
        }
        return element;
    }
}
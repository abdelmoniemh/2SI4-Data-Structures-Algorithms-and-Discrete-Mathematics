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
        end.next = new QueueNode<E>(e, null);
        end = end.next;
    }

    public E dequeue() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        else {
            if (end == front.next){
                end = front;
            }
            E val = front.next.element;
            front.next = front.next.next;
            return val;
        }

    }
}
import java.util.NoSuchElementException;

public class MyQueue<E> {
    private static class QueueNode<E> {
        private E element;
        private QueueNode<E> next;

        public QueueNode(E element) {
            this.element = element;
        }
    }

    private QueueNode<E> first;
    private QueueNode<E> last;

    public int getSize() {
        int size = 1;
        QueueNode<E> p = first;
        while (p.next != null) {
            p = p.next;
            size++;
        }
        return size;
    }

    public boolean isEmpty() {
        return (first == null);

    }

    public void enqueue(E e) {
        QueueNode<E> Node = new QueueNode<E>(e);
        if (last != null){
            last.next = Node;
        }
        last = Node;
        if (first == null){
            first = last;
        }
    }

    public E dequeue() throws NoSuchElementException {
        if (first == null)
            throw new NoSuchElementException();
        E element = first.element;
        first = first.next;
        if (first == null){
            last = null;
        }
        return element;
    }
}
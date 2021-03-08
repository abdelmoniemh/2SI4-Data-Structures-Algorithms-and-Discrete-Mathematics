import java.util.EmptyStackException;

public class MyStack<E> {
    private static class StackNode<E>{
        private E element;
        private StackNode<E> next;

        public StackNode(E element, StackNode<E> next){
            this.element = element;
            this.next = next;
        }
    }

    private StackNode<E> head;

    public MyStack(){
        head = null;
    }

    public boolean isEmpty(){
        return(head == null);
    }

    public E top() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException();
        else {
            return (head.element);
        }
    }

    public void push(E e){
        head = new StackNode<E>(e, head);
    }

    public E pop() throws EmptyStackException{
        if (isEmpty())
            throw new EmptyStackException();
        else {
            E e = head.element;
            head = head.next;
            return (e);
        }
    }
}


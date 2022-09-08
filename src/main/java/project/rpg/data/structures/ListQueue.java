package project.rpg.data.structures;

public class ListQueue<T> {

    Node first;
    int size = 0;

    public void add(T n) {
        if (first == null) {
            first = new Node(n);
        } else {
            Node tmp = first;
            while (tmp.pointer != null) {
                tmp = tmp.pointer;
            }
            tmp.pointer = new Node(n);
        }
        size++;
    }

    public T pop() {
        if (first == null) {
            throw new ArrayIndexOutOfBoundsException("Queue is empty");
        } else {
            Node tmp = this.first;
            T ret = tmp.value;
            this.first = this.first.pointer;
            size--;
            return ret;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        if (first != null) {
            ret.append(this.first.value);

            Node tmp = first;
            while (tmp.pointer != null) {
                tmp = tmp.pointer;
                ret.append(" -> ");
                ret.append(tmp.value);
            }
        }
        return ret.toString();
    }

    public int getSize() {
        return size;
    }

    class Node {
        Node pointer;
        final T value;

        Node(T v) {
            this.value = v;
        }
    }
}

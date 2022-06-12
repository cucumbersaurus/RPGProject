package project.rpg.material;

public class FIFO<J> {

    Node first;

    public void add(J n) {
        if (first == null) {
            this.first = new Node(n);
        } else {
            Node tmp = this.first;
            while (tmp.pointer != null) {
                tmp = tmp.pointer;
            }
            tmp.pointer = new Node(n);
        }
    }

    public J pop() {
        if (first == null) {
            throw new ArrayIndexOutOfBoundsException("비어있는데 꺼넴");
        } else {
            Node tmp = this.first;
            J ret = tmp.value;
            this.first = this.first.pointer;
            tmp = null;
            return ret;
        }
    }

    public boolean isEmpty() {
        if (this.first == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String ret = "";
        if (first != null) {
            ret += this.first.value;

            Node tmp = this.first;
            while (tmp.pointer != null) {
                tmp = tmp.pointer;
                ret += " -> ";
                ret += tmp.value;
            }
        }
        return ret;
    }

    class Node {
        Node pointer;
        J value;

        Node(J v) {
            this.value = v;
        }
    }
}

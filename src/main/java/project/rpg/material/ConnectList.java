package project.rpg.material;

public class ConnectList<T> {

    Node first;

    public boolean add(T a,T b) {
        if (first==null) {
            this.first = new Node(a,b);
        } else {
            Node tmp = this.first;
            while (tmp.pointer != null) {
                tmp = tmp.pointer;
            }
            tmp.pointer = new Node(a,b);
        }
        return true;
    }

    public boolean connect(T invitee) {
        if (first != null) {
            Node tmp = this.first;
            while (tmp.pointer != null) {
                tmp = tmp.pointer;
                if (tmp.invitee == invitee) {
                    tmp.connected = true;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean contains(T sender) {
        if (first != null) {
            Node tmp = this.first;
            while (tmp.pointer != null) {
                tmp = tmp.pointer;
                if (tmp.sender == sender) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containi(T invitee) {
        if (first != null) {
            Node tmp = this.first;
            while (tmp.pointer != null) {
                tmp = tmp.pointer;
                if (tmp.invitee == invitee) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean contain(T sender, T invitee) {
        if (first != null) {
            Node tmp = this.first;
            while (tmp.pointer != null) {
                tmp = tmp.pointer;
                if (tmp.sender == sender && tmp.invitee == invitee) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean connected(T sender, T invitee) {
        if (first != null) {
            Node tmp = this.first;
            while (tmp.pointer != null) {
                tmp = tmp.pointer;
                if (tmp.sender == sender && tmp.invitee == invitee && tmp.connected) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        if (first==null) {
            return "";
        } else {

            Node tmp = this.first;
            stringBuilder.append("{").append(tmp.sender).append(",").append(tmp.invitee).append("}");

            while (tmp.pointer != null) {
                tmp = tmp.pointer;
                stringBuilder.append(" , ");
                stringBuilder.append("{").append(tmp.sender).append(",").append(tmp.invitee).append("}");
            }
        }
        return stringBuilder.toString();
    }

    class Node{

        Node pointer;
        final T sender;
        T invitee;
        boolean connected;

        Node(T s,T i){
            this.sender = s;
            this.invitee = i;
            this.connected = false;
        }
    }
}

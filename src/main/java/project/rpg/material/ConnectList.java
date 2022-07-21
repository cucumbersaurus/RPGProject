package project.rpg.material;

public class ConnectList<T> {

    Node first;

    public boolean add(T a,T b,Direction d) {
        if (!this.contain(a,b)) {
            if (first == null) {
                this.first = new Node(a, b, d);
            } else {
                Node tmp = this.first;
                while (tmp.pointer != null) {
                    tmp = tmp.pointer;
                }
                tmp.pointer = new Node(a, b, d);
            }
            return true;
        } else {
            Node tmp = this.first;
            if ((tmp.leftValue.equals(a) && tmp.rightValue.equals(b)) || (tmp.rightValue.equals(a) && tmp.leftValue.equals(b))) {
                if (tmp.direction != d) {
                    tmp.direction = Direction.C;
                    return true;
                }
            }

            if (tmp.pointer==null){
                return false;
            }
            while ((tmp.pointer.leftValue.equals(a) && tmp.pointer.rightValue.equals(b)) || (tmp.pointer.rightValue.equals(a) && tmp.pointer.leftValue.equals(b))) {
                if ((tmp.leftValue.equals(a) && tmp.rightValue.equals(b)) || (tmp.rightValue.equals(a) && tmp.leftValue.equals(b))) {
                    break;
                }
                tmp = tmp.pointer;
            }
            if (tmp.pointer.direction != d) {
                tmp.pointer.direction = Direction.C;
                return true;
            }
            return false;
        }
    }

    @Deprecated
    public boolean connect(T a) {
        if (first != null) {
            Node tmp = this.first;
            while (tmp.pointer != null) {
                if (tmp.leftValue.equals(a)) {
                    tmp.direction = Direction.C;
                    return true;
                }
                tmp = tmp.pointer;
            }
        }
        return false;
    }

    @Deprecated
    public boolean containL(T a) {
        if (first != null) {
            Node tmp = this.first;
            while (tmp.pointer != null) {
                if (tmp.rightValue.equals(a)) {
                    return true;
                }
                tmp = tmp.pointer;
            }
            return tmp.rightValue.equals(a);
        }
        return false;
    }

    @Deprecated
    public boolean containR(T b) {
        if (first != null) {
            Node tmp = this.first;
            while (tmp.pointer != null) {
                if (tmp.leftValue.equals(b)) {
                    return true;
                }
                tmp = tmp.pointer;
            }
            return tmp.leftValue.equals(b);
        }
        return false;
    }

    public boolean contain(T a, T b) {
        if (first != null) {
            Node tmp = this.first;
            if ((tmp.leftValue.equals(a) && tmp.rightValue.equals(b)) || (tmp.rightValue.equals(a) && tmp.leftValue.equals(b))) {
                return true;
            }
            while (tmp.pointer != null) {
                tmp = tmp.pointer;
                if ((tmp.pointer.leftValue.equals(a) && tmp.pointer.rightValue.equals(b)) || (tmp.pointer.rightValue.equals(a) && tmp.pointer.leftValue.equals(b))) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean isConnected(T a, T b) {
        if (first != null) {
            Node tmp = this.first;
            while (tmp.pointer != null) {
                tmp = tmp.pointer;
                if ((tmp.pointer.leftValue.equals(a) && tmp.pointer.rightValue.equals(b)) || (tmp.pointer.rightValue.equals(a) && tmp.pointer.leftValue.equals(b))) {
                    return tmp.pointer.direction == Direction.C;
                }
            }
        }
        return false;
    }

    public Direction getDirect(T a, T b) {
        if (first != null) {
            Node tmp = this.first;
            while (tmp.pointer != null) {
                tmp = tmp.pointer;
                if ((tmp.pointer.leftValue.equals(a) && tmp.pointer.rightValue.equals(b)) || (tmp.pointer.rightValue.equals(a) && tmp.pointer.leftValue.equals(b))) {
                    return tmp.pointer.direction;
                }
            }
            if ((tmp.leftValue.equals(a) && tmp.rightValue.equals(b)) || (tmp.rightValue.equals(a) && tmp.leftValue.equals(b))) {
                return tmp.direction;
            }
        }
        return Direction.NULL;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        if (first==null) {
            return "";
        } else {

            Node tmp = this.first;
            stringBuilder.append("{").append(tmp).append("}");

            while (tmp.pointer != null) {
                tmp = tmp.pointer;
                stringBuilder.append(" , ");
                stringBuilder.append("{").append(tmp).append("}");
            }
        }
        return stringBuilder.toString();
    }

    class Node{

        Node pointer;
        T leftValue;
        T rightValue;
        Direction direction;

        @Override
        public String toString() {
            switch (direction) {
                case LTR:
                    return leftValue + " -> " + rightValue;
                case RTL:
                    return leftValue + " <- " + rightValue;
                case C:
                    return leftValue + " <-> " + rightValue;
                default:
                    return "";
            }
        }

        Node(T l, T r, Direction d){
            this.leftValue = l;
            this.rightValue = r;
            this.direction = d;
        }
    }
}

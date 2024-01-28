package mountain;

public class Side {

    private Point a;
    private Point b;

    public Side(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Side side = (Side) o;

        return (a.equals(side.b) && b.equals(side.b)) || (a.equals(side.b) && b.equals(side.a));

    }

    @Override
    public int hashCode() {
        return a.hashCode() + b.hashCode();
    }
}

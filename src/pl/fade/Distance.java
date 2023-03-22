package pl.fade;

public class Distance implements Comparable<Distance>{

    public final Node nodeTrain;
    public final Node nodeTest;
    public final Double distance;

    public Distance(Node nodeTrain, Node nodeTest, Double distance) {
        this.nodeTrain = nodeTrain;
        this.nodeTest = nodeTest;
        this.distance = distance;
    }

    public Node getNodeTrain() {
        return nodeTrain;
    }

    public Node getNodeTest() {
        return nodeTest;
    }

    @Override
    public String toString() {
        return "Distance{" +
                "nodeTrain=" + nodeTrain +
                ", nodeTest=" + nodeTest +
                '}';
    }

    @Override
    public int compareTo(Distance dis) {
        return Double.compare(this.distance, dis.distance);
    }
}

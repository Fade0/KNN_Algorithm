package pl.fade;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("KNN implementation");

        List<Node> nodeTrainingList;
        List<Node> nodeTestingList;
        int k = 1;

        String pathTrainingFile;
        String pathTestingFile;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Insert value for K");
        k = Integer.parseInt(br.readLine());

        System.out.println("Insert path to training file");
        pathTrainingFile = br.readLine();

        System.out.println("Insert path to testing file");
        pathTestingFile = br.readLine();

        nodeTrainingList = getNodeList(pathTrainingFile);
        nodeTestingList = getNodeList(pathTestingFile);


        return;
    }

    public static List<Node> getNodeList(String path) throws IOException {
        String line;
        List<Node> nodeSet = new ArrayList<>();
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);


        while ((line = br.readLine()) != null) {
            String[] temp = line.split(",");

            List<Double> attributes = new ArrayList<>();
            for (int i = 0; i < temp.length - 1; i++) {
                attributes.add(Double.valueOf(temp[i]));
            }

            nodeSet.add(new Node(attributes, temp[temp.length - 1]));
        }
        System.out.println(nodeSet);
        return nodeSet;
    }


    public static double getDistanceBetweenNodes(Node node1, Node node2) {
        if (node1 != null) {

            if (node2 != null) {
                double distance = 0;
                int length = node1.getAttributesColumn().size();
                for (int i = 0; i < length; i++) {
                    distance += Math.pow(node1.getAttributesColumn().get(i) - node2.getAttributesColumn().get(i), 2);
                }
                return distance;
            }
        } else {
            System.err.println("there is null somewhere");
            return -1;
        }
        return -1;
    }

    public static String alogythmKNN(int k, List<Node> nodeTrainingList, List<Node> nodeTestingList) {
        String result = "";
        Double accuracy = 0.0;


        for (Node testedNode : nodeTestingList) {

            List<Distance> distanceList = new ArrayList<>();

            for (Node trainedNode : nodeTrainingList) {
                distanceList.add(new Distance(testedNode, trainedNode, getDistanceBetweenNodes(testedNode, trainedNode)));
            }

            Collections.sort(distanceList);

        }
        return result;
    }



}


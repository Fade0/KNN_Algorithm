package pl.fade;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

        //System.out.println("Insert vector");
        //setFromConsole();

        System.out.println("Insert path to testing file");
        pathTestingFile = br.readLine();
        
        nodeTrainingList = getNodeList(pathTrainingFile);
        nodeTestingList = getNodeList(pathTestingFile);

        KNN(k, nodeTestingList, nodeTrainingList);

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


    public static double getDistanceNodes(Node node1, Node node2) {
        //Euklides
        if (node1 != null) {

            if (node2 != null) {
                double distance = 0;
                int length = node1.getAttributesColumn().size();

                for (int i = 0; i < length; i++) {
                    distance += Math.pow(node1.getAttributesColumn().get(i) - node2.getAttributesColumn().get(i), 2);
                }
                return distance;

            }
        }
        else {
            System.err.println("there is null somewhere");
            return -1;

        }
        return -1;

    }

    public static String KNN(int k, List<Node> nodeTestList, List<Node> nodeTrainList){
        String answer = "";
        double goodAnswer = 0;

        for (Node testedNode : nodeTestList) {
            List<Distance> distanceList = new ArrayList<>();
            List<String> stringList = new ArrayList<>();
            Set<String> stringSet = new HashSet<>();
            double correctVectors = 0;
            int max = 0;

            for (Node trainedNode : nodeTrainList){
                distanceList.add(new Distance(testedNode, trainedNode, getDistanceNodes(testedNode, trainedNode)));

            }

            Collections.sort(distanceList);

            for (int j = 0; j < k; j++) {
                stringList.add(distanceList.get(j).getNodeTest().getClassName());
                stringSet.add(distanceList.get(j).getNodeTest().getClassName());

                if (distanceList.get(j).getNodeTrain().getClassName().equals(testedNode.getClassName())){
                    correctVectors++;

                }
            }

            for (String string : stringSet) {
                if (Collections.frequency(stringList, string) > max) {
                    max = Collections.frequency(stringList, string);
                    answer = string;
                    System.out.println("max:" + max);

                }
            }

            if (answer.equals(testedNode.getClassName())){
                goodAnswer++;
                System.out.println("Correct Answer: " + goodAnswer);

            }


            System.out.println("Answer: " + answer);
            System.out.println("Vector:  " + ((correctVectors / k) * 100) + "%");
            System.out.println("Correct: " + testedNode.getClassName());
            System.out.println("--------------------");

        }

            System.out.println("Accuracy: "+ (goodAnswer/nodeTestList.size())*100+"%");
        return answer;
    }

    //Not working part
    public static void setFromConsole(){
        boolean dataIsCorrect = false;
            String line = "";
            System.out.println("Enter your vector 'a1,a2,a3,a4,k + name\n to exit type f");
            Scanner scanner = new Scanner(line);
            line = scanner.nextLine();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            if(!line.equals("f")){
                dataIsCorrect = false;
                return;
            }

            String[] tab = line.split(",");
            Double[] attributes = new Double[tab.length - 1];

            for (int i = 0; i < attributes.length-1; i++){
                attributes[i] = Double.parseDouble(tab[i].trim());
                System.out.println(attributes[i]);
            }
            return;
        }



}


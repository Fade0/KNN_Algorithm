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
        List<Node> userTestingList;
        int k = 1;

        String pathTrainingFile;
        String pathTestingFile;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Insert value for K");
        k = Integer.parseInt(br.readLine());

        //Loading TrainingFile
        System.out.println("Insert path to training file");
        pathTrainingFile = br.readLine();
        nodeTrainingList = getNodeList(pathTrainingFile);

        //Selecting Test option
        String userChoice = "";
        System.out.println("Do you want to load test data from file or insert vector it yourself [File/Vector]");
        userChoice = br.readLine();
        System.out.println(userChoice);

        if(userChoice.trim().equals("File")){
            //Loading TestingFile
            System.out.println("Insert path to testing file");
            pathTestingFile = br.readLine();
            nodeTestingList = getNodeList(pathTestingFile);
            KNN(k, nodeTestingList, nodeTrainingList);

        } else if (userChoice.trim().equals("Vector")) {
            //Custom vector
            System.out.println("Insert custom vector");
            userTestingList = setFromConsole();
            KNN(k,userTestingList,nodeTrainingList);

        }

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
            System.out.println("---------");

        }

            System.out.println("Accuracy: "+ (goodAnswer/nodeTestList.size())*100+"%");
        return answer;
    }


    public static List<Node> setFromConsole() throws IOException {
        List<Node> userNodeList = new ArrayList<>();

        String line = "";
        System.out.println("Enter your vector 'a1,a2,a3,a4 + name\n");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        line = br.readLine();

        //System.out.println(line);

        String[] tab = line.split(",");
        Double[] attr = new Double[tab.length-2];
        List<Double> userDouble = new ArrayList<>();
        //System.out.println("attr leng"+ attr.length);
        for (int i =0; i< attr.length; i++){
            attr[i] = Double.valueOf(tab[i].trim());
            //System.out.println(attr[i]);
            //System.out.println("tab"+ tab.length);
        }

        userDouble.add(attr[attr.length-3]);
        userDouble.add(attr[attr.length-2]);
        userDouble.add(attr[attr.length-1]);
        userDouble.add(attr[0]);
        //System.out.println("attr" + attr.length);
        userNodeList.add(new Node(userDouble,tab[tab.length-1]));


        return userNodeList;
    }


}


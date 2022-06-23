import java.util.*;

public class Main {


    public static void main(String[] args) {
        Comparator<Node> comparator = Comparator.comparing(Node::getValue);
        comparator = comparator.thenComparing(Node::getqPlace);

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();

        ArrayList<Node> allNodes = countLetters(line);
        int sumBefore = 0;
        int sumAfter = 0;

        for (int i = 0; i < allNodes.size(); i++) {
            allNodes.get(i).setCode(Integer.toBinaryString(i));
            sumBefore += Integer.parseInt(allNodes.get(i).getCode(), 2) * allNodes.get(i).getValue();
            if (allNodes.get(i).getCode().length() == 1)
                allNodes.get(i).setCode("00" + allNodes.get(i).getCode());
            if (allNodes.get(i).getCode().length() == 2)
                allNodes.get(i).setCode("0" + allNodes.get(i).getCode());
        }


        for (Node n : allNodes) {
            if (!String.valueOf(n.getC()).equals("none"))
                System.out.println(n.getC() + " code: " + n.getCode() + " quantity: " + n.getValue());
            n.setCode("");
        }
        System.out.println("SUM: " + sumBefore);

        allNodes.sort(comparator);

        System.out.println();

        ArrayList<Node> passedNodes = new ArrayList<>();
        while (allNodes.size() != 1) {
            //System.out.println("BEFORE TURN : " + allNodes);
            if (allNodes.get(0).getC().length() == 1) {
                allNodes.add(new Node(" Parent of " + allNodes.get(0).getValue() + allNodes.get(0).getC() + " and " + allNodes.get(1).getValue() + allNodes.get(1).getC(),
                        allNodes.get(0).getValue() + allNodes.get(1).getValue(),
                        999, allNodes.get(0), allNodes.get(1)));

            } else {
                allNodes.add(new Node(" Parent of " + allNodes.get(0).getValue() + " and " + allNodes.get(1).getValue(),
                        allNodes.get(0).getValue() + allNodes.get(1).getValue(),
                        999, allNodes.get(0), allNodes.get(1)));
            }//CZY W KOLEJCE POWINNO BYC 0 CZY 999?


            allNodes.get(allNodes.size() - 1).setChildLeft(allNodes.get(0));
            allNodes.get(allNodes.size() - 1).setChildRight(allNodes.get(1));
            allNodes.get(0).setSide("Left");
            allNodes.get(1).setSide("Right");
            passedNodes.add(allNodes.get(0));
            passedNodes.add(allNodes.get(1));
            allNodes.remove(1);
            allNodes.remove(0);
            allNodes.sort(comparator);
            //System.out.println("AFTER TURN : " + allNodes);
        }


        passedNodes.add(allNodes.get(0));

        for (int i = passedNodes.size() - 1; i > 0; i--) {
            System.out.println(passedNodes.get(i).getC() + "\t= " + passedNodes.get(i).getValue() +
                    "\t=>\tleft child: " + passedNodes.get(i).getChildLeft() + " " +
                    ", right child: " + passedNodes.get(i).getChildRight());


            if (passedNodes.get(i).getSide() != null) {
                if (passedNodes.get(i).getSide().equals("Left")) {
                    passedNodes.get(i).setCode(passedNodes.get(i).getCode() + "0");

                    System.out.println("LEFT [adding 0 to code]\t\t");


                    setChildrenCodes(passedNodes, i);
                }


                if (passedNodes.get(i).getSide().equals("Right")) {
                    System.out.println("RIGHT [adding 1 to code]\t\t");
                    passedNodes.get(i).setCode(passedNodes.get(i).getCode() + "1");


                    setChildrenCodes(passedNodes, i);

                }
                System.out.println("new code for: " + passedNodes.get(i).getC() + " = " +
                        passedNodes.get(i).getCode());
            }
            System.out.println();
        }


        for (Node n : passedNodes) {
            if (!String.valueOf(n.getC()).contains("Parent")) {
                System.out.println(n.getC() + " code: " + n.getCode());
                sumAfter += Integer.parseInt(n.getCode(), 2) * n.getValue();
            }

        }
        System.out.println("SUM: " + sumAfter);
    }

    private static void setChildrenCodes(ArrayList<Node> passedNodes, int i) {
        if (passedNodes.get(i).getC().contains("Parent")) {

            passedNodes.get(i).getChildLeft().setCode(passedNodes.get(i).getCode());
            System.out.println("new child L code = " + passedNodes.get(i).getChildLeft().getCode());
            passedNodes.get(i).getChildRight().setCode(passedNodes.get(i).getCode());
            System.out.println("new child R code = " + passedNodes.get(i).getChildRight().getCode());
        }
    }


    private static ArrayList<Node> countLetters(String line) {
        ArrayList<Node> allNodes = new ArrayList<>();
        int counter = 0;
        //allNodes.add(new Node(line.charAt(0), 1, counter));
        //System.out.print("adding new char " + line.charAt(0) + "\n");
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            int internalCounter = 0;
            //System.out.print("next: " + c + "\t");

            for (int j = 0; j < allNodes.size(); j++) {

                if (Objects.equals(allNodes.get(j).getC(), String.valueOf(c))) {
                    internalCounter++;
                    allNodes.get(j).setValue(allNodes.get(j).getValue() + 1);
                    //System.out.print("increasing " + c + "\n");
                }
            }
            if (internalCounter == 0) {
                counter++;
                allNodes.add(new Node(String.valueOf(c), 1, counter));
                //System.out.print("adding new char " + c + "\n");
            }
        }
        return allNodes;
    }

}

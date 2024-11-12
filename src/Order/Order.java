package Order;

import AllData.AllData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Order {
    AllData allData = new AllData();
    //link to file
    String linkService = "src/File/serviceFile.txt";
    String linkAllDataOrder = "src/File/allDataOrder.txt";
    String linkPlantFileUpdated = "src/File/plantFileUpdate.txt";
    String linkClientFileUpdated = "src/File/clientFileUpdate.txt";
    String linkOrder = "src/File/order.txt";
    //create file
    File serviceFile = new File(linkService);
    File fileAllDataOrder = new File(linkAllDataOrder);
    File plantFileUpdated = new File(linkPlantFileUpdated);
    File clientFileUpdated = new File(linkClientFileUpdated);
    File orderFile = new File(linkOrder);
    //
    public void addCostService() throws IOException {
        BufferedReader readAllDataOrder = new BufferedReader(new FileReader(fileAllDataOrder));
        List<List<String>> addTypeService0 = new ArrayList<>();

        while (true) {
            if (readAllDataOrder.readLine() == null && fileAllDataOrder.exists()) {
                BufferedReader fileReadAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                BufferedReader fileReadService = new BufferedReader(new FileReader(serviceFile));
                BufferedWriter fileWriterAllData = new BufferedWriter(new FileWriter(fileAllDataOrder, true));
                // if - else
                if (fileReadService.readLine() != null && fileReadAllData.readLine() == null) {
                    BufferedReader readService = new BufferedReader(new FileReader(serviceFile));
                    String lineAddDataFile;
                    while ((lineAddDataFile = readService.readLine()) != null) {
                        fileWriterAllData.write(lineAddDataFile + "\n");
                    }
                    readService.close();
                } else if (fileReadService.readLine() == null && fileReadAllData.readLine() == null) {
                    System.out.println("You must add service");
                    break;
                }
                //add data in list
                BufferedReader addListAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                String line;
                //info from file add to list
                while ((line = addListAllData.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    listAdd.add(line);
                    addTypeService0.add(listAdd);
                }
                //see data
                System.out.println("----------List type of service----------");
                int listi = 1;
                for (List<String> list : addTypeService0) {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(listi + ") " + list.get(i));
                        listi++;
                    }
                }
                System.out.println();
                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want add cost to order? Enter YES or NO: ");
                    String answer = in.nextLine().toLowerCase();
                    if (answer.equals("yes")) {
                        while (true) {
                            System.out.println(addTypeService0);
                            System.out.print("Please enter name of service: ");
                            String answerTypeService = in.nextLine();
                            System.out.print("Please enter cost for service: ");
                            String answerCost = in.nextLine();
                            try {
                                int cost = Integer.parseInt(answerCost);
                                if (answerTypeService.equals("no") || answerCost.equals("no")) {
                                    System.out.println("No problem");
                                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAllDataOrder))) {
                                        for (List<String> list : addTypeService0) {
                                            for (String item : list) {
                                                if (list.size() == 2) {
                                                    writer.write(list.get(0) + "," + list.get(1));
                                                    break;
                                                } else {
                                                    writer.write(item);
                                                }
                                            }
                                            writer.newLine();
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                } else if (cost >= 1) {
                                    boolean addCostList = false;
                                    for (List<String> list : addTypeService0) {
                                        for (int i = 0; i < list.size(); i++) {
                                            if (list.get(0).equals(answerTypeService) && list.size() < 2) {
                                                list.add(answerCost);
                                                System.out.println("System successful add cost");
                                                addCostList = true;
                                                break;
                                            } else {
                                                addCostList = false;
                                            }
                                        }
                                        if (addCostList) {
                                            break;
                                        }
                                    }
                                    if (!addCostList) {
                                        System.out.println("You can not add cost because type of service did not exist\n");
                                    }
                                } else if (cost < 1) {
                                    System.out.println("System must be > 0");
                                }
                            } catch (NumberFormatException e) {
                                if (answerCost.equals("no") || answerTypeService.equals("no")) {
                                    System.out.println("No problem");
                                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAllDataOrder))) {
                                        for (List<String> list : addTypeService0) {
                                            for (String item : list) {
                                                if (list.size() == 2) {
                                                    writer.write(list.get(0) + "," + list.get(1));
                                                    break;
                                                } else {
                                                    writer.write(item);
                                                }
                                            }
                                            writer.newLine();
                                        }
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                    break;
                                } else {
                                    System.out.println("Please enter correct data");
                                }
                            }
                        }
                        break;
                    } else if (answer.equals("no")) {
                        System.out.println("No problem");
                        break;
                    }
                }
                addListAllData.close();
                break;
            } else if (readAllDataOrder.readLine() != null && fileAllDataOrder.exists()) {
                BufferedReader readFileOrder = new BufferedReader(new FileReader(fileAllDataOrder));
                String line;
                //add data in list
                while ((line = readFileOrder.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    if (line.contains(",")) {
                        int index = 0;
                        for (int i = 0; i < line.length(); i++) {
                            if (line.charAt(i) == ',') {
                                listAdd.add(line.substring(index, i));
                                index = i + 1;
                            }
                        }
                        listAdd.add(line.substring(index));
                    } else {
                        listAdd.add(line);
                    }
                    addTypeService0.add(listAdd);
                }
                //saw data
                System.out.println("--------List data of service--------");
                //[[ww], [sss, 2121], [dddd, 232], [hhh]]
                int listi = 1;
                for (List<String> list : addTypeService0) {
                    for (String lineForList : list) {
                        if (list.size() > 1) {
                            String temporaryStr = String.join(", ", list);
                            System.out.println(listi + ") " + temporaryStr);
                            listi++;
                            break;
                        } else {
                            System.out.println(listi + ") " + lineForList);
                            listi++;
                        }
                    }
                }
                System.out.println();
                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want add cost to order? Enter YES or NO: ");
                    String answer = in.nextLine().toLowerCase();
                    if (answer.equals("yes")) {
                        while (true) {
                            System.out.print("Please enter name of service: ");
                            String answerTypeService = in.nextLine();
                            System.out.print("Please enter cost for service: ");
                            String answerCost = in.nextLine();
                            try {
                                int cost = Integer.parseInt(answerCost);
                                if (answerTypeService.equals("no") || answerCost.equals("no")) {
                                    System.out.println("No problem");
                                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAllDataOrder))) {
                                        for (List<String> list : addTypeService0) {
                                            for (String item : list) {
                                                int index = list.size() - 1;
                                                if (list.size() == 1 || item == list.get(index)) {
                                                    writer.write(item);
                                                } else {
                                                    writer.write(item + ",");
                                                }
                                            }
                                            writer.newLine();
                                        }
                                        addTypeService0.clear();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        addTypeService0.clear();
                                    }
                                    break;
                                } else {
                                    if (cost >= 1) {
                                        for (List<String> list : addTypeService0) {
                                            if (list.get(0).equals(answerTypeService) && list.size() == 1) {
                                                list.add(1, answerCost);
                                                System.out.println("System successful add cost");
                                                break;
                                            } else if (list.get(0).equals(answerTypeService) && list.size() >= 2 && list.get(1).isBlank()) {
                                                list.set(1, answerCost);
                                                System.out.println("System successful add cost");
                                                break;
                                            } else if (list.get(0).equals(answerTypeService) && list.size() >= 2 && !list.get(1).isBlank()) {
                                                System.out.println("You can not add cost because it is exist");
                                                break;
                                            }
                                        }
                                    } else {
                                        System.out.println("Cost must be > 1");
                                    }
                                }
                            } catch (NumberFormatException e) {
                                if (answerTypeService.equals("no") || answerCost.equals("no")) {
                                    System.out.println("No problem");
                                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAllDataOrder))) {
                                        for (List<String> list : addTypeService0) {
                                            for (String item : list) {
                                                int index = list.size() - 1;
                                                if (list.size() == 1 || item == list.get(index)) {
                                                    writer.write(item);
                                                } else {
                                                    writer.write(item + ",");
                                                }
                                            }
                                            writer.newLine();
                                        }
                                        addTypeService0.clear();
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                        addTypeService0.clear();
                                    }
                                    break;
                                } else {
                                    System.out.println("Please enter correct data");
                                }
                            }
                        }
                        break;
                    } else if (answer.equals("no")) {
                        System.out.println("No problem");
                        addTypeService0.clear();
                        break;
                    }
                }
                break;
            } else if (!fileAllDataOrder.exists()) {
                fileAllDataOrder.createNewFile();
                System.out.println("File successful create");
            } else {
                System.out.println("You can not add cost to service, because did not exist no one type of service");
                break;
            }
        }
        readAllDataOrder.close();
        allData.menu();

    }
    public void removeCostService() throws IOException {
        BufferedReader readTypeService = new BufferedReader(new FileReader(serviceFile));
        BufferedReader readAllDataOrder = new BufferedReader(new FileReader(fileAllDataOrder));
        List<List<String>> addTypeService0 = new ArrayList<>();

        while (true) {
            if (readAllDataOrder.readLine() == null && fileAllDataOrder.exists()) {
                System.out.println("You can not delete cost because cost empty");
                break;
            } else if (readAllDataOrder.readLine() != null && fileAllDataOrder.exists()) {
                BufferedReader readFileOrder = new BufferedReader(new FileReader(fileAllDataOrder));
                String line;
                //add data in list
                while ((line = readFileOrder.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    if (line.contains(",")) {
                        int index = 0;
                        for (int i = 0; i < line.length(); i++) {
                            if (line.charAt(i) == ',') {
                                listAdd.add(line.substring(index, i));
                                index = i + 1;
                            }
                        }
                        listAdd.add(line.substring(index));
                    } else {
                        listAdd.add(line);
                    }
                    addTypeService0.add(listAdd);
                }
                //saw data
                System.out.println("--------List data of service--------");
                int listi = 1;
                for (List<String> list : addTypeService0) {
                    for (String lineForList : list) {
                        if (list.size() > 1) {
                            String temporaryStr = String.join(", ", list);
                            System.out.println(listi + ") " + temporaryStr);
                            listi++;
                            break;
                        } else {
                            System.out.println(listi + ") " + lineForList);
                            listi++;
                        }
                    }
                }
                System.out.println();
                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want delete cost to order? Enter YES or NO: ");
                    String answer = in.nextLine().toLowerCase();

                    if (answer.equals("yes")) {
                        while (true) {
                            System.out.print("Please enter name of service: ");
                            String answerTypeService = in.nextLine();
                            if (answerTypeService.equals("no")) {
                                System.out.println("No problem");
                                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAllDataOrder))) {
                                    for (List<String> list : addTypeService0) {
                                        for (String item : list) {
                                            int index = list.size() - 1;
                                            if (list.size() == 1 || item == list.get(index)) {
                                                writer.write(item);
                                            } else {
                                                writer.write(item + ",");
                                            }
                                        }
                                        writer.newLine();
                                    }
                                    addTypeService0.clear();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    addTypeService0.clear();
                                }
                                break;
                            } else {
                                for (List<String> list : addTypeService0) {
                                    if (list.get(0).equals(answerTypeService) && (list.size() == 1 || list.get(1).isBlank())) {
                                        System.out.println("This cost did not exist");
                                        break;
                                    } else if (list.get(0).equals(answerTypeService) && list.size() >= 2 && !list.get(1).isBlank()) {
                                        list.set(1, " ");
                                        System.out.println("System successful remove cost");
                                        break;
                                    }
                                }
                            }


                        }
                        break;
                    } else if (answer.equals("no")) {
                        System.out.println("No problem");
                        addTypeService0.clear();
                        break;
                    }
                }
                break;
            } else if (!fileAllDataOrder.exists()) {
                fileAllDataOrder.createNewFile();
                System.out.println("File successful create");
            } else {
                System.out.println("You can not add cost to service, because did not exist no one type of service");
                break;
            }
        }
        readTypeService.close();
        readAllDataOrder.close();
        allData.menu();
    }
    public void addSizeService() throws IOException {
        BufferedReader readAllDataOrder = new BufferedReader(new FileReader(fileAllDataOrder));
        List<List<String>> addTypeService0 = new ArrayList<>();
        while (true) {
            if (readAllDataOrder.readLine() == null && fileAllDataOrder.exists()) {
                BufferedReader fileReadAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                BufferedReader fileReadService = new BufferedReader(new FileReader(serviceFile));
                BufferedWriter fileWriterAllData = new BufferedWriter(new FileWriter(fileAllDataOrder, true));
                // if - else
                if (fileReadService.readLine() != null && fileReadAllData.readLine() == null) {
                    BufferedReader readService = new BufferedReader(new FileReader(serviceFile));
                    String lineAddDataFile;
                    while ((lineAddDataFile = readService.readLine()) != null) {
                        fileWriterAllData.write(lineAddDataFile + "\n");
                    }
                    readService.close();
                } else if (fileReadService.readLine() == null && fileReadAllData.readLine() == null) {
                    System.out.println("You must add service");
                    break;
                }
                //add data in list
                BufferedReader addListAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                String line;
                //info from file add to list
                while ((line = addListAllData.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    listAdd.add(line);
                    addTypeService0.add(listAdd);
                }
                //see data
                System.out.println("----------List of service----------");
                int listi = 1;
                for (List<String> list : addTypeService0) {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(listi + ") " + list.get(i));
                        listi++;
                    }
                }
                System.out.println();
                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want add size to order? Enter YES or NO: ");
                    String answer = in.nextLine().toLowerCase();
                    if (answer.equals("yes")) {
                        while (true) {
                            System.out.println(addTypeService0);
                            System.out.print("Please enter name of service: ");
                            String answerTypeService = in.nextLine();
                            System.out.print("Please enter size for service: ");
                            String answerSize = in.nextLine();
                            try {
                                int size = Integer.parseInt(answerSize);
                                if (answerTypeService.equals("no") || answerSize.equals("no")) {
                                    System.out.println("No problem");
                                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAllDataOrder))) {
                                        for (List<String> list : addTypeService0) {
                                            for (String item : list) {
                                                if (list.size() == 3) {
                                                    writer.write(list.get(0) + "," + list.get(1) + ", " + list.get(2));
                                                    break;
                                                } else {
                                                    writer.write(item);
                                                }
                                            }
                                            writer.newLine();
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                } else if (size >= 1) {
                                    boolean addSizeList = false;
                                    for (List<String> list : addTypeService0) {
                                        for (int i = 0; i < list.size(); i++) {
                                            if (list.get(0).equals(answerTypeService) && list.size() < 2) {
                                                list.add(1, "");
                                                list.add(2, answerSize);
                                                System.out.println("System successful add size");
                                                addSizeList = true;
                                                break;
                                            } else if (list.get(0).equals(answerTypeService) && list.size() >= 2) {
                                                if (list.size() > 2 && list.get(2).isBlank()) {
                                                    list.set(2, answerSize);
                                                } else {
                                                    list.add(2, answerSize);
                                                }
                                                System.out.println("System successful add size");
                                                addSizeList = true;
                                                break;
                                            } else {
                                                addSizeList = false;
                                            }
                                        }
                                        if (addSizeList) {
                                            break;
                                        }
                                    }
                                    if (!addSizeList) {
                                        System.out.println("You can not add size because type of service did not exist\n");
                                    }
                                } else if (size < 1) {
                                    System.out.println("System must be > 0");
                                }
                            } catch (NumberFormatException e) {
                                if (answerSize.equals("no") || answerTypeService.equals("no")) {
                                    System.out.println("No problem");
                                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAllDataOrder))) {
                                        for (List<String> list : addTypeService0) {
                                            for (String item : list) {
                                                if (list.size() == 3) {
                                                    writer.write(list.get(0) + "," + list.get(1) + "," + list.get(2));
                                                    break;
                                                } else {
                                                    writer.write(item);
                                                }
                                            }
                                            writer.newLine();
                                        }
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                    break;
                                } else {
                                    System.out.println("Please enter correct data");
                                }
                            }
                        }
                        break;
                    } else if (answer.equals("no")) {
                        System.out.println("No problem");
                        break;
                    }
                }
                addListAllData.close();
                break;
            } else if (readAllDataOrder.readLine() != null && fileAllDataOrder.exists()) {
                BufferedReader readFileOrder = new BufferedReader(new FileReader(fileAllDataOrder));
                String line;
                //add data in list
                while ((line = readFileOrder.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    if (line.contains(",")) {
                        int index = 0;
                        for (int i = 0; i < line.length(); i++) {
                            if (line.charAt(i) == ',') {
                                listAdd.add(line.substring(index, i));
                                index = i + 1;
                            }
                        }
                        listAdd.add(line.substring(index));
                    } else {
                        listAdd.add(line);
                    }
                    addTypeService0.add(listAdd);
                }
                //saw data
                System.out.println("--------List of service--------");
                //[[ww], [sss, 2121], [dddd, 232], [hhh]]
                int listi = 1;
                for (List<String> list : addTypeService0) {
                    for (String lineForList : list) {
                        if (list.size() > 1) {
                            String temporaryStr = String.join(", ", list);
                            System.out.println(listi + ") " + temporaryStr);
                            listi++;
                            break;
                        } else {
                            System.out.println(listi + ") " + lineForList);
                            listi++;
                        }
                    }
                }
                System.out.println();

                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want add size to order? Enter YES or NO: ");
                    String answer = in.nextLine().toLowerCase();
                    if (answer.equals("yes")) {
                        while (true) {
                            System.out.print("Please enter name of service: ");
                            String answerTypeService = in.nextLine();
                            System.out.print("Please enter size for service: ");
                            String answerSize = in.nextLine();
                            try {
                                int cost = Integer.parseInt(answerSize);
                                if (answerTypeService.equals("no") || answerSize.equals("no")) {
                                    System.out.println("No problem");
                                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAllDataOrder))) {
                                        for (List<String> list : addTypeService0) {
                                            for (String item : list) {
                                                int index = list.size() - 1;
                                                if (list.size() == 1 || item == list.get(index)) {
                                                    writer.write(item);
                                                } else {
                                                    writer.write(item + ",");
                                                }
                                            }
                                            writer.newLine();
                                        }
                                        addTypeService0.clear();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        addTypeService0.clear();
                                    }
                                    break;
                                } else {
                                    if (cost >= 1) {
                                        for (List<String> list : addTypeService0) {
                                            if (list.get(0).equals(answerTypeService) && list.size() == 1) {
                                                list.add(1, "");
                                                list.add(2, answerSize);
                                                System.out.println("System successful add size");
                                                break;
                                            } else if (list.get(0).equals(answerTypeService) && list.size() >= 2 && !list.get(2).isBlank()) {
                                                System.out.println("This service have size");
                                                break;
                                            } else if (list.get(0).equals(answerTypeService) && list.size() >= 2) {
                                                if (list.size() > 2 && list.get(2).isBlank()) {
                                                    list.set(2, answerSize);
                                                } else if (list.size() == 2) {
                                                    list.add(answerSize);
                                                }
                                                System.out.println("System successful add size");
                                                break;
                                            }
                                        }
                                    } else {
                                        System.out.println("Cost must be > 1");
                                    }
                                }
                            } catch (NumberFormatException e) {
                                if (answerTypeService.equals("no") || answerSize.equals("no")) {
                                    System.out.println("No problem");
                                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAllDataOrder))) {
                                        for (List<String> list : addTypeService0) {
                                            for (String item : list) {
                                                int index = list.size() - 1;
                                                if (list.size() == 1 || item == list.get(index)) {
                                                    writer.write(item);
                                                } else {
                                                    writer.write(item + ",");
                                                }
                                            }
                                            writer.newLine();
                                        }
                                        addTypeService0.clear();
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                        addTypeService0.clear();
                                    }
                                    break;
                                } else {
                                    System.out.println("Please enter correct data");
                                }
                            }
                        }
                        break;
                    } else if (answer.equals("no")) {
                        System.out.println("No problem");
                        addTypeService0.clear();
                        break;
                    }
                }
                break;
            } else if (!fileAllDataOrder.exists()) {
                fileAllDataOrder.createNewFile();
                System.out.println("File successful create");
            } else {
                System.out.println("You can not add size to service, because did not exist no one type of service");
                break;
            }
        }
        readAllDataOrder.close();
        allData.menu();

    }
    public void removeSizeService() throws IOException {
        BufferedReader readAllDataOrder = new BufferedReader(new FileReader(fileAllDataOrder));
        List<List<String>> addTypeService0 = new ArrayList<>();
        while (true) {
            if (readAllDataOrder.readLine() == null && fileAllDataOrder.exists()) {
                BufferedReader fileReadAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                BufferedReader fileReadService = new BufferedReader(new FileReader(serviceFile));
                BufferedWriter fileWriterAllData = new BufferedWriter(new FileWriter(fileAllDataOrder, true));
                // if - else
                if (fileReadService.readLine() != null && fileReadAllData.readLine() == null) {
                    BufferedReader readService = new BufferedReader(new FileReader(serviceFile));
                    String lineAddDataFile;
                    while ((lineAddDataFile = readService.readLine()) != null) {
                        fileWriterAllData.write(lineAddDataFile + "\n");
                    }
                    readService.close();
                } else if (fileReadService.readLine() == null && fileReadAllData.readLine() == null) {
                    System.out.println("You must add service");
                    break;
                }
                //add data in list
                BufferedReader addListAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                String line;
                //info from file add to list
                while ((line = addListAllData.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    listAdd.add(line);
                    addTypeService0.add(listAdd);
                }
                //see data
                System.out.println("----------List of service----------");
                int listi = 1;
                for (List<String> list : addTypeService0) {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(listi + ") " + list.get(i));
                        listi++;
                    }
                }
                System.out.println();
                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want remove size to order? Enter YES or NO: ");
                    String answer = in.nextLine().toLowerCase();
                    if (answer.equals("yes")) {
                        while (true) {
                            System.out.println(addTypeService0);
                            System.out.print("Please enter name of service: ");
                            String answerTypeService = in.nextLine();

                            if (answerTypeService.equals("no")) {
                                System.out.println("No problem");
                                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAllDataOrder))) {
                                    for (List<String> list : addTypeService0) {
                                        for (String item : list) {
                                            if (list.size() == 2) {
                                                writer.write(list.get(0) + "," + list.get(1));
                                                break;
                                            } else {
                                                writer.write(item);
                                            }
                                        }
                                        writer.newLine();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                            boolean addSizeList = false;
                            for (List<String> list : addTypeService0) {
                                for (int i = 0; i < list.size(); i++) {
                                    if (list.get(0).equals(answerTypeService) && (list.get(2).isBlank() || list.get(2) == null)) {
                                        System.out.println("You can not remove size\n");
                                        break;
                                    } else if (list.get(0).equals(answerTypeService) && list.size() >= 3 && !list.get(2).isBlank()) {
                                        list.set(2, " ");
                                        System.out.println("System successful remove size");
                                        addSizeList = true;
                                        break;
                                    } else {
                                        addSizeList = false;
                                    }
                                }
                                if (addSizeList) {
                                    break;
                                }
                            }
                        }
                        break;
                    } else if (answer.equals("no")) {
                        System.out.println("No problem");
                        break;
                    }
                }
                addListAllData.close();
                break;
            } else if (readAllDataOrder.readLine() != null && fileAllDataOrder.exists()) {
                BufferedReader readFileOrder = new BufferedReader(new FileReader(fileAllDataOrder));
                String line;
                //add data in list
                while ((line = readFileOrder.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    if (line.contains(",")) {
                        int index = 0;
                        for (int i = 0; i < line.length(); i++) {
                            if (line.charAt(i) == ',') {
                                listAdd.add(line.substring(index, i));
                                index = i + 1;
                            }
                        }
                        listAdd.add(line.substring(index));
                    } else {
                        listAdd.add(line);
                    }
                    addTypeService0.add(listAdd);
                }
                //saw data
                System.out.println("--------List of service--------");
                //[[ww], [sss, 2121], [dddd, 232], [hhh]]
                int listi = 1;
                for (List<String> list : addTypeService0) {
                    for (String lineForList : list) {
                        if (list.size() > 1) {
                            String temporaryStr = String.join(", ", list);
                            System.out.println(listi + ") " + temporaryStr);
                            listi++;
                            break;
                        } else {
                            System.out.println(listi + ") " + lineForList);
                            listi++;
                        }
                    }
                }
                System.out.println();

                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want remove size to order? Enter YES or NO: ");
                    String answer = in.nextLine().toLowerCase();
                    if (answer.equals("yes")) {
                        while (true) {
                            System.out.print("Please enter name of service: ");
                            String answerTypeService = in.nextLine();
                            if (answerTypeService.equals("no")) {
                                System.out.println("No problem");
                                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAllDataOrder))) {
                                    for (List<String> list : addTypeService0) {
                                        for (String item : list) {
                                            int index = list.size() - 1;
                                            if (list.size() == 1 || item == list.get(index)) {
                                                writer.write(item);
                                            } else {
                                                writer.write(item + ",");
                                            }
                                        }
                                        writer.newLine();
                                    }
                                    addTypeService0.clear();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    addTypeService0.clear();
                                }
                                break;
                            } else {
                                for (List<String> list : addTypeService0) {
                                    if (list.get(0).equals(answerTypeService) && list.get(2).isBlank()) {
                                        System.out.println("System can not remove size");
                                        break;
                                    } else if (list.get(0).equals(answerTypeService) && list.size() >= 3 && (!list.get(2).isBlank() || list.get(2) != null)) {
                                        list.set(2, " ");
                                        System.out.println("System successful remove size");
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    } else if (answer.equals("no")) {
                        System.out.println("No problem");
                        addTypeService0.clear();
                        break;
                    }
                }
                break;
            } else if (!fileAllDataOrder.exists()) {
                fileAllDataOrder.createNewFile();
                System.out.println("File successful create");
            } else {
                System.out.println("You can not add size to service, because did not exist no one type of service");
                break;
            }
        }
        readAllDataOrder.close();
        allData.menu();

    }
    public void addPlantService() throws IOException {
        BufferedReader readAllDataOrder = new BufferedReader(new FileReader(fileAllDataOrder));
        BufferedReader readPlantOrder = new BufferedReader(new FileReader(plantFileUpdated));
        List<List<String>> addTypeService0 = new ArrayList<>();
        List<String> addTypePlant0 = new ArrayList<>();

        while (true) {
            if (readAllDataOrder.readLine() == null && readPlantOrder.readLine() != null && fileAllDataOrder.exists() && plantFileUpdated.exists()) {
                BufferedReader fileReadAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                BufferedReader fileReadService = new BufferedReader(new FileReader(serviceFile));
                BufferedWriter fileWriterAllData = new BufferedWriter(new FileWriter(fileAllDataOrder, true));
                // if - else
                if (fileReadService.readLine() != null && fileReadAllData.readLine() == null) {
                    BufferedReader readService = new BufferedReader(new FileReader(serviceFile));
                    String lineAddDataFile;
                    while ((lineAddDataFile = readService.readLine()) != null) {
                        fileWriterAllData.write(lineAddDataFile + "\n");
                    }
                    readService.close();
                } else if (fileReadService.readLine() == null && fileReadAllData.readLine() == null) {
                    System.out.println("You must add service");
                    break;
                }
                //add data in list
                BufferedReader addListAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                BufferedReader addListPlant = new BufferedReader(new FileReader(plantFileUpdated));
                String line;
                String line1;
                //info from file add to list
                while ((line = addListAllData.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    listAdd.add(line);
                    addTypeService0.add(listAdd);
                }
                while ((line1 = addListPlant.readLine()) != null) {
                    addTypePlant0.add(line1);
                }
                //see data
                System.out.println("----------List of service----------");
                int listi = 1;
                for (List<String> list : addTypeService0) {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(listi + ") " + list.get(i));
                        listi++;
                    }
                }
                System.out.println("\n----------List of plant----------");
                int count = 1;
                for (String list1 : addTypePlant0) {
                    System.out.println(count + ") " + list1);
                    count++;
                }
                System.out.println();


                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want add plant to order? Enter YES or NO: ");
                    String answer = in.nextLine().toLowerCase();
                    if (answer.equals("yes")) {
                        while (true) {
                            System.out.println(addTypeService0);
                            System.out.print("Please enter name of service: ");
                            String answerTypeService = in.nextLine();
                            System.out.print("Please enter plant for service: ");
                            String answerPlant = in.nextLine();
                            if (answerTypeService.equals("no") || answerPlant.equals("no")) {
                                System.out.println("No problem");
                                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAllDataOrder))) {
                                    for (List<String> list : addTypeService0) {
                                        for (String item : list) {
                                            if (list.size() == 4) {
                                                writer.write(list.get(0) + "," + list.get(1) + ", " + list.get(2) + ", " + list.get(4));
                                                break;
                                            } else {
                                                writer.write(item);
                                            }
                                        }
                                        writer.newLine();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                            boolean addSizeList = false;
                            for (List<String> list : addTypeService0) {
                                for (int i = 0; i < list.size(); i++) {
                                    if (list.get(0).equals(answerTypeService) && list.size() < 2) {
                                        for (String lineForPlant : addTypePlant0) {
                                            if (lineForPlant.contains(answerPlant)) {
                                                while (list.size() < 3) {
                                                    list.add(" ");
                                                }
                                                list.add(3, answerPlant);
                                                System.out.println("System successful add size");
                                                addSizeList = true;
                                                break;
                                            }
                                        }
                                    } else if (list.get(0).equals(answerTypeService) && list.size() >= 4) {
                                        if (list.get(4).isBlank()) {
                                            for (String line001 : addTypePlant0) {
                                                if (line001.equals(answerPlant)) {
                                                    list.set(4, answerPlant);
                                                    System.out.println("System successful add plant");
                                                    addSizeList = true;
                                                    break;

                                                }
                                            }
                                        } else {
                                            System.out.println("This order have plant");
                                        }
                                    } else {
                                        addSizeList = false;
                                    }
                                }
                                if (addSizeList) {
                                    break;
                                }
                            }
                            if (!addSizeList) {
                                System.out.println("You can not add plant because type of service did not exist\n");
                            }
                        }
                        break;
                    } else if (answer.equals("no")) {
                        System.out.println("No problem");
                        break;
                    }
                }
                addListAllData.close();
                addListPlant.close();
                break;
            } else if (readAllDataOrder.readLine() != null && readPlantOrder.readLine() != null && fileAllDataOrder.exists() && plantFileUpdated.exists()) {
                BufferedReader readFileOrder = new BufferedReader(new FileReader(fileAllDataOrder));
                BufferedReader readFilePlant = new BufferedReader(new FileReader(plantFileUpdated));
                String line;
                //add data in list
                while ((line = readFileOrder.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    if (line.contains(",")) {
                        int index = 0;
                        for (int i = 0; i < line.length(); i++) {
                            if (line.charAt(i) == ',') {
                                listAdd.add(line.substring(index, i));
                                index = i + 1;
                            }
                        }
                        listAdd.add(line.substring(index));
                    } else {
                        listAdd.add(line);
                    }
                    addTypeService0.add(listAdd);
                }
                String line001;
                while ((line001 = readFilePlant.readLine()) != null) {
                    addTypePlant0.add(line001);
                }
                //saw data
                System.out.println("--------List of service--------");
                int listi = 1;
                for (List<String> list : addTypeService0) {
                    for (String lineForList : list) {
                        if (list.size() > 1) {
                            String temporaryStr = String.join(", ", list);
                            System.out.println(listi + ") " + temporaryStr);
                            listi++;
                            break;
                        } else {
                            System.out.println(listi + ") " + lineForList);
                            listi++;
                        }
                    }
                }
                System.out.println("\n--------List of plant--------");
                int count = 1;
                for (String list002 : addTypePlant0) {
                    System.out.println(count + ") " + list002);
                    count++;
                }
                System.out.println();

                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want add plant to order? Enter YES or NO: ");
                    String answer = in.nextLine().toLowerCase();
                    if (answer.equals("yes")) {
                        while (true) {
                            System.out.print("Please enter name of service: ");
                            String answerTypeService = in.nextLine();
                            System.out.print("Please enter plant for service: ");
                            String answerPlant = in.nextLine();
                            if (answerTypeService.equals("no") || answerPlant.equals("no")) {
                                System.out.println("No problem");
                                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAllDataOrder))) {
                                    for (List<String> list : addTypeService0) {
                                        for (String item : list) {
                                            int index = list.size() - 1;
                                            if (list.size() == 1 || item == list.get(index)) {
                                                writer.write(item);
                                            } else {
                                                writer.write(item + ",");
                                            }
                                        }
                                        writer.newLine();
                                    }
                                    addTypeService0.clear();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    addTypeService0.clear();
                                }
                                break;
                            } else {
                                for (List<String> list : addTypeService0) {
                                    if (list.get(0).equals(answerTypeService) && list.size() < 4) {
                                        for (String line003 : addTypePlant0) {
                                            if (line003.equals(answerPlant)) {
                                                while (list.size() < 4) {
                                                    list.add(" ");
                                                }
                                                list.add(3, answerPlant);
                                                System.out.println("System successful add size");
                                                break;
                                            }
                                        }
                                    } else if (list.get(0).equals(answerTypeService) && list.size() >= 4 && !list.get(4).isBlank()) {
                                        System.out.println("This service have plant");
                                        break;
                                    } else if (list.get(0).equals(answerTypeService) && list.size() >= 3) {
                                        if (list.size() > 3 && list.get(4).isBlank()) {
                                            for (String line004 : addTypePlant0) {
                                                if (line004.equals(answerPlant)) {
                                                    list.set(4, answerPlant);
                                                }
                                            }
                                        } else if (list.size() == 3) {
                                            for (String line005 : addTypePlant0) {
                                                if (line005.equals(answerPlant)) {
                                                    list.add(answerPlant);
                                                }
                                            }
                                        }
                                        System.out.println("System successful add size");
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    } else if (answer.equals("no")) {
                        System.out.println("No problem");
                        addTypeService0.clear();
                        break;
                    }
                }
                break;
            } else if (readPlantOrder.readLine() == null && plantFileUpdated.exists()) {
                System.out.println("You can not add plant because it is not exist");
            } else if (!fileAllDataOrder.exists() || !plantFileUpdated.exists()) {
                if (!fileAllDataOrder.exists()) {
                    fileAllDataOrder.createNewFile();
                } else if (!plantFileUpdated.exists()) {
                    plantFileUpdated.createNewFile();
                }
                System.out.println("File successful create");
            } else {
                System.out.println("You can not add size to service, because did not exist no one type of service");
                break;
            }
        }
        readAllDataOrder.close();
        readPlantOrder.close();
        allData.menu();

    }
    public void removePlantService() throws IOException {
        BufferedReader readAllDataOrder = new BufferedReader(new FileReader(fileAllDataOrder));
        List<List<String>> addTypeService0 = new ArrayList<>();
        while (true) {
            if (readAllDataOrder.readLine() == null && fileAllDataOrder.exists()) {
                BufferedReader fileReadAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                BufferedReader fileReadService = new BufferedReader(new FileReader(serviceFile));
                BufferedWriter fileWriterAllData = new BufferedWriter(new FileWriter(fileAllDataOrder, true));
                // if - else
                if (fileReadService.readLine() != null && fileReadAllData.readLine() == null) {
                    BufferedReader readService = new BufferedReader(new FileReader(serviceFile));
                    String lineAddDataFile;
                    while ((lineAddDataFile = readService.readLine()) != null) {
                        fileWriterAllData.write(lineAddDataFile + "\n");
                    }
                    readService.close();
                } else if (fileReadService.readLine() == null && fileReadAllData.readLine() == null) {
                    System.out.println("You must add service");
                    break;
                }
                //add data in list
                BufferedReader addListAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                String line;
                //info from file add to list
                while ((line = addListAllData.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    listAdd.add(line);
                    addTypeService0.add(listAdd);
                }
                //see data
                System.out.println("----------List of service----------");
                int listi = 1;
                for (List<String> list : addTypeService0) {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(listi + ") " + list.get(i));
                        listi++;
                    }
                }
                System.out.println();
                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want remove plant to order? Enter YES or NO: ");
                    String answer = in.nextLine().toLowerCase();
                    if (answer.equals("yes")) {
                        while (true) {
                            System.out.println(addTypeService0);
                            System.out.print("Please enter name of service: ");
                            String answerTypeService = in.nextLine();

                            if (answerTypeService.equals("no")) {
                                System.out.println("No problem");
                                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAllDataOrder))) {
                                    for (List<String> list : addTypeService0) {
                                        for (String item : list) {
                                            if (list.size() == 4) {
                                                for (int i = 0; i < list.size() - 1; i++) {
                                                    writer.write(list.get(i) + ",");
                                                }
                                                writer.write(list.get(list.size() - 1));
                                                break;
                                            } else {
                                                writer.write(item);
                                            }
                                        }
                                        writer.newLine();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                            boolean addSizeList = false;
                            for (List<String> list : addTypeService0) {
                                for (int i = 0; i < list.size(); i++) {

                                    if (list.get(0).equals(answerTypeService) && (list.size() < 4 || list.get(3).isBlank())) {
                                        System.out.println("You can not remove plant\n");
                                        break;
                                    } else if (list.get(0).equals(answerTypeService) && list.size() >= 4 && !list.get(3).isBlank()) {
                                        list.set(4, " ");
                                        System.out.println("System successful remove plant");
                                        addSizeList = true;
                                        break;
                                    } else {
                                        addSizeList = false;
                                    }
                                }
                                if (addSizeList) {
                                    break;
                                }
                            }
                        }
                        break;
                    } else if (answer.equals("no")) {
                        System.out.println("No problem");
                        break;
                    }
                }
                addListAllData.close();
                break;
            } else if (readAllDataOrder.readLine() != null && fileAllDataOrder.exists()) {
                BufferedReader readFileOrder = new BufferedReader(new FileReader(fileAllDataOrder));
                String line;
                //add data in list
                while ((line = readFileOrder.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    if (line.contains(",")) {
                        int index = 0;
                        for (int i = 0; i < line.length(); i++) {
                            if (line.charAt(i) == ',') {
                                listAdd.add(line.substring(index, i));
                                index = i + 1;
                            }
                        }
                        listAdd.add(line.substring(index));
                    } else {
                        listAdd.add(line);
                    }
                    addTypeService0.add(listAdd);
                }
                //saw data
                System.out.println("--------List of service--------");
                int listi = 1;
                for (List<String> list : addTypeService0) {
                    for (String lineForList : list) {
                        if (list.size() > 1) {
                            String temporaryStr = String.join(", ", list);
                            System.out.println(listi + ") " + temporaryStr);
                            listi++;
                            break;
                        } else {
                            System.out.println(listi + ") " + lineForList);
                            listi++;
                        }
                    }
                }
                System.out.println();

                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want remove plant to order? Enter YES or NO: ");
                    String answer = in.nextLine().toLowerCase();
                    if (answer.equals("yes")) {
                        while (true) {
                            System.out.print("Please enter name of service: ");
                            String answerTypeService = in.nextLine();
                            if (answerTypeService.equals("no")) {
                                System.out.println("No problem");
                                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAllDataOrder))) {
                                    for (List<String> list : addTypeService0) {
                                        for (String item : list) {
                                            int index = list.size() - 1;
                                            if (list.size() == 1 || item == list.get(index)) {
                                                writer.write(item);
                                            } else {
                                                writer.write(item + ",");
                                            }
                                        }
                                        writer.newLine();
                                    }
                                    addTypeService0.clear();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    addTypeService0.clear();
                                }
                                break;
                            } else {
                                for (List<String> list : addTypeService0) {
                                    if (list.get(0).equals(answerTypeService) && list.size() >= 4 && list.get(3).isBlank()) {
                                        System.out.println("System can not remove plant");
                                        break;
                                    } else if (list.get(0).equals(answerTypeService) && list.size() >= 4 && !list.get(3).isBlank()) {
                                        list.set(3, " ");
                                        System.out.println("System successful remove plant");
                                        break;
                                    } else if (list.get(0).equals(answerTypeService) && list.size() < 4) {
                                        System.out.println("System can not delete plant");
                                    }
                                }
                            }
                        }
                        break;
                    } else if (answer.equals("no")) {
                        System.out.println("No problem");
                        addTypeService0.clear();
                        break;
                    }
                }
                break;
            } else if (!fileAllDataOrder.exists()) {
                fileAllDataOrder.createNewFile();
                System.out.println("File successful create");
            } else {
                System.out.println("You can not add plant to service, because did not exist no one type of service");
                break;
            }
        }
        readAllDataOrder.close();
        allData.menu();

    }
    public void addTimeService() throws IOException {
        BufferedReader readAllDataOrder = new BufferedReader(new FileReader(fileAllDataOrder));
        List<List<String>> addTypeService0 = new ArrayList<>();
        while (true) {
            if (readAllDataOrder.readLine() == null && fileAllDataOrder.exists()) {
                BufferedReader fileReadAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                BufferedReader fileReadService = new BufferedReader(new FileReader(serviceFile));
                BufferedWriter fileWriterAllData = new BufferedWriter(new FileWriter(fileAllDataOrder, true));
                // if - else
                if (fileReadService.readLine() != null && fileReadAllData.readLine() == null) {
                    BufferedReader readService = new BufferedReader(new FileReader(serviceFile));
                    String lineAddDataFile;
                    while ((lineAddDataFile = readService.readLine()) != null) {
                        fileWriterAllData.write(lineAddDataFile + "\n");
                    }
                    readService.close();
                } else if (fileReadService.readLine() == null && fileReadAllData.readLine() == null) {
                    System.out.println("You must add service");
                    break;
                }
                //add data in list
                BufferedReader addListAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                String line;
                //info from file add to list
                while ((line = addListAllData.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    listAdd.add(line);
                    addTypeService0.add(listAdd);
                }
                //see data
                System.out.println("----------List of service----------");
                int listi = 1;
                for (List<String> list : addTypeService0) {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(listi + ") " + list.get(i));
                        listi++;
                    }
                }
                System.out.println();
                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want add time to order? Enter YES or NO: ");
                    String answer = in.nextLine().toLowerCase();
                    if (answer.equals("yes")) {
                        while (true) {
                            System.out.println(addTypeService0);
                            System.out.print("Please enter name of service: ");
                            String answerTypeService = in.nextLine();
                            System.out.print("Please enter time start, hour: ");
                            String answerHourStart = in.nextLine();
                            System.out.print("Please enter time start, minute: ");
                            String answerMinuteStart = in.nextLine();
                            System.out.print("Please enter time end, hour: ");
                            String answerHourEnd = in.nextLine();
                            System.out.print("Please enter time end, minute: ");
                            String answerMinuteEnd = in.nextLine();
                            try {
                                int hourStart = Integer.parseInt(answerHourStart);
                                int minuteStart = Integer.parseInt(answerMinuteStart);
                                int hourEnd = Integer.parseInt(answerHourEnd);
                                int minuteEnd = Integer.parseInt(answerMinuteEnd);

                                if (answerTypeService.equals("no") || answerHourStart.equals("no") || answerMinuteStart.equals("no") || answerHourEnd.equals("no") || answerMinuteEnd.equals("no")) {
                                    System.out.println("No problem");
                                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAllDataOrder))) {
                                        for (List<String> list : addTypeService0) {
                                            for (String item : list) {
                                                if (list.size() == 5) {
                                                    writer.write(list.get(0) + "," + list.get(1) + "," + list.get(2) + "," + list.get(3) + "," + list.get(4));
                                                    break;
                                                } else {
                                                    writer.write(item);
                                                }
                                            }
                                            writer.newLine();
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                } else if (hourStart >= 1 && minuteStart >= 1 && hourEnd >= 1 && minuteEnd >= 1 && answerHourStart.length() == 2 &&
                                        answerMinuteStart.length() == 2 && answerHourEnd.length() == 2 && answerMinuteEnd.length() == 2) {
                                    boolean addSizeList = false;
                                    for (List<String> list : addTypeService0) {
                                        for (int i = 0; i < list.size(); i++) {
                                            if (list.get(0).equals(answerTypeService) && list.size() < 2) {
                                                while (list.size() < 4) {
                                                    list.add(" ");
                                                }
                                                list.add(4, answerHourStart + ":" + answerMinuteStart + "-" + answerHourEnd + ":" + answerMinuteEnd);
                                                System.out.println("System successful add time");
                                                addSizeList = true;
                                                break;
                                            } else if (list.get(0).equals(answerTypeService) && list.size() >= 4) {
                                                if (list.size() > 4 && list.get(4).isBlank()) {
                                                    list.set(4, answerHourStart + ":" + answerMinuteStart + "-" + answerHourEnd + ":" + answerMinuteEnd);
                                                } else if (list.size() == 4) {
                                                    list.add(4, answerHourStart + ":" + answerMinuteStart + "-" + answerHourEnd + ":" + answerMinuteEnd);
                                                }
                                                System.out.println("System successful add time");
                                                addSizeList = true;
                                                break;
                                            } else {
                                                addSizeList = false;
                                            }
                                        }
                                        if (addSizeList) {
                                            break;
                                        }
                                    }
                                    if (!addSizeList) {
                                        System.out.println("You can not add time because type of service did not exist\n");
                                    }
                                } else if (hourStart < 1 || minuteStart < 1 || hourEnd < 1 || minuteEnd < 1 || answerHourStart.length() < 2 ||
                                        answerMinuteStart.length() < 2 || answerHourEnd.length() == 2 || answerMinuteEnd.length() == 2) {
                                    System.out.println("System must be > 0");
                                }
                            } catch (NumberFormatException e) {
                                if (answerTypeService.equals("no") || answerHourStart.equals("no") || answerMinuteStart.equals("no") || answerHourEnd.equals("no") || answerMinuteEnd.equals("no")) {
                                    System.out.println("No problem");
                                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAllDataOrder))) {
                                        for (List<String> list : addTypeService0) {
                                            for (String item : list) {
                                                if (list.size() == 5) {
                                                    writer.write(list.get(0) + "," + list.get(1) + "," + list.get(2) + ", " + list.get(3) + ", " + list.get(4));
                                                    break;
                                                } else {
                                                    writer.write(item);
                                                }
                                            }
                                            writer.newLine();
                                        }
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                    break;
                                } else {
                                    System.out.println("Please enter correct data");
                                }
                            }
                        }
                        break;
                    } else if (answer.equals("no")) {
                        System.out.println("No problem");
                        break;
                    }
                }
                addListAllData.close();
                break;
            } else if (readAllDataOrder.readLine() != null && fileAllDataOrder.exists()) {
                BufferedReader readFileOrder = new BufferedReader(new FileReader(fileAllDataOrder));
                String line;
                //add data in list
                while ((line = readFileOrder.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    if (line.contains(",")) {
                        int index = 0;
                        for (int i = 0; i < line.length(); i++) {
                            if (line.charAt(i) == ',') {
                                listAdd.add(line.substring(index, i));
                                index = i + 1;
                            }
                        }
                        listAdd.add(line.substring(index));
                    } else {
                        listAdd.add(line);
                    }
                    addTypeService0.add(listAdd);
                }
                //saw data
                System.out.println("--------List of service--------");
                int listi = 1;
                for (List<String> list : addTypeService0) {
                    for (String lineForList : list) {
                        if (list.size() > 1) {
                            String temporaryStr = String.join(", ", list);
                            System.out.println(listi + ") " + temporaryStr);
                            listi++;
                            break;
                        } else {
                            System.out.println(listi + ") " + lineForList);
                            listi++;
                        }
                    }
                }
                System.out.println();

                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want add time to order? Enter YES or NO: ");
                    String answer = in.nextLine().toLowerCase();
                    if (answer.equals("yes")) {
                        while (true) {
                            System.out.print("Please enter name of service: ");
                            String answerTypeService = in.nextLine();
                            System.out.print("Please enter time start, hour: ");
                            String answerHourStart = in.nextLine();
                            System.out.print("Please enter time start, minute: ");
                            String answerMinuteStart = in.nextLine();
                            System.out.print("Please enter time end, hour: ");
                            String answerHourEnd = in.nextLine();
                            System.out.print("Please enter time end, minute: ");
                            String answerMinuteEnd = in.nextLine();


                            try {
                                int hourStart = Integer.parseInt(answerHourStart);
                                int minuteStart = Integer.parseInt(answerMinuteStart);
                                int hourEnd = Integer.parseInt(answerHourEnd);
                                int minuteEnd = Integer.parseInt(answerMinuteEnd);

                                if (answerTypeService.equals("no") || answerHourStart.equals("no") || answerMinuteStart.equals("no") || answerHourEnd.equals("no") || answerMinuteEnd.equals("no")) {
                                    System.out.println("No problem");
                                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAllDataOrder))) {
                                        for (List<String> list : addTypeService0) {
                                            for (String item : list) {
                                                int index = list.size() - 1;
                                                if (list.size() == 1 || item == list.get(index)) {
                                                    writer.write(item);
                                                } else {
                                                    writer.write(item + ",");
                                                }
                                            }
                                            writer.newLine();
                                        }
                                        addTypeService0.clear();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        addTypeService0.clear();
                                    }
                                    break;
                                } else {
                                    if (hourStart >= 1 && minuteStart >= 1 && hourEnd >= 1 && minuteEnd >= 1 && answerHourStart.length() == 2 && answerMinuteStart.length() == 2 && answerHourEnd.length() == 2 && answerMinuteEnd.length() == 2) {
                                        for (List<String> list : addTypeService0) {
                                            if (list.get(0).equals(answerTypeService) && list.size() < 5) {
                                                while (list.size() < 5) {
                                                    list.add(" ");
                                                }
                                                list.add(4, answerHourStart + ":" + answerMinuteStart + "-" + answerHourEnd + ":" + answerMinuteEnd);
                                                System.out.println("System successful add time");
                                                break;
                                            } else if (list.get(0).equals(answerTypeService) && list.size() >= 5 && !list.get(4).isBlank()) {
                                                System.out.println("This service have size");
                                                break;
                                            } else if (list.get(0).equals(answerTypeService) && list.size() >= 4) {
                                                if (list.size() > 4 && list.get(4).isBlank()) {
                                                    list.set(4, answerHourStart + ":" + answerMinuteStart + "-" + answerHourEnd + ":" + answerMinuteEnd);
                                                } else if (list.size() == 4) {
                                                    list.add(answerHourStart + ":" + answerMinuteStart + "-" + answerHourEnd + ":" + answerMinuteEnd);
                                                }
                                                System.out.println("System successful add time");
                                                break;
                                            }
                                        }
                                    } else {
                                        System.out.println("Cost must be > 1");
                                    }
                                }
                            } catch (NumberFormatException e) {
                                if (answerTypeService.equals("no") || answerHourStart.equals("no") || answerMinuteStart.equals("no") || answerHourEnd.equals("no") || answerMinuteEnd.equals("no")) {
                                    System.out.println("No problem");
                                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAllDataOrder))) {
                                        for (List<String> list : addTypeService0) {
                                            for (String item : list) {
                                                int index = list.size() - 1;
                                                if (list.size() == 1 || item == list.get(index)) {
                                                    writer.write(item);
                                                } else {
                                                    writer.write(item + ",");
                                                }
                                            }
                                            writer.newLine();
                                        }
                                        addTypeService0.clear();
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                        addTypeService0.clear();
                                    }
                                    break;
                                } else {
                                    System.out.println("Please enter correct data");
                                }
                            }
                        }
                        break;
                    } else if (answer.equals("no")) {
                        System.out.println("No problem");
                        addTypeService0.clear();
                        break;
                    }
                }
                break;
            } else if (!fileAllDataOrder.exists()) {
                fileAllDataOrder.createNewFile();
                System.out.println("File successful create");
            } else {
                System.out.println("You can not add size to service, because did not exist no one type of service");
                break;
            }
        }
        readAllDataOrder.close();
        allData.menu();

    }
    public void removeTimeService() throws IOException {
        BufferedReader readAllDataOrder = new BufferedReader(new FileReader(fileAllDataOrder));
        List<List<String>> addTypeService0 = new ArrayList<>();
        while (true) {
            if (readAllDataOrder.readLine() == null && fileAllDataOrder.exists()) {
                BufferedReader fileReadAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                BufferedReader fileReadService = new BufferedReader(new FileReader(serviceFile));
                BufferedWriter fileWriterAllData = new BufferedWriter(new FileWriter(fileAllDataOrder, true));
                // if - else
                if (fileReadService.readLine() != null && fileReadAllData.readLine() == null) {
                    BufferedReader readService = new BufferedReader(new FileReader(serviceFile));
                    String lineAddDataFile;
                    while ((lineAddDataFile = readService.readLine()) != null) {
                        fileWriterAllData.write(lineAddDataFile + "\n");
                    }
                    readService.close();
                } else if (fileReadService.readLine() == null && fileReadAllData.readLine() == null) {
                    System.out.println("You must add service");
                    break;
                }
                //add data in list
                BufferedReader addListAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                String line;
                //info from file add to list
                while ((line = addListAllData.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    listAdd.add(line);
                    addTypeService0.add(listAdd);
                }
                //see data
                System.out.println("----------List of service----------");
                int listi = 1;
                for (List<String> list : addTypeService0) {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(listi + ") " + list.get(i));
                        listi++;
                    }
                }
                System.out.println();
                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want remove time to order? Enter YES or NO: ");
                    String answer = in.nextLine().toLowerCase();
                    if (answer.equals("yes")) {
                        while (true) {
                            System.out.println(addTypeService0);
                            System.out.print("Please enter name of service: ");
                            String answerTypeService = in.nextLine();

                            if (answerTypeService.equals("no")) {
                                System.out.println("No problem");
                                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAllDataOrder))) {
                                    for (List<String> list : addTypeService0) {
                                        for (String item : list) {
                                            if (list.size() == 5) {
                                                for (int i = 0; i < list.size() - 1; i++) {
                                                    writer.write(list.get(i) + ",");
                                                }
                                                writer.write(list.get(list.size() - 1));
                                                break;
                                            } else {
                                                writer.write(item);
                                            }
                                        }
                                        writer.newLine();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                            boolean addSizeList = false;
                            for (List<String> list : addTypeService0) {
                                for (int i = 0; i < list.size(); i++) {
                                    if (list.get(0).equals(answerTypeService) && (list.size() < 5 || list.get(4).isBlank())) {
                                        System.out.println("You can not remove time\n");
                                        break;
                                    } else if (list.get(0).equals(answerTypeService) && list.size() >= 5 && !list.get(4).isBlank()) {
                                        list.set(4, " ");
                                        System.out.println("System successful remove time");
                                        addSizeList = true;
                                        break;
                                    } else {
                                        addSizeList = false;
                                    }
                                }
                                if (addSizeList) {
                                    break;
                                }
                            }
                        }
                        break;
                    } else if (answer.equals("no")) {
                        System.out.println("No problem");
                        break;
                    }
                }
                addListAllData.close();
                break;
            } else if (readAllDataOrder.readLine() != null && fileAllDataOrder.exists()) {
                BufferedReader readFileOrder = new BufferedReader(new FileReader(fileAllDataOrder));
                String line;
                //add data in list
                while ((line = readFileOrder.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    if (line.contains(",")) {
                        int index = 0;
                        for (int i = 0; i < line.length(); i++) {
                            if (line.charAt(i) == ',') {
                                listAdd.add(line.substring(index, i));
                                index = i + 1;
                            }
                        }
                        listAdd.add(line.substring(index));
                    } else {
                        listAdd.add(line);
                    }
                    addTypeService0.add(listAdd);
                }
                //saw data
                System.out.println("--------List of service--------");
                int listi = 1;
                for (List<String> list : addTypeService0) {
                    for (String lineForList : list) {
                        if (list.size() > 1) {
                            String temporaryStr = String.join(", ", list);
                            System.out.println(listi + ") " + temporaryStr);
                            listi++;
                            break;
                        } else {
                            System.out.println(listi + ") " + lineForList);
                            listi++;
                        }
                    }
                }
                System.out.println();

                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want remove time to order? Enter YES or NO: ");
                    String answer = in.nextLine().toLowerCase();
                    if (answer.equals("yes")) {
                        while (true) {
                            System.out.print("Please enter name of service: ");
                            String answerTypeService = in.nextLine();
                            if (answerTypeService.equals("no")) {
                                System.out.println("No problem");
                                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileAllDataOrder))) {
                                    for (List<String> list : addTypeService0) {
                                        for (String item : list) {
                                            int index = list.size() - 1;
                                            if (list.size() == 1 || item == list.get(index)) {
                                                writer.write(item);
                                            } else {
                                                writer.write(item + ",");
                                            }
                                        }
                                        writer.newLine();
                                    }
                                    addTypeService0.clear();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    addTypeService0.clear();
                                }
                                break;
                            } else {
                                for (List<String> list : addTypeService0) {
                                    if (list.get(0).equals(answerTypeService) && list.size() >= 5 && list.get(4).isBlank()) {
                                        System.out.println("System can not remove time");
                                        break;
                                    } else if (list.get(0).equals(answerTypeService) && list.size() >= 4 && !list.get(4).isBlank()) {
                                        list.set(4, " ");
                                        System.out.println("System successful remove time");
                                        break;
                                    } else if (list.get(0).equals(answerTypeService) && list.size() < 5) {
                                        System.out.println("System can not delete time");
                                    }
                                }
                            }
                        }
                        break;
                    } else if (answer.equals("no")) {
                        System.out.println("No problem");
                        addTypeService0.clear();
                        break;
                    }
                }
                break;
            } else if (!fileAllDataOrder.exists()) {
                fileAllDataOrder.createNewFile();
                System.out.println("File successful create");
            } else {
                System.out.println("You can not remove time to service, because did not exist no one type of service");
                break;
            }
        }
        readAllDataOrder.close();
        allData.menu();

    }
    public void viewListServiceCost() throws IOException {
        BufferedReader readAllDataOrder = new BufferedReader(new FileReader(fileAllDataOrder));
        List<List<String>> addTypeService0 = new ArrayList<>();
        while (true) {
            if (readAllDataOrder.readLine() == null && fileAllDataOrder.exists()) {
                BufferedReader fileReadAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                BufferedReader fileReadService = new BufferedReader(new FileReader(serviceFile));
                BufferedWriter fileWriterAllData = new BufferedWriter(new FileWriter(fileAllDataOrder, true));
                // if - else
                if (fileReadService.readLine() != null && fileReadAllData.readLine() == null) {
                    BufferedReader readService = new BufferedReader(new FileReader(serviceFile));
                    String lineAddDataFile;
                    while ((lineAddDataFile = readService.readLine()) != null) {
                        fileWriterAllData.write(lineAddDataFile + "\n");
                    }
                    readService.close();
                } else if (fileReadService.readLine() == null && fileReadAllData.readLine() == null) {
                    System.out.println("You must add service");
                    break;
                }
                //add data in list
                BufferedReader addListAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                String line;
                //info from file add to list
                while ((line = addListAllData.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    listAdd.add(line);
                    addTypeService0.add(listAdd);
                }
                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want see order? Enter YES or NO: ");
                    String answerTypeService = in.nextLine().toLowerCase();
                    if (answerTypeService.equals("yes")) {
                        while (true) {
                            if (answerTypeService.equals("no")) {
                                System.out.println("No problem");
                                break;
                            } else if (answerTypeService.equals("yes")) {
                                System.out.println("\n----------List of service----------");
                                int count = 1;
                                for (List<String> list : addTypeService0) {
                                    if (list.size() >= 2) {
                                        if (!list.get(0).isBlank() || !list.get(1).isBlank()) {
                                            System.out.println(count + ") type: " + list.get(0) + " cost: " + list.get(1));
                                            count++;
                                        } else {
                                            System.out.println(count + ") type: " + list.get(0) + " cost: 0");
                                        }
                                    } else {
                                        System.out.println(count + ") type: " + list.get(0) + " cost: 0");
                                        count++;
                                    }
                                }
                                System.out.println();
                                break;
                            }
                        }
                        break;
                    } else {
                        System.out.println("No problem");
                        break;
                    }
                }
                addListAllData.close();
                break;
            } else if (readAllDataOrder.readLine() != null && fileAllDataOrder.exists()) {
                BufferedReader readFileOrder = new BufferedReader(new FileReader(fileAllDataOrder));
                String line;
                //add data in list
                while ((line = readFileOrder.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    if (line.contains(",")) {
                        int index = 0;
                        for (int i = 0; i < line.length(); i++) {
                            if (line.charAt(i) == ',') {
                                listAdd.add(line.substring(index, i));
                                index = i + 1;
                            }
                        }
                        listAdd.add(line.substring(index));
                    } else {
                        listAdd.add(line);
                    }
                    addTypeService0.add(listAdd);
                }
                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want see order? Enter YES or NO: ");
                    String answer = in.nextLine().toLowerCase();
                    if (answer.equals("yes")) {
                        while (true) {
                            if (answer.equals("no")) {
                                System.out.println("No problem");
                                break;
                            } else {
                                System.out.println("\n--------List of service--------");
                                int count = 1;
                                for (List<String> list : addTypeService0) {
                                    if (list.size() >= 2) {
                                        if (!list.get(1).isBlank()) {
                                            System.out.println(count + ") type: " + list.get(0) + " cost: " + list.get(1));
                                            count++;
                                        } else if (list.get(1).isBlank()) {
                                            System.out.println(count + ") type: " + list.get(0) + " cost: 0");
                                            count++;
                                        }
                                    } else {
                                        System.out.println(count + ") type: " + list.get(0) + " cost: 0");
                                        count++;
                                    }
                                }
                                System.out.println();
                                break;
                            }
                        }
                        break;
                    } else if (answer.equals("no")) {
                        System.out.println("No problem");
                        addTypeService0.clear();
                        break;
                    }
                }
                break;
            } else if (!fileAllDataOrder.exists()) {
                fileAllDataOrder.createNewFile();
                System.out.println("File successful create");
            } else {
                System.out.println("You can not see list order to service, because did not exist no one type of service");
                break;
            }
        }
        readAllDataOrder.close();
        allData.menu();

    }
    public void averageOrderCost() throws IOException {
        BufferedReader readAllDataOrder = new BufferedReader(new FileReader(fileAllDataOrder));
        List<List<String>> addTypeService0 = new ArrayList<>();
        while (true) {
            if (readAllDataOrder.readLine() == null && fileAllDataOrder.exists()) {
                BufferedReader fileReadAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                BufferedReader fileReadService = new BufferedReader(new FileReader(serviceFile));
                BufferedWriter fileWriterAllData = new BufferedWriter(new FileWriter(fileAllDataOrder, true));
                // if - else
                if (fileReadService.readLine() != null && fileReadAllData.readLine() == null) {
                    BufferedReader readService = new BufferedReader(new FileReader(serviceFile));
                    String lineAddDataFile;
                    while ((lineAddDataFile = readService.readLine()) != null) {
                        fileWriterAllData.write(lineAddDataFile + "\n");
                    }
                    readService.close();
                } else if (fileReadService.readLine() == null && fileReadAllData.readLine() == null) {
                    System.out.println("You must add service");
                    break;
                }
                //add data in list
                BufferedReader addListAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                String line;
                //info from file add to list
                while ((line = addListAllData.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    listAdd.add(line);
                    addTypeService0.add(listAdd);
                }
                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want see average order value? Enter YES or NO: ");
                    String answerTypeService = in.nextLine().toLowerCase();
                    if (answerTypeService.equals("yes")) {
                        while (true) {
                            if (answerTypeService.equals("no")) {
                                System.out.println("No problem");
                                break;
                            } else if (answerTypeService.equals("yes")) {
                                System.out.println("\n----------List of service----------");
                                int count = 1;
                                int cost = 0;
                                for (List<String> list : addTypeService0) {
                                    if (list.size() >= 2) {
                                        if (!list.get(0).isBlank() || !list.get(1).isBlank()) {
                                            System.out.println(count + ") type: " + list.get(0) + " cost: " + list.get(1));
                                            count++;
                                            cost += Integer.parseInt(list.get(1));
                                        } else {
                                            System.out.println(count + ") type: " + list.get(0) + " cost: 0");
                                            cost += 0;
                                        }
                                    } else {
                                        System.out.println(count + ") type: " + list.get(0) + " cost: 0");
                                        count++;
                                        cost += 0;
                                    }
                                }
                                System.out.println(cost);
                                System.out.println(count);
                                int answerCost = cost / count;
                                System.out.printf("\nAverage order value: all cost / quantity order = %d / %d = %d", cost, count, answerCost);
                                System.out.println();
                                break;
                            }
                        }
                        break;
                    } else {
                        System.out.println("No problem");
                        break;
                    }
                }
                addListAllData.close();
                break;
            } else if (readAllDataOrder.readLine() != null && fileAllDataOrder.exists()) {
                BufferedReader readFileOrder = new BufferedReader(new FileReader(fileAllDataOrder));
                String line;
                //add data in list
                while ((line = readFileOrder.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    if (line.contains(",")) {
                        int index = 0;
                        for (int i = 0; i < line.length(); i++) {
                            if (line.charAt(i) == ',') {
                                listAdd.add(line.substring(index, i));
                                index = i + 1;
                            }
                        }
                        listAdd.add(line.substring(index));
                    } else {
                        listAdd.add(line);
                    }
                    addTypeService0.add(listAdd);
                }
                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want see average order value? Enter YES or NO: ");
                    String answerTypeService = in.nextLine().toLowerCase();
                    if (answerTypeService.equals("yes")) {
                        while (true) {
                            if (answerTypeService.equals("no")) {
                                System.out.println("No problem");
                                break;
                            } else if (answerTypeService.equals("yes")) {
                                System.out.println("\n----------List of service----------");
                                int count = 1;
                                int quantity = 0;
                                int cost = 0;
                                for (List<String> list : addTypeService0) {
                                    if (list.size() >= 2) {
                                        if (!list.get(1).isBlank()) {
                                            System.out.println(count + ") type: " + list.get(0) + " cost: " + list.get(1));
                                            count++;
                                            quantity++;
                                            cost += Integer.parseInt(list.get(1));
                                        } else {
                                            System.out.println(count + ") type: " + list.get(0) + " cost: 0");
                                            cost += 0;
                                            count++;
                                            quantity++;
                                        }
                                    } else {
                                        System.out.println(count + ") type: " + list.get(0) + " cost: 0");
                                        count++;
                                        quantity++;
                                        cost += 0;
                                    }
                                }
                                int answerCost = cost / quantity;
                                System.out.printf("\nAverage order value: all cost / quantity order = %d / %d = %d", cost, quantity, answerCost);
                                System.out.println();
                                break;
                            }
                        }
                        break;
                    } else if (answerTypeService.equals("no")) {
                        System.out.println("No problem");
                        addTypeService0.clear();
                        break;
                    }
                }
                break;
            } else if (!fileAllDataOrder.exists()) {
                fileAllDataOrder.createNewFile();
                System.out.println("File successful create");
            } else {
                System.out.println("You can not see average cost, because did not exist no one type of service");
                break;
            }
        }
        readAllDataOrder.close();
        allData.menu();

    }
    public void largeCost() throws IOException {
        BufferedReader readAllDataOrder = new BufferedReader(new FileReader(fileAllDataOrder));
        List<List<String>> addTypeService0 = new ArrayList<>();
        while (true) {
            if (readAllDataOrder.readLine() == null && fileAllDataOrder.exists()) {
                BufferedReader fileReadAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                BufferedReader fileReadService = new BufferedReader(new FileReader(serviceFile));
                BufferedWriter fileWriterAllData = new BufferedWriter(new FileWriter(fileAllDataOrder, true));
                // if - else
                if (fileReadService.readLine() != null && fileReadAllData.readLine() == null) {
                    BufferedReader readService = new BufferedReader(new FileReader(serviceFile));
                    String lineAddDataFile;
                    while ((lineAddDataFile = readService.readLine()) != null) {
                        fileWriterAllData.write(lineAddDataFile + "\n");
                    }
                    readService.close();
                } else if (fileReadService.readLine() == null && fileReadAllData.readLine() == null) {
                    System.out.println("You must add service");
                    break;
                }
                //add data in list
                BufferedReader addListAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                String line;
                //info from file add to list
                while ((line = addListAllData.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    listAdd.add(line);
                    addTypeService0.add(listAdd);
                }
                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want see order? Enter YES or NO: ");
                    String answerTypeService = in.nextLine().toLowerCase();
                    if (answerTypeService.equals("yes")) {
                        while (true) {
                            if (answerTypeService.equals("no")) {
                                System.out.println("No problem");
                                break;
                            } else if (answerTypeService.equals("yes")) {
                                System.out.println("\n----------List of service----------");
                                int count = 1;
                                int number = 0;
                                List<String> saveList = new ArrayList<>();
                                for (List<String> list : addTypeService0) {
                                    if (list.size() >= 2) {
                                        if (!list.get(1).isBlank()) {
                                            System.out.println(count + ") type: " + list.get(0) + " cost: " + list.get(1));
                                            count++;
                                            if (Integer.parseInt(list.get(1)) > number) {
                                                number = Integer.parseInt(list.get(1));
                                                saveList = list;
                                            }
                                        } else {
                                            System.out.println(count + ") type: " + list.get(0) + " cost: 0");
                                        }
                                    } else {
                                        System.out.println(count + ") type: " + list.get(0) + " cost: 0");
                                        count++;
                                    }
                                }
                                for (int i = 0; i < saveList.size() - 1; i++) {
                                    System.out.print(saveList.get(i) + ", ");
                                }
                                System.out.print(saveList.get(saveList.size() - 1));
                                System.out.printf("\nLarge cost: %d", number);
                                break;
                            }
                        }
                        break;
                    } else {
                        System.out.println("No problem");
                        break;
                    }
                }
                addListAllData.close();
                break;
            } else if (readAllDataOrder.readLine() != null && fileAllDataOrder.exists()) {
                BufferedReader readFileOrder = new BufferedReader(new FileReader(fileAllDataOrder));
                String line;
                //add data in list
                while ((line = readFileOrder.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    if (line.contains(",")) {
                        int index = 0;
                        for (int i = 0; i < line.length(); i++) {
                            if (line.charAt(i) == ',') {
                                listAdd.add(line.substring(index, i));
                                index = i + 1;
                            }
                        }
                        listAdd.add(line.substring(index));
                    } else {
                        listAdd.add(line);
                    }
                    addTypeService0.add(listAdd);
                }
                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want see large cost? Enter YES or NO: ");
                    String answerTypeService = in.nextLine().toLowerCase();
                    if (answerTypeService.equals("yes")) {
                        while (true) {
                            if (answerTypeService.equals("no")) {
                                System.out.println("No problem");
                                break;
                            } else if (answerTypeService.equals("yes")) {
                                System.out.println("\n----------List of service----------");
                                int count = 1;
                                int number = 0;
                                List<String> saveList = new ArrayList<>();
                                for (List<String> list : addTypeService0) {
                                    if (list.size() >= 2) {
                                        if (!list.get(1).isBlank()) {
                                            System.out.println(count + ") type: " + list.get(0) + " cost: " + list.get(1));
                                            count++;
                                            if (Integer.parseInt(list.get(1)) > number) {
                                                number = Integer.parseInt(list.get(1));
                                                saveList = list;
                                            }
                                        } else {
                                            System.out.println(count + ") type: " + list.get(0) + " cost: 0");
                                        }
                                    } else {
                                        System.out.println(count + ") type: " + list.get(0) + " cost: 0");
                                        count++;
                                    }
                                }
                                System.out.println();
                                for (int i = 0; i < saveList.size() - 1; i++) {
                                    System.out.print(saveList.get(i) + ", ");
                                }
                                System.out.print(saveList.get(saveList.size() - 1));
                                System.out.printf("\nLarge cost: %d\n\n", number);
                                break;
                            }
                        }
                        break;
                    } else if (answerTypeService.equals("no")) {
                        System.out.println("No problem");
                        addTypeService0.clear();
                        break;
                    }
                }
                break;
            } else if (!fileAllDataOrder.exists()) {
                fileAllDataOrder.createNewFile();
                System.out.println("File successful create");
            } else {
                System.out.println("You can not see large cost to service, because did not exist no one type of service");
                break;
            }
        }
        readAllDataOrder.close();
        allData.menu();

    }
    public void averageOrderTime() throws IOException {
        BufferedReader readAllDataOrder = new BufferedReader(new FileReader(fileAllDataOrder));
        List<List<String>> addTypeService0 = new ArrayList<>();
        while (true) {
            if (readAllDataOrder.readLine() == null && fileAllDataOrder.exists()) {
                BufferedReader fileReadAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                BufferedReader fileReadService = new BufferedReader(new FileReader(serviceFile));
                BufferedWriter fileWriterAllData = new BufferedWriter(new FileWriter(fileAllDataOrder, true));
                // if - else
                if (fileReadService.readLine() != null && fileReadAllData.readLine() == null) {
                    BufferedReader readService = new BufferedReader(new FileReader(serviceFile));
                    String lineAddDataFile;
                    while ((lineAddDataFile = readService.readLine()) != null) {
                        fileWriterAllData.write(lineAddDataFile + "\n");
                    }
                    readService.close();
                } else if (fileReadService.readLine() == null && fileReadAllData.readLine() == null) {
                    System.out.println("You must add service");
                    break;
                }
                //add data in list
                BufferedReader addListAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                String line;
                //info from file add to list
                while ((line = addListAllData.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    listAdd.add(line);
                    addTypeService0.add(listAdd);
                }
                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want see average time end? Enter YES or NO: ");
                    String answerTypeService = in.nextLine().toLowerCase();
                    if (answerTypeService.equals("yes")) {
                        while (true) {
                            if (answerTypeService.equals("no")) {
                                System.out.println("No problem");
                                break;
                            } else if (answerTypeService.equals("yes")) {
                                System.out.println("\n----------List of service----------");
                                int count = 1;
                                //end
                                int timeEndHour = 0;
                                int timeEndMinute = 0;
                                //start
                                int timeStartHour = 0;
                                int timeStartMinute = 0;

                                int quantity = 0;
                                for (List<String> list : addTypeService0) {
                                    if (list.size() >= 5) {
                                        if (!list.get(4).isBlank()) {
                                            System.out.println(count + ") type: " + list.get(0) + " time: " + list.get(4));
                                            count++;
                                            quantity++;
                                            String[] parts = list.get(4).split("-");
                                            timeStartHour += Integer.parseInt(parts[0].substring(0, 2));
                                            timeStartMinute += Integer.parseInt(parts[0].substring(3, 5));
                                            timeEndHour += Integer.parseInt(parts[1].substring(0, 2));
                                            timeEndMinute += Integer.parseInt(parts[1].substring(3, 5));

                                        } else {
                                            System.out.println(count + ") type: " + list.get(0) + " time: 0");
                                            timeStartHour += 0;
                                            timeStartMinute += 0;
                                            timeEndHour += 0;
                                            timeEndMinute += 0;
                                        }
                                    } else {
                                        System.out.println(count + ") type: " + list.get(0) + " time: 0");
                                        count++;
                                        timeStartHour += 0;
                                        timeStartMinute += 0;
                                        timeEndHour += 0;
                                        timeEndMinute += 0;
                                    }
                                }
                                while (timeEndMinute > 60) {
                                    timeEndMinute -= 60;
                                    timeEndHour++;
                                }
                                while (timeStartMinute > 60) {
                                    timeStartMinute -= 60;
                                    timeStartHour++;
                                }
                                int answerCost = (timeEndHour - timeStartHour) / quantity;
                                System.out.printf("\nAverage order value: (time end - time start) / quantity order = (%d - %d) / %d = %d",
                                        timeEndHour, timeStartHour, quantity, answerCost);
                                System.out.println();
                                break;
                            }
                        }
                        break;
                    } else {
                        System.out.println("No problem");
                        break;
                    }
                }
                addListAllData.close();
                break;
            } else if (readAllDataOrder.readLine() != null && fileAllDataOrder.exists()) {
                BufferedReader readFileOrder = new BufferedReader(new FileReader(fileAllDataOrder));
                String line;
                //add data in list
                while ((line = readFileOrder.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    if (line.contains(",")) {
                        int index = 0;
                        for (int i = 0; i < line.length(); i++) {
                            if (line.charAt(i) == ',') {
                                listAdd.add(line.substring(index, i));
                                index = i + 1;
                            }
                        }
                        listAdd.add(line.substring(index));
                    } else {
                        listAdd.add(line);
                    }
                    addTypeService0.add(listAdd);
                }
                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want see average order value? Enter YES or NO: ");
                    String answerTypeService = in.nextLine().toLowerCase();
                    if (answerTypeService.equals("yes")) {
                        while (true) {
                            if (answerTypeService.equals("no")) {
                                System.out.println("No problem");
                                break;
                            } else if (answerTypeService.equals("yes")) {
                                System.out.println("\n----------List of service----------");
                                int count = 1;
                                //end
                                int timeEndHour = 0;
                                int timeEndMinute = 0;
                                //start
                                int timeStartHour = 0;
                                int timeStartMinute = 0;

                                int quantity = 0;
                                for (List<String> list : addTypeService0) {
                                    if (list.size() >= 5) {
                                        if (!list.get(4).isBlank()) {
                                            System.out.println(count + ") type: " + list.get(0) + " time: " + list.get(4));
                                            count++;
                                            quantity++;
                                            String[] parts = list.get(4).split("-");
                                            timeStartHour += Integer.parseInt(parts[0].substring(0, 2));
                                            timeStartMinute += Integer.parseInt(parts[0].substring(3, 5));
                                            timeEndHour += Integer.parseInt(parts[1].substring(0, 2));
                                            timeEndMinute += Integer.parseInt(parts[1].substring(3, 5));

                                        } else {
                                            System.out.println(count + ") type: " + list.get(0) + " time: 0");
                                            timeStartHour += 0;
                                            timeStartMinute += 0;
                                            timeEndHour += 0;
                                            timeEndMinute += 0;
                                            quantity++;
                                        }
                                    } else {
                                        System.out.println(count + ") type: " + list.get(0) + " time: 0");
                                        count++;
                                        quantity++;
                                        timeStartHour += 0;
                                        timeStartMinute += 0;
                                        timeEndHour += 0;
                                        timeEndMinute += 0;
                                    }
                                }
                                while (timeEndMinute > 60) {
                                    timeEndMinute -= 60;
                                    timeEndHour++;
                                }
                                while (timeStartMinute > 60) {
                                    timeStartMinute -= 60;
                                    timeStartHour++;
                                }
                                int answerCost = (timeEndHour - timeStartHour) / quantity;
                                System.out.printf("\nAverage order value: (time end - time start) / quantity order = (%d - %d) / %d = %d",
                                        timeEndHour, timeStartHour, quantity, answerCost);
                                System.out.println();
                                break;
                            }
                        }
                        break;
                    } else if (answerTypeService.equals("no")) {
                        System.out.println("No problem");
                        addTypeService0.clear();
                        break;
                    }
                }
                break;
            } else if (!fileAllDataOrder.exists()) {
                fileAllDataOrder.createNewFile();
                System.out.println("File successful create");
            } else {
                System.out.println("You can not see average time, because did not exist no one type of service");
                break;
            }
        }
        readAllDataOrder.close();
        allData.menu();

    }
    public void addClientService() throws IOException {
        BufferedReader readAllDataOrder = new BufferedReader(new FileReader(fileAllDataOrder));
        BufferedReader readClientFile = new BufferedReader(new FileReader(clientFileUpdated));

        List<List<String>> addTypeService0 = new ArrayList<>();
        List<List<String>> addTypeClient0 = new ArrayList<>();
        List<List<String>> addServiceClient0 = new ArrayList<>();


        while (true) {
            if ((readAllDataOrder.readLine() == null || readClientFile.readLine() == null) && fileAllDataOrder.exists() && clientFileUpdated.exists()) {
                if (readAllDataOrder.readLine() == null) {
                    System.out.println("You must add service");
                } else if (readClientFile.readLine() == null) {
                    System.out.println("You must add client");
                } else {
                    System.out.println("You must add data");
                }

            } else if (readAllDataOrder.readLine() != null && readClientFile.readLine() != null && fileAllDataOrder.exists() && clientFileUpdated.exists()) {
                BufferedReader readFileOrder = new BufferedReader(new FileReader(fileAllDataOrder));
                BufferedReader readClientFileUpdated = new BufferedReader(new FileReader(clientFileUpdated));
                //add data in list
                String line, line1;
                while ((line = readFileOrder.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    if (line.contains(",")) {
                        int index = 0;
                        for (int i = 0; i < line.length(); i++) {
                            if (line.charAt(i) == ',') {
                                listAdd.add(line.substring(index, i));
                                index = i + 1;
                            }
                        }
                        listAdd.add(line.substring(index));
                    } else {
                        listAdd.add(line);
                    }
                    addTypeService0.add(listAdd);
                }
                while ((line1 = readClientFileUpdated.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    if (line1.contains(",")) {
                        int index = 0;
                        for (int i = 0; i < line1.length(); i++) {
                            if (line1.charAt(i) == ',') {
                                listAdd.add(line1.substring(index, i));
                                index = i + 1;
                            }
                        }
                        listAdd.add(line1.substring(index));
                    } else {
                        listAdd.add(line1);
                    }
                    addTypeClient0.add(listAdd);
                }
                //saw data
                System.out.println("--------List of service--------");
                int listi = 1;
                for (List<String> list : addTypeService0) {
                    for (String lineForList : list) {
                        if (list.size() > 1) {
                            String temporaryStr = String.join(", ", list);
                            System.out.println(listi + ") " + temporaryStr);
                            listi++;
                            break;
                        } else {
                            System.out.println(listi + ") " + lineForList);
                            listi++;
                        }
                    }
                }
                System.out.println();
                System.out.println("--------List of client--------");
                int count = 1;
                for (List<String> list : addTypeClient0) {
                    for (String lineForList : list) {
                        if (list.size() > 1) {
                            String temporaryStr = String.join(", ", list);
                            System.out.println(count + ") " + temporaryStr);
                            count++;
                            break;
                        } else {
                            System.out.println(count + ") " + lineForList);
                            count++;
                        }
                    }
                }
                System.out.println();

                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want add order to client? Enter YES or NO: ");
                    String answer = in.nextLine().toLowerCase();
                    if (answer.equals("yes")) {
                        while (true) {
                            System.out.print("Please enter name of service: ");
                            String answerTypeService = in.nextLine();
                            System.out.print("Please enter name of client: ");
                            String answerNameClient = in.nextLine();
                            if (answerTypeService.equals("no") || answerNameClient.equals("no")) {
                                System.out.println("No problem");
                                try (BufferedWriter writer = new BufferedWriter(new FileWriter(orderFile))) {
                                    for (List<String> list : addServiceClient0) {
                                        for (String item : list) {
                                            int index = list.size() - 1;
                                            if (list.size() == 1 || item == list.get(index)) {
                                                writer.write(item);
                                            } else {
                                                writer.write(item + ",");
                                            }
                                        }
                                        writer.newLine();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    addTypeService0.clear();
                                }
                                break;
                            } else {
                                boolean searchClient = false;
                                boolean searchService = false;
                                List<String> listSearchClient = new ArrayList<>();
                                List<String> listSearchService = new ArrayList<>();

                                for (List<String> listService : addTypeService0) {
                                    for (String lineListService : listService) {
                                        if (lineListService.equals(answerTypeService)) {
                                            searchService = true;
                                            listSearchService.addAll(listService);
                                            break;
                                        }
                                    }
                                }
                                for (List<String> listClient : addTypeClient0) {
                                    for (String lineListClient : listClient) {
                                        if (lineListClient.equals(answerNameClient)) {
                                            searchClient = true;
                                            listSearchService.addAll(listClient);
                                            break;
                                        }
                                    }
                                }

                                if (searchClient && searchService) {
                                    List<String> combinedList = new ArrayList<>(listSearchClient);
                                    combinedList.addAll(listSearchService);
                                    addServiceClient0.add(combinedList);
                                } else if (!searchClient || !searchService) {
                                    System.out.println("Please enter correct data");
                                }
                            }


                        }
                        break;
                    } else if (answer.equals("no")) {
                        System.out.println("No problem");
                        addTypeService0.clear();
                        break;
                    }
                }
                break;
            } else if (!fileAllDataOrder.exists() || !clientFileUpdated.exists()) {
                if (!fileAllDataOrder.exists()) {
                    fileAllDataOrder.createNewFile();
                    System.out.println("File successful create fileAllDataOrder");
                } else if (!clientFileUpdated.exists()) {
                    clientFileUpdated.createNewFile();
                    System.out.println("File successful create clientFileUpdated");
                }
            } else {
                System.out.println("You can not add data, because did not exist");
                break;
            }
        }
        addTypeClient0.clear();
        addTypeService0.clear();
        readClientFile.close();
        readAllDataOrder.close();
        allData.menu();

    }
    public void viewListClientChooseService() throws IOException {
        BufferedReader readOrderFile = new BufferedReader(new FileReader(orderFile));
        List<List<String>> addServiceClient0 = new ArrayList<>();
        while (true) {
            if (readOrderFile.readLine() != null && orderFile.exists()) {
                BufferedReader readFileOrder = new BufferedReader(new FileReader(orderFile));
                String line;
                //add data in list
                while ((line = readFileOrder.readLine()) != null) {
                    List<String> listAdd = new ArrayList<>();
                    if (line.contains(",")) {
                        int index = 0;
                        for (int i = 0; i < line.length(); i++) {
                            if (line.charAt(i) == ',') {
                                listAdd.add(line.substring(index, i));
                                index = i + 1;
                            }
                        }
                        listAdd.add(line.substring(index));
                    } else {
                        listAdd.add(line);
                    }
                    addServiceClient0.add(listAdd);
                }
                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want use this function? Enter YES or NO: ");
                    String answer = in.nextLine().toLowerCase();
                    if (answer.equals("yes")) {
                        while (true) {
                            System.out.print("Please enter name service: ");
                            String answerNameService = in.nextLine().toLowerCase();
                            if(answerNameService.equals("no")){
                                System.out.println("No problem");
                                break;
                            }
                            else{
                                System.out.println("Service: " + answerNameService);
                                for(List<String> lineForServiceClient0 : addServiceClient0){
                                    if(lineForServiceClient0.get(0).equals(answerNameService)){
                                        System.out.println(lineForServiceClient0.get(5));
                                    }
                                }
                            }
                        }
                        break;
                    }
                    else if (answer.equals("no")) {
                        System.out.println("No problem");
                        break;
                    }
                }
                break;
            }
            else if (readOrderFile.readLine() == null && orderFile.exists()) {
                System.out.println("You can not see list? because it is not exist");
            }
            else if (!orderFile.exists()) {
                orderFile.createNewFile();
                System.out.println("File successful create orderFile");
            }
            else {
                System.out.println("You can not see list, because did not exist");
                break;
            }
        }
        addServiceClient0.clear();
        readOrderFile.close();
        allData.menu();

    }
    private void readFileIntoList(File filePath, List<String> list) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        }
    }
    //service
    public void addTypeService() throws IOException {
        //list
        List<String> listAddService = new ArrayList<String>();
        BufferedReader readServiceFile = new BufferedReader(new FileReader(serviceFile));
        readFileIntoList(serviceFile, listAddService);

        //scanner
        Scanner in = new Scanner(System.in);
        System.out.print("Do you want to add a new service? Enter YES or NO: ");
        String answerService = in.nextLine().toLowerCase();
        //main code
        while (true) {
            if (answerService.equals("yes") && serviceFile.exists()) {
                while (true) {
                    System.out.print("Please enter name new service: ");
                    String newService = in.nextLine().toLowerCase();
                    if (newService.equals("no")) {
                        System.out.println("Ok, system do not add service\n");
                        BufferedWriter fileWriterService = new BufferedWriter(new FileWriter(serviceFile));
                        BufferedWriter fileWriterAllData = new BufferedWriter(new FileWriter(fileAllDataOrder, true));
                        //read
                        BufferedReader fileReadAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                        BufferedReader fileReadService = new BufferedReader(new FileReader(serviceFile));
                        // if - else
                        if (fileReadService.readLine() != null && fileReadAllData.readLine() == null) {
                            BufferedReader readService = new BufferedReader(new FileReader(serviceFile));
                            String lineAddDataFile;
                            while ((lineAddDataFile = readService.readLine()) != null) {
                                fileWriterAllData.write(lineAddDataFile + "\n");
                            }
                            readService.close();
                        }
                        if (fileReadService.readLine() == null && fileReadAllData.readLine() != null) {
                            BufferedReader readAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                            String lineAddDataFile;
                            while ((lineAddDataFile = readAllData.readLine()) != null) {
                                fileWriterService.write(lineAddDataFile + "\n");
                            }
                            readAllData.close();
                        }
                        if (fileReadService.readLine() == null && fileReadAllData.readLine() == null) {
                            for (String lineServiceAddFile : listAddService) {
                                fileWriterService.write(lineServiceAddFile + "\n");
                                fileWriterAllData.write(lineServiceAddFile + "\n");
                            }
                        }
                        listAddService.clear();
                        fileWriterService.close();
                        fileWriterAllData.close();
                        fileReadService.close();
                        fileReadAllData.close();
                        break;
                    } else {
                        if (listAddService.contains(newService)) {
                            System.out.println("This name is in the file");
                        } else {
                            System.out.println("Service successful add");
                            listAddService.add(newService);
                        }
                    }
                }
                break;
            } else if (answerService.equals("no") && serviceFile.exists()) {
                System.out.println("Ok, system do not add service\n");
                BufferedWriter fileWriterService = new BufferedWriter(new FileWriter(serviceFile));
                BufferedWriter fileWriterAllData = new BufferedWriter(new FileWriter(fileAllDataOrder, true));
                //read
                BufferedReader fileReadAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                BufferedReader fileReadService = new BufferedReader(new FileReader(serviceFile));
                //if - else
                if (fileReadService.readLine() != null && fileReadAllData.readLine() == null) {
                    BufferedReader readService = new BufferedReader(new FileReader(serviceFile));
                    String lineAddDataFile;
                    while ((lineAddDataFile = readService.readLine()) != null) {
                        fileWriterAllData.write(lineAddDataFile + "\n");
                    }
                    readService.close();
                } else if (fileReadService.readLine() == null && fileReadAllData.readLine() != null) {
                    BufferedReader readAllData = new BufferedReader(new FileReader(fileAllDataOrder));
                    String lineAddData;
                    while ((lineAddData = readAllData.readLine()) != null) {
                        fileWriterService.write(lineAddData + "\n");
                        System.out.println(lineAddData);
                    }
                    readAllData.close();
                } else if (fileReadService.readLine() == null && fileReadAllData.readLine() == null) {
                    for (String lineServiceAddFile : listAddService) {
                        fileWriterService.write(lineServiceAddFile + "\n");
                        fileWriterAllData.write(lineServiceAddFile + "\n");
                    }
                }

                listAddService.clear();
                fileWriterService.close();
                fileWriterAllData.close();
                fileReadService.close();
                fileReadAllData.close();
                break;
            } else {
                System.out.println("Create file serviceFile");
                serviceFile.createNewFile();
            }
        }
        readServiceFile.close();
        listAddService.clear();
        allData.menu();
    }
    public void removeTypeService() throws IOException {
        List<String> services = loadServices();
        if (services.isEmpty()) {
            System.out.println("You cannot delete a type of service because there are no services in the file.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Do you want to remove a type of service? Enter YES or NO: ");
            if (!scanner.hasNextLine()) {
                System.out.println("No input provided.");
                break;
            }
            String answer = scanner.nextLine().trim().toLowerCase();

            if ("yes".equals(answer)) {
                displayServices();
                handleServiceRemoval(services, scanner);
                saveServices(services);
                break;
            }
            else if ("no".equals(answer)) {
                System.out.println("No changes made.");
                break;
            }
        }
        allData.menu();
    }
    protected List<String> loadServices() throws IOException {
        List<String> services = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(serviceFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                services.add(line);
            }
        }
        return services;
    }
    protected void displayServices() throws IOException {
        System.out.println("----------------List of Services----------------");
        try (BufferedReader reader = new BufferedReader(new FileReader(linkService))) {
            String line;
            int index = 1;
            while ((line = reader.readLine()) != null) {
                System.out.printf("%d) %s%n", index++, line);
            }
        }
    }
    protected void handleServiceRemoval(List<String> services, Scanner scanner) {
        List<String> removedServices = new ArrayList<>();
        while (true) {
            System.out.print("Enter the name of the service you want to delete (or type 'no' to cancel): ");
            if (!scanner.hasNextLine()) {
                System.out.println("No input provided.");
                break;
            }
            String serviceToRemove = scanner.nextLine().trim().toLowerCase();

            if ("no".equals(serviceToRemove)) {
                System.out.println("Operation cancelled.");
                break;
            } else if (services.remove(serviceToRemove)) {
                removedServices.add(serviceToRemove);
                System.out.println("Service removed successfully.");
            } else if (removedServices.contains(serviceToRemove)) {
                System.out.println("This service has already been removed.");
            } else {
                System.out.println("Service not found.");
            }
        }
    }
    private void saveServices(List<String> services) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(serviceFile))) {
            for (String service : services) {
                writer.write(service);
                writer.newLine();
            }
        }
    }
    public void testHandleServiceRemoval() throws IOException {
        List<String> services = loadServices();
        Scanner scanner = new Scanner(System.in);
        handleServiceRemoval(services, scanner);
    }
    public List<String> forTestLoadServices() throws IOException {
        List<String> services = loadServices();
        return services;
    }
    public void testHandleService(List<String> services, Scanner scanner) throws IOException {
        handleServiceRemoval(services, scanner);
    }

}
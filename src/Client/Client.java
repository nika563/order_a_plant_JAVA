package Client;

import Human.Human;
import AllData.AllData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Client extends Human {
    private String address;
    private int idOrder;

    public Client(String nameHuman, int phoneHumber, String address) {
        super(nameHuman, phoneHumber);
        this.address = address;
    }
    public Client() {
        super("", 01);
    }
    public void setNameHuman() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter full name: ");
        nameHuman = in.nextLine();
        in.close();
    }
    public void setPhoneHumber() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter mobile phone: ");
            phoneHumber = Integer.parseInt(in.nextLine());
            in.close();
        } catch (NumberFormatException e) {
            System.out.println("Please try again");
        }
    }
    public void setAddress() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter address: ");
        address = in.nextLine();
        in.close();
    }
    public void setIdOrder() {
        Random random = new Random();
        idOrder = random.nextInt();
        System.out.println("ID: " + idOrder);
    }
    //get
    public String getCompanyName() {
        return nameHuman;
    }
    public int getPhoneHumber(int phoneHumber) {
        return this.phoneHumber = phoneHumber;
    }
    public String getAddress() {
        return address;
    }
    public int getIdOrder() {
        return idOrder;
    }
    //see
    public void getAll() {
        System.out.println("\nName: " + nameHuman + "\nPhone number: " + phoneHumber + "\nAdress: " + address +
                "\nid order: " + idOrder + "\n"
        );
    }


    AllData allData = new AllData();
    //link to file
    String linkClientFileBeforeDeletion = "src/File/clientFile.txt";
    String linkClientFileUpdated = "src/File/clientFileUpdate.txt";
    //create file
    File clientFileBeforeDeletion = new File(linkClientFileBeforeDeletion); //все внесенные данные пользователем
    File clientFileUpdated = new File(linkClientFileUpdated);


    //update function
    public void addClient() throws IOException {
        //read
        BufferedReader readClientUpdated = new BufferedReader(new FileReader(clientFileUpdated));
        BufferedReader readClientBeforeDeletion = new BufferedReader(new FileReader(clientFileBeforeDeletion));
        //list
        List<List<String>> addClient0 = new ArrayList<>();
        List<List<String>> removeClient0 = new ArrayList<>();
        // add data in list
        String line,lines;
        while ((line = readClientUpdated.readLine()) != null) {
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
            addClient0.add(listAdd);
        }
        while ((lines = readClientBeforeDeletion.readLine()) != null) {
            List<String> listAdd = new ArrayList<>();
            if (lines.contains(",")) {
                int index = 0;
                for (int i = 0; i < lines.length(); i++) {
                    if (lines.charAt(i) == ',') {
                        listAdd.add(lines.substring(index, i));
                        index = i + 1;
                    }
                }
                listAdd.add(lines.substring(index));
            } else {
                listAdd.add(lines);
            }
            removeClient0.add(listAdd);
        }


        //scanner
        Scanner in = new Scanner(System.in);
        System.out.print("Do you want to add a new client? Enter YES or NO: ");
        String answerClient = in.nextLine().toLowerCase();
        //main code
        while (true) {
            if (answerClient.equals("yes") && clientFileUpdated.exists() && clientFileBeforeDeletion.exists()) {
                while (true) {
                    System.out.print("\nPlease enter name new client: ");
                    String newClient = in.nextLine().toLowerCase();
                    System.out.print("Please enter name address client: ");
                    String addressClient = in.nextLine().toLowerCase();
                    setIdOrder();
                    if (newClient.equals("no") || addressClient.equals("no")) {
                        System.out.println("Ok, system do not add client\n");
                        BufferedWriter writer = new BufferedWriter(new FileWriter(clientFileUpdated));
                        BufferedWriter writer1 = new BufferedWriter(new FileWriter(clientFileBeforeDeletion));
                        for (List<String> list : addClient0) {
                            for (String item : list) {
                                if (item == list.get(list.size() - 1)) {
                                    writer.write(item + "\n");
                                } else {
                                    writer.write(item + ",");
                                }
                            }
                        }
                        for (List<String> list1 : removeClient0) {
                            for (String item1 : list1) {
                                if (item1 == list1.get(list1.size() - 1)) {
                                    writer1.write(item1 + "\n");
                                } else {
                                    writer1.write(item1 + ",");
                                }
                            }
                        }
                        writer.close();
                        writer1.close();
                        break;
                    }
                    else {
                        boolean isUnique = true;
                        for (List<String> list : addClient0) {
                            if (list.get(0).equals(newClient)) {
                                System.out.println("Client already exists!");
                                isUnique = false;
                                break;
                            }
                        }
                        if (isUnique) {
                            System.out.println("System successful add client");
                            List<String> newList = new ArrayList<>();
                            newList.add(newClient + "," + addressClient + "," + idOrder);
                            addClient0.add(newList);
                            removeClient0.add(newList);
                        }
                        System.out.println(addClient0);
                        System.out.println(removeClient0);
                    }
                }
                break;
            }
            else if (answerClient.equals("no") && clientFileUpdated.exists() && clientFileBeforeDeletion.exists()) {
                System.out.println("Ok, system do not add client\n");
                BufferedWriter writer = new BufferedWriter(new FileWriter(clientFileUpdated));
                BufferedWriter writer1 = new BufferedWriter(new FileWriter(clientFileBeforeDeletion));
                for (List<String> list : addClient0) {
                    for (String item : list) {
                        if (item == list.get(list.size() - 1)) {
                            boolean isLastList = addClient0.size() - 1 == list.size() - 1;
                            if (isLastList) {
                                writer.write(item);
                            } else {
                                writer.write(item + "\n");
                            }
                        } else {
                            writer.write(item + ",");
                        }
                    }
                }
                for (List<String> list1 : removeClient0) {
                    for (String item1 : list1) {
                        if (item1 == list1.get(list1.size() - 1)) {
                            boolean isLastList = addClient0.size() - 1 == list1.size() - 1;
                            if (isLastList) {
                                writer.write(item1);
                            } else {
                                writer.write(item1 + "\n");
                            }
                        } else {
                            writer.write(item1 + ",");
                        }
                    }
                }
                writer.close();
                writer1.close();
                break;
            }
            else if (!clientFileUpdated.exists() || !clientFileBeforeDeletion.exists()) {
                if(!clientFileUpdated.exists()) {
                    System.out.println("Create file clientFileUpdated");
                    clientFileUpdated.createNewFile();
                }
                else if(!clientFileBeforeDeletion.exists()){
                    System.out.println("Create file clientFileBeforeDeletion");
                    clientFileBeforeDeletion.createNewFile();
                }
            }
            else {
                System.out.println("Please enter correct data");
            }
        }
        addClient0.clear();
        removeClient0.clear();
        readClientUpdated.close();
        readClientBeforeDeletion.close();
        allData.menu();
    }
    public void removeClient() throws IOException {
        BufferedReader readClientFileUpdated = new BufferedReader(new FileReader(clientFileUpdated));
        List<List<String>> addClient0 = new ArrayList<>();
        while (true) {
            if (readClientFileUpdated.readLine() != null && clientFileUpdated.exists() && clientFileBeforeDeletion.exists()) {
                BufferedReader readClientUpdated = new BufferedReader(new FileReader(clientFileUpdated));
                String line;
                //add data in list
                while ((line = readClientUpdated.readLine()) != null) {
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
                    addClient0.add(listAdd);
                }
                //saw data
                System.out.println("--------List of client--------");
                int listi = 1;
                for (List<String> list : addClient0) {
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
                    System.out.print("Do you want remove client? Enter YES or NO: ");
                    String answer = in.nextLine().toLowerCase();
                    if (answer.equals("yes")) {
                        while (true) {
                            System.out.print("Please enter name of client: ");
                            String answerTypeService = in.nextLine();
                            if (answerTypeService.equals("no")) {
                                System.out.println("No problem");
                                BufferedWriter writer = new BufferedWriter(new FileWriter(clientFileUpdated));
                                for (List<String> list : addClient0) {
                                    for (String item : list) {
                                        if (item == list.get(list.size() - 1)) {
                                            boolean isLastList = addClient0.size() - 1 == list.size() - 1;
                                            if (isLastList) {
                                                writer.write(item);
                                            } else {
                                                writer.write(item + "\n");
                                            }
                                        } else {
                                            writer.write(item + ",");
                                        }
                                    }
                                }
                                writer.close();
                                break;
                            }
                            else {
                                for (List<String> list : addClient0) {
                                    if (list.get(0).equals(answerTypeService)) {
                                        System.out.println("System successful remove client");
                                        addClient0.remove(list);
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    } else if (answer.equals("no")) {
                        System.out.println("No problem");
                        break;
                    }
                }
                break;
            }
            else if (!clientFileUpdated.exists()) {
                if (!clientFileUpdated.exists()) {
                    clientFileUpdated.createNewFile();
                    System.out.println("File successful create clientFileUpdated");
                } else {
                    clientFileBeforeDeletion.createNewFile();
                    System.out.println("File successful create clientFileBeforeDeletion");
                }
            }
            else {
                System.out.println("You can not remove time to client, because did not exist no one client");
                break;
            }
        }
        addClient0.clear();
        readClientFileUpdated.close();
        allData.menu();

    }
    public void sawBeforeDeleteClient() throws IOException {
        BufferedReader readClientBeforeDeletion = new BufferedReader(new FileReader(clientFileBeforeDeletion));
        List<List<String>> removeClient0 = new ArrayList<>();
        while (true) {
            if (readClientBeforeDeletion.readLine() != null && clientFileBeforeDeletion.exists()) {
                BufferedReader readClientBeforeDelete = new BufferedReader(new FileReader(clientFileBeforeDeletion));
                String line;
                //add data in list
                while ((line = readClientBeforeDelete.readLine()) != null) {
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
                    removeClient0.add(listAdd);
                }
                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want see client? Enter YES or NO: ");
                    String answer = in.nextLine().toLowerCase();
                    if (answer.equals("no")) {
                        System.out.println("No problem");
                        break;
                    } else {
                        System.out.println("\n--------List of client--------");
                        int listi = 1;
                        for (List<String> list : removeClient0) {
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
                    }
                    break;
                }
            }
            else if (!clientFileBeforeDeletion.exists()) {
                    clientFileBeforeDeletion.createNewFile();
                    System.out.println("File successful create clientFileBeforeDeletion");
                }
            else {
                    System.out.println("You can not remove time to client, because did not exist no one client");
                    break;
                }
            removeClient0.clear();
            readClientBeforeDeletion.close();
            allData.menu();
        }
    }
    public void sawClient() throws IOException {
        BufferedReader readClientUpdated= new BufferedReader(new FileReader(clientFileUpdated));
        List<List<String>> seeClient0 = new ArrayList<>();
        while (true) {
            if (readClientUpdated.readLine() != null && clientFileUpdated.exists()) {
                BufferedReader readClientFileUpdated= new BufferedReader(new FileReader(clientFileUpdated));
                String line;
                //add data in list
                while ((line = readClientFileUpdated.readLine()) != null) {
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
                    seeClient0.add(listAdd);
                }
                while (true) {
                    Scanner in = new Scanner(System.in);
                    System.out.print("Do you want see client? Enter YES or NO: ");
                    String answer = in.nextLine().toLowerCase();
                    if (answer.equals("no")) {
                        System.out.println("No problem");
                        break;
                    }
                    else {
                        System.out.println("\n--------List of client--------");
                        int listi = 1;
                        for (List<String> list : seeClient0) {
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
                    }
                    break;
                }
            }
            else if (!clientFileUpdated.exists()) {
                clientFileBeforeDeletion.createNewFile();
                System.out.println("File successful create clientFileBeforeDeletion");
            }
            else {
                System.out.println("You can not remove time to client, because did not exist no one client");
                break;
            }
            seeClient0.clear();
            readClientUpdated.close();
            allData.menu();
        }
    }

}





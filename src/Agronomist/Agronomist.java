package Agronomist;

import AllData.AllData;
import Human.Human;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Agronomist extends Human {
    private static String nameBranch;

    static{
        nameBranch = "Herb";
    }
    protected Agronomist(String nameHuman, int phoneHumber){
        super(nameHuman, phoneHumber);
    }
    protected Agronomist(){super("", 01);}
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
        }catch (NumberFormatException e){
            System.out.println("Please try again");
        }
    }
    public String getCompanyName() {
        return nameHuman;
    }
    public int getPhoneHumber(int phoneHumber) {
        return this.phoneHumber = phoneHumber;
    }
    public String getNameBranch() {
        return nameBranch;
    }
    public void getAll() {
        System.out.println("\nName: " + nameHuman + "\nPhone number: " + phoneHumber + "\nName branch: " + nameBranch + "\n"
        );
    }


    AllData allData = new AllData();
    //link to file
    String linkPlantFileBeforeDeletion = "src/File/plantFileBeforeDelete.txt";
    String linkPlantFileUpdated = "src/File/plantFileUpdate.txt";
    String linkPlantingPlant = "src/File/plantPlanting.txt";
    String linkRemovePlantingPlant = "src/File/plantRemovePlanting.txt";
    //create file
    File plantFileBeforeDeletion = new File(linkPlantFileBeforeDeletion);
    File plantFileUpdated = new File(linkPlantFileUpdated);
    File plantFilePlanting = new File(linkPlantingPlant);
    File plantFileRemovePlanting = new File(linkRemovePlantingPlant);
    //method
    public void sawPlant() throws IOException {
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Do you want to see list plant? Enter YES or NO: ");
            String viewListPlant = in.nextLine().toLowerCase();

            if (viewListPlant.equals("yes")) {
                if (plantFileUpdated.exists()) {
                    try (BufferedReader readListPlant = new BufferedReader(new FileReader(plantFileUpdated))) {
                        String lineReadListPlant;
                        int lineNumber = 1;
                        while ((lineReadListPlant = readListPlant.readLine()) != null) {
                            System.out.printf("%d) %s\n", lineNumber, lineReadListPlant);
                            lineNumber++;
                        }
                        System.out.println();
                        break;
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + e.getMessage());
                    }
                } else if (!plantFileUpdated.exists()) {
                    try {
                        plantFileUpdated.createNewFile();
                        System.out.println("File created: plantFileBeforeDelete.txt");
                    } catch (IOException e) {
                        System.err.println("Error creating the file: " + e.getMessage());
                        break;
                    }
                } else {
                    System.out.println("Error file not exists \"plantFileBeforeDeletion\" in viewListPlant");
                    break;
                }

            }
            else if (viewListPlant.equals("no")) {
                System.out.println("Ok, no problem, you do not see a list plant");
                break;
            } else if (!viewListPlant.equals("yes") && !viewListPlant.equals("no")) {
                continue;
            } else {
                System.out.print("Error sawPlant");
                break;
            }
            allData.menu();
        }
        allData.menu();
    }
    public void addPlant() throws IOException {
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Do you want to add a new plant? Enter YES or NO: ");
            String answerPlant = in.nextLine().toLowerCase();
            if (answerPlant.equals("yes")) {
                while (true) {
                    System.out.print("Please enter name new plant: ");
                    String newPlant = in.nextLine().toLowerCase();
                    if (newPlant.equals("no")) {
                        System.out.println("Ok, system do not add plant\n");
                        break;
                    }
                    else {
                        while (true) {
                            BufferedReader readPlantFile = new BufferedReader(new FileReader(plantFileBeforeDeletion));
                            String lineReadPlantFile = null;
                            if (plantFileBeforeDeletion.exists()) {
                                String answerReadPlantFile = "";
                                while ((lineReadPlantFile = readPlantFile.readLine()) != null) {
                                    if (lineReadPlantFile.equals(newPlant)) {
                                        answerReadPlantFile = "true";
                                        break;
                                    } else if (!lineReadPlantFile.equals(newPlant)) {
                                        answerReadPlantFile = "false";
                                    } else {
                                        System.out.println("ERROR readPlantFile");
                                    }
                                }
                                if (answerReadPlantFile.equals("true")) {
                                    System.out.println("This name is in the file");
                                    System.out.print("You want to add a plant? Enter YES or NO: ");
                                    String answerAddPlant = in.nextLine().toLowerCase();
                                    if (answerAddPlant.equals("yes")) {
                                        System.out.println("plant add");
                                        FileWriter fileWriter = new FileWriter(plantFileBeforeDeletion, true);
                                        PrintWriter printWriter = new PrintWriter(fileWriter);
                                        printWriter.println(newPlant);
                                        printWriter.close();

                                        break;
                                    } else if (answerAddPlant.equals("no")) {
                                        System.out.println("OK, system did not add this plant");
                                        break;
                                    } else {
                                        continue;
                                    }
                                }
                                else if (!answerReadPlantFile.equals("true")) {
                                    FileWriter fileWriter = new FileWriter(plantFileBeforeDeletion, true);
                                    PrintWriter printWriter = new PrintWriter(fileWriter);
                                    printWriter.println(newPlant);
                                    printWriter.close();
                                    break;
                                }
                                else {
                                    System.out.println("ERROR plantFileBeforeDeletion 1");
                                    break;
                                }
                            }
                            else if (!plantFileBeforeDeletion.exists()) {
                                try {
                                    plantFileBeforeDeletion.createNewFile();
                                    System.out.println("File created: plantFileBeforeDeletion.txt");
                                } catch (IOException e) {
                                    System.err.println("Error creating the file: " + e.getMessage());
                                    break;
                                }
                            }
                            else {
                                System.out.println("ERROR FILE plantFileBeforeDeletion");
                                break;
                            }
                        }
                    }
                }
                break;
            } else if (!answerPlant.equals("yes") && !answerPlant.equals("no")) {
                continue;
            }
            else if (answerPlant.equals("no")) {
                System.out.print("\n");
                break;
            }
            else {
                System.out.print("\n");
                break;
            }
        }
        allData.menu();
    }
    public void removePlant() throws IOException {
        List<String> listExistsElement = new ArrayList<String>();
        List<String> listDeletedItems = new ArrayList<String>();
        BufferedReader readStringFile = new BufferedReader(new FileReader(plantFileBeforeDeletion));
        BufferedReader readStringFileUpdated = new BufferedReader(new FileReader(plantFileUpdated));
        String stringFile;

        while ((stringFile = readStringFile.readLine()) != null) {
            listExistsElement.add(stringFile);
        }
        readStringFile.close();
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Do you want remove plant? Enter YES or NO: ");
            String answerRemovePlant = in.nextLine().toLowerCase();


            if (answerRemovePlant.equals("yes")) {
                FileWriter writerPlantFileUpdated = new FileWriter(plantFileUpdated);
                BufferedReader readStringFileCheck = new BufferedReader(new FileReader(plantFileBeforeDeletion));

                if (plantFileBeforeDeletion.exists()) {
                    if (readStringFileCheck.readLine() != null) {
                        String lineReadAnswerRemovePlant = "";
                        boolean found = false;
                        while (true) {
                            System.out.print("Enter name which you want remove: ");
                            String answerNameRemovePlant = in.nextLine();

                            if (answerNameRemovePlant.equals("no")) {
                                System.out.println("System no longer deletes plants\n");

                                BufferedWriter writePlantFileUpdated = new BufferedWriter(new FileWriter(plantFileUpdated));
                                if (readStringFileUpdated.readLine() != null) {
                                    writePlantFileUpdated.write("");
                                }

                                for (String line : listExistsElement) {
                                    writePlantFileUpdated.write(line + "\n");
                                }
                                writePlantFileUpdated.close();
                                break;
                            } else if (!answerNameRemovePlant.equals("no")) {
                                BufferedReader readFileBeforeDeletion = new BufferedReader(new FileReader(plantFileBeforeDeletion));
                                //check if this element is in the file
                                while ((lineReadAnswerRemovePlant = readFileBeforeDeletion.readLine()) != null) {
                                    if (lineReadAnswerRemovePlant.equals(answerNameRemovePlant)) {
                                        found = true;
                                        break;
                                    } else if (!lineReadAnswerRemovePlant.equals(answerNameRemovePlant)) {
                                        found = false;
                                    }
                                }
                                readFileBeforeDeletion.close();

                                if (!found) {
                                    System.out.println("You can not delete this element because it is not exists");
                                    continue;
                                } else if (found) {
                                    if (listExistsElement.equals(answerNameRemovePlant)) {
                                        System.out.printf("Plant successful remove: %s\n", answerNameRemovePlant);
                                        listExistsElement.remove(answerNameRemovePlant);
                                        listDeletedItems.add(answerNameRemovePlant);
                                        continue;
                                    } else if (!listExistsElement.equals(answerNameRemovePlant) && listDeletedItems.equals(answerNameRemovePlant)) {
                                        System.out.printf("The element has already been deleted\n");
                                        continue;
                                    }
                                } else {
                                    break;
                                }
                            }
                        }
                        break;
                    } else {
                        System.out.println("You do not remove plant\n");
                        break;
                    }
                } else if (!plantFileBeforeDeletion.exists()) {
                    try {
                        plantFileBeforeDeletion.createNewFile();
                        System.out.println("File created: PlantFile.txt");
                        System.out.println("You do not remove plant");
                        break;
                    } catch (IOException e) {
                        System.err.println("Error creating the file: " + e.getMessage());
                        break;
                    }
                }
            } else if (answerRemovePlant.equals("no")) {
                System.out.println("Ok, system do not remove plant");
                break;
            } else if (!answerRemovePlant.equals("yes") && !answerRemovePlant.equals("no")) {
                continue;
            } else {
                System.out.println("Error removePlant");
                break;
            }
        }
        allData.menu();
    }
    public void sawBeforeDeletePlant() throws IOException {
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Do you want to see list plant before deletion? Enter YES or NO: ");
            String viewListPlant = in.nextLine().toLowerCase();

            if (viewListPlant.equals("yes")) {
                if (plantFileUpdated.exists()) {
                    try (BufferedReader readListPlant = new BufferedReader(new FileReader(plantFileBeforeDeletion))) {
                        String lineReadListPlant;
                        int lineNumber = 1;
                        while ((lineReadListPlant = readListPlant.readLine()) != null) {
                            System.out.printf("%d) %s\n", lineNumber, lineReadListPlant);
                            lineNumber++;
                        }
                        System.out.println();
                        break;
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + e.getMessage());
                    }
                } else if (!plantFileUpdated.exists()) {
                    try {
                        plantFileUpdated.createNewFile();
                        System.out.println("File created: plantFileUpdated.txt");
                    } catch (IOException e) {
                        System.err.println("Error creating the file: " + e.getMessage());
                        break;
                    }
                } else {
                    System.out.println("Error file not exists \"plantFileUpdated\" in viewListPlant");
                    break;
                }

            } else if (viewListPlant.equals("no")) {
                System.out.println("Ok, no problem, you do not see a list plant");
                break;
            } else if (!viewListPlant.equals("yes") && !viewListPlant.equals("no")) {
                continue;
            } else {
                System.out.print("Error sawPlant");
                break;
            }
            allData.menu();
        }
        allData.menu();
    }
    //planting
    public void sawPlantPlanting() throws IOException {
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Do you want to see list plant planting? Enter YES or NO: ");
            String viewListPlant = in.nextLine().toLowerCase();

            if (viewListPlant.equals("yes")) {
                if (plantFilePlanting.exists()) {
                    try (BufferedReader readListPlant = new BufferedReader(new FileReader(plantFilePlanting))) {
                        String lineReadListPlant;
                        int lineNumber = 1;
                        while ((lineReadListPlant = readListPlant.readLine()) != null) {
                            System.out.printf("%d) %s\n", lineNumber, lineReadListPlant);
                            lineNumber++;
                        }
                        System.out.println();
                        break;
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + e.getMessage());
                    }
                } else if (!plantFilePlanting.exists()) {
                    try {
                        plantFilePlanting.createNewFile();
                        System.out.println("File created: plantFileBeforeDelete.txt");
                    } catch (IOException e) {
                        System.err.println("Error creating the file: " + e.getMessage());
                        break;
                    }
                } else {
                    System.out.println("Error file not exists \"plantFilePlanting\" in viewListPlant");
                    break;
                }

            } else if (viewListPlant.equals("no")) {
                System.out.println("Ok, no problem, you do not see a list plant planting");
                break;
            } else if (!viewListPlant.equals("yes") && !viewListPlant.equals("no")) {
                continue;
            } else {
                System.out.print("Error sawPlant");
                break;
            }
            allData.menu();
        }
        allData.menu();
    }
    public void addPlantPlanting() throws IOException {
        List<String> listPlants = new ArrayList<String>();
        List<String> listPlantingPlants = new ArrayList<String>();
        // read file
        BufferedReader readPlantStringFileBeforeDeletion = new BufferedReader(new FileReader(plantFileBeforeDeletion));
        BufferedReader readPlantStringFileUpdated = new BufferedReader(new FileReader(plantFileUpdated));
        String line;
        //add data in list
        if (readPlantStringFileBeforeDeletion.readLine() != null && readPlantStringFileUpdated.readLine() != null) {
            while ((line = readPlantStringFileUpdated.readLine()) != null) {
                listPlants.add(line);
            }
            readPlantStringFileUpdated.close();
            readPlantStringFileBeforeDeletion.close();
        } else if (readPlantStringFileBeforeDeletion.readLine() != null && readPlantStringFileUpdated.readLine() == null) {
            while ((line = readPlantStringFileBeforeDeletion.readLine()) != null) {
                listPlants.add(line);
            }
            readPlantStringFileBeforeDeletion.close();
            readPlantStringFileUpdated.close();
        } else if (readPlantStringFileBeforeDeletion.readLine() == null && readPlantStringFileUpdated.readLine() == null) {
            System.out.println("plantFileBeforeDeletion is empty");
            readPlantStringFileBeforeDeletion.close();
            readPlantStringFileUpdated.close();
            allData.menu();
        }
        // add listYеtPlantingPlants
        BufferedReader readPlanting1 = new BufferedReader(new FileReader(linkPlantFileBeforeDeletion));
        BufferedReader readPlanting2 = new BufferedReader(new FileReader(plantFileUpdated));
        if (readPlanting1.readLine() != null && readPlanting2.readLine() == null) {
            while ((line = readPlanting1.readLine()) != null) {
                listPlants.add(line);
            }
            readPlanting1.close();
            readPlanting2.close();
        } else if (readPlanting1.readLine() != null && readPlanting2.readLine() != null) {
            while ((line = readPlanting2.readLine()) != null) {
                listPlants.add(line);
            }
            readPlanting1.close();
            readPlanting2.close();
        } else if (readPlanting1.readLine() == null && readPlanting2.readLine() == null) {
            System.out.println("You can not add the plant for planting, because plants not exists");
            allData.menu();
        }


        //main code
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Do you want to add a plant for planting? Enter YES or NO: ");
            String answerPlant = in.nextLine().toLowerCase();


            if (answerPlant.equals("yes")) {
                //see list
                int number = 1;
                System.out.println("----------------List of plant----------------");
                BufferedReader readSeePlanting = new BufferedReader(new FileReader(linkPlantFileBeforeDeletion));
                while ((line = readSeePlanting.readLine()) != null) {
                    System.out.printf("%d) %s \n", number, line);
                    number++;
                }
                readSeePlanting.close();

                //while
                while (true) {
                    System.out.print("Enter name which you want planting: ");
                    String answerPlanting = in.nextLine().toLowerCase();
                    if (answerPlanting.equals("no")) {
                        System.out.println("Ok, no problem system do not add plant for planting\n");
                        BufferedWriter writeFilePlanting = new BufferedWriter(new FileWriter(plantFilePlanting, true));

                        for (String lines : listPlantingPlants) {
                            writeFilePlanting.write(lines + "\n");
                        }
                        writeFilePlanting.close();
                        break;
                    } else if (!answerPlanting.equals("no")) {
                        if (listPlants.contains(answerPlanting)) {
                            listPlants.remove(answerPlanting);
                            listPlantingPlants.add(answerPlanting);
                            System.out.println("System successfully add plant for planting");
                        } else if (!listPlants.contains(answerPlanting) && listPlantingPlants.contains(answerPlanting)) {
                            System.out.println("This plant is already being planted");
                        } else if (!listPlants.contains(answerPlanting) && !listPlantingPlants.contains(answerPlanting)) {
                            System.out.println("You can not planting this plant because it is not in list");
                        }
                    }
                }
                break;
            } else if (!answerPlant.equals("yes") && !answerPlant.equals("no")) {
                continue;
            } else if (answerPlant.equals("no")) {
                System.out.print("\n");
                break;
            } else {
                System.out.print("\n");
                break;
            }
        }
        allData.menu();
    }
    public void removePlantPlanting() throws IOException {
        List<String> listRemovePlants = new ArrayList<String>();
        List<String> listRemovePlantingPlants = new ArrayList<String>();
        // read file
        BufferedReader readFilePlanting = new BufferedReader(new FileReader(plantFilePlanting));
        String line;
        //add data in list
        if (readFilePlanting.readLine() != null) {
            while ((line = readFilePlanting.readLine()) != null) {
                listRemovePlants.add(line);
            }
            readFilePlanting.close();
        } else if (readFilePlanting.readLine() == null) {
            System.out.println("You can not delete planting plants because no plants to plant\n");
            readFilePlanting.close();
            allData.menu();
        }


        // remove listYеtPlantingPlants
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Do you want to remove a plant for planting? Enter YES or NO: ");
            String answerPlant = in.nextLine().toLowerCase();


            if (answerPlant.equals("yes")) {
                //see list
                int number = 1;
                System.out.println("----------------List of planting plants----------------");
                BufferedReader readSeePlantingPlants = new BufferedReader(new FileReader(linkPlantingPlant));
                while ((line = readSeePlantingPlants.readLine()) != null) {
                    System.out.printf("%d) %s \n", number, line);
                    number++;
                }
                readSeePlantingPlants.close();

                //while
                while (true) {
                    System.out.print("Enter name which you want planting: ");
                    String answerPlanting = in.nextLine().toLowerCase();
                    if (answerPlanting.equals("no")) {
                        System.out.println("Ok, no problem system do not remove plant for planting\n");
                        BufferedWriter writeFilePlanting = new BufferedWriter(new FileWriter(plantFilePlanting));
                        BufferedWriter writeFileRemovePlanting = new BufferedWriter(new FileWriter(plantFileRemovePlanting, true));

                        for (String lines : listRemovePlants) {
                            writeFilePlanting.write(lines + "\n");
                        }
                        for (String lineRemove : listRemovePlantingPlants) {
                            writeFileRemovePlanting.write(lineRemove + "\n");
                        }
                        writeFilePlanting.close();
                        writeFileRemovePlanting.close();
                        break;
                    }
                    else {
                        if (listRemovePlants.contains(answerPlanting)) {
                            listRemovePlants.remove(answerPlanting);
                            listRemovePlantingPlants.add(answerPlanting);
                            System.out.println("System successfully remove plant for planting");
                        }
                        else if (!listRemovePlants.contains(answerPlanting) && listRemovePlantingPlants.contains(answerPlanting)) {
                            System.out.println("This plant is already remove");
                        }
                        else if (!listRemovePlants.contains(answerPlanting) && !listRemovePlantingPlants.contains(answerPlanting)) {
                            System.out.println("You can not remove this plant because it is not in list");
                        }
                    }
                }
                break;
            }
            else if (!answerPlant.equals("yes") && !answerPlant.equals("no")) {
                continue;
            }
            else if (answerPlant.equals("no")) {
                System.out.print("\n");
                break;
            }
            else {
                System.out.print("\n");
                break;
            }
        }
        allData.menu();
    }
}
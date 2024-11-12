package AllData;
import Client.Client;
import Agronomist.Agronomist;
import Order.Order;

import java.io.IOException;
import java.util.Scanner;

public class AllData {
    public String menu() throws IOException {
        System.out.println("---------------MENU--------------");
        System.out.println("1) Add plants"); //+
        System.out.println("2) Remove plants"); //+
        System.out.println("3) Add plants for planting"); //+
        System.out.println("4) Remove plants for planting"); //+
        System.out.println("5) Add client"); //+
        System.out.println("6) Remove client"); //+
        System.out.println("7) Add service type"); //+
        System.out.println("8) Remove service type"); //+
        System.out.println("9) Add service cost"); //+
        System.out.println("10) Remove service cost"); //+
        System.out.println("11) Add service size"); //+
        System.out.println("12) Remove service size"); //+
        System.out.println("13) Add plant to order"); //+
        System.out.println("14) Remove plant to order"); //+
        System.out.println("15) Add service time"); //+
        System.out.println("16) Remove service time"); //+
        System.out.println("17) Client choose service"); //+
        System.out.println("18) List of plants"); //+
        System.out.println("19) List of plants before removal"); //+
        System.out.println("20) List of plants to planting"); //+
        System.out.println("21) List of service types and their costs"); //+
        System.out.println("22) List of clients"); //+
        System.out.println("23) List of clients before removal"); //+
        System.out.println("24) List of clients who have chosen a specific type of service");
        System.out.println("25) Average order value"); //+
        System.out.println("26) Average completion time"); //+
        System.out.println("27) The service that has the highest value"); //+
        System.out.println("28) Exit\n"); //+

        while(true) {
            Client client = new Client();
            Agronomist agronomist = new Agronomist(){};
            Order order = new Order();


            Scanner in = new Scanner(System.in);
            System.out.print("Please enter a list number: ");
            if (!in.hasNextLine()) {
                System.out.println("No input provided.");
                break;
            }
            String MenuListNumber = in.nextLine().toLowerCase();

            if (MenuListNumber.equals("1") | MenuListNumber.equals("one")) {
                agronomist.addPlant();
            }
            else if (MenuListNumber.equals("2") | MenuListNumber.equals("two")) {
                agronomist.removePlant();
            }
            else if (MenuListNumber.equals("3") | MenuListNumber.equals("three")) {
                agronomist.addPlantPlanting();
            }
            else if (MenuListNumber.equals("4") | MenuListNumber.equals("four")) {
                agronomist.removePlantPlanting();
            }
            else if (MenuListNumber.equals("5") | MenuListNumber.equals("five")) {
                client.addClient();
            }
            else if (MenuListNumber.equals("6") | MenuListNumber.equals("six")) {
                client.removeClient();
            }
            else if (MenuListNumber.equals("7") | MenuListNumber.equals("seven")) {
                order.addTypeService();
            }
            else if (MenuListNumber.equals("8") | MenuListNumber.equals("eight")) {
                order.removeTypeService();
            }
            else if (MenuListNumber.equals("9") | MenuListNumber.equals("nine")) {
                order.addCostService();
            }
            else if (MenuListNumber.equals("10") | MenuListNumber.equals("ten")) {
                order.removeCostService();
            }
            else if (MenuListNumber.equals("11") | MenuListNumber.equals("eleven")) {
                order.addSizeService();
            }
            else if (MenuListNumber.equals("12") | MenuListNumber.equals("twelve")) {
                order.removeSizeService();
            }
            else if (MenuListNumber.equals("13") | MenuListNumber.equals("thirteen")) {
                order.addPlantService();
            }
            else if (MenuListNumber.equals("14") | MenuListNumber.equals("fourteen")) {
                order.removePlantService();
            }
            else if (MenuListNumber.equals("15") | MenuListNumber.equals("fifteen")) {
                order.addTimeService();
            }
            else if (MenuListNumber.equals("16") | MenuListNumber.equals("sixteen")) {
                order.removeTimeService();
            }
            else if (MenuListNumber.equals("17") | MenuListNumber.equals("seventeen")) {
                order.addClientService();
            }
            else if (MenuListNumber.equals("18") | MenuListNumber.equals("eighteen")) {
                agronomist.sawPlant();
            }
            else if (MenuListNumber.equals("19") | MenuListNumber.equals("nineteen")) {
                agronomist.sawBeforeDeletePlant();
            }
            else if (MenuListNumber.equals("20") | MenuListNumber.equals("twenty")) {
                agronomist.sawPlantPlanting();
            }
            else if (MenuListNumber.equals("21") | MenuListNumber.equals("twenty one")) {
                order.viewListServiceCost();
            }
            else if (MenuListNumber.equals("22") | MenuListNumber.equals("twenty two")) {
                client.sawClient();
            }
            else if (MenuListNumber.equals("23") | MenuListNumber.equals("twenty three")) {
                client.sawBeforeDeleteClient();
            }
            else if (MenuListNumber.equals("24") | MenuListNumber.equals("twenty four")) {
                order.viewListClientChooseService();
            }
            else if (MenuListNumber.equals("25") | MenuListNumber.equals("twenty five")) {
                order.averageOrderCost();
            }
            else if (MenuListNumber.equals("26") | MenuListNumber.equals("twenty six")) {
                order.averageOrderTime();
            }
            else if (MenuListNumber.equals("27") | MenuListNumber.equals("twenty seven")) {
                order.largeCost();
            }
            else if (MenuListNumber.equals("28") | MenuListNumber.equals("twenty eight")) {
                System.out.print("System finish work");
                break;
            }
            else {
                continue;
            }
        }
        return null;
    }
}

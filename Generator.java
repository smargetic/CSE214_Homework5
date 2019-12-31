import java.io.FileNotFoundException;
import java.util.Scanner;

public class Generator extends WebSite{
    public static void main(String[] args) throws FileNotFoundException, EmptyTreeException

    {
        //Scanner scanner = new Scanner(System.in);
        WebSite site = new WebSite();

        boolean truth = true;
        System.out.print("\nWelcome to ARK Website Generator! What would you like to do today?");
        while(truth){
            System.out.print("\n\nMenu");
            System.out.print("\n  (I)-Import .txt File");
            System.out.print("\n  (A)-Add Department");
            System.out.print("\n  (R)-Remove Department");
            System.out.print("\n  (C)-Current Department");
            System.out.print("\n  (G)-Go to Sub-department");
            System.out.print("\n  (H)-Head Department");
            System.out.print("\n  (P)-Print Format");
            System.out.print("\n  (E)-Empty Tree");
            System.out.print("\n  (Q)-Quit");

            System.out.print("\nEnter Selection: ");

            String answer = site.stdin.nextLine().trim().toLowerCase();

            switch(answer){
                case "i":
                    System.out.print("\nPlease enter the name of the .txt file: ");
                    String location = site.stdin.nextLine();


                    try{
                        //if the file exists, I load it
                        site.readInTree(location);
                    } catch(Exception FileNotFoundException) {
                        //if the file does not exist, I tell the user to try again
                        System.out.print("\n\nIncorrect File! Please try again!\n\n");
                    }

                    break;
                case "a":
                    System.out.print("\nWhat would you like to name the department?: ");
                    String newDepart = site.stdin.nextLine().trim();

                    //add department as child
                    WebPage newWeb = new WebPage(newDepart);
                    site.addDepartment(newWeb);
                    System.out.print("\nThe department has been added.");
                    break;
                case "r":
                    try{
                        if(site.getCursor()!=null){
                            System.out.print(site.getCursor().getName() + " has been removed.");
                        }
                        site.removeDepartment();
                    } catch (Exception EmptyTreeException) {
                        System.out.print("\nThe Tree is already empty.");

                    }


                    break;
                case "c":
                    if(site.getCursor()!=null){
                        System.out.print("\nYou are currently in " + site.getCursor().getName()); }
                    else {
                        System.out.print("\nThe tree is empty.");
                    }
                    break;
                case "g":
                    if(site.getCursor().getLinks()==null){
                        //if there are not sub-departments
                        System.out.print("\nThere is no further sub-Department");
                    } else {
                        System.out.print("\nWhich department would you like to go to? :");
                        //I print out the sub-departments
                        int length = site.getCursor().getLinks().length;
                        for (int i = 0; i < length; i++) {
                            int tempInt = i + 1;
                            System.out.print("\n\t(" + tempInt + ")-" + site.getCursor().getLink(i).getName());
                        }

                        System.out.print("\nEnter Selection: ");
                        String temp1 = site.stdin.nextLine().trim();
                        int number = Integer.parseInt(temp1);

                        //I change departments

                        site.setCursor(site.getCursor().getLink(number-1));
                    }
                    break;
                case "h":
                    //I go to parent
                    if(site.getCursor()!=null){
                        if(site.getCursor().getParent()==null){
                            //If I'm at the root, I tell the user
                            System.out.print("\nThere is no parent.");
                        } else {
                            site.setCursor(site.getCursor().getParent());
                            System.out.print("\nYou are currently in " + site.getCursor().getName());
                        }
                    } else {
                        //if the cursor does not yet exist
                        System.out.print("\nThe tree is empty.");
                    }

                    break;
                case "p":
                    try {
                    site.printFormat();
                    } catch (Exception EmptyTreeException) {
                        System.out.print("\nThere is currently no Website's format to print.");
                    }

                    break;

                case "e":
                    site.emptyTree();
                    break;
                case "q":
                    System.out.print("\nSorry to see you go. Have a good day!");
                    truth = false;
                    System.exit(0);
                    break;
                default:
                    System.out.print("\nThat is not an option. Please try again.");
            }


        }
    }
}

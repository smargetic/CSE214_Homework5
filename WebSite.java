import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WebSite extends WebPage{
    private WebPage homepage;
    private WebPage cursor; //points to the individual index


    public static Scanner stdin = new Scanner(System.in);

    public WebSite(){
        homepage = null;
        cursor = null;

    }

    public WebPage getCursor(){
        return this.cursor;
    }

    public void setCursor(WebPage newWeb){
        this.cursor = newWeb;
    }

    public void setHomepage(WebPage newWeb){
        this.homepage = newWeb;
    }

    public WebPage getHomepage(){
        return this.homepage;
    }

    public WebSite(WebPage homepage){
        this.homepage = homepage;
        this.cursor = homepage;
    }




    public static WebSite readInTree(String location) throws FileNotFoundException {

        WebSite site = new WebSite();

        try {
            stdin = new Scanner(new File(location));


            while (stdin.hasNextLine()) {
                String temp = stdin.nextLine().trim();
                String[] data = temp.split(" ", 2);
                //site.addToTree(data[0], data[1]);

                WebPage newWebPage = new WebPage(data[1]);


                int length = data[0].length();
                if (length == 1){

                    site.setHomepage(newWebPage);

                    //site.cursor = site.homepage;
                    site.setCursor(site.getHomepage());

                } else {

                    //site.cursor = this.homepage;
                    site.setCursor(site.getHomepage());
                    //I separate the numbers
                    char[] tempChar = data[0].toCharArray();
                    int length2 = tempChar.length;
                    int number[] = new int[length2];

                    for (int i = 0; i < length2-1; i++) {
                        String temp2 = String.valueOf((tempChar[i+1]));
                        int tempNumber = Integer.parseInt(temp2);
                        number[i] = tempNumber;
                    }

                    //I traverse the tree according to the numbers
                    for(int i=0; i<length2-2;i++){
                        int tempNumber = number[i];

                        site.setCursor(site.getCursor().getLink(tempNumber-1));
                    }


                    WebPage[] tempArray;


                    if(site.getCursor().getLinks()==null){
                        //If there is nothing present, I create a new array below
                        tempArray = new WebPage[1];
                        tempArray[0]=newWebPage;

                        tempArray[0].setParent(site.getCursor());


                        tempArray[0].setLevel(site.getCursor().getLevel()+1);
                        tempArray[0].setCapacity(1);
                       // System.out.print("\nNAME: " + tempArray[0].getName());


                        site.getCursor().setLink(tempArray);
                        site.setCursor(tempArray[0]);
                    } else {
                        //I expand the existing array and add in the new element
                       // int tempLength = this.cursor.getLinks().length;
                        int tempLength = site.getCursor().getLinks().length;
                        tempArray = new WebPage[tempLength+1];
                        for(int i=0;i<tempLength;i++){
                            //tempArray[i] = this.cursor.getLink(i);
                            tempArray[i] = site.getCursor().getLink(i);
                            tempArray[i].setCapacity(tempLength+1);
                        }
                        tempArray[tempLength] = newWebPage;

                        tempArray[tempLength].setParent(site.getCursor());

                        //variables to be used elsewhere
                        tempArray[tempLength].setCapacity(tempLength+1);
                        tempArray[tempLength].setIndex(tempLength);
                        tempArray[tempLength].setLevel(tempArray[tempLength-1].getLevel());
                        //System.out.print("\nNAME: " + tempArray[tempLength].getName());

                        site.getCursor().setLink(tempArray);
                    }




                }




            }


            //I reset the cursor to the homepage
            site.setCursor(site.getHomepage());


        } catch (Exception FileNotFoundException) {
        } finally {
            stdin = new Scanner(System.in); }
      //  site.printFormat();
        return site;
    }


    public void addDepartment(WebPage newWebPage){

        if(homepage == null){
            //if there is nothing present in the tree
            this.homepage = newWebPage;
            this.cursor = newWebPage;
            homepage.setLevel(0);
            homepage.setCapacity(1);

        } else {


            //I add to the department below the current department
            if(cursor.getLinks()==null){
                //if there is no array below
                WebPage[] list = new WebPage[1];
                list[0] = newWebPage;
                //I set the parent
                list[0].setParent(cursor);

                list[0].setLevel(cursor.getLevel()+1);
                list[0].setCapacity(1);

                cursor.setLink(list);
                cursor = list[0];



            } else {
                //I expand the array below

                int tempLength = cursor.getLinks().length;

                WebPage[] list2 = new WebPage[tempLength+1];
                for(int i=0; i<tempLength;i++){
                    list2[i] = cursor.getLink(i);
                    list2[i].setCapacity(tempLength+1);
                }

                list2[tempLength] = newWebPage;

                //I set variables to be used elsewhere
                list2[tempLength].setCapacity(tempLength+1);
                list2[tempLength].setIndex(tempLength);
                list2[tempLength].setLevel(list2[tempLength-1].getLevel());
                //I set the parent
                list2[tempLength].setParent(cursor);

                cursor.setLink(list2);
                //once done, I change the location of the cursor
                cursor = list2[tempLength];

            }
        }
    }



    public void removeDepartment() throws EmptyTreeException{
        if(homepage==null){
            //If the tree is empty, I throw an error
            throw new EmptyTreeException();
        } else {

        if(cursor.getLevel()==0){
            //if we are at the root of the tree
            this.homepage = null;
            this.cursor = null;
        } else {

            if ((cursor.getIndex() == 0)&&((cursor.getCapacity()-1)==0)) {
                //if I am removing the only element in the array
                cursor = cursor.getParent();
                cursor.setLink(null);
            } else {
                //I transfer values to a temp array
                WebPage[] temp = new WebPage[cursor.getCapacity() - 1];
                int length = cursor.getCapacity() - 1;
                int target = cursor.getIndex();
                int j = 0;

                cursor = cursor.getParent();

                for (int i = 0; i < length; i++) {
                    if ((i != target) && (j == 0)) {
                        temp[i] = cursor.getLink(i);
                    } else {
                        j = 1;
                        temp[i] = cursor.getLink(i + 1);
                    }
                    temp[i].setCapacity(length);
                }
                cursor.setLink(temp);
            }


        }
        }
    }


    public void printFormat() throws EmptyTreeException{

        if(homepage==null){
            //If the tree is empty, I throw an error

            throw new EmptyTreeException();
        } else {
            //I print the homepage
            System.out.print("\n" + this.homepage.getName());

            WebPage tempCursor = cursor;
            //I print whats below the homepage
            boolean truth = true;
            if (homepage.getLinks() != null) {
                cursor = homepage.getLink(0);
            } else {
                truth = false;
            }

            while (truth) {
                if (cursor.getLinks() == null) {
                    //If the cursor is at the end
                    System.out.print("\n");
                    //I print the number of tabs based on the level the cursor is on
                    for (int i = 0; i < cursor.getLevel(); i++) {
                        System.out.print("\t");
                    }
                    //I print the cursor value
                    System.out.print("-  " + cursor.getName());

                    if (cursor.getIndex() != (cursor.getCapacity() - 1)) {
                        //I go to the next spot in the array
                        cursor = cursor.getParent().getLink(cursor.getIndex() + 1);
                    } else {
                        boolean truth2 = true;
                        //I continue going to the parent till I find a value that I have not reached yet
                        while (truth2) {
                            if (cursor.getParent() != homepage) {
                                //while I have not reached the last index of the array below the homepage
                                cursor = cursor.getParent();
                                if (cursor.getIndex() != (cursor.getCapacity() - 1)) {
                                    cursor = cursor.getParent().getLink(cursor.getIndex() + 1);
                                    truth2 = false;
                                }
                            } else {
                                truth2 = false;
                                truth = false;
                            }
                        }

                    }


                } else {
                    //if the cursor is not at the end
                    System.out.print("\n");
                    //I print the number of tabs based on the level the cursor is on
                    for (int i = 0; i < cursor.getLevel(); i++) {
                        System.out.print("\t");
                    }
                    //I print the cursor value
                    System.out.print("+  " + cursor.getName());
                    cursor = cursor.getLink(0);
                }
            }


            cursor = tempCursor;
        }
    }


    public void emptyTree(){
        this.homepage = null;
        this.cursor = null;
        System.out.print("\nThe tree is now empty!");
    }

}

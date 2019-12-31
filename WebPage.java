public class WebPage {
    private String name;
    private WebPage[] link;
    private int capacity;
    private WebPage parent;
    private int index;
    private int level;

//    public WebPage(){
//
//    }

    public WebPage(){
       // parent = null;
        this.capacity = 1;
        link = new WebPage[capacity];
        index = 0;
    }

    public WebPage(String newName){
        this.name = newName;
        //link = new WebPage[capacity];

    }

    public void setName(String name){
        this.name = name;
    }

    public void setLink(WebPage[] link){
        this.link = link;
    }

    public String getName(){
        return this.name;
    }

    public WebPage[] getLinks(){
        return this.link;
    }

    public WebPage getLink(int a) {
        return this.link[a];
    }

    public void setParent(WebPage parentLink){
        this.parent = parentLink;
    }

    public WebPage getParent(){
        return this.parent;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public int getCapacity(){
        return this.capacity;
    }

    public void setIndex(int index){
        this.index = index;
    }

    public int getIndex(){
        return this.index;
    }

    public int getLevel(){
        return this.level;
    }

    public void setLevel(int level){
        this.level = level;
    }
}

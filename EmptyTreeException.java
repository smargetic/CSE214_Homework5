public class EmptyTreeException extends RuntimeException {
    public EmptyTreeException(){
        //System.out.print("\nThe tree is already empty");
        super("The tree is already empty");
    }
    public EmptyTreeException(String message){
        //System.out.print(message);
        super(message);
    }


}

import java.util.ArrayList;
public class AlternatingSplit {
    // function to split the list into two smaller lists
    public static void alternatingSplit(ArrayList<Integer> list)
    {
        // declare an array list to store list containing elements at even positions
        ArrayList<Integer> even_list = new ArrayList<>();
        // declare an array list to store list containing elements at odd positions
        ArrayList<Integer> odd_list = new ArrayList<>();

        // traverse the list and add the elements of even positions to the even_list
        for(int i=0; i < list.size(); i+=2)
            even_list.add(list.get(i));

        // traverse the list and add the elements of odd positions to the even_list
        for(int i=1; i < list.size(); i+=2)
            odd_list.add(list.get(i));

        // print the original list
        System.out.println("The elements of original list are:");
        for(int n : list)
            System.out.print(n+"   ");

        // print the list containing elements at even positions
        System.out.println("\n\nThe list containing elements at even positions is:");
        for(int n : even_list)
            System.out.print(n+"   ");

        // print the list containing elements at odd positions
        System.out.println("\n\nThe list containing elements at odd positions is:");
        for(int n : odd_list)
            System.out.print(n+"   ");
    }
    // driver code
    public static void main(String[] args)
    {
        // declare an array list of integers
        ArrayList<Integer> list = new ArrayList<>();
        // add some numbers to the list
        list.add(1); list.add(2); list.add(3); list.add(4); list.add(5);
        list.add(6);
        // call the AlternatingSplit() method to split the list
        alternatingSplit(list);
    }
}
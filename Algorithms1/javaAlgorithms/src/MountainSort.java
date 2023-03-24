import java.util.*;
public class MountainSort {
    public MountainSort(int[] A){
        int i, temp;

        for (i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] > A[j]) {
                    temp = A[i];
                    A[i] = A[j];
                    A[j] = temp;
                }
            }
        } //printing first half of the array
        System.out.print ("Sorted array: [");
        for (i = 0; i < A.length / 2; i++)  {
            System.out.print (A[i] + ", ");        }
        //printing second half of the array
        for (i = A.length - 1; i >= A.length / 2; i--) {
            System.out.print (A[i] + ", ");
        }
        System.out.print ("]");
    }

    public static void main(String[] args) {
        int[][] Tests = {
                {},
                {34, 12, 7,	43, 55, 97, 41, 28, 2, 62},
                {6, 2, 8, 5, 7, 5, 0, 2},
                {2,2,2,2,2,2,2,2},
                {4, -7, 2, 1, 0, 2, 4, 2, -3, 3, -3, 7, -2, 7, 7},
                {-2, -2, -4, -9, -1, -6, -1, -14, -3, -15, -12, -12, -2, -8, -9},
        };

        int n = Tests.length;

        for (int[] A: Tests) {
            try {
                System.out.println();
                System.out.println("Given array: " + Arrays.toString(A));
                new MountainSort(A);
                System.out.println();
            } catch (Exception e) {
                System.out.println("Error for array: "+ Arrays.toString(A));
                System.out.println(e);
            }
        }
    }
}



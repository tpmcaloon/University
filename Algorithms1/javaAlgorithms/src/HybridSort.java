import java.util.Arrays;

public class HybridSort {

    public static void main(String[] args) {

        int[][] Tests = {
                {},
                {9,8,7,6},
                {6, 2, 8, 5, 7, 5, 0, 2},
                {2,2,2,2,2,2,2,2},
                {4, -7, 2, 1, 0, 2, 4, 2, -3, 3, -3, 7, -2, 7, 7},
                {-2, -2, -4, -9, -1, -6, -1, -14, -3, -15, -12, -12, -2, -8, -9},
        };
        for (int[] A: Tests) {
            try {
                System.out.println("Given array: " + Arrays.toString(A));
                hybridSort(A); //change the name of function if necessary
                System.out.println("Array sorted: " + Arrays.toString(A));
                System.out.println();
            }   catch (Exception e) {
                System.out.println("Error for array: " + Arrays.toString(A));
                System.out.println(e);
            }
        }
    }


    public static void selectionSort(int[] A)
    {
        int small;
        for (int i = 0; i < A.length - 1; i++) {
            small = i;
            for (int j = i + 1; j < A.length; j++) {
                if (A[j] < A[small]) {
                    small = j;
                    int temp = A[i];
                    A[i] = A[small];
                    A[small] = temp;
                }
            }
        }
    }

    static void bubbleSort(int[] A) {
        int n = A.length;
        int temp = 0;
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                if(A[j-1] > A[j]){
                    //swap elements
                    temp = A[j-1];
                    A[j-1] = A[j];
                    A[j] = temp;
                }
            }
        }
    }

    public static void hybridSort(int[] A) {
        while (0 < A.length) {
            if (A.length < A.length+1) {
                bubbleSort(A);
                selectionSort(A);
                break;
            }
        }
    }
}

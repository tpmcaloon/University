import java.util.*;
import java.lang.*;
import java.io.*;

/*
 *
 */
public class Solution
{
  public static int  countOccur(String parent, String sub)
  {
    int  answer = 0;
    // Write your code here

    int P = parent.length;
    int S = sub.length;

    for (int i = 0; i <= S - P; i++) {
      int j;
      for (j = 0; j < P; j++) {
        if (sub.charAt(i + j) != parent.charAt(j)) {
          break;
        }
      }

      if (j == P) {
        answer++;
        j = 0;
      }
    }


    return answer;
  }

  public static void main(String[] args)
  {
    Scanner in = new Scanner(System.in);

    // input for parent
    String parent = in.nextLine();

    // input for sub
    String sub = in.nextLine();

    int result = countOccur(parent, sub);
    System.out.print(result);

  }
}

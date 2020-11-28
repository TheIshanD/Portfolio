import com.sun.source.tree.Tree;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
 
 
public class Problem2 {
 
    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        //--------------------------START CODING HERE-----------------------------
 
        int t = Integer.parseInt(f.readLine());
 
        while(t-->0)
        {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
 
 
 
            int evenNums = 0;
            int oddNums = 0;
 
            st = new StringTokenizer(f.readLine());
            while(n-->0)
            {
                if(Integer.parseInt(st.nextToken())%2==0)evenNums++;
                else oddNums++;
            }
 
            if(oddNums > 0) {
                x--;
                oddNums--;
                String toPrint = "YES";
                for (int i = 0; i < x; i += 0) {
                    if (oddNums >= 2 && (i + 2) <= x) {
                        oddNums -= 2;
                        i += 2;
                    } else if (evenNums >= 1) {
                        evenNums--;
                        i += 1;
                    } else {
                        toPrint = "NO";
                        break;
                    }
                }
                out.println(toPrint);
            }else{
                out.println("NO");
            }
 
 
        }
 
        out.close();
    }
}

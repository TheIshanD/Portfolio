import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
 
public class Problem2 {
    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        //--------------------------START CODING HERE-----------------------------
 
        StringTokenizer st = new StringTokenizer(f.readLine());
        int side = Integer.parseInt(st.nextToken());
        int xcd = Integer.parseInt(st.nextToken());
        int ycd = Integer.parseInt(st.nextToken());
 
        if(!(ycd == side/2 && xcd == side/2) && !(ycd == side/2 +1 && xcd == side/2 +1) &&!(ycd == side/2 +1&& xcd == side/2) &&!(ycd == side/2 && xcd == side/2+1))
        {
            out.print("YES");
        }
        else
            {
                out.print("NO");
            }
 
 
        out.close();
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
 
public class Problem1 {
    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        //--------------------------START CODING HERE-----------------------------
 
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
 
        if(N%2 ==0)
        {
            if(N <= 3)
            {
                System.out.println("No");
            }
            else
                {
                    if((N -2) % 2 == 0)
                    {
                        System.out.println("YES");
                    }
                    else
                    {
                        System.out.println("No");
                    }
                }
 
        }
        else out.print("No");
        out.close();
    }
}

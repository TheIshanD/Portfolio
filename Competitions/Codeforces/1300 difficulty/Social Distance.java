import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
 
public class Problem6 {
 
    public static int findDivisor(int n)
    {
 
        for(int i = 2; i <= n; i++)
        {
            if(n%i == 0)
            {
                return i;
            }
        }
        return n;
    }
    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        //--------------------------START CODING HERE-----------------------------
 
        StringTokenizer st = new StringTokenizer(f.readLine());
 
        int t = Integer.parseInt(st.nextToken());
 
        while(t-- > 0)
        {
            st = new StringTokenizer(f.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
 
            String tables = f.readLine();
 
            int l =tables.length();
            int tablesPut = 0;
            int next1Index = -2;
            for(int a = 0; a < l; a++)
            {
                if(tables.charAt(a)=='1')
                {
                    a=a+k;
                }else{
 
                    if(a > next1Index && next1Index != -1)
                    {
                        next1Index=tables.indexOf("1",a);
                    }
                    if(next1Index < 0 || next1Index - k > a)
                    {
                        a=a+k;
                        tablesPut++;
                    }else
                    {
                        a = next1Index-1;
                    }
                }
            }
 
            out.println(tablesPut);
        }
 
 
        out.close();
    }
}

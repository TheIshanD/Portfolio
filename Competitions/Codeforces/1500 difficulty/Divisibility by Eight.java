import javax.print.attribute.SupportedValuesAttribute;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;
 
public class Main{
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskB solver = new TaskB();
        solver.solve(in, out);
        out.close();
    }
 
    static class TaskB {
 
 
        public static int binarySearch(int arr[], int low, int high, int key)
        {
            int mid = (low + high)/2;
            while( low <= high )
            {
                if ( arr[mid] < key )
                {
                    low = mid + 1;
                }
                else if ( arr[mid] == key )
                {
                    return mid;
                }else
                {
                    high = mid - 1;
                }
                mid = (low + high)/2;
            }
            if( low > high )
            {
                return -1;
            }
            return -1;
        }
 
        public static int[][] maxPos;
 
        public void solve(InputReader in, PrintWriter out) {
            String s = in.next();
            int len = s.length();
 
            for(int i = 0; i < len; i++)
            {
                if((s.charAt(i)-'0') % 8 == 0)
                {
                    out.println("YES");
                    out.println(s.substring(i, i+1));
                    return;
                }
            }
            for(int i = 0; i < len; i++)
            {
                for(int g = 0; g < i; g++)
                {
                    String x = s.substring(g, g + 1)+s.substring(i, i + 1) ;
                    if(Integer.parseInt(x)%8 == 0)
                    {
                        out.println("YES");
                        out.println(x);
                        return;
                    }
                }
            }
            for(int i = 0; i < len; i++)
            {
                for(int g = 0; g < i; g++)
                {
                    for(int l = 0; l < g; l++)
                    {
                        String x = s.substring(l, l + 1)+s.substring(g, g + 1)  + s.substring(i, i + 1);
                        if(Integer.parseInt(x)%8 == 0)
                        {
                            out.println("YES");
                            out.println(x);
                            return;
                        }
                    }
                }
            }
            out.println("NO");
 
        }
    }
 
 
    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer = new StringTokenizer("");
 
        public InputReader(InputStream inputStream) {
            this.reader = new BufferedReader(
                    new InputStreamReader(inputStream));
        }
 
        public String next() {
            while (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }
 
        public int nextInt() {
            return Integer.parseInt(next());
        }
 
        public long nextLong() {
            return Long.parseLong(next());
        }
 
        public int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }
 
    }
}

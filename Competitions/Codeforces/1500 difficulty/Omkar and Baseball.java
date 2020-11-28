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
            int T = in.nextInt();
            while(T-->0)
            {
                int n = in.nextInt();
                int startInd = 0;
                int endInd = n-1;
 
                int[] arr = in.nextIntArray(n);
 
                for(int i = 1; i <= n; i++)
                {
                    startInd = i-1;
                    if(arr[i-1]!=i)
                    {
                        break;
                    }
                }
 
                if(startInd==n-1)
                {
                    out.println(0);
                    continue;
                }
 
                for(int i = n-1; i >= 0; i--)
                {
                    endInd = i;
                    if(arr[i]!=i+1)
                    {
                        break;
                    }
                }
 
                boolean allWrong = true;
                for(int i = startInd; i <= endInd; i++)
                {
                    if(arr[i]==(i+1))
                    {
                        allWrong = false;
                        break;
                    }
                }
                if(allWrong)
                {
                    out.println(1);
                    continue;
                }
 
                out.println(2);
 
            }
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

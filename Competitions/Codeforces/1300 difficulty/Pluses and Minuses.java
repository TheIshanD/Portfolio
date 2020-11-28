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
            int n = in.nextInt();
            for(int i = 0; i < n; i++)
            {
                String str = in.next();
                char[] Chars = str.toCharArray();
                int value = 0;
                int minVal = 0;
                ArrayList<Long> minIndex = new ArrayList<>();
                ArrayList<Long> minVals = new ArrayList<>();
                for(int g = 0; g < Chars.length; g++)
                {
                    if(Chars[g]=='+')
                    {
                        value++;
                    }else
                        {
                            value--;
                        }
 
                    if(value < minVal)
                    {
                        minVal = value;
                        minIndex.add((long) (g+1));
                        minVals.add((long) minVal);
                    }
 
                }
 
                long score = 0;
                long toAdd = 0;
                for(int g = 0; g < minIndex.size(); g++)
                {
                    if(minVals.get(g)+toAdd >= 0)
                    {
 
                    }else{
                        score+=minIndex.get(g);
                    }
                    toAdd++;
                }
 
                out.println(score+Chars.length);
 
 
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

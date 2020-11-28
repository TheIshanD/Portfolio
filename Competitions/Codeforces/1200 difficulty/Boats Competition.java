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
                    if(mid+1 != arr.length && arr[mid+1]>key)
                    {
                        return mid;
                    }
                    low = mid + 1;
                }
                else
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
 
 
        public void solve(InputReader in, PrintWriter out) {
            int T = in.nextInt();
            for(int s = 0; s < T; s++)
            {
                int n = in.nextInt();
 
                int[] arr = in.nextIntArray(n);
 
                int maxCount = 0;
                for(int w = 2; w < 101; w++)
                {
                    int count = 0;
                    boolean taken[] = new boolean[n];
                    for(int i = 0; i < n; i++)
                    {
                        for(int g = i+1; g < n; g++)
                        {
                            if((!taken[g]) && (!taken[i]) && (arr[g] + arr[i] == w))
                            {
                                count++;
                                taken[g]=true;
                                taken[i]=true;
                                break;
                            }
                        }
                    }
                    maxCount = Math.max(count, maxCount);
                }
                out.println(maxCount);
            }
        }
    }
 
    static void sort(int[] a)
    {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i: a)list.add(i);
        Collections.sort(list);
        for(int i = 0; i < a.length; i++)a[i]=list.get(i);
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

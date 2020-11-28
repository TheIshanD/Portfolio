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
 
        public boolean checkSet(String s1, String s2, String s3, int k)
        {
            for(int i = 0; i < k; i++)
            {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(i);
                char c3 = s3.charAt(i);
 
                if(c1==c2)
                {
                    if(c2!=c3)return false;
                }else{
                    if(c2==c3)return false;
                }
            }
            return true;
        }
 
 
        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            for(int i = 0; i < n; i++)
            {
                int l = in.nextInt();
                String str = in.next();
 
                l = str.length();
                if(str.contains("0") && str.contains("1"))
                {
                    int first1 = str.indexOf('1');
 
                    String zeros = str.substring(0, first1);
 
                    int last0 = str.lastIndexOf('0');
 
                    String ones = str.substring(last0+1);
 
                    if(ones.length()+zeros.length() == l)
                    {
                        out.println(str);
                        continue;
                    }
                    out.println(zeros+"0"+ones);
                }else{
                    out.println(str);
                }
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

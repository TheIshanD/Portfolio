import javax.management.Query;
import javax.print.attribute.SupportedValuesAttribute;
import java.io.*;
import java.lang.reflect.Array;
import java.security.interfaces.DSAKeyPairGenerator;
import java.util.*;
import java.util.function.Predicate;
 
 
public class Main {
 
    static class TaskB {
 
        static int oo = Integer.MAX_VALUE - 1;
 
        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
 
            int[] arr1 = in.nextIntArray(n);
            int[] arr2 = in.nextIntArray(n);
 
            Pair[] arr = new Pair[n];
 
            for(int i = 0; i < n; i++)
            {
                arr[i] = new Pair(arr1[i], arr2[i]);
            }
            Arrays.sort(arr);
 
            long ans = 0;
            int counter = 0;
            while(true)
            {
                if(counter >= arr.length)break;
                if(arr[counter].num1-arr[counter].num2<=0)
                {
                    ans+=arr[counter].num1;
                    counter++;
                }else{
                    break;
                }
            }
            for(; counter < k; counter++)
            {
                ans+=arr[counter].num1;
            }
            for(; counter < n; counter++)
            {
                ans+=arr[counter].num2;
            }
            out.println(ans);
        }
    }
 
    static class Pair implements Comparable
    {
        int num1, num2;
        Pair(int num1, int num2)
        {
            this.num1 = num1;
            this.num2 = num2;
        }
 
        @Override
        public int compareTo(Object o) {
            if(this.num1-this.num2 < ((Pair) o).num1- ((Pair) o).num2)
            {
                return -1;
            }else if(this.num1-this.num2 > ((Pair) o).num1- ((Pair) o).num2){
                return 1;
            }else{
                return 0;
            }
        }
        
 
    }
 
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskB solver = new TaskB();
        solver.solve(in, out);
        out.close();
    }
 
 
 
    static void sort(long[] a)
    {
        ArrayList<Long> list = new ArrayList<>();
        for(Long i: a)list.add(i);
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

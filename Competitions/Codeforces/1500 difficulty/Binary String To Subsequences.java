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
 
                String str = in.next();
 
                ArrayList<Integer> looking0 = new ArrayList<>();
                ArrayList<Integer> looking1 = new ArrayList<>();
                ArrayList<Integer> order = new ArrayList<>();
 
                int counter = 0;
                for(int i = 0; i < str.length(); i++)
                {
                    char c = str.charAt(i);
 
                    if(c=='0')
                    {
                        if(looking0.size()==0)
                        {
                            counter++;
                            looking1.add(counter);
                            order.add(counter);
                        }else{
                            int num = looking0.remove(0);
                            looking1.add(num);
                            order.add(num);
                        }
                    }else{
                        if(looking1.size()==0)
                        {
                            counter++;
                            looking0.add(counter);
                            order.add(counter);
                        }else{
                            int num = looking1.remove(0);
                            looking0.add(num);
                            order.add(num);
                        }
                    }
                }
                out.println(counter);
                for(int i = 0; i < order.size(); i++)
                {
                    out.print(order.get(i)+" ");
                }
                out.println();
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

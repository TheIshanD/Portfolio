import javax.management.Query;
import javax.print.attribute.SupportedValuesAttribute;
import java.io.*;
import java.security.interfaces.DSAKeyPairGenerator;
import java.sql.Array;
import java.util.*;
import java.util.function.Predicate;
 
 
public class Main {
 
    static class TaskB {
 
        static int oo = Integer.MAX_VALUE - 1;
 
        static int MOD = 1000000007;
 
 
        boolean[] visited;
        int n;
        LinkedList<Integer>[] arr;
        boolean[] colors;
 
        void dfs(int v, boolean pColor)
        {
            visited[v] = true;
            colors[v]=!pColor;
            for(int i = 0; i < arr[v].size(); i++)
            {
                if(!visited[arr[v].get(i)])
                {
                    dfs(arr[v].get(i), colors[v]);
                }
            }
        }
 
        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] pos = new int[n];
            int[] height = new int[n];
 
            int cur = 1;
            int ans = 1;
            int leftPos = in.nextInt();
            in.nextInt();
 
            if(n==1 || n==2)
            {
                out.print(n);
                return;
            }
            int currentPos = in.nextInt();
            int currentHeight = in.nextInt();
 
 
            while(n-2>0)
            {
                int rightPos = in.nextInt();
                int rightHeight = in.nextInt();
 
                if(leftPos<(currentPos-currentHeight))
                {
                    ans++;
                    leftPos = currentPos;
                }else if(rightPos>(currentPos+currentHeight))
                {
                    ans++;
                    leftPos = currentPos+currentHeight;
                }else{
                    leftPos = currentPos;
                }
 
                currentHeight = rightHeight;
                currentPos = rightPos;
                n--;
            }
            out.print(ans+1);
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
 
 
 
    static void sort(int[] a)
    {
        ArrayList<Integer> list = new ArrayList<>();
        for(Integer i: a)list.add(i);
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

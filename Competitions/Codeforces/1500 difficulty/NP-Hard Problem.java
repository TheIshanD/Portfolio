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
            n = in.nextInt();
            int m = in.nextInt();
            arr = new LinkedList[n];
            colors = new boolean[n];
            visited = new boolean[n];
 
            for(int i = 0; i < n; i++)
            {
                arr[i]= new LinkedList<>();
            }
 
            for(int i = 0; i<m; i++)
            {
                int first = in.nextInt() - 1;
                int second = in.nextInt() -1;
                arr[first].add(second);
                arr[second].add(first);
            }
 
            for(int i = 0; i < n; i++)
            {
                if(!visited[i])
                {
                    dfs(i, true);
                }
            }
 
            ArrayList<Integer> col1 = new ArrayList<>();
            ArrayList<Integer> col2 = new ArrayList<>();
            for(int i = 0; i < n; i++)
            {
                for(int g = 0; g < arr[i].size(); g++)
                {
                    if(colors[arr[i].get(g)]==colors[i])
                    {
                        out.println(-1);
                        return;
                    }
                }
                if(colors[i])col1.add(i);
                else col2.add(i);
            }
 
            out.println(col1.size());
            for(int i = 0; i < col1.size(); i++)
            {
                out.print((col1.get(i)+1)+" ");
            }
            out.println();
            out.println(col2.size());
            for(int i = 0; i < col2.size(); i++)
            {
                out.print((col2.get(i)+1)+" ");
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

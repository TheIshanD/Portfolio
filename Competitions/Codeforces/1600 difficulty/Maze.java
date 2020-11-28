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
 
 
        int k;
        int cnt = 0;
        char[][] mat;
        boolean[][] visited;
        int n, m;
 
        void dfs(int row, int col , boolean[][] visited)
        {
 
            if(row>=n || col>=m || row<0 || col <0)
            {
                return;
            }
            if(visited[row][col])return;
            visited[row][col]=true;
            if(mat[row][col]=='#')return;
            if(cnt==k)
            {
                mat[row][col]='X';
            }
            else{
                cnt++;
            }
            dfs(row+1, col, visited);
            dfs(row, col-1, visited);
            dfs(row, col+1, visited);
            dfs(row-1, col, visited);
        }
        public void solve(InputReader in, PrintWriter out) {
            n = in.nextInt();
            m = in.nextInt();
            k = in.nextInt();
 
            mat = new char[n][m];
            visited = new boolean[n][m];
 
            boolean foundDot = false;
            int startRow =0, startCol=0;
            int dots = 0;
            for(int i = 0; i < n; i++)
            {
                mat[i] = in.next().toCharArray();
 
                    for(int g = 0; g < m; g++)
                    {
                        if(mat[i][g]=='.')
                        {
                            dots++;
                            startRow = i;
                            startCol = g;
                        }
                    }
            }
            k=(dots-k);
 
            dfs(startRow, startCol, visited);
 
            for(int i = 0; i < n; i++)
            {
                for(int g = 0; g < m; g++)
                {
                    out.print(mat[i][g]);
                }
                out.println();
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

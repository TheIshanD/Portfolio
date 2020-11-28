import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
 
public class Main {
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
        public void solve(InputReader in, PrintWriter out) {
            int T = in.nextInt();
            for(int j = 0; j < T; j++)
            {
                int n = in.nextInt();
 
                String str1 = "";
                for(int g = 0; g < 200; g++)
                {
                    str1+="a";
                }
                String base = str1;
                out.println(str1);
                String str2;
                for(int i = 1; i <= n; i++)
                {
                    int a = in.nextInt();
                    str2 = str1.substring(0,a);
                    if(str1.charAt(a)=='z')
                    {
                        str2 += Character.toString(str1.charAt(a)-1);
                    }else{
                        str2 += Character.toString(str1.charAt(a)+1);
                    }
                    str2+=str1.substring(a+1);
                    out.println(str2);
                    str1 = str2;
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

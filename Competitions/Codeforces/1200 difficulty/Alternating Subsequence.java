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
            int t = in.nextInt();
            while (t-- > 0) {
                int n = in.nextInt();
                int[] a = in.nextIntArray(n);
 
                if (a.length > 1) {
                    long sum = 0;
                    for (int i = 0; i < n; i++) {
                        boolean isPos;
 
                        if (a[i] >= 0) isPos = true;
                        else isPos = false;
 
                        int max = a[i];
                        for (int k = i; k < n; k++) {
                            if (a[k] > 0 && !isPos) {
                                isPos = !isPos;
                                sum += max;
                                i = k - 1;
                                break;
                            } else if (a[k] < 0 && isPos) {
                                isPos = !isPos;
                                sum += max;
                                i = k - 1;
                                break;
                            }
                            max = Math.max(max, a[k]);
                            if(k == n-1){
                                i = n;
                                sum+=max;
                            }
                        }
 
                    }
                    out.println(sum);
 
                }else{
                    out.println(a[0]);
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
 
        public int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }
 
    }
}

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
            int numB = in.nextInt();
            int[] boys = in.nextIntArray(numB);
            int numG = in.nextInt();
            int[] girls = in.nextIntArray(numG);
 
            int[] boySkills = new int[102];
 
            Arrays.sort(girls);
 
            for(int i = 0; i < numB; i++)
            {
                boySkills[boys[i]]++;
            }
 
            int counter = 0;
 
            for(int i = 0; i < numG; i++)
            {
                if(boySkills[girls[i]-1]>0)
                {
                    boySkills[girls[i]-1]--;
                    counter++;
                }
                else if(boySkills[girls[i]]>0)
                {
                    boySkills[girls[i]]--;
                    counter++;
                }
                else if(boySkills[girls[i]+1]>0)
                {
                    boySkills[girls[i]+1]--;
                    counter++;
                }
            }
            out.print(counter);
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

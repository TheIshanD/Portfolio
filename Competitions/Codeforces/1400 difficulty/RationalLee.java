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
            int T = in.nextInt();
            for(int s = 0; s < T; s++)
            {
                int n = in.nextInt();
                int k = in.nextInt();
 
                int[] nums = in.nextIntArray(n);
                int[] friends = in.nextIntArray(k);
 
                sort(nums);
                sort(friends);
 
                int lowInd = 0;
                int highInd = n-1;
 
                long score = 0;
 
                int boost = 0;
                for(int i = 0; i < k; i++)
                {
                    if(friends[i]==1){
                        score += 2*nums[highInd];
                        highInd--;
                        boost ++;
                    }else if(friends[i]==2)
                    {
                        score += nums[highInd];
                        highInd--;
                        score += nums[highInd];
                        highInd--;
                        boost++;
                    }else{
                        break;
                    }
                }
 
                for(int i = k-1; i >=boost; i--)
                {
                    score+=nums[lowInd];
                    lowInd+=friends[i]-1;
                    score+=nums[highInd];
                    highInd--;
                }
                out.println(score);
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

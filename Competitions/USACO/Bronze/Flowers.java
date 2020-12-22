import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class ProblemTwo {

    public static void ProblemTwo(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int cnt = 0;
        int n = in.nextInt();
        in.nextLine();

        int[] petalArr = new int[n];

        for (int i = 0; i < n; i++)
        {
            petalArr[i] = in.nextInt();
        }

        for (int i = 0; i < n; i++)
        {
            for (int g = i + 1; g <= n; g++)
            {
                double sumPetals = 0.0;
                for(int y = i; y < g; y++)
                {
                    sumPetals += petalArr[y];
                }

                double avgPetals = sumPetals /(g - i);

                for (int y = i; y < g; y++)
                {
                    if (petalArr[y] == avgPetals)
                    {
                        cnt++;
                        break;
                    }
                }
            }
        }
        System.out.print(cnt);
    }
}

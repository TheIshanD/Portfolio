import java.util.*;

public class SilverProblemThree {

    public static void EffcientCode(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();

        ArrayList<Cow> eastList = new ArrayList<>();
        ArrayList<Cow> northList = new ArrayList<>();
        ArrayList<Cow> order = new ArrayList<>();

        for(int i =0; i<n;i++){
            String direction = in.next();
            Cow tempCow = new Cow(direction, in.nextInt(), in.nextInt());

            if(direction.equals("N"))
            {
                northList.add(tempCow);
            }
            else{
                eastList.add(tempCow);
            }

            order.add(tempCow);
        }

        Collections.sort(eastList, new xPosComparator());
        Collections.sort(northList, new xPosComparator());

        for (Cow northCow : northList) {
            ArrayList<Cow> onLeftSorted = new ArrayList<>();

            for (Cow checkCow : eastList) {
                if (checkCow.xPos >= northCow.xPos) {
                    break;
                }
                if (checkCow.time == -1) {
                    onLeftSorted.add(checkCow);
                }
            }

            Collections.sort(onLeftSorted, new yPosComparator());

            for (Cow eastCow : onLeftSorted) {
                if (eastCow.yPos < northCow.yPos) {
                    continue;
                }

                int horizontalDistance = northCow.xPos - eastCow.xPos;
                int verticalDistance = eastCow.yPos - northCow.yPos;

                if (horizontalDistance == verticalDistance) {
                    continue;
                }

                if (horizontalDistance > verticalDistance) {
                    //Kill east cow
                    eastCow.time = horizontalDistance;
                    eastCow.stopped += northCow.stopped - 1;
                    continue;
                }

                northCow.time = verticalDistance;
                break;
            }
        }
        for(Cow cow : order)
        {
            System.out.println(cow.getTime());
        }
    }

    public static class Cow
    {
        public int xPos;
        public int yPos;
        public String direction;
        public int time = -1;
        public int stopped = 0;

        Cow(String d, int x, int y)
        {
            xPos = x;
            yPos = y;
            direction = d;
        }

        public String getTime()
        {
            if(time == -1)
                return "Infinity";
            else
                return Integer.toString(time);
        }
    }

    static class xPosComparator implements Comparator{

        @Override
        public int compare(Object o1, Object o2) {
            Cow self = (Cow) o1;
            Cow other = (Cow) o2;

            if(self.xPos < other.xPos)
            {
                return -1;
            }else{
                return 1;
            }
        }
    }

    static class yPosComparator implements Comparator{

        @Override
        public int compare(Object o1, Object o2) {
            Cow self = (Cow) o1;
            Cow other = (Cow) o2;

            if(self.yPos < other.yPos)
            {
                return -1;
            }else{
                return 1;
            }
        }
    }
}

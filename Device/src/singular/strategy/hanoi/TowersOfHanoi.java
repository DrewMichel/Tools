package singular.strategy.hanoi;

/**
 * Created by Andrew Michel on 10/15/2017.
 *
 *
 */
public class TowersOfHanoi
{
    public static final int DEFAULT_NUMBER_OF_DISKS = 5,
                            DEFAULT_NUMBER_OF_TOWERS = 3,
                            DEFAULT_EMPTY_VALUE = 0,
                            DEFAULT_SPACING = 5;

    private int[][] towers;

    private MutableInteger movesWrapper;

    private int numberOfDisks;


    public TowersOfHanoi()
    {
        this.numberOfDisks = DEFAULT_NUMBER_OF_DISKS;
        this.towers = new int[DEFAULT_NUMBER_OF_TOWERS][numberOfDisks];
        populateTowers();
        movesWrapper = new MutableInteger();
    }

    public TowersOfHanoi(int numberOfDisks)
    {
        this.numberOfDisks = numberOfDisks;
        this.towers = new int[DEFAULT_NUMBER_OF_TOWERS][numberOfDisks];
        populateTowers();
        movesWrapper = new MutableInteger();
    }

    public void reset()
    {
        movesWrapper.reset();
        populateTowers();
    }

    public int getNumberOfMoves()
    {
        return movesWrapper.numberOfMoves;
    }

    private void populateTowers()
    {
        for(int i = 0; i < towers[0].length; i++)
        {
            towers[0][i] = i + 1;
        }

        for(int i = 1; i < towers.length; i++)
        {
            for(int k = 0; k < towers[i].length; k++)
            {
                towers[i][k] = DEFAULT_EMPTY_VALUE;
            }
        }
    }

    public void displayTowers()
    {
        for(int i = 0; i < towers[0].length; i++)
        {
            for(int k = 0; k < towers.length; k++)
            {
                System.out.printf("%" + DEFAULT_SPACING + "d", towers[k][i]);
            }

            System.out.println();
        }
    }

    public void solve()
    {
        reset();
        hanoi(towers[0], towers[1], towers[2], numberOfDisks, movesWrapper);
    }

    private static void move(int[] start, int[] end, MutableInteger moves)
    {
        int diskValue = 0, diskIndex = 0, placementIndex = 0;

        boolean found = false, placed = true;

        for(int i = 0; i < start.length && found == false; i++)
        {
            if(start[i] != DEFAULT_EMPTY_VALUE)
            {
                found = true;
                diskIndex = i;
                diskValue = start[i];
            }
        }

        for(int i = 0; i < end.length; i++)
        {
            if(end[i] == DEFAULT_EMPTY_VALUE)
            {
                placed = true;
                placementIndex = i;
            }
        }

        if(placed == true)
        {
            end[placementIndex] = diskValue;
            start[diskIndex] = DEFAULT_EMPTY_VALUE;
            moves.increment();
        }
    }

    private static void hanoi(int[] first, int[] middle, int[] last, int disks, MutableInteger moves)
    {
        if(disks > 0)
        {
            hanoi(first, last, middle, disks - 1, moves);

            move(first, last, moves);

            hanoi(middle, first, last, disks - 1, moves);
        }
    }

    private static class MutableInteger
    {
        private int numberOfMoves;

        public MutableInteger()
        {
            numberOfMoves = 0;
        }

        public MutableInteger(int numberOfMoves)
        {
            this.numberOfMoves = numberOfMoves;
        }

        public int getNumberOfMoves()
        {
            return numberOfMoves;
        }

        public void setNumberOfMoves(int numberOfMoves)
        {
            this.numberOfMoves = numberOfMoves;
        }

        public void increment()
        {
            this.numberOfMoves++;
        }

        public void reset()
        {
            this.numberOfMoves = 0;
        }
    }
}
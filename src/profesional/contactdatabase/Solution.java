package profesional.contactdatabase;

import java.util.Scanner;

/**
 * Created by Wendy P on 5/17/17.
 */

public class Solution {

    private static final int CMD_INIT = 0;
    private static final int CMD_ADD = 1;
    private static final int CMD_DELETE = 2;
    private static final int CMD_CHANGE = 3;
    private static final int CMD_SEARCH = 4;

    static Scanner sc = new Scanner(System.in);

    static int [] dummy = new int[100];
    static int Score, ScoreIdx;
    static char[] name = new char[20], number= new char[20], birthday = new char[20], email = new char[20], memo = new char[20];

    static char[][] lastname = { "kim".toCharArray(), "lee".toCharArray(), "park".toCharArray(), "choi".toCharArray(), "jung".toCharArray(), "kang".toCharArray(), "cho".toCharArray(), "oh".toCharArray(), "jang".toCharArray(), "lim".toCharArray() };
    static int[] lastname_length = { 3, 3, 4, 4, 4, 4, 3, 2, 4, 3 };

//    static UserSolution userSolution = new UserSolution();
    static UserSolutionUsingVector userSolution = new UserSolutionUsingVector();

    public static class RESULT
    {
        public int count;
        public char[] str = new char[20];
    };

    static int mSeed;
    static int mrand(int num)
    {
        mSeed = mSeed * 1103515245 + 37209;
        if (mSeed < 0) mSeed *= -1;
        return ((mSeed >> 8) % num);
    }

    static void make_field(int seed)
    {
        name = new char[20];
        number= new char[20];
        birthday = new char[20];
        email = new char[20];
        memo = new char[20];

        int name_length, email_length, memo_length;
        int idx, num;

        mSeed = (int)seed;

        name_length = 6 + mrand(10);
        email_length = 10 + mrand(10);
        memo_length = 5 + mrand(10);

        int a = 97;
        int zero = 48;
        num = mrand(10);
        for (idx = 0; idx < lastname_length[num]; idx++) name[idx] = lastname[num][idx];
        for (; idx < name_length; idx++) name[idx] = (char)(a + mrand(26));
        name[idx] = 0;

        for (idx = 0; idx < memo_length; idx++) memo[idx] = (char)(a + mrand(26));
        memo[idx] = 0;

        for (idx = 0; idx < email_length - 6; idx++) email[idx] = (char)(a + mrand(26));
        email[idx++] = '@';
        email[idx++] = (char)(a + mrand(26));
        email[idx++] = '.';
        email[idx++] = 'c';
        email[idx++] = 'o';
        email[idx++] = 'm';
        email[idx] = 0;

        idx = 0;
        number[idx++] = '0';
        number[idx++] = '1';
        number[idx++] = '0';
        for (; idx < 11; idx++) number[idx] = (char)(zero + mrand(10));
        number[idx] = 0;

        idx = 0;
        birthday[idx++] = '1';
        birthday[idx++] = '9';
        num = mrand(100);
        birthday[idx++] = (char) ('0' + num / 10);
        birthday[idx++] = (char) ('0' + num % 10);
        num = 1 + mrand(12);
        birthday[idx++] = (char) ('0' + num / 10);
        birthday[idx++] = (char) ('0' + num % 10);
        num = 1 + mrand(30);
        birthday[idx++] = (char) ('0' + num / 10);
        birthday[idx++] = (char) ('0' + num % 10);
        birthday[idx] = 0;

        // debug!
        //System.out.format("%s %s %s %s %s\n", new String(name), new String(number), new String(birthday), new String(email), new String(memo));
    }

    static void cmd_init()
    {
        ScoreIdx = sc.nextInt();

        userSolution.InitDB();
    }

    static void cmd_add()
    {
        int seed;
        seed = sc.nextInt();

        make_field(seed);

        userSolution.Add(name, number, birthday, email, memo);
    }

    static void cmd_delete()
    {
        int field, check, result;
        char[] str = new char[20];
        field  = sc.nextInt();
        str  = sc.next().toCharArray();
        check  = sc.nextInt();

        result = userSolution.Delete(field, str);
        if (result != check) {
            Score -= ScoreIdx;
        }
    }

    static void cmd_change()
    {
        int field, changefield, check, result;
        char[] str = new char[20], changestr = new char[20];
        field  = sc.nextInt();
        str  = sc.next().toCharArray();
        changefield  = sc.nextInt();
        changestr  = sc.next().toCharArray();
        check  = sc.nextInt();

        result = userSolution.Change(field, str, changefield, changestr);
        System.out.println("result - " + result + " " + check);
        if (result != check) {
            Score -= ScoreIdx;
        }
    }

    static void cmd_search() {
        int field, returnfield, check;
        char[] str = new char[20], checkstr = new char[20];
        field = sc.nextInt();
        str = sc.next().toCharArray();
        returnfield = sc.nextInt();
        checkstr = sc.next().toCharArray();
        check = sc.nextInt();
        RESULT result = userSolution.Search(field, str, returnfield);

        if (result.count != check || (result.count == 1 && (new String(checkstr).compareTo(new String(result.str)) != 0))) {
            Score -= ScoreIdx;
        }
    }


    static void run()
    {
        int N;
        N = sc.nextInt();
        for (int i = 0; i < N; i++)
        {
//            if(i == 42) break;
//            if(i == 42) {
//                System.out.println();
//            }
            int cmd;
            cmd = sc.nextInt();
            switch (cmd)
            {
                case CMD_INIT:   cmd_init();   break;
                case CMD_ADD:    cmd_add();    break;
                case CMD_DELETE: cmd_delete(); break;
                case CMD_CHANGE: cmd_change(); break;
                case CMD_SEARCH: cmd_search(); break;
                default: break;
            }
//            System.out.println(Score);
        }
    }

    static void init()
    {
        Score = 1000;
        ScoreIdx = 1;
    }

    public static void main(String[] args)
    {

        int T;
        T = sc.nextInt();

        int TotalScore = 0;
        for (int tc = 1; tc <= T; tc++)
        {
            init();

            run();

            if (Score < 0)
                Score = 0;

            TotalScore += Score;
            System.out.format("#%d %d%n", tc, Score);
        }
        System.out.format("TotalScore = %d%n", TotalScore);
    }

}

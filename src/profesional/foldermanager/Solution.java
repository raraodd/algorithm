package profesional.foldermanager;

import java.util.Scanner;

/**
 * Created by SRIN on 3/2/2017.
 */
public class Solution {
    private static final int CMD_ADD = 1;
    private static final int CMD_MOVE = 2;
    private static final int CMD_INFECT = 3;
    private static final int CMD_RECOVER = 4;
    private static final int CMD_REMOVE = 5;

    private static Scanner sc;
    private static UserSolution userSolution = new UserSolution();

    private static int stopN = 15;

    private static int run() {
        int score = 0;
        int N = Integer.parseInt(sc.next());

        for (int i=0; i<N; i++) {
            if(i == stopN) break;
            int cmd = Integer.parseInt(sc.next());
            int ret = 0;

            switch (cmd) {
                case CMD_ADD: {
                    int id = Integer.parseInt(sc.next());
                    int pid = Integer.parseInt(sc.next());
                    int fileSize = Integer.parseInt(sc.next());
                    ret = userSolution.add(id, pid, fileSize);
                    break;
                }
                case CMD_MOVE: {
                    int id = Integer.parseInt(sc.next());
                    int pid = Integer.parseInt(sc.next());
                    ret = userSolution.move(id, pid);
                    break;
                }
                case CMD_INFECT: {
                    int id = Integer.parseInt(sc.next());
                    ret = userSolution.infect(id);
                    break;
                }
                case CMD_RECOVER: {
                    int id = Integer.parseInt(sc.next());
                    ret = userSolution.recover(id);
                    break;
                }
                case CMD_REMOVE: {
                    int id = Integer.parseInt(sc.next());
                    ret = userSolution.remove(id);
                    break;
                }
            }

            int checkSum = Integer.parseInt(sc.next());
            if (ret == checkSum) score++;
        }
        return score;
    }

    public static void main (String args[]) throws Exception {
        sc = new Scanner(System.in);

        int totalScore = 0;

        int T = sc.nextInt();
        for(int t=1; t<=T; t++) {
            userSolution.init();
            int score = run();
            System.out.println("#" + t + " " + score);
            totalScore += score;
        }
        sc.close();
        System.out.println("Total Score : " + totalScore);
    }

}

package profesional.robotvacuum;

import java.util.Scanner;

class Solution {	
	private final static int GOFORWARD  		= 0;
	private final static int TURNRIGHT			= 1;
	
	private final static int MAXN				= 200;
	private final static int MAX_CALLCOUNT		= 1000000;
	
	private final static double factor          = 1.0; // it will be changed in evaluation
	
	private static int N, M, P, Q;
	private final static int room[][] = new int[MAXN][MAXN];
	
	private static int cur_x, cur_y, dir;
	private static int callcount;
	
	public static class Result {
		int x, y, dir;
		
		public Result() {
			x = y = dir = 0;
		}
	}
	
	private final static int UP					= 0;
	private final static int RIGHT				= 1;
	private final static int DOWN				= 2;
	private final static int LEFT				= 3;
	
	private final static int dx[] = {  0, 1, 0, -1};
	private final static int dy[] = { -1, 0, 1,  0};
	
	private final static UserSolution usersolution = new UserSolution();
	
	private static int seed;
	
	private static int pseudo_rand() {
		seed = seed * 214013 + 2531011;
		return (seed >> 16) & 0x7fff;
	}
	
	private static boolean available(int x, int y) {
		return room[y][x] != 9 &&
			   room[y + 1][x] != 9 &&
			   room[y][x + 1] != 9 &&
			   room[y + 1][x + 1] != 9;
	}
	
	public static boolean scan(int image[][]) {
		if (callcount == MAX_CALLCOUNT)
			return false;
		
		++callcount;
		
		switch(dir) {
		case UP:
			for (int y = 0; y <= 3; ++y)
			for (int x = 0; x <= 3; ++x)
				image[y][x] = room[cur_y + y - 1][cur_x + x - 1];
			break;
		case RIGHT:
			for (int y = 0; y <= 3; ++y)
			for (int x = 0; x <= 3; ++x)
				image[y][x] = room[cur_y + x - 1][cur_x - y + 2];
			break;
		case DOWN:
			for (int y = 0; y <= 3; ++y)
			for (int x = 0; x <= 3; ++x)
				image[y][x] = room[cur_y - y + 2][cur_x - x + 2];
			break;
		case LEFT:
			for (int y = 0; y <= 3; ++y)
			for (int x = 0; x <= 3; ++x)
				image[y][x] = room[cur_y - x + 2][cur_x + y - 1];
			break;
		}
		
		return true;
	}
	
	public static boolean control(int command) {
		int nx, ny;
		
		if (callcount == MAX_CALLCOUNT)
			return false;
		
		++callcount;
		
		switch(command) {
		case GOFORWARD:
			nx = cur_x + dx[dir];
			ny = cur_y + dy[dir];
			
			if (!available(nx, ny))
				return false;
			
			cur_x = nx; cur_y = ny;
			System.out.println("RE ANS: " + cur_y + " " + cur_x + " " + dir);
			break;
		case TURNRIGHT:
			dir = (dir + 1) % 4;
			System.out.println("RE ANS: " + cur_y + " " + cur_x + " " + dir);
			break;
		default:
			
			return false;
		}

		return true;
	}
	
	private static void init_m(Scanner sc) {
		N = sc.nextInt();
		M = sc.nextInt();
		P = sc.nextInt();
		Q = sc.nextInt();
		seed = sc.nextInt();
		
		for (int y = 1; y < N - 1; ++y)
		for (int x = 1; x < N - 1; ++x)
			room[y][x] = 0;
		
		for (int k = 0; k < N; ++k)
			room[k][0] = room[0][k] = room[k][N - 1] = room[N - 1][k] = 9;
		
		for (int m = 0; m < M; ++m) {
			int b_x = pseudo_rand() % (N - 3) + 1;
			int b_y = pseudo_rand() % (N - 3) + 1;
			
			room[b_y][b_x] = room[b_y + 1][b_x]
						   = room[b_y][b_x + 1]
						   = room[b_y + 1][b_x + 1]
						   = 9;
		}
		
		for (int y = 1; y < N - 1; ++y)
		for (int x = 1; x < N - 1; ++x)
			if (room[y][x] == 0)
				room[y][x] = (pseudo_rand() % 16) < P ? 1 : 0;
		
		int room_t[][] = new int[MAXN][MAXN];
		
		for (int y = 0; y < N; ++y)
		for (int x = 0; x < N; ++x)
			room_t[y][x] = room[y][x];
		
		usersolution.init(N, room_t);
	}
	
	private static boolean run(Scanner sc) {
		init_m(sc);
		
		boolean okay = true;
		
		callcount = 0;
		
		for (int q = 0; q < Q; ++q) {
			do {
				cur_x = pseudo_rand() % (N - 3) + 1;
				cur_y = pseudo_rand() % (N - 3) + 1;
				dir   = pseudo_rand() % 4;
			} while(!available(cur_x, cur_y));
			
			if (okay) {
				System.out.println("\n\n===== findoutwhere");
				System.out.println("ANS: " + cur_y + " " + cur_x + " " + dir);
				Result answer = usersolution.findoutwhere();
				if (answer.x != cur_x || answer.y != cur_y || answer.dir != dir) {
					okay = false;
					System.out.println("SALAAAH : " + cur_y+ " " + cur_x + " " + dir);
				}
					
			}
		}
		
		return okay;
	}
	
	public static void main(String[] args) throws Exception {
		int TC;
	
		System.setIn(new java.io.FileInputStream("/Users/wendy/Documents/3_Git/algorithm/src/profesional/robotvacuum/sample_input.txt"));
		Scanner sc = new Scanner(System.in);
		TC = sc.nextInt();
        
        int totalscore = 0;
		for (int testcase = 1; testcase <= TC; ++testcase) {
			int score;
			
			if (run(sc) == true && callcount <= N * Q * factor)
				score = 100;
			else
				score = 0;
			
            System.out.println("#" + testcase + " " + score);
            totalscore += score;
		}

        System.out.println("total score = " + totalscore / TC);
		sc.close();
	}
}
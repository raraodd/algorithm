package profesional.songnoise;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class Solution {
	static final private int MAX_MUSIC_LEN = 200;
	static final private int SAMPLE_DATA_LEN = 8;
	private static int seed;
	private static int n;
	private static int max_len;
	private static int query_cnt;
	private static int music_db[][] = new int[10005][MAX_MUSIC_LEN];
	private static int music_len[] = new int[10005];
	
	private static int total_score;

	private static Scanner sc;
	private static UserSolution user = new UserSolution();

	public static void main(String[] args) throws Exception {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		System.out.println(System.currentTimeMillis());
		System.setIn(new java.io.FileInputStream("/Users/wendy/Documents/3__Project/algorithm/src/profesional/songnoise/sample_input.txt"));
		
		sc = new Scanner(System.in);

		int TC = sc.nextInt();
		total_score = 0;

		for (int t = 1; t <= TC; t++) {
			seed = sc.nextInt();
			n = sc.nextInt();
			max_len = sc.nextInt();
			query_cnt = sc.nextInt();

			int correct_ans_cnt = 0;

			user.init(n);
			make_music_db();

			for (int query = 0; query < query_cnt; query++)
			{
				if (make_sample_with_noise())
					correct_ans_cnt++;
			}
			if (correct_ans_cnt == query_cnt)
			{
				System.out.println("#"+t+" 100");
				total_score += 100;
			}
			else
			{
				System.out.println("#"+t+" 0" + " correctans:"+correct_ans_cnt + " querycnt:"+query_cnt);
			}
		}

		System.out.println("Total Score = " + total_score/TC);
		sc.close();
		now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		System.out.println(System.currentTimeMillis());
	}

	private static int pseudo_rand()
	{
		seed = seed * 214013 + 2531011;
		return (seed >> 16) & 0x7FFF;
	}
	
	private static void make_music_db()
	{
		int param[] = new int[MAX_MUSIC_LEN];
		int music_len_param;
		for (int id = 1; id <= n; id++)
		{
			music_len_param = music_len[id] = pseudo_rand() % max_len + 1;

			if (music_len[id] < SAMPLE_DATA_LEN)
				music_len_param = music_len[id] = SAMPLE_DATA_LEN;

			for (int i = 0; i < music_len[id]; i++)
			{
				param[i] = music_db[id][i] = pseudo_rand() - 16384;
			}

			user.send_music_db_data(id, music_len_param, param);
		}
	}
	
	private static boolean make_sample_with_noise()
	{
		int param[] = new int[SAMPLE_DATA_LEN];

		int music_id = pseudo_rand() % n + 1;
		int offset = pseudo_rand() % music_len[music_id];

		if (offset + SAMPLE_DATA_LEN >= music_len[music_id])
		{
			offset = music_len[music_id] - SAMPLE_DATA_LEN;
		}

		for (int i = 0; i < SAMPLE_DATA_LEN; i++)
		{
			param[i] = music_db[music_id][i + offset] + pseudo_rand() % 256 - 128;
		}


		int return_val = user.find_music_id(param, music_id);
		if (return_val != music_id)
			System.out.println("return_val:"+return_val + "id:"+music_id);

		return return_val == music_id;
	}
}

#define MAX_MUSIC_LEN (200)
#define SAMPLE_DATA_LEN (8)

#include<stdio.h>

int total_music;
int musicDBUpper[10000][200];
int musicDBLower[10000][200];
int musicDBMSB[10000][200];
unsigned long long musicDBSum[10000][200];

int musicDBLen[10000];

void init(int total_music_cnt)
{
	total_music = total_music_cnt;

	for(int i = 0; i < total_music_cnt; i++) {
		musicDBLen[i] = 0;
	}
}



unsigned long long generateTemplate(int data[]) {
	unsigned long long result = 0;

	for(int i = 0; i < 8; i++) {
		result *= 256;
		result += data[i];
	}

	return result;
}

unsigned long long geserTemplate(unsigned long long lama, unsigned long long c0, unsigned long long c8) {
	c0 <<= 56;
	lama -= c0;
	lama <<= 8;
	return lama + c8;
}

void send_music_db_data(int music_id, int music_len, int data[MAX_MUSIC_LEN])
{
	int id = music_id - 1;
	musicDBLen[id] = music_len;

	for(int i = 0; i < music_len; i++) {
		 musicDBUpper[id][i] = data[i] + 127;
		 musicDBLower[id][i] = data[i] - 128;
		 musicDBMSB[id][i] = (data[i] + 16384) >> 10;
	}
	unsigned long long m = generateTemplate(musicDBMSB[id]);
	for(int i = 0; i <= music_len - 8; i++) {
		musicDBSum[id][i] = m;
		int c0 = musicDBMSB[id][i];
		int c8 = musicDBMSB[id][i+8];
		m = geserTemplate(m, c0, c8);	//m = 582678499217053184
	}
	//musicDBSum[id][music_len - 8] = m;
}


int find_music_id(int data[SAMPLE_DATA_LEN])
{
	int maskCh[] = {252,252,252,252,252,252,252,252,};
	unsigned long long maskL = generateTemplate(maskCh);

	int noiseMSB[SAMPLE_DATA_LEN];
	for(int i = 0 ; i < SAMPLE_DATA_LEN; i++) {
		noiseMSB[i] = (data[i] + 16384 + 128) >> 10;
	}

	unsigned long long t = generateTemplate(noiseMSB);

	for(int music_cnt = 0; music_cnt < total_music; music_cnt++) {
		unsigned long long m = generateTemplate(musicDBMSB[music_cnt]);

		for(int i = 0; i <= musicDBLen[music_cnt] - 8; i++) {
			m = musicDBSum[music_cnt][i];
			bool jackpot = (((t - m)&maskL)==0);
			if (!jackpot)
				continue;

			int match_cnt = 0;
			for(int j = 0; j < 8; j++) {
				int upper = musicDBUpper[music_cnt][i+j];
				int lower = musicDBLower[music_cnt][i+j];

				if(data[j] <= upper && data[j] >= lower) match_cnt++; 
				else break;
			}

			if(match_cnt == 8) return music_cnt + 1;
		
		}
	}

	return 0;
}
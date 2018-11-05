package profesional.songnoise;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Math.abs;

class UserSolution {
	static final int MAX_MUSIC_LEN = 200;
	static final int SAMPLE_DATA_LEN = 8;

	static Music music_db[];
    static ArrayList<Song> music_data_positive[] = new ArrayList[17];
    static ArrayList<Song> music_data_negative[] = new ArrayList[17];
    boolean isSorted;

	static class Song {
		int music_id;
		int song_seq;
		int value;

		public Song(int music_id, int song_seq, int value) {
			this.music_id = music_id;
			this.song_seq = song_seq;
			this.value = value;
		}

        @Override
        public String toString() {
            return music_id + " " + song_seq + " " + value;
        }
    }

	static class Music {
		int id;
		int n;
		int song_data[];

		public Music(int music_id, int music_len, int data[]) {
			this.id = music_id - 1;
			n = music_len;
			song_data = new int[n];
			for(int i = 0; i < music_len; i++) {
				song_data[i] = data[i];

				int song_key = abs((int) song_data[i]/1000);

				if(id == 4 && song_data[i] == -2034) {
				    System.out.println();
                }

				if(song_data[i] >= 0) {
				    if (music_data_positive[song_key] == null)
                        music_data_positive[song_key] = new ArrayList<>();
					music_data_positive[song_key].add(new Song(this.id, i, song_data[i]));
				} else {
                    if (music_data_negative[song_key] == null)
                        music_data_negative[song_key] = new ArrayList<>();
					music_data_negative[song_key].add(new Song(this.id, i, song_data[i]));
				}
			}
		}
	}

	public boolean check_music(Music music, int find_data[], int seq) {
        int start_seq = seq;

	    for(int i = 0; i < SAMPLE_DATA_LEN; i++) {
	        if(start_seq > music.n - 1)
	            return false;
            int xa = find_data[i] - 128;
            int xb = find_data[i] + 128;
            if(music.song_data[start_seq] < xa || music.song_data[start_seq] > xb) {
                return false;
            }
            start_seq += 1;
        }
        return true;
    }

    public void sort_music() {
	    for(int i = 0; i < 18; i++) {

        }

    }

	public void init(int total_music_cnt)
	{
		music_db = new Music[total_music_cnt];
		isSorted = false;
	}

	public void send_music_db_data(int music_id, int music_len, int data[])
	{
		music_db[music_id-1] = new Music(music_id, music_len, data);
	}
	
	
	public int find_music_id(int data[], int id)
	{
        int xa = data[0] - 128;
        int xb = data[0] + 128;

        ArrayList<Song> songs_a;
        ArrayList<Song> songs_b = new ArrayList<>();

        int song_key_a = abs(xa/1000);
        int song_key_b = abs(xb/1000);

        if(xa >= 0 && xb >= 0) {
            if(song_key_a == song_key_b) {
                songs_a = music_data_positive[song_key_a];
            } else {
                songs_a = music_data_positive[song_key_a];
                songs_b = music_data_positive[song_key_b];
            }
        } else if (xa < 0 && xb < 0){
            if(song_key_a == song_key_b) {
                songs_a = music_data_negative[song_key_a];
            }
            else {
                songs_a = music_data_negative[song_key_a];
                songs_b = music_data_negative[song_key_b];
            }
        } else {
            songs_a = music_data_negative[song_key_a];
            songs_b = music_data_positive[song_key_b];
        }

        for (Song song: songs_a) {
            if(song.value >= xa && song.value <= xb) {
                Music music = music_db[song.music_id];

                boolean res = check_music(music, data, song.song_seq);
                if(res) {
                    return song.music_id+1;
                }
            }
        }

        for (Song song: songs_b) {
            if(song.value >= xa && song.value <= xb) {
                Music music = music_db[song.music_id];
                boolean res = check_music(music, data, song.song_seq);
                if(res) {
                    return song.music_id+1;
                }
            }
        }
        System.out.println("xa = " + xa + " xb = " + xb + " n = " + music_db.length);
        print(0, data);
        print(0, music_db[id-1].song_data);

        return 0;
	}
	
	public void print(int start, int data[]) {
	    for(int i = start; i < data.length; i++)
	        System.out.print(data[i] + " ");
	    System.out.println();
        System.out.println();
    }
}

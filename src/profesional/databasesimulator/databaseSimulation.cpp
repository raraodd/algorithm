#include <stdio.h>
#include <string.h>

typedef enum
{
	CMD_INIT,
	CMD_ADD,
	CMD_DELETE,
	CMD_CHANGE,
	CMD_SEARCH
} COMMAND;

typedef enum
{
	NAME,
	NUMBER,
	BIRTHDAY,
	EMAIL,
	MEMO
} FIELD;

typedef struct
{
	int count;
	char str[20];
} RESULT;

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

extern void InitDB();
extern void Add(char* name, char* number, char* birthday, char* email, char* memo);
extern int Delete(FIELD field, char* str);
extern int Change(FIELD field, char* str, FIELD changefield, char* changestr);
extern RESULT Search(FIELD field, char* str, FIELD returnfield);

extern void testDataBase();
extern void printDataBase();

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

static int dummy[100];
static int Score, ScoreIdx;
static char name[20], number[20], birthday[20], email[20], memo[20];

static char lastname[10][5] = { "kim", "lee", "park", "choi", "jung", "kang", "cho", "oh", "jang", "lim" };
static int lastname_length[10] = { 3, 3, 4, 4, 4, 4, 3, 2, 4, 3 };

static int mSeed;
static int mrand(int num)
{
	mSeed = mSeed * 1103515245 + 37209;
	if (mSeed < 0) mSeed *= -1;
	return ((mSeed >> 8) % num);
}

static void make_field(int seed)
{
	int name_length, email_length, memo_length;
	int idx, num;

	mSeed = (unsigned int)seed;

	name_length = 6 + mrand(10);
	email_length = 10 + mrand(10);
	memo_length = 5 + mrand(10);

	num = mrand(10);
	for (idx = 0; idx < lastname_length[num]; idx++) name[idx] = lastname[num][idx];
	for (; idx < name_length; idx++) name[idx] = 'a' + mrand(26);
	name[idx] = 0;

	for (idx = 0; idx < memo_length; idx++) memo[idx] = 'a' + mrand(26);
	memo[idx] = 0;

	for (idx = 0; idx < email_length - 6; idx++) email[idx] = 'a' + mrand(26);
	email[idx++] = '@';
	email[idx++] = 'a' + mrand(26);
	email[idx++] = '.';
	email[idx++] = 'c';
	email[idx++] = 'o';
	email[idx++] = 'm';
	email[idx] = 0;

	idx = 0;
	number[idx++] = '0';
	number[idx++] = '1';
	number[idx++] = '0';
	for (; idx < 11; idx++) number[idx] = '0' + mrand(10);
	number[idx] = 0;

	idx = 0;
	birthday[idx++] = '1';
	birthday[idx++] = '9';
	num = mrand(100);
	birthday[idx++] = '0' + num / 10;
	birthday[idx++] = '0' + num % 10;
	num = 1 + mrand(12);
	birthday[idx++] = '0' + num / 10;
	birthday[idx++] = '0' + num % 10;
	num = 1 + mrand(30);
	birthday[idx++] = '0' + num / 10;
	birthday[idx++] = '0' + num % 10;
	birthday[idx] = 0;
}

static void cmd_init()
{
	scanf("%d", &ScoreIdx);

	InitDB();
}

static void cmd_add()
{
	int seed;
	scanf("%d", &seed);

	make_field(seed);

	Add(name, number, birthday, email, memo);
}

static void cmd_delete()
{
	int field, check, result;
	char str[20];
	scanf("%d %s %d", &field, str, &check);

	result = Delete((FIELD)field, str);
	if (result != check)
		Score -= ScoreIdx;
}

static void cmd_change()
{
	int field, changefield, check, result;
	char str[20], changestr[20];
	scanf("%d %s %d %s %d", &field, str, &changefield, changestr, &check);

	result = Change((FIELD)field, str, (FIELD)changefield, changestr);
	if (result != check)
		Score -= ScoreIdx;
}

static void cmd_search()
{
	int field, returnfield, check;
	char str[20], checkstr[20];
	scanf("%d %s %d %s %d", &field, str, &returnfield, checkstr, &check);

	RESULT result = Search((FIELD)field, str, (FIELD)returnfield);

	if (result.count != check || (result.count == 1 && (strcmp(checkstr, result.str) != 0)))
		Score -= ScoreIdx;
}

static void run()
{
	int N;
	scanf("%d", &N);
	for (int i = 0; i < N; i++)
	{
		int cmd;
		scanf("%d", &cmd);
		switch (cmd)
		{
		case CMD_INIT:   cmd_init();   break;
		case CMD_ADD:    cmd_add();    break;
		case CMD_DELETE: cmd_delete(); break;
		case CMD_CHANGE: cmd_change(); break;
		case CMD_SEARCH: cmd_search(); break;
		default: break;
		}
	}
}

static void init()
{
	Score = 1000;
	ScoreIdx = 1;
}

int main()
{
	//setbuf(stdout, NULL);
	//freopen("sample_input.txt", "r", stdin);

	//int T;
	//scanf("%d", &T);

	//int TotalScore = 0;
	//for (int tc = 1; tc <= T; tc++)
	//{
	//	init();

	//	run();

	//	if (Score < 0)
	//		Score = 0;

	//	TotalScore += Score;
	//	printf("#%d %d\n", tc, Score);
	//}
	//printf("TotalScore = %d\n", TotalScore);

	init();
	testDataBase();

	return 0;
}





















// ---------------------------------------------------------------------------------------------------------------------------
// by.0263

#include <iostream>
using namespace std;

//typedef enum
//{
//	NAME,
//	NUMBER,
//	BIRTHDAY,
//	EMAIL,
//	MEMO
//} FIELD;
//
//typedef struct
//{
//	int count;
//	char str[20];
//} RESULT;

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

struct t_data {
	char name[20], number[20], birthday[20], email[20], memo[20];
	t_data *next;
};

t_data *head, *tail;

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////


void testDataBase() {
	cout << "test add(1) add(2) add(3) add(4) : -------------------------- " << endl;
	make_field(1);  Add(name, number, birthday, email, memo);
	make_field(2);  Add(name, number, birthday, email, memo);
	make_field(3);  Add(name, number, birthday, email, memo);
	make_field(4);  Add(name, number, birthday, email, memo);
	printDataBase();

	//cout << "test delete(4) : -------------------------- " << endl;
	//Delete(FIELD::BIRTHDAY, "19460728");
	//printDataBase();

	//cout << "test delete(1) : -------------------------- " << endl;
	//Delete(FIELD::BIRTHDAY, "19001113");
	//printDataBase();

	//cout << "test delete(2) : -------------------------- " << endl;
	//Delete(FIELD::BIRTHDAY, "19770729");
	//printDataBase();


	cout << "test delete(3) : -------------------------- " << endl;
	Delete(FIELD::BIRTHDAY, "19840430");
	printDataBase();

	cout << "test update(4) : -------------------------- " << endl;
	Change(FIELD::BIRTHDAY, "19460728", FIELD::NAME, "sidangdut");
	printDataBase();

	cout << "test search(4) : -------------------------- " << endl;
	RESULT result = Search(FIELD::NAME, "sidangdut", FIELD::EMAIL);
	cout << "sidangdut email is : " << result.str << endl;
}

void printDataBase() {
	t_data *pointer;
	
	pointer = head;
	while (pointer != NULL) {
		cout << pointer->name << " " << pointer->number << " " << pointer->birthday << " " << pointer->email << " " << pointer->memo << endl;
		pointer = pointer->next;
	}
}

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

	void stringCopy(char* toStr, char* fromStr) {
		for (int i = 0; i < 20; i++) {
			toStr[i] = fromStr[i];
		}
	}

	bool stringCompare(char* str1, char* str2) {
		bool isSame = true;
		bool notEOS = true;
		int i = 0;

		while (notEOS && isSame) {
			if (str1[i] != str2[i])  isSame = false;
			if (str1[i] == '\0') notEOS = false;
			if (str2[i] == '\0') notEOS = false;
			i++;
		}

		return isSame;
	}


void InitDB()
{
	head = NULL;
	tail = NULL;
}

void Add(char* name, char* number, char* birthday, char* email, char* memo)
{
	t_data *newData = new t_data();
	stringCopy(newData->name, name);
	stringCopy(newData->number, number);
	stringCopy(newData->birthday, birthday);
	stringCopy(newData->email, email);
	stringCopy(newData->memo, memo);

	if (head == NULL) {
		head = newData;
		tail = newData;
	} else {
		tail->next = newData;
		tail = newData;
	}
}

int Delete(FIELD field, char* str)
{
	t_data *pointer = head;
	t_data *previous = NULL;
	int deletedData = 0;

	while (pointer != NULL) {
		bool isCurrentData = false;

		switch (field) {
			case NAME		: if (stringCompare(pointer->name, str)) isCurrentData = true; break;
			case NUMBER		: if (stringCompare(pointer->number, str)) isCurrentData = true; break;
			case BIRTHDAY	: if (stringCompare(pointer->birthday, str)) isCurrentData = true; break;
			case EMAIL		: if (stringCompare(pointer->email, str)) isCurrentData = true; break;
			case MEMO		: if (stringCompare(pointer->memo, str)) isCurrentData = true; break;
			default : break;
		}

		t_data *marked = pointer;
		if (isCurrentData) {
			if (pointer == head) {
				head = pointer->next;
			} else if (pointer == tail) {
				previous->next = NULL;
			} else {
				previous->next = pointer->next;
			}
		}

		previous = pointer;
		pointer = pointer->next;

		if (isCurrentData) {
			deletedData++;
			delete(marked);
		}
	}

	return deletedData;
}

int Change(FIELD field, char* str, FIELD changefield, char* changestr)
{
	t_data *pointer = head;
	int updatedData = 0;

	while (pointer != NULL) {
		bool isCurrentData = false;

		switch (field) {
			case NAME		: if (stringCompare(pointer->name, str)) isCurrentData = true; break;
			case NUMBER		: if (stringCompare(pointer->number, str)) isCurrentData = true; break;
			case BIRTHDAY	: if (stringCompare(pointer->birthday, str)) isCurrentData = true; break;
			case EMAIL		: if (stringCompare(pointer->email, str)) isCurrentData = true; break;
			case MEMO		: if (stringCompare(pointer->memo, str)) isCurrentData = true; break;
			default : break;
		}

		if (isCurrentData) {
			switch (changefield) {
				case NAME		: stringCopy(pointer->name, changestr); break;
				case NUMBER		: stringCopy(pointer->number, changestr); break;
				case BIRTHDAY	: stringCopy(pointer->birthday, changestr); break;
				case EMAIL		: stringCopy(pointer->email, changestr); break;
				case MEMO		: stringCopy(pointer->memo, changestr); break;
				default : break;
			}

			updatedData++;
		}

		pointer = pointer->next;
	}
	return updatedData;
}

RESULT Search(FIELD field, char* str, FIELD ret_field)
{
	RESULT result;
	result.count = 0;

	t_data *pointer = head;

	while (pointer != NULL) {
		bool isCurrentData = false;

		switch (field) {
			case NAME		: if (stringCompare(pointer->name, str)) isCurrentData = true; break;
			case NUMBER		: if (stringCompare(pointer->number, str)) isCurrentData = true; break;
			case BIRTHDAY	: if (stringCompare(pointer->birthday, str)) isCurrentData = true; break;
			case EMAIL		: if (stringCompare(pointer->email, str)) isCurrentData = true; break;
			case MEMO		: if (stringCompare(pointer->memo, str)) isCurrentData = true; break;
			default : break;
		}

		if (isCurrentData) {
			switch (ret_field) {
				case NAME		: stringCopy(result.str, pointer->name); break;
				case NUMBER		: stringCopy(result.str, pointer->number); break;
				case BIRTHDAY	: stringCopy(result.str, pointer->birthday); break;
				case EMAIL		: stringCopy(result.str, pointer->email); break;
				case MEMO		: stringCopy(result.str, pointer->memo); break;
				default : break;
			}

			result.count++;
		}

		pointer = pointer->next;
	}

	return result;
}
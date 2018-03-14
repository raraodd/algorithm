package profesional.texteditor;

public class UserSolution {

    class TextEditor {
        char text[];
        int textLength = 0;
        int cursorPosition;

        char selectedText[];
        int startSelectedText;
        int selectedTextLength;
        boolean isTextSelected;

        char clipboard[];
        int clipboardLength;


        public TextEditor(int N, char str[]) {
            text = new char[1000000];
            for (int i = 0; i < str.length; i++)
                text[i] = str[i];
            textLength = N;
            cursorPosition = N-1;
            System.out.println("Create text editor " + getString(text) + " " + textLength);
        }

        public void setSelectedText(int start, int end) {
            selectedText = new char[1000000];
            startSelectedText = start;
            int x = 0;
            for (int i = start; i <= end; i++) {
                if(text[i] == '\0') break;
                selectedText[x] = text[i];
                x++;
            }
            selectedTextLength = x;
        }


        public void copyClipboard() {
            clipboard = new char[1000000];
            int i = 0;
            while (selectedText[i] != '\0') {
                clipboard[i] = selectedText[i];
                i++;
            }
            isTextSelected = false;
            clipboardLength = selectedTextLength;
        }

        public void insert(int N, char[] str, boolean isPaste) {
            char tmp[] = new char[1000000];
            int i = 0;
            int j = 0;
            int k = cursorPosition;
            System.out.println(getString(text) + " " + i + " " + j + " " + k);

            while (j < N) {
                tmp[i] = text[k];
                text[k] = str[j];
                i++; j++; k++;
            }

            System.out.println(getString(text) + " --- " + getString(tmp) + " " + i);

            if(isTextSelected && !isPaste) {
                if(selectedTextLength > N) {
                    j = cursorPosition + selectedTextLength;
                    while (i != j) {
                        if(text[j] != '\0') {
                            text[i] = text[j];
                            j++;
                        } else {
                            text[i] = '\0';
                        }
                        i++;
                    }
                } else {
                  k = selectedTextLength;
                  do {
                      tmp[j] = text[i];
                      text[i] = tmp[k];
                      i++; j++; k++;
                  } while (tmp[k] != '\0');
                }
                textLength += (N - selectedTextLength);
            } else {
                j = 0;
                System.out.println(i + " " + j + " " + k);
                do {
                    tmp[i] = text[k];
                    text[k] = tmp[j];
                    i++; j++; k++;
                } while (tmp[j] != '\0');
                textLength += N;
            }
            cursorPosition += N;
            System.out.println(getString(text) + " " + textLength + " " + cursorPosition);
        }

        public void deleteSelectedText(){
            int i = startSelectedText;
            int j = startSelectedText + selectedTextLength;
            System.out.println(i + " " + j);
            while (i != j) {
                text[i] = text[j];
                i++;
                if(text[j] != '\0') j++;
            }
            textLength -= selectedTextLength;
            isTextSelected = false;
            System.out.println(getString(text) + " " + textLength + " " + cursorPosition);
        }
    }

    TextEditor te;
    int count;

    public void init(int N, char[] str) {
        count = 1;
        System.out.println("\n" + count + " init " + getString(str) + " " + N);
        count++;
        te = new TextEditor(N, str);
    }
    
    public void select_string(int mLeft, int mRight) {
        System.out.println("\n" + count + " select_string " + mLeft + " " + mRight);
        count++;
        if(mLeft > te.textLength - 1) {
            te.isTextSelected = false;
            te.selectedText = new char[1000000];
            te.selectedTextLength = 0;
            te.cursorPosition = te.textLength;
        }
        else if (mRight > te.textLength - 1) {
            te.isTextSelected = true;
            te.setSelectedText(mLeft, te.textLength - 1);
            te.cursorPosition = mLeft;
        } else {
            te.isTextSelected = true;
            te.setSelectedText(mLeft, mRight);
            te.cursorPosition = mLeft;
        }
        System.out.println("Selected text " + getString(te.selectedText) + " " + te.selectedTextLength + " " + te.cursorPosition);
        System.out.println("*** " + getString(te.text) + " " + getString(Solution.init_str));
    }
    
    public void copy_string() {
        System.out.println("\n" + count + " copy_string");
        count++;
        if(!te.isTextSelected) return;
        te.copyClipboard();
        System.out.println("Copy text " + getString(te.clipboard) + " " + te.clipboardLength + " " + te.cursorPosition);
        System.out.println("*** " + getString(te.text) + " " + getString(Solution.init_str));
    }

    public int insert_string(int M, char[] str) {
        System.out.println("\n" + count + " insert_string " + M + " " + getString(str));
        count++;
        te.insert(M, str, false);
        System.out.println("*** " + getString(te.text) + " " + te.textLength);
        return te.textLength;
    }

    public int paste_string() {
        System.out.println("\n" + count + " paste_string " + te.clipboard);
        count++;
        if(te.clipboard != null) {
            if(te.isTextSelected) te.deleteSelectedText();
            te.insert(te.clipboardLength, te.clipboard, true);
        }
        te.isTextSelected = false;
        System.out.println("*** " + getString(te.text) + " " + te.textLength);
        return te.textLength;
    }

    public void get_substring(int left, int right, char[] res_str) {
        System.out.println("\n" + count + " get_substring " + left + " " + right + " " + getString(te.text));
        count++;
        int i = 0;
        while (i < 15) {
            if(left <= right && te.text[left] != '\0') {
                res_str[i] = te.text[left];
            } else {
                res_str[i] = '\0';
            }
            i++; left++;
        }
        System.out.println("*** " + getString(te.text) + " " + getString(Solution.init_str));
        System.out.println(getString(res_str));
    }


    public int cut_string() {
        System.out.println("\n" + count + " cut_string " + getString(te.text));
        count++;
        if(te.isTextSelected) {
            te.copyClipboard();
            te.deleteSelectedText();
        }
        System.out.println("*** " + getString(te.text) + " " + getString(Solution.init_str));
        return te.textLength;
    }


    public String getString(char[] c) {
        String s = "";
        int i = 0;
        while (c[i] != '\0') {
            s += c[i];
            i++;
        }
        return s;
    }
}

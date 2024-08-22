package Java.hot_100;

public class _208 {
}

class Trie {
    /**
     * 前缀树的的存储结构：
     *        |--- a --> b -- c --|
     * null --|--- b --> a -- c --|
     *        |--- c --> a -- b --|
     * 第一个节点是不存储字符的，从第二个节点开始存储字符，从数组下标判断存储的是哪一个字符。
     * */

    boolean isEnd;
    // 标记当前节点是否是一个单词的结束。

    Trie[] next;
    // 存储下一 26 个节点。

    public Trie() {
        this.isEnd = false;
        this.next = new Trie[26];
    }

    public void insert(String word) {
        if (word.isEmpty()) {
            this.isEnd = true;
            return;
        }
        char ch = word.charAt(0);
        if (this.next[ch - 'a'] == null) {
            this.next[ch - 'a'] = new Trie();
        }
        this.next[ch - 'a'].insert(word.substring(1));
    }

    public boolean search(String word) {
        if (word.isEmpty()) {
            return this.isEnd;
        }
        char ch = word.charAt(0);
        if (this.next[ch - 'a'] == null) {
            return false;
        }
        return this.next[ch - 'a'].search(word.substring(1));
    }

    public boolean startsWith(String prefix) {
        if (prefix.isEmpty()) {
            return true;
        }
        char ch = prefix.charAt(0);
        if (this.next[ch - 'a'] == null) {
            return false;
        }
        return this.next[ch - 'a'].startsWith(prefix.substring(1));
    }
}

/*
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

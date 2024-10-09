// Time Complexity: O(n * l + l)
// Space Complexity: O(1)

class StreamChecker {
    class TrieNode {
        TrieNode[] children;
        boolean isStart;

        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }

    public void insert(String word) {
        TrieNode curr = root;
        for (int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);
            if (curr.children[c - 'a'] == null)
                curr.children[c - 'a'] = new TrieNode();
            curr = curr.children[c - 'a'];
        }
        curr.isStart = true;
    }

    StringBuilder sb;
    TrieNode root;
    int max;

    public StreamChecker(String[] words) {
        this.root = new TrieNode();
        this.sb = new StringBuilder();

        for (String word : words) {
            insert(word);
            max = Math.max(max, word.length());
        }

    }

    public boolean query(char letter) {
        sb.append(letter);
        TrieNode curr = root;
        if (sb.length() > max)
            sb.deleteCharAt(0);

        for (int i = sb.length() - 1; i >= 0; i--) {
            char c = sb.charAt(i);

            if (curr.children[c - 'a'] == null)
                return false;

            curr = curr.children[c - 'a'];
            if (curr.isStart)
                return true;

        }

        return false;
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
package training.leetcode.algorithm;

public class Solution165 {
    /*
        Given two version strings, version1 and version2, compare them. A version string consists of revisions
        separated by dots '.'. The value of the revision is its integer conversion ignoring leading zeros.

        To compare version strings, compare their revision values in left-to-right order. If one of the version
         strings has fewer revisions, treat the missing revision values as 0.

        Return the following:
            If version1 < version2, return -1.
            If version1 > version2, return 1.
            Otherwise, return 0.
     */

    public int compareVersion(String version1, String version2) {
        int offset1 = 0;
        int offset2 = 0;

        int rev1;
        int rev2;
        do {
            int nextOffset = nextRevisionOffset(version1, offset1);
            rev1 = getRevision(version1, offset1, nextOffset);
            offset1 = Math.min(nextOffset+1, version1.length());

            nextOffset = nextRevisionOffset(version2, offset2);
            rev2 = getRevision(version2, offset2, nextOffset);
            offset2 = Math.min(nextOffset+1, version2.length());

        } while (rev1 == rev2 && (offset1 < version1.length() || offset2 < version2.length()));

        return Integer.compare(rev1, rev2);
    }

    private int getRevision(String str, int start, int end) {
        int revision = 0;

        for(; start < str.length() && start < end; start++) {
            char ch = str.charAt(start);
            if(ch > '0') {
                break;
            }
        }

        for(int i = start; i < str.length() && i < end; i++) {
            char ch = str.charAt(i);
            if(ch >= '0' && ch <= '9') {
                revision = revision * 10 + (ch - '0');
            }
        }

        return revision;
    }

    private int nextRevisionOffset(String str, int offset) {
        for(int i = offset; i < str.length(); i++) {
            if(str.charAt(i) == '.') {
                return i;
            }
        }

        return str.length();
    }

    public int compareVersion2(String version1, String version2) {
        int i = 0, j = 0;
        int n = version1.length(), m = version2.length();

        while (i < n || j < m) {
            long num1 = 0, num2 = 0; // long to avoid overflow

            while (i < n && version1.charAt(i) != '.') {
                num1 = num1 * 10 + (version1.charAt(i) - '0');
                i++;
            }

            while (j < m && version2.charAt(j) != '.') {
                num2 = num2 * 10 + (version2.charAt(j) - '0');
                j++;
            }

            if (num1 > num2) return 1;
            if (num1 < num2) return -1;

            // skip dots
            if (i < n && version1.charAt(i) == '.') i++;
            if (j < m && version2.charAt(j) == '.') j++;
        }

        return 0;
    }

    public static void main(String[] args) {
        Solution165 solution = new Solution165();
        System.out.println(solution.compareVersion("1.2", "1.10"));
        System.out.println(solution.compareVersion("1.10", "1.01"));
        System.out.println(solution.compareVersion("1.001", "1.01"));
        System.out.println(solution.compareVersion("1.0", "1.0.0.0"));
    }
}

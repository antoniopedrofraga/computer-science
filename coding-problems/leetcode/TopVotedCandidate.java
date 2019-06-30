import java.util.*;

/**
 * In an election, the i-th vote was cast for persons[i] at time times[i].
 *
 * Now, we would like to implement the following query function: TopVotedCandidate.q(int t) will return the number of the person that was leading the election at time t.
 *
 * Votes cast at time t will count towards our query.  In the case of a tie, the most recent vote (among tied candidates) wins.
 *
 *
 *
 * Example 1:
 *
 * Input: ["TopVotedCandidate","q","q","q","q","q","q"], [[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]
 * Output: [null,0,1,1,0,0,1]
 * Explanation:
 * At time 3, the votes are [0], and 0 is leading.
 * At time 12, the votes are [0,1,1], and 1 is leading.
 * At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)
 * This continues for 3 more queries at time 15, 24, and 8.
 *
 *
 * Note:
 *
 * 1 <= persons.length = times.length <= 5000
 * 0 <= persons[i] <= persons.length
 * times is a strictly increasing array with all elements in [0, 10^9].
 * TopVotedCandidate.q is called at most 10000 times per test case.
 * TopVotedCandidate.q(int t) is always called with t >= times[0].
 */
class TopVotedCandidate {
    public TopVotedCandidate() {}
class Vote {
    int person;
    int time;
    public Vote(int person, int time) {
        this.person = person;
        this.time = time;
    }
}
TreeMap<Integer, Integer> bst = new TreeMap<>();
public TopVotedCandidate(int[] persons, int[] times) {
    Map<Integer, Integer> freq = new HashMap<>();
    List<Vote> votes = new ArrayList<>();
    for (int i = 0; i < persons.length; i++) {
        votes.add(new Vote(persons[i], times[i]));
    }
    votes.sort((v1, v2) -> v1.time - v2.time);
    int max = -1, maxPerson = -1;
    for (int i = 0; i < votes.size(); i++) {
        int person = votes.get(i).person;
        int time = votes.get(i).time;
        int nr = freq.getOrDefault(person, 0) + 1;
        freq.put(person, nr);
        if (nr >= max) {
            maxPerson = person;
            max = nr;
            bst.put(time, maxPerson);
        }
    }

}
public int q(int t) {
    if (bst.containsKey(t)) {
        t += 1;
    }
    return bst.lowerEntry(t).getValue();
}

    public void test() {
        /*String [] cmds = new String[]{"TopVotedCandidate","q","q","q","q","q","q"};
        Object [] args = new Object[] { new int[][]{new int[]{0,1,1,0,0,1,0}, new int[]{0,5,10,15,20,25,30}}, new int[]{3},new int[]{12},new int[]{25},new int[]{15},new int[]{24},new int[]{8}};
        TopVotedCandidate search = new TopVotedCandidate();
        for (int i = 0; i < cmds.length; i++) {
            if (cmds[i].equals("TopVotedCandidate")) {
                int [] candidates = (int [])((Object []) args[i])[0];
                int [] times = (int [])((Object []) args[i])[1];
                search = new TopVotedCandidate(candidates, times);
            } else {
                int t = ((int[]) args[i])[0];

                System.out.println("Searching for t = " + t + " -> " + search.q(t));
            }
        }*/
        String [] cmds = new String[]{"TopVotedCandidate","q","q","q","q","q","q","q","q","q","q"};
        Object [] args = new Object[] { new int[][]{new int[]{0,0,0,0,1}, new int[]{0,6,39,52,75}}, new int[]{45},new int[]{49},new int[]{59},new int[]{68},new int[]{42},new int[]{37},
                new int[]{99},new int[]{26},new int[]{78},new int[]{43}};
        TopVotedCandidate search = new TopVotedCandidate();
        for (int i = 0; i < cmds.length; i++) {
            if (cmds[i].equals("TopVotedCandidate")) {
                int [] candidates = (int [])((Object []) args[i])[0];
                int [] times = (int [])((Object []) args[i])[1];
                search = new TopVotedCandidate(candidates, times);
            } else {
                int t = ((int[]) args[i])[0];

                System.out.println("Searching for t = " + t + " -> " + search.q(t));
            }
        }
    }
}


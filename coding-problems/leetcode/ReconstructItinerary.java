import java.util.*;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
 *
 * Note:
 *
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * Example 1:
 *
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 * Example 2:
 *
 * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 *              But it is larger in lexical order.
 */
public class ReconstructItinerary {

public List<String> findItinerary(List<List<String>> tickets) {
    LinkedList<String> result = new LinkedList<>();
    Map<String, PriorityQueue<String>> destinations = new HashMap<>();
    for (List<String> ticket : tickets) {
        String from = ticket.get(0), to = ticket.get(1);
        destinations.putIfAbsent(from, new PriorityQueue<>());
        destinations.get(from).add(to);
    }
    dfs(result, destinations, "JFK");
    return result;
}
void dfs(LinkedList<String> result, Map<String, PriorityQueue<String>> destinations, String airport) {
    PriorityQueue<String> dests = destinations.get(airport);
    while (dests != null && !dests.isEmpty()) {
        String temp = dests.remove();
        dfs(result, destinations, temp);
    }
    result.addFirst(airport);
}
    public void test() {
        /*System.out.println(findItinerary(Arrays.asList(Arrays.asList("JFK","KUL"), Arrays.asList("JFK","NRT"), Arrays.asList("NRT","JFK"))).toString());
        System.out.println(findItinerary(Arrays.asList(Arrays.asList("MUC", "LHR"), Arrays.asList("JFK", "MUC"), Arrays.asList("SFO", "SJC"), Arrays.asList("LHR", "SFO"))).toString());*/
        System.out.println(findItinerary(Arrays.asList(Arrays.asList("JFK","SFO"), Arrays.asList("JFK","ATL")/*, Arrays.asList("SFO","ATL"), Arrays.asList("ATL","JFK"), Arrays.asList("ATL","SFO")*/)).toString());
    }
}

public class Main {

    public static void main(String[] args) {
        Sort<Integer> sorting = new Sort();
        for (Integer integer : sorting.quicksort(new Integer[]{3, 1, 2, 6, 4, 2})) {
            System.out.print(integer + " ");
        }
    }
}

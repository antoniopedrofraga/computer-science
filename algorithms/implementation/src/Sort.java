import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Queue;

public class Sort<T extends Comparable<T>> {
    public T [] insertionSort(T [] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j].compareTo(array[j - 1]) < 0) {
                    T temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                } else {
                    break;
                }
            }
        }
        return array;
    }

    public T [] mergeSort(T [] array) {
        return mergeSort(array, 0, array.length);
    }

    private T [] mergeSort(T [] array, int low, int high) {
        if (high <= low) {
            return (T []) Array.newInstance(array.getClass().getComponentType(), 0);
        } else if (high - low <= 1) {
            T [] newArray = (T []) Array.newInstance(array.getClass().getComponentType(), 1);
            newArray[0] = array[low];
            return newArray;
        }

        int mid = low + (high - low) / 2;
        T [] a = mergeSort(array, low, mid);
        T [] b = mergeSort(array, mid, high);
        T [] result = (T []) Array.newInstance(array.getClass().getComponentType(),a.length + b.length);
        int i = 0, j = 0;
        while (i < a.length || j < b.length) {
            int index = i + j;
            if (i < a.length && j < b.length) {
                result[index] = a[i].compareTo(b[j]) < 0 ? a[i++] : b[j++];
            } else if (i >= a.length) {
                result[index] = b[j++];
            } else if (j >= b.length) {
                result[index] = a[i++];
            }
        }
        return result;
    }

    private int rightIndex(int index) {
        return 2 * index + 1;
    }
    private int leftIndex(int index) {
        return 2 * index + 2;
    }
    private int parentIndex(int index) {
        return (index - 1) / 2;
    }
    private T [] maxHeapify(T [] array, int index, int heapSize) {
        T temp = array[index];
        boolean validHeap = false;
        while (index < heapSize / 2 && !validHeap) {
            int left = leftIndex(index), right = rightIndex(index);
            if (left < heapSize && right < heapSize) {
                int max = array[right].compareTo(array[left]) < 0 ? left : right;
                array[index] = array[max];
                array[max] = temp;
                index = max;
            } else if (left < heapSize && array[index].compareTo(array[left]) < 0) {
                array[index] = array[left];
                array[left] = temp;
                index = left;
            } else if (right < heapSize && array[index].compareTo(array[right]) < 0) {
                array[index] = array[right];
                array[rightIndex(index)] = temp;
                index = rightIndex(index);
            } else {
                validHeap = true;
            }
        }
        return array;
    }
    private T [] buildMaxHeap(T [] array, int heapSize) {
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            array = maxHeapify(array, i, heapSize);
        }
        return array;
    }
    public T [] heapSort(T [] array) {
        int heapSize = array.length;
        array = buildMaxHeap(array, heapSize);
        while (heapSize > 2) {
            T temp = array[heapSize - 1];
            array[heapSize - 1] = array[0];
            array[0] = temp;
            heapSize--;
            array = maxHeapify(array, 0, heapSize);
        }
        if (heapSize == 2) {
            T temp = array[0];
            array[0] = array[1];
            array[1] = temp;
        }
        return array;
    }

    private void swap(T [] array, int indexA, int indexB) {
        T temp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = temp;
    }
    public T [] quicksort(T [] array) {
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        if (array.length > 0) {
            queue.add(new Pair<>(0, array.length));
        }

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> bounds = queue.remove();
            int lowerBound = bounds.getKey(), higherBound = bounds.getValue();
            if (higherBound <= lowerBound) continue;

            int size = higherBound - lowerBound;
            int pivot = (int)(Math.random() * (size - 1) + lowerBound);
            swap(array, pivot, higherBound - 1);

            int i = lowerBound - 1;
            for (int j = lowerBound; j < higherBound - 1; j++) {
                if (array[j].compareTo(array[higherBound - 1]) < 0) {
                    swap(array, ++i, j);
                }
            }
            swap(array, i + 1, higherBound - 1);
            queue.add(new Pair<>(lowerBound, i + 1));
            queue.add(new Pair<>(i + 2, higherBound));
        }

        return array;
    }

    public Integer [] countingSort(Integer [] array) {
        /* Find max value */
        int max = 0;
        for (Integer integer : array) {
            if (integer < 0) {
                throw new IllegalArgumentException();
            } else if (integer > max) {
                max = integer;
            }
        }
        Integer [] result = new Integer[array.length], // The result array
            aux = new Integer[max + 1]; // The auxiliary array is filled with 0's following Java language specs
        for (Integer element : array) {
            aux[element] = 1;
        }
        for (int i = 1; i < aux.length; ++i) { // aux[i] contains the number of elements less or equal than i
            aux[i] += aux[i - 1];
        }
        for (int i = array.length - 1; i >= 0; --i) {
            result[aux[array[i]]] = array[i]; // placing the element in the correct / sorted index
            aux[array[i]]--; // Decrement number of elements before a certain position (useful if we have repeated numbers)
        }
        return result;
    }
}



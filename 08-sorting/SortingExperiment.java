import java.util.Random;

/**
 * implements and tests two sorting algorithms: insertion sort and merge sort.
 * provides methods for creating test data and measuring performance.
 */
public class SortingExperiment {
    /**
     * performs in-place insertion sort on an array.
     * 
     * @param arr the array to be sorted
     */
    static void insertionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int current = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > current) {
                arr[j + 1] = arr[j];
                j -= 1;
            }
            arr[j + 1] = current;
        }
    }

    /**
     * initiates merge sort on an array by calling the recursive sort method.
     * 
     * @param arr the array to be sorted
     */
    static void mergeSort(int[] arr) {
        int n = arr.length;
        if (n > 1) {
            int leftEdge = 0;
            int rightEdge = n - 1;
            sort(arr, leftEdge, rightEdge);
        }
    }

    /**
     * recursive helper method that implements the divide phase of merge sort.
     * 
     * @param arr the array being sorted
     * @param leftEdge start index of the current subarray
     * @param rightEdge end index of the current subarray
     */
    static void sort(int[] arr, int leftEdge, int rightEdge) {
        if (leftEdge < rightEdge) {
            int middle = leftEdge + (rightEdge - leftEdge) / 2;
            sort(arr, leftEdge, middle);
            sort(arr, middle + 1, rightEdge);
            merge(arr, leftEdge, middle, rightEdge);
        }
    }

    /**
     * merges two sorted subarrays into a single sorted array.
     * implements the conquer phase of merge sort.
     * 
     * @param arr the array containing both subarrays
     * @param leftEdge start index of first subarray
     * @param middle end index of first subarray
     * @param rightEdge end index of second subarray
     */
    static void merge(int[] arr, int leftEdge, int middle, int rightEdge) {
        int sizeLeft = middle - leftEdge + 1;
        int sizeRight = rightEdge - middle;
        int[] tempLeft = new int[sizeLeft];
        int[] tempRight = new int[sizeRight];
        for (int i = 0; i < sizeLeft; i++) {
            tempLeft[i] = arr[leftEdge + i];
        }
        for (int j = 0; j < sizeRight; j++) {
            tempRight[j] = arr[middle + 1 + j];
        }
        int i = 0;
        int j = 0;
        int k = leftEdge;
        while (i < sizeLeft && j < sizeRight) {
            if (tempLeft[i] < tempRight[j]) {
                arr[k++] = tempLeft[i++];
            } else {
                arr[k++] = tempRight[j++];
            }
        }
        while (i < sizeLeft) {
            arr[k++] = tempLeft[i++];
        }
        while (j < sizeRight) {
            arr[k++] = tempRight[j++];
        }
    }

    /**
     * creates an array of specified size filled with random integers.
     * 
     * @param size length of the array to create
     * @param minValue minimum value for random numbers
     * @param maxValue maximum value for random numbers
     * @return array of random integers
     */
    static int[] randomArray(int size, int minValue, int maxValue) {
        Random rand = new Random();
        int[] randArr = new int[size];
        for (int i = 0; i < randArr.length; i++) {
            randArr[i] = minValue + rand.nextInt(1 + maxValue);
        }
        return randArr;
    }

    /**
     * creates a copy of an array.
     * 
     * @param original array to copy
     * @return new array with same contents as original
     */
    private static int[] copyArray(int[] original) {
        int[] copy = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i];
        }
        return copy;
    }
    
    /**
     * checks if two arrays have identical contents.
     * 
     * @param arr1 first array to compare
     * @param arr2 second array to compare
     * @return true if arrays have same length and contents
     */
    private static boolean arraysEqual(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }

    /**
     * conducts performance experiments comparing insertion sort and merge sort.
     * tests arrays of increasing sizes (powers of 2) and runs multiple trials.
     * 
     * @param maxExponent maximum power of 2 to test (e.g., 13 for arrays up to 2^13)
     * @param trialsPerSize number of trials to run for each array size
     * @return array containing [size, insertion_sort_time, merge_sort_time] for each size tested
     */
    public static double[][] experiment(int maxExponent, int trialsPerSize) {
        double[][] results = new double[maxExponent + 1][3];
        for (int exp = 0; exp <= maxExponent; exp++) {
            int size = (int) Math.pow(2, exp);
            long totalInsertionTime = 0;
            long totalMergeTime = 0;
            for (int trial = 0; trial < trialsPerSize; trial++) {
                int[] originalArray = randomArray(size, 0, 10000);
                int[] insertionArray = copyArray(originalArray);
                long startTime = System.nanoTime();
                insertionSort(insertionArray);
                long endTime = System.nanoTime();
                totalInsertionTime += (endTime - startTime);
                
                int[] mergeArray = copyArray(originalArray);
                startTime = System.nanoTime();
                mergeSort(mergeArray);
                endTime = System.nanoTime();
                totalMergeTime += (endTime - startTime);
                
                if (!arraysEqual(insertionArray, mergeArray)) {
                    throw new RuntimeException("Sorting algorithms produced different results!");
                }
            }
            results[exp][0] = size;
            results[exp][1] = (totalInsertionTime / trialsPerSize) / 1_000_000.0;
            results[exp][2] = (totalMergeTime / trialsPerSize) / 1_000_000.0;
        }
        return results;
    }

    /**
     * runs the experiment and prints results in a formatted table.
     * tests array sizes from 2^0 to 2^13 with 5 trials per size.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        int maxExponent = 13;
        int trialsPerSize = 5;
        double[][] experimentResults = experiment(maxExponent, trialsPerSize);
        System.out.println("Array Size | Insertion Sort (ms) | Merge Sort (ms)");
        System.out.println("-----------|-------------------|----------------");
        for (double[] result : experimentResults) {
            System.out.printf("%9.0f | %17.3f | %14.3f%n",
                            result[0],
                            result[1],
                            result[2]);
        }
    }
}

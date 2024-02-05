package search;

public class BinarySearchMax {

    static public void main(String[] args) {
        int[] arr = new int[args.length];
        int i = 0;
        int count = 0;
        while (i < args.length) {
            int value = Integer.parseInt(args[i]);
            arr[i] = value;
            if (value % 2 == 1) {
                count++;
            }
            i++;
        }
        int res;
        if (count % 2 == 0) {
            res = recursiveBinarySearch(arr, 0, arr.length - 1);
        } else {
            res = iterBinarySearch(arr);
        }
        System.out.println(res);
    }

    static int recursiveBinarySearch(int[] arr, int left, int right) {
        if (right <= left + 1) {
            return Math.max(arr[right], arr[left]);
        }
        int mid = (left + right) / 2;
        if (arr[left] < arr[mid]) {
            return recursiveBinarySearch(arr, mid, right);
        } else {
            return recursiveBinarySearch(arr, left, mid);
        }
    }

    static int iterBinarySearch(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (right > left + 1) {
            int mid = (left + right) / 2;
            if (arr[left] < arr[mid]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return Math.max(arr[right], arr[left]);
    }
}
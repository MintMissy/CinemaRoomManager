class FixingExceptions {

    public static void calculateSquare(int[] array, int index) {
        if (index < 0 || index >= (array == null ? 0 : array.length)) {
            System.out.println("Exception!");
        } else {
            System.out.println(array[index] * array[index]);
        }
    }
}
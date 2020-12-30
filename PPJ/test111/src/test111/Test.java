package test111;

public class Test{
    public static void main(String args[]){
        int m = 0x4E;
        for (int i = 0; i < 4; ++i)
            System.out.print( (m & (3 << i)) + ",");

        System.out.println("!");
        
        int[] array = {3,6,2,9,2,5};
        int[][] array2D = {{1,6,3}, {5,9,2}, {2,6,8}};
        
        System.out.println(fun1(array));
        System.out.println(fun2(array2D));
    }

    public static int fun1(int[] a){
        int b = 0;
        for(int i = 0; i < a.length; i += 2){
            b += a[i];
            if(b < 8)
                System.out.print(b + " ");
        }

        return b % 3;
    }

    public static int fun2(int[][] a){
        int b = 0;
        for(int i = 0; i < a.length; i++){
            for(int j = i; j < a[i].length; j++){
                b += a[i][j];
                if(b > 10)
                    System.out.print(b + " ");
            }
        }

        return b / 3;
    }

}


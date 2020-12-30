public class Main {

    public static void main(String[] args) {

        ObjectPool pool = ObjectPool.getInstance();

        User user1 = new User();
        User user2 = new User();
        //some operations
        user1.takeFromPool(pool);
        user1.takeFromPool(pool);
        user1.takeFromPool(pool);
        user1.takeFromPool(pool);
        user1.takeFromPool(pool);
        user1.takeFromPool(pool);
        user1.takeFromPool(pool);
        user1.takeFromPool(pool);
        user1.takeFromPool(pool);
        user1.takeFromPool(pool);
       //limit reached
        user2.takeFromPool(pool);
        user1.returnToPool(pool);
        user1.returnToPool(pool);
        user1.returnToPool(pool);

        user2.takeFromPool(pool);
        user2.takeFromPool(pool);
        user2.returnToPool(pool);

        //check
        System.out.println();
        System.out.println("User 1 objects: " + user1.getListSize());
        System.out.println("User 2 objects: " + user2.getListSize());
        System.out.println("Objects in pool: " + pool.getListSize());
        ObjectPool.printNumber();


    }

}



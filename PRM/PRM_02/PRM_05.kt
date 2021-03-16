class BankAccount {
    // I think private variable should be enough to be not able to write that variable from any external class
    private var balance: Int

    constructor(balance: Int) {
        this.balance = balance
    }

    constructor() {
        this.balance = 0
    }

    fun getBalance(): Int {
        return balance;
    }

    fun withdraw(amount: Int) {
        this.balance = this.balance - amount;
    }

    fun deposit(amount: Int) {
        if (this.balance + amount >= 0)
            this.balance = this.balance + amount;
    }
}


fun main(args: Array<String>) {
    val account = BankAccount(500);
    println(account.getBalance())
    account.deposit(500)
    println(account.getBalance())
    account.withdraw(500)
    println(account.getBalance())

    // As we can see its not accesible
    //  account.balance = 500
}
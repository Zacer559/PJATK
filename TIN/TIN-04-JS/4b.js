function fibonacci(num) {
    let a = 1, b = 0, temp;

    while (num > 0) {
        temp = a;
        a = a + b;
        b = temp;
        num--;
    }

    return b;
}

console.log(fibonacci(5));
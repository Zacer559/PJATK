function amountToCoins(amount, coins){
    let str = '';
    for(let i = 0; i < coins.length; i++){
       while((amount - coins[i]) >= 0){
        str = str.concat(coins[i].toString(), ', ');
        amount = amount - coins[i];
       }
    }
    return str;
}
console.log(amountToCoins(46, [25, 10, 5, 2, 1]))
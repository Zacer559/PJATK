const express = require("express");
const app = express();

app.use(express.json());
//Due to task b and CORS policy...
app.use(function (req, res, next) {
    res.header('Access-Control-Allow-Origin', req.headers.origin);
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});

const Calculator = function (num) {
    this.num = num;
};
//results as an array for json formatting
const results = (function () {
    let results = [];

    return {
        add: function (obj) {
            results.push(obj);
        },
        get: function () {
            return results;
        },
        clear: function () {
            results = [];
        }
    };
})();

Calculator.prototype = {
    add: function (n) {
        let result = Math.floor(this.num + n);
        results.add({num1: this.num, num2: n, operation: "add", res: result});
        this.num = result;
        return this;
    },
    subtract: function (n) {
        let result = Math.floor(this.num - n);
        results.add({
            num1: this.num,
            num2: n,
            operation: "subtract",
            res: result
        });
        this.num = result;
        return this;
    },
    multiply: function (n) {
        let result = Math.floor(this.num * n);
        results.add({
            num1: this.num,
            num2: n,
            operation: "multiply",
            res: result
        });
        this.num = result;
        return this;
    },
    divide: function (n) {
        let result = Math.floor(this.num / n);
        results.add({num1: this.num, num2: n, operation: "divide", res: result});
        return this;
    },
    clear: function () {
        this.num = 0;
        return this;
    }
};


app.post("/calculator", (req, res) => {
    let num1 = parseInt(req.body.num1);
    let num2 = parseInt(req.body.num2);
    let func = req.body.func;

    let request = new Calculator(num1);

    switch (func) {
        case "add":
            request.add(num2);
            break;
        case "sub":
            request.subtract(num2);
            break;
        case "mul":
            request.multiply(num2);
            break;
        case "div":
            request.divide(num2);
            break;
        default:
            results.clear();
            break;
    }

    let calcResult = results.get();
    res.json(calcResult);
});

const port = process.env.PORT || 6969;
app.listen(port, () => console.log(`Listening on port ${port}`));

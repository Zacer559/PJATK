const http = require("http");
const url = require("url");

const server = http.createServer((req, res) => {
    if (req.url === "/") {
        res.write("It works!\nTry something like: \nhttp://localhost:6969/divide?num1=2&num2=5");
        res.end();
    }

    if (req.url.startsWith("/add")) {
        const queryObject = url.parse(req.url, true).query;
        const num1 = parseInt(queryObject.num1);
        const num2 = parseInt(queryObject.num2);

        if (num1 && num2) {
            let result = String(num1 + num2);
            res.write(result);
            res.end();
        } else {
            res.write("Wrong parameters");
            res.end();
        }
    }

    if (req.url.startsWith("/sub")) {
        let queryObject = url.parse(req.url, true).query;
        const num1 = parseInt(queryObject.num1);
        const num2 = parseInt(queryObject.num2);

        if (num1 && num2) {
            let result = String(num1 - num2);
            res.write(result);
            res.end();
        } else {
            res.write("Wrong parameters");
            res.end();
        }
    }

    if (req.url.startsWith("/mul")) {
        let queryObject = url.parse(req.url, true).query;
        const num1 = parseInt(queryObject.num1);
        const num2 = parseInt(queryObject.num2);

        if (num1 && num2) {
            let result = String(num1 * num2);
            res.write(result);
            res.end();
        } else {
            res.write("Wrong parameters");
            res.end();
        }
    }

    if (req.url.startsWith("/div")) {
        let queryObject = url.parse(req.url, true).query;
        const num1 = parseInt(queryObject.num1);
        const num2 = parseInt(queryObject.num2);

        if (num1 && num2) {
            let result = String(num1 / num2);
            res.write(result);
            res.end();
        } else {
            res.write("Wrong parameters");
            res.end();
        }
    }
});

server.listen(6969);

console.log("Listening on port 6969");

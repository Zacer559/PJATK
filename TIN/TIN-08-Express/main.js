const express = require("express");
const app = express();
const path = require("path");
const pug = require("pug");
const bodyParser = require("body-parser");

app.use(express.json());
app.use(bodyParser.urlencoded({extended: false}));
app.set('view engine', 'pug')

app.get("/hello", (req, res) => {
    res.send("hello World");
});

app.get('/form', (req, res) => {
    res.sendFile(path.join(__dirname, './', 'form.html'));
});

app.post("/formdata", (req, res) => {
    res.render('response', {user: req.body.username, password: req.body.password, email: req.body.email})
});

app.post("/jsondata", (req, res) => {
    res.send(
        `Json data is: \n username: ${req.body.username}  \n password: ${req.body.password} \n email: ${req.body.email}`
    );
});

const port = process.env.PORT || 3000;
app.listen(port, () => console.log(`Listening on port ${port}`));

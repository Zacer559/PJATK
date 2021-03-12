let getUserData = function (obj) {
    let data;
    let log = ""
    for (let prop in obj) {
        data = obj[prop];
        log += prop + " - " + data + " (" + typeof (data) + ")\n"
    }
    return log;
}

const user = {
    id: 1,
    name: "Franek",
    surname: "Kimono",
    work: function () {
        console.log("Na bramce stoję");
    },
    guard: function () {
        console.log("Niczego sie nie boję");
    },
    kill: function () {
        console.log("Ja jestem King Bruce Lee karate mistrz");
    }
};
//check
console.log(getUserData(user));
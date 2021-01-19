const fs = require("fs");

function watchPath(path) {
    let readFile = fs.readdirSync(path);

    console.log("Files in defined path are: \n" + readFile + "\n\n");

    fs.watch(path, "utf8", function (event, trigger) {
        console.log(event + " in " + trigger);
        let file = fs.readdirSync(path);
        console.log("Files in defined in path after changes: \n" + file + "\n\n");
    });
}

watchPath("./Super_hiper_data");

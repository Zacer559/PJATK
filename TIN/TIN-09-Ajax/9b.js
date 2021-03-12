function calculate() {
    const data = {
        num1: document.querySelector("#first-input").value,
        num2: document.querySelector("#second-input").value,
        func: document.querySelector("select").value
    };
    const xhr = new window.XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            const obj = JSON.parse(xhr.responseText);
            document.querySelector("textarea").value = "First number: " + obj[0].num1 + "\nSecond number: " + obj[0].num2 + "\nOperation type: " + obj[0].operation + "\nResult: " + obj[0].res;
        }
    };
    xhr.open("POST", "http://localhost:6969/calculator", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.send(JSON.stringify(data));
}

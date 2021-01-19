function validate() {
    const numberElement = document.querySelector("#first-input");
    const emailElement = document.querySelector("#second-input");
    const number = document.querySelector("#first-input").value;
    const email = document.querySelector("#second-input").value;

    if (!number) {
        console.log("invalid number");
        numberElement.classList.add("is-invalid");
        return false;
    }

    if (!email) {
        console.log("invalid email");
        emailElement.classList.add("is-invalid");
        return false;
    }

    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    const validEmail = re.test(String(email).toLowerCase());
    console.log(validEmail);
    if (!validEmail) {
        console.log("invalid email");
        emailElement.classList.add("is-invalid");
        return false;
    }

    console.log("valid");

    numberElement.classList.remove("is-invalid");
    emailElement.classList.remove("is-invalid");


    return true;
}

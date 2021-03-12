function convert() {

    const celsiusElement = document.querySelector("#first-input");
    const fahrenheitElement = document.querySelector("#second-input");
    const celsius = parseInt(celsiusElement.value);
    const fahrenheit = parseInt(fahrenheitElement.value);
    console.log(celsius, fahrenheit);

    switch (celsius || fahrenheit) {
        case celsius:
            fahrenheitElement.value = (celsius * 9 / 5) + 32;
            break;
        case fahrenheit:
            celsiusElement.value = (fahrenheit - 32) * (5 / 9);
            break;
        default:
            console.log("Two empty inputs")
    }
}
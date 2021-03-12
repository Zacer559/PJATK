function add() {
    const providedMark = document.querySelector("#first-input");
    const providedModel = document.querySelector("#second-input");
    const providedAge = document.querySelector("#third-input");
    const mark = providedMark.value;
    const model = providedModel.value;
    const age = providedAge.value;

    let table = document.querySelector("table");
    var row = table.insertRow(-1);
    var markCell = row.insertCell(0);
    var modelCell = row.insertCell(1);
    var ageCell = row.insertCell(2);

    markCell.innerHTML = mark;
    modelCell.innerHTML = model;
    ageCell.innerHTML = age;

    return true
}

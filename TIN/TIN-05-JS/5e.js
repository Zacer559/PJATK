class Student {
    constructor(id, grades) {
        this.name = "";
        this.surname = "";
        this.id = id;
        this.grades = grades;
    }

    get fullName() {
        return this.name + " " + this.surname;
    }

    set fullName(name) {
        let splittedName = name.split(" ");
        this.name = splittedName[0] || "";
        this.surname = splittedName[1] || "";
    }

    get avgGrade() {
        let sum = 0;
        for (let i = 0; i < this.grades.length; i++) {
            sum += this.grades[i];
        }
        return sum / this.grades.length;
    }

    get print() {
        return (
            "Id - " +
            this.id +
            " Name - " +
            this.name +
            " " +
            this.surname +
            " Avg grade - " +
            this.avgGrade
        );
    }
}
//check
const e = new Student(1, [4, 5, 4, 4]);
e.fullName = "Komisarz Ryba";
console.log("Name - " + e.fullName + " | Avg grade - " + e.avgGrade);
console.log(e.print);

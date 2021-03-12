class Person {
    constructor(name, surname) {
        this.name = name;
        this.surname = surname;
    }

    get fullName() {
        return this.name + " " + this.surname;
    }

    set fullName(name) {
        let splittedName = name.split(" ");
        this.name = splittedName[0] || "";
        this.surname = splittedName[1] || "";
    }
}

class Student extends Person {
    constructor(id, name, surname, grades) {
        super(name, surname);
        this.id = id;
        this.grades = grades;
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

    get avgGrade() {
        let sum = 0;
        for (let i = 0; i < this.grades.length; i++) {
            sum += this.grades[i];
        }
        return sum / this.grades.length;
    }

}
//check
const f = new Student(1, "Stefan", "Siara", [1, 1, 1, 1]);
f.fullName = "Stefan Siarzewski";
console.log("Name - " + f.fullName + " | Avg grade - " + f.avgGrade);
console.log(f.print);


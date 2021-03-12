function Student(name, surname, id, grades) {
    this.name = name;
    this.surname = surname;
    this.id = id;
    this.grades = grades;
    this.print = function () {
        let avgGrade = 0.0;
        for (let i = 0; i < this.grades.length; i++) {
            avgGrade += this.grades[i];
        }
        avgGrade = avgGrade / this.grades.length;
        return (
            "Id - " +
            this.id +
            " Name - " +
            this.name +
            " " +
            this.surname +
            " Avg grade - " +
            avgGrade
        );
    };
}
//check
const b = new Student("Franek", "Kimono", 1, [2, 5, 3, 5]);
console.log(b.print());

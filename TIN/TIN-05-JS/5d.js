function Student(id, grades) {
    this.name = "";
    this.surname = "";
    this.id = id;
    this.grades = grades;
}

Object.defineProperties(Student.prototype, {
    fullName: {
        get: function () {
            return this.name + " " + this.surname;
        },

        set: function (name) {
            let splitname = name.split(" ");
            this.name = splitname[0] || "";
            this.surname = splitname[1] || "";
        }
    },

    avgGrade: {
        get: function () {
            let sum = 0;
            for (let i = 0; i < this.grades.length; i++) {
                sum += this.grades[i];
            }
            return sum / this.grades.length;
        }
    },

    print: {
        get: function () {
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
});
//check
const d = new Student(1, [5, 5, 5, 5]);
d.fullName = "Jurek Kiler";
console.log("Name - " + d.fullName + " | Avg grade - " + d.avgGrade);
console.log(d.print);

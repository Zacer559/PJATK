const courses = ["TIN", "BYT", "ZPR"]; // <3 <3 <3

function Student(name, surname, id) {
    this.name = name;
    this.surname = surname;
    this.id = id;
}

Student.prototype.courses = courses;
//check
const c = new Student();
console.log(c.courses);
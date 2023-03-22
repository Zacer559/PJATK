class Animal:
    def __init__(self, gender, isAlive=True):
        self.gender = gender
        self.isAlive = isAlive

    @property
    def genus(self):
        raise NotImplementedError("Subclass must implement abstract method")

    def breed(self, partner):
        try:
            if partner.gender == self.gender:
                raise ValueError("Cannot breed with animal of same gender")
            if partner.genus != self.genus:
                raise ValueError("Cannot breed with animal of different genus")
            return type(self)(self.gender)
        except AttributeError:
            print("Attribute not found")
            return None


class Dog(Animal):
    def __init__(self, gender, isAlive=True):
        super().__init__(gender, isAlive)

    @property
    def genus(self):
        return "Canis"

    def woof(self):
        return "woof woof"

    def __str__(self) -> str:
        return "Dog, genius {}, gender {}, isAlive {}".format(self.genus, self.gender, self.isAlive)


class Cat(Animal):
    def __init__(self, gender, isAlive=True):
        super().__init__(gender, isAlive)

    @property
    def genus(self):
        return "Felis"

    def purr(self):
        return "purr"

    def __str__(self) -> str:
        return "Cat, genius {}, gender {}, isAlive {}".format(self.genus, self.gender, self.isAlive)


def mainAnimals():
    dog1 = Dog("male")
    dog2 = Dog("female")
    # Creating instance of Dog using breed method.
    puppy = dog1.breed(dog2)
    print(puppy)

    cat1 = Cat("male")
    cat2 = Cat("female")
    # Creating instance of Cat using breed method.
    kitten = cat1.breed(cat2)
    print(kitten)


class Worker:
    def __init__(self, number, name, age, salary):
        self.number = number
        self.name = name
        self.age = age
        self.salary = salary


# There are no arrays in python, so I'm using default lists instead of importing external library with arrays.
def average_salary(workers):
    total_salary = 0
    for worker in workers:
        total_salary = total_salary + worker.salary
    return total_salary / len(workers)


# There are no arrays in python, so I'm using default lists instead of importing external library with arrays.
def compare_earnings(workers, age):
    younger_earnings = 0
    younger_count = 0
    older_earnings = 0
    older_count = 0
    for worker in workers:
        if worker.age < age:
            younger_earnings = younger_earnings + worker.salary
            younger_count = younger_count + 1
        else:
            older_earnings = older_earnings + worker.salary
            older_count = older_count + 1
    if younger_count == 0:
        return "There are no workers younger than {}.".format(age)
    elif older_count == 0:
        return "There are no workers older than {}.".format(age)
    else:
        younger_average = younger_earnings / younger_count
        older_average = older_earnings / older_count
        return younger_average / older_average


def mainWorkers():
    workers = [
        Worker(1, "Adam", 1983, 1500),
        Worker(2, "Anna", 1981, 1700),
        Worker(3, "Błażej", 1990, 1800),
        Worker(4, "Beata", 1992, 1600),
        Worker(5, "Czesław", 1980, 2000),
        Worker(6, "Cecylia", 1983, 2100),
        Worker(7, "Daniel", 1976, 1900)
    ]

    print("Average salary of workers is: " + average_salary(workers).__str__())
    print("Younger people are earning " + compare_earnings(workers, 1985).__str__() + " salary of older people")


def main():
    mainAnimals()
    mainWorkers()


if __name__ == "__main__":
    main()

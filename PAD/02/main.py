def taskA():
    # Takinbg input
    arr = list(map(int, input("Enter the array of integers separated by space: ").split()))

    choice = input("Enter 's' to square or 'c' to cube each number in the array: ")
    # Calculations
    if choice == 's':
        result = list(map(lambda x: x ** 2, arr))
    elif choice == 'c':
        result = list(map(lambda x: x ** 3, arr))
    else:
        print("Bad choice")
    print(result)


# TaskB
class Game:
    def __init__(self, num_players):
        self.number_of_players = num_players

    def play(self):
        print("The game has started.")


class Hangman(Game):
    def __init__(self):
        super().__init__(2)
        self.levels = {"beginner": 8, "intermediate": 5, "advanced": 3}
        self.word_to_guess = ""
        self.guessed_letters = set()
        self.max_attempts = 0

    def play(self):
        # accessing protected method
        super().play()
        print("Welcome to Hangman!")
        # getting information from players
        level = input("Please select a difficulty level (beginner/intermediate/advanced): ")
        while level.lower() not in self.levels:
            level = input("Please select a valid difficulty level (beginner/intermediate/advanced): ")
        self.max_attempts = self.levels[level.lower()]
        self.word_to_guess = input("Please enter a word for the other player to guess: ")
        while not self.word_to_guess.isalpha():
            self.word_to_guess = input("Please enter a valid word for the other player to guess: ")
        self.word_to_guess = self.word_to_guess.lower()

        # game loop
        while True:
            print(self.get_current_state())
            letter = input("Guess a letter: ").lower()
            if len(letter) != 1 or not letter.isalpha():
                print("Invalid input. Please enter a single letter.")
                continue
            if letter in self.guessed_letters:
                print("You've already guessed that letter.")
                continue
            self.guessed_letters.add(letter)
            if letter in self.word_to_guess:
                print(f"Correct! '{letter}' is in the word.")
                if self.is_game_won():
                    print("Congratulations, you've won!")
                    break
            else:
                print(f"Incorrect. '{letter}' is not in the word.")
                self.max_attempts -= 1
                if self.max_attempts == 0:
                    print("Game over! You've run out of attempts.")
                    break

    def is_game_won(self):
        for letter in self.word_to_guess:
            if letter not in self.guessed_letters:
                return False
        return True

    def get_current_state(self):
        state = ""
        for letter in self.word_to_guess:
            if letter in self.guessed_letters:
                state += letter + " "
            else:
                state += "_ "
        return state.rstrip()


def taskB():
    hangman_game = Hangman()
    hangman_game.play()


if __name__ == '__main__':
    taskA()
    taskB()

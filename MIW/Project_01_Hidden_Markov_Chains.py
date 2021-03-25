import numpy
import matplotlib.pyplot as plt

possibleSigns = ['Rock', 'Paper', 'Scissors']
Initial_probability = [0.23, 0.44, 0.33]
sumOfPointsEarned = 0
xCoordinates = []
yCoordinates = []
learningStep = 0.0002

Transition_matrix = [
    #   Rock | Paper | Scissors
    [0.5, 0.43, 0.07],  # Rock
    [0.24, 0.36, 0.40],  # Paper
    [0.1, 0.6, 0.3]  # Scissors
]

Emission_matrix = [
    #  0  |  1  |  -1
    [0.28, 0.42, 0.3],  # Rock
    [0.15, 0.23, 0.62],  # Paper
    [0.4, 0.5, 0.1]  # Scissors
]


# Creating initial state
state = numpy.random.choice(possibleSigns, replace=True, p=Initial_probability)
print("        Predictions: | - result - |    - state -   | - response - ")
for x in range(1, 5000):
    response = ''
    # Generating response according to actual state
    if state == 'Rock':
        response = numpy.random.choice(possibleSigns, replace=True, p=Transition_matrix[0])
    elif state == 'Paper':
        response = numpy.random.choice(possibleSigns, replace=True, p=Transition_matrix[1])
    else:
        response = numpy.random.choice(possibleSigns, replace=True, p=Transition_matrix[2])
    # Checking that the answer was correct or not
    if state == 'Rock':
        # Generating new state
        state = numpy.random.choice(possibleSigns, replace=True, p=Emission_matrix[0])
        if response == 'Rock':
            result = 0
        elif response == 'Paper':
            result = 1
            # If win checking the transition matrix and if possible updating it
            if (Transition_matrix[0][1] + learningStep) < 1 and (Transition_matrix[0][0] - learningStep / 2) > 0 \
                    and (Transition_matrix[0][2] - learningStep / 2) > 0:
                Transition_matrix[0][1] += learningStep
                Transition_matrix[0][0] -= learningStep / 2
                Transition_matrix[0][2] -= learningStep / 2
        else:
            result = -1
    elif state == 'Paper':
        # Generating new state
        state = numpy.random.choice(possibleSigns, replace=True, p=Emission_matrix[1])
        if response == 'Rock':
            result = -1
        elif response == 'Paper':
            result = 0
        else:
            result = 1
            # If win checking the transition matrix and if possible updating it
            if (Transition_matrix[1][2] + learningStep) < 1 and (Transition_matrix[1][0] - learningStep / 2) > 0 \
                    and (Transition_matrix[1][1] - learningStep / 2) > 0:
                Transition_matrix[1][2] += learningStep
                Transition_matrix[1][0] -= learningStep / 2
                Transition_matrix[1][1] -= learningStep / 2
    else:
        # Generating new state
        state = numpy.random.choice(possibleSigns, replace=True, p=Emission_matrix[2])
        if response == 'Rock':
            result = 1
            # If win checking the transition matrix and if possible updating it
            if (Transition_matrix[2][0] + learningStep) < 1 and (Transition_matrix[2][1] - learningStep / 2) > 0 \
                    and (Transition_matrix[2][2] - learningStep / 2) > 0:
                Transition_matrix[2][0] += learningStep
                Transition_matrix[2][1] -= learningStep / 2
                Transition_matrix[2][2] -= learningStep / 2
        elif response == 'Paper':
            result = -1
        else:
            result = 0
    print('           {0: <8}  |     {1: <3}    |    {2: <8}    |   {3: <8}   '
          .format(x, result, state, response))
    sumOfPointsEarned = sumOfPointsEarned + result
    xCoordinates.append(x)
    yCoordinates.append(sumOfPointsEarned)

# Making graph and printing tables
print('Transformation matrix is:')
print(Transition_matrix)
print('Emission matrix is:')
print(Emission_matrix)
plt.plot(xCoordinates, yCoordinates)
plt.xlabel("Number of steps")
plt.ylabel("Summary of decisions")
plt.show()

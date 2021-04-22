import numpy
from numpy import arange
from scipy.optimize import curve_fit
from matplotlib import pyplot
from sklearn.metrics import accuracy_score


def loadData():
    i = 4
    file = open('dane/dane{}.txt'.format(i), 'r')
    x = []
    y = []
    for z in file:
        point = z.split(" ")
        point.remove('\n')
        x.append(float(point[0]))
        y.append(float(point[1]))
    return x, y


def accuracy(tTrue, yPredicted, exactness):
    recognizedCorrectly = 0
    allRecognized = 0
    for x in tTrue:
        if numpy.abs(tTrue[allRecognized] - yPredicted[allRecognized]) < exactness:
            recognizedCorrectly = recognizedCorrectly + 1
        allRecognized = allRecognized + 1
    print("Accuracy was equal {:.2f}%".format((recognizedCorrectly / allRecognized)*100))


def objective(x, a, b):
    return a * x + b


def objective2(x, a, b, c, d, e, f):
    return (a * x) + (b * x ** 2) + (c * x ** 3) + (d * x ** 4) + (e * x ** 5) + f


def task1():
    print("TASK 1:")
    x, y = loadData()
    popt, _ = curve_fit(objective, x, y)
    a, b = popt
    pyplot.scatter(x, y)
    x_line = arange(min(x), max(x), 0.1)
    y_line = objective(x_line, a, b)
    pyplot.plot(x_line, y_line, '--', color='red')
    accuracy(y[:-1], y_line, 0.5)
    pyplot.show()


def task2():
    print("TASK 2:")
    x, y = loadData()
    popt, _ = curve_fit(objective2, x, y)
    a, b, c, d, e, f = popt
    pyplot.scatter(x, y)
    x_line = arange(min(x), max(x), 0.1)
    y_line = objective2(x_line, a, b, c, d, e, f)
    pyplot.plot(x_line, y_line, '--', color='red')
    accuracy(y[:-1], y_line, 0.5)
    pyplot.show()


task1()
task2()

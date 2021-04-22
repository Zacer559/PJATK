from sklearn.model_selection import train_test_split
from sklearn import datasets
import numpy as np
from Plot import plot_decision_regions
from Classifier import Perceptron, LogisticRegression
from Classifier import MultiClassifier


def acc(realState, predictedState):
    return np.sum(realState == predictedState) / len(predictedState)


def get_data(random_state=42, centers=3):
    X, y = datasets.make_blobs(n_samples=150, n_features=2, centers=centers, cluster_std=1.05,
                               random_state=random_state)
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=random_state)
    return X_train, X_test, y_train, y_test


def main():
    # Perceptron
    X_train, X_test, y_train, y_test = get_data()
    multiClassifier = MultiClassifier(Perceptron)
    multiClassifier.fit(X_train, y_train)
    predictions = multiClassifier.predict(X_test)
    print(acc(y_test, predictions))
    plot_decision_regions(X_train, y_train, multiClassifier)

    # Linear
    X_train, X_test, y_train, y_test = get_data()
    multiClassifier = MultiClassifier(LogisticRegression)
    multiClassifier.fit(X_train, y_train)
    predictions = multiClassifier.predict(X_test)
    print(acc(y_test, predictions))
    plot_decision_regions(X_train, y_train, multiClassifier)


if __name__ == '__main__':
    main()

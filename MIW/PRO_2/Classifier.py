import numpy as np


class Perceptron:
    def __init__(self, learning_rate=0.01, n_iters=1000):
        self.lr = learning_rate
        self.n_iters = n_iters
        self.weights = None
        self.bias = None

    def fit(self, X, y):
        n_samples, n_features = X.shape
        self.weights = np.zeros(n_features)
        self.bias = 0
        y_ = np.array([1 if i > 0 else 0 for i in y])

        for _ in range(self.n_iters):
            for idx, x_i in enumerate(X):
                output = np.dot(x_i, self.weights) + self.bias
                y_predict = self._step_function(output)

                update = self.lr * (y_[idx] - y_predict)
                self.weights += update * x_i
                self.bias += update

    def predict(self, X):
        output = np.dot(X, self.weights) + self.bias
        y_predict = self._step_function(output)
        return y_predict

    def _step_function(self, x):
        return np.where(x >= 0, 1, 0)


class LogisticRegression:
    def __init__(self, learning_rate=0.001, n_iters=1000):
        self.lr = learning_rate
        self.n_iters = n_iters
        self.weights = None
        self.bias = None

    def fit(self, X, y):
        n_samples, n_features = X.shape
        self.weights = np.zeros(n_features)
        self.bias = 0
        for _ in range(self.n_iters):
            linear_model = np.dot(X, self.weights) + self.bias
            y_predict = self._sigmoid(linear_model)

            dw = (1 / n_samples) * np.dot(X.T, y_predict - y)
            db = (1 / n_samples) * np.sum(y_predict - y)

            self.weights -= self.lr * dw
            self.bias -= self.lr * db

    def _sigmoid(self, x):
        return 1 / (1 + np.exp(-x))

    def predict(self, X):
        linear_model = np.dot(X, self.weights) + self.bias
        y_predict = self._sigmoid(linear_model)
        if isinstance(y_predict, np.float64):
            if y_predict > 0.5:
                return 1
            else:
                return 0
        else:
            y_predict_cls = [1 if i > 0.5 else 0 for i in y_predict]
            return y_predict_cls


class MultiClassifier:
    def __init__(self, classifier, learning_rate=0.01, n_iters=1000):
        self.classifier = classifier
        self.lr = learning_rate
        self.n_iters = n_iters
        self.info = None

    def fit(self, X, y):
        self.info = []

        unique_class_names = np.unique(y)

        for class_name in unique_class_names:
            y_ = np.array([1 if y_value == class_name else 0 for y_value in y])

            temp_dict = {'Class name': class_name,
                         'Classifier': self._single_classifier(X, y_)}

            self.info.append(temp_dict)

    def _single_classifier(self, X, y):
        cl = self.classifier(self.lr, self.n_iters)
        cl.fit(X, y)
        return cl

    def predict(self, X):
        results = []
        for x in X:
            for i in self.info:
                c = i['Classifier']
                prediction = c.predict(x)
                if prediction == 1:
                    results.append(i['Class name'])
                    break
            else:
                results.append(self.info[-1]['Class name'])

        return np.array(results)

import sklearn
from sklearn import svm
from sklearn import datasets
from sklearn import metrics
from sklearn.neighbors import KNeighborsClassifier

cancer = datasets.load_breast_cancer()
#print("Features: ", cancer.feature_names)
#print("Labels: ", cancer.target_names)

x = cancer.data  # All of the features
y = cancer.target  # All of the labels

x_train, x_test, y_train, y_test = sklearn.model_selection.train_test_split(x, y, test_size=0.2)

#print(x_train[:5], y_train[:5])

# clf = svm.SVC(kernel = "linear", C =2) # linear # c= 2 soft margin
clf = KNeighborsClassifier(n_neighbors=9)

clf.fit(x_train, y_train)

y_pred = clf.predict(x_test)

acc = metrics.accuracy_score(y_test, y_pred)



print(acc)
# Step 1: Randomly pick K points to place K centroids Step 2: Assign all of the data points to the centroids by distance.
# The closest centroid to a point is the one it is assigned to. Step 3: Average all of the points belonging to each
# centroid to find the middle of those clusters (center of mass). Place the corresponding centroids into that position.
# Step 4: Reassign every point once again to the closest centroid. Step 5: Repeat steps 3-4 until no point changes
# which centroid it belongs to.
import numpy as np
import sklearn
from sklearn.preprocessing import scale
from sklearn.datasets import load_digits
from sklearn.cluster import KMeans
from sklearn import metrics

digits = load_digits()
data = scale(digits.data) #cale our data down
y = digits.target

k = 10
samples, features = data.shape

def bench_k_means(estimator, name, data):
    estimator.fit(data)
    print('%-9s\t%i\t%.3f\t%.3f\t%.3f\t%.3f\t%.3f\t%.3f'
          % (name, estimator.inertia_,
             metrics.homogeneity_score(y, estimator.labels_),
             metrics.completeness_score(y, estimator.labels_),
             metrics.v_measure_score(y, estimator.labels_),
             metrics.adjusted_rand_score(y, estimator.labels_),
             metrics.adjusted_mutual_info_score(y,  estimator.labels_),
             metrics.silhouette_score(data, estimator.labels_,
                                      metric='euclidean')))

clf = KMeans(n_clusters=k, init="random", n_init=10)
bench_k_means(clf, "1", data)
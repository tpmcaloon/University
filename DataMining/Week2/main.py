import numpy as np


# a)
arr = np.arange(100).reshape(10,10).astype(float)
# add 0.0 to the array of integers
arr += 0.0
# print the main diagonal
print(np.diag(arr))

# b)
# Add the squares of numbers on the main diagonal
sum_squares = np.sum(arr.diagonal()**2)
print(sum_squares)

# c)
# Compute the mean, median, and the standard deviation of the 1st column
mean = np.mean(arr[:,0])
median = np.median(arr[:,0])
std = np.std(arr[:,0])
print("mean =", mean, "median =", median, "standard deviation =", std)

# d)
# Modify the 1st column by subtracting the mean and dividing by standard deviation of the column
arr[:,0] = (arr[:,0] - mean) / std
# print the mean and the standard deviation of the 1st column
mean_col1 = np.mean(arr[:,0])
std_col1 = np.std(arr[:,0])
print("mean column1 =", mean_col1, "standard deviation column1 =", std_col1)

# e)
# Rescale the 2nd column to the [0,1] interval
min_col2 = np.min(arr[:,1])
max_col2 = np.max(arr[:,1])
arr[:,1] = (arr[:,1] - min_col2) / (max_col2 - min_col2)
print(arr[:,1])

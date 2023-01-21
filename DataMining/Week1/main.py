def count_negatives(lst):
    count = 0
    for item in lst:
        if item < 0:
            count += 1
    return count


lst = [-5, 12, -7, 0, 14]


def count_missing_values(list):
    count = 0
    for item in list:
        if item == None or item == '':
            count += 1
    return count


# Driver code
list = [1, 2, 3, '', None, '', 6, 7, '']

print("Number of Negative Numbers in List: " + str(count_negatives(lst)))
print("Number of Missing Values in List: " + str(count_missing_values(list)))

# This code counts the number of missing values in a given list. The function count_negative values takes a list as
# an argument and iterates over it to check if any of the items is negative. If so, it increases the count by 1 and
# returns it. The driver code creates a sample list and prints out the result of counting the negatives.

#%%

import os
import collections
import math
import matplotlib.pyplot as plt
from cmath import sqrt

d = {}

def convert_file_to_dict(filename, d):
    input_file = open(filename, 'r', encoding = "utf-8")
    for line in input_file:
    #data = input_file.read()
        lst = line.split(' ')
        for i in lst:
                if i == "\n" or i == "":
                    continue
                i = i.replace("\n", "")
                if i in d:
                    d[i] += 1
                else:
                    d[i] = 1
    input_file.close()
    return

def documents_containing_w(word):
    count = 0
    for filename in os.listdir(path):
        temp_d = {}
        convert_file_to_dict("news\\" + filename, temp_d)
        if word in temp_d:
            count += 1
    return count

def find_tf_idf(file, word):
    temp_d = {}
    convert_file_to_dict(file, temp_d)
    temp_d = collections.OrderedDict(reversed(sorted(temp_d.items(), key=lambda t: t[1])))
    tf = temp_d[word]/temp_d[next(iter(temp_d))]
    idf = math.log10(len(os.listdir(path))/documents_containing_w(word))
    #print(len(os.listdir(path)), documents_containing_w(word))
    #print(tf)
    #print(idf)
    return tf * idf

path = os.getcwd() + "\\news"
for filename in os.listdir(path):
    convert_file_to_dict("news\\" + filename, d);

d = collections.OrderedDict(reversed(sorted(d.items(), key=lambda t: t[1])))
count = 0
for i in d:
    count += d[i]

#print(d)
#QUESTION 1:1
'''How many computer word tokens (occurrences) are there in the corpus?'''
print(count)
'''How many computer word types (distinct words) are there in the corpus?'''
print(len(d))

#QUESTION 1:2
'''Sort the word types by their counts in the corpus, from large to small. 
List the top 20 word types and their counts.'''
lst = []
for i in range(20):
    t = (list(d)[i], d[list(d)[i]])
    lst.append(t)
print(lst)

#QUESTION 1:3
'''Plot r on the x-axis and c on the y-axis, namely each (ri, ci) 
is a point in that 2D space (you can choose to connect the points or not).'''
x = []
y = []
num = 1
for i in d:
    x.append(num)
    y.append(d[i])
    num += 1

plt.scatter(x,y, s = 5)
plt.xlabel('rank')
plt.ylabel('count')
plt.show()

'''Plot log(r) on the x-axis and log(c) on the y-axis. Use e or 10 as base'''
a = list(map(lambda l: math.log(l), x))
b = list(map(lambda l: math.log(l), y))
plt.xlabel('log(rank)')
plt.ylabel('log(count)')
plt.scatter(a, b, s = 5)
plt.show()

#QUESTION 1:4

print(find_tf_idf("news\\098.txt", "contract"))


'''Find top 10 words with the highest tf · idf in the text “098.txt”.'''
tf_idf098 = {}
dict_098 = {}
convert_file_to_dict("news\\098.txt", dict_098)
for i in dict_098:
    tf_idf098[i] = find_tf_idf("news\\098.txt", i)
tf_idf098 = collections.OrderedDict(reversed(sorted(tf_idf098.items(), key=lambda t: t[1])))
lst = []
for i in range(10):
    lst.append(list(tf_idf098)[i])
print(lst)

#QUESTION 1:5
'''What is the cosine similarity of v1 and v2 if we use bag-of-words representation?'''
dict_297 = {}
convert_file_to_dict("news\\297.txt", dict_297)

count098 = 0
v1_dict = {}
for i in dict_098:
    count098 += dict_098[i]
for i in dict_098:
    v1_dict[i] = float(dict_098[i]/count098)

count297 = 0
v2_dict = {}
for i in dict_297:
    count297 += dict_297[i]
for i in dict_297:
    v2_dict[i] = float(dict_297[i]/count297)

x = sum(v1_dict[key]*v2_dict.get(key, 0) for key in v1_dict)
y = sqrt(sum(v1_dict[key]*v1_dict[key] for key in v1_dict))
z = sqrt(sum(v2_dict[key]*v2_dict[key] for key in v2_dict))
cos_BOW = x/y/z
print(cos_BOW)

'''What is the cosine similarity if we use tf · idf?'''
tf_idf297 = {}
dict_297 = {}
convert_file_to_dict("news\\297.txt", dict_297)
for i in dict_297:
    tf_idf297[i] = find_tf_idf("news\\297.txt", i)
    
x = sum(tf_idf098[key]*tf_idf297.get(key, 0) for key in tf_idf098)
y = sqrt(sum(tf_idf098[key]*tf_idf098[key] for key in tf_idf098))
z = sqrt(sum(tf_idf297[key]*tf_idf297[key] for key in tf_idf297))
cos_tf_idf = x/y/z
print(cos_tf_idf)
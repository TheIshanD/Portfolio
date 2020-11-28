#from sklearn.naive_bayes import MultinomialNB
#from sklearn.svm import SVR
from sklearn.naive_bayes import GaussianNB
from csv import reader 
from statistics import mean
from numpy import array
from numpy import float64
from matplotlib import style
import matplotlib.pyplot as plt

style.use('ggplot')

print("What is the path of your file?")
print("")
print("Please type in the full path with double backward slashes. ")
print("")
FilePath=input("")
print("")
#C:\Users\Rishob\Documents\Ishan\Python\MyPythonDatasets\testdummycsv.csv
#C:\Users\Rishob\Documents\Ishan\Python\MyPythonDatasets\ScienceFairCancerDataCsv.csv
#r"C:\Users\Rishob\Documents\Ishan\Python\MyPythonDatasets\ScienceFairCancerData3CSV.csv"

test_output = []
test_data = []
i=0
label_names=[]
training_data=[]
training_output=[]
numb_data = 0
num_training_data=0
numb_features=0

def best_fit_slope_and_intercept(xs,ys):
    m = (((mean(xs)*mean(ys)) - mean(xs*ys)) /
         ((mean(xs)*mean(xs)) - mean(xs*xs)))
    b = mean(ys) - m*mean(xs)
    return m, b

def squared_error(preds,ys_line):
    return sum((ys_line - preds) * (ys_line - preds))

def coefficient_of_determination(preds,ys_line):
    y_mean_line = [mean(preds) for y in preds]
    squared_error_regr = squared_error(preds, ys_line)
    squared_error_y_mean = squared_error(preds, y_mean_line)
    return 1 - (squared_error_regr/squared_error_y_mean)


#Reading The CSV
with open(r"C:\Users\Rishob\Documents\Ishan\Python\MyPythonDatasets\ScienceFairCancerData3CSV.csv") as csvfile:  
    readCSV = reader(csvfile, delimiter=',')
    rownumber=0
    for row in readCSV:
        rownumber=rownumber+1
        if rownumber == 1:
            numb_data=int(row[0])
            num_training_data=int(row[1])
            numb_features=int(row[2])
            continue
        
        if rownumber==2:
            for i in range(numb_features):
                label_names.append(row[i])
            output_label= row[numb_features]
            continue
        
        if rownumber < num_training_data+3 :
            training_output.append(row[numb_features])
            a=[]
            for j in range(numb_features):
                a.append(float(row[j]))
            training_data.append(a)
            continue

        if rownumber < numb_data +3 :
            test_output.append(float(row[numb_features]))
            b=[]
            for j in range(numb_features):
                b.append(float(row[j]))
            test_data.append(b)





# Initialize our classifier
gnb = GaussianNB()
#gnb = MultinomialNB()
#gnb =SVR()


# Train our classifier
model = gnb.fit(training_data, training_output)


# Make predictions
preds = model.predict(test_data)

preds = array(preds, dtype = float64)
test_output = array(test_output, dtype = float64)


m,b = best_fit_slope_and_intercept(test_output,preds)


regression_line= [(m*x)+b for x in test_output]
regression_line_norm=[x for x in test_output]


r_squared_norm = coefficient_of_determination(preds, regression_line_norm)
r_squared = coefficient_of_determination(preds, regression_line)

r_norm = r_squared_norm ** 0.5
r = r_squared ** 0.5
rounded_r_norm = round(r_norm,3)
rounded_r = round(r,3)

print("Number of training points:", len(training_output))
print("Number of testing points:", len(test_output))
print("R of the perfect line:",r_norm)
print("R of the best fit line",r)
print(max(preds))

#for c in range(len(test_data)):
#    print(test_data[c], " ", test_output[c], " ", preds[c],  test_output[c]-preds[c])

plt.scatter(test_output,preds)
plt.title("Accuracy of Machine Learning for Predicting Cancer Risk")
plt.ylabel("Predicted Risk ")
plt.xlabel("Actual Risk")
plt.text((max(test_output)-min(test_output))*1/6,max(preds)*5/6,"R for Perfect Line:")
plt.text((max(test_output)-min(test_output))*1/6,(max(preds)*5/6)-(max(preds)*1/20),rounded_r_norm)
plt.plot(test_output,regression_line,color="y")
plt.plot(test_output,regression_line_norm,color="g")
plt.show()


    





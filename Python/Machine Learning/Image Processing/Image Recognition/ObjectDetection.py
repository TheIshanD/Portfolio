import numpy as np
import cv2
from time import sleep
from os.path import exists
from os import makedirs
import pickle

image_get = input("Will you take pictures? (y/n)?")

image_library= []
face_cascade = cv2.CascadeClassifier('haarcascade_frontalface_default.xml')
recognizer = cv2.face.LBPHFaceRecognizer_create()
recognizer.read("trainer.yml")

labels = {}
with open("labels.pickle","rb")as f:
        og_labels = pickle.load(f)
        labels = {v:k for k,v in og_labels.items()}
        print(labels)


def array_sum(arr):
        d = 0
        for k in range(len(arr)):
                d = d + arr[k]
        return d
        
        

cap = cv2.VideoCapture(0)

def cap_image(image_library,name):
        picture_loop=0
        newpath = "images"+"\\"+name
        if not exists(newpath):
            makedirs(newpath)
        while True:
                if picture_loop < 50: 
                        sleep(1/2)
                        image_library.append("images\\"+name+"\my-image"+str(picture_loop)+".png")
                        ret, frame = cap.read()
                        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
                        roi_gray = gray[y:y+h, x:x+w]
                        cv2.imwrite(image_library[picture_loop],roi_gray )
                        picture_loop=picture_loop+1
                else:
                        break
max_conf = 0

stats = []
for d in range(len(labels)):
        stats.append(0)
        
while True:
    ret, frame = cap.read()
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    faces = face_cascade.detectMultiScale(gray, 1.5, 5)

    for (x,y,w,h) in faces:
        cv2.rectangle(frame,(x,y),(x+w,y+h),(255,0,0),2)
        roi_gray = gray[y:y+h, x:x+w]
        roi_color = frame[y:y+h, x:x+w]
        id_, conf = recognizer.predict(roi_gray)
        if conf >= 50:
                if conf > max_conf:
                        max_conf=conf
                        person = labels[id_]
                
                stats[id_] = stats[id_] + 1
                
                print(conf)
                print(labels[id_])
                font = cv2.FONT_HERSHEY_SIMPLEX
                name = labels[id_]
                color = (0,255,0)
                stroke = 2
                cv2.putText(frame,name.capitalize(),(x,y-10),font,1,color,stroke,cv2.LINE_AA)
                
        if image_get == "y":
                name="Auro"
                cap_image(image_library,name)
                image_get=False
                
    cv2.imshow('frame',frame)
    k = cv2.waitKey(30) & 0xff
    if k == 27:
        break

print("-----------")
print(str(person),str(round(max_conf,3)))
print("-----------")
print("STATISTICS")

for d in range(len(labels)):
        print(str(labels[d]),":",str(stats[d]),":", str(round((stats[d]/(array_sum(stats))*100),3)),"%")
        
cap.release()
cv2.destroyAllWindows()



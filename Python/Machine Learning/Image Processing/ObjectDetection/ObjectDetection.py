import numpy as np
import cv2
import winsound
from pygame import mixer
from time import sleep
mixer.init()

CamSmooth=5

Pface_cascade = cv2.CascadeClassifier('haarcascade_profileface.xml')
face_cascade = cv2.CascadeClassifier('haarcascade_frontalface_default.xml')
Reye_cascade = cv2.CascadeClassifier('haarcascade_righteye_2splits.xml')
Leye_cascade = cv2.CascadeClassifier('haarcascade_lefteye_2splits.xml')
mouth_cascade = cv2.CascadeClassifier("haarcascade_smile.xml")
def playSound(path):
        mixer.music.load(path)
        mixer.music.play()
def rectangle(img,nx,ny,nw,nh,color,thickness,textsize,text,ratio,measuredistance):
    global danum
    global CamSmooth
    font = cv2.FONT_HERSHEY_SIMPLEX
    if measuredistance == True:
        DistancetoObjectInch = round(ratio/nw)
        DistancetoObject = round(ratio/(nw*12))
        cv2.putText(img,text +" ~"+ str(DistancetoObjectInch)+" Inches",(nx,ny-10),font,textsize,color)
        if danum%(20*CamSmooth)==0:
                if text=="Face":
                        if DistancetoObject == 0:
                                playSound("A_0Face.wav")
                        elif DistancetoObject ==1:
                                playSound("A_1Face.wav")
                        elif DistancetoObject ==2:
                                playSound("A_2Face.wav")
                        elif DistancetoObject ==3:
                                playSound("A_3Face.wav")
                        elif DistancetoObject ==4:
                                playSound("A_4Face.wav")
                        elif DistancetoObject ==5:
                                playSound("A_5Face.wav")
                        elif DistancetoObject ==6:
                                playSound("A_6MFace.wav")

    else:
        cv2.putText(img,text,(nx,ny-10),font,textsize,color)
    cv2.rectangle(img,(nx,ny),(nx+nw,ny+nh),color,thickness)
    
def detection(TheObject,img,color,thickness,textsize,text,ratio,measuredistance):
    for (x,y,w,h) in TheObject:
        rectangle(img,x,y,w,h,color,thickness,textsize,text,ratio,measuredistance)

cap = cv2.VideoCapture(0)
danum=CamSmooth

while True:
    ret, img = cap.read()
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    
    if danum%CamSmooth==0:
        Pfaces=Pface_cascade.detectMultiScale(gray,1.1,10)
        faces = face_cascade.detectMultiScale(gray, 1.1,25)
    danum+=1
    detection(Pfaces,img,(255,0,0),3,0.5,"Face",2860,True)
    for (x,y,w,h) in faces:
        rectangle(img,x,y,w,h,(255,0,0),3,0.5,"Face",2860,True)

        roi_gray = gray[y:y+h, x:x+w]
        roi_color = img[y:y+h, x:x+w]
        if danum%CamSmooth==1:
            Leyes = Leye_cascade.detectMultiScale(roi_gray,1.1,15)
            Reyes = Reye_cascade.detectMultiScale(roi_gray,1.1,15)
            mouth = mouth_cascade.detectMultiScale(roi_gray,1.1,75)
        detection(Reyes,roi_color,(0,255,0),3,0.5,"Eye",0,False)
        detection(Leyes,roi_color,(0,255,0),3,0.5,"Eye",0,False)

        detection(mouth,roi_color,(0,0,255),3,0.5,"Mouth",0,False)
            
    cv2.imshow("img",img)
    
    k = cv2.waitKey(30) & 0xff
    if k == 27:
        break
    
cap.release()
cv2.destroyAllWindows()

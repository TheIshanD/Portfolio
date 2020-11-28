import os
from PIL import Image
import numpy as np
import cv2
import pickle

BASE_DIR = os.path.dirname(os.path.abspath(__file__))
image_dir = os.path.join(BASE_DIR,"images")

face_cascade = cv2.CascadeClassifier('haarcascade_frontalface_default.xml')

recognizer = cv2.face.LBPHFaceRecognizer_create()

current_id = 0
label_ids = {}
y_labels = []
x_train = []

for root, dirs, files in os.walk(image_dir):
    
    for file in files:
        
        if file.endswith("png"):
            path = os.path.join(root,file)
            label=  os.path.basename(os.path.dirname(path)).replace(" ","-").lower()
            if not label in label_ids:
                label_ids[label] = current_id
                current_id = current_id + 1
                print(label)
                print(current_id)
                
            id_ = label_ids[label]
            pil_image = Image.open(path).convert("L")
            image_array = np.array(pil_image,"uint8")
            
            faces = face_cascade.detectMultiScale(image_array)
            for (x,y,w,h) in faces:
                roi = image_array[y:y+h, x:x+w]
                x_train.append(roi)
                y_labels.append(id_)


with open("labels.pickle","wb")as f:
    pickle.dump(label_ids, f)
    

recognizer.train(x_train, np.array(y_labels))
recognizer.save("trainer.yml")

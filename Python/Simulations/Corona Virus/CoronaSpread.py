import numpy as np
from PIL import Image
import matplotlib.pyplot as plt
import pickle
from matplotlib import style
import time
import cv2


style.use("ggplot")




# ACCTUALLY THE SQUARE ROOT OF THE NUMBER OF PEOPLE
PEOPLE =  2

SIDE_LEN = PEOPLE * 3

SHOW_EVERY = 1

d = {1:(255,0,0),
     2:(0,255,0),
     3:(0,0,255)}


def removeDead(theArr):
    continuing=True
    deaths=0
    while(continuing):
        for blobID in range(len(theArr)):
            theBlob = theArr[blobID]
            if(theBlob.isAlive==False):
                theArr.pop(blobID)
                deaths+=1
                break
            if(blobID>=len(theArr)-1):
                continuing=False
    return deaths

ACTUAL_NUM_PEOPLE= PEOPLE*PEOPLE

def createIncrementalArray(theArr):
    newArr=[]
    for i in range(len(theArr)):
        newArr.append(i)
    return newArr

def createIncrementedArray(theArr):
    if(len(theArr)==0):
        return [0]
    
    for i in range():
        theArr.append(theArr[-1]+1)
    return newArr

def getAllHealthy(theArr):

    count=0
    allHealthy = True
    for blobID in range(len(theArr)):
        if(theArr[blobID].hasDisease):
            allHealthy=False
            count+=1
            
    return allHealthy, count

def getNumSick(theArr):

    count=0
    for blobID in range(len(theArr)):
        if(theArr[blobID].hasDisease):
            count+=1
            
    return count

def printBlobs(theArr):
    for blobID in range(len(theArr)):
        print(theArr[blobID])     
        
class Blob:

    


    def __init__(self,posX,posY):
        self.x = posX
        self.y = posY
        self.hasDisease = False
        self.days = 0
        self.isAlive=True
        self.resistance = 1
        self.socDist = False
        
    def __str__(self):
        return f"{self.x}, {self.y}, {self.hasDisease}"

    def __sub__(self, other):
        return  [self.x - other.x, self.y - other.y]

    def teleport(self):
        self.x = np.random.randint(1,SIDE_LEN-1)
        self.y = np.random.randint(1,SIDE_LEN-1)
        
    def action(self, choice):
        if choice==0:
            self.move(x=1,y=0)
        elif choice == 1:
            self.move(x=-1, y = 0)
        elif choice==2:
            self.move(x=0,y=1)
        elif choice==3:
            self.move(x=0,y=-1)


    def move(self, x = False, y = False):

        self.x+=x
        self.y+=y

        if self.x < 0:
            self.x = 0
        elif self.x > SIDE_LEN-1:
            self.x = SIDE_LEN-1
            
        if self.y < 0:
            self.y = 0
        elif self.y > SIDE_LEN-1:
            self.y = SIDE_LEN-1

    
    def checkDisease(self, other):
        if(other.hasDisease and not self.hasDisease):
            if(abs((self-other)[0])==0 and abs((self-other)[1])==0):
                if(np.random.randint(0,10*self.resistance)==0):
                    self.hasDisease=True
            elif(abs((self-other)[0])<=1 and abs((self-other)[1])<=1):
                if(np.random.randint(0,20*self.resistance)==0):
                    self.hasDisease=True
            elif(abs((self-other)[0])<=2 and abs((self-other)[1])<=2):
                if(np.random.randint(0,30*self.resistance)==0):
                    self.hasDisease=True


    def hasPassedThirty(self,arr,index):
        if(self.hasDisease==True):
            self.days=self.days+1
            if(self.days==40):
                self.hasDisease=False
                self.days = 0
                self.resistance += 1
                self.socDist = False
            



PLAYER_N = 1
FOOD_N = 2

blobArr=[]
for y in range(SIDE_LEN):
    for x in range(SIDE_LEN):
        if((y-1)%3==0 and (x-1)%3==0):
            blobArr.append(Blob(x,y))
            



blobArr[np.random.randint(0,len(blobArr))].hasDisease=True

numBlobs=len(blobArr)

counter=0
simulation=True


deaths=[]
immune = []

peopleWithDisease=[]
while(simulation):
    this=True
    counter+=1
    
    if(counter%SHOW_EVERY==0):
        env = np.zeros((SIDE_LEN,SIDE_LEN,3), dtype= np.uint8)

        sumImmune = 0
        
        for blobID in range(numBlobs):
            theBlob = blobArr[blobID]

            sumImmune+=theBlob.resistance
            
            
            if(theBlob.hasDisease == True):
                COLOR=(0,0,250)
            else:
                if(theBlob.resistance*50>250):
                    COLOR = (250,0,0)
                else:
                    COLOR = (theBlob.resistance*50,250-theBlob.resistance*50,0)

                
            env[theBlob.y][theBlob.x] = COLOR

            if(np.random.randint(0,60)==0):
                theBlob.teleport()
            else:
                theBlob.action(np.random.randint(0,4))


            if(np.random.randint(0,1000)==0 and theBlob.hasDisease): #and theBlob.days >= 5 and theBlob.days <= 20):
                theBlob.isAlive=False


            
            for blobID2 in range(numBlobs):
                if(blobID2==blobID):
                    continue
                theBlob.checkDisease(blobArr[blobID2])

                
            theBlob.hasPassedThirty(blobArr, blobID)
        immune.append(sumImmune/len(blobArr))
        
        deaths.append(removeDead(blobArr))
        
        numBlobs=len(blobArr)
        
        img = Image.fromarray(env, "RGB")
        img = img.resize((700,700))
        cv2.imshow("", np.array(img))


##
##    for blobID in range(numBlobs):
##        theBlob = blobArr[blobID]
##        theBlob.action(np.random.randint(0,4))
##        for blobID2 in range(numBlobs):
##            if(blobID2==blobID):
##                continue
##            theBlob.checkDisease(blobArr[blobID2])

##    printBlobs(blobArr)
##    print("---"+str(counter)+"---")
    if cv2.waitKey(500) & 0xFF == ord("q"):
        break
##    while(this):
##        if cv2.waitKey(500) & 0xFF == ord("w"):
##            this=False

    allIsHealthy, countSick = getAllHealthy(blobArr)
    
    peopleWithDisease.append(countSick)
    
    if(allIsHealthy==True):
        break


env = np.zeros((SIDE_LEN,SIDE_LEN,3), dtype= np.uint8)

##for blobID in range(numBlobs):
##    theBlob = blobArr[blobID]
##    
##    if(theBlob.hasDisease == True):
##        COLOR=(0,0,250)
##    else:
##        if(theBlob.resistance*50>250):
##            if(500-theBlob.resistance*50 < 0):
##                COLOR = (0,250,250)
##            else:
##                COLOR = (500-theBlob.resistance*50,theBlob.resistance*50-250,theBlob.resistance*50-250)
##        else:
##            COLOR = (theBlob.resistance*50,250-theBlob.resistance*50,0)
##
##        
##    env[theBlob.y][theBlob.x] = COLOR



img = Image.fromarray(env, "RGB")
img = img.resize((700,700))
cv2.imshow("", np.array(img))

print("Days Passed: "+str(counter))
print("Population: "+str(len(blobArr)))

##plt.plot(createIncrementalArray(deaths),deaths)
##
##plt.plot(createIncrementalArray(peopleWithDisease),peopleWithDisease)
##plt.ylabel('Deaths or Corona Cases')
##plt.xlabel('Days')
##plt.show()

xAxis=createIncrementalArray(peopleWithDisease)

fig, axs = plt.subplots(3, sharex = True)
fig.suptitle('Effect Of Corona')
axs[0].plot(xAxis, peopleWithDisease, 'b')
axs[0].set_ylabel('Corona Cases')

axs[2].plot(xAxis, deaths, 'r')
axs[2].set_ylabel('Deaths')


axs[1].plot(xAxis, immune, 'g')
axs[1].set_ylabel('Avg. Resistance')

axs[2].set_xlabel('Days')
plt.show();


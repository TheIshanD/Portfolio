import turtle
import random
import math
import time
import winsound

#adjust these
sideHeight = 600
sideWidth  = 600
margin     =  50
sliderLength = 4
sliderSpeed = 12;
ballSpeed = 4;
ballSpeedLowest=ballSpeed;
totalHitsAndMisses = 2;

#variables that should stay the same
misses=0
hits=0
#Set up screen
turtle.setup(sideWidth, sideHeight)   
wn = turtle.Screen()
wn.title("Slide!")
wn.bgcolor("lightblue")
wn.tracer(1)


#draw border
borderPen = turtle.Turtle()
borderPen.hideturtle()
borderPen.penup()
borderPen.setposition(margin-(sideWidth/2), margin - (sideHeight/2))
borderPen.pendown()
borderPen.pensize(1)
for side in range(2):
   borderPen.forward(sideWidth- 2*margin)
   borderPen.left(90)
   borderPen.forward(sideHeight - 2*margin)
   borderPen.left(90)



# create the slider
sliderPen = turtle.Turtle()
sliderPen.penup()
sliderPen.setposition(0,margin -(sideHeight/2) + 20)
sliderPen.shape("square")
sliderPen.shapesize(0.1,sliderLength,0)
sliderPen.color("darkgreen")

# create the ball
ballPen = turtle.Turtle()
ballPen.penup()
ballPen.setposition(0,margin -(sideHeight/2) + 470)
ballPen.shape("circle")
ballPen.color("orange")
ballPen.setheading(random.randint(-170, -10))
ballPen.forward(ballSpeed);

def turnleft():
   if (sliderPen.xcor() - 40) > (margin - (sideWidth/2)): 
      sliderPen.backward(sliderSpeed)
   
def turnright():
   if (sliderPen.xcor() + 40) < ((sideWidth/2) - margin):
      sliderPen.forward(sliderSpeed)
   
def leave() :
   wn.bye()

   


#keyboard bindings
turtle.listen()
turtle.onkey(turnleft, "Left")
turtle.onkey(turnright, "Right")
turtle.onkey(leave, "q")


while True:
   ballPen.forward(ballSpeed)
   sliderPen.color("darkgreen")

   #collision with slider
   if (ballPen.ycor() < sliderPen.ycor() + 12) and (ballPen.ycor() > sliderPen.ycor()) :
      if (ballPen.xcor() < sliderPen.xcor() + 20*(sliderLength/2)) and (ballPen.xcor() > sliderPen.xcor() - 20*(sliderLength/2)):
         if ballPen.heading()>0 and ballPen.heading()<180:
            sliderPen.color("lightblue")
            time.sleep(0.15)
         else:
            ballPen.setheading(  - ballPen.heading() )
            hits=hits+1
            ballSpeed=ballSpeed+1
            borderPen.undo()
            scorestring = "Score: %d" %hits
            borderPen.penup()
            borderPen.setposition(-240,260)
            borderPen.write(scorestring,False, align ="left", font=("Arial",14, "normal"))
   
   #x cordinate bounce
   if ballPen.xcor() > (sideWidth/2)- margin- 10 -ballSpeed/2:
      ballPen.setheading( 180 - ballPen.heading() )
   if ballPen.xcor() < - (sideWidth/2)+ margin + 10 + ballSpeed/2:
      ballPen.setheading( 180 - ballPen.heading() )
   #y cordinate bounce
   if ballPen.ycor() > (sideHeight/2)- margin - 10 - ballSpeed/2:
      ballPen.setheading( - ballPen.heading() )
   if ballPen.ycor() < - (sideHeight/2)+ margin + 10 + ballSpeed/2:
      ballPen.setheading(  - ballPen.heading() )
      ballPen.setposition(ballPen.xcor() , - (sideHeight/2)+ margin + 10)
      misses=misses+1
      if ballSpeed >= ballSpeedLowest :
         ballSpeed = ballSpeed -1




   if hits+misses == totalHitsAndMisses:
      scorestring = "Final Score : %d" %hits
      borderPen.penup()
      borderPen.undo()
      borderPen.hideturtle()
      borderPen.setposition(-240,260)
      borderPen.write(scorestring,False, align ="left", font=("Arial",14, "normal"))
      time.sleep(10)
      wn.bye()











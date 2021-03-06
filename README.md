# HabitApp
Habit Tracker

Easy way to keep a to-do list when you're on the go.

Build Instructions:

1. Clone this repository into a specified directory

2. Open the project in Android Studio from that directory

3. Enjoy!

Video demo found at this url:
https://archive.org/details/20161003132213

Design and Coding

The main portion  of the workload in my application runs through the HabitMainActivity class. It takes in the users input to create a new
habit and then, depending on the day that Habit is set for, it displays each day as a ListView of Habits. Naturally AddHabitActivity
handles this new creation and ChangeDayActivity will change our ArrayList of Habits to the proper display. Two completed classes are needed,
one for each individual Habit, to show its stats for number of completions and the other is for the general list of items done that day.
All of the lists can be created, destroyed, and expanded. Without the HabitMainClass, all the other classes would have no purpose, it is 
the focal point of this app. 

The backend classes are Habit and HabitData. These two run together to create data that can be passed in between the activities. From Habit,
 we have the basic definition of all Habits and their attributes and funcitons. For HabitData we use lists to store days of the week that we
 want to be active, as well as ArrayLists of Habits that are extremely useful for saving, manipulating, and storing data. 

 Together these classes create a brain that will keep track of your to-do list so you don't have to!


Credits:

The following sites and sources we're used to understand or build parts of this code. The majority are all 
modified unless specifically stated. A large portion was created by myself.

http://stackoverflow.com/questions/920306/sending-data-back-to-the-main-activity-in-android
http://stackoverflow.com/questions/5374546/passing-arraylist-through-intent
https://developer.android.com/training/basics/firstapp/starting-activity.html
http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
https://github.com/joshua2ua/lonelyTwitter
https://developer.android.com/index.html (within several parts of this site)
https://developer.android.com/training/basics/intents/result.html
https://developer.android.com/reference/android/app/IntentService.html
https://www.tutorialspoint.com/android/android_event_handling.htm
http://stackoverflow.com/questions/7073577/how-to-get-object-from-listview-in-setonitemclicklistener-in-android
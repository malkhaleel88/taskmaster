# **Taskmaster**

## **Lab: 26 - Beginning TaskMaster**

This lab is an introduction to Android and how to use the Android Studio. In this lab I create three activities, The Main Activity, Add Task Activity and All Task Activity.

### **Main Activity**

![Home page](screenshots/lab26(1).jpg)

### **AddTask Activity**

![Add taskOld page](screenshots/lab26(2).jpg)

![Submitted taskOld](screenshots/lab26(3).jpg)

### **AllTask Activity**

![All taskOld page](screenshots/lab26(4).jpg)

-------------------------------------------------------------------------------------------------------------

## **Lab: 27 - Data in TaskMaster**

In This lab added three buttons on the main page, each button for one taskOld that takes the user to the taskOld detail page when the user clicks on it. Also, added a setting button on the home page that takes the user to the setting page.

### **Main Activity**

![Home page](screenshots/lab27(1).jpg)

### **TaskDetailPage Activity**

![Task1 detail page](screenshots/lab27(2).jpg)

![Task2 detail page](screenshots/lab27(3).jpg)

![Task3 detail page](screenshots/lab27(4).jpg)

### **SettingsPage Activity**

![Settings page](screenshots/lab27(5).jpg)

-------------------------------------------------------------------------------------------------------------

## **Lab: 28 - RecyclerView**

In this lab I used the RecyclerView to view all tasks in the home page as the list.

### **Main Activity**

![Home page](screenshots/lab28(1).jpg)

![Home page2](screenshots/lab28(2).jpg)

### **TaskDetailPage Activity**

![Task detail page](screenshots/lab28(3).jpg)

-------------------------------------------------------------------------------------------------------------

## **Lab: 29 - Room**

In this lab, I added a Room database to save the tasks and the details of tasks and get data from it, and let the recycler view take the data from the room database.

### **AddTask Activity**

![Add taskOld page](screenshots/lab29(1).jpg)

### **Main Activity**

![Home page](screenshots/lab29(2).jpg)

![Home page2](screenshots/lab29(3).jpg)

### **TaskDetailPage Activity**

![Task detail page](screenshots/lab29(4).jpg)

-------------------------------------------------------------------------------------------------------------

## **Lab: 31 - Espresso and Polish**

In This lab I tests the code by using Espresso test.

### **I created 4 test:**

**1. testAddTaskCheck()**

To test the important UI elements are displayed on the Add Task page.

**2. testAllTaskCheck()**

To test the important UI elements are displayed on the All Task page.

**3. assertTextChanged()**

To test if you edit the user’s username, and then assert that it says the correct thing on the homepage.

**4. addNewTask()**

To test if you can add a new taskOld then when you tap on this taskOld.

--------------------------------------------------------------------------------------------------------------

## **Lab 32: Amplify and DynamoDB**

In this lab I implemented **AWS amplify** to access the data in **DynamoDB** instead of **Room**.

### **Add Task Activity**

Now when the user add new task in the add task page, The task will store in the DynamoDb.

### **Main Activity**

![Home page](screenshots/lab32(1).jpg)

![Home page2](screenshots/lab32(2).jpg)

### **AWS DynamoDB**

![DynamoDB](screenshots/lab32(3).PNG)

---------------------------------------------------------------------------------------------------------------

## **Lab 33: Related Data**

In this lab I created a second entity for a team, which has a name and a list of tasks. So, tasks will be Updated to be owned by a team.

### **Main Activity**

![Home page](screenshots/lab33(1).jpg)

![Home page2](screenshots/lab33(2).jpg)

### **Add Task Activity**

![Add Task Page](screenshots/lab33(3).jpg)

### **Setting Activity**

![Setting Page](screenshots/lab33(4).jpg)

### **AWS DynamoDB**

![DynamoDB](screenshots/lab33(5).PNG)

---------------------------------------------------------------------------------------------------------------

## **Lab 34: Publishing to the Play Store**

This lab is about generate an APK for the application and publishing it to the Google Play Store.

* [APK File](/APK)

* Publishing process needs 25$ I did not complete it.

---------------------------------------------------------------------------------------------------------------

## **Lab 36: Cognito**

### **Main Page**

![Home page](screenshots/lab36(1).jpg)

### **Sign Up Activity**

![Sign Up page](screenshots/lab36(2).jpg)

### **Verification Activity**

![Verification Page](screenshots/lab36(3).jpg)

### **Sign in Activity**

![Sign in Page](screenshots/lab36(4).jpg)

### **AWS Cognito**

![Cognito](screenshots/lab36(5).PNG)

---------------------------------------------------------------------------------------------------------------

## **Lab 37: S3**

### **Add Task Activity**

![Home page](screenshots/lab37(1).jpg)

### **Download Files Page**

![Download Files Page](screenshots/lab37(2).jpg)

### **TaskDetailPage Activity**

![Task Detail Page](screenshots/lab37(3).jpg)

![TaskDetailPage2](screenshots/lab37(4).jpg)

### **AWS S3**

![S3](screenshots/lab37(5).PNG)
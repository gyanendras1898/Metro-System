Use this project to learn all the technology step by step and their importance.

# Metro-System
user have option to create account, login, swipe in, swipe out and get the fare between metro stations.


Case Study 1:

METRO SYSTEM 
Design and develop an application for central metro system. The application needs to provide the swipe in and swipe out functionality.
Swipe in refers to boarding the station. Swipe out refers to coming out of the station. Every metro station has both swipe in and out facilities. There will be a metro card issued to every user with a minimum balance of Rs 100.
The metro line consists of 5 linear stations as mentioned below. 

Instructions –
A. When a new user is created, accept user’s basic details along with the card balance to issue the card to the user.
B. For Swipe in functionality, the application should

1. Accept the user’s input as source station.

2. The station can be from the above list only. Create a custom exception (with a meaningful message to the user) to handle invalid station inputs.

3. Validate the minimum required balance in the card. The user should have minimum balance of Rs 20 in the card. If the balance is not there, throw custom exception with appropriate message to user and do not allow to swipe in.

4. On successful swipe in, which means if the minimum balance is present then print the message as “You have successfully swiped in at the station” + <Source Station Name>.
	C. For Swipe out functionality, the application should
		1. Accept the user’s input for destination station.
		2. The station can be from the above list only. Create a custom exception (with a meaningful message to the user) to handle invalid station inputs.
		3. Calculate the total fare based on source and destination stations and deduct the fare from the card balance. The fare calculation is based on the below rules.
		a. Fare between any 2 adjacent stations is Rs 5. Example – Fare between L1 and L2 is Rs 5 and fare between L2 and L3 is Rs 5.
4. After deducting balance, the message needs to be printed “You have successfully swiped out with card balance as” + <actual card balance>
	D. Show the output on the console.
	E. Handle appropriate exceptions with appropriate message whenever required.
	F. The design should be flexible enough so that in future, more stations and different fare calculation methods can be added.
=====================================================================

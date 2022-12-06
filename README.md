# Social_Media_App

## Start Menu

##### I added a start menu that includes the menu options:
Make an account, List accounts, Update your account, Delete your account, 
Search accounts, Posts, Account Status, Save Accounts, Load Accounts and Exit.

Four of these options lead to sub-menus. List Accounts (Submenu2),
Search Accounts (Submenu3), Posts (Submenu), Account Status (Submenu 5)

## SubMenu 

##### The first sub-menu includes the options:
Add Post to User,View Recent Posts , Edit a post , Delete a post, and Exit SubMenu.

View Recent posts leads to another submenu(Submenu4)

## SubMenu2

##### The second sub-menu includes the options:
List all Users, List Active Users, List Offline Users,
List Users with a profile pic, List Users without a profile pic,
List Verified Users, List Unverified Users, and Exit SubMenu.
The exit submenu option returns the user to the main menu.

## SubMenu3

##### The third sub-menu includes the options:
Search users by full name, Search by username, and Exit SubMenu.

## SubMenu4

##### The fourth sub-menu includes the options:
List all a users posts, List Posts by Verified Users who are active,
List Posts by Offline Users, and Exit SubMenu.

## SubMenu5

##### The fifth sub-menu includes the options:
Go Online, Go Offline, Get Verified, Unverify your account, and Exit SubMenu.

## Add Users

The add user function in the Main.kt reads the information into the
new user object that the user wants to add.
The add user function in the API then adds a new user.


## Add Posts
The user is prompted to pick a user account via id number to add posts to.
The add posts function in the Main.kt reads the information into the
new post object that the user wants to add.
The add notes function in the User model then adds the new post to
a mutable set of posts.

## Add Utilities 

##### isValidPhotoName

The main.kt Add Posts function uses "readValidPhotoName" for the photo-name
variable. Users are given a range of options for the photo-name [Travel","Event",
"Work","School","Party","Adventure] if they enter an option outside this range the
console returns "Please Pick from the list".


##### readValidCount(s)

The main.kt Add Users and Add Posts functions use "readValidCount" functions to 
read the integers being entered for the following, follower, number of likes and
number of comments variables. This utilises the validRange functions and the user 
can only input a number within those ranges. The range for the following and 
follower variables is 1- 5000 and the range for likes and comments is 1-500.
There are valid reentry messages for if a user enters the wrong number.

## Update Users

The update user function in the main.kt reads the new information
that the user wants to update the account with.
In the API the function then updates the user with the new info.

## Update Posts


In the API the function then updates the note with the new info.

The user is prompted to pick a user account via id number to update its posts.
The update posts function in the main.kt reads the new information
that the user wants to update the post with.
The update posts function in the User model then changes the post to include the 
new information in the mutable set of posts.
You may notice that not all the variables update but that was done on purpose as I
only wanted the Photo-name and Caption variables to be updatable.



## Delete Users

In the main.kt, the delete users function reads index number of the
account the user wants to delete, the function in the API then deletes it.

## Delete Posts

The user is prompted to pick a user account via id number to delete its posts.
In the main.kt, the delete post function reads index number of the
post the user wants to delete, the function in the User model then deletes it.

## List Users

Prints all users. the api function fetches all users.

## List Active Users

Prints active users. The API function fetches users where the activeNow
field is set to true.

## List Offline Users

Prints offline users. The Api function fetches users where the activeNow
field is set to false.

## List Users with a profile pic

Prints users with a profile picture. The Api function fetches notes where 
the profilePicture field is set to true.

## List Users without a profile pic

Prints users without a profile picture. The Api function fetches notes where
the profilePicture field is set to false.

## List Verified Users

Prints users who are verified. The Api function fetches notes where
the verified field is set to true.

## List Verified Users

Prints users who are unverified. The Api function fetches notes where
the verified field is set to false.

## Search users by Full Name 

A string is imputed by the user which is matched by the api to one of the users
with that Full name. If it doesn't match a result, the console returns "No users 
with that name found". It ignores case and returns half spelled values as well.

## Search users by username

A string is imputed by the user which is matched by the api to one of the users
with that username. If it doesn't match a result, the console returns "No users
with that username found". It ignores case and returns half spelled values as well.

## Go Online

The user has to input the index number of the offline user they would like to set to active now,
and confirm that they would like to set it to active with a yes or no answer.
The api function then marks the active now field as true.
The listing function for offline users is also used here to list the users that can actually
be marked active. The answers "Yes" and "No" must be in this format.


## Go Offline

Similar to the previous entry except that the functions now do the opposite,
the listing function for active users is used to list the users that can be  set to offline.
The answers "Yes" and "No" must be in this format.


## Get Verified

The user has to input the index number of the unverified user they would like to set to verified,
and confirm that they would like to set it to verified with a yes or no answer.
The api function then marks the verified field as true.
The listing function for unverified users is also used here to list the users that can actually
be marked verified. The answers "Yes" and "No" must be in this format.

## Unverify Account

Similar to the previous entry except that the functions now do the opposite,
the listing function for verified users is used to list the users that can be unverified.
The answers "Yes" and "No" must be in this format.

## Set Profile Picture 

The user has to input the index number of the user they would like to set a profile picture for,
and confirm that they would like to set it with a yes or no answer.
The api function then marks the profile picture field as true.
The listing function for users without profile picutre users is also used here to list the users
that can actually be set a profile picture. The answers "Yes" and "No" must be in this format.


## Remove Profile Picture

Similar to the previous entry except that the functions now do the opposite,
the listing function for users with profile picture is used to list the users that can have
their profile picture removed. The answers "Yes" and "No" must be in this format.



## Exit App

End the instance of the app running.

## Exit Sub Menu

Returns to the main menu instead of ending the instance.

## Save Notes

Serializer is used to save notes.

## Load Notes

Serializer is used to load up saved notes.




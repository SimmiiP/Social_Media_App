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

## Exit App

End the instance of the app running.

## Exit Sub Menu

Returns to the main menu instead of ending the instance.

## Save Notes

Serializer is used to save notes.

## Load Notes

Serializer is used to load up saved notes.









## List Notes by Selected Priority

Prints notes by selected priority. The Api function fetches notes that contain the same
number between 1 and 5 as imputed by the user in the priority field.


## List Notes by Category

Prints notes by category. The Api function fetches notes that contain the same words as
imputed by the user in the category field. This accounts for half imputed categories and when words
are not fully spelled.


## Archive Notes

The user has to input the index number of the note they would like to archive
and confirm that they want to archive it in the main which reads these responses.
The answers to the questions are yes and no for the function to read, and the first letter
of the users response hase to be to uppercase.
The api function then marks the isNoteArchived field to true instead of its default false.
The listing function for active notes is also used here to list the notes that can be archived.




## Go Online

The user has to input the index number of the note they would like to mark done,
and confirm that they would like to mark it done with a yes or no answer same as the
archive notes function. The api function then marks the note status field as true.
The listing function for notes to-do is also used here to list the notes that can actually
be marked done


## Go Offline

Similar to the previous entry except that the functions now do the opposite,
the listing function for notes done is used to list the notes that can be marked to-do.

## Get Verified

The user has to input the index number of the note they would like to mark done,
and confirm that they would like to mark it done with a yes or no answer same as the
archive notes function. The api function then marks the note status field as true.
The listing function for notes to-do is also used here to list the notes that can actually
be marked done


## Unverify your account

Similar to the previous entry except that the functions now do the opposite,
the listing function for notes done is used to list the notes that can be marked to-do.




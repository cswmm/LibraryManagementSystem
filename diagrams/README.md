User Diagram: https://drive.google.com/file/d/1ojYDXC1TcGyzGATDd5BHa-tjtL3NKTNa/view?usp=sharing

There are three different kinds of users- a base user, premium account user, and a librarian user. 
All of them are able to do some operations, like logging in and searching. The method create account extends login, 
as the user will go to the login page to create their account. Search also extends many different functionalities,
such as requesting a book, checking out a book, and adding/removing a book (only for librarians, however).
Most application operations are shared between the base user and premium user, except for buying and canceling 
premium for each user respectively. The librarian also has access to exclusive operations, such as manipulating users
and looking at user suggestions.

Class Diagram: https://drive.google.com/file/d/1m-tFybTbZYx0IOaorm_7smWB-uHpAJfQ/view?usp=sharing

There are 5 different classes, a user, premiumUser, librarian, book and library class. All of these classes interact with the library class as it has-a relationship with the book. The premiumUser class is a child of the User Class with overriden methods. The User class is associated with the library class overall as well as the admin class. The library class keeps track of the books, admins and users within the system.

State Diagram: https://drive.google.com/file/d/1JpyMoYyWAX0qxG5MGe9-wbmrdHLubV90/view?usp=sharing

The state diagram starts off with a login state where the user inputs a user ID/password. If the login is correct, the user will be go to the base user page, premium user page, or the librarian user page. In each of the pages, there are options for what each user can do. The base user has the option to buy premium for $100 and if he does so, he will be teleported to the premium user page. Similarly, if the premium user cancels his subscription, he will be teleported to the base user page.

Sequence Diagram: https://drive.google.com/file/d/113F0HTnlbuIufXG5lFJ-Xm8-CpZe9fUt/view?usp=sharing

Sequence Diagram.drawio denotes sequence diagram which show the interaction between the user and the library, their accounts, books,
and the path of how information got retrieved and display on the GUI or action from the user got performed in backend. The diagram also shows the processing time of each process and the time delay between processes.

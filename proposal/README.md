# Library Management System 

Group: 1337h4x0r

(Nathan, Rajiv, Huu, and Davis)

Our goal is to create a Library Management System, 
using Java, which will allow some users to manage a 
collection of books, and other users to check out these 
books. We aim to create a system that will utilize 
Object-Oriented Programming (OOP) concepts, Java's 
Graphical User Interface (GUI) capabilities, and 
Exception Handling.

We hope to tackle the issue of book management and digitalizing the user experience in a library system. If a library does not have a proper system in place, they might not be able to keep track of what books are checked in/out, or even what books they offer. Readers will not know what they can check out, or even how to do it on their own. To resolve this, we will be using Java Swing to create a user-friendly system that stores and provides information about what books the library has, as well as additional functionality to better the experience of library managers and readers.

Our approach is to create a library class that manages a collection of books and users. We will also implement a book class that comes with standard attributes such as the title, author, ISBN number, and status (checked-in/checked-out). However, we will also extend our library management system to handle physical books and e-books. All books will be stored in one data structure in the library class which will be updated based on the status or type of book present. E-books will be a child of the book class and will always have a status of checked-in because users will always be able to check out an e-book. Physical books will have their status updated based on whether they are checked or checked out. Next, we will have a user class which will contain attributes such as name, library card number, and a list of borrowed books. A child premium user class will extend this class and override many of the methods to provide more features, making use of polymorphism. We will also have an admin (librarian) class that can add/remove books by giving it direct access to the library class. The admin class will also be able to create/remove users and update their library privileges.

This provides functionality to library managers as well as readers by delivering services and providing information. Both kinds of users can see what books the library has- whether with a search or by sorting and looking through them. Readers can check out ebooks, as well as check on what books are already out under their name. Additionally, if they want to have more than 5 books checked out, they can pay more to check out more books, with additional perks. They can also request that the library add a book to the catalog, if it is not there. On the other hand, managers are able to add and remove books as well as users from the system.

Operations:

Admin user:

- Add/remove book: Add a book in the system without a request or remove a book.
- Add/remove category: Add a new category not in the library system or remove a category.
- Set category: place a book name in categories.
- Order copies: Add n physical copies to a book in the library system or add an online copy.
- Terminate account: Forced terminate user.
- Order requested book: If there is a request, add the book to the library system and order n physical copies or an online copy.
- Add admin account: Initially there is one admin account. Admin account can create an admin account or add a user account to become an admin account.
- Check user checked out: Show a list of user checkouts and their deadline.

Base user:

- Create/Terminate account: Provide username and password to create account, cannot create account if matched the username with existing user’s.
- Change account information: Change username or password, cannot change if matched with existing users.
- Request login/password information: Receive forgot username or password, type in username or password and enter the number of books checked out.
- Search: Can search books by name and category.
- Checkout/return book: Maximum 5 books,  can select between online and physical copy. There will be a three-day limit, after the limit, late fee will apply and refuse any service until late payment of $50 is received.
- Request extesion: Up to 2 times, three-day period each.
- Request book: Can suggest one book to add to the library system if the book is not in the system. Can ask to order more books if the book has no physical copy in the library system.
- Check checked out book: Show a list of checked out books and their deadline.
- Check checked out book history: Show a list of checked out books in the past.
- Buy Premium: Entrance fee of $100

Premium user: (Inherits operations of Base user)
- Cancel Premium: We are sad to see you go =(
- Search: Can search books by keyword, name, published year, and categories.
- Checkout/return book: Maximum 20 books instead of 5
- Request extension for checked out books: Up to 2 times, five-day period each.
- Request book: Can suggest three books instead of one
- Check checked out book: Show a list of checked out books and their deadline.
- Check checked out book history: Show a list of checked out books in the past.

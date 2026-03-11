# Plastic Footprint Calculator (DSA Application)

## Project Title
Plastic Footprint Calculator using Data Structures and Algorithms (DSA)

## Project Description
A menu-driven, console-based Java application engineered to help users track and evaluate their daily plastic consumption. The application provides an interactive interface to signup, login, log plastic usage records (e.g., bottles, bags, wrappers), evaluate aggregate queries dynamically, and calculates the total plastic footprint. Finally, it leverages conditional analytical checks to provide reduction suggestions. It is designed to demonstrate key Data Structure implementations.

## Problem Statement
Plastic pollution constitutes a monumental environmental crisis. Often, individuals remain oblivious to their scattered daily plastic consumption and its cumulative impact. A definitive track-and-report software mitigates this. We built a structured application tracking exact plastic usages mathematically, computing aggregate footprints, and supplying actionable suggestions (Low, Moderate, High impact brackets).

## Data Structures Used
1. **HashMap**: Used for **User Authentication**. The hash-based mapping architecture assigns unique Usernames (`String` Keys) to Passwords (`String` Values) to provide `O(1)` average time complexity for login verification and signups.
2. **ArrayList**: Utilized for maintaining **Usage History**. This dynamic array structure grants seamless amortized insertions to store incoming user record logs while keeping elements strictly ordered for easy retrieval sequentially.

## Algorithms Used
1. **Linear Search Algorithm**: Realized in the Search Module, iterating through the `ArrayList` in an `O(N)` algorithmic time bound to extract precise string matching for user query elements.
2. **Traversal Algorithms**: Enforced iteratively `O(N)` to navigate array collections. Used structurally to dump history logs to the console and to calculate aggregate computational totals mathematically.
3. **Insertion Algorithms**: Dynamically allocating strings and integer configurations onto collections structures efficiently.

## Features
1. **User Authentication Module**: Secure sign-up/login pipeline utilizing HashMaps.
2. **Add Plastic Usage**: Track exact units of pre-defined items (Bottles, Bags, Wrappers, Cups).
3. **View Usage History**: Spits out full tracking timeline chronologically.
4. **Footprint Computation**: Math engine runs traversal summations computing net grams used.
5. **Lookup & Search**: Search records loosely with Linear lookups.
6. **Analytical Suggestions**: System outputs warnings mapping into categories (<100g, 100-300g, >300g).

## Folder Structure
```
DSA/
│
├── PlasticFootprintCalculator.java
└── README.md
```

## How to Compile and Run
1. Verify `java` and `javac` are correctly installed on your machine.
2. Open your preferred Terminal or Command Prompt.
3. Use the `cd` command to navigate directly into the `DSA` directory.
4. **Compile the java program:**
   ```bash
   javac PlasticFootprintCalculator.java
   ```
5. **Run the compiled application:**
   ```bash
   java PlasticFootprintCalculator
   ```

## Sample Output
```text
=========================================================
 PLASTIC FOOTPRINT CALCULATOR (DATA STRUCTURES & ALGORITHMS) 
=========================================================

------------- MAIN MENU -------------
1. Signup
2. Login
7. Exit

Enter your choice: 1

--- SIGNUP ---
Enter a new username: myUser
Enter a secure password: securePass123
Success! Account created. You may now login.

------------- MAIN MENU -------------
1. Signup
2. Login
7. Exit

Enter your choice: 2

--- LOGIN ---
Username: myUser
Password: securePass123
Login Authenticated! Welcome back.

------------- MAIN MENU -------------
 Welcome User: [myUser]
-------------------------------------
3. Add Plastic Usage
4. View Plastic Usage History
5. Calculate Total Plastic Footprint
...
```

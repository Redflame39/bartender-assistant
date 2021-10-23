# Bartender Assistant
Bartender assistant is a website that allows users to create, share and discuss cocktails. There are three types of users:
- Client
- Bartender
- Admin
Each of them has their rights and functionality. You can find a description of each role below:
##### Client
Client is a regular user of the website. After registration and confirmation of your account you will become a Client. Client is allowed to do the following actions:
- Creating a cocktail recipes that needs to be approved by Admin
- Editing your own cocktail recipes
- Writing reviews on existing cocktails 
- Editing your profile data
##### Bartender
Bartender has the same possibilities as a User, and some extra rights, which are described below:
- Creating cocktails without a need to be approved by Admin
- All bartenders are shown at the Bartenders page, where they are ordered according to the average rate of cocktails they created and a tital number of their cocktails
##### Admin
Admin has absolute rights on the website:
- Deleting cocktails
- Approving cocktails
- Changing user role from Client to Bartender and back
## Additional functionality
- Email account confirmation
- All users are able to restore password by email
- Two available localizations: English and Russian
- Searching cocktails and users by name
## Technical information
- Java language level: 16
- Jakarta EE 8, JSP, JSTL
- Testing framework: JUnit 5, Mockito
- Utility: Apache Commons Lang 3
- JSON serialization: Jackson
- Logging: Apache Log4J2
- Database: Oracle MySQL
- Hashing: Mindrot jBCrypt

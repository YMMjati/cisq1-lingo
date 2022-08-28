# Vulnerability analysis
This is a short OWASP analysis that describes the security measures taken (or advised) to address three different types of security vulnerabilities.
This analysis is strictly meant for educational purposes and doesn't cover every facet of security.

## A1: Injection

###  Description
Injection entails modifying a query from the outside by inserting (meta) code in the input field(s).
This allows attackers to run additional, harmful queries that can lead to data leaks and security breaches.
Unless the input is sanitized, and other precautions (such as prepared statements) are taken.
Injection is not technology-specific, it applies to SQL, NoSQL, OS, LDAP and similar data technologies.

### Risks
The risks for SQL injection are very minimal.
The database is not accessable except internally and Spring Data JPA has inbuilt protection against SQL injection attacks for its data-related functionalities.

### Counter-measures
The Word repository uses a native query to select a random word, this query makes use of positional parameters and takes no input from outside the application.
As an additional measure, the database user's rights can be limited to just the required tables.
All metadata tables, such as INFORMATION_SCHEMA, should not be accessible to the user.
PostgreSQL can also be configured to cancel SQL queries that run for too long, to prevent them from reducing database performance.
This can be done by setting the `statement_timeout` in the PostgreSQL config file (`postgresql.conf`).


## A3: Sensitive Data Exposure

### Description
Sensitive Data Exposure means that the sensitive data is not protected (i.e. not encrypted or hashed, and not anonymized).
When a data leak occurs, this data can be stolen and used for identity fraud.
A good example is storing passwords as plaintext in the database, allowing hackers to access every user account.
Unencrypted HTTP messages are vulnerable to MITM (Man-in-the-Middle) attacks that can read and/or alter these messages.

### Risks
The risks for sensitive data exposure are real.
The HTTP messages are not encrypted, and the same goes for the game session identifiers in the database.
While this has grave consequences for a commercial application, there is little to gain here for a hacker; the users of this application are in no way identifiable.

### Counter-measures
The session identifiers can be encrypted or hashed using a safe algorithm (no outdated algorithms like MD5 or SHA1).
Spring Boot can be configured to use HTTPS instead of HTTP, this requires an SSL certificate must be generated, or a valid existing certificate must be used.

A step-by-step tutorial can be found here:
https://www.thomasvitale.com/https-spring-boot-ssl-certificate/


## A9: Using Components with Known Vulnerabilities

### Description
Dependencies become vulnerable over time, due to using outdated technologies, programming mistakes or even intentional backdoors.
Using vulnerable dependencies can lead to data leaks, data loss and a server takeover in the worst case scenario.
Giving libraries and frameworks the same rights as the application is a vulnerability on its own and also falls under this category.

### Risks
The libraries used in this application range from relatively up-to-date to being the latest (public) version.
It is unknown how high these risks are in reality, presumably, the risks aren't serious at the moment.

### Counter-measures
Github Dependabot is active for the repository, it automatically sends pull requests for addressing detected vulnerabilities.
These requests are handled manually by the developer(s).
The OWASP dependency checker (for Maven) has been added to pom.xml, it runs automatically for every commit to Github and can also be run manually with a Maven command.
Finally, SonarCloud provides static analyses and security analyses; these run the same way as the dependency checker.
These tools didn't detect any real vulnerabilities at the point of writing this.
As an extra security measure, unused dependencies can be removed manually from the project.
The dependencies can also be analyzed for vulnerabilities using the Common Vulnerabilities and Exposures (CVE) list, and the National Vulnerability Database (NVD) list.
Both are freely available online.
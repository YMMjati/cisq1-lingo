[THIS DOCUMENT IS A WORK IN PROGRESS]
(Add advice, no implementation is needed per se)


A1: Injection
There are no write operations and the database user has no access to metadata tables, such as INFORMATION_SCHEMA.
PostgreSQL is configured to cancel SQL queries that run for too long, to prevent them from reducing database performance.

(Add reference to Spring's inbuilt security vs injections!)


Set statement_timeout in the PG config file
/etc/postgresql/(version)/main/postgresql.conf

Using this source:
https://stackoverflow.com/questions/1263218/if-my-database-user-is-read-only-why-do-i-need-to-worry-about-sql-injection

A3: Sensitive Data Exposure
The JSON will be encrypted using a safe algorithm, to prevent MITM attacks.
- use HTTPS (protocol-level) as encryption, since it's standard <-- elaborate later!


A9: Using Components with Known Vulnerabilities
Remove unused dependencies, verify used dependencies against known vulnerabilities (CVE, NVD)

Dependabot, automated dependency checking
OWASP checker (install in pom.xml as compiler plugin)

Use Sonar Cloud (static analysis + basic security analysis)






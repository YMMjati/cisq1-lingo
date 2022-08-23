[THIS DOCUMENT IS A WORK IN PROGRESS]


A1: Injection
The server treats sessions as non-persistent, they're not stored in the database and will be gone if the server shuts down.
This is a deliberate design decision.
There are no write operations and the database user has no access to metadata tables, such as INFORMATION_SCHEMA.
PostgreSQL is configured to cancel SQL queries that run for too long, to prevent them from reducing database performance.

Set statement_timeout in the PG config file
/etc/postgresql/(version)/main/postgresql.conf

Using this source:
https://stackoverflow.com/questions/1263218/if-my-database-user-is-read-only-why-do-i-need-to-worry-about-sql-injection


A3: Sensitive Data Exposure
The session identifier will be encrypted using a safe algorithm, to prevent MITM attacks.


A9: Using Components with Known Vulnerabilities
Remove unused dependencies, verify used dependencies against known vulnerabilities (CVE, NVD)







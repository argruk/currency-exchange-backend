# currency-exchange-backend
This is a simple web application for currency exchange service

Front-end for this application is located here: https://github.com/argruk/currency-exchange-frontend

This backend uses MariaDB for database. For correct functioning database `MessengerDB` has to be created (Go to your MySQL/MariaDB client and do `CREATE DATABASE MessengerDB;`)
Database is located on port 3306.

Backend is written with Spring framework.
It can be started normally, and the default port is 8080. Should this port be busy,
connection string in front-end ServerRequest.js file should also have default port changed.
  
Front-end is written with React.js library.
Can be started with `yarn start`, and is located on port 3000. If this port is unavailable, it should be changed in all occurrences.

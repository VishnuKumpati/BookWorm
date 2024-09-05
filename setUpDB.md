## Database setup
Open the MySQL command-line tool and log in as the root user using your password.

```sh
mysql -u root -p
```
Make a new database called `BookWorm` where you can store your data.

```sh
create database BookWorm;
```
Set up a new user named `master` with the password `masterKee`.

```sh
CREATE USER 'master'@'localhost' IDENTIFIED BY 'masterKee';
```

Give the `master` user full access to the `BookWorm` database so they can view, add, and modify the data.

```sh
GRANT ALL ON BookWorm.* TO 'master'@'localhost';
```
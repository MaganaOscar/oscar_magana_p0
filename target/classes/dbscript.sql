CREATE EXTENSION pgcrypto;

CREATE TABLE users (
	user_id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	username varchar(255) NOT NULL UNIQUE,
	password varchar(255) NOT NULL
	);
	
CREATE TABLE bank_accounts (
	account_id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	account_type varchar(25) NOT NULL,
	balance numeric NOT NULL
	);
	
CREATE TABLE users_bank_accounts (
	user_id int NOT NULL,
	account_id int NOT NULL,
	PRIMARY KEY (user_id, account_id),
	FOREIGN KEY (user_id) REFERENCES users(user_id) ON UPDATE CASCADE,
	FOREIGN KEY (account_id) REFERENCES bank_accounts(account_id) ON UPDATE CASCADE
	);
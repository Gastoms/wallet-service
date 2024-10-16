# Wallet Service

This service allows you to create wallets as well as manage transactions (deposits, withdrawals and transfers). Java 11 language is used with Spring Boot. Spring Data JPA for data persistence, using an embedded H2 database.

## Prerequisites:
- Have Java 11 installed
- Have maven installed as it is used for dependency management

## How to run the application
Run in the terminal inside the project directory -> *mvn spring-boot:run*<br>
Url to access the application -> *http://localhost:8080*

## How to test the application
Run in the terminal inside the project directory -> *mvn test*

## Endpoints to interact with the service

1. *Create wallet*<br><br>
    POST **/api/wallet**<br>
    JSON Body<br>
    {<br>
    "name" : "carlos",<br>
    "last_name" : "diaz"<br>
    }
  
    Field descriptions:
    - name: name of the wallet owner
    - last_name: last name of the wallet owner
  
    This request returns the wallet id

2. *Get current balance of a wallet*<br><br>
    GET **/api/wallet/{wallet_id}/balance**<br>
  
    Field descriptions:
    - wallet_id: This is the wallet id. This id is returned when creating the wallet.<br>
    
    Example: /api/wallet/1/balance
  
    This request returns the wallet balance

3. *Get the balance of a wallet at a point in time*<br><br>
    GET **/api/wallet/{wallet_id}/balance?fromDate={from_date}**<br>
    Field descriptions:
    - wallet_id: This is the wallet id. This id is returned when creating the wallet.
    - from_date: This is the date up to which you want to get the balance.
    
    Example: localhost:8080/api/wallet/1/balance?fromDate=2024-10-16T15:00:00
  
    This request returns the wallet balance

4. *Perform a transaction from a wallet*<br><br>
    POST **/api/wallet/transaction**<br>
    JSON Body<br>
    {<br>
    "wallet_id": 1,<br>
    "type" : "DEPOSIT",<br>
    "amount": 9.9<br>
    "destination_wallet_id": 2<br>
    }
    
    Field descriptions:
    - wallet_id: This is the wallet id. This id is returned when creating the wallet. Long type field
    - type: This is the type of transaction to perform. It can be: DEPOSIT, WITHDRAW or TRANSFER. String type field
    - amount: This is the amount of the operation. Double type field
    - destination_wallet_id: This is the id of the wallet to which we make a transfer. NOTE: This field is optional and is MANDATORY only when making a TRANSFER type transaction.
  
    This request returns the id of the transaction made to the origin wallet


## Design explanation

The application follows the microservice architecture, separating responsibilities through layers:
- Controller: Handles HTTP requests
- Service: Handles business logic. Processes transactions and calculates balances
- Repository: Manages the storage and access to DB data
- Model: Defines the database entities (Wallet and Transaction)

The factory method pattern is used to create the different types of transactions, since it improves the maintainability of the code and makes it extensible when adding new transactions

To comply with the traceability of operations, all transactions are persisted in the "transactions" table, which contains the corresponding transaction types:
- DEPOSIT for deposits
- WITHDRAW for withdrawals
- TRANSFER_OUT for an outgoing transfer
- TRANSFER_IN for an incoming transfer

The amount, wallet ID and transaction date are also persisted in the transaction record. All of this data allows for easy auditing.


## Estimated project time

- *Planning time*: 3 hours
- *Project start-up and configuration time*: 2 hours
- *Development time*: 12 hours
- *Testing time*: 2 hours

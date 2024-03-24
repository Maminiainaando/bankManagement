/*select all data*/
select * from account ;
select * from transfer_history;
select * from transaction;
select * from transaction_history;

/*select the last id inserted*/
select  id_transaction from transaction order by  id_transaction  desc  limit 1;
select id_transfer from transfer_history order by id_transfer  desc  limit 1;

/*select the first id inserted*/
select  id_transaction from transaction order by  id_transaction  asc  limit 1;
select id_transfer from transfer_history order by id_transfer  asc  limit 1;

/*Join to get transaction details with account holder names*/
SELECT t.*, a.name, a.last_name
FROM transaction t
JOIN account a ON t.id_account = a.id_account;

/*Calculating the current balance of an account from transaction history*/
SELECT id_account, SUM(last_balance) AS current_balance
FROM transaction_history
GROUP BY id_account;

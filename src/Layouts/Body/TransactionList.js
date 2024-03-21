import React, { useState, useEffect } from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import ButtonAppBar from '../NavBar/Nav';
import Link from '@mui/material/Link';

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
  },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
  '&:nth-of-type(odd)': {
    backgroundColor: theme.palette.action.hover,
  },
  '&:last-child td, &:last-child th': {
    border: 0,
  },
}));

function TransactionList() {
  const [transactions, setTransactions] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const response = await fetch('http://localhost:8080/transaction');
      const data = await response.json();
      setTransactions(data);
    };

    fetchData();
  }, []);

  return (
    <div>
      <ButtonAppBar />
      <header className="Title">
        <h1>Transaction</h1>
      </header>
      <TableContainer sx={{ marginTop: '5vh', minWidth: '90vw' }} component={Paper}>
        <Table sx={{}} aria-label="customized table">
          <TableHead>
            <TableRow>
              <StyledTableCell align="center">ID de transaction</StyledTableCell>
              <StyledTableCell align="center">Label</StyledTableCell>
              <StyledTableCell align="center">Amount</StyledTableCell>
              <StyledTableCell align="center">Transaction Type</StyledTableCell>
              <StyledTableCell align="center">Date d'effet</StyledTableCell>
              <StyledTableCell align="center">Date d'enregistrement</StyledTableCell>
              <StyledTableCell align="center">Statut</StyledTableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {transactions.map((transaction) => (
              <StyledTableRow key={transaction.id_transaction}>
                <StyledTableCell component="th" scope="row" align="center">
                  {transaction.id_transaction}
                </StyledTableCell>
                <StyledTableCell align="center">{transaction.label}</StyledTableCell>
                <StyledTableCell align="center">{transaction.transactionAmount} Ar</StyledTableCell>
                <StyledTableCell align="center">{transaction.transactionType}</StyledTableCell>
                <StyledTableCell align="center">{transaction.dateEffect}</StyledTableCell>
                <StyledTableCell align="center">{transaction.dateRegistration}</StyledTableCell>
                <StyledTableCell align="center">{transaction.status}</StyledTableCell>
              </StyledTableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
}

export default TransactionList;

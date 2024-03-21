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

function AccountListList() {
  const [accounts, setAccounts] = useState([]);

  useEffect(() => {
    // Simulation d'une requête HTTP pour obtenir les données JSON
    const fetchData = async () => {
      const response = await fetch('http://localhost:8080/account');
      const data = await response.json();
      setAccounts(data);
    };

    fetchData();
  }, []);

  return (
    <div>
      <ButtonAppBar sx={{}} />
      <header className="Title">
        <h1>Account</h1>
      </header>
      <TableContainer sx={{ marginTop: '5vh', minWidth: '98vw' }} component={Paper}>
        <Table sx={{}} aria-label="customized table">
          <TableHead>
            <TableRow>
              <StyledTableCell align="center">name</StyledTableCell>
              <StyledTableCell align="center">lastname</StyledTableCell>
              <StyledTableCell align="center">birthDate</StyledTableCell>
              <StyledTableCell align="center">BALENCE</StyledTableCell>
              <StyledTableCell align="center">Date et heure</StyledTableCell>
              <StyledTableCell align="center">accountnumber</StyledTableCell>
              <StyledTableCell align='center'>Bank type</StyledTableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {accounts.map((account) => (
              <StyledTableRow key={account.id_account}>
                <StyledTableCell align="center">{account.name}</StyledTableCell>
                <StyledTableCell align="center">{account.lastname}</StyledTableCell>
                <StyledTableCell align="center">{account.birthDate}</StyledTableCell>
                <StyledTableCell align="center">{account.balance} Ar</StyledTableCell>
                <StyledTableCell align="center">{account.date_heure}</StyledTableCell>
                <StyledTableCell align="center">{account.accountNumber}</StyledTableCell>
                <StyledTableCell align="center">{account.bankType}</StyledTableCell>
              </StyledTableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
}

export default AccountListList;

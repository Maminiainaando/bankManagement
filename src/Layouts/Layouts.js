import *as React from 'react';
import Box from '@mui/material/Box';
import AccountListList from './Body/AccountList';
import './Layouts.css';

export default function Table() {
    return (
        <Box 
        sx={{ 
            display: 'flex', 
            flexDirection: 'row', 
            justifyContent: 'center',
            gap:'2vw'
        }}> 
            <AccountListList/>
        </Box>
    );
}
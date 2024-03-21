import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Button, TextField, Typography } from '@mui/material';
import './transaction.css'

function AddTransactionForm() {
    const [formData, setFormData] = useState({
        transactionType: '',
        idAccount: '',
        label: '',
        transactionAmount: '',
        bankType: '',
    });

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            // Fetch account data if needed
        } catch (error) {
            console.error('An error occurred while fetching data:', error);
        }
    };

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData((prevState) => ({
            ...prevState,
            [name]: value,
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post('http://localhost:8080/transaction/' + formData.transactionType + '/' + formData.idAccount, formData);
            setFormData({
                transactionType: '',
                idAccount: '',
                label: '',
                transactionAmount: '',
                bankType: '',
            });
        } catch (error) {
            console.error('An error occurred while adding transaction:', error);
        }
    };

    return (
        <div className='form'>
            <div className='form_submit'>

                <Typography variant="h6" component="h2">
                    Add Transaction
                </Typography>
                <form onSubmit={handleSubmit}>
                    <TextField
                        label="Transaction Type"
                        name="transactionType"
                        value={formData.transactionType}
                        onChange={handleChange}
                        required
                        fullWidth
                        margin="normal"
                    />
                    <TextField
                        label="Account ID"
                        name="idAccount"
                        value={formData.idAccount}
                        onChange={handleChange}
                        required
                        fullWidth
                        margin="normal"
                    />
                    <TextField
                        label="Label"
                        name="label"
                        value={formData.label}
                        onChange={handleChange}
                        required
                        fullWidth
                        margin="normal"
                    />
                    <TextField
                        label="Amount"
                        name="transactionAmount"
                        value={formData.transactionAmount}
                        onChange={handleChange}
                        required
                        fullWidth
                        margin="normal"
                    />
                    <TextField
                        label="Bank Type"
                        name="bankType"
                        value={formData.bankType}
                        onChange={handleChange}
                        fullWidth
                        margin="normal"
                    />
                    <Button variant="contained" type="submit" color="primary" fullWidth margin="normal">
                        Add Transaction
                    </Button>
                </form>
            </div>
        </div>
    );
}

export default AddTransactionForm;

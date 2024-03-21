import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Button, TextField, Typography } from '@mui/material';
import './AddAccount.css'

function AddPersonForm() {
    const [formData, setFormData] = useState({
        name: '',
        lastname: '',
        birthDate: '',
        accountNumber: '',
        bankType: '',
    });

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const response = await axios.get('http://localhost:8080/account');
            setFormData(response.data); // Assuming you have a state variable called 'Person' to store fetched data
        } catch (error) {
            console.error('Une erreur s\'est produite lors de la récupération des données:', error);
        }
    };

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData((prevState) => ({
            ...prevState,
            [name]: value,
        }));
    };

    const handleSubmit = async () => {
        try {
            await axios.post('http://localhost:8080/account/add', formData);
            setFormData({
                name: '',
                lastname: '',
                birthDate: '',
                accountNumber: '',
                bankType: '',
            });
            fetchData(); // Refetch data after successful submission
        } catch (error) {
            console.error('Une erreur s\'est produite lors de l\'ajout de l\'employé:', error);
        }
    };

    return (
        <div className='form'>

            <div className='form_submit'>
                <Typography variant="h6" component="h2">
                    Add Account
                </Typography>
                <form onSubmit={handleSubmit}>
                    <TextField
                        label="Name"
                        name="name"
                        value={formData.name}
                        onChange={handleChange}
                        required
                        fullWidth 
                        margin="normal"
                    />
                    <TextField
                        label="Lastname"
                        name="lastname"
                        value={formData.lastname}
                        onChange={handleChange}
                        required
                        fullWidth
                        margin="normal"
                    />
                    <TextField
                        label="Birthdate"
                        name="birthDate"
                        value={formData.birthDate}
                        onChange={handleChange}
                        fullWidth
                        margin="normal"
                    />
                    <TextField
                        label="Account Number"
                        name="accountNumber"
                        value={formData.accountNumber}
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
                    <Button sx={{ backgroundColor: 'black' }} variant="contained" type="submit" color="primary" fullWidth margin="normal">
                        Add Person
                    </Button>
                </form>
            </div>
        </div>
    );
}

export default AddPersonForm;

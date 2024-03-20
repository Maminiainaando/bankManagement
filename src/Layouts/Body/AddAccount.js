import React, { useState, useEffect } from 'react';
import axios from 'axios';

function AddPersonForm() {
    const[Person,setPerson]= useState([]);
    const [formData, setFormData] = useState({
        name: '',
        lastname: '',
        birthdate: '',
        accountNumber: '',
        bankType: ''
    }); 

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const response = await axios.get('http://localhost:8080/account');
            setPerson(response.data);
        } catch (error) {
            console.error('Une erreur s\'est produite lors de la récupération des données:', error);
        }
    };

    const handleChange = e => {
        const { name, value } = e.target;
        setFormData(prevState => ({
            ...prevState,   
            [name]: value
        }));
    };

    const handleSubmit = async () => {
        try {
          await axios.post('http://localhost:8080/account', formData);
          setFormData({
            name: '',
            lastname: '',
            birthdate: '',
            accountNumber: '',
            bankType: ''
          });
          fetchData(); // Recharger les données après l'ajout d'un employé
        } catch (error) {
          console.error('Une erreur s\'est produite lors de l\'ajout de l\'employé:', error);
        }
      };

    }
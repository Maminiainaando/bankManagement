import React, { useState, useEffect } from 'react';
import Link from '@mui/material/Link';
import { Button } from '@mui/material';
import './components.css';
import axios from 'axios';

const Components = () => {
  const [fonctionnaliteActive, setFonctionnaliteActive] = useState('paiement-facture');
  const handleFonctionnaliteClick = (fonctionnalite) => {
    setFonctionnaliteActive(fonctionnalite);
  };

  return (
    <div className="app">
      <Entete />
      <Solde />
      <MenuPrincipal
        fonctionnaliteActive={fonctionnaliteActive}
        onFonctionnaliteClick={handleFonctionnaliteClick}
      />
      <SectionFonctionnalite
        fonctionnaliteActive={fonctionnaliteActive}
      />
    </div>
  );
};

const Entete = () => {
  return (
    <header className="entete">
      <h1>Bonjour</h1>
    </header>
  );
};

const Solde = () => {
  const [accountId, setAccountId] = useState('');
  const [balance, setBalance] = useState(null);
  const [name, setName] = useState(null);
  const [error, setError] = useState(null);

  const fetchAccountBalance = async () => {
    try {
      const response = await fetch(`http://localhost:8080/account/balance/${accountId}`);
      const data = await response.json();
      // Suppose that the backend returns an array with a single Account object
      const account = data[0];
      setBalance(account.balance);
      setName(account.name);
    } catch (error) {
      setError('this account does not exist ');
    }
  };
  return (
    <div>
      <h2>Obtain the balance of Account</h2>
      <div className='find'>
        <input
          type="text"
          placeholder="ID du compte"
          value={accountId}
          onChange={(e) => setAccountId(e.target.value)}
        />
        <button className='obtain' onClick={fetchAccountBalance}>Obtenir le solde</button>
      </div>
      <section className="solde">
        <p>
          The Balance of account of {name}:
        </p>
        {balance !== null && <h1>{balance} Ar</h1>}
        <hr />
        {error && <p style={{ color: 'red' }}>{error}</p>}
      </section>
    </div>
  );
};

const MenuPrincipal = ({ fonctionnaliteActive, onFonctionnaliteClick }) => {
  const fonctionnalites = [
    {
      id: 'Account-List',
      label: 'Account List',
    },
    {
      id: 'Virement',
      label: 'Virement',
    },
    {
      id: 'retrait',
      label: 'Retrait',
    },
    {
      id: 'reception-argent',
      label: 'Réception d\'argent',
    },
    {
      id: 'Account-Statement',
      label: 'Account Statement',
    },
  ];

  return (
    <nav className="menu-principal">
      <button>
        <Link href="Account" underline="none" color={"black"}>
          {"Account List"}
        </Link>
      </button>
      <button>
        <Link href="Transaction" underline="none" color={"black"}>
          {"Transaction List"}
        </Link>
      </button>
      <button>
        <Link href="addTransaction" underline="none" color={"black"}>
          {"New transaction"}
        </Link>
      </button>
      <button>
        <Link href="AddPerson" underline="none" color={"black"}>
          {"Add account"}
        </Link>
      </button>
    </nav>
  );
};

const SectionFonctionnalite = ({ fonctionnaliteActive }) => {
  switch (fonctionnaliteActive) {

  }
};



export default Components;

import React, { useState } from 'react';

import Link from '@mui/material/Link';

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
  return (
    <section className="solde-mvola">
      <p>Total of your Balance :</p>
      <h2>4 676 152 Ar</h2>
      <hr />
    </section>
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
        <Link href="Transaction" underline="none" color={"black"}>
          {"Transaction List"}
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

const Table = () => {
  return (
    <div>

    </div>
  );
};

const SectionFonctionnalite = ({ fonctionnaliteActive }) => {
  switch (fonctionnaliteActive) {
    case 'paiement-facture':
      return <PaiementFacture />;
    case 'transfert-argent':
      return <TransfertArgent />;
    case 'retrait':
      return <Retrait />;
    case 'reception-argent':
      return <ReceptionArgent />;
    default:
      return null;
  }
};

const PaiementFacture = () => {
  // ... code pour le composant PaiementFacture
};

const TransfertArgent = () => {
  // ... code pour le composant TransfertArgent
};

const Retrait = () => {
  // ... code pour le composant Retrait
};

const ReceptionArgent = () => {
  // ... code pour le composant ReceptionArgent
};



export default Components;

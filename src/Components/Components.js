import React, { useState } from 'react';

const Components = () => {
  const [fonctionnaliteActive, setFonctionnaliteActive] = useState('paiement-facture');

  const handleFonctionnaliteClick = (fonctionnalite) => {
    setFonctionnaliteActive(fonctionnalite);
  };

  return (
    <div className="app">
      <Entete />
      <Solde/>
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

 const Solde= () => {
  return (
    <section className="solde-mvola">
      <p>Total Balance</p>
      <h2>4 676 152 Ar</h2>
      <hr/>
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
      {fonctionnalites.map((fonctionnalite) => (
        <button
          key={fonctionnalite.id}
          className={fonctionnaliteActive === fonctionnalite.id ? 'active' : ''}
          onClick={() => onFonctionnaliteClick(fonctionnalite.id)}
        >
          {fonctionnalite.label}
        </button>
      ))}
    </nav>
  );
};

const Table = () => {
    return(
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

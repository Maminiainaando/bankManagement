import React, { useState } from 'react';
import axios from 'axios';

function BalanceViewer() {
  const [idAccount, setIdAccount] = useState('');
  const [dateRegistration, setDateRegistration] = useState('');
  const [balance, setBalance] = useState(null);
  const [error, setError] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.get(`http://localhost:8080/account/${idAccount}/${dateRegistration}`);
      setBalance(response.data);
      setError(null);
    } catch (err) {
      setError('Failed to fetch balance');
      setBalance(null);
    }
  };

  return (
    <div>
      <h2>Balance Viewer</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Account ID:</label>
          <input type="text" value={idAccount} onChange={(e) => setIdAccount(e.target.value)} />
        </div>
        <div>
          <label>Registration Date:</label>
          <input type="text" value={dateRegistration} onChange={(e) => setDateRegistration(e.target.value)} />
        </div>
        <button type="submit">last Balance</button>
      </form>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      {balance !== null && <p>Balance: {balance} Ar</p>}
    </div>
  );
}

export default BalanceViewer;
import React, { useState, useEffect } from 'react';




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
}

export default TransactionList;

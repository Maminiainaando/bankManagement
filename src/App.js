import logo from './logo.svg';
import './App.css';
import Components from "./Components/Components";
import Table from './Layouts/Layouts';
import AccountList from './Layouts/Body/TransactionList';
import TransactionList from './Layouts/Body/TransactionList';
import AddPersonForm from './Layouts/Body/AddAccount';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

function App() {
  return (
    <div>
     <Router>
      <Routes>
      <Route path="/" element={<Components/>}/>
      <Route path="/Account" element={<Table/>}/>
      <Route path="/Transaction" element={<TransactionList/>}/>
      <Route path="/AddPerson" element={<AddPersonForm/>}/>
      </Routes>
     </Router>
    </div>
  );
}

export default App;

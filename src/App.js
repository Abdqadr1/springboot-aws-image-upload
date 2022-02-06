
import './App.css';
import React from 'react';
import {UserProfiles} from "./components/UserProfiles"
import AddUser from './components/addUser';

function App() {
  return (
    <div className="App">
      <AddUser />
      <UserProfiles />
    </div>
  );
}

export default App;

import React from 'react';
import logo from './logo.svg';
import './App.css';
import TicketingApp from './component/TicketingApp';

class App extends React.Component {
  render(){
    return(
      <div className = "container">
        <TicketingApp />
      </div>
    );
  }
}

export default App;

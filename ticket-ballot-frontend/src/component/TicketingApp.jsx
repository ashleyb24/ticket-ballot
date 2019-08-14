import React from 'react';
import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom';
import AllCustomersComponent from './AllCustomersComponent';
import AllEventsComponent from './AllEventsComponent';
import EventComponent from './EventComponent';

class TicketingApp extends React.Component {
  render(){
    return (
      <Router>
        <>
          <h1>Ticket App</h1>
          <Switch>
            <Route path="/customers" exact component={AllCustomersComponent} />
            <Route path="/events" exact component={AllEventsComponent} />
            <Route path="/events/:id" exact component={EventComponent} />
            <Route exact path="/" render={() => <Redirect to="/events"/>} />
          </Switch>
        </>
      </Router>
    )
  }
}

export default TicketingApp

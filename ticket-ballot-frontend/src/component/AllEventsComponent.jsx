import React from 'react';
import EventDataService from '../service/EventDataService';

//component to display all events
class AllEventsComponent extends React.Component{

  constructor(props){
    super(props);
    this.state = {
      events: [],
      message: null
    };
    this.refreshEvents = this.refreshEvents.bind(this);
    this.deleteClickedEvent = this.deleteClickedEvent.bind(this);
    this.updateClickedEvent = this.updateClickedEvent.bind(this);
    this.createNewEvent = this.createNewEvent.bind(this);
  }

  componentDidMount() {
    this.refreshEvents();
  }

  //get events from event API
  refreshEvents() {
    EventDataService.retrieveAllEvents()
        .then(
            response => {
                this.setState({
                  events: response.data //add response to state
                });
            }
        )
  }

  //delete event from event API
  deleteClickedEvent(id){
    EventDataService.deleteEvent(id)
        .then(
            response => {
                this.setState({
                  message: 'Delete of event ' + id + ' successful' //message to be displayed on screen
                });
                this.refreshEvents(); //display updated events list
            }
        )
  }

  //take user to screen where event can be updated
  updateClickedEvent(id){
    this.props.history.push('/events/' + id);
  }

  //take user to screen where event can be created
  createNewEvent(){
    this.props.history.push('/events/new');
  }

  render() {
        return (
            <div className="container">
                <h3>All Events</h3>
                {this.state.message && <div className="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Event Name</th>
                                <th>Venue</th>
                                <th>Capacity</th>
                                <th>Event Type</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                          {this.state.events.map( //display all events in state
                            event =>
                              <tr key={event.id}>
                                <td>{event.id}</td>
                                <td>{event.eventName}</td>
                                <td>{event.venue}</td>
                                <td>{event.capacity}</td>
                                <td>{event.eventType}</td>
                                <td>
                                    <button className="btn btn-info" onClick={() => this.updateClickedEvent(event.id)}>
                                      Edit
                                    </button>
                                </td>
                                <td>
                                    <button className="btn btn-warning" onClick={() => this.deleteClickedEvent(event.id)}>
                                        Delete
                                    </button>
                                </td>
                              </tr>
                          )}
                        </tbody>
                    </table>
                    <button className="btn btn-success float-right" onClick={() => this.createNewEvent()}>New Event</button>
                </div>
            </div>
        )
    }
}

export default AllEventsComponent

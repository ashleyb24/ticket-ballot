import React from 'react';
import EventDataService from '../service/EventDataService';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';

//component to display individual event
//TODO: handle event id being directly placed in url - this could lead to API errors if event id does not exist
class EventComponent extends React.Component{

  constructor(props){
    super(props);
    this.state = {
      id: this.props.match.params.id, //id taken from url
      event: [],
      eventId: '',
      eventName: '',
      venue: '',
      capacity: '',
      eventType: '',
      message: null
    };
    this.refreshEvent = this.refreshEvent.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  componentDidMount(){
    this.refreshEvent();
  }

  //if there is a id then we want to get data from event API
  //otherwise it is a new event so keep the state blank
  refreshEvent(){
    if(this.state.id !== 'new'){
      EventDataService.retrieveEventById(this.state.id)
          .then(
            response => {
              this.setState({
                event: response.data,
                eventId: response.data.id,
                eventName: response.data.eventName,
                venue: response.data.venue,
                capacity: response.data.capacity,
                eventType: response.data.eventType
              })
            }
          )
    }
  }

  onSubmit(values){
    //take form data and place in new event
    let event = {
      eventName: values.eventName,
      venue: values.venue,
      capacity: values.capacity,
      eventType: values.eventType
    }

    //logic to determine if event should be created or updated
    if(this.state.id === 'new'){
      EventDataService.createNewEvent(event).then(
        () => this.props.history.push('/events/')
      )
    }
    else{
      event.id = this.state.id;
      EventDataService.updateEvent(event.id, event).then(
        () => this.props.history.push('/events/')
      )
    }
  }


  render() {
    let {eventId, eventName, venue, capacity, eventType} = this.state

    return (
      <div>
          <h3>Event: {eventId}</h3>
          <div className="container">
              <Formik
                  initialValues={ {eventId, eventName, venue, capacity, eventType} }
                  validateOnChange={false}
                  validateOnBlur={false}
                  //use validation from yup
                  validationSchema={Yup.object().shape({
                    eventName: Yup.string().max(20,'Event Name must be less than 20 characters').required('Event Name is required'),
                    venue: Yup.string().max(20,'Venue must be less than 20 characters').required('Venue is required'),
                    capacity: Yup.number().integer('Capacity must be a valid integer').moreThan(0,'Capacity must be a positive number').required('Capacity is required'),
                    eventType: Yup.string().max(20,'Event Type must be less than 20 characters').required('Event Type is required')
                  })}
                  onSubmit={this.onSubmit}
                  enableReinitialize={true}
              >
                  {
                    (props) => (
                      <Form>
                          <fieldset className="form-group">
                              <label>Event Name</label>
                              <Field className="form-control" type="text" name="eventName" />
                          </fieldset>
                          <ErrorMessage name="eventName" component="div" className="alert alert-warning" />
                          <fieldset className="form-group">
                              <label>Venue</label>
                              <Field className="form-control" type="text" name="venue" />
                          </fieldset>
                          <ErrorMessage name="venue" component="div" className="alert alert-warning" />
                          <fieldset className="form-group">
                              <label>Capacity</label>
                              <Field className="form-control" type="text" name="capacity" />
                          </fieldset>
                          <ErrorMessage name="capacity" component="div" className="alert alert-warning" />
                          <fieldset className="form-group">
                              <label>Event Type</label>
                              <Field className="form-control" type="text" name="eventType" />
                          </fieldset>
                          <ErrorMessage name="eventType" component="div" className="alert alert-warning" />

                          <div className="btn-toolbar justify-content-between">
                              <button className="btn btn-warning float-left" onClick={() => this.props.history.push('/events/')}>Return</button>
                              <div className="btn-group justify-content-between">
                                  <button className="btn btn-warning mr-3" type="button" onClick={props.handleReset}>Reset</button>
                                  <button className="btn btn-success" type="submit">Save</button>
                              </div>
                          </div>
                      </Form>
                    )
                  }
              </Formik>
          </div>
      </div>
    )
  }
}

export default EventComponent

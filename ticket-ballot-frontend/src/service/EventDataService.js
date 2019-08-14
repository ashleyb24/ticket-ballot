import axios from 'axios';

const eventApiUrl = 'http://localhost:8080/event';

//functions to interact with event API
class EventDataService {

  retrieveEventById(id){
    return axios.get(eventApiUrl + '?id=' + id);
  }

  retrieveAllEvents(){
    return axios.get(eventApiUrl + '/all');
  }

  deleteEvent(id){
    return axios.delete(eventApiUrl + '?id=' + id);
  }

  updateEvent(id, event){
    return axios.put(eventApiUrl + '?id=' + id, event);
  }

  createNewEvent(event){
    return axios.post(eventApiUrl + '/new', event);
  }
}

export default new EventDataService()

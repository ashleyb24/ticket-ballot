import axios from 'axios';

const customerApiUrl = 'http://localhost:8080/customer';

//functions to interact with customer API
class CustomerDataService{

  retrieveCustomerById(id){
    return axios.get(customerApiUrl + '?id=' + id);
  }

  retrieveAllCustomers(){
    return axios.get(customerApiUrl + '/all');
  }

  deleteCustomer(id){
    return axios.delete(customerApiUrl + '?id=' + id);
  }
}

export default new CustomerDataService()

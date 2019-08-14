import React from 'react';
import CustomerDataService from '../service/CustomerDataService';

class AllCustomersComponent extends React.Component{

  constructor(props){
    super(props);
    this.state = {
      customers: [],
      message: null
    };
    this.refreshCustomers = this.refreshCustomers.bind(this);
    this.deleteClickedCustomer = this.deleteClickedCustomer.bind(this);
  }

  componentDidMount() {
    this.refreshCustomers();
  }

  refreshCustomers() {
    CustomerDataService.retrieveAllCustomers() //get the data from customer API
        .then(
            response => {
                //console.log(response);
                this.setState({
                  customers: response.data //store data in state
                });
            }
        )
  }

  //tell customer API to delete specific customer
  deleteClickedCustomer(id){
    CustomerDataService.deleteCustomer(id)
        .then(
            response => {
                this.setState({
                  message: 'Delete of customer ' + id + ' successful' //message to display on the screen
                });
                this.refreshCustomers(); //show updated customers list
            }
        )
  }

  //to be displayed on screen
  render() {
        return (
            <div className="container">
                <h3>All Customers</h3>
                {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Email</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                          {this.state.customers.map( //list each customer in state
                            customer =>
                              <tr key={customer.id}>
                                <td>{customer.id}</td>
                                <td>{customer.firstName}</td>
                                <td>{customer.lastName}</td>
                                <td>{customer.email}</td>
                                <td>
                                    <button className="btn btn-warning" onClick={() => this.deleteClickedCustomer(customer.id)}>
                                        Delete
                                    </button>
                                </td>
                              </tr>
                          )}
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

export default AllCustomersComponent

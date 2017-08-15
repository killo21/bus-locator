import React, { Component } from 'react';
import './RouteFrom.css';

class RouteForm extends Component {
  constructor(props) {
    super(props);
    this.state = { id: "", name: "" };

    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleInputChange = this.handleInputChange.bind(this);
  }

  handleInputChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  handleSubmit(event) {
    alert('The state to be submitted: ' + JSON.stringify(this.state));
    event.preventDefault();
  }

  render() {
    return (
      <div>
        <form onSubmit={this.handleSubmit}>
          <div className="Route-form">
            <h3>Submit a Route</h3>
          <label>
            Route ID:<br />
            <input type="text" name="id" value={this.state.id} onChange={this.handleInputChange}/>
          </label>
          <br />
          <br />
          <label>
            Route Name:<br />
            <input type="text" name="name" value={this.state.name} onChange={this.handleInputChange} />
          </label>
          <br />
          <br />
          <input type="submit" value="Submit" />
          </div>
        </form>
      </div>

    );
  }
}

export default RouteForm
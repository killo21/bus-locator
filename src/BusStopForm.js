import React, { Component } from 'react';
import './BusStopForm.css';

class BusStopForm extends Component {
  constructor(props) {
    super(props);
    this.state = { id: "", name: "", route: "" };

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
          <div className="BusStop-form">
            <h3>Submit a Bus Stop</h3>
            <label>Stop ID:<br />
              <input type="text" name="id" value={this.state.id} onChange={this.handleInputChange}/>
            </label>
            
            <br />
            <br />
            <label>Stop Name:<br />
              <input type="text" name="name" value={this.state.name} onChange={this.handleInputChange}/>
            </label>
            <br />
            <br />
            <label>Route: <br />
              <input type="text" name="route" value={this.state.route} onChange={this.handleInputChange}/>
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

export default BusStopForm
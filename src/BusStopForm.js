import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import {Form} from 'mui-react';
import {Input} from 'mui-react';
import {Textarea} from 'mui-react';
import {Button} from 'mui-react'
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
        <form className="mui-form" onSubmit={this.handleSubmit}>
          <div className="BusStop-form">
            <legend>Submit a Bus Stop</legend>
            <div className="mui-textfield mui-textfield--float-label">
              <input type="text" name="id" value={this.state.id} onChange={this.handleInputChange}/>
              <label>Stop ID</label>
            </div>
            <br />
            <br />
            <div className="mui-textfield mui-textfield--float-label">
              <input type="text" name="name" value={this.state.name} onChange={this.handleInputChange}/>
              <label>Stop Name:</label>
            </div>
            <br />
            <br />
            <div className="mui-textfield mui-textfield--float-label">
              <input type="text" name="route" value={this.state.route} onChange={this.handleInputChange}/>
              <label>Route</label>
            </div>
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
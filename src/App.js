import React, { Component } from 'react';
import './App.css';
import RouteForm from './RouteForm';
import BusStopForm from './BusStopForm';

class App extends Component {
  render() {
    return (
      <div className="App">
        <div className="App-header">
          <h2>High Tech High School Bus App</h2>
        </div>
        <p className="App-intro">
          To get started, edit <code>src/App.js</code> and save to reload.
        </p>
        <div>Hello {this.props.name}</div>
        <RouteForm />
        <br />
        <BusStopForm />
      </div>
      
    );
  }
}

export default App;

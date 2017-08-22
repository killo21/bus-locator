import React, { Component } from 'react';
import './App.css';


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
        <h4>Welcome to the High Tech High School Bus Services Application</h4>
        <h3>Lorem ipsum dolor sit amet, pri agam eirmod et.
          Vis alterum dissentiunt ex, in harum iuvaret adipisci
          per. Id malis laboramus per, semper mediocrem pri te, 
          odio mandamus mediocrem vis in. Ut ceteros offendit noluisse
          mea, congue utroque duo at. Id agam epicurei erroribus sea.
          </h3>

      </div>
      
    );
  }
}

export default App;

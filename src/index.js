import React from 'react';
import ReactDOM from 'react-dom';
import {
  BrowserRouter as Router,
  Route
} from 'react-router-dom';
import App from './App';
import BusStopForm from './BusStopForm';
import RouteForm from './RouteForm';
import registerServiceWorker from './registerServiceWorker';

const Root = () => (
  <Router>
    <div>
      <Route exact path="/" component={App} />
      <Route exact path="/add-route" component={RouteForm} />
      <Route exact path="/add-bus-stop" component={BusStopForm} />
    </div>
  </Router>
);

ReactDOM.render(<Root />, document.getElementById('root'));
registerServiceWorker();
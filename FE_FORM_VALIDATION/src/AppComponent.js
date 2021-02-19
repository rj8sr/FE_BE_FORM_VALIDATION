import React from "react";
import ReactDOM from "react-dom";
import AuthForm from "./ValidateForm";
import { HashRouter as Router } from "react-router-dom";
export default class extends React.Component {
  render() {
    return (
      <div>
        <div>
          <Router>
            <AuthForm />
          </Router>
        </div>
      </div>
    );
  }
}

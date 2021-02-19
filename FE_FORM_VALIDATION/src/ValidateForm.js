import React from "react";
import ReactDOM from "react-dom";
import axios from "axios";
import { HashRouter as Router } from "react-router-dom";

class AuthForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      idcs: "",
      telephone: "",
      email: "",
      regexp: /^[0-9\b]+$/,
      data: [],
      textfield: false,
    };
    this.onHandleTelephoneChange = this.onHandleTelephoneChange.bind(this);
    this.onHandleIdcsChange = this.onHandleIdcsChange.bind(this);
    this.validate = this.validate.bind(this);
    this.backToMenu = this.backToMenu.bind(this);
    this.setEmail = this.setEmail.bind(this);
  }

  onHandleTelephoneChange(e) {
    let telephone = e.target.value;
    if (telephone === "" || this.state.regexp.test(telephone)) {
      this.setState({ [e.target.name]: telephone });
    }
  }

  onHandleIdcsChange(e) {
    let idcs = e.target.value;
    if (idcs !== "" && this.state.regexp.test(idcs)) {
      this.setState({ [e.target.name]: idcs });
      let url = "/o/auth/getEmailAndPhoneByIdcs";
      let optUrl = url + "/" + idcs;
      axios.get(optUrl).then((res) => {
        if (
          res.data.length
            ? this.setState({ textfield: true })
            : this.setState({ email:"",telephone:"",textfield: false })
        ) {
          this.setState({ textfield: true });
          console.log(res.data);
        }
      });
    } else {
      this.setState({ idcs: "", textfield: false });
    }
  }

  validate(e) {
    var id = document.getElementById("idcs").value;
    var email = document.getElementById("email").value;
    var number = document.getElementById("number").value;
    if (id !== "" && email !== "" && number !== "") {
      let url = "/o/auth/authenticateUser";
      var searchURL = url;
      axios
        .post(searchURL, null, { params: { id, email, number } })
        .then((res) => {
           if (res.data) {
        	   console.log(res.data)
            this.setState({ data: res.data });
            alert("Successfully Validated" +" With Refernce Id : "+  res.data.slice(5,100));
          } else {
            alert("Validation Fail.");
          }
        });
    } else {
      alert("Please Enter Valid IDCS.");
    }
  }

  setEmail(e) {
    let emaill = e.target.value;
    this.setState({ email: emaill });
  }
  backToMenu() {
    this.setState({ email: "", idcs: "", telephone: "", textfield: false });
  }
  render() {
    return (
      <div className="main">
        <form class="form1">
          <label class="align">IDCS:</label>
          <input
            class="un "
            type="text"
            id="idcs"
            placeholder="Enter Your IDCS"
            value={this.state.idcs}
            onChange={this.onHandleIdcsChange}
            name="idcs"
            maxLength="10"
          />
          <br />

          <label class="align">Email:</label>
          <input
            disabled={this.state.textfield ? "" : "disabled"}
            class="un "
            type="email"
            id="email"
            name="email"
            placeholder="someone@example.com"
            value={this.state.email}
            onChange={this.setEmail}
          />

          <label class="align">PhoneNumber:</label>
          <input
            disabled={this.state.textfield ? "" : "disabled"}
            class="un "
            type="text"
            name="telephone"
            id="number"
            placeholder="Enter Your Phone Number"
            maxLength="10"
            value={this.state.telephone}
            onChange={this.onHandleTelephoneChange}
          />

          <input
            class="submit"
            type="button"
            value="Submit"
            onClick={this.validate}
          />
          <br />
          <br />
          <input
            class="submit"
            type="button"
            value="Cancel"
            onClick={this.backToMenu}
          />
        </form>
      </div>
    );
  }
}
export default AuthForm;

import React, { useState } from "react";
import { Navbar, Nav, NavDropdown, Container, Modal, Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import { useAuth } from "../context/AuthContext";  // ✅ Import Authentication Context
import Login from "../pages/Login";
import Signup from "../pages/Signup";

const NavigationBar = () => {
  const [showLogin, setShowLogin] = useState(false);
  const [showSignup, setShowSignup] = useState(false);
  const { user, signout } = useAuth();  // ✅ Get User & Signout function from Auth Context

  return (
    <>
      <Navbar expand="lg" bg="success" variant="dark">
        <Container>
          <Navbar.Brand as={Link} to="/">Policy Hub</Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              {/* Insurance Types Dropdown */}
              <NavDropdown title="Insurance Types" id="insurance-dropdown">
                <NavDropdown.Item as={Link} to="/insurance/life">Life Insurance</NavDropdown.Item>
                <NavDropdown.Item as={Link} to="/insurance/home">Home Insurance</NavDropdown.Item>
              </NavDropdown>

              {/* Purchase Policy Dropdown */}
              <NavDropdown title="Purchase Policy" id="purchase-dropdown">
                <NavDropdown.Item as={Link} to="/purchase/new">New Policy</NavDropdown.Item>
                <NavDropdown.Item as={Link} to="/policy/view">View Policy</NavDropdown.Item>
              </NavDropdown>

              {/* Claim Policy Dropdown */}
              {/* <NavDropdown title="Claim Policy" id="claim-dropdown">
                <NavDropdown.Item as={Link} to="/claim/initiate">Initiate Claim</NavDropdown.Item>
                <NavDropdown.Item as={Link} to="/claim/status">Check Claim Status</NavDropdown.Item>
              </NavDropdown> */}
            </Nav>

            {/* Authentication Buttons */}
            <Nav>
              {user ? (
                <>
                  <span className="text-white me-3">Welcome, {user.name}</span>
                  <Button variant="light" onClick={signout}>Logout</Button>
                </>
              ) : (
                <>
                  <Button variant="light" className="me-2" onClick={() => setShowLogin(true)}>Sign In</Button>
                  <Button variant="light" onClick={() => setShowSignup(true)}>Sign Up</Button>
                </>
              )}
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>

      {/* Sign In Modal */}
      <Modal show={showLogin} onHide={() => setShowLogin(false)} centered>
        <Modal.Header closeButton />
        <Modal.Body>
          <Container>
            <Login onSuccess={() => setShowLogin(false)} />
          </Container>
        </Modal.Body>
      </Modal>

      {/* Sign Up Modal */}
      <Modal show={showSignup} onHide={() => setShowSignup(false)} centered>
        <Modal.Header closeButton />
        <Modal.Body>
          <Container>
            <Signup onSuccess={() => setShowSignup(false)} />
          </Container>
        </Modal.Body>
      </Modal>
    </>
  );
};

export default NavigationBar;

import React, { useEffect, useState } from "react";
import { Container, Row, Col, Card, Button, Spinner, Alert, Form } from "react-bootstrap";
import PolicyTypeList from "../routes/PolicyTypeList";

const Home = () => {


  return (
    <Container className="mt-4">
      <Row className="text-center mb-4">
        <Col>
          <h1>Welcome to Policy Hub</h1>
          <p>Find and purchase the best insurance policies.</p>
        </Col>
      </Row>
      <PolicyTypeList/>
    
    </Container>
  );
};

export default Home;
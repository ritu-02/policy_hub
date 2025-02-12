import React, { useEffect, useState } from "react";
import api from "../services/api";
import { Container, Row, Col, Card, Spinner, Alert } from "react-bootstrap";

const PolicyTypeList = () => {
  const [policyTypes, setPolicyTypes] = useState([]); // Ensure default is an array
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchPolicyTypes = async () => {
      try {
        const response = await api.get("/policy-type");
        console.log("API Response:", response.data); // Debugging
        setPolicyTypes(Array.isArray(response.data) ? response.data : []);
      } catch (err) {
        console.error("Error fetching policy types:", err);
        setError("Failed to load policy types");
      } finally {
        setLoading(false);
      }
    };

    fetchPolicyTypes();
  }, []);

  if (loading)
    return (
      <Container className="text-center mt-4">
        <Spinner animation="border" variant="primary" />
      </Container>
    );

  if (error) return <Alert variant="danger">{error}</Alert>;

  return (
    <Container className="mt-4">
      <Row>
        {Array.isArray(policyTypes) && policyTypes.length > 0 ? (
          policyTypes.map((policy, index) => (
            <Col md={4} key={index} className="mb-4">
              <Card>
                <Card.Body>
                  <Card.Title>{policy.insuranceType}</Card.Title>
                  <Card.Text>{policy.description}</Card.Text>
                </Card.Body>
              </Card>
            </Col>
          ))
        ) : (
          <Col>
            <Alert variant="info">No policy types available.</Alert>
          </Col>
        )}
      </Row>
    </Container>
  );
};

export default PolicyTypeList;

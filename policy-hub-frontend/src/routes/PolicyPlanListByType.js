import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import { Container, Row, Col, Card, Spinner, Alert, Button } from "react-bootstrap";

const PolicyPlanListByType = () => {
  const { type } = useParams(); // Get policy type from URL
  const [policyPlans, setPolicyPlans] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchPolicyPlans = async () => {
      try {
        const response = await axios.get(`http://localhost:8081/api/policy-plan?type=${type}`);
        setPolicyPlans(Array.isArray(response.data) ? response.data : []);
      } catch (err) {
        console.error("Error fetching policy plans:", err);
        setError("Failed to load policy plans for " + type);
      } finally {
        setLoading(false);
      }
    };

    fetchPolicyPlans();
  }, [type]);

  const handleCardClick = (policyPlanId) => {
    navigate(`/policy-plan/${policyPlanId}`);
  };

  if (loading)
    return (
      <Container className="text-center mt-4">
        <Spinner animation="border" variant="primary" />
      </Container>
    );

  if (error) return <Alert variant="danger">{error}</Alert>;

  return (
    <Container className="mt-4">
      <h2 className="mb-4 text-center">Available Plans for {type.replace("-", " ")}</h2>
      <Row>
        {policyPlans.length > 0 ? (
          policyPlans.map((plan) => (
            <Col md={4} key={plan.policyPlanId} className="mb-4">
              <Card style={{ cursor: "pointer" }} onClick={() => handleCardClick(plan.policyPlanId)}>
                <Card.Body>
                  <Card.Title>{plan.policyPlanName}</Card.Title>
                  <Card.Text>
                    <strong>Premium:</strong> ₹{plan.premium} <br />
                    <strong>Sum Insured:</strong> ₹{plan.sumInsured} <br />
                    <strong>Term:</strong> {plan.term} <br />
                    <strong>Eligibility:</strong> {plan.eligibility}
                  </Card.Text>
                  <Button variant="primary" onClick={() => handleCardClick(plan.policyPlanId)}>View Details</Button>
                </Card.Body>
              </Card>
            </Col>
          ))
        ) : (
          <Col>
            <Alert variant="info">No policy plans available for {type.replace("-", " ")}.</Alert>
          </Col>
        )}
      </Row>
    </Container>
  );
};

export default PolicyPlanListByType;

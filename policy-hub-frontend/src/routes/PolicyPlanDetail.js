import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import { Container, Card, Spinner, Alert, Button } from "react-bootstrap";

const PolicyPlanDetail = () => {
  const { id } = useParams();
  const [policyPlan, setPolicyPlan] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchPolicyPlan = async () => {
      try {
        const response = await axios.get(`http://localhost:8081/api/policy-plan/${id}`);
        setPolicyPlan(response.data);
      } catch (err) {
        console.error("Error fetching policy plan details:", err);
        setError("Failed to load policy plan details");
      } finally {
        setLoading(false);
      }
    };

    fetchPolicyPlan();
  }, [id]);

  if (loading)
    return (
      <Container className="text-center mt-4">
        <Spinner animation="border" variant="primary" />
      </Container>
    );

  if (error) return <Alert variant="danger">{error}</Alert>;

  return (
    <Container className="mt-4">
      <Button variant="secondary" onClick={() => navigate(-1)}>Back</Button>
      <Card className="mt-3">
        <Card.Body>
          <Card.Title>{policyPlan.policyPlanName}</Card.Title>
          <Card.Text>
            <strong>Description:</strong> {policyPlan.description} <br />
            <strong>Premium:</strong> ₹{policyPlan.premium} <br />
            <strong>Sum Insured:</strong> ₹{policyPlan.sumInsured} <br />
            <strong>Term:</strong> {policyPlan.term} <br />
            <strong>Eligibility:</strong> {policyPlan.eligibility}
          </Card.Text>
        </Card.Body>
      </Card>
    </Container>
  );
};

export default PolicyPlanDetail;
